# 一、课程回顾

```bash
1，安装基础软件；
2，linux系统；
	- centos7.9
	- Ubuntu22.04（sshd服务的配置文件）
	- Kylin-v10-sp3
3，解释器的含义；
4，命令的格式：命令 [参数选项] 操作的目标；
5，shell解释器中的快捷键：
	crtl + l  #清屏
	crtl + a 
	crtl + e
	crtl + u
	crtl + k
	crtl + 左/右
	crtl + y
	crtl + c
	crtl + z
6，开关机命令；
	shutdown  -h now
	shutdown  -h 5
	shutdown  -r now
	shutdown  -r 10
	shutdown  -c
	halt
	poweroff
	init 0
	reboot
	init 6
7，ping ip/域名；
8，ip a【ip address】；
9，重点；
	- rm --help
	- man rm
	- 百度******
```

> 【8:30-9:00】吃饭

```bash
#规则：
	- 上课手机不能响，谁响谁发红包；
	- 迟到：红包；
```

# 二、linux系统的目录结构

> linux两大原则：
>
> - 一切从根【/】开始；
> - 一切皆文件；

```bash
[root@Kylin-oldboy ~]# ll /
总用量 16
lrwxrwxrwx    1 root root    7  3月  6  2021 bin -> usr/bin  #系统命令
dr-xr-xr-x.   6 root root 4096  2月 28 10:41 boot    #系统内核存储位置
drwxr-xr-x   19 root root 4000  2月 28 15:34 dev     #（硬盘）外接硬件的文件目录；
drwxr-xr-x  121 root root 8192  2月 28 15:40 etc     #系统软件服务的配置文件存放地；
drwxr-xr-x    3 root root   20  2月 28 11:40 home    #普通用户的家目录
....
drwxr-xr-x    2 root root    6  3月  6  2021 mnt     #默认的挂载目录
drwxr-xr-x    4 root root   53  2月 28 10:43 opt     #三方软件包的安装位置；
dr-xr-xr-x  204 root root    0  2月 28 15:34 proc    #系统服务进程信息（类似于汽车的仪表盘）
dr-xr-x---    3 root root  142  2月 28 15:52 root    #管理员的家目录
lrwxrwxrwx    1 root root    8  3月  6  2021 sbin -> usr/sbin #系统命令
drwxrwxrwt   10 root root  200  2月 28 15:46 tmp     #临时文件的存储位置（回收站）
drwxr-xr-x   12 root root  144  2月 28 10:37 usr   
drwxr-xr-x   22 root root  303  2月 28 10:56 var     #存放系统服务日志的目录；
```

# 三、linux系统核心命令（必会）

## 1，cd与pwd

```bash
cd   #切换目录，
pwd  #查看当前所在路径；
##################################
[root@Kylin-oldboy ~]# pwd
/root
[root@Kylin-oldboy ~]# cd /
[root@Kylin-oldboy /]# pwd
/
[root@Kylin-oldboy /]# cd /tmp/
[root@Kylin-oldboy tmp]# pwd
/tmp

###################################
#绝对路径与相对路径

#1，配置epel源
#kylin：
curl -o /etc/yum.repos.d/epel.repo https://mirrors.aliyun.com/repo/epel-7.repo

#centos：
curl -o /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo
curl -o /etc/yum.repos.d/epel.repo https://mirrors.aliyun.com/repo/epel-7.repo

#2，安装tree命令
[root@Kylin-oldboy ~]# yum -y install tree

#3，创建多级目录，使用tree命令显示目录结构
[root@Kylin-oldboy ~]# mkdir 111/222/333/444/555/666 -p
[root@Kylin-oldboy ~]# tree 
.
└── 111
    └── 222
        └── 333
            └── 444
                └── 555
                    └── 666
[root@Kylin-oldboy ~]# mkdir -p 1/2/3/4/5/6
[root@Kylin-oldboy ~]# tree
.
├── 1
│   └── 2
│       └── 3
│           └── 4
│               └── 5
│                   └── 6
└── 111
    └── 222
        └── 333
            └── 444
                └── 555
                    └── 666
[root@Kylin-oldboy ~]# cd /root/111/222/333/      #绝对路径
[root@Kylin-oldboy ~]# cd 111/222/333/            #相对路径（相对于我当前的路径）
[root@Kylin-oldboy ~]# cd ~
[root@Kylin-oldboy 333]# cd /root/1/2/3/
[root@Kylin-oldboy 3]# pwd
/root/1/2/3
```

