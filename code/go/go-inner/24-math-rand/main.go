// 标准库 math/rand/v2：伪随机数（Go 1.22+ 推荐 v2）。文档 https://studygolang.com/pkgdoc/pkg/math/rand/
package main

import (
	"fmt"
	"math/rand/v2"
)

func main() {
	// 全局随机源（自动播种）
	fmt.Println("IntN:", rand.IntN(100))
	fmt.Println("Float64:", rand.Float64())
	fmt.Println("Shuffle:", shuffleDemo())

	// 可复现实验：固定种子
	r := rand.New(rand.NewPCG(1, 2))
	fmt.Println("PCG 固定种子:", r.IntN(1000), r.IntN(1000))
}

func shuffleDemo() []int {
	s := []int{1, 2, 3, 4, 5}
	rand.Shuffle(len(s), func(i, j int) { s[i], s[j] = s[j], s[i] })
	return s
}
