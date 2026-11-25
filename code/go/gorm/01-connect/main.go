// 文档：连接到数据库 — https://gorm.io/zh_CN/docs/connecting_to_the_database.html
package main

import (
	"fmt"
	"time"

	"gorm-study/gormutil"
)

func main() {
	db, path, err := gormutil.OpenFile("01-connect")
	if err != nil {
		panic(err)
	}
	fmt.Println("sqlite 文件:", path)

	sqlDB, err := db.DB()
	if err != nil {
		panic(err)
	}
	sqlDB.SetMaxIdleConns(5)
	sqlDB.SetMaxOpenConns(20)
	sqlDB.SetConnMaxLifetime(time.Hour)

	if err := sqlDB.Ping(); err != nil {
		panic(err)
	}
	fmt.Println("Ping 成功，连接池已配置")
}
