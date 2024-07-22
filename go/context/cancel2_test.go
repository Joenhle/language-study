package main

import (
	"context"
	"errors"
	"fmt"
	"math/rand"
	"testing"
	"time"
)

func TestDoTask(t *testing.T) {
	// 能做到多个任务并发执行，如果有一个失败则ctx传播及时阻断，且外部能用ctx.Done进行fast-fail，并利用context.Cause(ctx)返回被cancel的原因，且所有任务都完成也能感知。
	ctx, cancel := context.WithCancelCause(context.Background())
	suc_chan := make(chan struct{}, 2)
	f1 := func(ctx2 context.Context) error {
		for {
			select {
			case <-ctx2.Done():
				fmt.Println("f1 canceled!")
				return nil
			default:
				time.Sleep(2 * time.Second)
				fmt.Println("f1 doing")
			}
		}
	}
	f2 := func(ctx2 context.Context) error {
		for {
			select {
			case <-ctx2.Done():
				fmt.Println("f2 canceled!")
				return nil
			default:
				time.Sleep(1 * time.Second)
				if rand.Intn(10) > 8 {
					return errors.New("f2 error")
				}
			}
		}
	}
	for _, f := range []func(ctx2 context.Context) error{f1, f2} {
		go func(f func(context.Context) error) {
			if err := f(ctx); err != nil {
				cancel(err)
			} else {
				suc_chan <- struct{}{}
			}
		}(f)
	}
	i := 0
LOOP:
	for {
		select {
		case <-suc_chan:
			i += 1
			if i == 2 {
				fmt.Println("all success!")
				break LOOP
			}
		case <-ctx.Done():
			fmt.Println(context.Cause(ctx))
			break LOOP
		}
	}
	time.Sleep(10 * time.Second)
}
