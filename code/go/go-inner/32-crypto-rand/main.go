// 标准库 crypto/rand：密码学安全随机数。文档 https://studygolang.com/pkgdoc/pkg/crypto/rand/
// 生成 token、密钥时用；不要用 math/rand。
package main

import (
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"io"
)

func main() {
	b := make([]byte, 16)
	if _, err := rand.Read(b); err != nil {
		panic(err)
	}
	fmt.Println("随机字节 hex:", hex.EncodeToString(b))

	// io.Reader 用法
	small := make([]byte, 4)
	if _, err := io.ReadFull(rand.Reader, small); err != nil {
		panic(err)
	}
	fmt.Println("ReadFull:", hex.EncodeToString(small))
}
