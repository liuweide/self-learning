package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 黑马程序员
 * 在mybatis中针对，CRUD一共有四个注解
 * //@Select @Insert @Update @Delete
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     *
     * @return 所有用户
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
//            主键映射，id=true， property是用户类属性，column是数据库对应列名
            @Result(id = true, property = "id", column = "id"),
//            非主见，id默认为false
            @Result(property = "username", column = "username"),
            @Result(property = "address", column = "address"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday")
    })
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    @ResultMap(value={"userMap"})
    User findUserById(Integer userid);

    @Insert("insert into user(username, address, sex, birthday) values(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

}
