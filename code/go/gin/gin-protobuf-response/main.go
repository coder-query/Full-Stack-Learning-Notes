package main

import (
	"gin-demo/gin-protobuf-response/proto"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	ginEngine := gin.Default()
	ginEngine.GET("/", func(c *gin.Context) {
		c.ProtoBuf(http.StatusOK, &proto.User{
			Name: "admin",
			Age:  21,
		})
	})
	ginEngine.Run(":5654")
}
