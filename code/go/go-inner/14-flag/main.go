// 标准库 flag：命令行参数解析。文档 https://studygolang.com/pkgdoc/pkg/flag/
// 运行：go run . -name=Go -verbose
package main

import (
	"flag"
	"fmt"
)

func main() {
	// 定义参数（须在用 flag.Parse 之前）
	name := flag.String("name", "world", "问候对象")
	verbose := flag.Bool("verbose", false, "打印详细信息")

	flag.Parse()

	fmt.Printf("Hello, %s\n", *name)

	// Parse 之后，剩余非 flag 参数在 flag.Args()
	if *verbose {
		fmt.Println("verbose=true")
		fmt.Println("其余参数:", flag.Args())
	}
}
