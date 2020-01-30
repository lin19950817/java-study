package org.lzn;

import org.lzn.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/01/16 23:16
 */
public class Main {

    public static void main(String[] args) {
        Connection connection;
        PreparedStatement preparedStatement = null;

        connection = DBCPUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement("select * from testa");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.release(connection, preparedStatement, null);
        }
    }
}
