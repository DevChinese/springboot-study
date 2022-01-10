package com.hua.restfulstarter.domain.mapper;

import com.hua.restfulstarter.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void addUser(User user);

    User selectUserById(int id);
}
