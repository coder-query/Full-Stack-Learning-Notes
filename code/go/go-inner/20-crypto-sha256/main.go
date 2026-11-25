// 标准库 crypto/sha256：SHA-256 摘要。文档 https://studygolang.com/pkgdoc/pkg/crypto/sha256/
package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
)

func main() {
	data := []byte("studygolang pkgdoc")

	// Sum256：一次性计算 32 字节摘要
	sum := sha256.Sum256(data)
	fmt.Println("SHA256 (hex):", hex.EncodeToString(sum[:]))

	// 流式写入（大文件）
	h := sha256.New()
	_, _ = h.Write([]byte("part1"))
	_, _ = h.Write([]byte("part2"))
	fmt.Println("流式 SHA256:", hex.EncodeToString(h.Sum(nil)))
}
