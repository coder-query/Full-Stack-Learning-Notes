package main

import (
	"fmt"
	"sync"
)

var lock sync.Mutex = sync.Mutex{}
var wg sync.WaitGroup = sync.WaitGroup{}
var num int = 0

func add() {
	defer wg.Done()
	lock.Lock()
	defer lock.Unlock()
	for i := 0; i < 1000000; i++ {
		num++
	}
	fmt.Println("add 完成")
}

func sub() {
	lock.Lock()
	defer lock.Unlock()
	defer wg.Done()
	for i := 0; i < 1000000; i++ {
		num--
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
