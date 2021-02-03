package com.example.dao;

import com.example.domain.User;

import java.util.List;

public interface IUserDao {
    /*
     * 查询所有操作
     */
    List<User> findAll();
}
