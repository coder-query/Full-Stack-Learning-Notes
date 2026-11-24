// 示例 05：MaxDepth 限制链接递归深度，避免整站无限爬取。
// 文档：https://go-colly.org/docs/examples/max_depth/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector(
		// MaxDepth(1)：起始页 depth=1；从该页点出去的链接 depth=2，超过 1 不再访问。
		colly.MaxDepth(1),
		colly.AllowedDomains("quotes.toscrape.com"),
	)

	c.OnHTML("a", func(e *colly.HTMLElement) {
		fmt.Println("e: ", e)
		link := e.Attr("href")
		fmt.Printf("发现链接 depth=%d e.Text: %s -> link: %s\n", e.Request.Depth, e.Text, link)
		// Request.Visit 会沿用当前请求的 depth，并在内部 +1 作为子请求深度。
		_ = e.Request.Visit(link)
	})

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("正在访问 depth", r.Depth, r.URL)
	})

	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
