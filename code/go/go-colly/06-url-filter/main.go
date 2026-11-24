// 示例 06：URLFilters 用正则白名单决定哪些 URL 允许访问。
// 文档：https://go-colly.org/docs/examples/url_filter/
package main

import (
	"fmt"
	"regexp"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector(
		colly.URLFilters(
			// 多个正则是“或”的关系：匹配任意一条即允许。
			regexp.MustCompile(`https://quotes\.toscrape\.com/$`),
			regexp.MustCompile(`https://quotes\.toscrape\.com/tag/.+`),
		),
		colly.MaxDepth(1),
	)

	c.OnHTML("a[href]", func(e *colly.HTMLElement) {
		// AbsoluteURL 把相对路径转成绝对 URL，便于 Visit 与正则匹配。
		link := e.Request.AbsoluteURL(e.Attr("href"))
		fmt.Printf("页面上的链接: %q -> %s\n", e.Text, link)
		_ = c.Visit(link)
	})

	c.OnRequest(func(r *colly.Request) {
		// 未通过 URLFilters 的地址不会走到这里。
		fmt.Println("通过过滤器，正在访问", r.URL.String())
	})

	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
