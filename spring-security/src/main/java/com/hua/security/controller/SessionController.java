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

//    @PostMapping("/login")
//    public String login() {
//        return "/login";
//    }

    @GetMapping("/api")
    public String api() {
        return "成功返回数据";
    }
}
