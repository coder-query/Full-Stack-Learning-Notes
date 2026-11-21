package main

import (
	"context"
	"fmt"
	"log"
	"os"
	"time"

	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
)

type Product struct {
	gorm.Model
	Code  string `gorm:"type:varchar(255)"`
	Name  string `gorm:"type:varchar(255)"`
	Desc  string `gorm:"type:varchar(255)"`
	Price int    `gorm:"type:int"`
}

func main() {
	newLogger := logger.New(
		log.New(os.Stdout, "\r\n", log.LstdFlags), // io writer
		logger.Config{
			SlowThreshold:             time.Second, // Slow SQL threshold
			LogLevel:                  logger.Info, // Log level
			IgnoreRecordNotFoundError: true,        // Ignore ErrRecordNotFound error for logger
			ParameterizedQueries:      true,        // Don't include params in the SQL log
			Colorful:                  true,        // Disable color
		},
	)
	dsn := "root:123456@tcp(127.0.0.1:3306)/gorm_test?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{
		Logger: newLogger,
	})
	if err != nil {
		panic("连接数据库失败")
	}

	ctx := context.Background()

	// 自动建表
	err = db.AutoMigrate(&Product{})
	if err != nil {
		return
	}

	// 创建
	err = gorm.G[Product](db).Create(ctx, &Product{Code: "D42", Price: 100})

	// 查询
	product, err := gorm.G[Product](db).Where("id = ?", 1).First(ctx) // 查找对应主键的产品
	fmt.Println(product)
}
