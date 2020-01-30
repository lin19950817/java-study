import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Properties;

/**
 * description
 *
 * @author LinZhenNan 2019/08/15 16:44
 */
public class FirstExperience {


   /**
    *
    * jdbc
    *
    * @author LinZhenNan 2019-08-16 11:30
    * @return void
    */
   public void first() throws Exception {

       /**
        * 注册驱动
        * 弊端
        *      导致驱动被注册两次
        *      强烈依赖数据库的驱动 jar
        *
        * 使用加载驱动
        */
       DriverManager.registerDriver(new Driver());

       /// 连接字符串
       String sqlConnect = "jdbc:mysql://localhost:3306/java_web?useUnicode=true&characterEncoding=utf-8&useSSL=false";

       // 获取连接 Connection
       Connection connection = DriverManager.getConnection(sqlConnect, "root", "root");

       // 后去执行 sql语句的对象 statement
       Statement statement = connection.createStatement();

       // sql语句
       String sql = "select * from user_demo";

       /**
        *  执行 sql语句，并返回结果
        *   ResultSet executeQuery(String sql)  根据查询语句返回结果集。只能执行 select语句
        *   int executeUpdate(String sql)   根据执行的 DML语句，返回受影响的行数
        */
       ResultSet resultSet = statement.executeQuery(sql);

       // 处理结果
       while(resultSet.next()) {
           System.out.println(resultSet.getObject(1));
           System.out.println(resultSet.getObject(2));
           System.out.println(resultSet.getObject(3));
           System.out.println("========================================================================================================");
       }

       // 关闭资源
       resultSet.close();
       statement.close();
       connection.close();
   }

   /**
    * jdbc2 使用加载驱动和 properties
    *
    *
    * @author LinZhenNan 2019-08-16 15:05
    * @return void
    */
   public void second() throws Exception {
       // 加载驱动
       Class.forName("com.mysql.jdbc.Driver");
       String sqlConnect = "jdbc:mysql://localhost:3306/java_web?useUnicode=true&characterEncoding=utf-8&useSSL=false";
       Properties info = new Properties();
       info.setProperty("user", "root");
       info.setProperty("password", "root");
       Connection connection = DriverManager.getConnection(sqlConnect, info);
       Statement statement = connection.createStatement();
       String sql = "select * from user_demo";
       ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + " - " + resultSet.getObject(1).getClass().getName());
            System.out.println(resultSet.getObject(2) + " - " + resultSet.getObject(2).getClass().getName());
            System.out.println(resultSet.getObject(3) + " - " + resultSet.getObject(3).getClass().getName());
            System.out.println("========================================================================================================");
        }

        resultSet.close();
        statement.close();
        connection.close();
   }

   /**
    * jdbc3 url传值方式
    *
    *
    * @author LinZhenNan 2019-08-16 16:26
    * @return void
    */
   public void third() throws Exception {
       Class.forName("com.mysql.jdbc.Driver");
       String sqlConnect = "jdbc:mysql://localhost:3306/java_web?user=root&password=root";
       Connection connection = DriverManager.getConnection(sqlConnect);
       Statement statement = connection.createStatement();
       String sql = "select * from user_demo";
       ResultSet resultSet = statement.executeQuery(sql);
       while (resultSet.next()) {
           System.out.println(resultSet.getObject(1));
           System.out.println(resultSet.getObject(2));
           System.out.println(resultSet.getObject(3));
           System.out.println("========================================================================================================");
       }

       resultSet.close();
       statement.close();
       connection.close();

   }
}
