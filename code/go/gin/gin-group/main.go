package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：路由分组。增删改查都挂在 /user 前缀下。
func main() {
	engine := gin.Default()
	userGroup := engine.Group("/user")
	{
		userGroup.POST("", addUser)
		userGroup.DELETE("/:id", deleteUser)
		userGroup.PUT("/:id", updateUser)
		userGroup.GET("/:id", getUserInfoById)
	}
	if err := engine.Run(":7676"); err != nil {
		panic(err)
	}
}

func addUser(c *gin.Context) {
	name := c.PostForm("name")
	c.JSON(http.StatusCreated, gin.H{"name": name})
}

func deleteUser(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"deleted": c.Param("id")})
}

func updateUser(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"updated": c.Param("id"), "name": c.PostForm("name")})
}

func getUserInfoById(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"id": c.Param("id")})
}
