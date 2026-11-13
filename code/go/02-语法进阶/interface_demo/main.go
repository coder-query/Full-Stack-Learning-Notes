package main

import "fmt"

type Sleeper interface {
	Sleep()
}

type Dog struct {
	Name string
}

func (d *Dog) Sleep() {
	fmt.Printf("%s is sleeping\n", d.Name)
}

type Cat struct {
	Name string
}

func (c *Cat) Sleep() {
	fmt.Printf("%s is sleeping\n", c.Name)
}

func AnimalSleep(sleeper Sleeper) {
	sleeper.Sleep()
}

func main() {
	var sleeper Sleeper
	dog := Dog{Name: "dog"}
	cat := Cat{Name: "cat"}
	sleeper = &dog
	AnimalSleep(sleeper)
	sleeper = &cat
	AnimalSleep(sleeper)
}
