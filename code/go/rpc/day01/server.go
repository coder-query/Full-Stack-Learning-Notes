package main

import (
	"fmt"
	"net"
	"net/rpc"
)

type World struct {
}

func (world *World) Hello(name string, response *string) error {
	*response = "hello " + name + "我是rpc 的服务端"
	return nil
}

func main() {
	// 1.注册服务,绑定对象方法
	err := rpc.RegisterName("Hello", new(World))
	if err != nil {
		fmt.Println("注册服务失败:", err)
		return
	}
	fmt.Println("注册服务成功")
	// 2.设置监听
	fmt.Println("开始监听8800端口")
	listener, err := net.Listen("tcp", "127.0.0.1:8800")
	if err != nil {
		fmt.Println("设置监听失败:", err)
		return
	}
	// 监听完毕后，defer执行
	defer func(listener net.Listener) {
		err := listener.Close()
		if err != nil {
			fmt.Println("关闭监听失败:", err)
		}
	}(listener)

	// 3.获取连接
	conn, err := listener.Accept()
	if err != nil {
		fmt.Println("获取连接失败:", err)
		return
	}
	fmt.Println("获取连接成功")
	// 4.绑定服务，处理连接
	rpc.ServeConn(conn)
}
