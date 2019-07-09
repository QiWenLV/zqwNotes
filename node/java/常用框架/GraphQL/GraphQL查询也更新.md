

## 查询

接口

```java
@GraphQLQuery
public Project project(String code) {
    return repo.byCode(code);
}
```

project的type

```json
type Project {
    code: String
    currentFunding: Long!
    name: String
    tags: [String]
    tasks: [Task]
}
```

查询语法

```json
query ProjectQuery {
  project(code: "AXX"){
    code
    currentFunding
    name
    tags
  }
}
```

## 变更

新增

```json
mutation CreateProjectForEpisode {
  createProject(name: "a", tags: ["a", "b"]){
    name
    tags
    code
  }
}
```

修改

```json
mutation{
  updateTask(
    code: "AXX-6", 
    description: "bbbb",
    status: READY
  ){
    description
    code
    project{
      code
 			currentFunding
    }
    type
    status
  }
}
```

删除

```

```

分页

```json
#从 $userId 的第 4 个朋友开始算起，取前 2 个朋友。
query ($userId: ID) {
  user(id: $userId) {
    name
    friends(first: 2, offset: 3) {
      name
    }
  }
}
```

类似地，分页接口还可以设计为

- `friends(first: 2, after: $friendId)`;
- `friends(first: 2, after: $friendCursor)`

无论分页接口设计成怎么样，都需要**前后端共同的**封装与支持。其中 Relay 风格的分页接口在各大前后端 GraphQL 框架中基本都已有比较完整的实现。

