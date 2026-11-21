package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func getUserInfoById(c *gin.Context) {
	paramId := c.Param("id")
	// 这里直接就返回了，后续做项目是gorm查询数据库
	c.JSON(http.StatusOK, gin.H{
		"id": paramId,
	})
}

func main() {
	ginEngine := gin.Default()
	// http get 请求的路径传参
	// 例如 http://localhost:8080/user/1 表示查询用户id为1的数据
	ginEngine.GET("/user/:id", getUserInfoById)
	err := ginEngine.Run(":7675")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
}
