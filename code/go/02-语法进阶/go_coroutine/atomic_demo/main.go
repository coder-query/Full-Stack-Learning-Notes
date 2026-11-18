package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

var wg sync.WaitGroup = sync.WaitGroup{}
var num int32 = 0

func add() {
	defer wg.Done()
	for i := 0; i < 1000000; i++ {
		atomic.AddInt32(&num, 1)
	}
	fmt.Println("add 完成")
}

func sub() {

	defer wg.Done()
	for i := 0; i < 1000000; i++ {
		atomic.AddInt32(&num, -1)
	}
	fmt.Println("sub 完成")
}

func main() {
	wg.Add(2)
	go add()
	go sub()
	wg.Wait()
	fmt.Printf("num = %v\n", num)

}
