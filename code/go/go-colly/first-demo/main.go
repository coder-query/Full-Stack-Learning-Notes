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

func RandomString() string {
	b := make([]byte, rand.Intn(10)+10)
	for i := range b {
		b[i] = letterBytes[rand.Intn(len(letterBytes))]
	}
	return string(b)
}
func main() {
	// 创建默认收集器
	c := colly.NewCollector()
	c.OnRequest(func(r *colly.Request) {
		r.Headers.Set("User-Agent", RandomString())
	})
	c.WithTransport(&http.Transport{
		DialContext: (&net.Dialer{
			Timeout:   30 * time.Second, // 设置连接超时时间为 30 秒
			KeepAlive: 30 * time.Second, // 设置连接保持活动时间为 30 秒
		}).DialContext,
		TLSHandshakeTimeout: 10 * time.Second, // 设置 TLS 握手超时时间为 10 秒
	})
	urlSlice := make([]string, 10)
	// 当访问页面时，输出页面标题
	c.OnHTML("title", func(e *colly.HTMLElement) {
		fmt.Println("网页标题：", e.Text)
	})
	// 当访问页面时，输出页面标题
	c.OnHTML("img", func(e *colly.HTMLElement) {
		const urlPrefix = "https:"
		//fmt.Println("网页img：", e)
		fmt.Printf("网页img: %s,说明alt: %s", urlPrefix+e.Attr("src"), e.Attr("alt"))
		urlSlice = append(urlSlice, urlPrefix+e.Attr("src"))
	})

	// 访问页面
	c.Visit("https://www.w3cschool.cn/")
}
