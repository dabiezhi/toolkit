/*
 Navicat MySQL Data Transfer

 Source Server         : bloom
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : poseidon

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 01/04/2023 17:28:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chain
-- ----------------------------
DROP TABLE IF EXISTS `chain`;
CREATE TABLE `chain` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `chain_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `chain_desc` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `el_data` text COLLATE utf8mb4_general_ci,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of chain
-- ----------------------------
BEGIN;
INSERT INTO `chain` VALUES (1, 'demo', 'chain1', '测试流程1', ' IF(d, THEN(a.tag(\"tag1\"), b, c, s4), e);', '2022-09-19 19:31:00');
INSERT INTO `chain` VALUES (2, 'demo', 'chain2', '测试流程2', ' IF(s3, b, c);', '2023-03-29 17:04:19');
INSERT INTO `chain` VALUES (3, 'demo', 'chain3', '测试流程3', ' IF(d, THEN(IF(f,g,THEN(IF(s3,g,h)))), g);', '2023-03-31 15:25:41');
COMMIT;

-- ----------------------------
-- Table structure for script
-- ----------------------------
DROP TABLE IF EXISTS `script`;
CREATE TABLE `script` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `script_id` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `script_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `script_data` text COLLATE utf8mb4_general_ci,
  `script_type` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `script_language` varchar(16) COLLATE utf8mb4_general_ci DEFAULT 'groovy',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of script
-- ----------------------------
BEGIN;
INSERT INTO `script` VALUES (1, 'demo', 's1', '脚本s1', 'import cn.hutool.core.date.DateUtil\n\ndef date = DateUtil.parse(\"2022-10-17 13:31:43\")\nprintln(date)\ndefaultContext.setData(\"demoDate\", date)\n\nclass Student {\n   int studentID\n   String studentName\n}\n\nStudent student = new Student()\nstudent.studentID = 100301\nstudent.studentName = \"张三\"\ndefaultContext.setData(\"student\",student)\n\ndef a=3\ndef b=2\ndefaultContext.setData(\"s1\",a*b)', 'script', 'groovy');
INSERT INTO `script` VALUES (2, 'demo', 's2', '脚本s2', 'defaultContext.setData(\"s2\",\"hello\")', 'script', 'groovy');
INSERT INTO `script` VALUES (3, 'demo', 's3', '条件脚本', 'return false || demo.getFlag()', 'if_script', 'groovy');
INSERT INTO `script` VALUES (4, 'demo', 's4', '脚本s4', 'defaultContext.setData(\"s1\",demo.getStr(_meta.requestData))', 'script', 'groovy');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
