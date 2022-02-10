/*
 Navicat Premium Data Transfer

 Source Server         : kokoro
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : javat

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 10/02/2022 16:36:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES (1, '计算机理论', 'Spring实战 第五版', 'Spring 入门经典教程');
INSERT INTO `tbl_book` VALUES (2, '计算机理论', 'Spring实战 第六版', 'Spring 入门经典教程');
INSERT INTO `tbl_book` VALUES (3, '测试数据123', '测试数据123', '测试数据123');
INSERT INTO `tbl_book` VALUES (4, '计算机理论', 'Spring 5 设计模式全方位解析面向 Web 应用的轻量级框架', '轻量级框架');
INSERT INTO `tbl_book` VALUES (5, '市场营销', '直播就该这么做', '网红的密码在此');
INSERT INTO `tbl_book` VALUES (6, '市场营销', '直播销讲实战一本通', '和秋叶一起学系列');
INSERT INTO `tbl_book` VALUES (7, '市场营销', '淘宝直播带货', '玩转直播');
INSERT INTO `tbl_book` VALUES (10, '测试数据33', '测试数据33', '测试数据33');
INSERT INTO `tbl_book` VALUES (11, '测试数据44', '测试数据44', '测试数据44');
INSERT INTO `tbl_book` VALUES (12, 'Post测试数据1', 'Post测试数据1', 'Post测试数据1');
INSERT INTO `tbl_book` VALUES (13, 'Post测试数据1', 'Post测试数据1', 'Post测试数据1');
INSERT INTO `tbl_book` VALUES (14, 'Post测试数据1', 'Post测试数据1', 'Post测试数据1');
INSERT INTO `tbl_book` VALUES (15, 'Post测试数据1', 'Post测试数据1', 'Post测试数据1');
INSERT INTO `tbl_book` VALUES (16, 'Post测试数据1', 'Post测试数据1', 'Post测试数据1');
INSERT INTO `tbl_book` VALUES (17, 'Post测试数据1', 'Post测试数据1', 'Post测试数据2');
INSERT INTO `tbl_book` VALUES (18, '22', '22', '33');
INSERT INTO `tbl_book` VALUES (19, '11', '22', '33');
INSERT INTO `tbl_book` VALUES (20, 'aa', 'bb', 'cc');
INSERT INTO `tbl_book` VALUES (21, 'aaa', 'bbb', 'ccc');
INSERT INTO `tbl_book` VALUES (24, '213', '124', '12312');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'demoo', '1241');
INSERT INTO `user` VALUES (3, 'xxx', 'abc');
INSERT INTO `user` VALUES (4, 'xxx', 'abc');
INSERT INTO `user` VALUES (5, 'zhangsan', '124');
INSERT INTO `user` VALUES (6, 'ceshi', 'abc');
INSERT INTO `user` VALUES (7, 'ceshi', 'abc');
INSERT INTO `user` VALUES (8, 'ceshi', 'abc');
INSERT INTO `user` VALUES (9, 'ceshi', 'abc');
INSERT INTO `user` VALUES (10, 'ceshi', 'abc');

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '',
  `age` tinyint(3) UNSIGNED NOT NULL,
  `country` char(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `createtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
