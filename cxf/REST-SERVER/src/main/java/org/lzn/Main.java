package org.lzn;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.lzn.service.impl.StudentImpl;

/**
 * CXF 发布 REST 的服务（服务端）
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:26
 */
public class Main {
    public static void main(String[] args) {
        // JAXRSServerFactoryBean 发布 REST 的服务
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        // 设置服务实现类
        jaxrsServerFactoryBean.setServiceBean(new StudentImpl());
        // 设置资源类，如果有多个资源，可以用 ， 分隔
        jaxrsServerFactoryBean.setResourceClasses(StudentImpl.class);
        // 设置服务地址
        jaxrsServerFactoryBean.setAddress("http://127.0.0.1:12345/user");
        // 发布服务
        jaxrsServerFactoryBean.create();
    }
}
