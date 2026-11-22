package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"
	"time"
)

const baseURL = "http://127.0.0.1:7656"

// postJSON 用标准库 net/http 发送 POST + JSON 请求，返回 HTTP 状态码和响应体
func postJSON(path string, body map[string]any) (int, string) {
	// 1. 把参数序列化成 JSON 字节
	data, err := json.Marshal(body)
	if err != nil {
		return 0, "序列化请求体失败: " + err.Error()
	}

	// 2. 构造 POST 请求（第三个参数是 io.Reader，所以要用 bytes.NewReader 包一层）
	req, err := http.NewRequest(http.MethodPost, baseURL+path, bytes.NewReader(data))
	if err != nil {
		return 0, "构造请求失败: " + err.Error()
	}

	// 3. 设置请求头，告诉服务端发的是 JSON
	req.Header.Set("Content-Type", "application/json")

	// 4. 发送请求。自定义 Client 并设置超时，服务没启动时不会一直卡住
	client := &http.Client{Timeout: 5 * time.Second}
	resp, err := client.Do(req)
	if err != nil {
		return 0, "请求失败（服务启动了吗？）: " + err.Error()
	}
	defer resp.Body.Close() // 记得关闭响应体，否则连接无法复用

	// 5. 读取响应体
	b, err := io.ReadAll(resp.Body)
	if err != nil {
		return resp.StatusCode, "读取响应失败: " + err.Error()
	}
	return resp.StatusCode, string(b)
}

// postBadJSON 专门发一个非法 JSON，测试服务端对格式错误的兜底处理
func postBadJSON(path string) (int, string) {
	req, err := http.NewRequest(http.MethodPost, baseURL+path, strings.NewReader("{bad json"))
	if err != nil {
		return 0, "构造请求失败: " + err.Error()
	}
	req.Header.Set("Content-Type", "application/json")

	client := &http.Client{Timeout: 5 * time.Second}
	resp, err := client.Do(req)
	if err != nil {
		return 0, "请求失败（服务启动了吗？）: " + err.Error()
	}
	defer resp.Body.Close()

	b, _ := io.ReadAll(resp.Body)
	return resp.StatusCode, string(b)
}

func main() {
	// 用表驱动的方式组织测试场景：名称 + 路径 + 请求体
	cases := []struct {
		name string
		path string
		body map[string]any
	}{
		{"登录-缺用户名密码", "/login", map[string]any{}},
		{"登录-密码太短", "/login", map[string]any{"username": "query", "password": "123"}},
		{"登录-成功", "/login", map[string]any{"username": "query", "password": "123456"}},

		{"注册-两次密码不一致", "/register", map[string]any{
			"username": "query", "password": "123456", "confirm_password": "654321",
			"email": "q@qq.com", "phone": "13800138000", "age": 20,
		}},
		{"注册-邮箱格式错误", "/register", map[string]any{
			"username": "query", "password": "123456", "confirm_password": "123456",
			"email": "not-an-email", "phone": "13800138000", "age": 20,
		}},
		{"注册-手机号不是11位", "/register", map[string]any{
			"username": "query", "password": "123456", "confirm_password": "123456",
			"email": "q@qq.com", "phone": "138", "age": 20,
		}},
		{"注册-年龄超范围", "/register", map[string]any{
			"username": "query", "password": "123456", "confirm_password": "123456",
			"email": "q@qq.com", "phone": "13800138000", "age": 200,
		}},
		{"注册-成功", "/register", map[string]any{
			"username": "query", "password": "123456", "confirm_password": "123456",
			"email": "q@qq.com", "phone": "13800138000", "age": 20,
		}},
	}

	// 逐个场景发送请求并打印结果
	for i, c := range cases {
		code, body := postJSON(c.path, c.body)
		fmt.Printf("%d. %s\n   HTTP %d | %s\n\n", i+1, c.name, code, body)
	}

	// 额外测一个非法 JSON
	code, body := postBadJSON("/register")
	fmt.Printf("9. 注册-JSON格式非法\n   HTTP %d | %s\n", code, body)
}
