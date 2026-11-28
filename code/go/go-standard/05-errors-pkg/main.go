package main

import (
	"errors"
	"fmt"
)

func main() {
	if err := errors.New("错误信息"); err != nil {
		fmt.Print("1111")
		return
	}

}
