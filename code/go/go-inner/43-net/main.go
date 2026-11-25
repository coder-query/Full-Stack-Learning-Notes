// 标准库 net：网络地址与简单解析（不含 HTTP）。文档 https://studygolang.com/pkgdoc/pkg/net/
package main

import (
	"fmt"
	"net"
)

func main() {
	// JoinHostPort / SplitHostPort
	addr := net.JoinHostPort("127.0.0.1", "8080")
	fmt.Println("JoinHostPort:", addr)
	host, port, err := net.SplitHostPort(addr)
	if err != nil {
		panic(err)
	}
	fmt.Println("Split:", host, port)

	// ParseIP
	ip := net.ParseIP("192.0.2.1")
	fmt.Println("ParseIP:", ip, "IsLoopback:", ip.IsLoopback())

	// 本机网卡（无网络请求）
	ifaces, err := net.Interfaces()
	if err != nil {
		panic(err)
	}
	for _, iface := range ifaces {
		if iface.Flags&net.FlagUp == 0 || iface.Flags&net.FlagLoopback != 0 {
			continue
		}
		fmt.Println("网卡:", iface.Name)
		break
	}
}
