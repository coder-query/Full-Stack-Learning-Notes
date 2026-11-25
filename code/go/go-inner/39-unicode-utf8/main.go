// 标准库 unicode/utf8：UTF-8 编解码与遍历。文档 https://studygolang.com/pkgdoc/pkg/unicode/utf8/
package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {
	s := "Go语言"

	fmt.Println("RuneCount:", utf8.RuneCountInString(s))
	fmt.Println("Valid:", utf8.ValidString(s))

	for i, r := range s {
		fmt.Printf("  字节偏移 %d -> U+%04X %c\n", i, r, r)
	}

	// DecodeRuneInString
	r, size := utf8.DecodeRuneInString(s)
	fmt.Println("首字符:", string(r), "占用字节:", size)
}
