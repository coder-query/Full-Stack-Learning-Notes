package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：BasicAuth 中间件。浏览器会弹出用户名密码框。
// 示例账号 admin / secret。
func main() {
	r := gin.Default()
	authorized := r.Group("/admin", gin.BasicAuth(gin.Accounts{
		"admin": "secret",
	}))
	authorized.GET("/ping", func(c *gin.Context) {
		user := c.MustGet(gin.AuthUserKey).(string)
		c.JSON(http.StatusOK, gin.H{"user": user})
	})
	if err := r.Run(":7685"); err != nil {
		panic(err)
	}
}
