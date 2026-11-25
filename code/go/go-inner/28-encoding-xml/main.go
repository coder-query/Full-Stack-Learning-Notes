// 标准库 encoding/xml：XML 编解码。文档 https://studygolang.com/pkgdoc/pkg/encoding/xml/
package main

import (
	"encoding/xml"
	"fmt"
)

type Note struct {
	XMLName xml.Name `xml:"note"`
	To      string   `xml:"to"`
	From    string   `xml:"from"`
	Body    string   `xml:"body"`
}

func main() {
	n := Note{To: "Go", From: "pkgdoc", Body: "studygolang"}
	data, err := xml.MarshalIndent(n, "", "  ")
	if err != nil {
		panic(err)
	}
	fmt.Println(string(data))

	var decoded Note
	if err := xml.Unmarshal(data, &decoded); err != nil {
		panic(err)
	}
	fmt.Printf("解码: %+v\n", decoded)
}
