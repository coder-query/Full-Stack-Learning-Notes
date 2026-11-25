// 标准库 text/template：文本模板（不做 HTML 转义）。文档 https://studygolang.com/pkgdoc/pkg/text/template/
// 对比 html/template 见 21-html-template。
package main

import (
	"bytes"
	"fmt"
	"text/template"
)

func main() {
	tpl := template.Must(template.New("cfg").Parse(`
pkg={{.Pkg}}
list={{range .Items}}[{{.}}]{{end}}
`))

	data := struct {
		Pkg   string
		Items []string
	}{Pkg: "std", Items: []string{"fmt", "strings", "slices"}}

	var buf bytes.Buffer
	if err := tpl.Execute(&buf, data); err != nil {
		panic(err)
	}
	fmt.Println(buf.String())
}
