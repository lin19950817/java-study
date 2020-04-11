package org.lzn.struts.struts2package.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 动作类级资源包的国际化信息
 *      获取到的是 Demo3Action_zh_CN 或者 Demo3Action_en_US
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/10 23:49
 */
public class Demo3Action extends ActionSupport {

    @Override
    public String execute() throws Exception {
        String key = getText("key");
        System.out.println(key);
        return SUCCESS;
    }
}
