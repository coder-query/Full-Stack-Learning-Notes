// 文档：迁移 — https://gorm.io/zh_CN/docs/migration.html
package main

import (
	"fmt"
	"time"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type Product struct {
	gorm.Model
	Code  string `gorm:"size:32;uniqueIndex"`
	Price uint
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	if err := db.AutoMigrate(&Product{}); err != nil {
		panic(err)
	}
	fmt.Println("AutoMigrate Product 完成")

	db.Create(&Product{Code: "D42", Price: 100})
	var p Product
	db.First(&p, 1)
	fmt.Printf("插入: ID=%d Code=%s CreatedAt=%v (软删除字段 DeletedAt 由 gorm.Model 提供)\n",
		p.ID, p.Code, p.CreatedAt.Format(time.RFC3339))
}
