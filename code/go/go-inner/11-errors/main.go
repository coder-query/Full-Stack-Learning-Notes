// 标准库 errors：错误值构造与判断。文档 https://studygolang.com/pkgdoc/pkg/errors/
package main

import (
	"errors"
	"fmt"
)

var ErrNotFound = errors.New("record not found")

type OpError struct {
	Op string
}

func (e *OpError) Error() string {
	return "op failed: " + e.Op
}

func main() {
	err1 := errors.New("简单错误")
	fmt.Println(err1)

	// errors.Is：判断错误链里是否包含目标（可用哨兵 errors）
	wrapped := fmt.Errorf("查询失败: %w", ErrNotFound)
	fmt.Println("Is NotFound?", errors.Is(wrapped, ErrNotFound))

	// errors.As：从错误链里取出具体类型
	var op *OpError
	opErr := fmt.Errorf("外层: %w", &OpError{Op: "read"})
	if errors.As(opErr, &op) {
		fmt.Println("As OpError:", op.Op)
	}

	// errors.Join（Go 1.20+）：合并多个错误
	joined := errors.Join(errors.New("a"), errors.New("b"))
	fmt.Println("Join:", joined)
}
