// 标准库 io/fs：抽象文件系统（与 os 实文件配合或用于测试替身）。文档 https://studygolang.com/pkgdoc/pkg/io/fs/
package main

import (
	"fmt"
	"io/fs"
	"os"
)

func main() {
	// WalkDir：遍历目录（回调返回 fs.SkipDir 可跳过子树）
	root := must(os.MkdirTemp("", "fs-walk-*"))
	defer os.RemoveAll(root)
	_ = os.WriteFile(root+"/a.txt", []byte("a"), 0644)

	fmt.Println("WalkDir:")
	err := fs.WalkDir(os.DirFS(root), ".", func(path string, d fs.DirEntry, err error) error {
		if err != nil {
			return err
		}
		info, _ := d.Info()
		fmt.Printf("  %s size=%d dir=%v\n", path, info.Size(), d.IsDir())
		return nil
	})
	if err != nil {
		panic(err)
	}

	// Glob、ReadFile 可直接在 fs.FS 上调用
	matches, _ := fs.Glob(os.DirFS(root), "*.txt")
	fmt.Println("Glob:", matches)
	data, _ := fs.ReadFile(os.DirFS(root), "a.txt")
	fmt.Println("ReadFile:", string(data))
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
