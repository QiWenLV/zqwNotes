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



## 操作



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