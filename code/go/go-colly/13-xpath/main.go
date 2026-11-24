// 示例 13：用 OnXML + XPath 解析页面（HTML 与纯 XML 均可）。
// Colly 在 HTML 上走 htmlquery，在 XML 上走 xmlquery；API 与 OnHTML 类似，只是选择器换成 XPath。
// 文档 Getting started：https://go-colly.org/docs/introduction/start/（OnXML 小节）
package main

import (
	"fmt"
	"strings"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector(
		colly.AllowedDomains(
			"quotes.toscrape.com",
			"www.w3schools.com",
			"w3schools.com",
		),
	)

	// ---------- 1. 在 HTML 上用 XPath（与 01-basic 的 CSS 选择器效果可对照） ----------

	// //div[@class='quote']：选出每个名言块（等价于 CSS 的 div.quote）
	c.OnXML("//div[@class='quote']", func(e *colly.XMLElement) {
		text := strings.TrimSpace(e.ChildText(".//span[@class='text']"))
		author := e.ChildText(".//small[@class='author']")
		fmt.Printf("[HTML+XPath] %s —— %s\n", text, author)
	})

	// //a[@href]：所有带 href 的链接；取 @href 属性
	c.OnXML("//a[@href]", func(e *colly.XMLElement) {
		if e.Attr("class") != "tag" {
			return
		}
		fmt.Printf("[HTML+XPath] 标签链接 %s -> %s\n", e.Text, e.Attr("href"))
	})

	// ---------- 2. 纯 XML 文档（根节点匹配一次，子节点用 ChildText） ----------

	c.OnXML("//note", func(e *colly.XMLElement) {
		fmt.Println("[XML] note 节点:")
		fmt.Println("  to:", e.ChildText("to"))
		fmt.Println("  from:", e.ChildText("from"))
		fmt.Println("  heading:", e.ChildText("heading"))
		fmt.Println("  body:", e.ChildText("body"))
	})

	// 也可以直接匹配子路径，回调里的 e.Text 即为该节点文本
	c.OnXML("//note/to", func(e *colly.XMLElement) {
		fmt.Println("[XML] //note/to 直达:", e.Text)
	})

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("正在访问", r.URL)
	})

	// HTML 练习站
	if err := c.Visit("https://quotes.toscrape.com/"); err != nil {
		fmt.Println("quotes 访问失败:", err)
	}

	// W3Schools 自带的小型 XML 样例（无 HTML 标签，专门练 XML XPath）
	if err := c.Visit("https://www.w3schools.com/xml/note.xml"); err != nil {
		fmt.Println("note.xml 访问失败:", err)
	}
}
