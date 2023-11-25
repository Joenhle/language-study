package goroutine

import (
	"fmt"
	"testing"
	"time"
)

type Student struct {
	Age int
}

func TestCopy(t *testing.T) {
	s := Student{Age: 10}
	fmt.Printf("%p\n", &s)
	go func() {
		s.Age = 20
		fmt.Printf("%p\n", &s)
	}()
	time.Sleep(1 * time.Second)
	fmt.Printf("%v\n", s)
}
