package main

import (
	"log"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
)

// timeMiddleware 统计每个请求耗时的中间件
// 注意：返回值类型是 gin.HandlerFunc，不能加 *（指针），否则 engine.Use 编译不过
func timeMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		// Next 之前：请求还没进入业务处理，常用于记开始时间、鉴权、打日志
		start := time.Now()

		c.Next() // 放行，先执行后面的中间件和真正的 handler

		// Next 之后：整个请求已处理完毕，此时才能拿到最终的状态码和总耗时
		latency := time.Since(start)
		log.Printf("[%s] %s %s %d %s",
			c.Request.Method,       // 请求方法，如 GET/POST
			c.Request.URL.Path,     // 路径，如 /slow
			c.Request.URL.RawQuery, // 查询参数，如 id=1&name=q（没有则为空串）
			c.Writer.Status(),      // 响应状态码，Next 之后拿到的才是 handler 设置的最终值
			latency,                // 本次请求总耗时
		)
	}
}

func main() {
	// gin.New() 创建一个"裸"引擎（不带任何中间件）
	engine := gin.New()
	// 手动挂载两个官方中间件：访问日志 + panic 恢复
	// 等价于直接用 gin.Default()
	engine.Use(gin.Logger(), gin.Recovery())

	// 挂载自定义中间件（请求响应计时）
	engine.Use(timeMiddleware())

	// 普通接口：耗时极短，中间件打出的耗时接近 0
	engine.GET("/hello", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"msg": "hello"})
	})

	// 模拟耗时接口：睡 300ms，方便观察中间件统计出的耗时
	engine.GET("/slow", func(c *gin.Context) {
		time.Sleep(300 * time.Millisecond)
		c.JSON(http.StatusOK, gin.H{"msg": "slow response"})
	})

	// 端口用 7657：7656 已被 gin-validator 服务占用（本目录是独立的演示程序）
	if err := engine.Run(":7657"); err != nil {
		log.Fatal(err)
	}
}
