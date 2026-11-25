// 标准库 net/http/cookiejar：为 net/http.Client 管理 Cookie（自动携带 Set-Cookie）。
// 文档 https://studygolang.com/pkgdoc/pkg/net/http/cookiejar/
package main

import (
	"fmt"
	"net/http"
	"net/http/cookiejar"
	"net/http/httptest"
)

func main() {
	jar, err := cookiejar.New(nil)
	if err != nil {
		panic(err)
	}
	client := &http.Client{Jar: jar}

	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		switch r.URL.Path {
		case "/login":
			http.SetCookie(w, &http.Cookie{Name: "session", Value: "abc123", Path: "/"})
			fmt.Fprintln(w, "set cookie")
		case "/me":
			c, err := r.Cookie("session")
			if err != nil {
				http.Error(w, "no cookie", http.StatusUnauthorized)
				return
			}
			fmt.Fprintf(w, "session=%s", c.Value)
		default:
			fmt.Fprintln(w, "ok")
		}
	}))
	defer srv.Close()

	_, _ = client.Get(srv.URL + "/login")
	resp, err := client.Get(srv.URL + "/me")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	buf := make([]byte, 64)
	n, _ := resp.Body.Read(buf)
	fmt.Println("带 Jar 的第二次请求:", string(buf[:n]))
}
