package org.lzn.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * c3p0 工具类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/01/28 0:30
 */
public class C3P0Util {

    /**
     * 数据源
     */
    private static DataSource dataSource = new ComboPooledDataSource();


    /**
     * 获取连接
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-01-28 0:55
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("服务器错误");
        }
    }

    /**
     * 释放资源
     *
     * @param connection 连接
     * @param statement 状态
     * @param resultSet 返回集
     * @author LinZhenNan lin_hehe@qq.com 2020-01-28 0:54
     * @return void
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        // 关闭资源
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
    }

    //
    // 私有方法
    // ------------------------------------------------------------------------------

    private C3P0Util() {}

    //
    // getter、setter
    // ------------------------------------------------------------------------------

    public static DataSource getDataSource() {
        return dataSource;
    }
}
