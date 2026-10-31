package main

import (
	"fmt"
	"import-go-mod/utils"
)

func main() {
	fmt.Println("hello world")
	utils.Add(10, 5) // 注意：add 是包名，Add 是函数名，都要写
	utils.Sub(10, 5)
}
