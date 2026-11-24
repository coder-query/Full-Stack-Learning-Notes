// 示例 01：Colly 最简用法——创建收集器、注册回调、发起访问。
// 文档：https://go-colly.org/docs/introduction/start/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	// NewCollector 创建爬虫实例，可通过 colly.xxx() 配置选项。
	c := colly.NewCollector(
		// AllowedDomains：只允许访问列表里的域名，防止跟到站外链接。
		colly.AllowedDomains("quotes.toscrape.com"),
	)

	// OnHTML：当页面 HTML 中出现匹配 CSS 选择器的元素时触发。
	// ChildText 在当前元素下按子选择器取文本。
	c.OnHTML("div.quote", func(e *colly.HTMLElement) {
		fmt.Printf("名言: %s\n作者: %s\n\n",
			e.ChildText("span.text"),
			e.ChildText("small.author"),
		)
	})

	// OnRequest：每次 HTTP 请求发出之前触发，可改 Header、打日志等。
	c.OnRequest(func(r *colly.Request) {
		fmt.Println("正在访问", r.URL.String())
	})

	// Visit 是同步 GET 入口；失败时返回 error（如 DNS、被拒绝的域名）。
	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
