package com.hua.security.controller;

import com.hua.security.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @Value("${spring.security.user.name}")
    private String username;
    @Value("${spring.security.user.password}")
    private String password;

    @PostMapping("login")
    public String login(@RequestBody User user, HttpSession session) {
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "登录成功";
        }
        return "账号或密码错误";
    }

    @PostMapping("logout")
    public String logout(HttpSession session) {
        // 退出登录就是将用户信息删除
        session.removeAttribute("user");
        return "退出登录成功";
    }

    @GetMapping("api")
    public String api(HttpSession session) {
        // 模拟各种api，访问之前都要检查有没有登录，没有登录就提示用户登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "请先登录";
        }
        // 如果有登录就调用业务层执行业务逻辑，然后返回数据
        return "成功返回数据";
    }

    @GetMapping("/api2")
    public String api2(HttpSession session) {
        // 模拟各种api，访问之前都要检查有没有登录，没有登录就提示用户登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "请先登录";
        }
        // 如果有登录就调用业务层执行业务逻辑，然后返回数据
        return "成功返回数据";
    }

    @GetMapping("/api3")
    public String api3() {
        return "成功返回数据";
    }
}
