package main

import (
	"crypto/md5"
	"crypto/sha1"
	"crypto/sha256"
	"crypto/sha512"
	"encoding/hex"
	"fmt"
)

func main() {
	data := []byte("Hello, Go!")

	// MD5
	md5Hash := md5.Sum(data)
	fmt.Printf("MD5: %x\n", md5Hash)
	fmt.Printf("MD5: %s\n", hex.EncodeToString(md5Hash[:]))

	// SHA1
	sha1Hash := sha1.Sum(data)
	fmt.Printf("SHA1: %x\n", sha1Hash)

	// SHA256
	sha256Hash := sha256.Sum256(data)
	fmt.Printf("SHA256: %x\n", sha256Hash)

	// SHA512
	sha512Hash := sha512.Sum512(data)
	fmt.Printf("SHA512: %x\n", sha512Hash)
}
