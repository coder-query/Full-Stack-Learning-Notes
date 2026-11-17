package main

import (
	"fmt"
	"os"
)

func main() {
	file, err := os.Open("README.txt")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			fmt.Println("关闭文件失败error:", err)
			return
		}
	}(file)
	fmt.Println(file)
	fmt.Println(file.Name())
	fmt.Println(file.Stat())
}
