// 标准库 sync：并发原语。文档 https://studygolang.com/pkgdoc/pkg/sync/
package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {
	// Mutex：互斥锁
	var mu sync.Mutex
	var count int
	var wg sync.WaitGroup
	for i := 0; i < 5; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			mu.Lock()
			count++
			mu.Unlock()
		}()
	}
	wg.Wait()
	fmt.Println("Mutex count:", count)

	// Once：某段初始化只执行一次
	var once sync.Once
	for i := 0; i < 3; i++ {
		once.Do(func() {
			fmt.Println("Once 初始化")
		})
	}

	// atomic：无锁整数操作
	var n atomic.Int32
	for i := 0; i < 100; i++ {
		n.Add(1)
	}
	fmt.Println("atomic:", n.Load())
}
