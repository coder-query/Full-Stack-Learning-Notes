package main

import (
	"fmt"
	"io"
	"io/fs"
	"log"
	"os"
)

const (
	DefaultFileName    string = "ioutil.txt"
	DefaultFileContent string = "我是程序员Query"
)

func testCreateFile() *os.File {
	f, err := os.OpenFile(DefaultFileName, os.O_RDWR|os.O_APPEND|os.O_CREATE, os.ModePerm)
	if err != nil {
		log.Println("os打开文件失败，filename：", f.Name())
		return nil
	}
	log.Println("os打开并创建文件成功！filename: ", f.Name())
	return f
}

func testWriteContentToFile() {
	err := os.WriteFile(DefaultFileName, []byte(DefaultFileContent), fs.ModePerm)
	if err != nil {
		fmt.Println("os.WriteFile err: ", err)
	}
	fmt.Println("写入成功！！！")
}

func testReadFileContent() {
	f, err := os.Open(DefaultFileName)
	if err != nil {
		fmt.Println("testReadFileContent err: ", err)
	}
	byteData, err := io.ReadAll(f)
	if err != nil {
		panic(err)
	}
	fmt.Printf("文件内容为：%v", string(byteData))
}

func main() {
	// 创建文件
	testCreateFile()
	// 往文件中写入内容
	testWriteContentToFile()
	// 从文件中读取内容
	testReadFileContent()
}