![image-20250303102502422](linux目录结构与核心命令.assets/image-20250303102502422.png)

```bash
#关于cd命令
	cd    #回到家目录
	cd -  #回到上一次所在的路径下
	cd ~  #回到家目录
	cd .. #前往上一级目录
	cd .  #一动不动
```

> 绝对路径与相对路径~

## 2，ls查看

> 语法： 
>
> - ls                  查看当前目录下有什么
> - ls   路径       查看路径下有什么

```bash
#1,查看根/下有什么
[root@Kylin-oldboy ~]# ls /
bin   dev  home  lib64  mnt  proc  run   srv  tmp  var
boot  etc  lib   media  opt  root  sbin  sys  usr

#2，查看当前目录（milv）下有什么
[root@Kylin-oldboy ~]# ls
1  111
```

> -l参数：显示文件或者目录的属性信息

```bash
[root@Kylin-oldboy ~]# ls -l /
或者
[root@Kylin-oldboy ~]# ll /
总用量 16
lrwxrwxrwx    1 root root    7  3月  6  2021 bin -> usr/bin
dr-xr-xr-x.   6 root root 4096  2月 28 10:41 boot
drwxr-xr-x   19 root root 4000  2月 28 15:34 dev
drwxr-xr-x  121 root root 8192  2月 28 15:40 etc
drwxr-xr-x    3 root root   20  2月 28 11:40 home
lrwxrwxrwx    1 root root    7  3月  6  2021 lib -> usr/lib
lrwxrwxrwx    1 root root    9  3月  6  2021 lib64 -> usr/lib64
drwxr-xr-x    2 root root    6  3月  6  2021 media
drwxr-xr-x    2 root root    6  3月  6  2021 mnt
drwxr-xr-x    4 root root   53  2月 28 10:43 opt
dr-xr-xr-x  201 root root    0  2月 28 15:34 proc
dr-xr-x---    5 root root  162  2月 28 17:08 root
drwxr-xr-x   38 root root 1080  2月 28 15:34 run
lrwxrwxrwx    1 root root    8  3月  6  2021 sbin -> usr/sbin
drwxr-xr-x    2 root root    6  3月  6  2021 srv
dr-xr-xr-x   14 root root    0  2月 28 15:39 sys
drwxrwxrwt   10 root root  200  2月 28 17:45 tmp
drwxr-xr-x   12 root root  144  2月 28 10:37 usr
drwxr-xr-x   22 root root  303  2月 28 10:56 var
```

> -h  人类可读的方式显示

```bash
[root@Kylin-oldboy ~]# ls -lh /
总用量 16K
lrwxrwxrwx    1 root root    7  3月  6  2021 bin -> usr/bin
dr-xr-xr-x.   6 root root 4.0K  2月 28 10:41 boot
drwxr-xr-x   19 root root 4.0K  2月 28 15:34 dev
drwxr-xr-x  121 root root 8.0K  2月 28 15:40 etc

[root@Kylin-oldboy ~]# ll -h /
总用量 16K
lrwxrwxrwx    1 root root    7  3月  6  2021 bin -> usr/bin
dr-xr-xr-x.   6 root root 4.0K  2月 28 10:41 boot
drwxr-xr-x   19 root root 4.0K  2月 28 15:34 dev
drwxr-xr-x  121 root root 8.0K  2月 28 15:40 etc

###########################
#扩展：2^10=1024
	1byte = 8bit
	1kb   = 1024byte
	1mb   = 1024kb
	1gb   = 1024mb
	1tb   = 1024gb
	1pb   = 1024tb
	1eb   = 1024pb
	1zb   = 1024eb
```

> -d查看目录本身

```bash
[root@Kylin-oldboy ~]# ll -d /etc
drwxr-xr-x 121 root root 8192  2月 28 15:40 /etc
```

> -a显示隐藏文件或隐藏目录

```bash
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 3 root root 15  2月 28 17:08 1
drwxr-xr-x 3 root root 17  2月 28 16:57 111


[root@Kylin-oldboy ~]# ll -a
总用量 28
dr-xr-x---   5 root root 162  2月 28 17:08 .
dr-xr-xr-x. 18 root root 238  2月 28 11:01 ..
drwxr-xr-x   3 root root  15  2月 28 17:08 1
drwxr-xr-x   3 root root  17  2月 28 16:57 111
-rw-------   1 root root 326  2月 28 17:45 .bash_history
-rw-r--r--   1 root root  18  3月 13  2020 .bash_logout
-rw-r--r--   1 root root 176  3月 13  2020 .bash_profile
-rw-r--r--   1 root root 176  3月 13  2020 .bashrc
-rw-r--r--   1 root root 100  3月 13  2020 .cshrc
drwx------   3 root root 108  2月 28 15:34 .gnupg
-rw-------   1 root root  20  2月 28 15:14 .lesshst
-rw-r--r--   1 root root 129  3月 13  2020 .tcshrc
```

