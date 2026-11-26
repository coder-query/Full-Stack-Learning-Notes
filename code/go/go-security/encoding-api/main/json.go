package main

import (
	"encoding/json"
	"fmt"
	"log"
)

type Person1 struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func main() {
	// 编码（序列化）
	p := Person1{Name: "张三", Age: 30}
	jsonData, err := json.Marshal(p)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(jsonData))
	// 输出: {"name":"张三","age":30}

	// 解码（反序列化）
	var p2 Person1
	err = json.Unmarshal(jsonData, &p2)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%+v\n", p2)
}
