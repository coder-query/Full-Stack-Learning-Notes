package main

import (
	"context"
	"fmt"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"

	"grpc-demo/proto/proto_go"
)

func main() {
	// 1. 建立连接（NewClient 是懒连接，真正请求时才拨号）
	// 本地学习环境用 insecure 明文传输，生产环境必须用 TLS
	grpcConn, err := grpc.NewClient("127.0.0.1:7876", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	defer func(grpcConn *grpc.ClientConn) {
		err := grpcConn.Close()
		if err != nil {
			panic(err)
		}
	}(grpcConn)

	// 2. 用生成代码里的构造函数创建客户端
	client := proto_go.NewCpuServiceClient(grpcConn)

	// 3. 轮询调用 RPC：每次调用独立的 3 秒超时，互不影响
	var i int = 0
	for {
		i++
		ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
		resp, err := client.GetCpuInfo(ctx, &proto_go.CpuInfoRequest{Name: "zsh", Value: "query"})
		cancel() // 循环内不能 defer cancel()，要立刻释放，避免堆积 timer
		if err != nil {
			fmt.Println("call error:", err)
			return
		}
		fmt.Printf("响应: name=%s, value=%s\n", resp.GetName(), resp.GetValue())
		time.Sleep(1 * time.Second)
		if i >= 10 {
			fmt.Println("数据全部获取完毕")
			break
		}
	}

}
