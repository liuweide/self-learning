package com.example.test;

import com.example.dao.IUserDao;
import com.example.domain.QueryVo;
import com.example.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public  void destroy() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setUserName("modified 张三");
        user.setUserAddress("成都");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setUserId(49);
        user.setUserName("lisa");
        user.setUserAddress("成都");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception{
        userDao.deleteUser(49);
    }

    @Test
    public void testFindById() throws Exception{
        User user = userDao.findById(49);
        if(user != null) {
            System.out.println(user);
        }else{
            System.out.println("查无此人");
        }
    }

    @Test
    public void testFindByName() throws Exception{
        List<User> users = userDao.findByName("%"+"王"+"%");
        if(users != null) {
            for(User user : users){
                System.out.println(user);
            }
        }else{
            System.out.println("不存在");
        }
    }

    @Test
    public void testFindByVo() throws Exception{
        User user = new User();
        user.setUserName("%王%");

        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }

    }
}
