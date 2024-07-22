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

func TestRangeCopy(t *testing.T) {
	for _, value := range []int{1, 2, 3} {
		go func() {
			println(value)
		}()
	}
}

type A struct {
	name string
}

func TestNilMap(t *testing.T) {
	func() {
		a := new(A)
		a.name = "123"
		defer fmt.Printf("%+v", a)
		a.name = "456"
	}()
}
