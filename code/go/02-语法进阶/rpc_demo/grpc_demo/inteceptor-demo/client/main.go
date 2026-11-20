package main

import (
	"context"
	"fmt"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"

	"grpc-demo/proto/proto_go"
)

// clientInterceptor 一元客户端拦截器：每次 RPC 调用【发起前后】经过这里
//   - ctx:     调用上下文（可塞入 metadata）
//   - method:  完整方法名 "/CpuService/getCpuInfo"
//   - req/reply: 请求体 / 响应体（reply 是指针，调用后已有值）
//   - invoker: 真正发起 RPC 的函数，调用它 = 放行
func clientInterceptor(ctx context.Context, method string, req, reply interface{}, cc *grpc.ClientConn, invoker grpc.UnaryInvoker, opts ...grpc.CallOption) error {
	start := time.Now()
	fmt.Printf("[客户端拦截器] 发起调用前: method=%s\n", method)

	// ── 前置处理：注入 token、埋点、重试逻辑都可以在这里做 ──

	err := invoker(ctx, method, req, reply, cc, opts...) // 真正发出 RPC 请求

	// ── 后置处理：记录耗时、错误统一处理 ──
	fmt.Printf("[客户端拦截器] 调用返回后: method=%s 耗时=%v err=%v\n", method, time.Since(start), err)

	return err
}

func main() {
	// 1. 建立连接，客户端拦截器用 WithUnaryInterceptor 传入
	grpcConn, err := grpc.NewClient("127.0.0.1:7876",
		grpc.WithTransportCredentials(insecure.NewCredentials()),
		grpc.WithUnaryInterceptor(clientInterceptor),
	)
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
		if i >= 5 { // 演示拦截器 5 次足够看清效果
			fmt.Println("数据全部获取完毕")
			break
		}
	}
}
