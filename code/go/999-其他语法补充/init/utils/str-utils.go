package utils

import "fmt"

func init() {
	fmt.Println("字符串工具类截取 init函数执行")
}
func SliceStr(str string) string {
	return str[0:1]
}
