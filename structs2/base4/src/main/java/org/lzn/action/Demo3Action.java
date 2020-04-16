package org.lzn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.lzn.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用 ValueStack 存数据
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/13 20:26
 */
public class Demo3Action extends ActionSupport {
    @Override
    public String execute() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路人甲", 18));
        students.add(new Student("炮灰乙", 19));
        students.add(new Student("流氓丙", 20));
        ActionContext.getContext().getValueStack().set("students", students);
        return SUCCESS;
    }
}
