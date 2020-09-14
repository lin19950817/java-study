package org.lzn.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * 学生实体
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:16
 */
@XmlRootElement(name = "myStudent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    private Long id;
    private String name;
    private LocalDate birthday;

    public Student() {
    }

    public Student(Long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
