package main

import "fmt"

func main() {

	//匿名结构体，匿名函数
	var person = struct {
		name string
		age  int
	}{
		name: "张xx",
		age:  18,
	}
	fmt.Println(person)

	score := struct {
		yvwen   float64
		math    float64
		english float64
	}{
		yvwen:   90,
		math:    80,
		english: 75,
	}
	fmt.Println(score)
}
