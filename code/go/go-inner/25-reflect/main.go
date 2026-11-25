// 标准库 reflect：运行时反射类型与值。文档 https://studygolang.com/pkgdoc/pkg/reflect/
package main

import (
	"fmt"
	"reflect"
)

type Book struct {
	Title string `json:"title"`
	Pages int    `json:"pages"`
}

func main() {
	b := Book{Title: "Go 标准库", Pages: 300}
	t := reflect.TypeOf(b)
	v := reflect.ValueOf(b)

	fmt.Println("Type:", t.Name(), "Kind:", t.Kind())
	for i := 0; i < t.NumField(); i++ {
		f := t.Field(i)
		fmt.Printf("  字段 %s tag=%s 值=%v\n", f.Name, f.Tag.Get("json"), v.Field(i).Interface())
	}

	// 通过 reflect 修改（需传指针且字段可导出）
	ptr := reflect.ValueOf(&b)
	ptr.Elem().FieldByName("Pages").SetInt(320)
	fmt.Println("修改后:", b)
}
