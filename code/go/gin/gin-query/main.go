package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func getUserInfoById(c *gin.Context) {
	userId := c.Query("userId")
	c.JSON(http.StatusOK, gin.H{
		"userId": userId,
	})
}

func main() {
	ginEngine := gin.Default()
	// 例如 http://localhost:8080/user?userId=1 表示查询用户id为1的数据
	ginEngine.GET("/user", getUserInfoById)
	err := ginEngine.Run(":7677")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
}
