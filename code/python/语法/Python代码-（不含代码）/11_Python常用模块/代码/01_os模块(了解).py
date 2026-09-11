import os

# os  用于获取系统的功能，主要用于操作文件或者文件夹

print(os.name)  # nt 表示window操作系统
print(os.getcwd())  # 当前目录

# 创建目录: mkdir()  如果文件存在会报错
# 创建多层目录: makedirs('a/b/c')

# 删除空目录: rmdir

# 删除文件: remove

# 重命名: rename

# listdir() : 返回指定目录下的所有文件或文件夹名组成的列表


# os.path
#  os.path.exists : 文件或文件夹是否存在
#  os.path.isfile() : 是否为文件
# os.path.isdir() : 是否为目录

# 合并路径

# 需求: 将指定目录下的子目录的绝对路径打印



# 绝对路径: 从盘符开始的路径
# 相对路径: 从当前文件所在目录开始的路径

# os.path.split : 拆分

# os.path.splittext() : 拆分文件的扩展名

# 文件大小:字节

# 绝对路径




