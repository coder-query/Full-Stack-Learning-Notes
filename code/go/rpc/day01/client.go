package main

import (
	"fmt"
	"net/rpc"
)

func main() {

	connection, err := rpc.Dial("tcp", "127.0.0.1:8800")
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
