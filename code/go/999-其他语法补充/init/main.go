package main

import (
	"fmt"
	"init-go-mod/utils"
)

func main() {
	var strRes string
	strRes = utils.SliceStr("hello world")
	fmt.Println(strRes)
}
