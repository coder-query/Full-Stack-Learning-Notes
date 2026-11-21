package main

import (
	"context"
	"database/sql"
	"fmt"
	"gorm-quickstart/gorm_init_mysql"
	"time"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name           string         `gorm:"type:varchar(255)"`
	Email          sql.NullString `gorm:"type:varchar(255)"`
	Age            uint8          `gorm:"type:int"`
	Birthday       *time.Time     `gorm:"type:datetime"`
	IdentityNumber sql.NullString `gorm:"type:varchar(255)"`
}

func main() {
	// Go 不允许对函数返回值直接取地址，需先赋给变量再取地址
	birthday := time.Date(2000, 1, 1, 0, 0, 0, 0, time.Local)
	user := User{
		Name:           "Jinzhu",
		Email:          sql.NullString{String: "2798679648@qq.com", Valid: true},
		Age:            18,
		Birthday:       &birthday,
		IdentityNumber: sql.NullString{String: "1234567890", Valid: true},
	}
	ctx := context.Background()
	db := gorm_init_mysql.GetMysqlDBConnection()
	// 建表
	err := db.AutoMigrate(&User{})
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	// WithResult 必须在执行前挂到 gorm.G 上，执行后自动回填结果
	r := gorm.WithResult()
	err = gorm.G[User](db, r).Create(ctx, &user)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println("影响行数:", r.RowsAffected)
	// sql.Result 是接口，必须调方法取值，直接打印只会显示内部指针地址
	lastInsertID, _ := r.Result.LastInsertId()
	rowsAffected, _ := r.Result.RowsAffected()
	fmt.Println("LastInsertId:", lastInsertID)
	fmt.Println("RowsAffected(sql.Result):", rowsAffected)
	fmt.Println("r.Error:", r.Error)
	fmt.Println("user:", user)

	// 批量新增
	var users = []User{{Name: "jinzhu1"}, {Name: "jinzhu2"}, {Name: "jinzhu3"}}
	//db.CreateInBatches(&users, 100)
	db.Create(&users)
	for _, user := range users {
		fmt.Println(user.ID)
	}

}
