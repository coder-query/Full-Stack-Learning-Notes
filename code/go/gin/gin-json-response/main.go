package main

import "github.com/gin-gonic/gin"

type commonResponse struct {
	Code    int         `json:"code"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func main() {
	engine := gin.Default()
	engine.GET("/", func(c *gin.Context) {
		c.JSON(200, commonResponse{
			Code:    0,
			Message: "success",
			Data:    "hello world",
		})
	})
	engine.Run(":5654")
}
