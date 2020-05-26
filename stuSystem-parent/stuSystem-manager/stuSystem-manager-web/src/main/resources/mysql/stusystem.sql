/*
 Navicat Premium Data Transfer

 Source Server         : yrc
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : stusystem

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 26/05/2020 17:57:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announce
-- ----------------------------
DROP TABLE IF EXISTS `announce`;
CREATE TABLE `announce`  (
  `annou_id` int(11) NOT NULL AUTO_INCREMENT,
  `annou_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `annou_content` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `annou_auth` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `annou_link` varchar(42) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `annou_visited` int(11) NULL DEFAULT NULL,
  `annou_intime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`annou_id`) USING BTREE,
  INDEX `annoAuth`(`annou_auth`) USING BTREE,
  CONSTRAINT `annoAuth` FOREIGN KEY (`annou_auth`) REFERENCES `teacher` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for awapply
-- ----------------------------
DROP TABLE IF EXISTS `awapply`;
CREATE TABLE `awapply`  (
  `awApply_id` int(11) NOT NULL AUTO_INCREMENT,
  `awApply_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `awApply_desc` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `awApply_type` int(1) NULL DEFAULT 3,
  `awApply_stuId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `awApply_approver` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `awApply_status` int(1) NULL DEFAULT 0,
  `awApply_intime` datetime(6) NULL DEFAULT NULL,
  `awApply_succtime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`awApply_id`) USING BTREE,
  INDEX `awStuId`(`awApply_stuId`) USING BTREE,
  INDEX `awApprover`(`awApply_approver`) USING BTREE,
  CONSTRAINT `awApprover` FOREIGN KEY (`awApply_approver`) REFERENCES `teacher` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `awStuId` FOREIGN KEY (`awApply_stuId`) REFERENCES `student` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_boss` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_stuCount` int(255) NULL DEFAULT NULL,
  `class_createTime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `class-teach`(`class_boss`) USING BTREE,
  CONSTRAINT `class-teach` FOREIGN KEY (`class_boss`) REFERENCES `teacher` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for courser
-- ----------------------------
DROP TABLE IF EXISTS `courser`;
CREATE TABLE `courser`  (
  `courser_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courser_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courser_type` int(1) NULL DEFAULT 3,
  `courser_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`courser_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `scores_id` int(11) NOT NULL AUTO_INCREMENT,
  `scores_tcId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scores_stuId` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scores_res` double(6, 0) NULL DEFAULT NULL,
  `scores_intime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`scores_id`) USING BTREE,
  INDEX `scores-stu`(`scores_stuId`) USING BTREE,
  INDEX `scores-tc`(`scores_tcId`) USING BTREE,
  CONSTRAINT `scores-stu` FOREIGN KEY (`scores_stuId`) REFERENCES `student` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `scores-tc` FOREIGN KEY (`scores_tcId`) REFERENCES `teachcourse` (`tc_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stu_class` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stu_pwd` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123456',
  `stu_photo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stu_mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_birthday` datetime(6) NULL DEFAULT NULL,
  `stu_level` int(1) NULL DEFAULT 1,
  `stu_online` int(1) UNSIGNED ZEROFILL NULL DEFAULT 2,
  `stu_enSch` datetime(6) NULL DEFAULT NULL,
  `stu_lastLogin` datetime(6) NULL DEFAULT NULL,
  `stu_regLogin` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `stu-class`(`stu_class`) USING BTREE,
  CONSTRAINT `stu-class` FOREIGN KEY (`stu_class`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teachcourse
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse`  (
  `tc_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tc_courser` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tc-teacher` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tc_level` int(1) NULL DEFAULT 1,
  `tc_stuCount` int(11) NULL DEFAULT NULL,
  `tc_start` datetime(6) NULL DEFAULT NULL,
  `tc_end` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`tc_id`) USING BTREE,
  INDEX `tct`(`tc-teacher`) USING BTREE,
  INDEX `ctc`(`tc_courser`) USING BTREE,
  CONSTRAINT `ctc` FOREIGN KEY (`tc_courser`) REFERENCES `courser` (`courser_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tct` FOREIGN KEY (`tc-teacher`) REFERENCES `teacher` (`teach_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teach_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_pwd` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123456',
  `teach_photo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teach_sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_birthday` datetime(6) NULL DEFAULT NULL,
  `teach_mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teach_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teach_title` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teach_level` int(1) NULL DEFAULT 2,
  `teach_line` int(1) NULL DEFAULT 2,
  `teach_enSch` datetime(6) NULL DEFAULT NULL,
  `teach_lastLogin` datetime(6) NULL DEFAULT NULL,
  `teach_regLogin` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`teach_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
