package org.lzn.service.impl;

import org.lzn.dao.AccountDao;
import org.lzn.dao.impl.AccountDaoImpl;
import org.lzn.entity.Account;
import org.lzn.service.AccountService;
import org.lzn.util.C3P0Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 账户服务实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 17:57
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void transfer(String fromName, String toName, BigDecimal money) throws SQLException {
        // 过时
//        accountDao.updateAccount(fromName, toName, money);

        Connection connection = C3P0Util.getConnection();
        AccountDao accountDao = new AccountDaoImpl(connection);
        try {
            // 关闭自动事务提交
            connection.setAutoCommit(false);
            // 分别得到转出和转入账户对象
            Account fromAccount = accountDao.findAccountByName(fromName);
            Account toAccount = accountDao.findAccountByName(toName);

            // 修改账户各自的金额
            fromAccount.setMoney(fromAccount.getMoney().subtract(money));
            toAccount.setMoney(toAccount.getMoney().add(money));

            // 完成转账操作
            accountDao.updateAccount(fromAccount);
            // 用于控制异常
//            int i = 1/0;
            accountDao.updateAccount(toAccount);

            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚事务
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
