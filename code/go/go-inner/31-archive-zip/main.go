// 标准库 archive/zip：ZIP 归档。文档 https://studygolang.com/pkgdoc/pkg/archive/zip/
package main

import (
	"archive/zip"
	"bytes"
	"fmt"
	"io"
)

func main() {
	var buf bytes.Buffer
	zw := zip.NewWriter(&buf)

	fw, err := zw.Create("readme.txt")
	if err != nil {
		panic(err)
	}
	_, _ = fw.Write([]byte("go-inner zip demo\n"))
	if err := zw.Close(); err != nil {
		panic(err)
	}

	zr, err := zip.NewReader(bytes.NewReader(buf.Bytes()), int64(buf.Len()))
	if err != nil {
		panic(err)
	}
	for _, f := range zr.File {
		fmt.Println("归档内文件:", f.Name, "大小:", f.UncompressedSize64)
		rc, err := f.Open()
		if err != nil {
			panic(err)
		}
		body, _ := io.ReadAll(rc)
		rc.Close()
		fmt.Print(string(body))
	}
}
