// 标准库 encoding/base64：Base64 编解码。文档 https://studygolang.com/pkgdoc/pkg/encoding/base64/
package main

import (
	"encoding/base64"
	"fmt"
)

func main() {
	raw := []byte("Go 标准库")

	// StdEncoding：常用、带 padding '='
	encoded := base64.StdEncoding.EncodeToString(raw)
	fmt.Println("Encode:", encoded)

	decoded, err := base64.StdEncoding.DecodeString(encoded)
	if err != nil {
		panic(err)
	}
	fmt.Println("Decode:", string(decoded))

	// URLEncoding：用于 URL/query，避免 + / 等字符
	urlEnc := base64.URLEncoding.EncodeToString([]byte("a+b/c"))
	fmt.Println("URLEncoding:", urlEnc)
}
