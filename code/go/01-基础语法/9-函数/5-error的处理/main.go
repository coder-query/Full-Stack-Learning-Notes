package main

import (
	"errors"
	"fmt"
)

func A() (int, error) {
	return 0, errors.New("this is an error")
}

func main() {
	if _, err := A(); err != nil {
		fmt.Println(err)
	}
}
