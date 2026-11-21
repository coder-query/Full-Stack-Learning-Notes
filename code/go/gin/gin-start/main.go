package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	ginEngine := gin.Default()
	ginEngine.GET("/ping", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})
	err := ginEngine.Run(":7678") // listens on 0.0.0.0:8080 by default
	if err != nil {
		fmt.Println("error:", err)
		return
	}
}
