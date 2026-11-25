// 标准库 slices：切片通用工具（Go 1.21+）。文档 https://studygolang.com/pkgdoc/pkg/slices/
package main

import (
	"fmt"
	"slices"
)

func main() {
	nums := []int{3, 1, 4, 1, 5}
	slices.Sort(nums)
	fmt.Println("Sort:", nums)

	fmt.Println("Contains 4:", slices.Contains(nums, 4))
	fmt.Println("Index 5:", slices.Index(nums, 5))

	cloned := slices.Clone(nums)
	cloned[0] = 0
	fmt.Println("Clone 后改副本不影响原切片:", nums[0], cloned[0])

	// Compact：去掉连续重复
	dup := []string{"a", "a", "b", "b", "b", "c"}
	dup = slices.Compact(dup)
	fmt.Println("Compact:", dup)
}
