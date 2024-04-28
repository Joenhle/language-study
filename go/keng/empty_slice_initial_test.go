package keng

import (
	"encoding/json"
	"fmt"
	"testing"
)

func Test(t *testing.T) {
	// method 1, 仅申明，不初始化，这种序列化之后是null
	var a []int
	marshal, _ := json.Marshal(a)
	fmt.Println(string(marshal))

	// method 2, 初始化为空切片，这种序列化之后是[]
	b := []int{}
	marshal, _ = json.Marshal(b)
	fmt.Println(string(marshal))
}
