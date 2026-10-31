package main

import (
	"fmt"
	"time"
)

// go程直接的通信： channel 通道

func main() {
	// 创建通道
	numChan := make(chan int, 10)

	// 启动第一个go程，向通道写数据
	go func() {
		for i := 1; i <= 5; i++ {
			numChan <- i
		}
		fmt.Println("写完了")
		// 写完一定记得关闭通道
		close(numChan)
	}()

	// 启动另外2个go程，从通道读数据
	for i := 0; i < 2; i++ {
		go func() {
			for num := range numChan {
				fmt.Printf("收到数字: %d\n", num)
			}
		}()
	}

	time.Sleep(time.Second * 10)
}
