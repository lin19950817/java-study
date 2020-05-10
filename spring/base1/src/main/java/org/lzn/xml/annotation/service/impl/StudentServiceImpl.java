package org.lzn.xml.annotation.service.impl;

import org.lzn.xml.annotation.dao.StudentDao;
import org.lzn.xml.annotation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 注解注入，serviceImpl
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 22:13
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentDaoId")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent() {
        System.out.println("StudentServiceImpl.addStudent()");
        studentDao.saveStudent();
    }
}
