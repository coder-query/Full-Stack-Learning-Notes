package main

import (
	"context"
	"fmt"
	"net"
	"time"

	"google.golang.org/grpc"

	"grpc-demo/proto/proto_go"
)

// CpuService 实现 cpu_grpc.pb.go 中的 CpuServiceServer 接口
type CpuService struct {
	proto_go.UnimplementedCpuServiceServer // 必须嵌入，保证前向兼容
}

// GetCpuInfo 真正的业务方法（拦截器包裹在它外层）
func (s *CpuService) GetCpuInfo(ctx context.Context, cpuInfoRequest *proto_go.CpuInfoRequest) (*proto_go.CpuInfoResponse, error) {
	fmt.Println("    [业务方法] GetCpuInfo 执行中...")
	return &proto_go.CpuInfoResponse{Name: "cpu", Value: "i9-14900K"}, nil
}

// serverInterceptor 一元服务端拦截器：请求到达业务方法【之前】先经过这里
//   - ctx:     请求上下文（可取出 metadata）
//   - req:     反序列化后的请求体
//   - info:    请求元信息（FullMethod 就是 "/CpuService/getCpuInfo"）
//   - handler: 被包裹的业务方法，调用它 = 放行到真正的业务逻辑
//
// 返回值必须把 handler 的 (resp, err) 透传出去，否则客户端拿不到结果
func serverInterceptor(ctx context.Context, req interface{}, info *grpc.UnaryServerInfo, handler grpc.UnaryHandler) (interface{}, error) {
	start := time.Now()
	fmt.Printf("[服务端拦截器] 拦截到请求: method=%s\n", info.FullMethod)

	// ── 前置处理：鉴权、限流、参数校验、日志都可以在这里做 ──
	// md, _ := metadata.FromIncomingContext(ctx) // 例如取出 metadata 做 token 校验
	// if !valid(md) { return nil, status.Error(codes.Unauthenticated, "无效 token") }

	// 调用 handler = 放行，执行真正的业务方法
	resp, err := handler(ctx, req)

	// ── 后置处理：统一记录耗时、错误上报、审计日志 ──
	fmt.Printf("[服务端拦截器] 处理完成: method=%s 耗时=%v err=%v\n", info.FullMethod, time.Since(start), err)

	return resp, err
}

func main() {
	// 1. 创建 gRPC 服务器 —— 关键：拦截器必须作为参数传进 NewServer 才生效！
	grpcServer := grpc.NewServer(grpc.UnaryInterceptor(serverInterceptor))

	// 2. 注册服务实现
	proto_go.RegisterCpuServiceServer(grpcServer, new(CpuService))

	// 3. 监听端口
	listen, err := net.Listen("tcp", ":7876")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("gRPC server (with interceptor) listening on :7876")

	// 4. 开始提供服务（阻塞）
	_ = grpcServer.Serve(listen)
}
