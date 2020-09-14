package org.lzn.service.impl;

import org.lzn.pojo.Student;
import org.lzn.service.IStudent;

import java.time.LocalDate;

/**
 * 学生接口实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:24
 */
public class StudentImpl implements IStudent {
    @Override
    public Student get(Long id) {
        return new Student(id, "hehe", LocalDate.now());
    }
}
