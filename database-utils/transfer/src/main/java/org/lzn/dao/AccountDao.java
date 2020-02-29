package org.lzn.dao;

import org.lzn.entity.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * 转账 dao
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 17:41
 */
public interface AccountDao {

    /**
     * 转账
     *
     * @param fromName 转出账户
     * @param toName 转入账户
     * @param money 转账金额
     * @author LinZhenNan lin_hehe@qq.com 2020-02-11 17:43
     */
    @Deprecated
    void updateAccount(String fromName, String toName, BigDecimal money) throws SQLException;

    /**
     * 根据账户信息修改金额
     *
     * @param account 账户实体
     * @author LinZhenNan lin_hehe@qq.com 2020-02-11 20:46
     */
    void updateAccount(Account account) throws SQLException;

    /**
     * 根据用户名查找账户信息
     *
     * @param name 用户名
     * @author LinZhenNan lin_hehe@qq.com 2020-02-11 20:51
     * @return org.lzn.entity.Account
     */
    Account findAccountByName(String name) throws Exception;
}
