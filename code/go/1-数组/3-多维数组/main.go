package main

import "fmt"

func main() {
	var courseInfo [3][4]string
	courseInfo[0] = [4]string{"Java", "shuaihong", "1h", "Spring5"}
	courseInfo[1] = [4]string{"Java", "shuaihong", "2h", "SpringBoot2"}
	courseInfo[2] = [4]string{"Python", "shuaihong", "1h", "FastApi"}
	fmt.Print("课程名称\t教师\t时长\t内容\n")
	for i := 0; i < len(courseInfo); i++ {
		for j := 0; j < len(courseInfo[i]); j++ {
			fmt.Print(courseInfo[i][j], "\t")
		}
		fmt.Println()
	}

}
