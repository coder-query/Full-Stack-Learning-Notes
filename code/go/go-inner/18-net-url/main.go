// 标准库 net/url：URL 解析与构造。文档 https://studygolang.com/pkgdoc/pkg/net/url/
package main

import (
	"fmt"
	"net/url"
)

func main() {
	// Parse：绝对 URL
	u, err := url.Parse("https://studygolang.com/pkgdoc/pkg/fmt/?tab=doc#Println")
	if err != nil {
		panic(err)
	}
	fmt.Println("Scheme:", u.Scheme)
	fmt.Println("Host:", u.Host)
	fmt.Println("Path:", u.Path)
	fmt.Println("RawQuery:", u.RawQuery)
	fmt.Println("Fragment:", u.Fragment)

	// Query：解析查询参数（map[string][]string）
	q := u.Query()
	fmt.Println("tab=", q.Get("tab"))

	// Values + Encode：构造查询串
	params := url.Values{}
	params.Set("q", "标准库")
	params.Add("tag", "fmt")
	fmt.Println("Encode:", params.Encode())

	// ResolveReference：相对路径拼到 base 上
	base, _ := url.Parse("https://studygolang.com/pkgdoc/")
	rel, _ := url.Parse("pkg/strings/")
	fmt.Println("Resolve:", base.ResolveReference(rel).String())
}
