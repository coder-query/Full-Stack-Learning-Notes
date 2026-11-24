// 示例 04：请求失败时用 OnError 处理；成功时才会走 OnHTML。
// 文档：https://go-colly.org/docs/examples/error_handling/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector()

	c.OnHTML("title", func(e *colly.HTMLElement) {
		fmt.Println("标题:", e.Text)
	})

	// OnError：网络错误、状态码策略、域名/深度限制等导致失败时调用。
	c.OnError(func(r *colly.Response, err error) {
		fmt.Println("请求失败")
		fmt.Println("URL:", r.Request.URL)
		fmt.Println("Error:", err)
	})

	// 故意访问不存在域名，触发 OnError。
	if err := c.Visit("https://definitely-not-a.website/"); err != nil {
		// Visit 也可能直接把错误返回给调用方。
		fmt.Println("Visit 返回:", err)
	}
}
