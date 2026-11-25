package main

import (
	"net/http"
	"os"
	"path/filepath"

	"github.com/gin-gonic/gin"
)

// 文档：单文件与多文件上传。文件写到系统临时目录。
func main() {
	r := gin.Default()
	r.MaxMultipartMemory = 8 << 20

	r.POST("/upload", func(c *gin.Context) {
		file, err := c.FormFile("file")
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		dst := filepath.Join(os.TempDir(), filepath.Base(file.Filename))
		if err := c.SaveUploadedFile(file, dst); err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
			return
		}
		c.JSON(http.StatusOK, gin.H{"name": file.Filename, "size": file.Size, "saved": dst})
	})

	r.POST("/uploads", func(c *gin.Context) {
		form, err := c.MultipartForm()
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		files := form.File["files"]
		names := make([]string, 0, len(files))
		for _, file := range files {
			dst := filepath.Join(os.TempDir(), filepath.Base(file.Filename))
			if err := c.SaveUploadedFile(file, dst); err != nil {
				c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
				return
			}
			names = append(names, file.Filename)
		}
		c.JSON(http.StatusOK, gin.H{"count": len(names), "names": names})
	})

	if err := r.Run(":7681"); err != nil {
		panic(err)
	}
}
