package main

import (
	"bytes"
	"errors"
	"io"
	"log"
	"strings"
)

func main() {
	strReader := strings.NewReader("张ss")
	byteReader := bytes.NewReader([]byte("abcdef"))
	limitReader1 := io.LimitReader(strReader, 3)
	limitReader2 := io.LimitReader(byteReader, 3)

	buf := make([]byte, 10)

	n1, err := limitReader1.Read(buf)
	if err != nil && err != io.EOF {
		panic(err)
	}
	log.Printf("n1=%d data=%q", n1, buf[:n1]) // n1=3 data="张"

	// 用新缓冲区，或用 buf[:n1] 的副本，否则会被覆盖
	buf2 := make([]byte, 10)
	n2, err := limitReader2.Read(buf2)
	if err != nil && errors.Is(err, io.EOF) {
		panic(err)
	}
	log.Printf("n2=%d data=%q", n2, buf2[:n2]) // n2=3 data="abc"
}
