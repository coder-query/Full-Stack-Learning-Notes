package utils

import "fmt"

func Sub(a int, b int) int {
	fmt.Printf("sub a:%d, b:%d res = %d\n", a, b, a-b)
	return a - b
}
