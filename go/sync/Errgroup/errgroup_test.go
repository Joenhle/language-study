package Errgroup

import (
	"errors"
	"fmt"
	"golang.org/x/sync/errgroup"
	"testing"
	"time"
)

func TestErrGroup(t *testing.T) {
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
