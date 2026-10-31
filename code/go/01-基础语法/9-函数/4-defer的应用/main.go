package main

import (
	"fmt"
	"sync"
)

func main() {
	// defer 类似java的finally
	//连接数据库、打开文件、开始锁，无论如何 最后都要记得去关闭数据库、关闭文件、解锁
	var mu sync.Mutex
	mu.Lock()
	defer mu.Unlock() //defer后面的代码是会放在函数return之前执行
	defer fmt.Println("defer1111")
	defer fmt.Println("defer2222")
	fmt.Println("123131")
	ret := deferReturn()
	fmt.Printf("ret = %d\r\n", ret)
}
func deferReturn() (ret int) {
	defer func() {
		ret++
	}()
	return 10
}
