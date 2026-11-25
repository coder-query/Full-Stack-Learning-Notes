// 标准库 context：请求作用域的取消与超时。文档 https://studygolang.com/pkgdoc/pkg/context/
package main

import (
	"context"
	"fmt"
	"time"
)

func work(ctx context.Context, name string) error {
	select {
	case <-time.After(200 * time.Millisecond):
		fmt.Println(name, "完成")
		return nil
	case <-ctx.Done():
		return ctx.Err()
	}
}

func main() {
	// WithCancel：手动取消
	ctx, cancel := context.WithCancel(context.Background())
	go func() {
		time.Sleep(50 * time.Millisecond)
		cancel()
	}()
	if err := work(ctx, "cancel 任务"); err != nil {
		fmt.Println("cancel:", err)
	}

	// WithTimeout：超时自动取消
	ctx2, cancel2 := context.WithTimeout(context.Background(), 100*time.Millisecond)
	defer cancel2()
	if err := work(ctx2, "timeout 任务"); err != nil {
		fmt.Println("timeout:", err)
	}

	// WithValue：传请求级元数据（勿滥用存业务大对象）
	ctx3 := context.WithValue(context.Background(), "traceID", "t-001")
	fmt.Println("Value:", ctx3.Value("traceID"))
}
