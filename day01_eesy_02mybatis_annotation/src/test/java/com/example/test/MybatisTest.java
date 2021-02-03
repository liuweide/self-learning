package com.example.test;

import com.example.dao.IUserDao;
import com.example.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.spi.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class MybatisTest {
    private static final Logger log = Logger.getLogger(MybatisTest.class.toString());
    /**
     * 入门案例
     */
    public static void main(String[] args) throws Exception{
        log.info("=======info 信息提示==========");
        log.warning("=======warn 信息提示==========");
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
