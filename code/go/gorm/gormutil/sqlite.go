package gormutil

import (
	"os"
	"path/filepath"

	"github.com/glebarez/sqlite"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
)

// OpenFile 在系统临时目录创建独立 sqlite 文件，便于每个示例互不干扰。
// 文档：https://gorm.io/zh_CN/docs/connecting_to_the_database.html#SQLite
func OpenFile(exampleName string) (*gorm.DB, string, error) {
	path := filepath.Join(os.TempDir(), "gorm-study-"+exampleName+".db")
	_ = os.Remove(path)
	db, err := gorm.Open(sqlite.Open(path), &gorm.Config{
		Logger: logger.Default.LogMode(logger.Info),
	})
	return db, path, err
}

// OpenMemory 纯内存库，进程结束即消失。DSN: file::memory:?cache=shared
func OpenMemory() (*gorm.DB, error) {
	return gorm.Open(sqlite.Open("file::memory:?cache=shared"), &gorm.Config{
		Logger: logger.Default.LogMode(logger.Info),
	})
}
