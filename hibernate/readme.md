# hibernate

`Hibernate` 是一个数据持久化层的 `ORM` 框架。以面向对象的思想操作数据库。

[TOC]

## ORM

> 对象关系映射（Object Relational Mapping，简称 ORM）是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。本质上就是将数据从一种形式转换到另外一种形式。 这也同时暗示着额外的执行开销；然而，如果 ORM 作为一种中间件实现，则会有很多机会做优化，而这些在手写的持久层并不存在。 更重要的是用于控制转换的元数据需要提供和管理；但是同样，这些花费要比维护手写的方案要少；而且就算是遵守 ODMG 规范的对象数据库依然需要类级别的元数据。

### 主流的 ORM 框架

* JPA (Java Persistence API)，JPA 通过 JDK 5.0 注解或 XML 描述对象-关系表的映射关系（只有借口规范）
* Hibernate，最流行 ORM 框架，通过对 新-关系 映射配置，可以完全脱离底层 SQL
* MyBatis，本事 apache 的一个开源项目 iBatis，支持普通 SQL 查询，存储过程和高级映射的优秀持久层框架
* Apache DBUtils、Spring JDBCTemplate

## hibernate 学习

* [快速入门](quick-start)
* [基础1](base1)
* [基础2](base2)
* [基础3](base3)
* [基础4](base4)

## Bean

* PO (Persistent object)，持久化对象。
* BO (Business object)，业务数据对象。service 层
* VO (Value object)，值对象。web 层