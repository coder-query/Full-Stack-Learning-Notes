// 标准库 encoding/hex：十六进制编解码。文档 https://studygolang.com/pkgdoc/pkg/encoding/hex/
package main

import (
	"encoding/hex"
	"fmt"
)

func main() {
	src := []byte("go-inner")

	encoded := hex.EncodeToString(src)
	fmt.Println("EncodeToString:", encoded)

	decoded, err := hex.DecodeString(encoded)
	if err != nil {
		panic(err)
	}
	fmt.Println("Decode:", string(decoded))

	// Dump：人类可读的十六进制转储（带偏移）
	fmt.Print(hex.Dump([]byte("AB")))
}
