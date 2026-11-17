package main

func main() {
	// 一次性读取文件到内存，适用于文件小的情况
	//content, err := ioutil.ReadFile("README.txt")
	//if err != nil {
	//	fmt.Println("error:", err)
	//	return
	//}
	//fmt.Println(string(content))

	// 缓冲区读取文件，适用于文件大的情况
	//file, err := os.Open("README.txt")
	//if err != nil {
	//	fmt.Println("error:", err)
	//	return
	//}
	//defer func(file *os.File) {
	//	err := file.Close()
	//	if err != nil {
	//		fmt.Println("关闭文件失败error:", err)
	//		return
	//	}
	//}(file)
	//reader := bufio.NewReader(file)
	//for {
	//	line, err := reader.ReadString('\n')
	//	if err == io.EOF {
	//		fmt.Println("error----> :", err)
	//		break
	//	}
	//	fmt.Println(line)
	//}
	//fmt.Println("文件读取完成")

}
