package main

import (
	"fmt"
	"net/rpc"
)

func main() {
	client, err := rpc.Dial("tcp", "127.0.0.1:4576")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("client:", client)
	var response *string = new(string)
	err = client.Call("helloService.CallHelloFun", "hello", response)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("response:", response)
}
