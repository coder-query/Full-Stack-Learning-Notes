// 文档：快速入门 泛型 API (>= v1.30) — https://gorm.io/zh_CN/docs/index.html
package main

import (
	"context"
	"fmt"

	"gorm-study/gormutil"

	"gorm.io/gorm"
)

type Product struct {
	gorm.Model
	Code  string
	Price uint
}

func main() {
	db, err := gormutil.OpenMemory()
	if err != nil {
		panic(err)
	}
	ctx := context.Background()
	_ = db.AutoMigrate(&Product{})

	p := &Product{Code: "D42", Price: 100}
	if err := gorm.G[Product](db).Create(ctx, p); err != nil {
		panic(err)
	}

	got, err := gorm.G[Product](db).Where("id = ?", p.ID).First(ctx)
	if err != nil {
		panic(err)
	}
	fmt.Println("泛型 First:", got.Code, got.Price)

	if _, err := gorm.G[Product](db).Where("id = ?", p.ID).Update(ctx, "Price", 200); err != nil {
		panic(err)
	}
	got, _ = gorm.G[Product](db).Where("id = ?", p.ID).First(ctx)
	fmt.Println("泛型 Update 后 Price:", got.Price)

	if _, err := gorm.G[Product](db).Where("id = ?", p.ID).Delete(ctx); err != nil {
		panic(err)
	}
	fmt.Println("泛型 Delete 完成")
}
