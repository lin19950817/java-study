package org.lzn.many2many;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.lzn.many2many.domain.Course;
import org.lzn.many2many.domain.Student;
import org.lzn.many2many.util.HibernateUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 多对多关系
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/25 11:03
 */
public class Many2Many {
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Student salamence = new Student();
        salamence.setName("salamence");

        Student dragonite = new Student();
        dragonite.setName("dragionite");

        Course struts2 = new Course();
        struts2.setName("struts2");

        Course hibernate = new Course();
        hibernate.setName("hibernate");

        Course spring = new Course();
        spring.setName("spring");

        // 维护关系
        salamence.getCourses().add(struts2);
        salamence.getCourses().add(hibernate);
        salamence.getCourses().add(spring);

        // 维护关系
        dragonite.getCourses().add(struts2);
        dragonite.getCourses().add(hibernate);
        dragonite.getCourses().add(spring);

        // 保存
        session.save(salamence);
        session.save(dragonite);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // join
//        Student student = (Student) session.get(Student.class, 1);
//        for (Course c : student.getCourses()) {
//            System.out.println(c.getName());
//        }

        // subselect
        List<Student> listStudent = session.createQuery("from Student").list();
        listStudent.forEach(item -> System.out.println(item.getName().concat("\t").concat(String.valueOf(item.getCourses().size()))));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 使用局部配置的 hql
        Query localHql = session.getNamedQuery("org.lzn.many2many.domain.Student.localHql");

        List list = localHql.list();
        System.out.println(Arrays.toString(list.toArray()));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 使用全局配置的 hql
        Query localHql = session.getNamedQuery("globalHql");

        List list = localHql.list();
        System.out.println(Arrays.toString(list.toArray()));

        session.getTransaction().commit();
        session.close();
    }
}
