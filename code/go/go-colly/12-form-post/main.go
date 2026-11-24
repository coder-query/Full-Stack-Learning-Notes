// 示例 12：用 Post 提交 application/x-www-form-urlencoded 表单。
// 文档 login 示例：https://go-colly.org/docs/examples/login/
package main

import (
	"fmt"

	"github.com/gocolly/colly"
)

func main() {
	c := colly.NewCollector(
		colly.AllowedDomains("httpbin.org"),
	)

	c.OnResponse(func(r *colly.Response) {
		fmt.Println("status", r.StatusCode)
		// httpbin/post 会把收到的 form 字段 echo 回 JSON body。
		fmt.Println(string(r.Body))
	})

	// Post 走 POST，且不经过 GET 的「已访问 URL」去重逻辑。
	err := c.Post("https://httpbin.org/post", map[string]string{
		"username": "demo",
		"password": "demo",
	})
	if err != nil {
		fmt.Println("提交失败:", err)
	}
}
