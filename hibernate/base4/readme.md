# Hibernate 基础4

## Hibernate 中的日志

`slf4j` 核心 `jar`，`slf4j-api-1.6.1.jar`。`slf4j` 是日志框架，将其他优秀的日志第三方进行整合。配置 [log4j.properties](src/main/resources/log4j.properties)

slf4j(slf4j-api) -> 中间 jar(slf4j-log4j12) -> log4j(log4j)

```xml
<!--日志框架（整合 log4j）-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.6.1</version>
</dependency>
<!--日志中间整合 jar-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.30</version>
</dependency>
<!--log4j-->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

### log4j 日志级别

由高到底顺序

* fatal 致命错误
* error 错误
* warn 警告
* info 信息
* debug 调试信息
* trace 堆栈信息

### 输出源

log4j.appender:输出源的名称=输出源实现类。代码 [log4j.properties](src/main/resources/log4j.properties)

```properties
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.file=org.apache.log4j.FileAppender
```

### 记录器

log4j.rootLogger=日志级别，输出源 1，输出源 2。代码 [log4j.properties](src/main/resources/log4j.properties)

```properties
log4j.rootLogger=debug, stdout, file
```

## 一对一

一个公司拥有一个地址，一个地址属于一个公司，公司和地址存在 **一对一** 关系。

### 通过外键实现一对一关系

company.hbm.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="org.lzn.one2one.domain">
    <class name="Company" table="t_company">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name" type="string"/>

        <!--
            配置 一对一
                one-to-one: 默认是使用主键同步策略完成一对一的表关系
                property-ref: 指定 company 在一对一关联时，指向那个属性
        -->
        <one-to-one name="address" class="Address" property-ref="company"/>
    </class>
</hibernate-mapping>
```

address.hbm.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="org.lzn.one2one.domain">
    <class name="Address" table="t_address">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name" type="string"/>

        <!--unique: 唯一，外键唯一-->
        <many-to-one name="company" class="Company" column="c_id" unique="true"/>
    </class>
</hibernate-mapping>
```

演示

```java
public class One2One {
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Company company = new Company();
        company.setName("度娘");

        Address address = new Address();
        address.setName("北京");

        // 使用外键维护一对一表关系时，只有在外键所在的对象才能维护关系
        address.setCompany(company);
        company.setAddress(address);

        session.save(company);
        session.save(address);

        session.getTransaction().commit();
        session.close();
    }
}
```

### 使用主键同步实现一对一关系

company.hbm.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="org.lzn.one2one.domain">
    <class name="Company" table="t_company">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name" type="string"/>

        <!--
            配置 一对一
                one-to-one: 默认是使用主键同步策略完成一对一的表关系
        -->
        <one-to-one name="address" class="Address"/>
    </class>
</hibernate-mapping>
```

address.hbm.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="org.lzn.one2one.domain">
    <class name="Address" table="t_address">
        <id name="id" column="id">
            <!--foreign: 该主键即是主键又是外键-->
            <generator class="foreign">
                <!--作为外键时引用那个属性-->
                <param name="property">company</param>
            </generator>
        </id>
        <property name="name" column="name" type="string"/>
        <!--配置一对一关系-->
        <one-to-one name="company" class="Company" constrained="true"/>
    </class>
</hibernate-mapping>
```

演示

```java
public class One2One {
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Company company = new Company();
        company.setName("度娘");

        Address address = new Address();
        address.setName("北京");

        address.setCompany(company);
        company.setAddress(address);

        session.save(company);
        session.save(address);

