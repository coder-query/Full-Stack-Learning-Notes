package main

import (
	"fmt"
	"reflect"
)

// 基本数据类型的反射
func testBaseReflect(i interface{}) {
	typeOf := reflect.TypeOf(i)
	fmt.Println("typeOf: ", typeOf)
	valueOf := reflect.ValueOf(i)
	fmt.Println("valueOf: ", valueOf)
}

func main() {
	testBaseReflect(1)
}
