// 标准库 crypto/md5：MD5 摘要（校验用，不要用于密码）。文档 https://studygolang.com/pkgdoc/pkg/crypto/md5/
package main

import (
	"crypto/md5"
	"encoding/hex"
	"fmt"
)

func main() {
	sum := md5.Sum([]byte("pkgdoc"))
	fmt.Println(hex.EncodeToString(sum[:]))

	h := md5.New()
	_, _ = h.Write([]byte("part"))
	fmt.Println(hex.EncodeToString(h.Sum(nil)))
}
