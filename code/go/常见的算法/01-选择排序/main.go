package main

import "fmt"

func main() {
	// 定义好切片（类似java中的arrayList）
	slice := []int{64, 34, 25, 12, 22, 11, 90}
	// 输出排序前的切片
	fmt.Println("排序前的切片:", slice)
	// 调用自定义的选择排序函数
	selectionSort(slice)
	// 输出排序后的切片
	fmt.Println("排序后的切片:", slice)
}

// 选择排序函数
func selectionSort(slice []int) {
	// 获取切片的长度
	n := len(slice)
	for i := 0; i < n-1; i++ {
		// 假设当前索引 i 的元素是最小的
		minIndex := i
		for j := i + 1; j < n; j++ {
			// 如果找到更小的元素，更新最小元素的索引
			if slice[j] < slice[minIndex] {
				minIndex = j
			}
		}
		swap(slice, i, minIndex)
	}
}

func swap(slice []int, i, j int) {
	var tmp = slice[i]
	// 交换切片中索引 i 和 j 的元素
	slice[i] = slice[j]
	slice[j] = tmp
}
