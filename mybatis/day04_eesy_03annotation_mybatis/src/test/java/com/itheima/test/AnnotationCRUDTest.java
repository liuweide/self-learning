package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("annotation crud test");
        user.setAddress("枣庄");

        userDao.saveUser(user);
    }

    @Test
    public void testFindUserById(){
        User u = userDao.findUserById(41);
        System.out.println(u);
    }
}
