// 标准库 compress/gzip：gzip 压缩。文档 https://studygolang.com/pkgdoc/pkg/compress/gzip/
package main

import (
	"bytes"
	"compress/gzip"
	"fmt"
	"io"
)

func main() {
	plain := append([]byte("studygolang pkgdoc "), bytes.Repeat([]byte("x"), 50)...)

	var compressed bytes.Buffer
	w := gzip.NewWriter(&compressed)
	if _, err := w.Write(plain); err != nil {
		panic(err)
	}
	if err := w.Close(); err != nil {
		panic(err)
	}
	fmt.Println("原始", len(plain), "字节 -> gzip", compressed.Len(), "字节")

	r, err := gzip.NewReader(&compressed)
	if err != nil {
		panic(err)
	}
	defer r.Close()
	out, err := io.ReadAll(r)
	if err != nil {
		panic(err)
	}
	fmt.Println("解压后与原文相等:", bytes.Equal(plain, out))
}
