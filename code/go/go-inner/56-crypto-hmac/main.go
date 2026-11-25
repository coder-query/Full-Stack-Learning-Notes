// 标准库 crypto/hmac：带密钥的消息认证码。文档 https://studygolang.com/pkgdoc/pkg/crypto/hmac/
package main

import (
	"crypto/hmac"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
)

func main() {
	secret := []byte("studygolang-pkgdoc")
	body := []byte(`{"user":"demo"}`)

	mac := hmac.New(sha256.New, secret)
	mac.Write(body)
	sig := mac.Sum(nil)
	fmt.Println("HMAC-SHA256:", hex.EncodeToString(sig))

	// 校验：用 ConstantTimeCompare 防时序攻击
	mac2 := hmac.New(sha256.New, secret)
	mac2.Write(body)
	ok := hmac.Equal(sig, mac2.Sum(nil))
	fmt.Println("校验通过:", ok)
}
