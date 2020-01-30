package org.lzn.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * dbcp 连接工具
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/01/16 22:15
 */
public class DBCPUtil {

    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            // 根据 DBCPUtil 的 classes 的路径，加载配置文件
            properties.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
            ds = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化错误，请检查配置文件");
        }
    }

    /**
     * 获取连接
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-01-23 0:54
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("服务器忙！");
        }
    }

    /**
     * 释放资源
     *
     * @param connection 连接
     * @param statement 状态
     * @param resultSet 返回集
     * @author LinZhenNan lin_hehe@qq.com 2020-01-23 0:57
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
    // 私有
    // ------------------------------------------------------------------------------

    private DBCPUtil() {}
}
