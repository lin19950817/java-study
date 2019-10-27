import com.lzn.entity.User;
import com.lzn.utils.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author LinZhenNan 2019/08/16 17:01
 */
public class Crud {

    public void insert() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String sqlConnect = "jdbc:mysql://localhost:3306/java_web?user=root&password=hehe&useUnicode=true&characterEncoding=utf-8&useSSL=true";
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "insert into user_demo values(666, 'insertCreate', 'm')";
        // 执行
        int rows = statement.executeUpdate(sql);
        // 要么成功，要么抛异常
        if (rows > 0) {
            System.out.println("success");
        }


        // 关闭资源
        statement.close();
        connection.close();
    }


    public void update() throws Exception{
        String sqlConnect = "jdbc:mysql:///java_web?user=root&password=hehe&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "update user_demo set userName = 'crudUpdate' where userId = 666";
        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("success");
        }

        statement.close();
        connection.close();
    }

    public void delete() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String sqlConnect = "jdbc:mysql:///java_web?user=root&password=hehe&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "DELETE from user_demo where userId = 666";
        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("success");
        }

        statement.close();
        connection.close();
    }


    public void selectByUtils() throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select * from user_demo";
            resultSet = statement.executeQuery(sql);
            List<User> userList = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getDouble("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserSex(resultSet.getString("userSex"));
                userList.add(user);
            }

            System.out.println(userList);
        } catch (Exception e) {
            throw e;
        } finally {
            DBUtils.closeAll(resultSet, statement, connection);
        }
    }
}
