package main

import (
	"context"
	"errors"
	"fmt"
	"math/rand"
	"sync"
	"testing"
	"time"
)

type FastFailErrGroup struct {
	once   sync.Once
	ctx    context.Context
	cancel context.CancelCauseFunc
}

func (g *FastFailErrGroup) Go(f func(ctx context.Context) error) {
	g.once.Do(func() {
		g.ctx, g.cancel =
	})
	go func(ctx context.Context, cancel context.CancelCauseFunc, f func(ctx2 context.Context) error) {
		if err := f(ctx); err != nil {
			cancel(err)
		}
	}(ctx, cancel, f)
}

func TestDoTask(t *testing.T) {
	ctx, cancel := context.WithCancelCause(context.Background())

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
				if rand.Intn(10) > 5 {
					return errors.New("f2 error")
				}
			}
		}
	}
}
