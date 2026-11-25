// 标准库 math/big：大整数、大浮点。文档 https://studygolang.com/pkgdoc/pkg/math/big/
package main

import (
	"fmt"
	"math/big"
)

func main() {
	a := new(big.Int).SetInt64(1)
	a.Lsh(a, 100) // 2^100
	fmt.Println("2^100 位数:", a.BitLen())

	b := new(big.Int)
	b.SetString("100000000000000000000", 10)
	sum := new(big.Int).Add(a, b)
	fmt.Println("相加:", sum.String()[:20]+"...")

	rat := new(big.Rat).SetFrac64(22, 7)
	f, _ := rat.Float64()
	fmt.Println("22/7 ≈", f)
}
