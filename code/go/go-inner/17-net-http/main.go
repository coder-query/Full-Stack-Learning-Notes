// 标准库 net/http：HTTP 客户端与服务端（原生 API）。
// 测试/调试配套：httptest → 47，httputil → 46，cookie → 48，multipart → 49。
// 文档 https://studygolang.com/pkgdoc/pkg/net/http/
package main

import (
	"fmt"
	"io"
	"net"
	"net/http"
	"time"
)

func main() {
	// 本机临时启动 HTTP 服务（不依赖 httptest 包，但生产/单测更常用 httptest）
	ln := must(net.Listen("tcp", "127.0.0.1:0"))
	srv := &http.Server{
		Handler: http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			fmt.Fprintf(w, "method=%s path=%s", r.Method, r.URL.Path)
		}),
	}
	go func() { _ = srv.Serve(ln) }()
	defer srv.Close()

	base := "http://" + ln.Addr().String()

	client := &http.Client{Timeout: 3 * time.Second}
	resp, err := client.Get(base + "/pkgdoc")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Println("Get 响应:", string(body), "状态:", resp.Status)

	req, _ := http.NewRequest(http.MethodPost, base+"/echo", nil)
	resp2, err := client.Do(req)
	if err != nil {
		panic(err)
	}
	defer resp2.Body.Close()
	b2, _ := io.ReadAll(resp2.Body)
	fmt.Println("Post 响应:", string(b2))
}

func must[T any](v T, err error) T {
	if err != nil {
		panic(err)
	}
	return v
}
