package org.lzn.service.impl;

import org.lzn.AccountTest;
import org.lzn.dao.AccountDao;
import org.lzn.service.AccountService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 转账实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/28 23:52
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(String outer, String inner, Integer money) {
        accountDao.out(outer, money);
        AccountTest.getException();
        accountDao.in(inner, money);
    }

    //
    // setter
    // ------------------------------------------------------------------------------

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
