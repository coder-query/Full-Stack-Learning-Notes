// 标准库 html/template：HTML 安全模板（自动转义）。文档 https://studygolang.com/pkgdoc/pkg/html/template/
package main

import (
	"bytes"
	"fmt"
	"html/template"
)

func main() {
	// text/template 不会转义 HTML；html/template 会防止 XSS
	tpl := template.Must(template.New("page").Parse(`
<title>{{.Title}}</title>
<p>欢迎，{{.User}}</p>
<p>注释：{{.Comment}}</p>
`))

	data := struct {
		Title   string
		User    string
		Comment string
	}{
		Title:   "pkgdoc",
		User:    "Gopher",
		Comment: `<script>alert("x")</script>`,
	}

	var buf bytes.Buffer
	if err := tpl.Execute(&buf, data); err != nil {
		panic(err)
	}
	fmt.Println(buf.String())
}
