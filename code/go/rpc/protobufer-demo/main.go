package main

import (
	"encoding/json"
	"fmt"
	local_proto "proto-demo/proto"

	proto "google.golang.org/protobuf/proto"
)

func main() {
	helloRequestObj := local_proto.HelloRequest{
		Name:    "zsh",
		Age:     21,
		Hobbies: []string{"编程", "王者", "ai"},
	}
	marshal, err := proto.Marshal(&helloRequestObj)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println(marshal)
	fmt.Println(string(marshal))
	fmt.Println(len(string(marshal)))
	bytes, err := json.Marshal(&helloRequestObj)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println(bytes)
	fmt.Println(string(bytes))
	fmt.Println(len(string(bytes)))
}
