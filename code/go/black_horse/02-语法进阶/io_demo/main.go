package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {

	//file, err := os.Open("README.txt")
	//if err != nil {
	//	fmt.Println("error:", err)
	//	return
	//}
	//defer func(file *os.File) {
	//	err := file.Close()
	//	if err != nil {
	//		fmt.Println("关闭文件失败error:", err)
	//		return
	//	}
	//}(file)

	// 一次性读取文件到内存，适用于文件小的情况
	//content, err := ioutil.ReadFile("README.txt")
	//if err != nil {
	//	fmt.Println("error:", err)
	//	return
	//}
	//fmt.Println(string(content))

	// 缓冲区读取文件，适用于文件大的情况

	//reader := bufio.NewReader(file)
	//for {
	//	line, err := reader.ReadString('\n')
	//	if err == io.EOF {
	//		fmt.Println("error----> :", err)
	//		break
	//	}
	//	fmt.Println(line)
	//}
	//fmt.Println("文件读取完成")

	file, err := os.OpenFile("zsh.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0744)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	writer := bufio.NewWriter(file)
	writer.WriteString("hello world golang")
	writer.WriteString("\n")
	if err := writer.Flush(); err != nil {
		fmt.Println("writer.Flush() error:", err)
		return
	}

	// fileMode ： 只对linux 有效，windows 无效
	mode := os.FileMode(0744).String()
	fmt.Println(mode)
}
