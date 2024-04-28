package keng

import (
	"fmt"
	"testing"
)

func TestShadowsDeclaration(t *testing.T) {
	// 在编程中，“shadows declaration”意味着在当前作用域内重新声明了已存在的标识符（变量名，函数名等），从而隐藏了外部作用域中同名的标识符
	x := 10
	fmt.Println("Outer x:", x)
	{
		x := 20
		fmt.Println("Inter x:", x)
	}
	fmt.Println("Outer x:", x)
}
