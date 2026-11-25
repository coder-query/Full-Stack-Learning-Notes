// 文档：Hook — https://gorm.io/zh_CN/docs/hooks.html
package main

import (
	"fmt"
	"strings"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	Name string
}

func (u *User) BeforeCreate(tx *gorm.DB) error {
	u.Name = strings.TrimSpace(u.Name)
	if u.Name == "" {
		return fmt.Errorf("name 不能为空")
	}
	fmt.Println("Hook BeforeCreate:", u.Name)
	return nil
}

func (u *User) AfterCreate(tx *gorm.DB) error {
	fmt.Println("Hook AfterCreate ID:", u.ID)
	return nil
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&User{})

	if err := db.Create(&User{Name: "  go  "}).Error; err != nil {
		fmt.Println(err)
	}
	if err := db.Session(&gorm.Session{SkipHooks: true}).Create(&User{Name: ""}).Error; err != nil {
		fmt.Println("SkipHooks 允许空名:", err)
	}
}
