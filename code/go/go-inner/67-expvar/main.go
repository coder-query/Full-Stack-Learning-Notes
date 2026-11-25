// 标准库 expvar：通过 HTTP 暴露进程内指标（常挂在 /debug/vars）。文档 https://studygolang.com/pkgdoc/pkg/expvar/
package main

import (
	"expvar"
	"fmt"
	"net/http"
	"net/http/httptest"
)

func main() {
	counter := expvar.NewInt("hits")
	counter.Add(1)
	counter.Add(2)

	mux := http.NewServeMux()
	mux.Handle("/debug/vars", expvar.Handler())

	ts := httptest.NewServer(mux)
	defer ts.Close()

	resp, err := http.Get(ts.URL + "/debug/vars")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	fmt.Println("expvar HTTP:", resp.Status)
}
