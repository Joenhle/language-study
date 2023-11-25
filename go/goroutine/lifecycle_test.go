package goroutine

import (
	"fmt"
	"testing"
	"time"
)

/*
*
go没有父协程和子协程的区别
*/
func TestLifeCycle(t *testing.T) {
	go func() {
		go func() {
			time.Sleep(3 * time.Second)
			fmt.Println("son dead")

		}()
		fmt.Println("father dead")
	}()
	time.Sleep(5 * time.Second)
}
