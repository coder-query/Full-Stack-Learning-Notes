package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	engine := gin.Default()
	// 用户组路由
	userGroup := engine.Group("/user")
	{
		// 增
		userGroup.POST("/add", addUser)
		// 删
		userGroup.DELETE("/delete/:id", deleteUser)
		// 改
		userGroup.PUT("/update/:id", updateUser)
		// 查
		userGroup.GET("/:id", getUserInfoById)
	}
	err := engine.Run(":7675")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
}

func updateUser(context *gin.Context) {

}

func deleteUser(context *gin.Context) {

}

func addUser(context *gin.Context) {

}
func getUserInfoById(c *gin.Context) {
	paramId := c.Param("id")
	// 这里直接就返回了，后续做项目是 gorm 查询数据库
	c.JSON(http.StatusOK, gin.H{
		"id": paramId,
	})
}
