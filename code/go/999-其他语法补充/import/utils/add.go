package utils

import "fmt"

// Add 首字母大写才能被其他包调用（导出）
func Add(a int, b int) int {
	fmt.Printf("add a:%d, b:%d res = %d", a, b, a+b)
	return a + b
}
