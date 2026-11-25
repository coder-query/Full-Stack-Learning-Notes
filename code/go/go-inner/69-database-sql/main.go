// 标准库 database/sql：SQL 通用接口（需单独注册驱动，如 mysql、sqlite）。
// 文档 https://studygolang.com/pkgdoc/pkg/database/sql/
package main

import (
	"database/sql"
	"fmt"
)

func main() {
	// 未安装驱动时 Open 不报错，首次 Ping/Query 才失败
	db, err := sql.Open("unknown-driver", "dsn")
	if err != nil {
		panic(err)
	}
	defer db.Close()

	if err := db.Ping(); err != nil {
		fmt.Println("Ping 失败（预期）：", err)
		fmt.Println("实战需：")
		fmt.Println(`  import _ "github.com/go-sql-driver/mysql"`)
		fmt.Println(`  db, _ := sql.Open("mysql", "user:pass@tcp(127.0.0.1:3306)/db")`)
		fmt.Println("  row := db.QueryRow(`SELECT 1`)")
		return
	}

	var n int
	if err := db.QueryRow("SELECT 1").Scan(&n); err != nil {
		panic(err)
	}
	fmt.Println(n)
}
