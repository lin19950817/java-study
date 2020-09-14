# CXF 发布 REST 的服务

[TOC]

## CXF 发布 REST 的服务

发布服务，查询学生。

## 代码

### 发布的接口

学生接口，通过 id 查询学生。@GET：指定请求方式，@Produces：指定服务数据类型

[IStudent.java](src/main/java/org/lzn/service/IStudent.java)

```java
package org.lzn.service;

import org.lzn.pojo.Student;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 学生接口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:19
 */
@WebService
@Path("/student")
public interface IStudent {

    /**
     * 通过id查询学生<br>
     *     @GET：指定请求方式
     *     @Produces：指定服务数据类型
     *
     * @param id id
     * @return org.lzn.pojo.Student
     * @author LinZhenNan lin_hehe@qq.com
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    Student get(@PathParam("id") Long id);
}
```

### 接口实现类

[StudentImpl.java](src/main/java/org/lzn/service/impl/StudentImpl.java)

```java
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
```

### 学生实体

@XmlRootElement：表示对象可序列化

[Student.java](src/main/java/org/lzn/pojo/Student.java)

```java
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
```

### 发布

[Main.java](src/main/java/org/lzn/Main.java)

```java
package org.lzn;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.lzn.service.impl.StudentImpl;

/**
 * 入口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:26
 */
public class Main {
    public static void main(String[] args) {
        // JAXRSServerFactoryBean 发布 REST 的服务
        JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        // 设置服务实现类
        jaxrsServerFactoryBean.setServiceBean(new StudentImpl());
        // 设置资源类，如果有多个资源，可以用 ， 分隔
        jaxrsServerFactoryBean.setResourceClasses(StudentImpl.class);
        // 设置服务地址
        jaxrsServerFactoryBean.setAddress("http://127.0.0.1:12345/user");
        // 发布服务
        jaxrsServerFactoryBean.create();
    }
}
```

### 测试

输入 URL：`http://127.0.0.1:12345/user/student/23`