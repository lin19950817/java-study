package org.lzn.service;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * 账户服务
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 17:55
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param fromName 转出账户
     * @param toName 转入账户
     * @param money 转账金额
     * @author LinZhenNan lin_hehe@qq.com 2020-02-11 17:57
     */
    void transfer(String fromName, String toName, BigDecimal money) throws SQLException;
}
