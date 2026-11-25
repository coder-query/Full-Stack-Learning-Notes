// 标准库 container/list：双向链表。文档 https://studygolang.com/pkgdoc/pkg/container/list/
package main

import (
	"container/list"
	"fmt"
)

func main() {
	l := list.New()
	l.PushBack("fmt")
	l.PushFront("io")
	l.PushBack("http")

	for e := l.Front(); e != nil; e = e.Next() {
		fmt.Println(e.Value)
	}

	// 移除中间元素
	mid := l.Back()
	l.Remove(mid)
	fmt.Println("移除 http 后:")
	for e := l.Front(); e != nil; e = e.Next() {
		fmt.Println(e.Value)
	}
}
