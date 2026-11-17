package main

import "fmt"

func main() {
	var intChan chan int
	intChan = make(chan int, 3)
	intChan <- 10
	intChan <- 20
	intChan <- 30
	defer close(intChan)
	for v := range intChan {
		fmt.Println("v", v)
	}
}
