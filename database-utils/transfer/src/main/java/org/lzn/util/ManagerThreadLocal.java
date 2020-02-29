package org.lzn.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/24 23:20
 */
public class ManagerThreadLocal {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 得到一个连接
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:21
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        // 从当前线程中取出一个连接
        Connection connection = threadLocal.get();
        if (connection == null) {
            // 从线程池中取出一个
            connection = C3P0Util.getConnection();
            // 把 Connection 对象放入到当前线程中
            threadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 开始事务
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:26
     */
    public static void startTransction() {
        try {
            Connection connection = getConnection();
            // 从当前线程对象中取出连接，并开始事务
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:28
     */
    public static void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:29
     */
    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:33
     */
    public static void close() {
        try {
            // 把连接放回连接池中
            getConnection().close();
            // 把当前线程对象中的 Connection 移除
            threadLocal.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
