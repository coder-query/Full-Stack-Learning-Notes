// 示例 11：把 HTML 解析成结构体，翻页汇总后输出 JSON。
// 思路参考文档 coursera / cryptocoin 等结构化抓取示例。
package main

import (
	"encoding/json"
	"fmt"
	"os"
	"strings"

	"github.com/gocolly/colly"
)

// Quote 表示 quotes.toscrape.com 上的一条名言记录。
type Quote struct {
	Text   string   `json:"text"`
	Author string   `json:"author"`
	Tags   []string `json:"tags"`
	About  string   `json:"about"` // 作者详情页链接
}

func main() {
	c := colly.NewCollector(
		colly.AllowedDomains("quotes.toscrape.com"),
		colly.MaxDepth(2), // 允许：首页 + 若干翻页
	)

	var quotes []Quote

	c.OnHTML("div.quote", func(e *colly.HTMLElement) {
		item := Quote{
			Text:   strings.TrimSpace(e.ChildText("span.text")),
			Author: e.ChildText("small.author"),
			About:  e.Request.AbsoluteURL(e.ChildAttr("span a", "href")),
		}
		// ForEach 遍历当前元素下匹配的子节点。
		e.ForEach("div.tags a.tag", func(_ int, tag *colly.HTMLElement) {
			item.Tags = append(item.Tags, tag.Text)
		})
		quotes = append(quotes, item)
	})

	// 只跟随分页“下一页”，不跟进每个标签链接，控制抓取范围。
	c.OnHTML("li.next a", func(e *colly.HTMLElement) {
		_ = e.Request.Visit(e.Attr("href"))
	})

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("正在访问", r.URL)
	})

	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("访问失败:", err)
		return
	}

	enc := json.NewEncoder(os.Stdout)
	enc.SetIndent("", "  ")
	if err := enc.Encode(quotes); err != nil {
		fmt.Println("编码失败:", err)
	}
	fmt.Fprintf(os.Stderr, "共 %d 条\n", len(quotes))
}
