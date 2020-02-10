package org.lzn;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import org.lzn.entity.Testa;
import org.lzn.util.C3P0Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * queryRnner 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/07 0:13
 */
public class TestQueryRunner {

    @Test
    public void test1() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        List<Testa> list = queryRunner.query("select * from testa", new ResultSetHandler<List<Testa>>() {
            // 当 query 方法执行 select 语句后，将结果集以参数形式传递过来
            @Override
            public List<Testa> handle(ResultSet resultSet) throws SQLException {
                List<Testa> list = new ArrayList<Testa>();
                while (resultSet.next()) {
                    Testa testa = new Testa(resultSet.getString(1), resultSet.getString(2));
                    list.add(testa);
                }
                return list;
            }
        });

        list.forEach(item -> System.out.println(item));
    }

    @Test
    public void testSelect() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 执行查询语句，并返回结果集
        List<Testa> list = queryRunner.query("select * from testa", new BeanListHandler<Testa>(Testa.class));

        list.forEach(item -> System.out.println(item));
    }

    @Test
    public void testSelectWithParameter() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 执行带参数查询语句，并返回结果集
        List<Testa> list = queryRunner.query("select * from testa where a = ?", new BeanListHandler<Testa>(Testa.class), "2");

        list.forEach(item -> System.out.println(item));
    }

    @Test
    public void testInsert() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 执行带参数插入语句
        queryRunner.update("insert into testa(a, b) values(?, ?)", "5", "testInsert");
    }

    @Test
    public void testUpdate() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 执行带参数更新语句
        queryRunner.update("update testa set b = ? where a = ?", "testUpdate", "5");
    }

    @Test
    public void testDelete() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 执行带参数更新语句
        queryRunner.update("delete from testa where a = ?", "5");
    }

    @Test
    public void testBatch() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

        // 高维代表执行多少次 sql 语句
        Object[][] params = new Object[5][];
        for (int i = 0; i < params.length; i++) {
            // 执行下面 sql 语句的参数
            params[i] = new Object[]{String.valueOf(i + 100), "testBatch"};
        }
        // 批处理，只能执行相同的 sql 语句
        queryRunner.batch("insert into testa(a, b) values(?, ?)", params);
    }
}
