// 标准库 time：时间与时钟。文档 https://studygolang.com/pkgdoc/pkg/time/
package main

import (
	"fmt"
	"time"
)

func main() {
	// 当前时间
	now := time.Now()
	fmt.Println("Now:", now)

	// 布局字符串必须用参考时间 2006-01-02 15:04:05
	fmt.Println("Format:", now.Format("2006-01-02 15:04:05"))

	// Parse：字符串 → time.Time
	t, err := time.Parse("2006-01-02", "2026-11-25")
	if err != nil {
		panic(err)
	}
	fmt.Println("Parse:", t.Format(time.RFC3339))

	// 加减、比较
	later := now.Add(2 * time.Hour)
	fmt.Println("2小时后:", later.After(now))

	// Duration 与 Sleep
	start := time.Now()
	time.Sleep(50 * time.Millisecond)
	fmt.Println("Sleep 耗时:", time.Since(start))

	// Timer / Ticker（Ticker 用于周期任务）
	timer := time.NewTimer(30 * time.Millisecond)
	<-timer.C
	fmt.Println("Timer 触发")

	ticker := time.NewTicker(20 * time.Millisecond)
	defer ticker.Stop()
	<-ticker.C
	fmt.Println("Ticker 第一次 tick")
}
