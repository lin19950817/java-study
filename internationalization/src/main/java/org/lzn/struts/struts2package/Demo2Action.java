package org.lzn.struts.struts2package;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 包级资源包的国际化信息
 *      获取到的是 package_zh_CN 或者 package_en_US
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/10 22:22
 */
public class Demo2Action extends ActionSupport {

    @Override
    public String execute() throws Exception {
        String key = getText("key");
        System.out.println(key);
        return SUCCESS;
    }
}
