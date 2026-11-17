package main

import "fmt"

type Person struct {
	name string
	age  int
}
type Student struct {
	//第一种嵌套方式
	person Person
	score  float32
}

func main() {
	// 创建一个 Student 实例
	var stu01 = Student{
		person: Person{
			name: "张三",
			age:  18,
		},
		score: 90.5,
	}
	fmt.Print(stu01)
}
