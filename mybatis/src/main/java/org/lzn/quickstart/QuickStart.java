package org.lzn.quickstart;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.lzn.po.User;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis 快速入门
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/14 22:26
 */
public class QuickStart {

    @Test
    public void findUserByIdTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        User user = sqlSession.selectOne("quickstart.findUserById", 1);

        // 打印结果集
        System.out.println(user);

        // 5. 关闭资源
        sqlSession.close();
    }

    @Test
    public void findUserByUsernameTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        List<User> users = sqlSession.selectList("quickstart.findUserByUsername", "o");

        // 打印结果集
        System.out.println(users);

        // 5. 关闭资源
        sqlSession.close();
    }

    @Test
    public void insertUserTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession。sqlSessionFactory.openSession() 默认关闭自动提交事务，打开使用 sqlSessionFactory.openSession(true)
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 构建 user 参数，没有赋值的属性采取默认值
        User user = new User();
        user.setUsername("mybaties insert");
        user.setPassword("insertUserTest");
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        sqlSession.insert("quickstart.insertUser", user);

        // sqlSessionFactory.openSession() 默认关闭自动提交事务，所以增删改，要执行 commit 操作。
        sqlSession.commit();

        // 打印结果集
        System.out.println(user);

        // 5. 关闭资源
        sqlSession.close();
    }

    @Test
    public void insertUserOfSelectKeyMySqlTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession。sqlSessionFactory.openSession() 默认关闭自动提交事务，打开使用 sqlSessionFactory.openSession(true)
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 构建 user 参数，没有赋值的属性采取默认值
        User user = new User();
        user.setUsername("mybaties insert");
        user.setPassword("insertUserOfSelectKeyMySqlTest");
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        sqlSession.insert("quickstart.insertUserOfSelectKeyMySql", user);

        // sqlSessionFactory.openSession() 默认关闭自动提交事务，所以增删改，要执行 commit 操作。
        sqlSession.commit();

        // 打印结果集
        System.out.println(user);

        // 5. 关闭资源
        sqlSession.close();
    }

    @Test
    public void deleteUserTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession。sqlSessionFactory.openSession() 默认关闭自动提交事务，打开使用 sqlSessionFactory.openSession(true)
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        sqlSession.delete("quickstart.deleteUser", 10L);

        // sqlSessionFactory.openSession() 默认关闭自动提交事务，所以增删改，要执行 commit 操作。
        sqlSession.commit();

        // 5. 关闭资源
        sqlSession.close();
    }

    @Test
    public void updateUserTest() throws Exception {
        // 1. 读取配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 根据配置文件创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SQLSessionFactory 创建 SqlSession。sqlSessionFactory.openSession() 默认关闭自动提交事务，打开使用 sqlSessionFactory.openSession(true)
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. SqlSession 执行 statement，并返回映射结果
        // 构建 user 参数，没有赋值的属性采取默认值
        User user = new User();
        user.setId(15L);
        user.setUsername("mybaties update");
        user.setPassword("updateUserTest");
        // 第一个参数：statement 的 id，建议：namespace.statementId （确保唯一）
        // 第二个参数：入参的值，他的类型要和映射文件中对应的 statement 的入参类型一致
        sqlSession.update("quickstart.updateUser", user);

        // sqlSessionFactory.openSession() 默认关闭自动提交事务，所以增删改，要执行 commit 操作。
        sqlSession.commit();

        // 打印结果集
        System.out.println(user);

        // 5. 关闭资源
        sqlSession.close();
    }
}
