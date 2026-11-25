// 标准库 net/netip：IP 与网段值类型（替代部分 net.ParseIP 场景）。文档 https://studygolang.com/pkgdoc/pkg/net/netip/
package main

import (
	"fmt"
	"net/netip"
)

func main() {
	addr := netip.MustParseAddr("192.0.2.1")
	fmt.Println("Addr:", addr, "IPv4:", addr.Is4())

	prefix, err := netip.ParsePrefix("10.0.0.0/8")
	if err != nil {
		panic(err)
	}
	fmt.Println("Contains:", prefix.Contains(addr))

	localhost := netip.AddrPortFrom(netip.MustParseAddr("127.0.0.1"), 8080)
	fmt.Println("AddrPort:", localhost.String())
}
