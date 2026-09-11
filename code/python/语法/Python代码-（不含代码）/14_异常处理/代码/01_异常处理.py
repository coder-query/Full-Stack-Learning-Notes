
# 异常处理

# 错误：代码还没运行就已经出错了， 这种情况要先解决
# 异常：代码写的时候不报错，运行报错





# 异常处理：针对有一定概率(小概率)出现异常的，我们需要做异常处理
# try-except:
#   尝试执行try中的代码，如果出错则进入except，否则不进入
#   作用是：防止报错导致程序结束，出现的错误可以被except捕获



# try-except-else
#   try尝试执行代码，如果出错了就进入except,否则进入else


# try-except-finally
#    try尝试执行代码，如果出错了就进入except, 最终进入finally(不管有没有错)



# Python自带的异常类型:
#     AttributeError : 属性错误
#     NameError: 变量没定义
#     IndexError : 索引越界
#     ZeroDivisionError : 除以0的错误
#     KeyError : 字典的key错误
#     FileExistsError : 文件已经存在
#     FileNotFoundError : 文件不存在
#     ImportError : 导包错误
#     IndentationError : 缩进错误
#     SyntaxError : 语法错误

# 2. 主动抛出异常


# 3. 断言

