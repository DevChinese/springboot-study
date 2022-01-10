package com.hua.restfulstarter.service;

import com.hua.restfulstarter.domain.entity.User;
import com.hua.restfulstarter.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }
}
