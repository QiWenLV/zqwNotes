`Docker`是一个开源工具，它可以让创建和管理`Linux`容器变得简单。**容器**就像是**轻量级**的**虚拟机**，并且可以以**毫秒级**的速度来启动或停止。`Docker`帮助系统管理员和程序员**在容器中开发应用程序**，并且可以扩展到成千上万的节点。

## Docker与传统虚拟化的区别

传统虚拟化技术架构

![](https://wendy-image.oss-cn-shanghai.aliyuncs.com/19-07-11/1562809731700.png)

Docker技术体系架构

![1562809811020](C:\Users\wen\AppData\Roaming\Typora\typora-user-images\1562809811020.png)

**容器**和**虚拟机**(`VM`)的主要区别是：

- 容器提供了基于**进程**的隔离，而虚拟机提供了**资源**(`CPU`、内存和硬盘)的**完全隔离**。
- 虚拟机可能需要**一分钟**来启动，而容器只需要**一秒钟**或更短。
- 虚拟机占用的**内存空间**可达到**几个**`G`，而容器可能只需要**几百兆**。
- 容器使用**宿主操作系统**的内核，而虚拟机使用**独立**的内核。

## Docker平台的基本构成

Docker平台基本上由三部分组成：

- **客户端**：用户使用`Docker`提供的工具(`CLI`以及`API`等)来构建，**上传镜像**并发布命令来**创建**和**启动容器**。
- **Docker主机**：从`Docker registry`上下载**镜像**并**启动**和**托管**容器。
- **Docker registry**：`Docker`镜像仓库，用于**保存镜像**，并提供镜像上传和下载。



## Docker容器的状态机

![](https://wendy-image.oss-cn-shanghai.aliyuncs.com/19-07-11/1562810006288.png)

一个容器在某个时刻可能处于以下几种状态之一：

- **created**：已经被创建 (使用`docker ps -a`命令可以列出) 但是还没有被启动，使用`docker ps`命令还无法列出。 
- **running**：容器在这正常运行中。 
- **paused**：容器的进程被暂停了。 
- **restarting**：容器的进程正在重启过程中。 
- **exited**：上图中的`stopped`状态，表示容器之前运行过但是现在处于停止状态 (要区别于`created`状态，它是指一个**新创建**的**尚未运行过**的容器)。可以通过`start`命令使其重新进入`running`状态。 
- **destroyed**：容器从宿主机删除了，再也不存在了。

## Docker部署

yum安装

```sh
#安装
yum install docker -y

#查看版本
docker -v
```

启动

```sh
#启动Docker
systemctl start docker
#设置开机启动
chkconfig docker on
```

报错注意：

```sh
#报错
Error starting daemon: SELinux is not supported with the overlay2 graph driver on this kernel. Either boot into a newer kernel or disable selinux in docker (--selinux-enabled=false)
```

此linux的内核中的SELinux不支持 overlay2 graph driver ，解决方法有两个，要么启动一个新内核，要么就在docker里禁用selinux，--selinux-enabled=false.

修改配置文件

```sh
vi /etc/sysconfig/docker

#改为
改为：--selinux-enabled=false
```

## Docker镜像

拉取官方CentOS镜像

```sh
#拉取
docker pull centos

# 查看本地镜像列表
docker images
# 删除镜像
docker rmi <image id>
# 删除镜像(针对多个相同image id的镜像)
docker rmi repository:tag
```

镜像信息

```sh
docker images centos
#REPOSITORY       TAG        IMAGE ID     CREATED         SIZE
#docker.io/centos latest    3fa822599e10    3weeks ago   203.5 MB
```

修改TAG

```sh
# docker tag IMAGE_ID(镜像id) REPOSITORY:TAG(仓库：标签)
docker tag 3fa822599e10  docker.io/centos:centos
```

启动镜像

```sh
docker run -itd -p 8082:8081 3763dbe4ed67
```

- `-i`：表示以**交互模式**运行容器
- `-t`：表示容器启动后会进入其命令行
- `-v`：表示需要将本地哪个目录**挂载**到容器中，格式：-v <宿主机目录>:<容器目录>
- `-p`：第二个8081表示容器内部的端口，映射到物理机的8082端口

其他启动参数解析

```sh
Usage: docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
  -d, --detach=false         指定容器运行于前台还是后台，默认为false
  -i, --interactive=false   打开STDIN，用于控制台交互
  -t, --tty=false            分配tty设备，该可以支持终端登录，默认为false
  -u, --user=""              指定容器的用户
  -a, --attach=[]            登录容器（必须是以docker run -d启动的容器）
  -w, --workdir=""           指定容器的工作目录
  -c, --cpu-shares=0        设置容器CPU权重，在CPU共享场景使用
  -e, --env=[]               指定环境变量，容器中可以使用该环境变量
  -m, --memory=""            指定容器的内存上限
  -P, --publish-all=false    指定容器暴露的端口
  -p, --publish=[]           指定容器暴露的端口
  -h, --hostname=""          指定容器的主机名
  -v, --volume=[]            给容器挂载存储卷，挂载到容器的某个目录
  --cap-add=[]               添加权限，权限清单详见：http://linux.die.net/man/7/capabilities
  --cap-drop=[]              删除权限，权限清单详见：http://linux.die.net/man/7/capabilities
  --cidfile=""               运行容器后，在指定文件中写入容器PID值，一种典型的监控系统用法
  --cpuset=""                设置容器可以使用哪些CPU，此参数可以用来容器独占CPU
  --dns=[]                   指定容器的dns服务器
  --dns-search=[]            指定容器的dns搜索域名，写入到容器的/etc/resolv.conf文件
  --entrypoint=""            覆盖image的入口点
  --env-file=[]              指定环境变量文件，文件格式为每行一个环境变量
  --expose=[]                指定容器暴露的端口，即修改镜像的暴露端口
  --link=[]                  指定容器间的关联，使用其他容器的IP、env等信息
  --lxc-conf=[]              指定容器的配置文件，只有在指定--exec-driver=lxc时使用
  --name=""                  指定容器名字，后续可以通过名字进行容器管理，links特性需要使用名字
  --net="bridge"             容器网络设置:
                                bridge 使用docker daemon指定的网桥
                                host     //容器使用主机的网络
                                container:NAME_or_ID  >//使用其他容器的网路，共享IP和PORT等网络资源
                                none 容器使用自己的网络（类似--net=bridge），但是不进行配置
  --privileged=false         指定容器是否为特权容器，特权容器拥有所有的capabilities
  --restart="no"             指定容器停止后的重启策略:
                                no：容器退出时不重启
                                on-failure：容器故障退出（返回值非零）时重启
                                always：容器退出时总是重启
  --rm=false                 指定容器停止后自动删除容器(不支持以docker run -d启动的容器)
  --sig-proxy=true           设置由代理接受并处理信号，但是SIGCHLD、SIGSTOP和SIGKILL不能被代理
```

### 打包镜像

编写Dockerfile

```

```

构建

```sh
docker build -t name .
```

用run命令进行初始化运行

## Docker的常用命令

我们可以把`Docker`的命令大概地分类如下：

```sh
## 镜像操作：
    build     Build an image from a Dockerfile
    commit    Create a new image from a container's changes
    images    List images
    load      Load an image from a tar archive or STDIN
    pull      Pull an image or a repository from a registry
    push      Push an image or a repository to a registry
    rmi       Remove one or more images
    search    Search the Docker Hub for images
    tag       Tag an image into a repository
    save      Save one or more images to a tar archive
    history   显示某镜像的历史
    inspect   获取镜像的详细信息

## 容器及其中应用的生命周期操作：
    create    创建一个容器
    kill      Kill one or more running containers
    inspect   Return low-level information on a container, image or task
    pause     Pause all processes within one or more containers
    ps        List containers
    rm        删除一个或者多个容器
    rename    Rename a container
    restart   Restart a container
    run       创建并启动一个容器
    start     启动一个处于停止状态的容器
    stats     显示容器实时的资源消耗信息
    stop      停止一个处于运行状态的容器
    top       Display the running processes of a container
    unpause   Unpause all processes within one or more containers
    update    Update configuration of one or more containers
    wait      Block until a container stops, then print its exit code
    attach    Attach to a running container
    exec      Run a command in a running container
    port      List port mappings or a specific mapping for the container
    logs      获取容器的日志

## 容器文件系统操作：
    cp        Copy files/folders between a container and the local filesystem
    diff      Inspect changes on a container's filesystem
    export    Export a container's filesystem as a tar archive
    import    Import the contents from a tarball to create a filesystem image
    Docker registry 操作：
    login     Log in to a Docker registry.
    logout    Log out from a Docker registry.

## Volume操作：
    volume    Manage Docker volumes

## 网络操作：
    network   Manage Docker networks

## Swarm 相关操作：
    swarm     Manage Docker Swarm
    service   Manage Docker services
    node      Manage Docker Swarm nodes

## 系统操作：
    version   Show the Docker version information
    events    持续返回docker 事件
    info      显示Docker 主机系统范围内的信息
```

```sh
# 查看运行中的容器
docker ps
# 查看所有容器
docker ps -a
# 退出容器
按Ctrl+D 即可退出当前容器【但退出后会停止容器】
# 退出不停止容器：
组合键：Ctrl+P+Q
# 启动容器
docker start 容器名或ID
# 进入容器
docker attach 容器名或ID
# 停止容器
docker stop 容器名或ID
# 暂停容器
docker pause 容器名或ID
# 继续容器
docker unpause 容器名或ID
# 删除容器
docker rm 容器名或ID
# 删除全部容器--慎用
docker stop $(docker ps -q) & docker rm $(docker ps -aq)
#保存容器，生成镜像
docker commit 容器ID 镜像名称
#从 host 拷贝文件到 container 里面
docker cp /home/soft centos:/webapp
```

## docker run与start的区别

`docker run`只在第一次运行时使用，将镜像放到容器中，以后再次启动这个容器时，只需要使用命令`docker start` 即可。

`docker run`相当于执行了两步操作：将镜像放入容器中(`docker create`)，然后将容器启动，使之变成运行时容器(`docker start`)。

![](https://wendy-image.oss-cn-shanghai.aliyuncs.com/19-07-11/1562815256206.png)

而`docker start`的作用是，重新启动已存在的镜像。也就是说，如果使用这个命令，我们必须事先知道这个**容器**的`ID`，或者这个容器的**名字**，我们可以使用`docker ps`找到这个容器的信息。因为容器的`ID`是**随机码**，而容器的名字又是看似无意义的命名，我们可以使用命令：

```sh
docker rename jovial_cori  centos
```

给这个容器命名。这样以后，我们再次启动或停止容器时，就可以直接使用这个名字：

```sh
docker [stop] [start]  new_name
```

而要显示出所有容器，包括没有启动的，可以使用命令：

```sh
docker ps -a
```

## Docker的配置

更改存储目录：

```sh
#复制docker存储目录
rsync -aXS /var/lib/docker/. /home/docker

#更改 docker 存储文件目录
ln -s  /home/docker  /var/lib/docker
```

查看启动容器的具体信息：

```sh
docker inspect <container_id>
```

要获取所有容器名称及其`IP`地址只需一个命令：

```sh
docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -aq)

docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
```

## Docker镜像加速器

阿里云会自动为用户分配一个镜像加速器的地址，登录后进入”管理中心” —> ”加速器”，里面有分配给你的镜像加速器的地址以及各个环境的使用说明。

镜像加速器地址示例：https://xxxxx.mirror.aliyuncs.com

### 如何配置镜像加速器

针对`Docker`客户端版本大于`1.10.0`的用户，可以通过修改`daemon`配置文件`/etc/docker/daemon.json`**来使用**加速器**：

```json
{
    "registry-mirrors": ["<your accelerate address>"]
}
```

重启`Docker Daemon`：

```sh
sudo systemctl daemon-reload
sudo systemctl restart docker
```

## 编写Dockerfile文件

### Dockerfile 语法示例

Dockerfile语法由两部分构成，注释和命令+参数

一个简单的例子：

1. `# Print "Hello docker!"`
2. `RUN echo "Hello docker!"`

### Dockerfile 命令

**ADD**

功能是复制。两个参数，源和目标。(源可以是URL)

```sh
ADD /my_app_folder /my_app_folder 
```

**CMD**

CMD可以用于执行特定的命令。在镜像构建容器之后调用

```sh
CMD "echo" "Hello docker!"
```

**ENTRYPOINT**

配置容器启动后执行的命令，并且不可被 docker run 提供的参数覆盖。

每个 Dockerfile 中只能有一个 ENTRYPOINT，当指定多个时，只有最后一个起效。

**ENV** 

ENV命令用于设置环境变量。(脚本中使用 $key调用)

```sh
# Usage: ENV key value
ENV SERVER_WORKS 4
```

**EXPOSE**

EXPOSE用来指定端口，使容器内的应用可以通过端口和外界交互。

```sh
EXPOSE 8080
```

**FROM**

指定一个基础镜像，（FROM命令必须是Dockerfile的首个命令）

```sh
# Usage: FROM [image name]
FROM ubuntu 
```

**MAINTAINER**

声明作者

**RUN**

执行命令

```sh
RUN aptitude install -y riak
```

**USER**

USER命令用于设置运行容器的UID

```sh
USER 751
```

**VOLUME**

让容器访问宿主机上的目录。

```
VOLUME ["/my_files"]
```

**WORKDIR**

指明CMD命令的运行目录。

```sh
WORKDIR ~/
```

