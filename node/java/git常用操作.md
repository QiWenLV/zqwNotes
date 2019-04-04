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

