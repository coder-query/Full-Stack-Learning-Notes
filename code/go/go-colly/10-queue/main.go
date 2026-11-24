// 示例 10：用内置 queue 先入队、再按固定线程数消费 URL。
// 文档：https://go-colly.org/docs/examples/queue/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
	"github.com/gocolly/colly/queue"
)

func main() {
	c := colly.NewCollector()

	// 第一个参数是消费者 goroutine 数量；第二个是内存队列存储。
	q, err := queue.New(
		2,
		&queue.InMemoryQueueStorage{MaxSize: 10000},
	)
	if err != nil {
		fmt.Println("创建队列失败:", err)
		return
	}

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("正在访问", r.URL)
	})

	c.OnHTML("span.text", func(e *colly.HTMLElement) {
		fmt.Println(e.Request.URL.Path, e.Text)
	})

	for i := 1; i <= 3; i++ {
		if err := q.AddURL(fmt.Sprintf("https://quotes.toscrape.com/page/%d/", i)); err != nil {
			fmt.Println("入队失败:", err)
		}
	}

	// Run 会阻塞直到队列里所有 URL 被当前 Collector 处理完。
	if err := q.Run(c); err != nil {
		fmt.Println("队列执行失败:", err)
	}
}
