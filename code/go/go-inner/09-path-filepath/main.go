// 标准库 path/filepath：跨平台路径处理。文档 https://studygolang.com/pkgdoc/pkg/path/filepath/
package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func main() {
	// Join：按当前 OS 规则拼接，自动处理分隔符
	p := filepath.Join("go-inner", "07-os", "main.go")
	fmt.Println("Join:", p)

	// Dir / Base / Ext
	fmt.Println("Dir:", filepath.Dir(p))
	fmt.Println("Base:", filepath.Base(p))
	fmt.Println("Ext:", filepath.Ext(p))

	// Abs：转为绝对路径
	abs, err := filepath.Abs(".")
	if err != nil {
		panic(err)
	}
	fmt.Println("Abs(.):", abs)

	// WalkDir：遍历目录（本示例只遍历系统临时目录下刚建的子树）
	root := must(os.MkdirTemp("", "walk-*"))
	defer os.RemoveAll(root)
	_ = os.WriteFile(filepath.Join(root, "a.txt"), []byte("a"), 0644)
	sub := filepath.Join(root, "sub")
	_ = os.Mkdir(sub, 0755)
	_ = os.WriteFile(filepath.Join(sub, "b.txt"), []byte("b"), 0644)

	fmt.Println("WalkDir", root+":")
	_ = filepath.WalkDir(root, func(path string, d os.DirEntry, err error) error {
		if err != nil {
			return err
		}
		rel, _ := filepath.Rel(root, path)
		fmt.Println(" ", rel, d.IsDir())
		return nil
	})
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
