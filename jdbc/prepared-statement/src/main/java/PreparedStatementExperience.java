import com.lzn.entity.SysUser;
import com.lzn.entity.User;
import com.lzn.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author LinZhenNan 2019/08/19 15:09
 */
public class PreparedStatementExperience {

    public void select() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtils.getConnection();
            String sql = "select * from sys_user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "hehe");
            preparedStatement.setString(2, "demo03");
            resultSet = preparedStatement.executeQuery();
            List<SysUser> userList = new ArrayList<SysUser>();
            while (resultSet.next()) {
                SysUser user = new SysUser();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }

            System.out.println(userList);
        } catch (Exception e) {
            throw e;
        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
    }
}
