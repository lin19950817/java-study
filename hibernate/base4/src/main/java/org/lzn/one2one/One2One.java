package org.lzn.one2one;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.one2one.domain.Address;
import org.lzn.one2one.domain.Company;
import org.lzn.util.HibernateUtils;

/**
 * 一对一
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/28 23:40
 */
public class One2One {
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Company company = new Company();
        company.setName("度娘");

        Address address = new Address();
        address.setName("北京");

        // 使用外键维护一对一表关系时，只有在外键所在的对象才能维护关系
        address.setCompany(company);
        company.setAddress(address);

        session.save(company);
        session.save(address);

        session.getTransaction().commit();
        session.close();
    }
}
