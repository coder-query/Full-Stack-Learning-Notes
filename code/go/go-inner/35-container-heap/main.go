// 标准库 container/heap：最小堆/优先队列。文档 https://studygolang.com/pkgdoc/pkg/container/heap/
package main

import (
	"container/heap"
	"fmt"
)

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x any)        { *h = append(*h, x.(int)) }
func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func main() {
	h := &IntHeap{5, 3, 9, 1}
	heap.Init(h)
	heap.Push(h, 2)

	fmt.Println("堆顶(最小):", (*h)[0])
	for h.Len() > 0 {
		fmt.Println("Pop:", heap.Pop(h))
	}
}
