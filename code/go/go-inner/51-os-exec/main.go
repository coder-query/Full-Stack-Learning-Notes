// 标准库 os/exec：执行外部命令。文档 https://studygolang.com/pkgdoc/pkg/os/exec/
package main

import (
	"bytes"
	"fmt"
	"os"
	"os/exec"
)

func main() {
	// 简单：CombinedOutput 收集 stdout+stderr
	out, err := exec.Command("go", "version").CombinedOutput()
	if err != nil {
		fmt.Println("go version 失败:", err)
	} else {
		fmt.Print(string(out))
	}

	// 分设 Stdout/Stderr、环境变量、工作目录
	var stdout bytes.Buffer
	cmd := exec.Command("go", "env", "GOROOT")
	cmd.Stdout = &stdout
	cmd.Env = append(os.Environ(), "GO_INNER=1")
	if err := cmd.Run(); err != nil {
		panic(err)
	}
	fmt.Println("GOROOT:", stdout.String())
}
