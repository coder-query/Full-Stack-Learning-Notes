package main

import "fmt"

type Person interface {
	Say(str string) string
	Walk()
}

func main() {
	var person Person
	fmt.Println(person)
}
