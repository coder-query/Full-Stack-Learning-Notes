package main

import "fmt"

type LoginDTO struct {
	username    string
	password    string
	captchaCode string
}

func main() {
	var loginDTO = LoginDTO{
		username:    "admin",
		password:    "123456",
		captchaCode: "1234",
	}
	fmt.Print(loginDTO)

}
