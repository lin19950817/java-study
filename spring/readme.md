# spring

> Spring 是一个开源框架，Spring 是于 2003 年兴起的一个轻量级的 Java 开发框架，由 Rod Johnson 在其著作 Expert One-On-One J2EE Development and Design 中阐述的部分理念和原型衍生而来。它是为了解决企业应用开发的复杂性而创建的。框架的主要优势之一就是其分层架构，分层架构允许使用者选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架。Spring 使用基本的 JavaBean 来完成以前只可能由 EJB 完成的事情。然而，Spring 的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何 Java 应用都可以从 Spring 中受益。Spring 的核心是控制反转（IoC）和面向切面（AOP）。简单来说，Spring 是一个分层的 JavaSE/EE full-stack(一站式) 轻量级开源框架。

**分层**

* web 层：struts，spring-MVC
* service 层：spring
* dao 层：Hibernate，mybatis，jdbcTemplate

[TOC]

## spring 核心

Spring 的核心是 **控制反转（IoC）** 和 **面向切面（AOP）**

## spring 优点

* 方便解耦，简化开发  （高内聚低耦合）
  * Spring就是一个大工厂（容器），可以将所有对象创建和依赖关系维护，交给Spring管理
  * spring工厂是用于生成bean
* AOP编程的支持
  * Spring提供面向切面编程，可以方便的实现对程序进行权限拦截、运行监控等功能
* 声明式事务的支持
  * 只需要通过配置就可以完成对事务的管理，而无需手动编程
* 方便程序的测试
  * Spring对Junit4支持，可以通过注解方便的测试Spring程序
* 方便集成各种优秀框架
  * Spring不排斥各种优秀的开源框架，其内部提供了对各种优秀框架（如：Struts、Hibernate、MyBatis、Quartz等）的直接支持
* 降低JavaEE API的使用难度
  * Spring 对JavaEE开发中非常难用的一些API（JDBC、JavaMail、远程调用等），都提供了封装，使这些API应用难度大大降低

## 案例

* [快速入门-IOC](ioc-quick-start)
* [快速入门-DI](di-quick-start)
* [spring 基础1](base1)
* [spring 基础2](base2)
* [spring 基础3](base3)