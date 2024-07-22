package main

import (
	"context"
	"fmt"
	"testing"
	"time"
)

func TestPropagate(t *testing.T) {
	// context的cancel具有传播能力，父节点如果cancel了，子节点也会cancel

	f := func(ctx context.Context, name string) error {
		for {
			select {
			case <-ctx.Done():
				fmt.Printf("%s over!\n", name)
				return nil
			default:
				time.Sleep(1 * time.Second)
				fmt.Printf("%s doing...\n", name)
			}
		}
	}

	father_ctx, _ := context.WithTimeout(context.Background(), 5*time.Second)
	chid_ctx, _ := context.WithTimeout(father_ctx, 10*time.Second)

	go func() {
		f(father_ctx, "father")
	}()

	go func() {
		f(chid_ctx, "child")
	}()

	// 可以看到，5s后父任务结束，子任务也结束，就是cancel转播造成的
	time.Sleep(10 * time.Second)
}
