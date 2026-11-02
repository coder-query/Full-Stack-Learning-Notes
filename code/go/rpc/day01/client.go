package main

import (
	"fmt"
	"net/rpc/jsonrpc"
)

func main() {

	connection, err := jsonrpc.Dial("tcp", "192.168.110.252:8800")
	if err != nil {
		fmt.Println("连接失败:", err)
		return
	}
	fmt.Println("连接成功")
	// 2. 调用远程的对象方法
	var rpcResponse string
	err = connection.Call("Hello.Hello", "张三", &rpcResponse)
	if err != nil {
		fmt.Println("调用失败:", err)
		return
	}
	fmt.Println("调用成功:", rpcResponse)
}
