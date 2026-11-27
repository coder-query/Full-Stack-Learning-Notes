package main

import (
	"fmt"
	"time"
)

func main() {
	// 获取当前时间
	now := time.Now()
	fmt.Printf("当前时间: %v\n", now)

	// 获取时间戳
	fmt.Printf("时间戳(秒): %d\n", now.Unix())
	fmt.Printf("时间戳(纳秒): %d\n", now.UnixNano())
}
