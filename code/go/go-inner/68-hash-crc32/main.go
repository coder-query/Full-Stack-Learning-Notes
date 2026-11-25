// 标准库 hash/crc32：CRC32 校验（ZIP、Ethernet 等）。文档 https://studygolang.com/pkgdoc/pkg/hash/crc32/
package main

import (
	"fmt"
	"hash/crc32"
)

func main() {
	table := crc32.MakeTable(crc32.IEEE)
	sum := crc32.Checksum([]byte("pkgdoc"), table)
	fmt.Printf("CRC32 IEEE: 0x%x\n", sum)

	h := crc32.NewIEEE()
	_, _ = h.Write([]byte("go-inner"))
	fmt.Printf("流式 CRC32: 0x%x\n", h.Sum32())
}
