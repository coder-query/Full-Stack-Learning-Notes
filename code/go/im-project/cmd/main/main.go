package main

import (
	"im-project/internal/web"
	"net/http"
)

func main() {
	http.HandleFunc("/login", web.UserLogin)
	// 启动 HTTP 服务，监听 8020 端口
	//
	if err := http.ListenAndServe(":8020", nil); err != nil {
		panic(err)
	}
}
