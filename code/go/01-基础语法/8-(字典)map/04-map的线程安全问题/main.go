package main

import (
	"fmt"
	"sync"
)

func main() {
	var waitGroup sync.WaitGroup
	waitGroup.Add(10)
	// map
	//mp := make(map[string]int, 10)
	mp := sync.Map{}
	for i := 0; i < 10; i++ {
		go func() {
			defer waitGroup.Done()
			// 写操作
			for i := 0; i < 100; i++ {
				mp.Store("helloworld", 1)
			}
			// 读操作
			for i := 0; i < 10; i++ {
				fmt.Println(mp.Load("helloworld"))
			}
		}()
	}
	waitGroup.Wait()
}
