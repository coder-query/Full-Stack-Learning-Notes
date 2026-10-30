package main

import "fmt"

func main() {
	var nums []int // 值
	nums = append(nums, 0, 111, 333)
	for idx, val := range nums {
		fmt.Printf("Index: %d, Value: %d\n", idx, val)

	}
}