        session.getTransaction().commit();
        session.close();
    }
}
```

### 代码

Hibernate 配置 [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)

log4j 配置 [log4j.properties](src/main/resources/log4j.properties)

相关实体和配置 [Company.java](src/main/java/org/lzn/one2one/domain/Company.java)，[Company.hbm.xml](src/main/resources/org/lzn/one2one/domain/Company.hbm.xml)，[Address.java](src/main/java/org/lzn/one2one/domain/Address.java)，[Address.hbm.xml](src/main/resources/org/lzn/one2one/domain/Address.hbm.xml)

演示 [One2One.java](src/main/java/org/lzn/one2one/One2One.java)

## 二级缓存

`sessionFactory` 级别缓存，整个应用程序共享一个会话工厂，共享一个二级缓存。

`SessionFactory` 的缓存两部分：	

* 内置缓存：使用一个Map，用于存放配置信息，预定义HQL语句等，提供给Hibernate框架自己使用，对外只读的。不能操作。
* 外置缓存：使用另一个Map，用于存放用户自定义数据。默认不开启。外置缓存hibernate只提供规范（接口），需要第三方实现类。外置缓存有成为二级缓存。

### 并发访问策略

常用访问策略：读写型（read-write）、只读型（read-only）

| 名称                               | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| transactional(事务型)              | 仅在受管理的环境中适用<br>提供 `Repeatable Read` 事务隔离级别<br>适用经常被读，很少修改的数据<br>可以 **防止脏读和不可重复读** 的并发问题<br>**缓存支持事务，发生异常的时候，缓存也能回滚** |
| read-write(读写型)                 | 提供 `Read Committed` 事务隔离级别<br>在非集群的环境中适用<br>适用经常被读，很少修改的数据<br>**可以防止脏读**<br>**更新缓存的时候也会锁定缓存中的数据** |
| nonstrict-read-write(非严格读写型) | 适用极少被修改，偶尔允许脏读的数据（两个事务同事修改数据的情况很少见）<br>不保证缓存和数据库中数据的一致性<br>为缓存数据设置很短的过期时间，从而尽量避免脏读<br>**不锁定缓存中的数据** |
| read-only(只读型)                  | 适用 **从来不会被修改** 的数据<br>在此模式下，如果对数据进行更新操作，会有异常事务隔离级别低，并发性能高<br>在集群环境中也能完美运作 |

### 二级缓存提供商

Hibernate 只提供接口。

* EHCache: 可作为进程（单机）范围内的缓存, 存放数据的物理介质可以是内存或硬盘, 对 Hibernate 的查询缓存提供了支持。--支持集群。
* OpenSymphony :可作为进程范围内的缓存, 存放数据的物理介质可以是内存或硬盘, 提供了丰富的缓存数据过期策略, 对 Hibernate 的查询缓存提供了支持
* SwarmCache: 可作为集群范围内的缓存, 但不支持 Hibernate 的查询缓存
* JBossCache:可作为集群范围内的缓存, 支持 Hibernate 的查询缓存

| 提供商      | Read-only | Nonstrict-read-write | Read-write | Transactional |
| ----------- | --------- | -------------------- | ---------- | ------------- |
| EHCAche     | Y         | Y                    | Y          | N             |
| OSCache     | Y         | Y                    | Y          | N             |
| SwarmCache  | Y         | Y                    | N          | N             |
| JBoss Cache | Y         | N                    | N          | Y             |

### 二级缓存的缓存区

* [类缓存区](#classCache)
* [集合缓存区](#collectCache)
* [查询缓存区](#queryCache)
* [时间戳缓存区](#timeStamp)

### 配置

使用 EHCAche 提供的二级缓存

1. 导入 jar

   ```xml
   <dependency>
       <groupId>org.hibernate</groupId>
       <artifactId>hibernate-ehcache</artifactId>
       <version>5.4.14.Final</version>
   </dependency>
   ```

2. 开启二级缓存

   ```xml
   <!--配置使用二级缓存-->
   <property name="hibernate.cache.use_second_level_cache">true</property>
   ```

3. 确定供应商

   ```xml
   <!--配置二级缓存的实现类 EhCache，Hibernate4 之后版本-->
   <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
   ```

4. 确定缓存内容

   ```xml
   <!--mapping 后面配置缓存区-->
   <!--配置缓存区，类缓存-->
   <class-cache class="org.lzn.one2one.domain.Company" usage="read-only"/>
   ```

### <a name="classCache" style="text-decoration:none">类缓存</a>

```xml
<class-cache class="org.lzn.one2one.domain.Company" usage="read-only"/>
```

演示

```java
public class SecondCache {
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        // 清空一级缓存
        session.clear();

        // 查二级缓存
        Customer customer2 = session.get(Customer.class, 1);

        // 结果 false，二级缓存在缓存数据时，并不是以对象的形式缓存。缓存的是对象数据的散列。每次从二级缓存拿，会在一级缓存中组装成对象
        System.out.println(customer == customer2);

        session.getTransaction().commit();
        session.close();
    }
}
```

### <a name="collectCache" style="text-decoration:none">集合缓存</a>

集合只缓存 id，然后通过 id 查询缓存，缓存没有则查询数据库。因此配置集合对应的类缓存。

```xml
<!--配置缓存区，类缓存-->
<class-cache class="org.lzn.secondcache.domain.Customer" usage="read-only"/>
<class-cache class="org.lzn.secondcache.domain.Order" usage="read-only"/>
<!--
    配置缓存区，集合缓存
        collection: 完整类名.集合属性名
-->
<collection-cache collection="org.lzn.secondcache.domain.Customer.orders" usage="read-only"/>
```

演示

```java
public class SecondCache {
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        for (Order order : customer.getOrders()) {
            System.out.println(order.getName());
        }

        // 清空缓存
        session.clear();

        Customer customer2 = session.get(Customer.class, 1);

        for (Order order : customer2.getOrders()) {
            System.out.println(order.getName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
```

### <a name="queryCache" style="text-decoration:none">查询缓存</a>

对 HQL 语句查询的缓存。开启查询缓存后，查询时缓存语句和结果的 id，然后会先从二级缓存那个取结果，取不到则通过 id 发送 sql 语句查询并将结果放入二级缓存中

需要配置开启查询缓存

```xml
<!--开启查询缓存-->
<property name="hibernate.cache.user_query_cache">true</property>
```

演示

```java
public class SecondCache {
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Query query = session.createQuery("from Customer");
        // 使用二级缓存的查询缓存，查询时，会先从二级缓存那个取结果，取不到则执行语句并将结果放入二级缓存中
        query.setCacheable(true);
        List<Customer> list = query.list();

        session.clear();

        Query query2 = session.createQuery("from Customer");
        query2.setCacheable(true);
        List<Customer> list2 = query2.list();


        session.getTransaction().commit();
        session.close();
    }
}
```

### <a name="timeStamp" style="text-decoration:none">时间戳区</a>

记录表和最后操作时间，来确保二级缓存中的数据是有效的。

```java
public class SecondCache {
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        session.createQuery("update Customer set name = :name where id = :id")
                .setParameter("name", "rose").setParameter("id", 1).executeUpdate();

        session.clear();

        // 发送 sql 语句查询，确保二级缓存中的数据是有效的
        Customer customer2 = session.get(Customer.class, 1);

        session.getTransaction().commit();
        session.close();
    }
}
```

