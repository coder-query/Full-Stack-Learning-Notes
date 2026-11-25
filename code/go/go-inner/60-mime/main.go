// 标准库 mime：MIME 类型识别。文档 https://studygolang.com/pkgdoc/pkg/mime/
// 上传解析用 mime/multipart（49-mime-multipart）。
package main

import (
	"fmt"
	"mime"
)

func main() {
	fmt.Println(mime.TypeByExtension(".json"))
	fmt.Println(mime.TypeByExtension(".go"))

	// 解析 Content-Type 里的 charset 等
	mediatype, params, err := mime.ParseMediaType(`application/json; charset=utf-8`)
	if err != nil {
		panic(err)
	}
	fmt.Println("ParseMediaType:", mediatype, params)
}
