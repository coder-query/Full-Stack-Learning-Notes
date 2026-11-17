package main

import (
	"fmt"
	"strconv"
	"sync"
	"time"
)

func printHello() {
	for i := 1; i <= 10; i++ {
		str, err := strconv.Atoi("123")
		if err != nil {
			fmt.Println("error:", err)
			continue
		}
		fmt.Println("str", str)
		fmt.Println("go协程：hello, golang")
		time.Sleep(time.Second * 1)
	}
}
func main() {
	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		defer wg.Done()
		printHello()
	}()

	for i := 1; i <= 10; i++ {
		fmt.Println("主线程main：hello, golang")
		time.Sleep(time.Second)
	}

	wg.Wait() // 等子协程真正结束
}
