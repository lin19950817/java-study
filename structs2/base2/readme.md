# struts2 基础2

[主页](web/welcome.jsp) 中有各例子。

## 分文件编写配置文件

新建 **struts.xml** 文件 [struts_module.xml](src/main/resources/struts_module.xml)，在 [struts.xml](src/main/resources/struts.xml) 中配置引入。

```xml
<include file="struts_module.xml"></include>
```

## 封装请求正文到对象中

### 静态封装

在 **action** 配置时配置 **静态参数封装**。参考：[struts_module.xml](src/main/resources/struts_modult.xml)，[Demo1Action](src/main/java/base2/org/lzn/web/action/Demo1Action.java)，[welcome.jsp](web/welcome.jsp)

```xml
<action name="action1" class="base2.org.lzn.web.action.Demo1Action" method="addUser">
    <param name="username">张三</param>
    <param name="age">18</param>
</action>
```

### 动态封装

`form` 表单的 `name` 属性必须与动作类 [Demo2Action](src/main/java/base2/org/lzn/web/action/Demo2Action.java) 的字段名一致

* 数据模型与动作类写在一起。参考：[struts_module.xml](src/main/resources/struts_modult.xml)，[Demo2Action](src/main/java/base2/org/lzn/web/action/Demo2Action.java)，[addUser.jsp](web/addUser.jsp)

* 数据模型与动作类分开写。参考：[struts_module.xml](src/main/resources/struts_modult.xml)，[Demo3Action](src/main/java/base2/org/lzn/web/action/Demo3Action.java)，[addUser2.jsp](web/addUser2.jsp)

  Demo3Action

  ```java
  public class Demo3Action extends ActionSupport {
      private User user;
      public String addUser() {
          System.out.println(user.getUsername() + "\t" + user.getAge());
          return null;
      }
      public User getUser() {
          return user;
      }
      public void setUser(User user) {
          this.user = user;
      }
  }
  ```

  User

  ```java
  public class User {
      private String username;
      private int age;
      public String getUsername() {
          return username;
      }
      public void setUsername(String username) {
          this.username = username;
      }
      public int getAge() {
          return age;
      }
      public void setAge(int age) {
          this.age = age;
      }
  }
  ```

  addUser2

  ```jsp
  <form action="${pageContext.request.contextPath}/action3.action" method="post">
      用户名：<input type="text" name="user.username" /><br>
      年龄：<input type="text" name="user.age" /><br>
      <input type="submit" value="提交" />
    </form>
  ```

* 使用模型驱动，建议开发使用。参考：[struts_module.xml](src/main/resources/struts_modult.xml)，[Demo4Action](src/main/java/base2/org/lzn/web/action/Demo4Action.java)，[addUser3.jsp](web/addUser3.jsp)

  Demo4Action

  ```java
  public class Demo4Action implements ModelDriven<User> {
      private User user = new User();
      @Override
      public User getModel() {
          return user;
      }
      public String addUser() {
          System.out.println(user.getUsername() + "\t" + user.getAge());
          return null;
      }
      public User getUser() {
          return user;
      }
      public void setUser(User user) {
          this.user = user;
      }
  }
  
  ```

  addUser3

  ```jsp
  <form action="${pageContext.request.contextPath}/action4.action" method="post">
      用户名：<input type="text" name="username" /><br>
      年龄：<input type="text" name="age" /><br>
      <input type="submit" value="提交" />
    </form>
  ```

## 类型转换器

### 自定义类型转换器

编写一个自定义类型转换器 [MyTypeConvertor](src/main/java/base2/org/lzn/web/converter/MyTypeConvertor.java)，把 MM/dd/yyyy 格式的数据转成日期类型，把数据库中的本地日期格式，转成 MM/dd/yyyy 形式输出。

### 注册类型转换器

* 局部类型转换器：只能指定 `javabean` 中的属性使用

  按照属性类注册。在属性所属的 `javabean` 的包下建立一个 `.properties` 文件。文件名称：**javabean 的名称-conversion.properties**。例如 [User-conversion.properties](src/main/resources/base2/org/lzn/domain/User-conversion.properties)，设置了开发工具，编译后此文件与 User.class 处于同一目录下。
  
* 全局类型转换器，推荐开发中使用。

  按照要转换的数据类型来注册。在 **classpath** 下，建立一个固定名称 `xwork-conversion.properties` 的属性文件。例如 [xwork-conversion.properties](src/main/resources/xwork-conversion.properties)

* 转换失败后的异常处理。

## struts2 标签

参考 [addUser5.jsp](web/addUser5.jsp)

自定义字段格式错误的提示，在数据模型的目录下创建 **javabean.properties**，参考 [User.properties](src/main/resources/base2/org/lzn/domain/User.properties)。

```properties
invalid.fieldvalue.birthday=请输入正确的日期格式。yyyy-MM-dd
```

## 编程式验证

在 struts2 的框架中，它也提供了一个 **Map<表单的字段名，错误提示>**。我们只需往 `map` 中存放错误信息，需要继承 `ActionSupport` 并且重写 `validate()` 方法，`validate()` 方法会在 **所有** 动作方法之前，进行校验。参考 [Demo5Action.java](src/main/java/base2/org/lzn/web/action/Demo5Action.java)

```java
public class Demo5Action extends ActionSupport {
    private String testField;
    @Override
    public void validate() {
		if (testField == null) {
            addFieldError("testField", "字段不能为空")
        }
    }
}
```

使用 `@SkipValidation` 的注解，使动作方法跳过验证。参考 [Demo5Action.java](src/main/java/base2/org/lzn/web/action/Demo5Action.java)

```java
public class Demo5Action extends ActionSupport {
    private String testField;
    @Override
    public void validate() {
		if (testField == null) {
            addFieldError("testField", "字段不能为空")
        }
    }
    @SkipValidation
    public String addUserNotValid() {
        return null;
    }
}
```

使用 validate + 动作名称（首字母大写） 的校验方法来指定校验那个动作。参考 [Demo6Action.java](src/main/java/base2/org/lzn/web/action/Demo6Action.java)

```java
public class Demo5Action extends ActionSupport {
    private String testField;
    public void validateAddUser() {
		if (testField == null) {
            addFieldError("testField", "字段不能为空")
        }
    }
    public String addUser() {
        return null;
    }
    public String addUserNotValid() {
        return null;
    }
}
```

### 声明式验证，推荐

通过编写规则的 `xml` 文件。需要验证时在动作类所在的包中编写 `xml` 文件，不要验证，就不写。**基于字段的验证** 和 **基于验证器的验证** 参考 [Demo7Action-validation.xml](src/main/resources/base2/org/lzn/web/action/Demo7Action-validation.xml)。

* 使用 `ActionClassName-validation.xml` 来命名文件时，他是一个动作类验证器，会验证动作类中的所有动作方法，可以使用 `@SkipValidation` 跳过验证。参考 [Demo7Action-validation.xml](src/main/resources/base2/org/lzn/web/action/Demo7Action-validation.xml)
* 使用 `ActionClassName-ActionName-validation.xml`来命名文件时，针对动作类中的某个动作进行验证。参考 [Demo8Action-validation.xml](src/main/resources/base2/org/lzn/web/action/Demo8Action-validation.xml)

