package org.lzn.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lzn.dao.AccountDao;
import org.lzn.entity.Account;
import org.lzn.util.C3P0Util;
import org.lzn.util.ManagerThreadLocal;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * AccountDao 实现类，学习 ThreadLocal 后更新实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/24 23:36
 */
public class AccountDaoImpl2 implements AccountDao {

    @Override
    public void updateAccount(String fromName, String toName, BigDecimal money) throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

        queryRunner.update("update account set money = money - ? where name = ?", money, fromName);
        queryRunner.update("update account set money = money + ? where name = ?", money, toName);
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();

        queryRunner.update(ManagerThreadLocal.getConnection(), "update account set money = ? where name = ?", account.getMoney(), account.getName());
    }

    @Override
    public Account findAccountByName(String name) throws Exception {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();

        return queryRunner.query(ManagerThreadLocal.getConnection(), "select * from account where name = ?", new BeanHandler<Account>(Account.class), name);
    }
}
