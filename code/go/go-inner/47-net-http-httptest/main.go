// 标准库 net/http/httptest：为 net/http 提供测试用 Server / RequestRecorder（不测外网）。
// 文档 https://studygolang.com/pkgdoc/pkg/net/http/httptest/
package main

import (
	"fmt"
	"io"
	"net/http"
	"net/http/httptest"
)

func main() {
	// NewServer：内存监听，返回可用的 http.Client 基地址
	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "path=%s", r.URL.Path)
	}))
	defer srv.Close()
	fmt.Println("测试服 URL:", srv.URL)

	resp, err := http.Get(srv.URL + "/pkgdoc")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	body, _ := io.ReadAll(resp.Body)
	fmt.Println("Get:", string(body))

	// NewRequest + NewRecorder：不真正发网，只测 Handler
	h := http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(http.StatusTeapot)
		fmt.Fprintf(w, "hello %s", r.URL.Query().Get("name"))
	})
	req := httptest.NewRequest(http.MethodGet, "/?name=Go", nil)
	rec := httptest.NewRecorder()
	h.ServeHTTP(rec, req)
	fmt.Println("Recorder Status:", rec.Code)
	fmt.Println("Recorder Body:", rec.Body.String())
}
