package Errgroup

import (
	"errors"
	"fmt"
	"golang.org/x/sync/errgroup"
	"testing"
	"time"
)

func TestWait(t *testing.T) {
	// errgroup的Wait必须要所有协程都执行完了之后才会返回，不会因为其中一个协程发生了err就返回，如果有协程返回了err，Wait方法会返回第一个返回err的那个err
	var g errgroup.Group

	g.Go(func() error {
		time.Sleep(5 * time.Second)
		fmt.Println("req 1 suc")
		return nil
	})

	g.Go(func() error {
		time.Sleep(10 * time.Second)
		fmt.Println("req 2 failed")
		return errors.New("req error")
	})

	g.Go(func() error {
		time.Sleep(30 * time.Second)
		fmt.Println("req 3 suc")
		return nil
	})

	if err := g.Wait(); err == nil {
		fmt.Printf("suc")
	} else {
		fmt.Println("failed")
	}
}

func TestCancel(t *testing.T) {

}
