// 标准库 encoding/json：JSON 编解码。文档 https://studygolang.com/pkgdoc/pkg/encoding/json/
package main

import (
	"encoding/json"
	"fmt"
)

type User struct {
	ID   int    `json:"id"`
	Name string `json:"name"`
	// json:"-" 表示该字段不序列化
	Secret string `json:"-"`
}

func main() {
	u := User{ID: 1, Name: "Go", Secret: "hidden"}

	// Marshal：结构体 → []byte
	data, err := json.Marshal(u)
	if err != nil {
		panic(err)
	}
	fmt.Println("JSON:", string(data))

	// MarshalIndent：带缩进，便于阅读
	pretty, _ := json.MarshalIndent(u, "", "  ")
	fmt.Println(string(pretty))

	// Unmarshal：[]byte → 结构体
	var decoded User
	if err := json.Unmarshal(data, &decoded); err != nil {
		panic(err)
	}
	fmt.Printf("解码: %+v\n", decoded)

	// 未知字段可解码到 map[string]any
	raw := `{"pkg":"fmt","ver":1}`
	var m map[string]any
	_ = json.Unmarshal([]byte(raw), &m)
	fmt.Println("map:", m)
}
