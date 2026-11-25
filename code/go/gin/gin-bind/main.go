package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// 文档：模型绑定。ShouldBind* 失败时由调用方决定状态码，不会自动 400。

type jsonBody struct {
	Name string `json:"name" binding:"required"`
	Age  int    `json:"age" binding:"gte=0,lte=130"`
}

type queryParam struct {
	Page int    `form:"page,default=1"`
	Q    string `form:"q"`
}

type uriParam struct {
	ID string `uri:"id" binding:"required"`
}

type headerParam struct {
	Token string `header:"X-Token" binding:"required"`
}

type formBody struct {
	Title string `form:"title" binding:"required"`
}

func main() {
	r := gin.Default()

	r.POST("/json", func(c *gin.Context) {
		var in jsonBody
		if err := c.ShouldBindJSON(&in); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, in)
	})

	r.GET("/query", func(c *gin.Context) {
		var in queryParam
		if err := c.ShouldBindQuery(&in); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, in)
	})

	r.GET("/users/:id", func(c *gin.Context) {
		var in uriParam
		if err := c.ShouldBindUri(&in); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, in)
	})

	r.GET("/header", func(c *gin.Context) {
		var in headerParam
		if err := c.ShouldBindHeader(&in); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, gin.H{"token": in.Token})
	})

	r.POST("/form", func(c *gin.Context) {
		var in formBody
		if err := c.ShouldBind(&in); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, in)
	})

	if err := r.Run(":7680"); err != nil {
		panic(err)
	}
}
