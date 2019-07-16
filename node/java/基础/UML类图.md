## UML基本介绍

UML——*Unified modeling language UML*( 统一建模语言 )，是一种用于软件系统分析和设计的语言工具，它用于帮助软件开发人员进行思考和记录思路的结果

UML本身是一套符号的规定，就像数学符号和化学符号一样，这些符号用于描述软件模型中的各个元素和他们之间的关系，比如类、接口、实现、泛化、依赖、组合、聚合等。

使用UML来建模，常用的工具有 Rational Rose , 也可以使用一些插件来建模



**UML图包含：**

1. 用例图(use case)
2. 静态结构图： 类图 、对象图、包图、组件图、部署 图
3. 动态行为图：交互图（时序图与协作图）、状态图、活动 图

说 明：

1. 类图是描述类与类之间的关系的，是UML 图中最核心的
2. 在讲解设计模式时，我们必然会使用类图，为了让学员们能够把设计模式学到位，需要先给大家讲解类图



## 类与类中间的关系

### 依赖关系

只要是在**类中用到了对方**，那么他们之间就存在依赖关系。如果没有对方，连编绎都通过不了。

```java
public class PersonServiceBean {
    private PersonDao personDao;//类
    
    public void save(Person person){}
    public IDCard getIDCard(Integer personid){}
    public void modify(){
   		Department department = new Department();
	}
}
public class PersonDao{}
public class IDCard{}
public class Person{}
public class Department{}
```

类中用到了对方:

- 类的成员属性
- 方法的返回类型
- 方法接收的参数类型
- 方法中使用到

### 泛化关系

泛化关系实际上就是**继承关系**，是**依赖关系的特例**

```java
public abstract class DaoSupport{
    public void save(Object entity){

    }
    public void delete(Object id){

    }
}
public class PersonServiceBean extends Daosupport{
}
```

小结:

1. 泛化关系实际上就是继承关系
2.  如果A类继承了B类，我们就说A和B存在泛化关系

### 实现关系

实现关系实际上就是A类实现B接口，他是**依赖关系的特例**

```java
public interface PersonService {
	public void delete(Interger id);
}
public class PersonServiceBean implements PersonService {
	public void delete(Interger id){}
}
```

### 关联关系

关联关系实际上就是**类与类之间的联系**，他是**依赖关系的特例**

- 关联具有导航性：即双向关系或单向关系(谁关联谁的问题)
- 关系具有多重性：如“1”（表示有且仅有一个），“0...”（表示0个或者多个），“0，1”（表示0个或者一个），“n...m”(表示n到 m个都可以),“m...*”（表示至少m个）。

单向一对一关系

```java
public class Person {
	private IDCard card;
}
public class IDCard{}
```

双向一对一关系

```java
public class Person {
	private IDCard card;
}
public class IDCard{
	private Person person;
}
```

### 聚合关系

聚合关系（*Aggregation*）表示的是 整体和部分的关系，整体与部分可以分开。聚合关系是关联关系的特例，所以他具有关联的导航性与多重性。
如：一台电脑由键盘(keyboard)、显示器(monitor)，鼠标等组成；组成电脑的各个配件是可以从电脑上分离出来的，使用带空心菱形的实线来表示：

```java
punlic class Computer {
	private Mouse mouse;
    private Monitor monitor;
    
    public void setMouse(Mouse mouse){
        this.mouse = mouse;
    }
    public void setMonitor(Monitor monitor){
        this.monitor = monitor;
    }
}
```

### 组合关系

如果我们认为Mouse，Monitor和Computer是不可分离的，则升级为**组合关系**

组合关系：也是整体与部分的关系，但是整体与部分不可以分开。再看一个案例：在程序中我们定义实体：Person与IDCard、Head, 那么 Head 和Person 就是 组合，IDCard 和 Person 就是聚合。

但是如果在程序中Person实体中定义了对IDCard进行**级联删除**，即删除Person时连同IDCard一起删除，那么IDCard 和 Person 就是组合了。

```java
public class Person{
    private IDCard card;
    private Head head = new Head();
}
public class IDCard{}
public class Head{}
```

