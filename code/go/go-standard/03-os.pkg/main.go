package main

import (
	"encoding/json"
	"fmt"
	"io"
	"os"
)

// 创建文件
func TestCreateFile() {
	f, err := os.Create("test.txt")
	if err != nil {
		fmt.Println("Failed to create file:", err)
		panic(err)
	}
	defer func(f *os.File) {
		if err := f.Close(); err != nil {
			fmt.Println("Failed to close file:", err)
			panic(err)
		}
	}(f)
	fmt.Println("File created successfully:", f.Name())
}

// 创建文件夹
func TestCreateDir() {
	err := os.Mkdir("test-dir", os.ModePerm)
	if err != nil {
		fmt.Println("Failed to create directory:", err)
		panic(err)
	}
	fmt.Println("Directory created successfully:")
}

// 创建文件夹及其父目录
func TestCreateDirAll() {
	err := os.MkdirAll("test-dir-666/sub-dir", os.ModePerm)
	if err != nil {
		fmt.Println("Failed to create directory and its parents:", err)
		panic(err)
	}
	fmt.Println("Directory and its parents created successfully:")
}

// 删除文件或文件夹
func TestRemoveFileOrDir() {
	err := os.Remove("test.txt")
	if err != nil {
		fmt.Println("Failed to remove file:", err)
		panic(err)
	}
	fmt.Println("File removed successfully! file name: test.txt")
}

// 删除文件夹及其子目录
func TestRemoveDirAll() {
	dirSlice := []string{"test-dir", "test-dir-666"}
	for _, dir := range dirSlice {
		err := os.RemoveAll(dir)
		if err != nil {
			fmt.Println("Failed to remove directory and its contents:", err)
			panic(err)
		}
	}
	fmt.Println("Directory and its contents removed successfully!")
}

// 获取当前工作目录
func TestGetCurrentWorkingDir() {
	dir, err := os.Getwd()
	if err != nil {
		fmt.Println("Failed to get current working directory:", err)
		panic(err)
	}
	fmt.Println("Current working directory:", dir)
}

// 临时目录
func TestGetTempDir() {
	dir := os.TempDir()
	fmt.Println("Temporary directory:", dir)
}

// 重名名
func TestRenameFileOrDir() {
	err := os.Rename("test.txt", "test-renamed.txt")
	if err != nil {
		fmt.Println("Failed to rename file:", err)
		panic(err)
	}
	fmt.Println("File renamed successfully! new file name: test-renamed.txt")
}

// 修改文件权限
func TestChmod() {
	err := os.Chmod("test.txt", 0755)
	if err != nil {
		fmt.Println("Failed to change file permissions:", err)
		panic(err)
	}
	fmt.Println("File permissions changed successfully!")
}

// 修改文件的所有者和组
func TestChown() {
	err := os.Chown("test.txt", 1000, 1000)
	if err != nil {
		fmt.Println("Failed to change file owner and group:", err)
		panic(err)
	}
	fmt.Println("File owner and group changed successfully!")
}

// 文件的状态
func TestStatFile() {
	f, err := os.OpenFile("test.txt", os.O_RDONLY, os.ModePerm)
	if err != nil {
		fmt.Println("Failed to open file:", err)
		panic(err)
	}
	defer f.Close()

	info, err := f.Stat()
	fmt.Printf("file status: %+v\n", info)
	if err != nil {
		fmt.Println("Failed to get file status:", err)
		panic(err)
	}
	jsonSerialData, err := json.MarshalIndent(info, "", "  ")
	if err != nil {
		fmt.Println("Failed to marshal file info:", err)
		panic(err)
	}
	fmt.Println("File status:", string(jsonSerialData))
}

// 读取文件内容
func TestReadFile() {
	// 1. 打开文件
	f, err := os.Open("test.txt")
	if err != nil {
		fmt.Println("Failed to open file:", err)
		panic(err)
	}
	// 2. 关闭文件
	defer func(f *os.File) {
		if err := f.Close(); err != nil {
			fmt.Println("Failed to close file:", err)
			panic(err)
		}
	}(f)
	// 3. 读取文件内容（自己手动实现）
	// var fileContent []byte
	// buf := make([]byte, 1024)
	// for {
	// 	n, err := f.Read(buf)
	// 	if n > 0 {
	// 		fileContent = append(fileContent, buf[:n]...)
	// 	}
	// 	if err == io.EOF {
	// 		break
	// 	}
	// 	if err != nil {
	// 		fmt.Println("Failed to read file:", err)
	// 		panic(err)
	// 	}
	// }
	// 4. 读取文件内容（使用 ioutil.ReadAll）
	fileContent, err := io.ReadAll(f)
	if err != nil {
		fmt.Println("Failed to read file:", err)
		panic(err)
	}
	fmt.Println("File content:", string(fileContent))
}

// 写入文件内容
func TestWriteFile() {
	// 1. 打开文件
	f, err := os.OpenFile("test.txt", os.O_RDWR|os.O_APPEND, os.ModePerm)
	if err != nil {
		fmt.Println("Failed to open file:", err)
		panic(err)
	}
	// 2. 关闭文件
	defer func(f *os.File) {
		if err := f.Close(); err != nil {
			fmt.Println("Failed to close file:", err)
			panic(err)
		}
	}(f)
	// 3. 写入文件内容
	content := "Hello, World!"
	n, err := f.WriteString(content)
	if err != nil {
		fmt.Println("Failed to write to file:", err)
		panic(err)
	}
	fmt.Printf("Wrote %d bytes to file\n", n)
}

func main() {
	// // 创建文件
	// TestCreateFile()
	// // 创建文件夹
	// TestCreateDir()
	// // 创建文件夹及其父目录
	// TestCreateDirAll()

	// // 删除文件或文件夹
	// TestRemoveFileOrDir()
	// // 删除文件夹及其子目录
	// TestRemoveDirAll()

	// 获取当前工作目录
	// TestGetCurrentWorkingDir()
	// // 临时目录
	// TestGetTempDir()
	// // 重名名
	// TestRenameFileOrDir()

	// // 文件的状态
	// TestStatFile()

	// // 写入文件内容
	// TestWriteFile()

	// // 读取文件内容
	// TestReadFile()

}
