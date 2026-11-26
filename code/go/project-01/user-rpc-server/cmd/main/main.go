package main

import (
	"project-01/user-rpc-server/internal/db"
	"project-01/user-rpc-server/internal/domain/test"
	"project-01/user-rpc-server/internal/domain/user"
)

func main() {
	// 初始化db连接
	mysqlConnection := db.GetMysqlConnection()
	err := mysqlConnection.AutoMigrate(&user.User{}, &test.Test{})
	if err != nil {
		return
	}
}
