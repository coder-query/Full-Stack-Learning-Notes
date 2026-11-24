// 示例 03：Collector 配置项与底层 HTTP Transport。
// 文档：https://go-colly.org/docs/introduction/configuration/
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

// randomString 生成随机 User-Agent 字符串（文档中的 UA 切换示例）。
func randomString() string {
	b := make([]byte, rand.Intn(10)+10)
	for i := range b {
		b[i] = letterBytes[rand.Intn(len(letterBytes))]
	}
	return string(b)
}

func main() {
	c := colly.NewCollector(
		// UserAgent：设置默认 HTTP 请求头 User-Agent（可被 OnRequest 覆盖）。
		colly.UserAgent("go-colly-study/1.0"),
		colly.AllowedDomains("httpbin.org"),
		// AllowURLRevisit：允许对同一 URL 多次 GET（默认会去重，返回 ErrAlreadyVisited）。
		colly.AllowURLRevisit(),
	)

	c.OnRequest(func(r *colly.Request) {
		// 每次请求前改写 Header，优先级高于 NewCollector 里的 UserAgent。
		r.Headers.Set("User-Agent", randomString())
		fmt.Println("User-Agent:", r.Headers.Get("User-Agent"))
	})

	// WithTransport：替换标准库 http.Client 使用的 RoundTripper，可调超时、代理等。
	c.WithTransport(&http.Transport{
		Proxy: http.ProxyFromEnvironment, // 读取 HTTP_PROXY 等环境变量
		DialContext: (&net.Dialer{
			Timeout:   30 * time.Second,
			KeepAlive: 30 * time.Second,
		}).DialContext,
		MaxIdleConns:        100,
		IdleConnTimeout:     90 * time.Second,
		TLSHandshakeTimeout: 10 * time.Second,
	})

	// httpbin 的 /user-agent 会把收到的 User-Agent 写在响应 JSON 里。
	c.OnHTML("body", func(e *colly.HTMLElement) {
		fmt.Println(e.Text)
	})

	if err := c.Visit("https://httpbin.org/user-agent"); err != nil {
		fmt.Println("访问失败:", err)
	}
}
