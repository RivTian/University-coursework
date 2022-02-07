/*
 Navicat Premium Data Transfer

 Source Server         : kokoro
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : ssmbook

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 07/02/2022 21:26:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `bookID` int(10) NOT NULL AUTO_INCREMENT COMMENT '书id',
  `bookName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `bookCounts` int(11) NOT NULL COMMENT '数量',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  INDEX `bookID`(`bookID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (2, 'MySQL', 10, '我是Mysql');
INSERT INTO `books` VALUES (3, 'SSM', 5, 'SSM整合啦');
INSERT INTO `books` VALUES (5, '如何养成自律习惯', 1, 'demo');

SET FOREIGN_KEY_CHECKS = 1;
