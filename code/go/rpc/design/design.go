package design

import (
	"fmt"
	"net/rpc"
)

type HelloWorldIf interface {
	Hello(name string, response *string) error
}

func RegisterRpcService(i HelloWorldIf) {
	err := rpc.RegisterName("Hello", i)
	if err != nil {
		fmt.Println("注册服务失败:", err)
		return
	}
	fmt.Println("注册服务成功")
}
