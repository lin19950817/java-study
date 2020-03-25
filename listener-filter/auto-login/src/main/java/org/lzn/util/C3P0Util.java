package org.lzn.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * C3P0 工具
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 22:03
 */
public class C3P0Util {
    /**
     * 数据源
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 获取数据源
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:11
     * @return javax.sql.DataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 从数据源得到一个连接对象
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:05
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("服务器错误");
        }
    }

    /**
     * 关闭资源
     *
     * @param connection 连接对象
     * @param statement 执行对象
     * @param resultSet 结果集
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:08
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null){
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
            connection = null;
        }
    }
}
