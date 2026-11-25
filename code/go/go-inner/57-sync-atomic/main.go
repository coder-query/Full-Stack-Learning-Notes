// 标准库 sync/atomic：原子操作（计数器、标志位）。文档 https://studygolang.com/pkgdoc/pkg/sync/atomic/
// 复杂互斥仍用 sync.Mutex（见 16-sync）。
package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {
	var counter atomic.Uint64
	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			counter.Add(1)
		}()
	}
	wg.Wait()
	fmt.Println("counter:", counter.Load())

	var flag atomic.Bool
	flag.Store(true)
	fmt.Println("flag:", flag.Load())
}
