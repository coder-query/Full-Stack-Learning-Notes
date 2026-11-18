package main

import "fmt"

func main() {
	// 正确示例
	var nums [50]string
	nums[0] = "one"
	nums[1] = "two"
	nums[2] = "three"
	nums[3] = "four"
	nums[4] = "five"
	nums[5] = "six"
	for index, value := range nums {
		fmt.Printf("%d:%s", index, value)
	}
}
