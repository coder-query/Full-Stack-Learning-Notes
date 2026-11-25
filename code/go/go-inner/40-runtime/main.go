// 标准库 runtime：运行时信息（GOMAXPROCS、内存等）。文档 https://studygolang.com/pkgdoc/pkg/runtime/
package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Println("GOOS/GOARCH:", runtime.GOOS, runtime.GOARCH)
	fmt.Println("NumCPU:", runtime.NumCPU())
	fmt.Println("NumGoroutine:", runtime.NumGoroutine())
	fmt.Println("Version:", runtime.Version())

	var ms runtime.MemStats
	runtime.ReadMemStats(&ms)
	fmt.Printf("Alloc=%dKB TotalAlloc=%dKB Sys=%dKB NumGC=%d\n",
		ms.Alloc/1024, ms.TotalAlloc/1024, ms.Sys/1024, ms.NumGC)
}