> 不常用的参数-t：按照时间排序
>
> 不常用的参数-r：逆序排序

```bash
[root@Kylin-oldboy ~]# ll -at
总用量 28
-rw-------   1 root root 326  2月 28 17:45 .bash_history
dr-xr-x---   5 root root 162  2月 28 17:08 .
drwxr-xr-x   3 root root  15  2月 28 17:08 1
drwxr-xr-x   3 root root  17  2月 28 16:57 111
drwx------   3 root root 108  2月 28 15:34 .gnupg
-rw-------   1 root root  20  2月 28 15:14 .lesshst
dr-xr-xr-x. 18 root root 238  2月 28 11:01 ..
-rw-r--r--   1 root root  18  3月 13  2020 .bash_logout
-rw-r--r--   1 root root 176  3月 13  2020 .bash_profile
-rw-r--r--   1 root root 176  3月 13  2020 .bashrc
-rw-r--r--   1 root root 100  3月 13  2020 .cshrc
-rw-r--r--   1 root root 129  3月 13  2020 .tcshrc

[root@Kylin-oldboy ~]# ll -atr
总用量 28
-rw-r--r--   1 root root 129  3月 13  2020 .tcshrc
-rw-r--r--   1 root root 100  3月 13  2020 .cshrc
-rw-r--r--   1 root root 176  3月 13  2020 .bashrc
-rw-r--r--   1 root root 176  3月 13  2020 .bash_profile
-rw-r--r--   1 root root  18  3月 13  2020 .bash_logout
dr-xr-xr-x. 18 root root 238  2月 28 11:01 ..
-rw-------   1 root root  20  2月 28 15:14 .lesshst
drwx------   3 root root 108  2月 28 15:34 .gnupg
drwxr-xr-x   3 root root  17  2月 28 16:57 111
drwxr-xr-x   3 root root  15  2月 28 17:08 1
dr-xr-x---   5 root root 162  2月 28 17:08 .
-rw-------   1 root root 326  2月 28 17:45 .bash_history
```

```bash
ls   
	-l    #显示属性信息
	-a    #显示隐藏信息
	-h    #人类可读的方式显示
	-d    #查看目录本身的信息
	-t    #按照时间排序
	-r    #逆序显示
	-i    #显示inode号；

[root@Kylin-oldboy ~]# ll -atri
总用量 28
136159995 -rw-r--r--   1 root root 129  3月 13  2020 .tcshrc
136159994 -rw-r--r--   1 root root 100  3月 13  2020 .cshrc
136159993 -rw-r--r--   1 root root 176  3月 13  2020 .bashrc
136159992 -rw-r--r--   1 root root 176  3月 13  2020 .bash_profile
136159991 -rw-r--r--   1 root root  18  3月 13  2020 .bash_logout
      128 dr-xr-xr-x. 18 root root 238  2月 28 11:01 ..
134337496 -rw-------   1 root root  20  2月 28 15:14 .lesshst
 68032713 drwx------   3 root root 108  2月 28 15:34 .gnupg
134337492 drwxr-xr-x   3 root root  17  2月 28 16:57 111
   899628 drwxr-xr-x   3 root root  15  2月 28 17:08 1
134317953 dr-xr-x---   5 root root 162  2月 28 17:08 .
134609353 -rw-------   1 root root 326  2月 28 17:45 .bash_history
```

## 3，mkdir创建目录（milv）

```bash
[root@Kylin-oldboy ~]# mkdir 111
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 2 root root 6  2月 28 18:23 111

#-p递归创建目录
[root@Kylin-oldboy ~]# mkdir 11/22/33/44
mkdir: 无法创建目录 “11/22/33/44”: 没有那个文件或目录

[root@Kylin-oldboy ~]# mkdir -p 11/22/33/44
[root@Kylin-oldboy ~]# tree
.
├── 11
│   └── 22
│       └── 33
│           └── 44
└── 111

#-v显示创建的过程
[root@Kylin-oldboy ~]# mkdir -pv 1111/2222/3333/4444
mkdir: 已创建目录 '1111'
mkdir: 已创建目录 '1111/2222'
mkdir: 已创建目录 '1111/2222/3333'
mkdir: 已创建目录 '1111/2222/3333/4444'
```

