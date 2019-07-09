## 概念

GraphQL 既是一种用于 API 的查询语言

GraphQL 对你的 API 中的数据提供了一套易于理解的完整描述，使得客户端能够**准确地**获得它需要的数据，而且没有任何冗余，

特点

- 请求你所要的数据，不多不少。因为控制数据的是应用，而不是服务器
- 获取多个资源，只用一个请求

## GraphQL与RESTful有什么区别

### 1. 入口

RESTful 的核心理念在于资源 (resource)，且讲究一个 RESTful 接口仅操作单一资源；因此在你使用 RESTful 时，会设计出大量的接口。

GraphQL 是单一入口，一般配置在 `[host]/graphql/`，所有的资源都从该入口通过 graphql 的语句获取或修改

### 2. 数据的关联性

RESTful 所操作的资源相对是离散的；而 GraphQL 的数据更有整体性。

用例：查询一个User A朋友的朋友

- RESTful接口

  ```http
  GET /user/:userId/friends/
  ```

  如果A有20个朋友，那么这个接口要查询21次，才能得到结果

- GraphQL查询

  ```json
  # 先定义一个User的type
  type User {
    id: ID!
    name: String!
    friends: [User]
  }
  #Graph root上挂一个Node
  type Query {
      user(id: ID!): User
  }
  ```

  那么查询的query就可以写成：

  ```json
  query ($userId: ID) {
    user(id: $userId) {
      name
      friends {
        name
        friends {
          name
        }
      }
    }
  }
  ```

## GraphQL 与 RESTful 相比有什么优点？

1. 数据的关联性和结构化更好
2. 更易于前端缓存数据：Relay，apollo-client。[缓存原理](https://graphql.org/learn/caching/)
3. Versionless API：在root Query中添加新的Node就可以了。
4. 更健壮的接口
5. 推送信息的解决方案subscription





