package main

import "fmt"

func add(items ...int) (sum int, err error) {
	for _, value := range items {
		sum += value
	}
	return sum, err
}

func main() {
	var a = 1
	var b = 2
	res, err := add(a, b, 3)
	if err == nil {
		fmt.Print(res)
	}
}
