package main

import (
	"fmt"
	"math"
	"math/rand"
	"runtime"
	"time"
)

func Method1() {
	ch := make(chan struct{}, 300)
	maxCount := math.MaxInt64
	for i := 0; i < maxCount; i++ {
		ch <- struct{}{}
		go func(i int) {
			time.Sleep(time.Duration(rand.Intn(5)))
			fmt.Println("当前任务为", i, " 当前的协程数为:", runtime.NumGoroutine())
			<-ch
		}(i)
	}
}

func main() {
	Method1()
}
