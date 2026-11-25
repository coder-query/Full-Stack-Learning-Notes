package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：Cookie、重定向、未匹配路由。
func main() {
	r := gin.Default()

	r.GET("/set", func(c *gin.Context) {
		c.SetCookie("session", "demo-token", 3600, "/", "", false, true)
		c.JSON(http.StatusOK, gin.H{"msg": "cookie 已写入"})
	})

	r.GET("/get", func(c *gin.Context) {
		v, err := c.Cookie("session")
		if err != nil {
			c.JSON(http.StatusUnauthorized, gin.H{"error": "没有 cookie"})
			return
		}
		c.JSON(http.StatusOK, gin.H{"session": v})
	})

	r.GET("/go", func(c *gin.Context) {
		c.Redirect(http.StatusFound, "/get")
	})

	r.NoRoute(func(c *gin.Context) {
		c.JSON(http.StatusNotFound, gin.H{"error": "路由不存在", "path": c.Request.URL.Path})
	})

	if err := r.Run(":7684"); err != nil {
		panic(err)
	}
}
