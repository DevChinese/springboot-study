package com.hua.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        // 表单提交
//        http.formLogin()
//                // 必须和login.html中表单的action是一样的，去执行UserDetailsServiceImpl
//                .loginProcessingUrl("/login")
//                // 自定义登录页面
//                .loginPage("/templates/login.html");
//
//        http.authorizeHttpRequests()
//                // /login.html 不需要被认证，复杂会不断重定向到login页面
//                .antMatchers("/templates/login.html").permitAll()
//                // 所有需求都必须被认证，必须登录后才能访问
//                .anyRequest().authenticated();
//
//        // 关闭csrf防护
//        http.csrf().disable();

        http
                .authorizeHttpRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }

    /**
     * 配置加密算法
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
