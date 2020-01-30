package org.lzn;

import org.lzn.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/01/28 0:57
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = C3P0Util.getConnection();
            preparedStatement = connection.prepareStatement("insert into testa values('3', 'test')");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0Util.release(connection, preparedStatement, null);
        }
    }
}
