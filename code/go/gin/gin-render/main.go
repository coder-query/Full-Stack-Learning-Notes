package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：多种响应渲染。JSON 见 gin-json-response。
type user struct {
	Name string `json:"name" xml:"name" yaml:"name"`
	City string `json:"city" xml:"city" yaml:"city"`
}

func main() {
	r := gin.Default()
	u := user{Name: "张三<script>", City: "杭州"}

	r.GET("/xml", func(c *gin.Context) { c.XML(http.StatusOK, u) })
	r.GET("/yaml", func(c *gin.Context) { c.YAML(http.StatusOK, u) })
	// JSONP：请求需带 callback 查询参数，例如 /jsonp?callback=cb
	r.GET("/jsonp", func(c *gin.Context) { c.JSONP(http.StatusOK, u) })
	// 非 ASCII 转成 \uXXXX
	r.GET("/ascii", func(c *gin.Context) { c.AsciiJSON(http.StatusOK, u) })
	// 不转义 < > &
	r.GET("/pure", func(c *gin.Context) { c.PureJSON(http.StatusOK, u) })
	// 防 JSON 劫持：数组前加 while(1);
	r.GET("/secure", func(c *gin.Context) { c.SecureJSON(http.StatusOK, []string{"a", "b"}) })

	if err := r.Run(":7682"); err != nil {
		panic(err)
	}
}
