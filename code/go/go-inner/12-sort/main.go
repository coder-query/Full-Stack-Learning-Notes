// 标准库 sort：排序与搜索。文档 https://studygolang.com/pkgdoc/pkg/sort/
package main

import (
	"fmt"
	"sort"
)

func main() {
	// sort.Strings / sort.Ints 原地排序
	words := []string{"fmt", "os", "io", "bufio"}
	sort.Strings(words)
	fmt.Println("Strings:", words)

	nums := []int{3, 1, 4, 1, 5}
	sort.Ints(nums)
	fmt.Println("Ints:", nums)

	// sort.Slice：任意切片 + 比较函数
	type Pkg struct {
		Name string
		Use  int
	}
	pkgs := []Pkg{{"regexp", 2}, {"strings", 10}, {"fmt", 8}}
	sort.Slice(pkgs, func(i, j int) bool {
		return pkgs[i].Use > pkgs[j].Use
	})
	fmt.Printf("Slice: %+v\n", pkgs)

	// sort.Search：二分查找插入位置（nums 已升序）
	idx := sort.SearchInts(nums, 4)
	fmt.Println("SearchInts 4 at:", idx)
}
