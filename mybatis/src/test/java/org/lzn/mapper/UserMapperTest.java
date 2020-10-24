package org.lzn.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.lzn.po.User;

import java.io.InputStream;
import java.util.List;

/**
 * 用户mapper测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/17 21:33
 */
public class UserMapperTest {

    /**
     * 声明全局的 SqlSessionFactory
     */
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        // 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2、根据配置文件创建SqlSessionFactory
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void getUserById() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用mapper对象的方法
        User user = userMapper.getUserById(1L);

        System.out.println(user);
        // 关闭SqlSession
        sqlSession.close();

    }

    @Test
    public void listUsersByName() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用mapper对象的方法
        List<User> list = userMapper.listUsersByName("o");

        System.out.println(list);
        // 关闭SqlSession
        sqlSession.close();

    }

    @Test
    public void insertUser() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //构造User对象
        User user = new User();
        user.setUsername("mybatis mapper insert");
        user.setPassword("insertUser");

        // 调用mapper对象的方法
        userMapper.insertUser(user);

        System.out.println(user.getId());

        //执行SqlSession的commit操作
        sqlSession.commit();
        // 关闭SqlSession
        sqlSession.close();

    }
}