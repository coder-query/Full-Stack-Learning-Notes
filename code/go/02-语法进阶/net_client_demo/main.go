package main

import (
	"errors"
	"fmt"
	"net"
)

func main() {
	cient, err := net.Dial("tcp", "127.0.0.1:9898")
	if err != nil {
		_ = errors.New("客户端创建失败")
		fmt.Print("error:", err)
		return
	}
	fmt.Println("客户端创建成功")
	_, err = cient.Write([]byte("hello, server"))
	if err != nil {
		_ = errors.New("客户端发送数据失败")
		fmt.Print("error:", err)
		return
	}
	fmt.Println("客户端发送数据成功")
}
