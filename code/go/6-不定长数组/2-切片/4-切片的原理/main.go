package main

import (
	"fmt"
	"strconv"
)

func printSlice(data []string) {
	data[0] = "java"
	for i := 0; i < 10; i++ {
		data = append(data, strconv.Itoa(i))
	}
}

func main() {
	//go的slice在函数参数传递的时候是值传递还是引用传递： 值传递， 效果又呈现出了引用的效果（不完全是）
	courses := []string{"go", "grpc", "gin"}
	printSlice(courses)
	fmt.Println(courses)

	var data []int
	for i := 0; i < 2000; i++ {
		data = append(data, i)
		fmt.Printf("len: %d, cap: %d\r\n", len(data), cap(data))
	}
}
