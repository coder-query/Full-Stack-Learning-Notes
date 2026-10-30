package main

import "fmt"

func main() {
	// 1. 基于数组的初始化
	var arr [5]int = [5]int{1, 2, 3, 4, 5}
	var numsSlice1 []int = arr[0:len(arr)]
	fmt.Println(numsSlice1)

	// 2. 使用  []类型{}  的初始化
	var numsSlice2 []int = []int{1, 2, 3, 4, 5}
	fmt.Println(numsSlice2)
	var numsSlice3 []string = []string{"1", "2", "3", "4", "5"}
	fmt.Println(numsSlice3)

	// 3. 使用 make() 的初始化
	var numsSlice4 []int = make([]int, 0, 5)
	numsSlice4 = append(numsSlice4, 1, 2, 3, 4, 5)
	fmt.Println(numsSlice4)
}
