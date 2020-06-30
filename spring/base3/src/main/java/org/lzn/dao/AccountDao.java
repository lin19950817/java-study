package org.lzn.dao;

/**
 * 转账 Dao
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/28 23:35
 */
public interface AccountDao {
    /**
     * 汇款
     *
     * @param outer 汇款人
     * @param money 金额
     * @author LinZhenNan lin_hehe@qq.com
     */
    void out(String outer, Integer money);
    
    /**
     * 收款
     *
     * @param inner 收款人
     * @param money 金额
     * @author LinZhenNan lin_hehe@qq.com
     */
    void in(String inner, Integer money);
}
