# 自动登录

实现功能，登录页面勾选自动登录后，则下次进入主页无需登录。

### `util`

* [C3P0Util.java](src/main/java/org/lzn/util/C3P0Util.java) : 数据库连接工具
* [MD5Util.java](src/main/java/org/lzn/MD5Util.java) : 信息摘要加密

### `domain`

* [User.java](src/main/java/org/lzn/domain/User.java) : user 实体

### `dao`

* [UserDao.java](src/main/java/org/lzn/dao/UserDao.java) : dao 层

### `service`

* [UserService.java](src/main/java/org/lzn/service/UserService.java) : service 层

### `web`

* [AutoLoginFilter.java](src/main/java/org/lzn/web/filter/AutoLoginFilter.java) : 过滤器，处理 **自动登录** 功能逻辑
* [LoginServlet.java](src/main/java/org/lzn/web/servlet/LoginServlet.java) : 登录 servlet

## mysql 脚本

```mysql
/*
Navicat MySQL Data Transfer

Source Server         : LinHehe
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : hehe_test

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-25 23:40:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码（MD5加密）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tom', '202cb962ac59075b964b07152d234b70');

```

