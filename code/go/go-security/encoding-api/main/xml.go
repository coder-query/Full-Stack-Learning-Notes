package main

import (
	"encoding/xml"
	"fmt"
	"log"
)

type Person2 struct {
	XMLName xml.Name `xml:"person"`
	Name    string   `xml:"name"`
	Age     int      `xml:"age"`
}

func main() {
	// 编码
	p := Person2{Name: "张三", Age: 30}
	xmlData, err := xml.Marshal(p)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(xmlData))

	// 解码
	var p2 Person2
	err = xml.Unmarshal(xmlData, &p2)
	if err != nil {
		return
	}
	fmt.Printf("%+v\n", p2)
}
