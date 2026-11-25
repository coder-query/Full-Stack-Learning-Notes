// 标准库 net/rpc：早期 Go RPC（JSON 等需 net/rpc/jsonrpc）。文档 https://studygolang.com/pkgdoc/pkg/net/rpc/
package main

import (
	"fmt"
	"net"
	"net/rpc"
)

type Arith struct{}

type Args struct {
	A, B int
}

type Reply struct {
	C int
}

func (a *Arith) Add(args Args, reply *Reply) error {
	reply.C = args.A + args.B
	return nil
}

func main() {
	rpcServer := rpc.NewServer()
	_ = rpcServer.Register(new(Arith))

	ln, err := net.Listen("tcp", "127.0.0.1:0")
	if err != nil {
		panic(err)
	}
	defer ln.Close()
	go rpcServer.Accept(ln)

	client, err := rpc.Dial("tcp", ln.Addr().String())
	if err != nil {
		panic(err)
	}
	defer client.Close()

	var reply Reply
	if err := client.Call("Arith.Add", Args{A: 3, B: 5}, &reply); err != nil {
		panic(err)
	}
	fmt.Println("RPC 3+5=", reply.C)
}
