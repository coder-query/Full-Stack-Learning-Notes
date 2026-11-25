// 标准库 archive/tar：tar 归档。文档 https://studygolang.com/pkgdoc/pkg/archive/tar/
package main

import (
	"archive/tar"
	"bytes"
	"fmt"
	"io"
)

func main() {
	var buf bytes.Buffer
	tw := tar.NewWriter(&buf)
	_ = tw.WriteHeader(&tar.Header{Name: "hello.txt", Size: 5, Mode: 0644})
	_, _ = tw.Write([]byte("hello"))
	_ = tw.Close()

	tr := tar.NewReader(&buf)
	hdr, err := tr.Next()
	if err != nil {
		panic(err)
	}
	fmt.Println("成员:", hdr.Name, "size", hdr.Size)
	data, _ := io.ReadAll(tr)
	fmt.Println("内容:", string(data))
}
