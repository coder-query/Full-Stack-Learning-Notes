package main

import (
	"bytes"
	"fmt"
	"log"
)

func main() {
	var strBytes1 []byte = []byte("1314")
	var strBytes2 []byte = []byte("1212张")
	fmt.Print(bytes.Compare(strBytes1, strBytes2))

	// buffer
	buf := bytes.NewBufferString("abc张帅宏 cba宏帅张")
	// b, err := buf.ReadByte()
	// if err != nil {
	// 	panic(err)
	// }
	// for i := 0; i < 3; i++ {
	// 	r, size, err2 := buf.ReadRune()
	// 	if err2 != nil {
	// 		panic(err2)
	// 	}
	// 	log.Printf("读取一个字符rune: %v size:%v", string(r), size)
	// }
	line, err := buf.ReadString(' ')
	if err != nil {
		panic(err)
	}
	log.Printf("line: %v", line)
	// line, err3 := buf.ReadString(3)
	// if err3 != nil {
	// 	panic(err)
	// }
	// log.Printf("读取一个字符ReadString: %v ", line)

	log.Printf("最后buffer缓冲区中还剩下的内容为：%v 剩余数据len长度：%d, buffercap容量：%d", buf.String(), buf.Len(), buf.Cap())

}
