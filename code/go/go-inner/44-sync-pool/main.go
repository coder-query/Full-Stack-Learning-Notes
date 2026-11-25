// 标准库 sync：Pool 对象复用，减轻 GC。文档 https://studygolang.com/pkgdoc/pkg/sync/#Pool
package main

import (
	"fmt"
	"sync"
)

func main() {
	pool := sync.Pool{
		New: func() any {
			fmt.Println("Pool New")
			return make([]byte, 1024)
		},
	}

	buf := pool.Get().([]byte)
	fmt.Println("Get 长度:", len(buf))
	pool.Put(buf)

	buf2 := pool.Get().([]byte)
	fmt.Println("再次 Get 可能复用:", len(buf2) == 1024)
	pool.Put(buf2)
}
