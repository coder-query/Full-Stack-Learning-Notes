// 文档：预加载 — https://gorm.io/zh_CN/docs/preload.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name   string
	Orders []Order
}

type Order struct {
	gorm.Model
	UserID uint
	Price  float64
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{}, &Order{})

	u := User{Name: "Query"}
	db.Create(&u)
	db.Create([]Order{{UserID: u.ID, Price: 10}, {UserID: u.ID, Price: 20}})

	var users []User
	db.Preload("Orders").Find(&users)
	fmt.Printf("用户 %s 订单数 %d 首单价 %.0f\n", users[0].Name, len(users[0].Orders), users[0].Orders[0].Price)
}
