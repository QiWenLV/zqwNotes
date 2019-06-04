在静态方法中使用Autowired注入的变量

```java
@Component 
public class AutoLoginUtil { 
    @Autowired 
    private IUserService userService; 

    private static AutoLoginUtil autoLoginUtil; 

    @PostConstruct 
    public void init() { 
        autoLoginUtil = this; 
        autoLoginUtil.userService = this.userService; 
    } 

    public static void autoLogin() { 
        autoLoginUtil.userService.queryUserAutoLogin(); 
    } 
} 
```



SpringBoot在Bean加载之前，进行初始化

继承 `CommandLineRunner` ，并实现它的 `run()` 方法。

```java
@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The Runner start to initialize ...");
    }
}
```

如果需要初始化很多资源，那么可以使用`@Order`进行排序。`@Order()`里面的值越小启动越早。

```java
@Component
@Order(1)
public class OrderRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The OrderRunner1 start to initialize ...");
    }
}
```



拦截器的配置

实现`WebMvcConfigurer`接口，用于管理拦截器

```java
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
  // 这个方法是用来配置静态资源的，比如html，js，css，等等
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      
  }
  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
  	registry
        .addInterceptor(loginInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/login", "/register");
  }
}
```

自定义拦截器需要实现`HandlerInterceptor `接口

```java

@Component
public class LoginInterceptor implements HandlerInterceptor {
 
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("user");
        //如果session中没有user，表示没登陆
        if (user == null){
            //这个方法返回false表示忽略当前请求，
            //如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            return false;
        }else {
            //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
            return true;    
        }
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
```

