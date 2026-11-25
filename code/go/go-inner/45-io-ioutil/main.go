// 标准库 io/ioutil：围绕 io 的便捷函数（Go 1.16 起多数已废弃，迁到 io / os）。
// 对照基础接口包 io：见 08-io。
// 文档 https://studygolang.com/pkgdoc/pkg/io/ioutil/
package main

import (
	"bytes"
	"fmt"
	"io"
	"io/ioutil"
	"os"
	"strings"
)

func main() {
	r := strings.NewReader("hello ioutil")

	// ioutil.ReadAll —— 现用 io.ReadAll（08-io）
	oldData, _ := ioutil.ReadAll(r)
	r2 := strings.NewReader("hello io")
	newData, _ := io.ReadAll(r2)
	fmt.Println("ReadAll 相同结果:", string(oldData), string(newData))

	// ioutil.NopCloser —— 现用 io.NopCloser：给 bytes.Reader 套上 Close
	closable := ioutil.NopCloser(bytes.NewReader([]byte("ok")))
	defer closable.Close()
	b, _ := io.ReadAll(closable)
	fmt.Println("NopCloser:", string(b))

	// ioutil.TempFile / ioutil.WriteFile / ioutil.ReadFile —— 现用 os.CreateTemp、os.WriteFile、os.ReadFile（07-os）
	dir := must(os.MkdirTemp("", "ioutil-*"))
	defer os.RemoveAll(dir)

	legacyPath := dir + string(os.PathSeparator) + "legacy.txt"
	_ = ioutil.WriteFile(legacyPath, []byte("from ioutil"), 0644)
	modernPath := dir + string(os.PathSeparator) + "modern.txt"
	_ = os.WriteFile(modernPath, []byte("from os"), 0644)

	legacyRead, _ := ioutil.ReadFile(legacyPath)
	modernRead, _ := os.ReadFile(modernPath)
	fmt.Println("WriteFile/ReadFile:", string(legacyRead), string(modernRead))

	f, err := ioutil.TempFile(dir, "tmp-*.txt")
	if err != nil {
		panic(err)
	}
	fmt.Println("TempFile(旧):", f.Name())
	f.Close()
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
