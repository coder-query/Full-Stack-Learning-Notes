// 文档：更新 — https://gorm.io/zh_CN/docs/update.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name  string
	Email string
	Age   int
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{})
	u := User{Name: "old", Email: "a@b.com", Age: 1}
	db.Create(&u)

	db.Model(&u).Update("Age", 18)
	fmt.Println("Update 单字段 Age:", u.Age)

	db.Model(&u).Updates(User{Name: "new", Age: 20})
	fmt.Println("Updates 结构体:", u.Name, u.Age)

	db.Model(&u).Updates(map[string]any{"email": "x@y.com", "age": 22})
	var fresh User
	db.First(&fresh, u.ID)
	fmt.Println("Updates map:", fresh.Email, fresh.Age)

	// 只更新 age
	db.Model(&fresh).Select("Age").Updates(map[string]any{"name": "ignored", "age": 30})
	db.First(&fresh, u.ID)
	fmt.Println("Select Age 后 name 不变:", fresh.Name, "age:", fresh.Age)
}
