## 系统命令

### 端口

```sh
netstat -tunlp | grep 端口号
```

参数含义

```sh
-t 	(tcp) 仅显示tcp相关选项
-u 	(udp)仅显示udp相关选项
-n 	拒绝显示别名，能显示数字的全部转化为数字
-l 	仅列出在Listen(监听)的服务状态
-p 	显示建立相关链接的程序名
```

其他用法

```sh
netstat -an | grep 3306   #查看所有3306端口使用情况·
netstat -lanp		#查看一台服务器上面哪些服务及端口
netstat -pnt |grep :3306 |wc	#查看某一端口的连接数量,比如3306端口
netstat -anp |grep 3306 		#查看某一端口的连接客户端IP 比如3306端口
netstat -an 	#查看网络端口 

ps -ef |grep mysqld				#查看一个服务有几个端口。比如要查看mysqld
lsof -i :port  	#就能看见所指定端口运行的程序，同时还有当前连接。 
netstat -anp 	#显示系统端口使用情况
```

### 防火墙

CentOS 7.0默认使用的是firewall作为防火墙

```sh
systemctl start firewalld			#启动firewall
systemctl stop firewalld			#停止firewall
systemctl disable firewalld			#禁止firewall开机启动

systemctl status firewalld			#查看防火墙当前状态
firewall-cmd --list-all 			#查看防火墙的配置情况
systemctl is-enabled firewalld  	#查看是否开启启动
```

打开某一端口

```sh
firewall-cmd --add-port=3306/tcp --permanent
```

### 进程

```sh
ps -ef | grep java		#表示查看所有进程里 CMD 是 java 的进程信息	
ps -aux | grep java		#显示所有状态

-e 		#显示所有进程,环境变量
-f 		#全格式
-h 		#不显示标题
-l 		#长格式
-w 		#宽输出
-a 		#显示终端上地所有进程,包括其他用户地进程
-r 		#只显示正在运行地进程
-x 		#显示没有控制终端地进程
```

进程数

```sh
ps -u root -L | wc -l 				#查看当前系统该用户连接数
cat /etc/security/limits.conf		#检查系统配置的用户进程数
cat  /etc/security/limits.d/90-nproc.conf	#检查系统配置的用户进程数

ulimit -u 		#查看系统进程数上限
ulimit -u 5120	#修改进程数上限
```

### 服务

```sh
systemctl status -l serviceName 	#服务状态的详细信息（可查看报错信息）
```

### yum&rpm

```sh
yum install docker		#安装
yum remove docker		#卸载
```

### ssh

```sh
ssh-keygen
ssh-copy-id root@192.168.0.1
```

