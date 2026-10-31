package main

import "fmt"

func main() {
	var coursesMap map[string]string = make(map[string]string, 3)
	coursesMap["course1"] = "go"
	coursesMap["course2"] = "grpc"
	coursesMap["course3"] = "gin"
	// 1. 遍历map，获取key和value
	for key, value := range coursesMap {
		fmt.Println("key:", key, "value:", value)
	}
	// 2. 遍历map，获取key
	for key := range coursesMap {
		fmt.Printf("key: %s val: %s\n", key, coursesMap[key])
	}
	// 3. 遍历map，获取value
	for _, value := range coursesMap {
		fmt.Println("value:", value)
	}
}
