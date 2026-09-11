

# 生成器 generator (掌握)
#  需要使用next或者for循环来调用






# 生成器函数:
#   1. 函数内部要有 yield
#   2. 需要用next来调用
#   3. 每个next都会在yield处暂停
#   4. yield 会暂停, 可以返回值





# 示例:
def gen():
    g = (i for i in range(1, 10**100))

    for i in g:
        # 一个个返回值, 不会退出函数
        yield i


g = gen()
print(next(g))
print(next(g))
print(next(g))
print()



# 练习
# 1.请写一个生成器函数, 得到前20个斐波那契数 (难度:*****)
# 斐波那契数列如下：0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55...
#    提示:使用while True, 通过调用n次next来获取前20个数






