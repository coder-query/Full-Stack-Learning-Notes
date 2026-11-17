package main

import (
	"fmt"
	"time"
)

func getDbData() {
	time.Sleep(time.Second * 3)
	fmt.Println("getDbData")
}

func main() {
	go getDbData()
	go getDbData()
	go getDbData()
	time.Sleep(time.Second * 5)
	fmt.Println("main")
}
