package com.hua.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询数据库看用户是否存在，若不存在抛出 UsernameNotFoundException 异常
        if (! "admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        // 把查询出来的密码（注册时已经加密过）进行解析，或者直接把密码放入构造方法
        String passworld = passwordEncoder.encode("123");
        return new User(username, passworld, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal"));
    }
}
