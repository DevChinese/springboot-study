package com.hua.restfulstarter.controller;

import com.hua.restfulstarter.domain.entity.User;
import com.hua.restfulstarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springboot")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public String selectUserById(@PathVariable int id) {
        return userService.selectUserById(id).toString();
    }

}
