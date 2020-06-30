package org.lzn.hibernate.cfg.service;

import org.lzn.hibernate.cfg.domain.User;

/**
 * UserService
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/08 23:40
 */
public interface UserService {
    /**
     * 保存用户
     *
     * @param user 实体
     * @author LinZhenNan lin_hehe@qq.com
     */
    void save(User user);
}
