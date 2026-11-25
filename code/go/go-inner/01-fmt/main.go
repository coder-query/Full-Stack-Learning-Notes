// 标准库 fmt：格式化 I/O。文档 https://studygolang.com/pkgdoc/pkg/fmt/
package main

import (
	"fmt"
	"os"
)

func main() {
	// Println：自动空格、换行
	fmt.Println("Hello", "Go")

	// Printf：占位符 %s %d %v %q %t
	name, age := "小明", 20
	fmt.Printf("姓名=%s 年龄=%d\n", name, age)

	// Sprintf：格式化成字符串，不打印
	msg := fmt.Sprintf("用户 %q 余额 %.2f", name, 123.456)
	fmt.Println(msg)

	// Fprintf：写到任意 io.Writer，例如标准错误
	fmt.Fprintf(os.Stderr, "日志: %v\n", []int{1, 2, 3})

	// Errorf：生成带格式说明的 error（常与 %w 包装错误配合）
	err := fmt.Errorf("无效输入: id=%d", -1)
	fmt.Println(err)
}
