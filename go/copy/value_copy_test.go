package copy

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

type Student struct {
	Age int
}

func TestValueCopy(t *testing.T) {
	t.Run("结构体赋值", func(t *testing.T) {
		// 结构体赋值会进行值拷贝
		s := Student{Age: 10}
		m := s
		m.Age = 20
		assert.Equal(t, 10, s.Age)
	})

	t.Run("指针赋值", func(t *testing.T) {
		s := &Student{Age: 10}
		m := s
		m.Age = 20
		assert.Equal(t, 20, s.Age)
	})

	t.Run("map赋值", func(t *testing.T) {
		// map其实底层是有一个指针类型的引用遍历，就算是值拷贝，底层也是一样的，slice，channel都是一样的
		s := make(map[int]int)
		s[1] = 1
		m := s
		m[1] = 2
		assert.Equal(t, 2, s[1])
	})

	t.Run("channel赋值", func(t *testing.T) {
		// channel底层也是一个循环数组的指针，值拷贝还是指向同一个数组
		s := make(chan int, 10)
		s <- 1
		m := s
		assert.Equal(t, 1, <-m)
	})

	t.Run("slice赋值", func(t *testing.T) {

		t.Run("test1", func(t *testing.T) {
			// 在这个例子中，slice2在append的时候，由于容量不够，所以创建了一个新的数组，导致slice1和slice2的底层数组不一样了，并不会去改变slice1的底层数组。但是当容量够的时候就会去改变底层数组
			slice1 := make([]int, 3, 3)
			slice2 := append(slice1, 5, 6, 7)
			slice1[0] = 3
			fmt.Println(slice1, len(slice1), cap(slice1))
			fmt.Println(slice2, len(slice2), cap(slice2))
		})

		t.Run("test2", func(t *testing.T) {
			// 在这个例子中，slice2在append的时候，由于容量不够，所以创建了一个新的数组，导致slice1和slice2的底层数组不一样了，并不会去改变slice1的底层数组。但是当容量够的时候就会去改变底层数组，如果不对一个切片变量重新赋值它的元素个数和cap是不会变的
			slice1 := make([]int, 3, 6)
			fmt.Println(slice1, len(slice1), cap(slice1))
			slice2 := append(slice1, 5, 6, 7)
			slice1[0] = 3
			fmt.Println(slice1, len(slice1), cap(slice1))
			fmt.Println(slice2, len(slice2), cap(slice2))
		})
	})

}
