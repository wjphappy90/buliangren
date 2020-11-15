/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : boot_homework

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-11-12 17:49:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` varchar(40) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '教学部');
INSERT INTO `t_dept` VALUES ('2', '研发部');
INSERT INTO `t_dept` VALUES ('3', '小卖部');
INSERT INTO `t_dept` VALUES ('4', '医疗部');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `id` varchar(40) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `bir` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `deptId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1ac7d0cb-cb39-41c6-8045-0d378836bfe7', '金莲ert', '23', '2012-12-12 00:00:00', '4');
INSERT INTO `t_emp` VALUES ('3', '小王', '24', '2019-11-12 15:14:56', '2');
INSERT INTO `t_emp` VALUES ('36bf189e-7c0a-4146-ab36-746fe42745d6', '小黑124', '23', '2012-12-12 00:00:00', '1');
INSERT INTO `t_emp` VALUES ('9c30906c-0fad-434c-86c6-f78ef66ff5f2', '小明123', '23', '2012-12-12 00:00:00', '4');
INSERT INTO `t_emp` VALUES ('cdaaa879-61cd-4331-9be9-762337a3274b', '小明123', '23', '2012-12-11 00:00:00', '2');

-- ----------------------------
-- Table structure for t_emp_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_emp_tag`;
CREATE TABLE `t_emp_tag` (
  `id` varchar(40) NOT NULL,
  `empId` varchar(40) DEFAULT NULL,
  `tagId` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp_tag
-- ----------------------------

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` varchar(40) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
