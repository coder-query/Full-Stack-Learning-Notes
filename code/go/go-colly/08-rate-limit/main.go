// 示例 08：异步请求 + LimitRule 控制并发、固定延迟与随机延迟。
// 文档：https://go-colly.org/docs/examples/rate_limit/
package main

import (
	"fmt"
	"time"

	"github.com/gocolly/colly"
	"github.com/gocolly/colly/debug"
)

func main() {
	c := colly.NewCollector(
		// Async(true)：Visit 异步提交任务，需在最后调用 Wait() 阻塞等待完成。
		colly.Async(true),
		// Debugger：打印请求/响应等调试日志。
		colly.Debugger(&debug.LogDebugger{}),
	)

	// Limit 按域名模式套用限速规则。
	if err := c.Limit(&colly.LimitRule{
		DomainGlob:  "*httpbin.*", // glob 匹配域名
		Parallelism: 2,            // 同一规则下最多 2 个并发请求
		Delay:       1 * time.Second,
		RandomDelay: 1 * time.Second, // 在 Delay 上再叠加 [0, RandomDelay) 的随机等待
	}); err != nil {
		fmt.Println("设置限速失败:", err)
		return
	}

	c.OnResponse(func(r *colly.Response) {
		fmt.Println("完成", r.Request.URL, "status", r.StatusCode)
	})

	// httpbin /delay/1 会刻意睡 1 秒再响应，便于观察限速效果。
	for i := 0; i < 4; i++ {
		url := fmt.Sprintf("https://httpbin.org/delay/1?n=%d", i)
		if err := c.Visit(url); err != nil {
			fmt.Println("提交请求失败:", err)
		}
	}

	c.Wait()
}
