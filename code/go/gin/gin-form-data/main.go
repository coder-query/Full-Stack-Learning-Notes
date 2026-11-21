package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func addUser(c *gin.Context) {
	username := c.PostForm("username")
	password := c.PostForm("password")
	age := c.PostForm("age")
	c.JSON(http.StatusOK, gin.H{
		"username": username,
		"password": password,
		"age":      age,
	})
}

func main() {
	engine := gin.Default()
	engine.POST("/user/add", addUser)
	err := engine.Run(":7675")
	if err != nil {
		fmt.Println("error:", err)
		return
	}
}
