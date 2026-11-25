// 标准库 maps：map 通用工具（Go 1.21+）。文档 https://studygolang.com/pkgdoc/pkg/maps/
package main

import (
	"fmt"
	"maps"
)

func main() {
	m1 := map[string]int{"fmt": 1, "io": 2}
	m2 := maps.Clone(m1)
	m2["io"] = 99
	fmt.Println("Clone 后原 map 不变 io=", m1["io"])

	m3 := map[string]int{"a": 1}
	maps.Copy(m3, map[string]int{"b": 2, "c": 3})
	fmt.Println("Copy 合并:", m3)

	keys := make([]string, 0, len(m1))
	for k := range maps.Keys(m1) {
		keys = append(keys, k)
	}
	fmt.Println("Keys:", keys)
}
