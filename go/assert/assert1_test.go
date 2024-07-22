package assert

import (
	"fmt"
	"golang.org/x/time/rate"
	"testing"
)

type Animal interface {
	Speak() string
}

type Dog struct {
}

func (d Dog) Speak() string {
	return "Woof!"
}

func (d Dog) Walk() string {
	return "dog walk!"
}

func (d *Dog) PointerMethod() string {
	return "dog pointer method"
}

func TestAssert1(t *testing.T) {
	var base interface{}
	base = Dog{}

	if a, ok := base.(interface{ Speak() string }); ok {
		fmt.Println(a.Speak())
	} else {
		fmt.Println("base没有speak这个方法")
	}

	if a, ok := base.(interface{ Walk() string }); ok {
		fmt.Println(a.Walk())
	} else {
		fmt.Println("base没有walk这个方法")
	}

	if a, ok := base.(interface{ Fly() string }); ok {
		fmt.Println(a.Fly())
	} else {
		fmt.Println("base没有Fly这个方法")
	}

	if a, ok := base.(interface{ PointerMethod() string }); ok {
		fmt.Println(a.PointerMethod())
	} else {
		fmt.Println("base没有PointerMethod这个方法")
	}

	var base2 interface{}
	base2 = &Dog{}

	if a, ok := base2.(interface{ PointerMethod() string }); ok {
		fmt.Println(a.PointerMethod())
	} else {
		fmt.Println("base2没有PointerMethod这个方法")
	}
	limiter := rate.NewLimiter(1, 1)
	limiter.Wait()

}
