# Hibernate 基础3

## 多对多

一个学生可以拥有多个课程，一个课程可以属于多个学生。学生和课程存在 **多对多** 关系。

### 加载策略（优化查询）

* 策略种类
  * 延迟加载：等到使用的时候才会加载数据。
  * 立即加载：不管使用不使用，都会立刻将数据加载
* 策略的应用
  * 类级别的加载策略
  * 关联级别的加载策略

#### 类级别的加载策略

get/load

*  get：立刻查询数据库，将数据初始化
*  load：hbm 文件中，class 元素的 lazy 属性决定类级别 load 方法的加载策略
   *  true：默认值，先返回一个代理对象，使用代理对象的属性时，才会查询数据库
   *  false：与 get 一致，会立刻加载数据

#### 关联级别的加载策略

在查询有关联关系的数据时，加载一方的数据是否需要将另一方立即查询出。默认关联的数据在使用时才查询，通过 set 元素的 lazy 属性设置是否懒加载。

lazy 是否对 set 数据使用懒加载

* true 为默认值，对集合使用懒加载。
* false 集合将会被立即加载。
* extra 及其懒惰，如果使用集合时，只调用 size 方法查询数量，Hibernate 会发送 count 语句，只查询数量。不加载集合内数据

fetch 决定加载集合使用的 sql 语句冲突

* select 默认值，普通 select 查询
* join 使用表连接语句查询集合
* subselect 使用子查询加载集合数据

| fetch     | lazy  | 结论                                                         |
| --------- | ----- | ------------------------------------------------------------ |
| select    | true  | 默认值，会在使用集合时加载，普通 select 语句                 |
| select    | false | 立刻使用 select 语句加载集合数据                             |
|           | extra | 会在使用集合时加载，普通 select 语句，如果只是获得集合的长度，会发送 count 语句查询长度 |
| join      | true  | 查询集合时使用表连接查询，会立刻记载集合数据                 |
| join      | false | 查询集合时使用表连接查询，会立刻记载集合数据                 |
| join      | extra | 查询集合时使用表连接查询，会立刻记载集合数据                 |
| subselect | true  | 会在使用集合时加载，使用子查询语句                           |
| subselect | false | 会在使用集合时加载，立即使用子查询加载成员集合数据           |
|           | extra | 会在使用集合时加载，使用子查询语句，如果只是获得集合的长度，会发送 count |



### 代码

实体和配置 [Student.java](src/main/java/org/lzn/many2many/domain/Student.java)，[Student.hbm.xml](src/main/resources/org/lzn/many2many/domain/Student.hbm.xml)，[Course.java](src/main/java/org/lzn/many2many/domain/Course.java)，[Course.hbm.xml](src/main/resources/org/lzn/many2many/domain/Course.hbm.xml)

Hibernate 配置 [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)

演示 [Many2Many.java](src/main/java/org/lzn/many2many/Many2Many.java)

## 命名查询

将 `HQL` 从 `java` 源码中，提起到配置文件中。在 *.hbm.xml 中配置。代码 [Student.hbm.xml](src/main/resources/org/lzn/many2many/domain/Student.hbm.xml)，[Many2Many.java](src/main/java/org/lzn/many2many/Many2Many.java)

### 全局的配置

```xml
<hibernate-mapping package="org.lzn.many2many.domain">
    <class name="Student" table="t_student">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name"/>
        <set name="courses" table="t_student_course" inverse="true" cascade="save-update" fetch="select" lazy="false">
            <key column="s_id"/>
            <many-to-many class="Course" column="c_id"/>
        </set>
    </class>
    <!--全局配置-->
    <query name="globalHql"><![CDATA[from Student ]]></query>
</hibernate-mapping>
```

使用

```java
public class Many2Many {
    public void demo3() {
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
```

### 局部的配置

```xml
<hibernate-mapping package="org.lzn.many2many.domain">
    <class name="Student" table="t_student">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name"/>
        <set name="courses" table="t_student_course" inverse="true" cascade="save-update" fetch="select" lazy="false">
            <key column="s_id"/>
            <many-to-many class="Course" column="c_id"/>
        </set>
        <!--局部配置-->
        <query name="localHql"><![CDATA[from Course ]]></query>
    </class>
</hibernate-mapping>
```

使用

```java
public class Many2Many {
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
}
```

## 事务、连接池、锁 相关配置

### 整合 C3P0 连接池

* 导入 jar，代码 [pom.xml](pom.xml)

  ```xml
  <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-c3p0</artifactId>
      <version>5.4.14.Final</version>
  </dependency>
  ```

* Hibernate 配置 C3P0，[hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)

  ```xml
  <!--告诉 Hibernate，我要使用 C3P0-->
  <property name="hibernate.connection.provider_class">org.hibernate.c3p0.internal.C3P0ConnectionProvider</property>
  <property name="hibernate.c3p0.max_size">5</property>
  <property name="hibernate.c3p0.min_size">2</property>
  <property name="hibernate.c3p0.timeout">5000</property>
  <property name="hibernate.c3p0.max_statements">100</property>
  <property name="hibernate.c3p0.idle_test_period">3000</property>
  <property name="hibernate.c3p0.acquire_increment">2</property>
  ```

### 改变 Hibernate 的事务隔离级别

[hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)

```xml
<property name="hibernate.connection.isolation">2</property>
```

**hibernate.connection.isolation**

* 1: 读未提交
* 2: 读已提交
* 4: 不可重复读
* 8: 序列化