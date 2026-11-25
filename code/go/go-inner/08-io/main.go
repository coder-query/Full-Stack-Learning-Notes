// 标准库 io：流式读写抽象（Reader / Writer / Copy 等）。
// 历史便捷封装 io/ioutil（已废弃）见 45-io-ioutil。
// 文档 https://studygolang.com/pkgdoc/pkg/io/
package main

import (
	"bytes"
	"fmt"
	"io"
	"strings"
)

func main() {
	src := strings.NewReader("hello io package")

	// ReadAll：读完整个 Reader
	all, err := io.ReadAll(src)
	if err != nil {
		panic(err)
	}
	fmt.Println("ReadAll:", string(all))

	// Copy：从 src 拷贝到 dst
	var dst bytes.Buffer
	n, err := io.Copy(&dst, strings.NewReader("copy me"))
	if err != nil {
		panic(err)
	}
	fmt.Println("Copy 字节数:", n, "内容:", dst.String())

	// Pipe：连接 goroutine 之间的流（一端写、一端读）
	pr, pw := io.Pipe()
	go func() {
		defer pw.Close()
		_, _ = io.WriteString(pw, "from pipe")
	}()
	out, _ := io.ReadAll(pr)
	fmt.Println("Pipe:", string(out))

	// LimitReader：只读前 N 字节
	lim := io.LimitReader(strings.NewReader("123456789"), 4)
	chunk, _ := io.ReadAll(lim)
	fmt.Println("LimitReader:", string(chunk))
}
