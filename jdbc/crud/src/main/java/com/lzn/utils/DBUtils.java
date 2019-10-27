package com.lzn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * description
 *
 * @author LinZhenNan 2019/08/19 11:15
 */
public class DBUtils {
    public static final String DRIVER_CLASS;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;

    static {
        // 此对象用于加载 properties文件的数据
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dbInfo");
        DRIVER_CLASS = resourceBundle.getString("driverClass");
        URL = resourceBundle.getString("url");
        USERNAME = resourceBundle.getString("username");
        PASSWORD = resourceBundle.getString("password");
    }


    /**
     * 得到连接的方法
     *
     * @author LinZhenNan 2019-08-19 11:27
     * @return java.sql.Connection
     */
    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 关闭资源
     *
     * @param resultSet
 * @param statement
 * @param connection
     * @author LinZhenNan 2019-08-19 11:30
     * @return void
     */
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
