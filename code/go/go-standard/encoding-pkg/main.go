package main

import (
	"encoding/json"
	"fmt"
)

type Person struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func jsonMarshal(data interface{}) ([]byte, error) {
	fmt.Printf("jsonMarshal; %v", data)
	return json.Marshal(data)
}

func jsonUnmarshal(data []byte, v interface{}) error {
	fmt.Printf("jsonUnmarshal； %v", data)
	return json.Unmarshal(data, v)
}

func main() {
	person := Person{Name: "John", Age: 30}
	// json Marshal
	jsonSerialData, err := jsonMarshal(person)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	fmt.Println("JSON Data:", string(jsonSerialData))
	// json Unmarshal
	var newPerson Person
	err = jsonUnmarshal(jsonSerialData, &newPerson)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	fmt.Println("Unmarshaled Data:", newPerson)
}
