## config配置

```sh
git config --global user.name "Your Name"
git config --global user.email "email@example.com"
```

注意 git config 命令的 –global 参数，用了这个参数，表示你这台机器上所有的 Git 仓库都会使用这个配置，当然也可以对某个仓库指定不同的用户名和 Email 地址。



## 基本操作

```sh
git init
git add
git commit -m ""
git push origin master
git pull origin master

git clone git@github.com:...		#克隆代码
git remote add origin git@github.com:...	#指定远端库
```

## 分支操作

```sh
git branch dev		#创建分支
git checkout dev 	#切换到dev分支
git checkout -b dev		#创建dev，然后切换到dev
git checkout -b b origin/dev  #创建一个本地分支，将远端分支放入（创建完还是要拉取代码）

git branch 		#查看所有分支

git merge dev 	#合并指定分支到当前分支

git branch -d dev #删除指定分支dev
git branch -D dev #强行删除分支
git push origin :dev #删除远端分支
```

## 查看

```sh
git status 		#详细显示文件在暂存区和工作目录的状态
git status -s 	#简单显示

git diff            # 工作目录和暂存区
git diff --cached   # 暂存区和本地仓库
git diff HEAD 	    # 工作目录和本地仓库
git diff --stat     # 显示信息摘要
```

## 回滚

```sh
git reset HEAD -- file    # 将本地仓库的当前版本恢复到暂存区	
git reset HEAD~1 -- file  # 将本地仓库的上个版本恢复到暂存区

```

