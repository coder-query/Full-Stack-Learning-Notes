package main

import "fmt"

func main() {
	// Print: 直接输出，不换行
	fmt.Print("Hello")
	fmt.Print("World")
	// 输出: HelloWorld

	// Println: 输出后换行
	fmt.Println("Hello")
	fmt.Println("World")
	// 输出:
	// Hello
	// World

	// Printf: 格式化输出
	fmt.Printf("姓名: %s, 年龄: %d\n", "张三", 30)
	// 输出: 姓名: 张三, 年龄: 30
}
