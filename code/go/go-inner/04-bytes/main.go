// 标准库 bytes：[]byte 切片操作（与 strings  API 类似）。文档 https://studygolang.com/pkgdoc/pkg/bytes/
package main

import (
	"bytes"
	"fmt"
)

func main() {
	a := []byte("hello")
	b := []byte("hello world")

	fmt.Println(bytes.Equal(a, []byte("hello")))
	fmt.Println(bytes.Contains(b, []byte("world")))
	fmt.Println(bytes.Index(b, []byte("lo")))

	// Buffer：可变字节缓冲，常配合 io
	var buf bytes.Buffer
	buf.WriteString("pkgdoc\n")
	buf.WriteByte('!')
	fmt.Print(buf.String())

	// Join：用分隔符连接多个 []byte
	line := bytes.Join([][]byte{[]byte("go"), []byte("inner")}, []byte("-"))
	fmt.Println(string(line))
}
