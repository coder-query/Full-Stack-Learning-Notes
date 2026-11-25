// 标准库 crypto/tls：TLS 配置（配 net/http、net.Conn）。文档 https://studygolang.com/pkgdoc/pkg/crypto/tls/
package main

import (
	"crypto/tls"
	"fmt"
	"net/http"
	"net/http/httptest"
)

func main() {
	// 测试服：Go 自带 localhost 测试证书
	srv := httptest.NewUnstartedServer(http.HandlerFunc(func(w http.ResponseWriter, _ *http.Request) {
		fmt.Fprintln(w, "tls ok")
	}))
	srv.EnableHTTP2 = false
	srv.StartTLS()
	defer srv.Close()

	client := &http.Client{
		Transport: &http.Transport{
			TLSClientConfig: &tls.Config{
				MinVersion: tls.VersionTLS12,
				// 测试证书：正式环境应校验服务端证书
				InsecureSkipVerify: true,
			},
		},
	}
	resp, err := client.Get(srv.URL)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	fmt.Println("HTTPS 状态:", resp.Status, "TLS:", resp.TLS.Version)
}
