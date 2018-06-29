/*
Navicat MySQL Data Transfer

Source Server         : 47.93.254.168
Source Server Version : 50173
Source Host           : 47.93.254.168:3306
Source Database       : library1

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-06-29 23:17:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for library_admin
-- ----------------------------
DROP TABLE IF EXISTS `library_admin`;
CREATE TABLE `library_admin` (
  `userid` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sex` varchar(10) NOT NULL DEFAULT '男',
  `phone` varchar(11) CHARACTER SET latin1 NOT NULL,
  `power` varchar(10) NOT NULL DEFAULT 'general',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of library_admin
-- ----------------------------
INSERT INTO `library_admin` VALUES ('0000000004', 'admin', 'd18e1f302fda235854f04bba967f4aa4', '男', '15646168416', 'admin');
INSERT INTO `library_admin` VALUES ('0000000005', 'homework_library', 'e71f080014ef51be70f0d71d8423c3f1', '男', '15023146581', 'root');

-- ----------------------------
-- Table structure for library_book
-- ----------------------------
DROP TABLE IF EXISTS `library_book`;
CREATE TABLE `library_book` (
  `bookid` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `bookname` varchar(50) NOT NULL,
  `bookauthor` varchar(50) NOT NULL,
  `bookpublisher` varchar(50) NOT NULL,
  `bookprice` double(20,0) unsigned NOT NULL,
  `bookcategory` varchar(20) NOT NULL,
  `booksurplus` int(50) unsigned NOT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of library_book
-- ----------------------------
INSERT INTO `library_book` VALUES ('00000000000000000006', 'java', '20', '计算机', '20', '高教', '48');
INSERT INTO `library_book` VALUES ('00000000000000000007', '数据结构', '20', '计算机', '20', '高教', '49');
INSERT INTO `library_book` VALUES ('00000000000000000008', 'Spring', '20', '计算机', '20', '高教', '49');
INSERT INTO `library_book` VALUES ('00000000000000000009', '计算机组成', '20', '计算机', '20', '高教', '49');
INSERT INTO `library_book` VALUES ('00000000000000000010', '计网', '20', '计算机', '20', '高教', '49');
INSERT INTO `library_book` VALUES ('00000000000000000011', '毛概', '20', '哲学', '20', '高教', '48');
INSERT INTO `library_book` VALUES ('00000000000000000012', '马原', '20', '哲学', '20', '高教', '49');
INSERT INTO `library_book` VALUES ('00000000000000000015', '高数', '13', '高教', '63', '数学', '51');
INSERT INTO `library_book` VALUES ('00000000000000000016', '高数', '13', '高教', '63553', '数学', '46');
INSERT INTO `library_book` VALUES ('00000000000000000017', '离散', '张三', '高教', '18', '数学', '40');
INSERT INTO `library_book` VALUES ('00000000000000000018', '121', '2', '1', '1', '1', '2');
INSERT INTO `library_book` VALUES ('00000000000000000019', '456', '456', '446', '456', '456', '456');

-- ----------------------------
-- Table structure for library_borrow
-- ----------------------------
DROP TABLE IF EXISTS `library_borrow`;
CREATE TABLE `library_borrow` (
  `id` int(50) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `bookid` int(20) unsigned NOT NULL,
  `username` varchar(20) NOT NULL,
  `bookname` varchar(20) NOT NULL,
  `loan` varchar(50) NOT NULL,
  `back` varchar(50) NOT NULL,
  `isrenew` varchar(5) NOT NULL DEFAULT '是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of library_borrow
-- ----------------------------
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000008', '1', '1', '高数', '2018年05月22日', '2018年08月20日', '否');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000014', '16', '1', '高数', '2018年05月30日', '2018年07月29日', '否');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000015', '16', '1', '高数', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000017', '16', '1', '高数', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000018', '16', '1', '高数', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000019', '15', '1', '高数', '2018年05月30日', '2018年07月29日', '否');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000020', '7', '1', '数据结构', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000021', '9', '1', '计算机组成', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000022', '10', '1', '计网', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000023', '12', '1', '马原', '2018年05月30日', '2018年07月29日', '否');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000024', '16', '1', '高数', '2018年05月30日', '2018年07月29日', '否');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000025', '15', '1', '高数', '2018年05月30日', '2018年06月29日', '是');
INSERT INTO `library_borrow` VALUES ('00000000000000000000000000000000000000000000000026', '5', '1', 'C语言', '2018年06月12日', '2018年07月12日', '是');

-- ----------------------------
-- Table structure for library_user
-- ----------------------------
DROP TABLE IF EXISTS `library_user`;
CREATE TABLE `library_user` (
  `userid` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sex` varchar(10) NOT NULL DEFAULT '男',
  `phone` varchar(11) CHARACTER SET latin1 NOT NULL,
  `power` varchar(10) NOT NULL DEFAULT 'general',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of library_user
-- ----------------------------
INSERT INTO `library_user` VALUES ('0000000004', 'admin', 'd18e1f302fda235854f04bba967f4aa4', '男', '15646168416', 'admin');
INSERT INTO `library_user` VALUES ('0000000006', '1', '26f2f830c23ab29bd87478d45915a636', '男', '15035161322', 'general');
INSERT INTO `library_user` VALUES ('0000000008', '13', 'ca2f9b0b6ca691c97c4781f3c6689446', '男', '15035161324', 'general');
INSERT INTO `library_user` VALUES ('0000000009', '该放松', '8ff53aa182f2e03defb00262ae07c0cd', '男', '18444855553', 'general');
