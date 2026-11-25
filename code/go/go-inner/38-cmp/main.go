// 标准库 cmp：可排序类型的比较（Go 1.21+）。文档 https://studygolang.com/pkgdoc/pkg/cmp/
package main

import (
	"cmp"
	"fmt"
)

func main() {
	fmt.Println("Compare 3,5:", cmp.Compare(3, 5))
	fmt.Println("Compare 5,5:", cmp.Compare(5, 5))

	// Less：用于 sort.SliceFunc 等
	fmt.Println("Less:", cmp.Less(1, 2))

	// Compare 三值：a<b 返回 -1，a>b 返回 1，相等返回 0（sort.Slice 等场景常用）
	a, b := 10, 20
	switch cmp.Compare(a, b) {
	case -1:
		fmt.Println(a, "小于", b)
	case 1:
		fmt.Println(a, "大于", b)
	default:
		fmt.Println(a, "等于", b)
	}
}
