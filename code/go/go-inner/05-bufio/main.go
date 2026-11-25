// 标准库 bufio：带缓冲的读写。文档 https://studygolang.com/pkgdoc/pkg/bufio/
package main

import (
	"bufio"
	"fmt"
	"strings"
)

func main() {
	input := "line1\nline2\nline3\n"

	// Scanner：按行/按词扫描，适合日志、文本协议
	sc := bufio.NewScanner(strings.NewReader(input))
	for sc.Scan() {
		fmt.Println("行:", sc.Text())
	}
	if err := sc.Err(); err != nil {
		panic(err)
	}

	// Reader：Peek、ReadString 等（大文件读常用）
	r := bufio.NewReader(strings.NewReader("hello\n"))
	line, err := r.ReadString('\n')
	if err != nil {
		panic(err)
	}
	fmt.Print("ReadString:", line)

	// Writer：写满或 Flush 才真正写出
	var out strings.Builder
	w := bufio.NewWriter(&out)
	_, _ = w.WriteString("buffered ")
	_ = w.Flush()
	fmt.Println(out.String())
}
