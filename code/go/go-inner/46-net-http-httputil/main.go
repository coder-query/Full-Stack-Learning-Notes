// 标准库 net/http/httputil：围绕 net/http 的调试与反向代理等工具。
// 对照原生 HTTP：见 17-net-http。
// 文档 https://studygolang.com/pkgdoc/pkg/net/http/httputil/
package main

import (
	"fmt"
	"io"
	"net/http"
	"net/http/httptest"
	"net/http/httputil"
	"net/url"
	"strings"
)

func main() {
	// DumpRequest：把请求按 HTTP 报文格式打印（调试抓包）
	req, err := http.NewRequest(http.MethodPost, "http://example.com/pkgdoc",
		strings.NewReader(`{"pkg":"fmt"}`))
	if err != nil {
		panic(err)
	}
	req.Header.Set("Content-Type", "application/json")
	dump, err := httputil.DumpRequest(req, true)
	if err != nil {
		panic(err)
	}
	fmt.Println("=== DumpRequest ===")
	fmt.Println(string(dump))

	// DumpResponse：需要先有 *http.Response
	ts := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, _ *http.Request) {
		w.Header().Set("X-Demo", "httputil")
		fmt.Fprintln(w, "body")
	}))
	defer ts.Close()
	resp, err := http.Get(ts.URL)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	respDump, err := httputil.DumpResponse(resp, true)
	if err != nil {
		panic(err)
	}
	fmt.Println("=== DumpResponse ===")
	fmt.Println(string(respDump))

	// NewSingleHostReverseProxy：简易反向代理，把路径转到后端
	backend, _ := url.Parse(ts.URL)
	proxy := httputil.NewSingleHostReverseProxy(backend)
	frontend := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		r.URL.Path = "/proxied" + r.URL.Path
		proxy.ServeHTTP(w, r)
	}))
	defer frontend.Close()

	res, err := http.Get(frontend.URL + "/api")
	if err != nil {
		panic(err)
	}
	defer res.Body.Close()
	body, _ := io.ReadAll(res.Body)
	fmt.Println("ReverseProxy 响应:", string(body))
}
