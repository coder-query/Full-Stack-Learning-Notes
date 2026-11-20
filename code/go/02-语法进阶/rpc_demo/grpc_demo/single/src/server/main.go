package main

import (
	"context"
	"fmt"
	"net"

	"google.golang.org/grpc"

	"grpc-demo/proto/proto_go"
)

// CpuService 实现 cpu_grpc.pb.go 中的 CpuServiceServer 接口
type CpuService struct {
	proto_go.UnimplementedCpuServiceServer // 必须嵌入，保证前向兼容
}

// GetCpuInfo 必须是 *CpuService 的方法（而不是普通函数）
// 签名与生成代码一致：*CpuInfoRequest -> *CpuInfoResponse
func (s *CpuService) GetCpuInfo(ctx context.Context, cpuInfoRequest *proto_go.CpuInfoRequest) (*proto_go.CpuInfoResponse, error) {
	fmt.Printf("收到请求: name=%s, value=%s\n", cpuInfoRequest.GetName(), cpuInfoRequest.GetValue())
	return &proto_go.CpuInfoResponse{Name: "cpu", Value: "i9-14900K"}, nil
}

func main() {
	// 1. 创建 gRPC 服务器
	grpcServer := grpc.NewServer()

	// 2. 注册服务实现
	proto_go.RegisterCpuServiceServer(grpcServer, new(CpuService))

	// 3. 监听端口
	listen, err := net.Listen("tcp", ":7876")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("gRPC server listening on :7876")

	// 4. 开始提供服务（阻塞）
	_ = grpcServer.Serve(listen)
}
