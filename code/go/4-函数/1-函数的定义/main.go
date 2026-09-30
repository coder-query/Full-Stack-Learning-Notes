package main

import "fmt"

func addNum(a int, b int) (int, error) {
	return a + b, nil

}

func addNum2(a int, b int) (sum int, err error) {
	sum = a + b
	return sum, err
}

func main() {
	/**
	func 函数名([参数列表]) [返回值] {
	  函数体
	}
	*/
	res, err := addNum(1, 2)
	if err == nil {
		fmt.Println(res)
	}

	res2, err2 := addNum2(1, 2)
	if err2 == nil {
		fmt.Println(res2)
	}

}
