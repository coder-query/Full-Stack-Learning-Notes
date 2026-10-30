package main

import "fmt"

func main() {

	var coursesMap map[string]string = make(map[string]string, 3)
	coursesMap["course1"] = "go"
	coursesMap["course2"] = "grpc"
	coursesMap["course3"] = "gin"

	if val, ok := coursesMap["java"]; ok {
		fmt.Println("java exists, val:", val)
	} else {
		fmt.Println("java not exists")
	}
	fmt.Println(coursesMap)
	delete(coursesMap, "course1")
	fmt.Println(coursesMap)
}
