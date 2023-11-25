package main

import (
	"context"
	"fmt"
	"sync"
	"time"
)

func f1(ctx context.Context, wg *sync.WaitGroup, taskOutputQueue chan<- int) {
	i := 0
	for {
		select {
		case <-ctx.Done():
			fmt.Println("f1 got done signal, done")
			wg.Done()
			return
		default:
			taskOutputQueue <- i
			if i > 10 {
				fmt.Printf("%d > 10, done!\n", i)
				wg.Done()
				return
			}
			i++
		}
	}
}

func f2(ctx context.Context, wg *sync.WaitGroup, taskInputQueue <-chan int, taskOutputQueue chan<- int) {
	for {
		select {
		case <-ctx.Done():
			fmt.Println("f2 got done signal, over!")
			wg.Done()
			return
		case i := <-taskInputQueue:
			time.Sleep(500 * time.Millisecond)
			taskOutputQueue <- i + 1
		}
	}
}

func f3(ctx context.Context, wg *sync.WaitGroup, taskInputQueue <-chan int, taskOutputQueue chan<- int) {
	for {
		select {
		case <-ctx.Done():
			fmt.Println("f3 got done signal, over!")
			wg.Done()
			return
		case i := <-taskInputQueue:
			time.Sleep(500 * time.Millisecond)
			taskOutputQueue <- i * 2
		}
	}
}

func f4(ctx context.Context, wg *sync.WaitGroup, taskInputQueue <-chan int) {
	for {
		select {
		case <-ctx.Done():
			fmt.Println("f4 got done signal, over!")
			wg.Done()
			return
		case i := <-taskInputQueue:
			fmt.Printf("res=%d\n", i)
		}
	}
}

func main() {
	q1, q2, q3 := make(chan int, 300), make(chan int, 300), make(chan int, 300)
	ctx, _ := context.WithTimeout(context.Background(), 5*time.Second)
	wg := &sync.WaitGroup{}
	wg.Add(4)
	go f1(ctx, wg, q1)
	go f2(ctx, wg, q1, q2)
	go f3(ctx, wg, q2, q3)
	go f4(ctx, wg, q3)
	wg.Wait()
	close(q1)
	close(q2)
	close(q3)
	fmt.Println("done")
}
