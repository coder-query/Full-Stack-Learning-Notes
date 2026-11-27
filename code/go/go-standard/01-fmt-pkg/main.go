package main

import "fmt"

// %v    // 默认格式
// %+v   // 打印结构体时包含字段名
// %#v   // Go 语法表示
// %T    // 类型
// %%    // 字面量 %

// %t    // true 或 false

// %b    // 二进制
// %o    // 八进制
// %d    // 十进制
// %x    // 十六进制（小写）
// %X    // 十六进制（大写）
// %c    // Unicode 字符
// %U    // Unicode 格式

// %s    // 字符串
// %q    // 带引号的字符串
// %x    // 十六进制（小写）
// %X    // 十六进制（大写）

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

	name := "张三"
	age := 30
	height := 175.5

	// 基本格式化
	fmt.Printf("姓名: %s\n", name)
	fmt.Printf("年龄: %d\n", age)
	fmt.Printf("身高: %.1f cm\n", height)

	// 使用 %v（默认格式）
	fmt.Printf("信息: %v\n", name)
	fmt.Printf("信息: %+v\n", name)
	fmt.Printf("信息: %#v\n", name)
	fmt.Printf("类型: %T\n", name)
}
