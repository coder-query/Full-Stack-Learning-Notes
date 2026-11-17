package main

import (
	"errors"
	"fmt"
	"net"
)

func main() {
	listen, err := net.Listen("tcp", "127.0.0.1:9898")
	if err != nil {
		_ = errors.New("服务端监听器创建失败")
		fmt.Print("error:", err)
		return
	}
	fmt.Println("服务端监听器创建成功")
	conn, err := listen.Accept()
	if err != nil {
		_ = errors.New("服务端连接conn获取失败")
		fmt.Print("error:", err)
		return
	}
	fmt.Println("服务端监听器创建成功")
	var buf = make([]byte, 1024)
	content, err := conn.Read(buf)
	if err != nil {
		_ = errors.New("读取客户端发送的数据失败")
		fmt.Print("error:", err)
		return
	}
	fmt.Println("客户端发送的数据：, string(buf[:content]))", string(buf[:content]))
}
