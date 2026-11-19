package main

import (
	"fmt"
	"net/rpc/jsonrpc"
)

func main() {
	jsonRpcClient, err := jsonrpc.Dial("tcp", "127.0.0.1:4576")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("jsonRpcClient:", jsonRpcClient)
	//var response string
	//err = client.Call("helloService.CallHelloFun", "hello", &response)
	var response *string = new(string)
	err = jsonRpcClient.Call("helloService.CallHelloFun", "hello", response)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("*response:", *response)
}
