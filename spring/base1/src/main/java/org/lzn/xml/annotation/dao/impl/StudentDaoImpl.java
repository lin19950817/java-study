package org.lzn.xml.annotation.dao.impl;

import org.lzn.xml.annotation.dao.StudentDao;
import org.springframework.stereotype.Repository;

/**
 * 注解注入，daoImpl
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 22:24
 */
@Repository("studentDaoId")
public class StudentDaoImpl implements StudentDao {
    @Override
    public void saveStudent() {
        System.out.println("StudentDaoImpl.saveStudent()");
    }
}
