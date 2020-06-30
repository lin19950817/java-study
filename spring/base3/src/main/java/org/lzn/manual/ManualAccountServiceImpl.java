package org.lzn.manual;

import org.lzn.AccountTest;
import org.lzn.dao.AccountDao;
import org.lzn.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 手动管理事务，转账服务
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/29 23:33
 */
public class ManualAccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(final String outer, final String inner, final Integer money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                accountDao.out(outer, money);
                AccountTest.getException();
                accountDao.in(inner, money);
            }
        });
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
