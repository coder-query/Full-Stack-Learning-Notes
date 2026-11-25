// 标准库 hash/fnv：非加密哈希（分桶、去重）。文档 https://studygolang.com/pkgdoc/pkg/hash/fnv/
package main

import (
	"fmt"
	"hash/fnv"
)

func main() {
	h := fnv.New64a()
	_, _ = h.Write([]byte("https://studygolang.com/pkgdoc"))
	fmt.Printf("FNV-1a 64: %#x\n", h.Sum64())

	// Colly 等库也常用 FNV 给 URL 做 visited 键
	for _, s := range []string{"a", "b", "a"} {
		h.Reset()
		_, _ = h.Write([]byte(s))
		fmt.Println(s, "->", h.Sum64())
	}
}
