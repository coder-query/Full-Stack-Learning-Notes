package main

import (
	"fmt"
	"strconv"
	"sync"
)

var wg sync.WaitGroup = sync.WaitGroup{}

func consumer(intChan chan string) {
	defer wg.Done()
	for data := range intChan {
		fmt.Printf("消费数据：%v\n", data)
	}
	fmt.Println("数据全部消费完毕！！！")
}

func producer(intChan chan string) {
	defer wg.Done()
	defer close(intChan) // 生产完成后关闭 channel
	for i := 1; i <= 10; i++ {
		d := strconv.Itoa(i) + "@"
		intChan <- d
		fmt.Println("生产数据：", d)
	}
	fmt.Println("数据生产完毕！！！")
}

func main() {
	wg.Add(2)
	var intChan chan string = make(chan string, 20)
	go consumer(intChan)
	go producer(intChan)
	fmt.Println("go coroutine")
	wg.Wait()
	//close(intChan)
}
