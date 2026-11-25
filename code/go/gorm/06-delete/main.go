// 文档：删除 — https://gorm.io/zh_CN/docs/delete.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type Article struct {
	gorm.Model
	Title string
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&Article{})
	a := Article{Title: "hello"}
	db.Create(&a)
	id := a.ID

	db.Delete(&a)
	var cnt int64
	db.Model(&Article{}).Count(&cnt)
	fmt.Println("软删后可见条数:", cnt)

	db.Unscoped().Model(&Article{}).Count(&cnt)
	fmt.Println("Unscoped 含已删:", cnt)

	db.Unscoped().Delete(&Article{}, id)
	db.Unscoped().Model(&Article{}).Count(&cnt)
	fmt.Println("物理删除后:", cnt)
}
