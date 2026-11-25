// 标准库 net/http/httptrace：跟踪 HTTP 客户端各阶段耗时。文档 https://studygolang.com/pkgdoc/pkg/net/http/httptrace/
package main

import (
	"context"
	"fmt"
	"net/http"
	"net/http/httptest"
	"net/http/httptrace"
	"time"
)

func main() {
	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, _ *http.Request) {
		time.Sleep(30 * time.Millisecond)
		w.WriteHeader(http.StatusOK)
	}))
	defer srv.Close()

	trace := &httptrace.ClientTrace{
		DNSStart:             func(_ httptrace.DNSStartInfo) { fmt.Println("trace: DNSStart") },
		GotConn:              func(_ httptrace.GotConnInfo) { fmt.Println("trace: GotConn") },
		WroteRequest:         func(_ httptrace.WroteRequestInfo) { fmt.Println("trace: WroteRequest") },
		GotFirstResponseByte: func() { fmt.Println("trace: FirstByte") },
	}
	req, _ := http.NewRequestWithContext(context.Background(), http.MethodGet, srv.URL, nil)
	req = req.WithContext(httptrace.WithClientTrace(req.Context(), trace))

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		panic(err)
	}
	resp.Body.Close()
	fmt.Println("完成", resp.StatusCode)
}
