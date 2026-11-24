// 示例 02：演示一次成功请求里各回调的触发顺序（XPath 专题见 13-xpath）。
// v1.2.0 无 OnResponseHeaders，成功路径一般为：
// OnRequest → OnResponse → OnHTML → OnXML → OnScraped
// 若请求失败：OnRequest → OnError（OnHTML 等解析回调不会执行）。
package main

import (
	"fmt"
	"log"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("1. OnRequest ", r.URL)
	})

	c.OnError(func(r *colly.Response, err error) {
		fmt.Println("2. OnError   ", r.Request.URL, err)
	})

	// OnResponse：已拿到响应体，尚未做 DOM 解析。
	c.OnResponse(func(r *colly.Response) {
		fmt.Println("3. OnResponse", r.Request.URL, "status", r.StatusCode, "bytes", len(r.Body))
	})

	// OnHTML：用 goquery CSS 选择器匹配节点。
	c.OnHTML("h1", func(e *colly.HTMLElement) {
		fmt.Println("4. OnHTML    h1 =", e.Text)
	})

	// OnXML：用 XPath 匹配节点（HTML/XML 均可）。
	c.OnXML("//title", func(e *colly.XMLElement) {
		fmt.Println("5. OnXML     title =", e.Text)
	})

	// OnScraped：该 URL 上所有 HTML/XML 回调处理完毕后的收尾点。
	c.OnScraped(func(r *colly.Response) {
		fmt.Println("6. OnScraped ", r.Request.URL)
	})

	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		log.Fatal(err)
	}
}
