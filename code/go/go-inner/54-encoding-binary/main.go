// 标准库 encoding/binary：字节序、结构体与流的二进制读写。文档 https://studygolang.com/pkgdoc/pkg/encoding/binary/
package main

import (
	"bytes"
	"encoding/binary"
	"fmt"
)

func main() {
	var buf bytes.Buffer
	// Write 按大端把数字写入
	_ = binary.Write(&buf, binary.BigEndian, uint16(0x1234))
	fmt.Println("BigEndian hex:", fmt.Sprintf("%x", buf.Bytes()))

	buf.Reset()
	_ = binary.Write(&buf, binary.LittleEndian, uint16(0x1234))
	fmt.Println("LittleEndian hex:", fmt.Sprintf("%x", buf.Bytes()))

	// Read 解析
	reader := bytes.NewReader([]byte{0x12, 0x34})
	var v uint16
	_ = binary.Read(reader, binary.BigEndian, &v)
	fmt.Printf("Read uint16: 0x%x\n", v)

	fmt.Println("Uvarint:", binary.PutUvarint(make([]byte, 10), 300))
}
