package org.lzn;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;
import org.lzn.entity.Testa;
import org.lzn.util.C3P0Util;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * resultSetHandler 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/07 0:14
 */
public class TestResultSetHandler {

    @Test
    public void testArrayHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // ArrayHandler: 适合取 1 条记录。把该条记录的每列值封装到一个数组中 Object[]
        Object[] objects = queryRunner.query("select * from testa", new ArrayHandler());

        for (Object obj : objects) {
            System.out.println(obj);
        }
    }

    @Test
    public void testArrayListHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // ArrayListHandler: 适合取多条记录。把每条记录的每列值封装到一个数组中 Object[]，把数组封装到一个 List 中
        List<Object[]> query = queryRunner.query("select * from testa", new ArrayListHandler());

        for (Object[] objects : query) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testColumnListHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // ColumnListHandler: 取某一列的数据。封装 List 中。
        List<Object> query = queryRunner.query("select * from testa", new ColumnListHandler<>(1));

        query.forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void testKeyedHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // 外层 Map 的 key 是表中的列数据 id 的值，里层 Map 的 key 是表的列名。外层 Map 的 key 是 Object 类型，里层 Map 的 key 是 String 类型
        Map<Object, Map<String, Object>> map = queryRunner.query("select * from testa", new KeyedHandler<>());

        map.forEach((key, value) -> {
            System.out.println(key + "\t" + value);
        });
    }

    @Test
    public void testMapHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // Map 的 key 是 列名，value 是 列的值。
        // MapHandler: 适合取 1 条记录。把当前记录的列名和列值放到一个 Map 中
        Map<String, Object> map = queryRunner.query("select * from testa where a = ?", new MapHandler(), "1");

        map.forEach((key, value) -> {
            System.out.println(key + "\t" + value);
        });
    }

    @Test
    public void testMapListHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // MapListHandler: 适合取多条记录。把每条记录封装到一个 Map 中，再把 Map 封装到 List 中
        List<Map<String, Object>> query = queryRunner.query("select * from testa", new MapListHandler());

        query.forEach(item -> {
            item.forEach((key, value) -> System.out.println(key + "\t" + value));
        });
    }

    @Test
    public void testScalarHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // ScalarHandler: 适合取单行单列数据。下方 new ScalarHandler<>(2) 的 2 代表第二列
        Object query = queryRunner.query("select * from testa", new ScalarHandler<>(2));

        System.out.println(query);
    }

    @Test
    public void testBeanHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // BeanHandler：适合取单行单列数据。封装到 Testa
        Testa query = queryRunner.query("select * from testa", new BeanHandler<Testa>(Testa.class));

        System.out.println(query);
    }

    @Test
    public void testBeanListHandler() throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        // BeanListHandler：适合取多条记录。封装到 List<Testa>
        List<Testa> query = queryRunner.query("select * from testa", new BeanListHandler<Testa>(Testa.class));

        query.forEach(item -> System.out.println(item));
    }
}
