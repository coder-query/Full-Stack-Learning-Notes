package main

import (
	"fmt"
	"net"
	"net/rpc"
)

type HelloService struct {
}

func (helloService *HelloService) CallHelloFun(request string, response *string) error {
	*response = "你好啊。你的请求参数是：" + request
	return nil
}

func main() {
	// 实例化一个监听器,sockt监听
	listen, err := net.Listen("tcp", "127.0.0.1:4576")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("实例化一个监听器 success")
	// 注册逻辑处理 handler
	err = rpc.RegisterName("helloService", new(HelloService))
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("注册逻辑处理 handler success")
	// 从监听器中获取连接conn
	conn, err := listen.Accept()
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("从监听器中获取连接conn success")
	rpc.ServeConn(conn)
}
