package main

import (
	"fmt"
	"log"
)

func main() {
	defer fmt.Println("defer ...")
	log.Println("this is log")
	// log.Panic("this is panic log")
	log.Fatal("this is fatal")
	fmt.Printf("运行结束。。。")
}
