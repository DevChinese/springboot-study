# spring security

## Authentication 和 Access Control
应用程序安全性归结为两个或多或少独立的问题： authentication（你是谁？）和 authorization（你可以做什么？）。
有时人们会说“access control”而不是“authorization”，这可能会让人感到困惑，但这样想可能会有所帮助，因为“授权”在其他地方被重载了。
Spring Security 的架构旨在将身份验证与授权分开，并为两者提供策略和扩展点。

### Authentication
authentication最重要的接口是 AuthenticationManager，它仅有一个方法
```java
public interface AuthenticationManager {

  Authentication authenticate(Authentication authentication)
    throws AuthenticationException;
}
```
AuthenticationManager 可以在 authenticate() 方法中做一下3件事之一:
- Return an Authentication (normally with authenticated=true) if it can verify that the input represents a valid principal.
- Throw an AuthenticationException if it believes that the input represents an invalid principal.
- Return null if it cannot decide.

AuthenticationException 是一个runtime异常。它通常由应用程序以通用方式处理，具体取决于应用程序的样式或用途。 换句话说，通常不期望用户代码来捕获和处理它。
例如，Web UI 可能会呈现一个页面，显示身份验证失败，并且后端 HTTP 服务可能会发送 401 响应，根据上下文是否有 WWW-Authenticate 标头。

AuthenticationManager 最常用的实现是 ProviderManager，它委托给一个 AuthenticationProvider 实例链。
AuthenticationProvider 有点像 AuthenticationManager，但它有一个额外的方法允许调用者查询它是否支持给定的 Authentication 类型：
```java
public interface AuthenticationProvider {

	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;

	boolean supports(Class<?> authentication);
}
```
supports() 方法中的 Class<?> 参数实际上是 Class<? extends Authentication> （它只会被问到它是否支持传递给 authenticate() 方法的东西）。
ProviderManager 可以通过委托给一个 AuthenticationProvider 链来支持同一应用程序中的多种不同的身份验证机制。 如果 ProviderManager 不能识别特定的 Authentication 实例类型，则会跳过它。
A ProviderManager has an optional parent, which it can consult if all providers return null. 
If the parent is not available, a null Authentication results in an AuthenticationException.
Sometimes, an application has logical groups of protected resources (for example, all web resources that match a path pattern, such as /api/**), and each group can have its own dedicated AuthenticationManager.
Often, each of those is a ProviderManager, and they share a parent. The parent is then a kind of “global” resource, acting as a fallback for all providers.
![authentication](images/authentication.png)
<center>Figure 1. An AuthenticationManager hierarchy using ProviderManager</center>

### Customizing Authentication Managers
Spring Security provides some configuration helpers to quickly get common authentication manager features set up in your application.
The most commonly used helper is the `AuthenticationManagerBuilder`, which is great for setting up in-memory, JDBC, or LDAP user details or for adding a custom `UserDetailsService`.
The following example shows an application that configures the global (parent) `AuthenticationManager`:
```java
@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

   ... // web stuff here

  @Autowired
  public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) {
    builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
      .password("secret").roles("USER");
  }

}
```

This example relates to a web application, but the usage of `AuthenticationManagerBuilder` is more widely applicable (see [Web Security](https://spring.io/guides/topicals/spring-security-architecture#web-security) for more detail on how web application security is implemented).
Note that the AuthenticationManagerBuilder is @Autowired into a method in a `@Bean` that is what makes it build the global (parent) `AuthenticationManager`. 
In contrast, consider the following example:
```java
@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

   ... // web stuff here

  @Override
  public void configure(AuthenticationManagerBuilder builder) {
    builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
      .password("secret").roles("USER");
  }

}
```

