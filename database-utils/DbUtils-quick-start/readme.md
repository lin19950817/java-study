# DbUtils 快速入门

1. [导入 jar 包](pom.xml) 和 [配置文件(c3p0)](src/main/resources/c3p0-config.xml)
2. 创建 QueryRunner 对象
3. 使用 query 方法执行 select 语句
4. 使用 ResultSetHandler 封装结果集
5. 使用 DbUtils 类释放资源

## QueryRunner 对象

### 构造函数

1. new QueryRunner()

   事务可以手动控制。此对象的调用方法（如： `query`、`update`、`batch`）参数中要有 `Connection` 对象。

2. new QueryRunner(DataSource ds)

   事务是自动控制，一个 sql 一个事务。此对象的调用方法（如： `query`、`update`、`batch`）参数中无需 `Connection` 对象。

### 方法

| 返回类型 | 函数                                                         | 练习                                                         |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| <T> T    | query(String sql, ResultSetHandler<T> rsh, Object... params) | org.lzn.TestQueryRunner#testSelect、org.lzn.TestQueryRunner#testSelectWithParameter |
| int      | update(String sql, Object... params)                         | org.lzn.TestQueryRunner#testUpdate、org.lzn.TestQueryRunner#testInsert |
| int[]    | batch(String sql, Object[][] params)                         | org.lzn.TestQueryRunner#testBatch                            |

## ResultSetHandler 接口

| 函数              | 功能                                                         | 练习                                               |
| ----------------- | ------------------------------------------------------------ | -------------------------------------------------- |
| ArrayHandler      | 适合取 1 条记录。把该条记录的每列值封装到一个数组中 Object[] | org.lzn.TestResultSetHandler#testArrayHandler      |
| ArrayListHandler  | 适合取多条记录。把每条记录的每列值封装到一个数组中 Object[]，把数组封装到一个 List 中 | org.lzn.TestResultSetHandler#testArrayListHandler  |
| ColumnListHandler | 取某一列的数据。封装到 List 中                               | org.lzn.TestResultSetHandler#testColumnListHandler |
| KeyedHandler      | 取多条记录，每一条记录封装到一个 Map 中，再把这个 Map 封装到另外一个 Map 中，key 为指定的字段值 | org.lzn.TestResultSetHandler#testKeyedHandler      |
| MapHandler        | 适合取 1 条记录。把当前记录的列名和列值放到一个 Map 中       | org.lzn.TestResultSetHandler#testMapHandler        |
| MapListHandler    | 适合取多条记录。把每条记录封装到一个 Map 中，再把 Map 封装到  List 中 | org.lzn.TestResultSetHandler#testMapListHandler    |
| ScalarHandler     | 适合取单行单列数据                                           | org.lzn.TestResultSetHandler#testScalarHandler     |
| BeanHandler       | 适合取单行单列数据。封装到泛型 T 中                          | org.lzn.TestResultSetHandler#testBeanHandler       |
| BeanListHandler   | 适合取多条记录。封装到 List<T>                               | org.lzn.TestResultSetHandler#testBeanListHandler   |

