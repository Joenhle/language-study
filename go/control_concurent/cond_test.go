package other

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

var done = false

func read(name string, c *sync.Cond) {
	c.L.Lock()
	for !done {
		c.Wait()
	}
	fmt.Println(name + " read done")
	c.L.Unlock()
}

func write(name string, c *sync.Cond) {
	fmt.Println(name + " start writing")
	c.L.Lock()
	time.Sleep(time.Second)
	done = true
	c.L.Unlock()
	fmt.Println(name + "wakes all")
	c.Broadcast()
}

func TestCondition(t *testing.T) {
	cond := sync.NewCond(&sync.Mutex{})

	go read("read1", cond)
	go read("read2", cond)
	go read("read3", cond)

	write("write", cond)
	time.Sleep(3 * time.Second)
}
