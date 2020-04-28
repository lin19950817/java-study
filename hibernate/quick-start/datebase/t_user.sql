/*
Navicat MySQL Data Transfer

Source Server         : LinHehe
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : study_hibernate

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-04-18 23:53:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(63) DEFAULT NULL COMMENT '用户名',
  `password` varchar(63) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
