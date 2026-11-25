// 标准库 strings：字符串处理。文档 https://studygolang.com/pkgdoc/pkg/strings/
package main

import (
	"fmt"
	"strings"
)

func main() {
	s := "  go,inner,study  "

	fmt.Println(strings.TrimSpace(s))
	fmt.Println(strings.Split(strings.TrimSpace(s), ","))
	fmt.Println(strings.Contains("studygolang", "golang"))
	fmt.Println(strings.HasPrefix("go1.26", "go"))
	fmt.Println(strings.ToUpper("abc"))

	// Builder：高效拼接，避免反复 + 产生新字符串
	var b strings.Builder
	b.WriteString("pkgdoc: ")
	b.WriteString("https://studygolang.com/pkgdoc")
	fmt.Println(b.String())

	fmt.Println(strings.ReplaceAll("a-b-c", "-", "_"))
}
