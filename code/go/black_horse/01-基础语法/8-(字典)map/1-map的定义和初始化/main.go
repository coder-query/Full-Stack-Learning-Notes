package main

import "fmt"

func main() {
	var coursesMap map[string]string = make(map[string]string, 3)
	coursesMap["course1"] = "go"
	coursesMap["course2"] = "grpc"
	coursesMap["course3"] = "gin"
	fmt.Println(coursesMap)
	var ageMap = map[string]int{
		"张三": 18,
		"李四": 20,
		"王五": 22,
	}
	fmt.Println(ageMap)
}
