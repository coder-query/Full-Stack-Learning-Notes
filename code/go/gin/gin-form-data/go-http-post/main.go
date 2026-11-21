package main

import (
	"fmt"
	"io"
	"net/http"
	"net/url"
)

func main() {
	var form_data url.Values = url.Values{
		"username": {"admin"},
		"password": {"admin123"},
		"age":      {"21"},
	}
	resp, err := http.PostForm("http://127.0.0.1:7675/user/add", form_data)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	defer func(Body io.ReadCloser) {
		err := Body.Close()
		if err != nil {
			fmt.Println("error:", err)
			return
		}
	}(resp.Body)
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("error:", err)
		return
	}
	fmt.Println(string(body))
}
