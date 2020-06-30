package org.lzn.dao.impl;

import org.lzn.dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 转账接口实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/28 23:42
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Override
    public void out(String outer, Integer money) {
        String sql = "UPDATE account SET money = money - ? WHERE `name` = ?";
        this.getJdbcTemplate().update(sql, money, outer);
    }

    @Override
    public void in(String inner, Integer money) {
        String sql = "UPDATE account SET money = money + ? WHERE `name` = ?";
        this.getJdbcTemplate().update(sql, money, inner);
    }
}
