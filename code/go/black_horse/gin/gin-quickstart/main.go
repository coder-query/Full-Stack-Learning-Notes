package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()
	router.GET("/ping", func(ginContext *gin.Context) {
		ginContext.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})
	if err := router.Run("127.0.0.1:8083"); err != nil {
		panic(err)
	}
}