## 4，touch创建文件

```bash
#1，当前目录下，创建一个1.txt的文件
[root@Kylin-oldboy ~]# touch /mnt/1.txt
[root@Kylin-oldboy ~]# ll /mnt/1.txt
-rw-r--r-- 1 root root 0  2月 28 18:31 /mnt/1.txt

#2，在/mnt下创建一个1.txt文件
[root@Kylin-oldboy ~]# touch /mnt/1.txt
[root@Kylin-oldboy ~]# ll /mnt/1.txt
-rw-r--r-- 1 root root 0  2月 28 18:31 /mnt/1.txt
```

## 5，mv移动改名

move  移动：就类似于windows中的剪切+粘贴；

语法： mv   被移动的文件或者目录    移动到的路径位置

【-t参数】mv  -t  移动到的路径位置    被移动的文件或者目录

```bash
#1，将当前目录下的1.txt文件移动到/tmp下
[root@Kylin-oldboy ~]# mv 1.txt /tmp/
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 3 root root 16  2月 28 18:25 11
drwxr-xr-x 2 root root  6  2月 28 18:23 111
drwxr-xr-x 3 root root 18  2月 28 18:27 1111
[root@Kylin-oldboy ~]# ll /tmp/
总用量 0
-rw-r--r-- 1 root root  0  2月 28 18:31 1.txt

#2，将/tmp/1.txt移动到root的家目录，并改名为2.txt
[root@Kylin-oldboy ~]# mv /tmp/1.txt ./2.txt
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 3 root root 16  2月 28 18:25 11
drwxr-xr-x 2 root root  6  2月 28 18:23 111
drwxr-xr-x 3 root root 18  2月 28 18:27 1111
-rw-r--r-- 1 root root  0  2月 28 18:31 2.txt

#3，【-t参数】：将当前目录下的2.txt，移动到/tmp下并改名3.txt
- 【-t参数使用后】不能改名
[root@Kylin-oldboy ~]# mv -t /tmp/3.txt ./2.txt 
mv: 访问 '/tmp/3.txt' 失败: 没有那个文件或目录
[root@Kylin-oldboy ~]# mv -t /tmp/ ./2.txt 
[root@Kylin-oldboy ~]# ll /tmp/
总用量 0
-rw-r--r-- 1 root root  0  2月 28 18:31 2.txt

[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 3 root root 16  2月 28 18:25 11
drwxr-xr-x 2 root root  6  2月 28 18:23 111
drwxr-xr-x 3 root root 18  2月 28 18:27 1111
```

## 6，cp复制改名

> 语法：
>
> - 复制文件：cp  把什么   复制到哪里
> - 复制目录：cp  -r  把什么   复制到哪里
> - -t参数：与mv中的-t一致；

```bash
#复制文件
[root@Kylin-oldboy ~]# touch 1.txt
[root@Kylin-oldboy ~]# cp 1.txt 11
11/   111/  1111/ 
[root@Kylin-oldboy ~]# cp 1.txt 11/22/33/
[root@Kylin-oldboy ~]# tree
.
├── 11
│   └── 22
│       └── 33
│           ├── 1.txt
│           └── 44
├── 111
├── 1111
│   └── 2222
│       └── 3333
│           └── 4444
└── 1.txt

#复制目录
[root@Kylin-oldboy ~]# cp -r 111 1111/2222/3333/4444/
[root@Kylin-oldboy ~]# tree
.
├── 11
│   └── 22
│       └── 33
│           ├── 1.txt
│           └── 44
├── 111
├── 1111
│   └── 2222
│       └── 3333
│           └── 4444
│               └── 111

#复制加改名
[root@Kylin-oldboy ~]# cp -r 1111/2222/3333/4444/111 ./11111
[root@Kylin-oldboy ~]# tree
.
├── 11
│   └── 22
│       └── 33
│           ├── 1.txt
│           └── 44
├── 111
├── 1111
│   └── 2222
│       └── 3333
│           └── 4444
│               └── 111
├── 11111
└── 1.txt

#-t参数
[root@Kylin-oldboy ~]# cp -t 111/ 1.txt 
[root@Kylin-oldboy ~]# tree
.
├── 11
│   └── 22
│       └── 33
│           ├── 1.txt
│           └── 44
├── 111
│   └── 1.txt

#【了解一下】-a参数：表示-r、-p、-d参数的合集
	- -r #递归复制目录
	- -p #表示复制后属性信息不变
	- -d #复制软连接的意思（后续会将）
```

