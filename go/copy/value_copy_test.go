package copy

import (
	"fmt"
	"testing"
)

type Student struct {
	Age int
}

func TestValueCopy(t *testing.T) {
	s := &Student{Age: 10}
	m := s
	m.Age = 20
	fmt.Printf("%v\n", *s)
}
