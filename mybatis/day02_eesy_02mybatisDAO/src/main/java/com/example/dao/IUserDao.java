package com.example.dao;

import com.example.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     */
    List<User> findAll();

    /**
     *
     * @param user
     */
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);

    User findById(Integer userId);

    List<User> findByName(String username);

}
