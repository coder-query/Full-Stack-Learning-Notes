// 示例 09：同时发起多个起始 URL，并行抓取单页内容。
// 文档：https://go-colly.org/docs/examples/parallel/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector(
		colly.MaxDepth(1), // 只解析每个起始页，不跟链接继续爬
		colly.Async(true),
		colly.AllowedDomains("quotes.toscrape.com"),
	)

	if err := c.Limit(&colly.LimitRule{
		DomainGlob:  "*",
		Parallelism: 2,
	}); err != nil {
		fmt.Println("设置并发失败:", err)
		return
	}

	c.OnHTML("a.tag", func(e *colly.HTMLElement) {
		fmt.Println("标签", e.Text, "->", e.Request.AbsoluteURL(e.Attr("href")))
	})

	pages := []string{
		"https://quotes.toscrape.com/page/1/",
		"https://quotes.toscrape.com/page/2/",
		"https://quotes.toscrape.com/tag/love/",
	}
	for _, page := range pages {
		if err := c.Visit(page); err != nil {
			fmt.Println("提交失败:", page, err)
		}
	}

	// 异步模式下必须 Wait，否则 main 退出后 goroutine 可能被中断。
	c.Wait()
	fmt.Println("全部请求结束")
}
