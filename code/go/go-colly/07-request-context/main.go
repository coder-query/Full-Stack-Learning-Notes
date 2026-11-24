// 示例 07：Request.Ctx 在单次请求生命周期内传递自定义数据。
// 文档：https://go-colly.org/docs/examples/request_context/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		// Put 存入键值；子请求会继承父请求的 Ctx（跟链时很有用）。
		r.Ctx.Put("url", r.URL.String())
		r.Ctx.Put("tag", "quotes-home")
	})

	c.OnResponse(func(r *colly.Response) {
		// Get 按字符串键读取（非 string 类型需用 GetAny）。
		fmt.Println("从上下文取出 url:", r.Ctx.Get("url"))
		fmt.Println("从上下文取出 tag:", r.Ctx.Get("tag"))
	})

	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