## 7，rm删除命令

> -r   递归删除，删除目录；
>
> -f   强制删除，不提示操作者；

```bash
#1，-r参数删除目录
[root@Kylin-oldboy ~]# rm -r 11 
rm：是否进入目录'11'? y
rm：是否进入目录'11/22'? y
rm：是否进入目录'11/22/33'? y
rm：是否删除目录 '11/22/33/44'？y
rm：是否删除普通空文件 '11/22/33/1.txt'？y
rm：是否删除目录 '11/22/33'？y
rm：是否删除目录 '11/22'？y
rm：是否删除目录 '11'？y

#2，-f参数，删除目录，不提示
[root@Kylin-oldboy ~]# rm -rf 1111

#3，删除文件不提示
[root@Kylin-oldboy ~]# rm -f 1.txt 
[root@Kylin-oldboy ~]# tree
.
├── 111
│   └── 1.txt
└── 11111
```

> rm  -rf  /*

## 8，vi/vim编辑文件

### · vi与vim的区别

| 命令 | 区别                                                         |
| ---- | ------------------------------------------------------------ |
| vi   | 系统自带命令，不需要额外的安装，没有vim功能多；              |
| vim  | centos需要额外安装（ubuntu与kylin不需要安装），未来大部分编辑文件都是用vim |

```bash
#有这个文件，则进入编辑，没有这个文件，则创建后再编辑；
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 2 root root 6  2月 28 18:57 111
drwxr-xr-x 2 root root 6  2月 28 18:47 11111
[root@Kylin-oldboy ~]# vim 1.txt
[root@Kylin-oldboy ~]# ll
总用量 0
drwxr-xr-x 2 root root 6  2月 28 18:57 111
drwxr-xr-x 2 root root 6  2月 28 18:47 11111
-rw-r--r-- 1 root root 0  2月 28 22:20 1.txt
```

### · 编辑文件的流程

```bash
#1，编辑文件
	- vi/vim   文件路径/文件名成
#2，进入编辑模式
	- i/a/o
	- 开始编辑内容
#3，退出编辑模式
	- esc
#4，保存并退出
	- :wq    #write写，quit退出；保存并退出；
	- :q     #quit退出，不保存；
	- :wq!   #强制保存退出；
	- :q!    #强制quit退出，不保存；
```

> 工作模式

![image-20250303151729968](linux目录结构与核心命令.assets/image-20250303151729968.png)

### · vi与vim快捷键

```bash
#1，准备练习环境
[root@Kylin-oldboy ~]# cat /etc/passwd > 3.txt

#2，开始练习vim的命令
	- 光标移动的快捷键
		G       #光标移动到文件的最后一行；
		1G/gg   #光标移动到文件的第一行；
		5gg     #光标移动到第5行；
		^       #光标到行首
		$       #光标到行尾
	- 复制删除剪切黏贴
		yy      #复制当前行       3yy：复制当前行向下3行内容
		p       #当前行下一行黏贴；3p：剪切板上的内容，黏贴3次；
		dd      #删除当前行，3dd：连续删除3行；
		dG      #删除光标行到最后一行
		d1G/dgg     #删除光标行到文件第一行
	- 撤销
		u       #退回到上一步操作；
	- 底行模式
		:set nu    #显示行号
		:set nonu  #不显示行号
		:set paste  #无格式粘贴（之后会讲）
		/要搜索的内容    #搜索问价内容
			n   #从上往下查询
			N   #从下往上查询
		:noh        #取消高亮显示
	- 进入编辑模式
		i  #【光标位置不变】insert插入
		a  #【光标向后一位】进入编辑模式，append追加
		o  #【光标向下一行】进入编辑模式；
		A  #【光标移动到行尾】进入编辑模式；
		I  #【光标移动到行首】进入编辑模式；
		O  #【光标向上一行】进入编辑模式；
	- esc退出编辑模式；
```

### · vi/vim经典故障案例

> 案例场景：
>
> - 编辑一个文件，意外退出后，再次进入文件编辑，发现页面提示信息，如下图：

![image-20250303160805435](linux目录结构与核心命令.assets/image-20250303160805435.png)

> 编辑文件的原理

![image-20250303161316952](linux目录结构与核心命令.assets/image-20250303161316952.png)

> 解决方式

```bash
#1，根据提示R、D
#2，将隐藏文件改名为正式文件，或者直接删除隐藏文件。
```

# 四、作业

```bash
#1，做好自己的命令笔记；
```





























