

# 装饰器：
#     作用是在其他函数的前面或后面添加功能，但是不修改原函数

def swim():
    print('我爱游泳')

def swim2():
    print('先跳个舞')
    swim()
    print('再唱个歌')


# 上面的方式有缺陷：只能给swim添加功能

def run(x):
    print('我爱跑步')


def run2(fn):
    print('先跳个舞')
    fn(2)
    print('再唱个歌')

run2(run)
run2(swim)
print('*' * 100)


# 上面还有缺点：调用的方式会发生变化
#    不使用装饰器就调用 run()
#      使用装饰器就调用 run2()


# 标准装饰器
# 定义装饰器




# 练习：写一个装饰器，计算函数运行的时间



