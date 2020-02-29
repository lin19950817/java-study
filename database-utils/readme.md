# DBUtils

> `Commons DbUtils` 是 `Apache` 组织提供的一个对 `JDBC` 进行简单封装的开源工具类库，使用它能够简化 `JDBC` 应用程序的开发，同时也不会影响程序的性能。

`DBUtils` 是 `java` 编程中的数据库操作实用工具，小巧简单实用。

`DBUtils` 封装了对 `JDBC` 的操作，简化了 `JDBC` 操作。可以少写代码

1. 对于数据表的 **读操作**，他可以把结果转换成 `List`，`Array`，`Set` 等 `java` 集合。
2. 对于数据表的 **写操作**，也变得很简单（只需写 `sql` 语句）。
3. 可以使用数据源，使用 `JNDI(Java Naming and Directory Interface,Java命名和目录接口)`，数据库连接池等技术来优化性能，重用已经够建好的数据库连接对象。

## 三个核心对象

1. <a href="#queryRunner">QueryRunner 类</a>
2. <a href="#resultSetHandler">ResultSetHandler 接口</a>
3. <a href="#dbUtils">DbUtils 类</a>

### <a name="queryRunner" style="text-decoration:none">QueryRunner 类</a>

`QueryRunner` 提供对 `sql` 语句操作的 `API`

1. `query()` 用于执行 `select`
2. `update()` 用于执行 `insert update delete`
3. `batch()` 批处理

### <a name="resultSetHandler" style="text-decoration:none">ResultSetHandler 接口</a>

用于定义 `select` 操作后，怎样封装结果集

### <a name="dbUtils" style="text-decoration:none">DbUtils 类</a>

工具类，定义了关闭资源与事务处理的方法

## 学习

1. [DBUtils 学习](DbUtils-quick-start)
2. [转账（案例）](transfer)

