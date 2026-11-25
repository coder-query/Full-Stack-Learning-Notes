// 文档：会话 — DryRun、跳过 Hook — https://gorm.io/zh_CN/docs/session.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name string
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{})

	// DryRun：只生成 SQL，不执行
	stmt := db.Session(&gorm.Session{DryRun: true}).Create(&User{Name: "dry"})
	fmt.Println("DryRun SQL:", stmt.Statement.SQL.String())

	db.Session(&gorm.Session{SkipHooks: true}).Create(&User{Name: "real"})
	var cnt int64
	db.Model(&User{}).Count(&cnt)
	fmt.Println("真实插入条数:", cnt)
}
