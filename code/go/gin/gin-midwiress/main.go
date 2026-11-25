package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：中间件可以只挂在 Group 上，不影响组外路由。
func auth() gin.HandlerFunc {
	return func(c *gin.Context) {
		if c.GetHeader("X-Token") == "" {
			c.AbortWithStatusJSON(http.StatusUnauthorized, gin.H{"msg": "缺少 X-Token"})
			return
		}
		c.Set("user", "demo")
		c.Next()
	}
}

func main() {
	r := gin.Default()
	r.GET("/public", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"msg": "无需 token"})
	})

	api := r.Group("/api", auth())
	api.GET("/me", func(c *gin.Context) {
		user, _ := c.Get("user")
		c.JSON(http.StatusOK, gin.H{"user": user})
	})

	if err := r.Run(":7658"); err != nil {
		panic(err)
	}
}
