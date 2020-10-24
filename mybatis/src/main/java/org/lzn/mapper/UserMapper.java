package org.lzn.mapper;

import org.lzn.po.User;

import java.util.List;

/**
 * 用户信息mapper
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/17 21:26
 */
public interface UserMapper {

    /**
     * 根据用户 id 来查询用户信息
     *
     * @param id 用户id
     * @return org.lzn.po.User
     * @author LinZhenNan lin_hehe@qq.com
     */
    User getUserById(Long id);

    /**
     * 根据用户名来模糊查询用户信息列表
     *
     * @param username 用户名称
     * @return java.util.List<org.lzn.po.User>
     * @author LinZhenNan lin_hehe@qq.com
     */
    List<User> listUsersByName(String username);

    /**
     * 添加用户
     *
     * @param user 待添加用户信息
     * @return int 受影响的行数
     * @author LinZhenNan lin_hehe@qq.com
     */
    int insertUser(User user);
}
