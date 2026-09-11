
# 字符串的功能

# count(): 计数,统计某子串出现的次数
s = "hello hello hello"
# print(s.count('l'))  # 6
# print(s.count('ll'))  # 3
# print(s.count('ll', 8, 10))  # 2  在[8,10)之间查找"ll"出现的次数

# 大小写判断 (掌握)
# print('ABC'.isupper())  # 字母是否为大写
# print('abc'.islower())  # 字母是否为小写
# print('123'.isdigit())  # 是否为数字
# print('abcABC'.isalpha())  # 是否为字母
# print('abcABC123'.isalnum())  # 是否字母或数字
# print('   '.isspace())  # 是否为空格字符串
# print('Hello World'.istitle())  # 是否每个单词首字母大写,其他小写

# 大小写转换
# print('abc123'.upper())  # "ABC123" 转成大写
# print('ABC123'.lower())  # 'abc123' 转成小写
# print(int('123'))  # 转成整数 123
# print(float('3.14'))  # 转成小数 3.14
# # print('heLLO woRLd'.title())  # 转换成:每个单词首字母大写,其他小写
# print('heLLO woRLd'.swapcase())  # 大小写切换: 大写变成小写,小写变成大写  'HEllo WOrlD'



# 查找
#   find(): 查找指定子串第一次出现的下标位置,如果不存在则返回-1
#  rfind(): 从右往左查找指定子串第一次出现的下标位置,如果不存在则返回-1
#   index(): 不用
#   rindex(): 不用
s = "ikun is very very very handsome"




# 练习:
# 1.已知字符串：“this is a test of Python”
s = 'this is a test of Python'

# a.统计该字符串中字母s出现的次数: count()
# b.取出子字符串“test”, 用切片,不能数: 使用find(),len()
# c.采用不同的方式将字符串倒序输出: 切片，循环


# 拆分,合并,替换
# 拆分: 分割
#  split() : 拆分后得到列表
s1 = "ikun  is very very very handsome"


# splitlines(): 按行拆分
s2 = '''床前明月光,
疑是地上霜.
举头望明月,
低头思故乡.'''



# 合并: join : 会得到字符串类型
#   将列表中的字符串拼接
n = ['床前明月光,', '疑是地上霜.', '举头望明月,', '低头思故乡.']


# 替换: replace() : 默认替换所有匹配的



# 练习
# 1.已知字符串：“this is a test of Python”
# d.将其中的"test"替换为"exam" : replace()



# 2.去掉字符串123@zh@qq.com中的@;
# 提示: replace()  或者 split()+join()




