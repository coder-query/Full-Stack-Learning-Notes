
# 函数嵌套（难点）





# 函数名称：既是函数名，也是指向该函数的变量（对象）
#          只要是指向该函数的变量，就可以调用该函数



# 函数之间可以进行相互嵌套调用
def test():
    test1()
    print(11111)

def test1():
    test2()
    print(22222)

def test2():
    test3()
    print(33333)

def test3():
    print(44444)

test()
