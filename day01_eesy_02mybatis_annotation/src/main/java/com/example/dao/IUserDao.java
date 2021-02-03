package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    /*
     * 查询所有操作
     */
    @Select("select * from user;")
    List<User> findAll();
}
