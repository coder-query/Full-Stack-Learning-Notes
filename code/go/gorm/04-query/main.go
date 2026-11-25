// 文档：查询 — https://gorm.io/zh_CN/docs/query.html
package main

import (
	"errors"
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name string
	Age  int
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{})
	db.Create([]User{{Name: "Tom", Age: 18}, {Name: "Jerry", Age: 20}, {Name: "Tom", Age: 25}})

	var one User
	if err := db.First(&one).Error; err != nil {
		panic(err)
	}
	fmt.Println("First:", one.Name)

	var list []User
	db.Where("age >= ?", 20).Order("age desc").Limit(10).Find(&list)
	fmt.Println("Where age>=20:", list)

	var cnt int64
	db.Model(&User{}).Where("name = ?", "Tom").Count(&cnt)
	fmt.Println("Tom 数量:", cnt)

	var names []string
	db.Model(&User{}).Pluck("name", &names)
	fmt.Println("Pluck names:", names)

	err = db.First(&User{}, 999).Error
	if errors.Is(err, gorm.ErrRecordNotFound) {
		fmt.Println("预期: ErrRecordNotFound")
	}
}
