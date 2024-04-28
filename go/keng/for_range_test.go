package keng

import (
	"fmt"
	"testing"
	"time"
	"unsafe"
)

//func TestAssign(t *testing.T) {
//	// 不能对for-range的value进行赋值，只能作为右值，不能作为左值
//	arr := [2]int{1, 2}
//	for _, v := range arr {
//		v = 3
//	}
//}

func TestReference(t *testing.T) {
	// go里面只有值拷贝，没有引用变量这一说，变量的赋值都是值拷贝，所以for range里的v其实也是创建的一个变量，然后不断循环进行值拷贝
	type Student struct {
		scores [1000]int
	}
	arr := [2]Student{}
	fmt.Printf("%p, %v\n", &arr[0], unsafe.Sizeof(arr[0]))
	for _, v := range arr {
		fmt.Printf("%p, %v\n", &v, unsafe.Sizeof(v))
	}
}

func TestReCopy(t *testing.T) {
	t.Run("no_copy", func(t *testing.T) {
		// 如果在for-range里面使用协程，协程直接使用for-range的value可能会出错，因为用的同一个变量，在协程生成时进行的闭包的参数value可能已经变了。
		arr := []int{1, 2, 3, 4, 5}
		for _, num := range arr {
			go func() {
				fmt.Println(num)
			}()
		}
		time.Sleep(5 * time.Second)
	})

	t.Run("right_copy1", func(t *testing.T) {
		// 如果在for-range里面使用协程，协程直接使用for-range的value可能会出错，因为用的同一个变量，在协程生成时进行的闭包的参数value可能已经变了。
		arr := []int{1, 2, 3, 4, 5}
		for _, num := range arr {
			// 这里创建了一个新的相同名称的闭包变量shadow declaration，已经发生了值拷贝，且后续循环的时候这个变量的值不会发生改变
			num := num
			go func() {
				fmt.Println(num)
			}()
		}
		time.Sleep(5 * time.Second)
	})

	t.Run("right_copy1", func(t *testing.T) {
		// 如果在for-range里面使用协程，协程直接使用for-range的value可能会出错，因为用的同一个变量，在协程生成时进行的闭包的参数value可能已经变了。
		arr := []int{1, 2, 3, 4, 5}
		for _, num := range arr {
			// 这种方式在协程创建之前就先把变量拷贝作为参数供后面使用
			go func(tmp int) {
				fmt.Println(tmp)
			}(num)
		}
		time.Sleep(5 * time.Second)
	})
}
