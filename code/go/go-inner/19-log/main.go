// 标准库 log：简易日志。文档 https://studygolang.com/pkgdoc/pkg/log/
package main

import (
	"log"
	"os"
)

func main() {
	// 默认 Logger 写到 stderr，带日期时间、文件名行号（需 Lshortfile 或 Llongfile）
	log.SetFlags(log.Ldate | log.Ltime | log.Lshortfile)
	log.Println("标准 log 输出")

	// 前缀
	log.SetPrefix("[go-inner] ")
	log.Printf("格式化 %s", "demo")

	// 自定义 Logger
	logger := log.New(os.Stdout, "INFO ", log.LstdFlags)
	logger.Println("独立 Logger")

	// Fatal / Panic 会打印后 exit(1) 或 panic（示例里不用，避免中断学习）
	// log.Fatal("不可恢复")
}
