// 标准库 log/slog：结构化日志（Go 1.21+）。文档 https://studygolang.com/pkgdoc/pkg/log/slog/
package main

import (
	"log/slog"
	"os"
)

func main() {
	logger := slog.New(slog.NewTextHandler(os.Stdout, &slog.HandlerOptions{
		Level: slog.LevelDebug,
	}))
	slog.SetDefault(logger)

	slog.Info("请求完成",
		"pkg", "fmt",
		"ms", 12,
	)
	slog.Warn("慢查询", "sql", "SELECT 1")
	slog.Debug("调试字段", "trace", "abc")

	// 带固定属性的子 logger
	reqLog := logger.With("req_id", "r-001")
	reqLog.Error("业务错误", "code", 500)
}
