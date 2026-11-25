// 文档：事务 — https://gorm.io/zh_CN/docs/transactions.html
package main

import (
	"errors"
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type Account struct {
	gorm.Model
	Name    string
	Balance int
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	_ = db.AutoMigrate(&Account{})
	db.Create(&Account{Name: "A", Balance: 100})
	db.Create(&Account{Name: "B", Balance: 0})

	err = db.Transaction(func(tx *gorm.DB) error {
		if err := tx.Model(&Account{}).Where("name = ?", "A").Update("balance", gorm.Expr("balance - ?", 50)).Error; err != nil {
			return err
		}
		if err := tx.Model(&Account{}).Where("name = ?", "B").Update("balance", gorm.Expr("balance + ?", 50)).Error; err != nil {
			return err
		}
		return errors.New("演示回滚")
	})
	fmt.Println("事务 error (应回滚):", err)

	var a, b Account
	db.Where("name = ?", "A").First(&a)
	db.Where("name = ?", "B").First(&b)
	fmt.Println("回滚后 A:", a.Balance, "B:", b.Balance)

	_ = db.Transaction(func(tx *gorm.DB) error {
		tx.Model(&Account{}).Where("name = ?", "A").Update("balance", gorm.Expr("balance - ?", 30))
		tx.Model(&Account{}).Where("name = ?", "B").Update("balance", gorm.Expr("balance + ?", 30))
		return nil
	})
	db.Where("name = ?", "A").First(&a)
	db.Where("name = ?", "B").First(&b)
	fmt.Println("成功事务 A:", a.Balance, "B:", b.Balance)
}
