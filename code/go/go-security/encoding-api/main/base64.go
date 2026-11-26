package main

import (
	"encoding/base64"
	"fmt"
	"log"
)

func main() {
	// 编码
	data := []byte("Hello, Go!")
	encoded := base64.StdEncoding.EncodeToString(data)
	fmt.Println(encoded)

	// 解码
	decoded, err := base64.StdEncoding.DecodeString(encoded)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(decoded))
	// URL 安全编码
	encoded = base64.URLEncoding.EncodeToString(data)
	fmt.Println(encoded)

	// URL 安全解码
	decoded, _ = base64.URLEncoding.DecodeString(encoded)
	fmt.Println(string(decoded))
}
