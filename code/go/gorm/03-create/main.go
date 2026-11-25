// 文档：创建 — https://gorm.io/zh_CN/docs/create.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name   string
	Age    int
	Active bool
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{})

	u := User{Name: "Jinzhu", Age: 18}
	res := db.Create(&u)
	fmt.Println("Create 行数:", res.RowsAffected, "ID:", u.ID)

	users := []User{
		{Name: "A", Age: 20},
		{Name: "B", Age: 21},
	}
	db.Create(&users)
	fmt.Println("批量 ID:", users[0].ID, users[1].ID)

	// 只插入指定列
	db.Select("Name", "Age").Create(&User{Name: "Partial", Age: 30, Active: true})

	// 跳过 Age 列（零值不写入）
	db.Omit("Age").Create(&User{Name: "NoAge"})
	fmt.Println("创建示例完成")
}
