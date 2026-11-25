// 标准库 html：HTML 转义（非模板）。文档 https://studygolang.com/pkgdoc/pkg/html/
// 生成 HTML 片段用 EscapeString；整页模板用 html/template（21-html-template）。
package main

import (
	"fmt"
	"html"
)

func main() {
	raw := `<script>alert(1)</script> & "quotes"`
	fmt.Println("EscapeString:", html.EscapeString(raw))
	fmt.Println("UnescapeString:", html.UnescapeString(html.EscapeString(raw)))
}
