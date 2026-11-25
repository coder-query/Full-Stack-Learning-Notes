// 标准库 encoding/csv：读写 CSV。文档 https://studygolang.com/pkgdoc/pkg/encoding/csv/
package main

import (
	"encoding/csv"
	"fmt"
	"os"
	"strings"
)

func main() {
	// 从字符串读
	in := strings.NewReader("pkg,desc\nfmt,格式化\nstrings,字符串\n")
	r := csv.NewReader(in)
	records, err := r.ReadAll()
	if err != nil {
		panic(err)
	}
	for _, row := range records {
		fmt.Println(row)
	}

	// 写到 Buffer
	var out strings.Builder
	w := csv.NewWriter(&out)
	_ = w.Write([]string{"id", "name"})
	_ = w.Write([]string{"1", "Go"})
	w.Flush()
	fmt.Println("写出:", out.String())

	// 也可写文件（演示用临时文件）
	f := must(os.CreateTemp("", "csv-*.csv"))
	defer os.Remove(f.Name())
	_ = csv.NewWriter(f).Write([]string{"a", "b"})
	fmt.Println("临时文件:", f.Name())
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
