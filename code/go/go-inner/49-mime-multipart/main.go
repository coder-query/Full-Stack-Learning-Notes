// 标准库 mime/multipart：HTTP 文件上传、multipart/form-data（常与 net/http 搭配）。
// 文档 https://studygolang.com/pkgdoc/pkg/mime/multipart/
package main

import (
	"bytes"
	"fmt"
	"io"
	"mime/multipart"
	"net/http"
	"net/http/httptest"
)

func main() {
	// 构造 multipart 请求体
	var body bytes.Buffer
	w := multipart.NewWriter(&body)
	_ = w.WriteField("username", "demo")
	fw, _ := w.CreateFormFile("avatar", "avatar.txt")
	_, _ = fw.Write([]byte("fake image bytes"))
	_ = w.Close()

	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if err := r.ParseMultipartForm(1 << 20); err != nil {
			http.Error(w, err.Error(), 400)
			return
		}
		fmt.Fprintf(w, "user=%s file=%v",
			r.FormValue("username"),
			r.MultipartForm.File["avatar"][0].Filename)
	}))
	defer srv.Close()

	req, _ := http.NewRequest(http.MethodPost, srv.URL+"/upload", &body)
	req.Header.Set("Content-Type", w.FormDataContentType())
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	out, _ := io.ReadAll(resp.Body)
	fmt.Println(string(out))
}
