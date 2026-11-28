package web

import (
	"encoding/json"
	"fmt"
	"im-project/internal/model"
	"net/http"
)

const (
	DefaultUserName = "zsh"
	DefaultPassword = "123456"
)

func UserLogin(w http.ResponseWriter, r *http.Request) {
	// 处理用户登录请求
	err := r.ParseForm()
	if err != nil {
		http.Error(w, "Failed to parse form", http.StatusBadRequest)
		return
	}
	username := r.PostForm.Get("username")
	password := r.PostForm.Get("	")
	// 进一步处理登录逻辑
	var loginSuccess bool
	if username == DefaultUserName && password == DefaultPassword {
		loginSuccess = true
	}
	if loginSuccess {
		fmt.Println("Login successful")
		jsonSerialData, err := json.Marshal(model.CustomHttpRes{
			Code: http.StatusOK,
			Msg:  "Login successful",
			Data: nil,
		})
		if err != nil {
			http.Error(w, "Failed to marshal response", http.StatusInternalServerError)
			return
		}
		w.WriteHeader(http.StatusOK)
		w.Write(jsonSerialData)
		return
	}
	fmt.Println("Login failed")
	jsonSerialData, err := json.Marshal(model.CustomHttpRes{
		Code: http.StatusUnauthorized,
		Msg:  fmt.Sprintf("Login failed: invalid username or password, username: %s, password: %s", username, password),
		Data: nil,
	})
	if err != nil {
		http.Error(w, "Failed to marshal response", http.StatusInternalServerError)
		return
	}
	w.WriteHeader(http.StatusUnauthorized)
	w.Write(jsonSerialData)
}
