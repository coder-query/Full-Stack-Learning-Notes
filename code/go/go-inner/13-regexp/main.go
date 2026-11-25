// 标准库 regexp：正则表达式。文档 https://studygolang.com/pkgdoc/pkg/regexp/
package main

import (
	"fmt"
	"regexp"
)

func main() {
	// MustCompile：模式非法时 panic，适合包级变量
	re := regexp.MustCompile(`go(\d+)\.(\d+)`)
	text := "当前版本 go1.26.10"

	fmt.Println("MatchString:", re.MatchString(text))
	fmt.Println("FindString:", re.FindString(text))
	fmt.Println("FindStringSubmatch:", re.FindStringSubmatch(text))

	// FindAllString：所有匹配
	line := "pkg fmt, pkg strings, pkg fmt"
	all := regexp.MustCompile(`pkg (\w+)`).FindAllStringSubmatch(line, -1)
	for _, m := range all {
		fmt.Println("捕获:", m[1])
	}

	// ReplaceAllString
	fmt.Println(regexp.MustCompile(`\s+`).ReplaceAllString("a  b\tc", " "))
}
