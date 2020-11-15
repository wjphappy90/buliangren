/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : springmvc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-10-28 15:47:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_filelog
-- ----------------------------
DROP TABLE IF EXISTS `t_filelog`;
CREATE TABLE `t_filelog` (
  `id` varchar(40) NOT NULL,
  `oldfilename` varchar(100) DEFAULT NULL,
  `newFileName` varchar(160) DEFAULT NULL,
  `ext` varchar(80) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `filesize` varchar(80) DEFAULT NULL,
  `contentType` varchar(200) DEFAULT NULL,
  `downcounts` varchar(80) DEFAULT NULL,
  `uploadDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userid` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_filelog
-- ----------------------------
INSERT INTO `t_filelog` VALUES ('75c68524-d876-48ee-a01a-3c04f189da23', '小黑.txt', 'ff2fcf43f30d4e86b739f08147b955a2.txt', '.txt', '/files/2019-10-28', '313', 'text/plain', '1', '2019-10-28 14:50:16', '1');
INSERT INTO `t_filelog` VALUES ('7b1dc6b0-5966-48cd-9d8f-8e6a0bcc2767', 'Tulips.jpg', '86117627a5af454a9c3751a7b5eb32ed.jpg', '.jpg', '/files/2019-10-28', '620888', 'image/jpeg', '3', '2019-10-28 14:48:36', '1');
INSERT INTO `t_filelog` VALUES ('872c54bc-00ea-48e0-ba55-144b83195480', 'Desert.jpg', '991d8ac3ece44aa3a5854cfe7c50d9ea.jpg', '.jpg', '/files/2019-10-28', '845941', 'image/jpeg', '2', '2019-10-28 14:48:00', '1');
INSERT INTO `t_filelog` VALUES ('ecfeb7b6-859b-42eb-90dd-5dbb576c5ce5', 'A.java', '516ee1a601064e7bbc5c701a0dc3f919.java', '.java', '/files/2019-10-28', '0', 'application/octet-stream', '0', '2019-10-28 14:50:10', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xiaohei', '123');
