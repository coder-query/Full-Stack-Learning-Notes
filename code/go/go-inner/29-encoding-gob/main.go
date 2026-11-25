// 标准库 encoding/gob：Go 专有二进制序列化（仅 Go 互通）。文档 https://studygolang.com/pkgdoc/pkg/encoding/gob/
package main

import (
	"bytes"
	"encoding/gob"
	"fmt"
)

type Payload struct {
	Name string
	IDs  []int
}

func main() {
	var buf bytes.Buffer
	enc := gob.NewEncoder(&buf)
	if err := enc.Encode(Payload{Name: "demo", IDs: []int{1, 2}}); err != nil {
		panic(err)
	}
	fmt.Println("gob 字节数:", buf.Len())

	var out Payload
	if err := gob.NewDecoder(&buf).Decode(&out); err != nil {
		panic(err)
	}
	fmt.Printf("解码: %+v\n", out)
}
