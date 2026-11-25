// 标准库 embed：编译期把文件打进二进制。文档 https://studygolang.com/pkgdoc/pkg/embed/
package main

import (
	_ "embed"
	"fmt"
)

//go:embed version.txt
var version string

//go:embed version.txt
var versionBytes []byte

func main() {
	fmt.Println("嵌入字符串:")
	fmt.Println(version)

	fmt.Println("嵌入 []byte 长度:", len(versionBytes))
}
