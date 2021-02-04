package com.example.test;

import com.example.dao.IUserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        /**
         * 使用dao类实现初始化
         */
        //3.使用工厂对象，创建dao对象
        userDao = new UserDaoImpl(factory);

//        /**
//         * 使用mybatis框架初始化
//         */
//        //3.使用工厂生产sqlSession对象，
//        sqlSession = factory.openSession();
//        //4.使用SqlSession创建Dao接口的代理对象
//        userDao=session.getMapper(IUserDao.class);
    }
    @After
    public  void destroy() throws Exception{
        //6.释放资源
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setUsername("dao impl 张三");
        user.setAddress("成都");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setId(49);
        user.setUsername("lisa");
        user.setAddress("成都");
        user.setSex("女");
        user.setBirthday(new Date());
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

}
