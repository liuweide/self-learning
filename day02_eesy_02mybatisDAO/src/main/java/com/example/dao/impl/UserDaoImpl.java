package com.example.dao.impl;

import com.example.dao.IUserDao;
import com.example.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }

    @Override
    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        //参数就是能获取配置信息的key
        List<User> users = sqlSession.selectList("com.example.dao.IUserDao.findAll");
        //3.释放资源
        sqlSession.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        //参数就是能获取配置信息的key
        sqlSession.insert("com.example.dao.IUserDao.saveUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        //参数就是能获取配置信息的key
        sqlSession.update("com.example.dao.IUserDao.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public User findById(Integer userId) {
        return null;
    }

    @Override
    public List<User> findByName(String username) {
        return null;
    }
}
