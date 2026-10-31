// c语言：i++, i--, --i, ++i
//
// go语言：i++, i--， 没有--i，++i， 自增语法必须单独一行

package main

import "fmt"

func main() {
	i := 20
	i++
	//++i //这个也是错的， 语义更加明确
	//fmt.Println("i:", i++)  //这是错误的，不允许和其他代码放在一起，必须单独一行
	fmt.Println("i:", i)
}
