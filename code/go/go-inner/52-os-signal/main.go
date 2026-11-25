// 标准库 os/signal：监听 OS 信号，用于优雅退出。文档 https://studygolang.com/pkgdoc/pkg/os/signal/
package main

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"time"
)

func main() {
	// 缓冲通道，避免错过信号
	sigCh := make(chan os.Signal, 1)
	signal.Notify(sigCh, os.Interrupt, syscall.SIGTERM)

	go func() {
		fmt.Println("等待 Ctrl+C（本示例 3 秒后自动退出演示）...")
		time.Sleep(3 * time.Second)
		// 模拟收到中断（仅演示；真实环境由用户 Ctrl+C）
		sigCh <- os.Interrupt
	}()

	sig := <-sigCh
	fmt.Println("收到信号:", sig)
	signal.Stop(sigCh)
	fmt.Println("可做清理后 exit")
}
