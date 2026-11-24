// first-demo：早期练习示例——自定义 Transport、随机 UA、解析 title 与 img。
package main

import (
	"fmt"
	"math/rand"
	"net"
	"net/http"
	"time"

	"github.com/gocolly/colly"
)

const letterBytes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

// RandomString 生成随机长度字母串，用作 User-Agent。
func RandomString() string {
	b := make([]byte, rand.Intn(10)+10)
	for i := range b {
		b[i] = letterBytes[rand.Intn(len(letterBytes))]
	}
	return string(b)
}

func main() {
	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		r.Headers.Set("User-Agent", RandomString())
	})

	c.WithTransport(&http.Transport{
		DialContext: (&net.Dialer{
			Timeout:   30 * time.Second,
			KeepAlive: 30 * time.Second,
		}).DialContext,
		TLSHandshakeTimeout: 10 * time.Second,
	})

	// 收集图片绝对地址（站点若用协议相对路径 //... 需补全 https:）
	urlSlice := make([]string, 0)

	c.OnHTML("title", func(e *colly.HTMLElement) {
		fmt.Println("网页标题：", e.Text)
	})

	c.OnHTML("img", func(e *colly.HTMLElement) {
		const urlPrefix = "https:"
		src := e.Attr("src")
		fmt.Printf("网页img: %s, 说明alt: %s\n", urlPrefix+src, e.Attr("alt"))
		urlSlice = append(urlSlice, urlPrefix+src)
	})

	if err := c.Visit("https://www.w3cschool.cn/"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
