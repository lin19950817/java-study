package org.lzn.many2many.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 课程
 * 多对多，一个课程可以属于多个学生
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/24 23:36
 */
public class Course {
    private Integer id;
    private String name;

    /**
     * 多对多，课程
     */
    Set<Student> students = new HashSet<>();

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    //
    // hashCode/equals
    // ------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
