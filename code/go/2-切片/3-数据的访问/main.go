package main

import "fmt"

func main() {
	// 切片初始化
	var nums []int = []int{1, 2, 3, 4, 5}
	// 1. 访问切片数据，索引下标
	fmt.Println(nums[0])
	fmt.Println(nums[1])
	fmt.Println("------------------")
	// 2. 访问切片数据，步长
	// start:end
	// start--->启始索引下标
	// end---->结束索引下标
	fmt.Println(nums[0:3])
	// 取全部的数据
	fmt.Println(nums[:])
	// 3. for遍历
	for i := 0; i < len(nums); i++ {
		fmt.Println(nums[i])
	}
	fmt.Println("------------------")
	for idx, val := range nums {
		fmt.Printf("Index: %d, Value: %d\n", idx, val)
	}
}
