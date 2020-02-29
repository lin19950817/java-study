package org.lzn.service.impl;

import org.lzn.dao.AccountDao;
import org.lzn.dao.impl.AccountDaoImpl2;
import org.lzn.entity.Account;
import org.lzn.service.AccountService;
import org.lzn.util.ManagerThreadLocal;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * AccountService 实现类，学习 ThreadLocal 后更新实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/24 23:42
 */
public class AccountServiceImpl2 implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl2();

    @Override
    public void transfer(String fromName, String toName, BigDecimal money) throws SQLException {
        try {
            // 开始事务
            ManagerThreadLocal.startTransction();
            // 分别得到转出和转入账户对象
            Account fromAccount = accountDao.findAccountByName(fromName);
            Account toAccount = accountDao.findAccountByName(toName);

            // 修改账户各自的金额
            toAccount.setMoney(toAccount.getMoney().add(money));
            fromAccount.setMoney(fromAccount.getMoney().subtract(money));

            // 完成转账操作
            accountDao.updateAccount(fromAccount);
            // 用于控制异常
//            int i = 1/0;
            accountDao.updateAccount(toAccount);

            // 提交事务
            ManagerThreadLocal.commit();
        } catch (Exception e) {
            // 回滚事务
            ManagerThreadLocal.rollback();
        } finally {
            ManagerThreadLocal.close();
        }
    }
}
