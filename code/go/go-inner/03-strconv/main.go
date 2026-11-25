// 标准库 strconv：字符串与基本类型的相互转换。文档 https://studygolang.com/pkgdoc/pkg/strconv/
package main

import (
	"fmt"
	"strconv"
)

func main() {
	// 字符串 → 整数（必须处理 error）
	n, err := strconv.Atoi("42")
	if err != nil {
		panic(err)
	}
	fmt.Println("Atoi:", n)

	// 整数 → 字符串
	fmt.Println("Itoa:", strconv.Itoa(256))

	// ParseBool / FormatBool
	ok, _ := strconv.ParseBool("true")
	fmt.Println("bool:", ok, strconv.FormatBool(ok))

	// ParseFloat、FormatFloat（'f' 定点，-1 表示最短表示）
	f, _ := strconv.ParseFloat("3.1415", 64)
	fmt.Println("float:", strconv.FormatFloat(f, 'f', 2, 64))

	// 按进制转换
	fmt.Println("ParseInt:", must(strconv.ParseInt("ff", 16, 32)))
	fmt.Println("FormatInt:", strconv.FormatInt(255, 16))
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
