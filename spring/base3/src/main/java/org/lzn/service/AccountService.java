package org.lzn.service;

/**
 * 转账服务
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/28 23:48
 */
public interface AccountService {
    /**
     * 转账
     *
     * @param outer 汇款人
     * @param inner 收款人
     * @param money 金额
     * @author LinZhenNan lin_hehe@qq.com
     */
    void transfer(String outer, String inner, Integer money);
}
