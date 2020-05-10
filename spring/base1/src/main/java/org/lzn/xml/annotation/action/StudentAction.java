package org.lzn.xml.annotation.action;

import org.lzn.xml.annotation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 注解注入，action
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 22:01
 */
@Controller("studentAction")
public class StudentAction {

    @Autowired
    private StudentService studentService;

    public void execute() {
        System.out.println("StudentAction.execute()");
        studentService.addStudent();
    }
}
