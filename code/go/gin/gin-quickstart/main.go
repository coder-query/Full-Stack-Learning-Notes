package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()
	router.GET("/ping", func(context *gin.Context) {
		writeString, err := context.Writer.WriteString("pong")
		if err != nil {
			return
		}
		fmt.Println(writeString)
	})
	router.Run() // listens on 0.0.0.0:8080 by default
}
