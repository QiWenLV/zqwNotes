## JavaAgent是什么

JDK5中只能通过命令行参数在启动JVM时指定javaagent参数来设置代理类，而JDK6中已经不仅限于在启动JVM时通过配置参数来设置代理类，JDK6中通过 Java Tool API 中的 attach 方式，我们也可以很方便地在运行过程中动态地设置加载代理类，以达到 instrumentation 的目的。 Instrumentation 的最大作用，就是类定义动态改变和操作。

## JavaAgent能干什么

javaagent的主要的功能如下：

- 可以在加载class文件之前做拦截把字节码做修改
- 可以在运行期将已经加载的类的字节码做变更，但是这种情况下会有很多的限制
- 还有其他的一些小众的功能
  - 获取所有已经被加载过的类
  - 获取所有已经被初始化过了的类（执行过了clinit方法，是上面的一个子集）
  - 获取某个对象的大小
  - 将某个jar加入到bootstrapclasspath里作为高优先级被bootstrapClassloader加载
  - 将某个jar加入到classpath里供AppClassloard去加载
  - 设置某些native方法的前缀，主要在查找native方法的时候做规则匹配

