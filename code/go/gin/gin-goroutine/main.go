package main

import (
	"log"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
)

// 文档：在 handler 里启动 goroutine 时，不能直接用原来的 *gin.Context。
// 请求结束后 Context 会被复用，必须 c.Copy()。
func main() {
	r := gin.Default()
	r.GET("/async", func(c *gin.Context) {
		cp := c.Copy()
		go func() {
			time.Sleep(100 * time.Millisecond)
			log.Println("异步读路径", cp.Request.URL.Path)
		}()
		c.JSON(http.StatusOK, gin.H{"msg": "已接收，后台继续"})
	})
	if err := r.Run(":7686"); err != nil {
		panic(err)
	}
}
