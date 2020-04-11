package org.lzn.struts;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 全局资源包的国际化信息
 *      获取到的是 message_zh_CN 或者 message_en_US
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/10 22:09
 */
public class DemoAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        String key = getText("key");
        System.out.println(key);
        return SUCCESS;
    }
}
