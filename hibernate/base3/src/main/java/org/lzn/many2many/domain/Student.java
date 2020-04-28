package org.lzn.many2many.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 学生
 * 多对多，一个学生可以拥有多个课程
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/24 23:24
 */
public class Student {
    private Integer id;
    private String name;

    /**
     * 多对多，课程
     */
    private Set<Course> courses = new HashSet<>();

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
