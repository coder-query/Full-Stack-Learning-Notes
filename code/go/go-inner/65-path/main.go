// 标准库 path：斜杠分隔路径（URL 路径），不用于 Windows 盘符路径。文档 https://studygolang.com/pkgdoc/pkg/path/
// 本地文件路径用 path/filepath（09-path-filepath）。
package main

import (
	"fmt"
	"path"
)

func main() {
	fmt.Println(path.Join("pkgdoc", "pkg", "fmt"))
	fmt.Println(path.Dir("/pkgdoc/pkg/fmt"))
	fmt.Println(path.Base("/pkgdoc/pkg/fmt"))
	fmt.Println(path.Ext("main.go"))

	clean := path.Clean("/pkgdoc//pkg/../pkg/fmt/")
	fmt.Println("Clean:", clean)

	ok, _ := path.Match("pkg/*", "pkg/fmt")
	fmt.Println("Match:", ok)
}
