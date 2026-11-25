package main

import (
	"html/template"
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：HTML 模板渲染。模板写在源码里，避免依赖启动时的工作目录。
const page = `<!DOCTYPE html>
<html><body>
<h1>{{ .Title }}</h1>
<p>{{ .Name }}</p>
</body></html>`

func main() {
	r := gin.Default()
	r.SetHTMLTemplate(template.Must(template.New("index").Parse(page)))

	r.GET("/", func(c *gin.Context) {
		c.HTML(http.StatusOK, "index", gin.H{
			"Title": "Gin HTML",
			"Name":  "Query",
		})
	})
	r.GET("/raw", func(c *gin.Context) {
		c.Data(http.StatusOK, "text/plain; charset=utf-8", []byte("static-like body"))
	})

	if err := r.Run(":7683"); err != nil {
		panic(err)
	}
}
