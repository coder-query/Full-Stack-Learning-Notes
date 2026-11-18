package main

import (
	"fmt"
	"sync"
	"time"
)

func main() {
	start_time := time.Now()
	fmt.Printf("start_time = %v\n", start_time)
	wg := sync.WaitGroup{}
	wg.Add(10)
	for i := 0; i < 10; i++ {
		go func(i int) {
			defer wg.Done()
			time.Sleep(time.Second * 3)
			println("go routine", i)
		}(i)
	}
	wg.Wait()
	fmt.Printf("main\n")
	end_time := time.Now()
	//fmt.Printf("end_time = %v\n", end_time)
	fmt.Printf("end_time - start_time = %v\n", end_time.Sub(start_time))

}
