// 标准库 os：操作系统接口（文件、环境变量、进程等）。文档 https://studygolang.com/pkgdoc/pkg/os/
package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println("PID:", os.Getpid())
	fmt.Println("工作目录:", must(os.Getwd()))

	// 环境变量
	fmt.Println("PATH 长度:", len(os.Getenv("PATH")))
	_ = os.Setenv("GO_INNER_DEMO", "1")
	fmt.Println("GO_INNER_DEMO=", os.Getenv("GO_INNER_DEMO"))

	// 读写临时文件
	dir := must(os.MkdirTemp("", "go-inner-*"))
	defer os.RemoveAll(dir)

	path := dir + string(os.PathSeparator) + "note.txt"
	content := []byte("studygolang pkgdoc\n")
	if err := os.WriteFile(path, content, 0644); err != nil {
		panic(err)
	}
	read, err := os.ReadFile(path)
	if err != nil {
		panic(err)
	}
	fmt.Print("文件内容:\n", string(read))

	// 文件信息
	info, err := os.Stat(path)
	if err != nil {
		panic(err)
	}
	fmt.Println("大小:", info.Size(), "字节")
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
