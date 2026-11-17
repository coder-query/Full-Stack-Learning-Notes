package main

import "fmt"

func main() {
	// 1. 初始化第1种方式
	var nums1 [5]int = [5]int{1, 2, 3, 4, 5}
	for idx, val := range nums1 {
		fmt.Printf("Index: %d, Value: %d\n", idx, val)
	}
	fmt.Println("-------------------------------")
	// 2. 初始化第2种方式
	var nums2 = [...]int{1, 2}
	for idx, val := range nums2 {
		fmt.Printf("Index: %d, Value: %d\n", idx, val)
	}
	fmt.Println("-------------------------------")
	// 3. 初始化第3种方式
	var nums3 [5]int = [5]int{1: 2, 3: 4}
	for idx, val := range nums3 {
		fmt.Printf("Index: %d, Value: %d\n", idx, val)
	}
}
