package org.lzn.advance;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.lzn.dto.CompanyDTO;
import org.lzn.dto.CustomerDTO;

import java.io.InputStream;

/**
 * 一对一，一对多 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 22:58
 */
public class AdvanceMapperTest {

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
    public void getCompanyByResultType() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        AdvanceMapper mapper = sqlSession.getMapper(AdvanceMapper.class);

        CompanyDTO company = mapper.getCompanyByResultType(1L);

        System.out.println(company);
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void getCompanyByResultMap() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        AdvanceMapper mapper = sqlSession.getMapper(AdvanceMapper.class);

        CompanyDTO company = mapper.getCompanyByResultMap(1L);

        System.out.println(company);
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void getCustomer() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        AdvanceMapper mapper = sqlSession.getMapper(AdvanceMapper.class);

        CustomerDTO customer = mapper.getCustomer(1L);

        System.out.println(customer);
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void getCustomerLazyLoad() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        AdvanceMapper mapper = sqlSession.getMapper(AdvanceMapper.class);

        CustomerDTO customer = mapper.getCustomerLazyLoad(1L);

        // 不查询订单信息则打印 customer.getCustomerName()
        System.out.println(customer);
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void oneLevelCache() {
        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession，获取mapper接口的动态代理对象
        AdvanceMapper mapper = sqlSession.getMapper(AdvanceMapper.class);

        // 第一次查询
        CustomerDTO customer = mapper.getCustomerLazyLoad(1L);
        System.out.println(customer);

        // 第二次查询
        CustomerDTO customer2 = mapper.getCustomerLazyLoad(1L);
        System.out.println(customer2);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void twoLevelCache() {
        // 创建 Mapper 对象
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        // 由 mybatis 通过 sqlsession 来创建代理对象
        AdvanceMapper mapper1 = sqlSession1.getMapper(AdvanceMapper.class);
        AdvanceMapper mapper2 = sqlSession2.getMapper(AdvanceMapper.class);
        AdvanceMapper mapper3 = sqlSession3.getMapper(AdvanceMapper.class);

        // 第一次查询
        CustomerDTO customer1 = mapper1.getCustomerLazyLoad(1L);
        System.out.println(customer1);
        // 在 close 的时候，才会将数据写进二级缓存
        sqlSession1.close();

        // 第二次查询
        CustomerDTO customer2 = mapper2.getCustomerLazyLoad(1L);
        System.out.println(customer2);
        sqlSession2.close();

//        CustomerDTO customer3 = mapper3.getCustomerLazyLoad(1L);

        // 关闭资源
        sqlSession2.close();
        sqlSession3.close();
    }
}