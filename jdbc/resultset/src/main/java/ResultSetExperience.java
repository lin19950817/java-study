import com.lzn.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author LinZhenNan 2019/08/16 19:39
 */
public class ResultSetExperience {

    /**
     * getObject() 方法
     *
     *
     * @author LinZhenNan 2019-08-17 17:16
     * @return void
     */
    public void getObject() throws Exception{
        String sqlConnect = "jdbc:mysql:///java_web?user=root&password=root&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "select * from user_demo";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            /**
             * 封装数据的方法
             *  Object getObject(int columnIndex)   根据序号取值，索引从 1 开始
             *  Object getObject(String columnName)   根据列名取值
             */
            System.out.println(resultSet.getObject("userId"));
            System.out.println(resultSet.getObject("userName"));
            System.out.println("========================================================================================================");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 封装实体 list后返回
     *
     *
     * @author LinZhenNan 2019-08-17 17:18
     * @return void
     */
    public void returnEntity() throws Exception{
        String sqlConnect = "jdbc:mysql:///java_web?user=root&password=root&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "select * from user_demo";
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> userList = new ArrayList<User>();
        while(resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getDouble("userId"));
            user.setUserName(resultSet.getString("userName"));
            user.setUserSex(resultSet.getString("userSex"));
            userList.add(user);
        }

        resultSet.close();
        statement.close();
        connection.close();

        System.out.println(userList.toString());

    }

    /**
     * boolean next()   将光标从当前位置向前移一行
     * boolean previous()   将光标移动到此 ResultSet对象的上一行
     * boolean absolte(int row) 参数是当前行的索引，从 1 开始根据行的索引定位移动的指定索引行
     * void afterLast() 将光标移动到末尾，正好位于最后一行之后
     * void beforeFirst()   将光标移动到开头，正好位于第一行之前
     *
     * @author LinZhenNan 2019-08-17 19:28
     * @return void
     */
    public void moveCursor() throws Exception{

        String sqlConnect = "jdbc:mysql:///java_web?user=root&password=root&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(sqlConnect);
        Statement statement = connection.createStatement();

        String sql = "select * from user_demo";
        ResultSet resultSet = statement.executeQuery(sql);

        // 移动到最后一行之后
        resultSet.afterLast();
        // 移动上一行
        resultSet.previous();
        User user = new User();
        user.setUserId(resultSet.getDouble("userId"));
        user.setUserName(resultSet.getString("userName"));
        user.setUserSex(resultSet.getString("userSex"));
        System.out.println("最后一行数据：" + user);
    }
}
