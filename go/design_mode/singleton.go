package design_mode

import "sync"

type A struct {
	name string
}

var a *A = nil
var once sync.Once

func GetASingleton() *A {
	once.Do(func() {
		a = &A{name: "123"}
	})
	return a
}
