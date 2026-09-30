package main

import "fmt"

func auoIncrement() func() int {
	local := 0 //一个函数中访问另一个函数的局部变量 不行的 闭包return func() int{
	return func() int {
		local += 1
		return local
	}
}

func main() {
	inc := auoIncrement()
	for i := 1; i <= 10; i++ {
		fmt.Println(inc())
	}
}
