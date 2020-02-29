package org.lzn.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lzn.dao.AccountDao;
import org.lzn.entity.Account;
import org.lzn.util.C3P0Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 转账 Dao 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 17:47
 */
public class AccountDaoImpl implements AccountDao {

    private Connection connection;

    public AccountDaoImpl() {}

    public AccountDaoImpl(Connection connection) {
        this.connection = connection;
    }

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

        queryRunner.update(this.connection, "update account set money = ? where name = ?", account.getMoney(), account.getName());
    }

    @Override
    public Account findAccountByName(String name) throws Exception {
        // 创建一个 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();

        return queryRunner.query(this.connection, "select * from account where name = ?", new BeanHandler<Account>(Account.class), name);
    }
}
