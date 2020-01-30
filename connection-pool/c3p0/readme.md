# C3P0

> `C3P0` 是一个开源的 `JDBC` 连接池，它实现了数据源和 `JNDI` 绑定，支持 `JDBC3` 规范和 `JDBC2` 的标准扩展。目前使用它的开源项目有 `Hibernate` 、`Spring` 等。

## 配置

1. [jar](pom.xml)
2. [配置文件](src/main/resources/c3p0-config.xml)（名字为 `c3p0-config.xml` 或 `c3p0.properties`）
3. [工具类](src/main/java/org/lzn/util/C3P0Util.java)

## 原生数据源设置

```java
public class C3P0Util {
    private static ComboPooledDataSource dataSource;
    static {
        try {
            // 加载驱动
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hehe_test?useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            // 初始化 10 个连接
            dataSource.setInitialPoolSize(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

