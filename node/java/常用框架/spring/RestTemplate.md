## RestTempalate 基本使用

RestTemplate是一个**http请求的客户端工具**，它不是类似HttpClient的东东，也不是类似Jackson，jaxb等工具，但它封装了这些工具．



使调用端无须关心http接口响应的消息是什么格式，统统使用Java pojo来接收请求结果．它主要做了以下事情：

- 封装并屏蔽了http通信细节
- 组装Http请求参数
- 抽取结果并转成调用端指定的Java pojo对象
- 屏蔽了不同消息格式的差异化处理

在http通信的底层，它默认采用的是 JDK 的标准设施，当然你也可以切换成其它的http类库 例如`ApacheHttpComponents`,`Netty`, `OkHttp`等.

RestTemplate这个类是用在同步调用上的，异步调用请移步AsyncRestTemplate

### 简单案例

get请求

```java
RestTemplate restTemplate = new RestTemplate();
Map<String, Object> params = new HashMap<>();
params.put("id", 666106231640L);
String url = "http://www.baidu.com";
MyResult result = restTemplate.getForObject(url, MyResult.class, params);
```

post请求

```java
URI uri = URI.create(url);
String ans = restTemplate.postForObject(uri, request, String.class);
System.out.println(ans);
```

常用方法：

```java
DELETE    	delete(String, Object...)
GET     	getForObject(String, Class<T>, Object...)
GET      	getForEntity(String, Class<T>, Object...)
HEAD    	headForHeaders(String, Object...)
OPTIONS     optionsForAllow(String, Object...)
POST    	postForLocation(String, Object, Object...)
POST    	postForObject(String, Object, Class<T>, Object...)
PUT    		put(String, Object, Object...)
any    		exchange(String, HttpMethod, HttpEntity<?>, Class<T>, Object...)
any    		execute(String, HttpMethod, RequestCallback, ResponseExtractor<T>, Object...)
```

需要跳转的时候可以使用postForLocation，返回对象为URL，比如登录注册跳转