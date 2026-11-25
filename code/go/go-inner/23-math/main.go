// 标准库 math：数学函数与常量。文档 https://studygolang.com/pkgdoc/pkg/math/
package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("Pi:", math.Pi)
	fmt.Println("Sqrt(2):", math.Sqrt(2))
	fmt.Println("Pow(2,10):", math.Pow(2, 10))
	fmt.Println("Round(2.6):", math.Round(2.6))
	fmt.Println("Max:", math.Max(3, 7), "Min:", math.Min(3, 7))

	// Mod：取余，符号与除数一致
	fmt.Println("Mod:", math.Mod(7, 3))

	// 判断特殊浮点
	x := math.Sqrt(-1)
	fmt.Println("IsNaN:", math.IsNaN(x))
}
