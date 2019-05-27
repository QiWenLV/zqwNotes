## 起源

1. Stream自己**不会存储元素**。
2. Stream**不会改变源对象**。相反，他们会返回一个持有结果的新Stream。
3. Stream操作是**延迟执行**的。这意味着他们会等到需要结果的时候才执行。

### 与集合的区别

#### 加载方式

- 集合中的数据是全部加载好的，所有数据都在内存中
- 而流是按需加载的，可以类比分页。

#### 外部迭代和内部迭代

- 集合是外部迭代：集合比作一个工厂的仓库，一开始工厂比较落后，要对货物作什么修改，只能工人亲自走进仓库对货物进行处理，有时候还要将处理后的货物放到一个新的仓库里面。在这个时期，我们需要亲自去做迭代，一个个地找到需要的货物，并进行处理，这叫做**外部迭代**。
- 流是内部迭代：后来工厂发展了起来，配备了流水线作业，只要根据需求设计出相应的流水线，然后工人只要把货物放到流水线上，就可以等着接收成果了，而且流水线还可以根据要求直接把货物输送到相应的仓库。这就叫做**内部迭代**，流水线已经帮你把迭代给完成了，你只需要说要干什么就可以了（即设计出合理的流水线）。

**流只能使用一次**

## 创建流

4种方式

```java
//1.集合 
list.stream();        //顺序流
ist.parallelStream(); //获取一个并行流

//2.数组
Arrays.stream(strs)；

//3.静态方法
Stream.of(1,2,3,4,5,6);

//4.无限流（生成集合，不固定大小）
//迭代生成
Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);

//随机生成
Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
```



## 中间操作



```java
list.stream()
    .filter((e) -> e.getSalary() >= 5000)	//过滤
    .limit(5)       //限制5个
    .skip(2)        //丢弃前两个
    .distinct()     //去重
    .sorted(Comparator.comparingInt(Person::getAge))	//排序
    .forEach(System.out::println);      //中断流
```

- `filter(T -> boolean)` —— 只保留返回true的元素，传入返回值为 boolean 的函数
- `limit(long n)` —— 截断流，使其元素不超过给定数量。
- `skip(long n)` —— 去除前 n 个元素。若流中元素不足n个，则返回一个空流。与limit(n)互补。
- `distinct()` —— 去重，通过流中素的hashCode()和equals()去除重复元素
- `sorted()/sorted((T, T) -> int)`—— 排序，如果元素实现了Comparable 接口就不需要传参，如果没有这需要传入比较函数，返回结果小于零说明前一个值小，大于零说明前一个值大。
- `peek()`——  外部操作
- `map()` —— 将流中一种元素转换为另一种类型的元素
- `flatMap()` —— 将流中的流合并为一个流



## 结束操作

### 匹配

- allMatch —— 检查是否匹配所有元素
- anyMatch —— 检查是否至少匹配一个元素
- noneMatch —— 检查是否没有匹配的元素
- findFirst —— 返回第一个元素
- indAny —— 返回当前流中的任意元素
- count —— 返回流中元素的总个数	
- max —— 返回流中最大值
- min —— 返回流中最小值

### 归约

```java
//Integer集合求和，
List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
Integer sum = list.stream()
	.reduce(0, (x, y) -> x + y);

//求员工的工资和
Optional<Double> op = emps.stream()
    .map(Employee::getSalary)
    .reduce(Double::sum);
System.out.println(op.get());
```

- reduce(T identity, BinaryOperator) / reduce(BinaryOperator)——可以将流中元素反复结合起来，得到一个值。

### 收集

```java
//将所有的名字收集到List中去
List<String> list = emps.stream()
	.map(Employee::getName)
	.collect(Collectors.toList());
	
//将名字放入特殊集合中
HashSet<String> hs = emps.stream()
	.map(Employee::getName)
	.collect(Collectors.toCollection(HashSet::new));
```

- collect——将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法

#### 对流求值

```java
//最大值
Optional<Double> max = emps.stream()
	.map(Employee::getSalary)
	.collect(Collectors.maxBy(Double::compare));

//最小值
Optional<Employee> op = emps.stream()
	.collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

//总和
Double sum = emps.stream()
	.collect(Collectors.summingDouble(Employee::getSalary));

//平均值
Double avg = emps.stream()
	.collect(Collectors.averagingDouble(Employee::getSalary));

//总数
Long count = emps.stream()
	.collect(Collectors.counting());

```

#### 关于收集Map

```java
//普通的toMap，出现重复key会报错
Map<Long, String> map = userList.stream()
            .collect(Collectors.toMap(User::getId, User::getUsername);
                     
//重复key的value覆盖
Map<Long, String> map = userList.stream()
        .collect(Collectors.toMap(User::getId, User::getUsername, (v1, v2) -> v1));
                     
//重复key的value收集为List
userList.stream().collect(Collectors.toMap(
    User::getId,
    e -> Arrays.asList(e.getUsername()),
    (List<String> oldList, List<String> newList) -> {
                    oldList.addAll(newList);
                    return oldList;
    })
);
```
