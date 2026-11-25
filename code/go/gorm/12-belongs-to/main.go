// 文档：Belongs To — https://gorm.io/zh_CN/docs/belongs_to.html
package main

import (
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type Company struct {
	gorm.Model
	Name string
}

type Employee struct {
	gorm.Model
	Name      string
	CompanyID uint
	Company   Company
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&Company{}, &Employee{})

	c := Company{Name: "GORM Inc"}
	db.Create(&c)
	e := Employee{Name: "Dev", CompanyID: c.ID}
	db.Create(&e)

	var emp Employee
	db.Preload("Company").First(&emp, e.ID)
	fmt.Println("员工", emp.Name, "公司", emp.Company.Name)
}
