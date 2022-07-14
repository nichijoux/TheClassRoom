/*
Navicat MySQL Data Transfer

Source Server         : study
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : tcr

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2022-07-14 10:40:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', '18', '第七章：I/O流', '7', '2021-11-05 03:05:51', '2021-12-01 05:48:44', '0');
INSERT INTO `chapter` VALUES ('2', '2', '第一章节', '0', '2021-11-05 03:05:51', '2021-11-05 03:38:14', '0');
INSERT INTO `chapter` VALUES ('3', '3', '第一章', '0', '2021-11-05 03:05:51', '2021-11-05 03:38:24', '0');
INSERT INTO `chapter` VALUES ('4', '4', '第一章', '0', '2021-11-05 03:05:51', '2021-11-05 03:38:34', '0');
INSERT INTO `chapter` VALUES ('5', '14', '第一章：HTML', '0', '2021-11-05 03:05:51', '2021-11-05 03:05:51', '0');
INSERT INTO `chapter` VALUES ('6', '14', '第二章：CSS', '0', '2021-11-05 03:05:51', '2021-11-05 03:05:51', '0');
INSERT INTO `chapter` VALUES ('7', '5', '第一章', '0', '2021-11-05 03:05:51', '2021-11-05 03:38:50', '0');
INSERT INTO `chapter` VALUES ('8', '15', '第一章', '0', '2021-11-05 03:05:51', '2021-11-05 03:39:03', '0');
INSERT INTO `chapter` VALUES ('9', '6', '数据仓库基础', '0', '2021-11-05 03:05:51', '2021-11-05 03:39:14', '0');
INSERT INTO `chapter` VALUES ('10', '7', '课程基础', '0', '2021-11-05 03:05:51', '2021-11-05 03:39:26', '0');
INSERT INTO `chapter` VALUES ('11', '8', '介绍及原理', '0', '2021-11-05 03:05:51', '2021-11-05 03:39:38', '0');
INSERT INTO `chapter` VALUES ('12', '1', 'spark介绍', '0', '2021-11-05 03:05:51', '2021-11-05 03:39:50', '0');
INSERT INTO `chapter` VALUES ('15', '18', '第一章：Java入门', '1', '2021-11-05 03:05:51', '2021-12-01 05:48:35', '0');
INSERT INTO `chapter` VALUES ('16', '18', '第二章：控制台输入和输出', '2', '2021-11-05 03:05:51', '2021-12-01 05:48:36', '0');
INSERT INTO `chapter` VALUES ('17', '18', '第三章：控制流', '3', '2021-11-05 03:05:51', '2021-12-01 05:48:36', '0');
INSERT INTO `chapter` VALUES ('18', '18', '第四章：类的定义', '4', '2021-11-05 03:05:51', '2021-12-01 05:48:38', '0');
INSERT INTO `chapter` VALUES ('19', '18', '第五章：数组', '5', '2021-11-05 03:05:51', '2021-12-01 05:48:41', '0');
INSERT INTO `chapter` VALUES ('20', '18', '第六章：继承', '6', '2021-11-05 03:05:51', '2021-12-01 05:48:42', '0');
INSERT INTO `chapter` VALUES ('65', '19', '入门及安装配置', '0', '2021-11-22 11:10:56', '2021-11-22 11:10:56', '0');
INSERT INTO `chapter` VALUES ('66', '19', 'mysql原理', '1', '2021-11-22 11:11:17', '2021-11-22 11:11:17', '0');
INSERT INTO `chapter` VALUES ('67', '25', '第一章', '0', '2022-07-11 20:28:13', '2022-07-11 20:28:13', '0');
INSERT INTO `chapter` VALUES ('68', '20', '卸载idea2019', '0', '2022-07-11 21:55:19', '2022-07-11 21:55:19', '0');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程id',
  `teacher_id` bigint NOT NULL DEFAULT '0' COMMENT '讲师id',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `idx_member_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for coupon_info
-- ----------------------------
DROP TABLE IF EXISTS `coupon_info`;
CREATE TABLE `coupon_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_type` tinyint NOT NULL DEFAULT '1' COMMENT '购物券类型 1 注册卷 2：推荐赠送卷',
  `coupon_name` varchar(100) DEFAULT NULL COMMENT '优惠卷名字',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `condition_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '使用门槛 0->没门槛',
  `start_time` date DEFAULT NULL COMMENT '可以领取的开始日期',
  `end_time` date DEFAULT NULL COMMENT '可以领取的结束日期',
  `range_type` tinyint NOT NULL DEFAULT '1' COMMENT '使用范围[1->全场通用]',
  `rule_desc` varchar(200) DEFAULT NULL COMMENT '规则描述',
  `publish_count` int NOT NULL DEFAULT '1' COMMENT '发行数量',
  `per_limit` int NOT NULL DEFAULT '1' COMMENT '每人限领张数',
  `use_count` int NOT NULL DEFAULT '0' COMMENT '已使用数量',
  `receive_count` int NOT NULL DEFAULT '0' COMMENT '领取数量',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `publish_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发布状态[0-未发布，1-已发布]',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='优惠券信息';

-- ----------------------------
-- Records of coupon_info
-- ----------------------------
INSERT INTO `coupon_info` VALUES ('1', '1', '双十一新用户注册赠送100元卷', '100.00', '0.00', '2021-06-03', '2022-07-10', '2', '双十一新用户注册赠送100元卷，全程通用，没有限制', '100', '1', '0', '3', '2021-07-02 00:00:00', '1', '2021-06-06 18:29:14', '2021-11-12 08:30:39', '0');
INSERT INTO `coupon_info` VALUES ('2', '1', '双十一推荐课程并购买600福利卷', '600.00', '0.00', '2021-08-04', '2022-10-06', '2', '双十一推荐课程，新用户购买并支付，赠送推荐人600福利卷，全程通用，没有限制', '100', '1', '0', '4', '2022-08-26 00:00:00', '1', '2021-08-17 11:35:56', '2021-11-12 08:31:37', '0');
INSERT INTO `coupon_info` VALUES ('3', '2', '国庆新用户注册赠送100元卷', '100.00', '0.00', '2021-09-05', '2022-10-06', '1', '国庆新用户注册赠送100元卷，全程通用，没有限制', '100', '1', '0', '4', '2021-10-07 00:00:00', '1', '2021-09-28 06:14:38', '2021-11-12 08:31:10', '0');
INSERT INTO `coupon_info` VALUES ('4', '1', '国庆推荐课程并购买500福利卷', '500.00', '0.00', '2021-09-27', '2022-09-23', '1', '双十一推荐课程，新用户购买并支付，赠送推荐人500福利卷，全程通用，没有限制', '100', '1', '0', '25', '2022-09-30 00:00:00', '1', '2021-09-28 06:50:17', '2021-11-12 08:31:16', '0');
INSERT INTO `coupon_info` VALUES ('5', '1', 'TCR注册卷', '0.00', '0.00', '2022-07-12', '2022-07-13', '1', '注册即送', '1', '1', '0', '0', '2022-07-30 00:00:00', '0', '2022-07-12 16:42:42', '2022-07-12 16:42:42', '0');

-- ----------------------------
-- Table structure for coupon_use
-- ----------------------------
DROP TABLE IF EXISTS `coupon_use`;
CREATE TABLE `coupon_use` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `coupon_id` bigint DEFAULT NULL COMMENT '购物券ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `coupon_status` varchar(10) DEFAULT NULL COMMENT '购物券状态（1：未使用 2：已使用）',
  `get_time` datetime DEFAULT NULL COMMENT '获取时间',
  `using_time` datetime DEFAULT NULL COMMENT '使用时间',
  `used_time` datetime DEFAULT NULL COMMENT '支付时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='优惠券领用表';

-- ----------------------------
-- Records of coupon_use
-- ----------------------------
INSERT INTO `coupon_use` VALUES ('1', '1', '1', null, '0', '2021-11-10 09:02:23', null, null, '2021-07-02 00:00:00', '2021-11-10 09:02:23', '2021-11-22 07:59:00', '0');
INSERT INTO `coupon_use` VALUES ('2', '1', '1', null, '0', '2021-11-10 09:02:23', null, null, '2022-07-02 00:00:00', '2021-11-10 09:02:28', '2021-11-22 07:59:02', '0');
INSERT INTO `coupon_use` VALUES ('5', '4', '1', '4', '1', '2021-11-12 16:37:00', '2021-11-23 18:57:27', null, '2022-09-30 00:00:00', '2021-11-12 08:36:58', '2021-11-23 10:57:27', '0');
INSERT INTO `coupon_use` VALUES ('9', '4', '1', '1', '1', '2021-11-22 14:50:05', '2021-11-22 21:38:48', '2021-11-22 21:39:12', '2022-09-30 00:00:00', '2021-11-22 14:50:05', '2021-11-22 13:39:12', '0');
INSERT INTO `coupon_use` VALUES ('27', '4', '24', null, '0', '2021-11-23 18:14:01', null, null, '2022-09-30 00:00:00', '2021-11-23 10:14:01', '2021-11-23 10:14:01', '0');
INSERT INTO `coupon_use` VALUES ('28', '4', '25', null, '0', '2021-11-23 18:49:01', null, null, '2022-09-30 00:00:00', '2021-11-23 10:49:00', '2021-11-23 10:49:00', '0');
INSERT INTO `coupon_use` VALUES ('29', '4', '26', null, '0', '2021-11-23 18:49:03', null, null, '2022-09-30 00:00:00', '2021-11-23 10:49:03', '2021-11-23 10:49:03', '0');
INSERT INTO `coupon_use` VALUES ('30', '4', '27', '5', '1', '2021-11-23 18:50:03', '2021-11-23 18:57:52', null, '2022-09-30 00:00:00', '2021-11-23 10:50:02', '2021-11-23 10:57:52', '0');
INSERT INTO `coupon_use` VALUES ('31', '4', '28', null, '0', '2021-11-23 18:52:49', null, null, '2022-09-30 00:00:00', '2021-11-23 10:52:49', '2021-11-23 10:52:49', '0');
INSERT INTO `coupon_use` VALUES ('32', '4', '29', null, '0', '2021-11-26 08:57:40', null, null, '2022-09-30 00:00:00', '2021-11-26 00:57:39', '2021-11-26 00:57:39', '0');
INSERT INTO `coupon_use` VALUES ('33', '4', '29', null, '0', '2021-11-26 18:33:04', null, null, '2022-09-30 00:00:00', '2021-11-26 10:33:03', '2021-11-26 10:33:03', '0');
INSERT INTO `coupon_use` VALUES ('34', '4', '30', null, '0', '2021-11-26 18:34:12', null, null, '2022-09-30 00:00:00', '2021-11-26 10:34:11', '2021-11-26 10:34:11', '0');
INSERT INTO `coupon_use` VALUES ('35', '4', '31', null, '0', '2021-11-28 16:46:53', null, null, '2022-09-30 00:00:00', '2021-11-28 08:46:53', '2021-11-28 08:46:53', '0');
INSERT INTO `coupon_use` VALUES ('36', '4', '32', null, '0', '2021-12-28 12:29:18', null, null, '2022-09-30 00:00:00', '2021-12-28 12:29:17', '2021-12-28 12:29:17', '0');
INSERT INTO `coupon_use` VALUES ('37', '4', '33', null, '0', '2022-01-05 14:35:16', null, null, '2022-09-30 00:00:00', '2022-01-05 14:35:15', '2022-01-05 14:35:15', '0');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` bigint NOT NULL DEFAULT '0' COMMENT '课程讲师ID',
  `subject_id` bigint NOT NULL DEFAULT '0' COMMENT '课程专业ID',
  `subject_parent_id` bigint NOT NULL DEFAULT '0' COMMENT '课程专业父级ID',
  `title` varchar(50) NOT NULL COMMENT '课程标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `duration_sum` int NOT NULL DEFAULT '0' COMMENT '视频总时长（秒）',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '课程状态 0未发布 1已发布',
  `publish_time` datetime DEFAULT NULL COMMENT '课程发布时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除，1为删除，0为未删除',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1', '16', '14', 'Spark', '21800.00', '1', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/39e51c23-e2fa-4a8a-9239-3c0d5268f34d.jpg', '1679', '34634', '1', '2021-11-05 11:30:14', '2021-11-05 03:05:16', '2022-07-11 20:13:55', '0');
INSERT INTO `course` VALUES ('2', '1', '2', '1', 'java基础课程', '19800.00', '2', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/bd89ae0b-e660-47fd-9ed7-ad8733a46c65.jpg', '5999', '10052', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:37', '0');
INSERT INTO `course` VALUES ('3', '1', '15', '14', '尚硅谷大数据技术之Flume（2019新版）', '20800.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/806572e0-6d85-485e-8784-d2cb4716a881.jpg', '4234', '23780', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:36', '0');
INSERT INTO `course` VALUES ('4', '2', '15', '14', '尚硅谷大数据技术之HBase（2019新版）', '19800.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/a16c5694-3037-4330-b1c5-438052081fcb.jpg', '6675', '90383', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:34', '0');
INSERT INTO `course` VALUES ('5', '1', '1', '2', '尚硅谷大数据技术之Kafka（2019新版）', '22800.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2349', '13546', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:33', '0');
INSERT INTO `course` VALUES ('6', '2', '15', '14', '尚硅谷大数据项目之电商数仓', '21800.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/dd2da284-b37f-4818-ba9a-c55667837f5a.jpg', '898', '8977', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:32', '0');
INSERT INTO `course` VALUES ('7', '2', '15', '14', '尚硅谷大数据技术之Sqoop', '23800.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/9452b057-6ad6-4600-891e-b168083fee4d.jpg', '1286', '45695', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:31', '0');
INSERT INTO `course` VALUES ('8', '1', '16', '14', '大数据Scala入门到精通（新版）', '0.00', '0', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/f2cd321f-6378-4e92-8515-0b8f42f2770b.jpg', '900', '15496', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:13', '0');
INSERT INTO `course` VALUES ('14', '1', '4', '3', 'XHTML CSS2 JS整站制作教程课程学习', '21800.00', '3', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/2829c8d1-f16f-44a4-96cd-d13b451a8d56.jpg', '3452', '21010', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:28', '0');
INSERT INTO `course` VALUES ('15', '2', '2', '1', '  14417人 分享 收藏 SpringMVC', '22800.00', '23', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/273ddd90-5ef7-40e5-9ffd-86e8175fc229.jpg', '892', '9107', '0', null, '2021-11-05 03:05:16', '2021-11-24 06:37:24', '0');
INSERT INTO `course` VALUES ('18', '2', '2', '1', 'Java精品课程', '22800.00', '20', '100000', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/e4ee03d7-52bd-41ca-99f9-04dc23250a71.jpg', '6784', '67629', '1', '2021-11-19 14:16:19', '2021-11-05 03:05:16', '2021-11-24 06:37:25', '0');
INSERT INTO `course` VALUES ('19', '1', '2', '1', 'JAVA之Mysql基础', '1000.00', '10', '100201', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '0', '155', '1', '2021-11-25 09:57:42', '2021-11-22 11:09:22', '2021-11-25 01:57:39', '0');
INSERT INTO `course` VALUES ('20', '11', '10', '8', '重装idea', '0.00', '1', '0', '', '0', '0', '1', '2022-07-11 21:58:18', '2022-07-11 19:48:50', '2022-07-11 19:48:50', '0');

-- ----------------------------
-- Table structure for course_collect
-- ----------------------------
DROP TABLE IF EXISTS `course_collect`;
CREATE TABLE `course_collect` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程讲师ID',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '会员ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程收藏';

-- ----------------------------
-- Records of course_collect
-- ----------------------------

-- ----------------------------
-- Table structure for course_description
-- ----------------------------
DROP TABLE IF EXISTS `course_description`;
CREATE TABLE `course_description` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint DEFAULT NULL,
  `description` text COMMENT '课程简介',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程简介';

-- ----------------------------
-- Records of course_description
-- ----------------------------
INSERT INTO `course_description` VALUES ('1', '1', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:29:33', '2021-11-08 11:27:01', '0');
INSERT INTO `course_description` VALUES ('2', '2', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:30:07', '2021-11-08 11:27:04', '0');
INSERT INTO `course_description` VALUES ('3', '3', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:30:47', '2021-11-08 11:27:07', '0');
INSERT INTO `course_description` VALUES ('4', '4', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>\n', '2021-11-05 03:33:18', '2021-11-08 11:25:23', '0');
INSERT INTO `course_description` VALUES ('5', '5', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:41:05', '2021-11-08 11:27:10', '0');
INSERT INTO `course_description` VALUES ('6', '6', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:41:27', '2021-11-08 11:27:12', '0');
INSERT INTO `course_description` VALUES ('7', '7', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:41:43', '2021-11-08 11:27:22', '0');
INSERT INTO `course_description` VALUES ('8', '8', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>', '2021-11-05 03:42:01', '2021-11-08 11:25:30', '0');
INSERT INTO `course_description` VALUES ('9', '14', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:42:16', '2021-11-08 11:27:24', '0');
INSERT INTO `course_description` VALUES ('10', '15', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:42:32', '2021-11-08 11:27:26', '0');
INSERT INTO `course_description` VALUES ('11', '18', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>', '2021-11-05 03:42:51', '2021-11-08 11:27:28', '0');
INSERT INTO `course_description` VALUES ('12', '19', ' 数据库就像一棵常青的技能树，无论是初级程序员还是CTO、首席架构师都能从中汲取足够的技术养料。菜鸟往往积累单点技术，如 DML、DDL、存储过程和函数、约束、索引的数据结构，老鸟则需要吃透底层原理，数据库事务ACID如何实现？锁机制与MVCC又是怎么回事？分布式场景下数据库怎么优化保持高性能？\n      知道怎么用是一方面，知道为什么则是更为稀缺的能力。程序员核心能力中至关重要的一点：精通数据库。精通意味着：第一，形成知识网，更灵活地应对突发问题；第二，懂底层原理，更自由地应对复杂多变的业务场景。', '2021-11-22 11:09:22', '2021-11-22 11:09:22', '0');
INSERT INTO `course_description` VALUES ('20', '20', '教你如何重装idea', '2022-07-11 19:48:50', '2022-07-11 20:23:58', '0');
INSERT INTO `course_description` VALUES ('23', '23', '测试', '2022-07-11 20:15:45', '2022-07-11 21:55:00', '1');
INSERT INTO `course_description` VALUES ('25', null, '现在有了', '2022-07-11 20:27:55', '2022-07-11 21:54:17', '1');

-- ----------------------------
-- Table structure for live_course
-- ----------------------------
DROP TABLE IF EXISTS `live_course`;
CREATE TABLE `live_course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_id` bigint DEFAULT NULL COMMENT '分类Id',
  `course_name` varchar(64) NOT NULL DEFAULT '' COMMENT '直播名称',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '直播开始时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '直播结束时间',
  `cover` varchar(255) DEFAULT NULL,
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程id',
  `teacher_id` bigint DEFAULT NULL COMMENT '主播老师id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程表';

-- ----------------------------
-- Records of live_course
-- ----------------------------
INSERT INTO `live_course` VALUES ('1', null, 'Spring Cloud源码讲解', '2021-11-07 17:00:00', '2021-10-28 17:00:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2687659', '2', '2021-10-27 08:11:33', '2021-11-22 13:25:21', '0');
INSERT INTO `live_course` VALUES ('2', null, '大数据Spark全面分析', '2021-11-27 17:00:00', '2021-10-28 17:00:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2671785', '1', '2021-10-27 08:50:18', '2021-11-22 13:25:27', '0');
INSERT INTO `live_course` VALUES ('3', null, '微服务架构演进', '2021-12-28 16:00:00', '2021-10-29 16:00:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2679789', '1', '2021-10-28 07:42:04', '2021-11-22 13:25:36', '0');
INSERT INTO `live_course` VALUES ('4', null, 'JAVA新特性全面讲解', '2021-11-08 14:30:00', '2021-11-09 14:30:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2687659', '4', '2021-11-08 06:00:43', '2021-11-09 07:44:03', '0');
INSERT INTO `live_course` VALUES ('5', null, 'Spring MVC讲解', '2021-11-09 15:04:00', '2021-11-10 15:00:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/f5ada6ba-8d12-4c00-8ad9-6a521f71b0da.jpg', '2689593', '2', '2021-11-09 07:40:37', '2021-11-22 13:31:03', '0');
INSERT INTO `live_course` VALUES ('7', null, 'Spark讲解', '2021-11-23 18:30:00', '2021-11-23 22:00:00', 'https://cdn.uviewui.com/uview/swiper/1.jpg', '2711543', '6', '2021-11-23 10:40:44', '2021-11-23 10:40:44', '0');
INSERT INTO `live_course` VALUES ('8', null, '11月26日晚8点电商分享', '2021-11-26 17:00:00', '2021-11-27 17:00:00', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/2829c8d1-f16f-44a4-96cd-d13b451a8d56.jpg', '2716727', '6', '2021-11-26 09:19:39', '2021-11-26 09:20:58', '0');

-- ----------------------------
-- Table structure for live_course_account
-- ----------------------------
DROP TABLE IF EXISTS `live_course_account`;
CREATE TABLE `live_course_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `zhubo_account` varchar(20) DEFAULT NULL COMMENT '主播账号（欢拓系统的主播id）',
  `zhubo_password` varchar(32) DEFAULT NULL COMMENT '主播密码',
  `zhubo_key` varchar(64) NOT NULL DEFAULT '' COMMENT '主播登录秘钥',
  `admin_key` varchar(32) NOT NULL DEFAULT '' COMMENT '助教登录秘钥',
  `user_key` varchar(32) NOT NULL DEFAULT '' COMMENT '学生登录秘钥',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程账号表（受保护信息）';

-- ----------------------------
-- Records of live_course_account
-- ----------------------------
INSERT INTO `live_course_account` VALUES ('1', '1', '582523', null, '2518', '6240', '8447', '2021-10-27 08:11:33', '2021-10-27 08:11:33', '0');
INSERT INTO `live_course_account` VALUES ('2', '2', '582549', '000000', '1325', '6632', '8582', '2021-10-27 08:50:30', '2021-10-27 08:50:30', '0');
INSERT INTO `live_course_account` VALUES ('3', '3', '582825', '111111', '1758', '5167', '8643', '2021-10-28 07:42:04', '2021-10-28 07:42:04', '0');
INSERT INTO `live_course_account` VALUES ('4', '4', '582549', '000000', '3800', '4233', '8874', '2021-11-08 06:00:43', '2021-11-08 06:04:27', '0');
INSERT INTO `live_course_account` VALUES ('5', '5', '582523', '111111', '3060', '4868', '8963', '2021-11-09 07:40:37', '2021-11-09 07:40:37', '0');
INSERT INTO `live_course_account` VALUES ('6', '6', '582825', '111111', '3977', '4548', '8658', '2021-11-22 13:29:18', '2021-11-22 13:29:18', '0');
INSERT INTO `live_course_account` VALUES ('7', '7', '589813', '111111', '1921', '4328', '8302', '2021-11-23 10:40:44', '2021-11-23 10:40:44', '0');
INSERT INTO `live_course_account` VALUES ('8', '8', '589813', '111111', '3108', '6824', '8717', '2021-11-26 09:19:39', '2021-11-26 09:19:39', '0');

-- ----------------------------
-- Table structure for live_course_config
-- ----------------------------
DROP TABLE IF EXISTS `live_course_config`;
CREATE TABLE `live_course_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `page_view_mode` tinyint NOT NULL DEFAULT '0' COMMENT '界面模式 1全屏模式 0二分屏 2课件模式',
  `number_enable` tinyint NOT NULL DEFAULT '0' COMMENT '是否开启 观看人数 0否 1是',
  `store_enable` tinyint NOT NULL DEFAULT '0' COMMENT '商城是否开启 0未开启 1开启',
  `store_type` tinyint NOT NULL DEFAULT '1' COMMENT '1商品列表,2商城链接,3商城二维码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程配置表';

-- ----------------------------
-- Records of live_course_config
-- ----------------------------
INSERT INTO `live_course_config` VALUES ('1', '2', '1', '1', '1', '1', '2021-10-28 07:27:23', '2021-10-28 07:27:23', '0');
INSERT INTO `live_course_config` VALUES ('2', '3', '1', '1', '1', '1', '2021-10-28 07:42:29', '2021-10-28 07:42:29', '0');
INSERT INTO `live_course_config` VALUES ('3', '4', '1', '1', '1', '1', '2021-11-08 06:12:04', '2021-11-08 06:12:04', '0');
INSERT INTO `live_course_config` VALUES ('4', '5', '1', '1', '1', '1', '2021-11-09 07:54:01', '2021-11-09 07:54:01', '0');
INSERT INTO `live_course_config` VALUES ('5', '6', '1', '1', '1', '1', '2021-11-22 13:32:13', '2021-11-22 13:32:13', '0');
INSERT INTO `live_course_config` VALUES ('6', '7', '1', '1', '1', '1', '2021-11-23 10:42:01', '2021-11-23 10:42:01', '0');
INSERT INTO `live_course_config` VALUES ('7', '8', '1', '1', '1', '1', '2021-11-26 09:20:07', '2021-11-26 09:20:07', '0');

-- ----------------------------
-- Table structure for live_course_description
-- ----------------------------
DROP TABLE IF EXISTS `live_course_description`;
CREATE TABLE `live_course_description` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint DEFAULT NULL,
  `description` text COMMENT '课程简介',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程简介';

-- ----------------------------
-- Records of live_course_description
-- ----------------------------
INSERT INTO `live_course_description` VALUES ('1', '1', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>\n', '2021-11-08 01:42:07', '2021-11-08 01:42:18', '0');
INSERT INTO `live_course_description` VALUES ('2', '2', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>\n', '2021-11-08 01:42:25', '2021-11-08 01:42:30', '0');
INSERT INTO `live_course_description` VALUES ('14', '3', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>\n', '2021-11-08 01:42:34', '2021-11-08 01:42:34', '0');
INSERT INTO `live_course_description` VALUES ('15', '4', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>', '2021-11-08 06:00:43', '2021-11-08 06:24:59', '0');
INSERT INTO `live_course_description` VALUES ('16', '5', '本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。', '2021-11-09 07:40:37', '2021-11-09 07:40:37', '0');
INSERT INTO `live_course_description` VALUES ('17', '6', ' 数据库就像一棵常青的技能树，无论是初级程序员还是CTO、首席架构师都能从中汲取足够的技术养料。菜鸟往往积累单点技术，如 DML、DDL、存储过程和函数、约束、索引的数据结构，老鸟则需要吃透底层原理，数据库事务ACID如何实现？锁机制与MVCC又是怎么回事？分布式场景下数据库怎么优化保持高性能？\n      知道怎么用是一方面，知道为什么则是更为稀缺的能力。程序员核心能力中至关重要的一点：精通数据库。精通意味着：第一，形成知识网，更灵活地应对突发问题；第二，懂底层原理，更自由地应对复杂多变的业务场景。', '2021-11-22 13:29:18', '2021-11-22 13:29:18', '0');
INSERT INTO `live_course_description` VALUES ('18', '7', 'Spring4.0是 Spring 推出的一个重大版本升级，进一步加强了 Spring 作为 Java 领域第一开源平台的地位。Spring4.0 引入了众多 Java 开发者期盼的新特性，如泛型依赖注入、SpEL、校验及格式化框架、Rest风格的 WEB 编程模型等。这些新功能实用性强、易用性高，可大幅降低 JavaEE 开发的难度，同时有效提升应用开发的优雅性。', '2021-11-23 10:40:44', '2021-11-23 10:40:44', '0');
INSERT INTO `live_course_description` VALUES ('19', '8', ' 数据库就像一棵常青的技能树，无论是初级程序员还是CTO、首席架构师都能从中汲取足够的技术养料。菜鸟往往积累单点技术，如 DML、DDL、存储过程和函数、约束、索引的数据结构，老鸟则需要吃透底层原理，数据库事务ACID如何实现？锁机制与MVCC又是怎么回事？分布式场景下数据库怎么优化保持高性能？\n      知道怎么用是一方面，知道为什么则是更为稀缺的能力。程序员核心能力中至关重要的一点：精通数据库。精通意味着：第一，形成知识网，更灵活地应对突发问题；第二，懂底层原理，更自由地应对复杂多变的业务场景。\n', '2021-11-26 09:19:39', '2021-11-26 09:21:18', '0');

-- ----------------------------
-- Table structure for live_course_goods
-- ----------------------------
DROP TABLE IF EXISTS `live_course_goods`;
CREATE TABLE `live_course_goods` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '推荐点播课程id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品现价',
  `originalPrice` decimal(10,0) DEFAULT NULL COMMENT '商品原价',
  `tab` tinyint DEFAULT NULL COMMENT '商品标签',
  `url` varchar(255) DEFAULT NULL COMMENT '商品链接',
  `putaway` varchar(255) DEFAULT NULL COMMENT '商品状态：0下架，1上架，2推荐',
  `pay` tinyint DEFAULT NULL COMMENT '购买模式(1,链接购买 2,二维码购买)',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '商品二维码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程关联推荐表';

-- ----------------------------
-- Records of live_course_goods
-- ----------------------------
INSERT INTO `live_course_goods` VALUES ('1', '2', '1', '小米1', 'http://47.93.148.192:9000/gmall/20211027/235.jpg', '10', '10', '1', 'http://item.atguigu.cn/1.html', '1', '1', '', '2021-10-28 07:29:36', '2021-10-28 08:54:17', '0');
INSERT INTO `live_course_goods` VALUES ('2', '3', '1', '小米2', 'http://47.93.148.192:9000/gmall/20211027/235.jpg', '10', '10', '1', 'http://item.atguigu.cn/2.html', '1', '1', '', '2021-10-28 07:42:30', '2021-10-28 08:52:54', '0');
INSERT INTO `live_course_goods` VALUES ('3', '4', '18', 'Java精品课程', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/e4ee03d7-52bd-41ca-99f9-04dc23250a71.jpg', '22800', '22800', '1', 'http://item.gmall.com/118.html', '1', '1', '', '2021-11-08 06:12:05', '2021-11-08 06:12:05', '0');
INSERT INTO `live_course_goods` VALUES ('4', '4', '8', '大数据Scala入门到精通（新版）', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/f2cd321f-6378-4e92-8515-0b8f42f2770b.jpg', '0', '0', '1', 'http://item.gmall.com/18.html', '1', '1', '', '2021-11-08 06:12:05', '2021-11-08 06:12:05', '0');
INSERT INTO `live_course_goods` VALUES ('5', '5', '15', '  14417人 分享 收藏 SpringMVC', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/273ddd90-5ef7-40e5-9ffd-86e8175fc229.jpg', '22800', '22800', '1', 'http://glkt.atguigu.cn/#/courseInfo/15', '1', '1', '', '2021-11-09 07:54:01', '2021-11-09 07:54:01', '0');
INSERT INTO `live_course_goods` VALUES ('6', '6', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '1000', '1000', '1', 'http://glkt.atguigu.cn/#/courseInfo/19', '1', '1', '', '2021-11-22 13:32:13', '2021-11-22 13:32:13', '0');
INSERT INTO `live_course_goods` VALUES ('7', '6', '18', 'Java精品课程', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/e4ee03d7-52bd-41ca-99f9-04dc23250a71.jpg', '22800', '22800', '1', 'http://glkt.atguigu.cn/#/courseInfo/18', '1', '1', '', '2021-11-22 13:32:13', '2021-11-22 13:32:13', '0');
INSERT INTO `live_course_goods` VALUES ('8', '7', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '1000', '1000', '1', 'http://glkt.atguigu.cn/#/courseInfo/19', '1', '1', '', '2021-11-23 10:42:01', '2021-11-23 10:42:01', '0');
INSERT INTO `live_course_goods` VALUES ('9', '7', '15', '  14417人 分享 收藏 SpringMVC', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/273ddd90-5ef7-40e5-9ffd-86e8175fc229.jpg', '22800', '22800', '1', 'http://glkt.atguigu.cn/#/courseInfo/15', '1', '1', '', '2021-11-23 10:42:01', '2021-11-23 10:42:01', '0');
INSERT INTO `live_course_goods` VALUES ('10', '8', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '1000', '1000', '1', 'http://glkt.atguigu.cn/#/courseInfo/19', '1', '1', '', '2021-11-26 09:20:07', '2021-11-26 09:20:07', '0');
INSERT INTO `live_course_goods` VALUES ('11', '8', '15', '  14417人 分享 收藏 SpringMVC', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/273ddd90-5ef7-40e5-9ffd-86e8175fc229.jpg', '22800', '22800', '1', 'http://glkt.atguigu.cn/#/courseInfo/15', '1', '1', '', '2021-11-26 09:20:07', '2021-11-26 09:20:07', '0');

-- ----------------------------
-- Table structure for live_visitor
-- ----------------------------
DROP TABLE IF EXISTS `live_visitor`;
CREATE TABLE `live_visitor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `course_name` varchar(100) DEFAULT NULL,
  `user_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '来访者用户id',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `join_time` varchar(30) DEFAULT NULL COMMENT '进入时间',
  `leave_time` varchar(30) DEFAULT NULL COMMENT '离开的时间',
  `location` varchar(50) DEFAULT NULL COMMENT '用户地理位置',
  `duration` bigint DEFAULT NULL COMMENT '用户停留的时间(单位：秒)',
  `duration_time` varchar(30) DEFAULT NULL COMMENT '用户停留时间(时分秒)',
  `live_visitor_id` varchar(50) DEFAULT NULL COMMENT '平台来访者id，去重使用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_live_visitor_id` (`live_visitor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播来访者记录表';

-- ----------------------------
-- Records of live_visitor
-- ----------------------------
INSERT INTO `live_visitor` VALUES ('6', '1', 'Spring Cloud深入源码讲解', '1', '晴天', '2021-11-08 14:41:43', '2021-11-08 14:48:05', '中国北京北京', '382', '00:06:22', '220172045', '2021-11-11 08:01:42', '2021-11-11 08:01:42', '0');
INSERT INTO `live_visitor` VALUES ('7', '1', 'Spring Cloud深入源码讲解', 'zb_xid_508954845', '钟老师', '2021-11-08 14:41:44', '2021-11-08 14:47:47', '中国四川成都', '363', '00:06:03', '220172043', '2021-11-11 08:01:42', '2021-11-11 08:01:42', '0');
INSERT INTO `live_visitor` VALUES ('8', '1', 'Spring Cloud深入源码讲解', '4', '晴天', '2021-11-08 14:10:06', '2021-11-08 14:25:23', '中国四川成都', '917', '00:15:17', '220171079', '2021-11-11 08:01:42', '2021-11-11 08:01:42', '0');
INSERT INTO `live_visitor` VALUES ('9', '1', 'Spring Cloud深入源码讲解', 'zb_xid_508954845', '钟老师', '2021-11-08 14:04:41', '2021-11-08 14:35:09', '中国四川成都', '1828', '00:30:28', '220171077', '2021-11-11 08:01:42', '2021-11-11 08:01:42', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint DEFAULT NULL COMMENT '上级id',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `url` varchar(100) DEFAULT NULL COMMENT '网页 链接，用户点击菜单可打开链接',
  `menu_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单KEY值，用于消息接口推送',
  `sort` tinyint DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='订单明细 订单明细';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '直播', null, null, null, '1', '2021-11-24 08:41:53', '2021-11-24 08:44:55', '0');
INSERT INTO `menu` VALUES ('2', '0', '课程', null, null, null, '2', '2021-11-24 08:41:57', '2021-11-25 01:33:52', '0');
INSERT INTO `menu` VALUES ('3', '0', '我的', null, null, null, '3', '2021-11-24 08:42:00', '2021-11-25 01:34:16', '0');
INSERT INTO `menu` VALUES ('4', '3', '关于我们', 'click', null, 'aboutUs', '10', '2021-11-24 08:42:05', '2021-11-24 08:45:00', '0');
INSERT INTO `menu` VALUES ('5', '1', '微服务架构演进', 'view', '/liveInfo/3', '', '2', '2021-11-24 10:29:12', '2021-11-25 01:26:13', '0');
INSERT INTO `menu` VALUES ('6', '1', '大数据Spark全面分析', 'view', '/liveInfo/2', '', '4', '2021-11-24 10:29:24', '2021-11-25 01:27:05', '0');
INSERT INTO `menu` VALUES ('7', '2', '后端开发', 'view', '/course/1', '', '1', '2021-11-24 10:31:48', '2021-11-25 01:27:06', '0');
INSERT INTO `menu` VALUES ('8', '2', '大数据', 'view', '/course/2', '', '2', '2021-11-24 10:31:59', '2021-11-25 01:27:07', '0');
INSERT INTO `menu` VALUES ('9', '3', '我的订单', 'view', '/order', '', '1', '2021-11-25 01:19:25', '2021-11-25 01:27:07', '0');
INSERT INTO `menu` VALUES ('10', '3', '我的课程', 'view', '/myCourse', '', '2', '2021-11-25 01:26:51', '2021-11-25 01:26:51', '0');
INSERT INTO `menu` VALUES ('11', '1', '全部列表', 'view', '/live', '', '6', '2021-11-25 01:41:47', '2021-11-25 01:41:47', '0');
INSERT INTO `menu` VALUES ('12', '3', '我的优惠券', 'view', '/coupon', null, '3', '2021-11-26 08:52:27', '2021-11-26 08:52:40', '0');
INSERT INTO `menu` VALUES ('13', '1', '11月26日晚8点电商分享', 'view', '/liveInfo/8', '', '1', '2021-11-26 09:21:39', '2021-11-26 09:21:39', '0');
INSERT INTO `menu` VALUES ('14', '2', '编程语言', 'view', '/course/3', '', '3', '2022-07-12 17:13:25', '2022-07-12 17:13:25', '0');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `course_id` bigint DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(4000) DEFAULT NULL COMMENT '课程名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '课程封面',
  `order_id` bigint DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `origin_amount` decimal(16,2) DEFAULT NULL COMMENT '原始金额',
  `coupon_reduce` decimal(16,2) DEFAULT NULL COMMENT '优惠劵减免金额',
  `final_amount` decimal(16,2) DEFAULT NULL COMMENT '最终金额',
  `session_id` varchar(4000) DEFAULT NULL COMMENT '会话id 当前会话id 继承购物车中会话id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_oid_cid` (`order_id`,`course_id`),
  KEY `idx_cid` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='订单明细 订单明细';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '1', '1', '1000.00', '0.00', '1000.00', null, '2021-11-22 13:38:47', '2021-11-26 03:07:25', '0');
INSERT INTO `order_detail` VALUES ('2', '7', '尚硅谷大数据技术之Sqoop', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/10/9452b057-6ad6-4600-891e-b168083fee4d.jpg', '2', '1', '23800.00', '0.00', '23800.00', null, '2021-11-23 10:09:08', '2021-11-26 03:08:05', '0');
INSERT INTO `order_detail` VALUES ('3', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '3', '25', '1000.00', '0.00', '1000.00', null, '2021-11-23 10:54:51', '2021-11-26 03:07:26', '0');
INSERT INTO `order_detail` VALUES ('4', '18', 'Java精品课程', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/e4ee03d7-52bd-41ca-99f9-04dc23250a71.jpg', '4', '1', '22800.00', '0.00', '22800.00', null, '2021-11-23 10:57:27', '2021-11-26 03:07:41', '0');
INSERT INTO `order_detail` VALUES ('5', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '5', '27', '1000.00', '0.00', '1000.00', null, '2021-11-23 10:57:52', '2021-11-26 03:07:27', '0');
INSERT INTO `order_detail` VALUES ('6', '4', '尚硅谷大数据技术之HBase（2019新版）', 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/cover/2021/08/09/a16c5694-3037-4330-b1c5-438052081fcb.jpg', '6', '1', '19800.00', '0.00', '19800.00', null, '2021-11-26 08:56:07', '2021-11-26 08:56:07', '0');
INSERT INTO `order_detail` VALUES ('7', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '7', '29', '1000.00', '0.00', '1000.00', null, '2021-11-26 10:35:46', '2021-11-26 10:35:46', '0');
INSERT INTO `order_detail` VALUES ('8', '19', 'JAVA之Mysql基础', 'http://47.93.148.192:9000/gmall/20211122/1504320cbe2b246514.jpg', '8', '32', '1000.00', '0.00', '1000.00', null, '2021-12-28 15:25:01', '2021-12-28 15:25:01', '0');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `nick_name` varchar(30) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `origin_amount` decimal(10,2) DEFAULT NULL COMMENT '原始金额',
  `coupon_reduce` decimal(10,2) DEFAULT NULL COMMENT '优惠券减免',
  `final_amount` decimal(10,2) DEFAULT NULL COMMENT '最终金额',
  `order_status` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `out_trade_no` varchar(50) DEFAULT NULL COMMENT '订单交易编号（第三方支付用)',
  `trade_body` varchar(200) DEFAULT NULL COMMENT '订单描述(第三方支付用)',
  `session_id` varchar(100) DEFAULT NULL COMMENT 'session id',
  `province` varchar(20) DEFAULT NULL COMMENT '地区id',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `expire_time` datetime DEFAULT NULL COMMENT '失效时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='订单表 订单表';

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('1', '1', '晴天', '15010546384', '1000.00', '500.00', '500.00', '1', '20211122213847830', 'JAVA之Mysql基础', null, '成都', '2021-11-22 21:39:12', null, '2021-11-22 13:38:47', '2021-11-22 13:39:12', '0');
INSERT INTO `order_info` VALUES ('2', '1', '晴天', '15010546384', '23800.00', '0.00', '23800.00', '1', '20211123180908744', '尚硅谷大数据技术之Sqoop', null, '成都', '2021-11-23 18:09:32', null, '2021-11-23 10:09:08', '2021-11-23 10:09:32', '0');
INSERT INTO `order_info` VALUES ('3', '25', '晨', '13810168266', '1000.00', '0.00', '1000.00', '1', '20211123185451570', 'JAVA之Mysql基础', null, '', '2021-11-23 18:55:04', null, '2021-11-23 10:54:51', '2021-11-23 10:55:04', '0');
INSERT INTO `order_info` VALUES ('4', '1', '晴天', '15069352568', '22800.00', '500.00', '22300.00', '0', '20211123185726513', 'Java精品课程', null, '成都', null, null, '2021-11-23 10:57:26', '2021-11-23 10:57:26', '0');
INSERT INTO `order_info` VALUES ('5', '27', '******', '17512080612', '1000.00', '500.00', '500.00', '0', '20211123185752103', 'JAVA之Mysql基础', null, '', null, null, '2021-11-23 10:57:52', '2021-11-23 10:57:52', '0');
INSERT INTO `order_info` VALUES ('6', '1', '晴天', '13500009888', '19800.00', '0.00', '19800.00', '1', '20211126165606808', '尚硅谷大数据技术之HBase（2019新版）', null, '成都', '2021-11-26 16:56:25', null, '2021-11-26 08:56:07', '2022-07-12 09:56:12', '0');
INSERT INTO `order_info` VALUES ('7', '29', '晴天', '13500009888', '1000.00', '0.00', '1000.00', '1', '20211126183546799', 'JAVA之Mysql基础', null, '', '2021-11-26 18:36:14', null, '2021-11-26 10:35:46', '2022-07-12 09:56:19', '0');
INSERT INTO `order_info` VALUES ('8', '32', '晴天', '13500009888', '1000.00', '0.00', '1000.00', '0', '20211228152501327', 'JAVA之Mysql基础', null, '', null, null, '2021-12-28 15:25:01', '2022-07-12 09:56:20', '0');

-- ----------------------------
-- Table structure for payment_info
-- ----------------------------
DROP TABLE IF EXISTS `payment_info`;
CREATE TABLE `payment_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `out_trade_no` varchar(50) DEFAULT NULL COMMENT '对外业务编号',
  `order_id` bigint DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint DEFAULT NULL,
  `alipay_trade_no` varchar(50) DEFAULT NULL COMMENT '支付宝交易编号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `trade_body` varchar(200) DEFAULT NULL COMMENT '交易内容',
  `payment_type` varchar(20) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL COMMENT '支付状态',
  `callback_content` varchar(1000) DEFAULT NULL COMMENT '回调信息',
  `callback_time` datetime DEFAULT NULL COMMENT '回调时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_oid` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='支付信息表';

-- ----------------------------
-- Records of payment_info
-- ----------------------------
INSERT INTO `payment_info` VALUES ('1', '20211122213847830', '1', '1', null, '0.01', 'JAVA之Mysql基础', '2', '2', '{transaction_id=4200001236202111223264279278, nonce_str=60QD7Oevf9wU02zc, trade_state=SUCCESS, bank_type=OTHERS, openid=oQTXC56A4KDJuNRgj7hSoOqbxtDw, sign=1D685646F5D4D5BB4AEA9A3285A322B0, return_msg=OK, fee_type=CNY, mch_id=1481962542, cash_fee=1, out_trade_no=20211122213847830, cash_fee_type=CNY, appid=wxf913bfa3a2c7eeeb, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20211122213909, is_subscribe=Y, return_code=SUCCESS}', '2021-11-22 21:39:12', '2021-11-22 21:38:55', '2021-11-22 13:39:12', '0');
INSERT INTO `payment_info` VALUES ('2', '20211123180908744', '2', '1', null, '0.01', '尚硅谷大数据技术之Sqoop', '2', '2', '{transaction_id=4200001120202111230211882387, nonce_str=wn4XulMGtpBWmHvP, trade_state=SUCCESS, bank_type=OTHERS, openid=oQTXC56A4KDJuNRgj7hSoOqbxtDw, sign=0F505EC786ECC4C649578D8A71DE139C, return_msg=OK, fee_type=CNY, mch_id=1481962542, cash_fee=1, out_trade_no=20211123180908744, cash_fee_type=CNY, appid=wxf913bfa3a2c7eeeb, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20211123180927, is_subscribe=Y, return_code=SUCCESS}', '2021-11-23 18:09:32', '2021-11-23 18:09:15', '2021-11-23 10:09:32', '0');
INSERT INTO `payment_info` VALUES ('3', '20211123185451570', '3', '25', null, '0.01', 'JAVA之Mysql基础', '2', '2', '{transaction_id=4200001123202111233106099225, nonce_str=VNHOOhJVvGW8V0xo, trade_state=SUCCESS, bank_type=OTHERS, openid=oQTXC5zyE9p-gp7T_qUnFlv8VbB0, sign=CE0049BC9A1922CF70F423AC406BEB88, return_msg=OK, fee_type=CNY, mch_id=1481962542, cash_fee=1, out_trade_no=20211123185451570, cash_fee_type=CNY, appid=wxf913bfa3a2c7eeeb, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20211123185502, is_subscribe=N, return_code=SUCCESS}', '2021-11-23 18:55:04', '2021-11-23 18:54:53', '2021-11-23 10:55:04', '0');
INSERT INTO `payment_info` VALUES ('4', '20211123185726513', '4', '1', null, '0.01', 'Java精品课程', '2', '1', null, null, '2021-11-23 18:57:30', '2021-11-23 10:57:30', '0');
INSERT INTO `payment_info` VALUES ('5', '20211123185752103', '5', '27', null, '0.01', 'JAVA之Mysql基础', '2', '1', null, null, '2021-11-23 18:57:53', '2021-11-23 10:57:53', '0');
INSERT INTO `payment_info` VALUES ('6', '20211126165606808', '6', '1', null, '0.01', '尚硅谷大数据技术之HBase（2019新版）', '2', '2', '{transaction_id=4200001146202111266807786474, nonce_str=xTJGeq7F0Dz0c0U8, trade_state=SUCCESS, bank_type=OTHERS, openid=oQTXC56A4KDJuNRgj7hSoOqbxtDw, sign=201D5B989A83376A3AA34DFDAB457CFF, return_msg=OK, fee_type=CNY, mch_id=1481962542, cash_fee=1, out_trade_no=20211126165606808, cash_fee_type=CNY, appid=wxf913bfa3a2c7eeeb, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20211126165619, is_subscribe=Y, return_code=SUCCESS}', '2021-11-26 16:56:24', '2021-11-26 16:56:09', '2021-11-26 08:56:24', '0');
INSERT INTO `payment_info` VALUES ('7', '20211126183546799', '7', '29', null, '0.01', 'JAVA之Mysql基础', '2', '2', '{transaction_id=4200001228202111261700574727, nonce_str=pzhJ1kkbfSwFMslY, trade_state=SUCCESS, bank_type=OTHERS, openid=oQTXC51A-QwGey9bsMH0rwP6pj0g, sign=5B8528ED454C09E24F4297A97345FD87, return_msg=OK, fee_type=CNY, mch_id=1481962542, cash_fee=1, out_trade_no=20211126183546799, cash_fee_type=CNY, appid=wxf913bfa3a2c7eeeb, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20211126183611, is_subscribe=Y, return_code=SUCCESS}', '2021-11-26 18:36:14', '2021-11-26 18:35:48', '2021-11-26 10:36:13', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '所属上级',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(50) DEFAULT NULL COMMENT '名称code',
  `to_code` varchar(100) DEFAULT NULL COMMENT '跳转页面code',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '类型(1:菜单,2:按钮)',
  `status` tinyint DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '0', '全部数据', null, null, '1', null, '2021-05-31 18:05:37', '2021-09-27 13:37:59', '0');
INSERT INTO `permission` VALUES ('2', '1', '权限管理', 'Acl', null, '1', null, '2021-05-31 18:05:37', '2021-05-31 19:36:53', '0');
INSERT INTO `permission` VALUES ('3', '2', '用户管理', 'User', null, '1', null, '2021-05-31 18:05:37', '2021-05-31 19:36:58', '0');
INSERT INTO `permission` VALUES ('4', '2', '角色管理', 'Role', null, '1', null, '2021-05-31 18:05:37', '2021-05-31 19:37:02', '0');
INSERT INTO `permission` VALUES ('5', '2', '菜单管理', 'Permission', null, '1', null, '2021-05-31 18:05:37', '2021-05-31 19:37:05', '0');
INSERT INTO `permission` VALUES ('6', '3', '分配角色', 'btn.User.assgin', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:35:35', '0');
INSERT INTO `permission` VALUES ('7', '3', '添加', 'btn.User.add', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:34:29', '0');
INSERT INTO `permission` VALUES ('8', '3', '修改', 'btn.User.update', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:34:45', '0');
INSERT INTO `permission` VALUES ('9', '3', '删除', 'btn.User.remove', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:34:38', '0');
INSERT INTO `permission` VALUES ('10', '4', '修改', 'btn.Role.update', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:36:20', '0');
INSERT INTO `permission` VALUES ('11', '4', '分配权限', 'btn.Role.assgin', 'RoleAuth', '2', null, '2021-05-31 18:05:37', '2021-06-01 08:36:56', '0');
INSERT INTO `permission` VALUES ('12', '4', '添加', 'btn.Role.add', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:36:08', '0');
INSERT INTO `permission` VALUES ('13', '4', '删除', 'btn.Role.remove', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:36:32', '0');
INSERT INTO `permission` VALUES ('14', '4', '角色权限', 'role.acl', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:37:22', '1');
INSERT INTO `permission` VALUES ('15', '5', '查看', 'btn.permission.list', null, '2', null, '2021-05-31 18:05:37', '2021-05-31 19:32:52', '0');
INSERT INTO `permission` VALUES ('16', '5', '添加', 'btn.Permission.add', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:37:39', '0');
INSERT INTO `permission` VALUES ('17', '5', '修改', 'btn.Permission.update', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:37:47', '0');
INSERT INTO `permission` VALUES ('18', '5', '删除', 'btn.Permission.remove', null, '2', null, '2021-05-31 18:05:37', '2021-06-01 08:37:54', '0');
INSERT INTO `permission` VALUES ('19', '1', '订单管理', 'Order', null, '1', null, '2021-06-18 16:38:51', '2021-06-18 16:48:22', '0');
INSERT INTO `permission` VALUES ('20', '19', '订单列表', 'OrderInfo', '', '1', null, '2021-06-18 16:39:21', '2021-06-18 16:42:36', '0');
INSERT INTO `permission` VALUES ('23', '1', '点播管理', 'Vod', null, '1', null, '2021-06-18 16:45:55', '2021-11-19 11:39:47', '0');
INSERT INTO `permission` VALUES ('24', '23', '课程分类管理', 'Subject', null, '1', null, '2021-06-18 16:46:44', '2021-11-19 11:40:12', '0');
INSERT INTO `permission` VALUES ('25', '23', '讲师列表', 'TeacherList', '', '1', null, '2021-06-18 16:48:01', '2021-12-01 06:06:50', '0');
INSERT INTO `permission` VALUES ('26', '23', '课程列表', 'CourseList', '', '1', null, '2021-06-18 16:50:11', '2021-12-01 06:08:49', '0');
INSERT INTO `permission` VALUES ('27', '25', '添加讲师', '', 'TeacherCreate', '2', null, '2021-06-18 16:52:12', '2021-12-01 06:11:18', '0');
INSERT INTO `permission` VALUES ('28', '25', '编辑讲师', '', 'TeacherEdit', '2', null, '2021-06-18 16:53:04', '2021-12-01 06:09:34', '0');
INSERT INTO `permission` VALUES ('29', '26', '发布课程', '', 'CourseInfo', '2', null, '2021-06-18 16:53:22', '2021-12-01 06:10:13', '0');
INSERT INTO `permission` VALUES ('30', '27', '编辑课程', '', 'CourseInfoEdit', '2', null, '2021-06-18 16:54:34', '2021-12-01 06:10:35', '0');
INSERT INTO `permission` VALUES ('31', '27', '编辑大纲', null, 'CourseChapterEdit', '2', null, '2021-06-18 16:56:42', '2021-12-01 06:10:50', '0');
INSERT INTO `permission` VALUES ('32', '27', '课程统计', null, 'CourseChart', '2', null, '2021-06-18 16:56:57', '2021-12-01 06:11:01', '0');
INSERT INTO `permission` VALUES ('36', '1', '营销活动管理', 'Activity', null, '1', null, '2021-06-18 17:04:15', '2021-06-18 17:04:15', '0');
INSERT INTO `permission` VALUES ('40', '36', '优惠券列表', 'CouponInfo', null, '1', null, '2021-06-18 17:06:41', '2021-06-18 17:07:18', '0');
INSERT INTO `permission` VALUES ('41', '40', '添加', null, 'CouponInfoAdd', '2', null, '2021-06-18 17:06:57', '2021-06-18 17:07:22', '0');
INSERT INTO `permission` VALUES ('42', '40', '修改', null, 'CouponInfoEdit', '2', null, '2021-06-18 17:07:11', '2021-06-18 17:07:25', '0');
INSERT INTO `permission` VALUES ('43', '40', '详情', null, 'CouponInfoShow', '2', null, '2021-06-18 17:07:49', '2021-12-01 06:12:31', '0');
INSERT INTO `permission` VALUES ('45', '1', '直播管理', 'Live', null, '1', null, '2021-06-18 17:08:44', '2021-12-01 06:13:25', '0');
INSERT INTO `permission` VALUES ('46', '45', '直播列表', 'liveCourseList', '', '1', null, '2021-06-18 17:09:23', '2021-12-01 06:13:50', '0');
INSERT INTO `permission` VALUES ('47', '45', '直播配置', null, 'liveCourseConfig', '2', null, '2021-06-18 17:09:28', '2021-12-01 06:14:21', '0');
INSERT INTO `permission` VALUES ('48', '45', '观看记录', null, 'liveVisitor', '2', null, '2021-06-18 17:09:43', '2021-12-01 06:14:29', '0');
INSERT INTO `permission` VALUES ('49', '1', '菜单管理', 'Wechat', null, '1', null, '2021-06-18 17:15:44', '2021-12-01 06:14:50', '0');
INSERT INTO `permission` VALUES ('50', '49', '菜单列表', 'Menu', '', '1', null, '2021-06-18 17:16:02', '2021-12-01 06:14:57', '0');
INSERT INTO `permission` VALUES ('100', '1', '全部', 'btn.all', null, '2', null, '2021-09-27 13:35:24', '2021-09-27 13:40:09', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', 'SYSTEM', null, '2021-05-31 18:09:18', '2021-05-31 18:09:18', '0');
INSERT INTO `role` VALUES ('2', '平台管理员', null, null, '2021-06-01 08:38:40', '2021-06-18 17:13:17', '0');
INSERT INTO `role` VALUES ('3', '运营管理员', null, null, '2021-06-18 17:12:21', '2021-12-01 06:21:14', '0');
INSERT INTO `role` VALUES ('4', '游客', null, null, '2021-09-27 09:37:13', '2021-09-27 09:37:13', '0');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL DEFAULT '0',
  `permission_id` bigint NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='角色权限';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('3', '4', '1', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('4', '4', '2', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('5', '4', '3', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('6', '4', '6', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('7', '4', '7', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('8', '4', '8', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('9', '4', '9', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('10', '4', '4', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('11', '4', '10', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('12', '4', '11', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('13', '4', '12', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('14', '4', '13', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('15', '4', '5', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('16', '4', '15', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('17', '4', '16', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('18', '4', '17', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('19', '4', '18', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('20', '4', '19', '2021-12-01 06:25:27', '2021-12-01 06:25:27', '0');
INSERT INTO `role_permission` VALUES ('21', '4', '20', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('22', '4', '23', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('23', '4', '24', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('24', '4', '25', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('25', '4', '27', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('26', '4', '30', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('27', '4', '31', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('28', '4', '32', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('29', '4', '28', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('30', '4', '26', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('31', '4', '29', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('32', '4', '36', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('33', '4', '40', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('34', '4', '41', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('35', '4', '42', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('36', '4', '43', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('37', '4', '45', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('38', '4', '46', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('39', '4', '47', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('40', '4', '48', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('41', '4', '49', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');
INSERT INTO `role_permission` VALUES ('42', '4', '50', '2021-12-01 06:25:28', '2021-12-01 06:25:28', '0');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '主键',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程科目';

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '后端开发', '0', '1', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('2', 'Java', '1', '1', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('3', '前端开发', '0', '3', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('4', 'JavaScript', '3', '4', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('5', '云计算', '0', '5', '2019-09-29 15:47:25', '2022-07-11 01:07:40', '0');
INSERT INTO `subject` VALUES ('6', 'Docker', '5', '5', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('7', 'Linux', '5', '6', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('8', '系统/运维', '0', '7', '2019-09-29 15:47:25', '2022-07-11 01:07:38', '0');
INSERT INTO `subject` VALUES ('9', 'Linux', '8', '7', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('10', 'Windows', '8', '8', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('11', '数据库', '0', '9', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('12', 'MySQL', '11', '9', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('13', 'MongoDB', '11', '10', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('14', '大数据', '0', '11', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('15', 'Hadoop', '14', '11', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('16', 'Spark', '14', '12', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('17', '人工智能', '0', '13', '2019-09-29 15:47:25', '2022-07-11 01:07:35', '0');
INSERT INTO `subject` VALUES ('18', 'Python', '17', '13', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('19', '编程语言', '0', '14', '2019-09-29 15:47:25', '2022-07-11 01:07:37', '0');
INSERT INTO `subject` VALUES ('20', 'Java', '19', '14', '2019-09-29 15:47:25', '2019-09-29 15:47:25', '0');
INSERT INTO `subject` VALUES ('21', 'Python', '19', '2', '2019-09-30 16:19:22', '2019-09-30 16:19:22', '0');
INSERT INTO `subject` VALUES ('22', 'HTML/CSS', '19', '3', '2019-09-30 16:19:22', '2019-09-30 16:19:22', '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int unsigned NOT NULL COMMENT '头衔 0高级讲师 1首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `join_date` date DEFAULT NULL COMMENT '入驻时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除，1为删除，0为未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '张老师', '高级讲师', '高级讲师', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIB1WtJibSTqXvnJccFhOR1cSpVpdQ3BP5eTPCUO9CyI1feDefMoUFyA4E2C1oe2j8VMLrtAyBricvA/132', '0', '2021-11-02', '2021-11-05 03:18:36', '2021-11-08 01:50:46', '0');
INSERT INTO `teacher` VALUES ('2', '李老师', '高级讲师', '高级讲师', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIB1WtJibSTqXvnJccFhOR1cSpVpdQ3BP5eTPCUO9CyI1feDefMoUFyA4E2C1oe2j8VMLrtAyBricvA/132', '0', '2021-11-02', '2021-11-05 03:18:51', '2021-11-08 01:50:49', '0');
INSERT INTO `teacher` VALUES ('4', '钟老师', '首席讲师', '高级讲师', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIB1WtJibSTqXvnJccFhOR1cSpVpdQ3BP5eTPCUO9CyI1feDefMoUFyA4E2C1oe2j8VMLrtAyBricvA/132', '0', '2022-07-12', '2021-11-08 00:00:00', '2022-07-10 21:51:21', '0');
INSERT INTO `teacher` VALUES ('5', '钱老师', '钱老师', '钱老师', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo8uwBUP6f5JtibWlkmpPAVjsSsibMaFupwFRyo2Vr5Gkc33uctiasfOFgZADd5X1NYP82bKYjMDbFnA/132', '3', '2021-11-01', '2021-11-22 13:26:58', '2021-11-23 10:26:54', '0');
INSERT INTO `teacher` VALUES ('6', '宋老师', '宋老师', '宋老师', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo8uwBUP6f5JtibWlkmpPAVjsSsibMaFupwFRyo2Vr5Gkc33uctiasfOFgZADd5X1NYP82bKYjMDbFnA/132', '4', '2021-11-02', '2021-11-23 10:25:58', '2021-11-23 10:26:53', '0');
INSERT INTO `teacher` VALUES ('10', '东方老师', '魔教教主', '葵花宝典拥有者', '1', null, '0', '2022-06-29', '2022-07-10 21:52:05', '2022-07-10 21:52:05', '0');
INSERT INTO `teacher` VALUES ('11', '伍阔海', '神', '神中神', '1', null, '0', '2022-07-11', '2022-07-11 19:48:17', '2022-07-11 19:48:17', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `ware_id` bigint NOT NULL DEFAULT '0' COMMENT '仓库id（默认为：0为平台用户）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '96e79218965eb72c92a549dd5a330112', 'admin', null, '0', '2021-05-31 18:08:43', '2021-05-31 18:08:56', '0');
INSERT INTO `user` VALUES ('2', 'shangguigu', '96e79218965eb72c92a549dd5a330112', 'shangguigu', null, '0', '2021-06-01 08:46:22', '2021-12-01 06:20:29', '0');
INSERT INTO `user` VALUES ('3', 'chengdu', '96e79218965eb72c92a549dd5a330112', 'chengdu', null, '1', '2021-06-18 17:18:29', '2021-06-18 17:20:08', '0');
INSERT INTO `user` VALUES ('4', 'atguigu', 'e10adc3949ba59abbe56e057f20f883e', '游客', null, '0', '2021-09-27 09:37:39', '2021-09-27 09:39:14', '0');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `phone` varchar(200) DEFAULT NULL COMMENT '手机号',
  `password` varchar(200) DEFAULT NULL COMMENT '用户密码',
  `name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint DEFAULT NULL COMMENT '性别',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `province` varchar(30) DEFAULT NULL,
  `subscribe` tinyint NOT NULL DEFAULT '0' COMMENT '0：未订阅 1：已订阅',
  `open_id` varchar(45) DEFAULT NULL COMMENT '小程序open id',
  `union_id` varchar(45) DEFAULT NULL COMMENT '微信开放平台unionID',
  `recommend_id` bigint DEFAULT NULL COMMENT '推荐人用户id',
  `status` tinyint DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '15611248741', null, null, '晴天', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIB1WtJibSTqXvnJccFhOR1cSpVpdQ3BP5eTPCUO9CyI1feDefMoUFyA4E2C1oe2j8VMLrtAyBricvA/132', '成都', '0', 'oQTXC56A4KDJuNRgj7hSoOqbxtDw', null, null, null, '2021-10-21 07:19:29', '2021-11-23 11:32:44', '0');
INSERT INTO `user_info` VALUES ('24', '13562359685', null, null, '简', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/2GVkdw3J3kLruw37EYdW6RsFNUEL5mX5K3tgDolibM8hYICibPXpFIneMzyQpkFI0TsnE8R5ryUMvriaBmBNmNOsQ/132', '', '0', 'oQTXC51A-QwGey9bsMH0rwP6pj0g0', null, '1', null, '2021-11-23 10:14:00', '2021-11-26 10:31:55', '0');
INSERT INTO `user_info` VALUES ('25', '13810168266', null, null, '晨', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIB1WtJibSTqXsnvhFoEV7vpEMZkCfT0E9ib1TnUFHUYSppWy575onuuEDH8NRwU4aDj8PwXjQjY9OA/132', '', '0', 'oQTXC5zyE9p-gp7T_qUnFlv8VbB0', null, '1', null, '2021-11-23 10:49:00', '2021-11-26 08:45:40', '0');
INSERT INTO `user_info` VALUES ('26', '13716962779', null, null, '张晓飞', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/vByI6bJx9js2GLBicLYGXJKy5cnRq9ojCBNmk3Zesakia8eShdfwV6JLfIumJyEPtLerUlQDwcF6ng8OuugaKEjg/132', '', '0', 'oQTXC5xUafs2LDYATkXsXigZkE98', null, '1', null, '2021-11-23 10:49:03', '2021-11-26 08:45:40', '0');
INSERT INTO `user_info` VALUES ('27', '17512080612', null, null, '我是', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqhPquGcKzauUrwFxf82UfZVGbXApUU2vXhnQ7ZmSyHkGnPpUibahRs49vJcibTp1Co8iawppr0McL2A/132', '', '0', 'oQTXC5znK6fxptadSmzTwjNIPbKo', null, '1', null, '2021-11-23 10:50:02', '2021-11-26 08:45:41', '0');
INSERT INTO `user_info` VALUES ('28', '15901520518', null, null, '婷儿姐', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIVXAe2FkRhjYicibOVzMZrsEaObjmNMes9ru9ZNjx6WXt6aQSQsiccw25r2FzeGIqlUcYson4uQ8Bcw/132', '', '0', 'oQTXC51Qq1bxuVcpivsiW3xeC6Us', null, '1', null, '2021-11-23 10:52:48', '2021-11-26 08:45:42', '0');
INSERT INTO `user_info` VALUES ('29', '13500009888', null, null, '吧', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/2GVkdw3J3kLruw37EYdW6RsFNUEL5mX5K3tgDolibM8hYICibPXpFIneMzyQpkFI0TsnE8R5ryUMvriaBmBNmNOsQ/132', '', '0', 'oQTXC51A-QwGey9bsMH0rwP6pj0g', null, null, null, '2021-11-26 10:33:03', '2021-11-26 10:39:46', '0');
INSERT INTO `user_info` VALUES ('30', '13766816630', null, null, '环', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTInJ6LZx4So2d41ZWmv0z9hmM4yaE2dn2gvBeiasssw66NQCibuou4icHyMhjdu9ZQR6xiav2qswyNylA/132', '', '0', 'oQTXC5x67M6p0kvuP8aDUrz3WZPg', null, null, null, '2021-11-26 10:34:11', '2021-11-26 10:39:52', '0');
INSERT INTO `user_info` VALUES ('31', null, null, null, 'ya', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/fw0kHmJ1rqCwcibxTYUTBZ3KltT74MG7hnhCRd5EAazTDibckZ4gKR11iaVa1dM8BiccZXpnXv2rVnJLDltB7mCnrA/132', '', '0', 'oQTXC51_nDWnWRosSd-LxCfq_5l0', null, null, null, '2021-11-28 08:46:52', '2021-11-28 08:46:52', '0');
INSERT INTO `user_info` VALUES ('32', '15611248741', null, null, '', '0', '', '', '1', 'oQTXC56lAy3xMOCkKCImHtHoLLN4', null, null, null, '2021-12-28 12:29:17', '2021-12-28 12:29:17', '0');
INSERT INTO `user_info` VALUES ('33', '13521096172', null, null, 'testatguigu', '0', '', '', '0', 'oQTXC52GRKUUFk6WVH4yF22R3NlM', null, null, null, '2022-01-05 14:35:15', '2022-01-05 14:35:15', '0');

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `ip` varchar(64) DEFAULT NULL COMMENT '登录ip',
  `city` varchar(64) DEFAULT NULL COMMENT '登录城市',
  `type` tinyint(1) DEFAULT NULL COMMENT '登录类型【0-web，1-移动】',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登陆记录表';

-- ----------------------------
-- Records of user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2021-05-31 18:09:02', '2021-05-31 18:09:02', '0');
INSERT INTO `user_role` VALUES ('2', '2', '2', '2021-06-01 08:53:12', '2021-12-01 06:21:40', '1');
INSERT INTO `user_role` VALUES ('3', '3', '3', '2021-06-18 17:18:37', '2021-06-18 17:18:37', '0');
INSERT INTO `user_role` VALUES ('4', '4', '4', '2021-09-27 09:37:45', '2021-09-27 09:37:45', '0');
INSERT INTO `user_role` VALUES ('5', '4', '2', '2021-12-01 06:21:40', '2021-12-01 06:21:40', '0');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_id` bigint NOT NULL DEFAULT '0' COMMENT '章节ID',
  `title` varchar(50) NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `size` bigint unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程视频';

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '18', '16', '第一节', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:54', '0');
INSERT INTO `video` VALUES ('2', '14', '5', 'html基础', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:55', '0');
INSERT INTO `video` VALUES ('4', '18', '26', 'IO高级', '3b71d85d93554e7dbb59becdf823f63d', '视频', '1', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:56', '0');
INSERT INTO `video` VALUES ('5', '18', '16', 'IO流基础', '3b71d85d93554e7dbb59becdf823f63d', '视频.mp4', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:56', '0');
INSERT INTO `video` VALUES ('6', '2', '2', '第一课时', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:58', '0');
INSERT INTO `video` VALUES ('7', '2', '2', '第二课时', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:17:57', '0');
INSERT INTO `video` VALUES ('8', '2', '2', '第三课时', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:20', '0');
INSERT INTO `video` VALUES ('9', '2', '2', '第四课时', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:20', '0');
INSERT INTO `video` VALUES ('10', '2', '2', '第五课时', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '5', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:19', '0');
INSERT INTO `video` VALUES ('11', '3', '3', '01-尚硅谷-Flume（课程介绍）', '3b71d85d93554e7dbb59becdf823f63d', '01-尚硅谷-Flume（课程介绍）.avi', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:21', '0');
INSERT INTO `video` VALUES ('12', '3', '3', '02-尚硅谷-Flume（概念）', '3b71d85d93554e7dbb59becdf823f63d', '02-尚硅谷-Flume（概念）.avi', '1', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:21', '0');
INSERT INTO `video` VALUES ('13', '3', '3', '03-尚硅谷-Flume（概念）', '3b71d85d93554e7dbb59becdf823f63d', '03-尚硅谷-Flume（架构）.avi', '2', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:22', '0');
INSERT INTO `video` VALUES ('14', '4', '4', '01_尚硅谷_HBase_课程介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:23', '0');
INSERT INTO `video` VALUES ('15', '4', '4', '02_尚硅谷_HBase_介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:23', '0');
INSERT INTO `video` VALUES ('16', '4', '4', '03_尚硅谷_HBase_特点', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:24', '0');
INSERT INTO `video` VALUES ('17', '5', '7', '01_尚硅谷_Kafka_课程介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:16', '0');
INSERT INTO `video` VALUES ('18', '5', '7', '02_尚硅谷_Kafka_消息队列介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:15', '0');
INSERT INTO `video` VALUES ('19', '5', '7', '03_尚硅谷_Kafka_概念', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:14', '0');
INSERT INTO `video` VALUES ('20', '14', '6', 'CSS基础', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:14', '0');
INSERT INTO `video` VALUES ('21', '15', '8', 'spring mvc基础', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:13', '0');
INSERT INTO `video` VALUES ('22', '15', '8', 'spring mvc集成', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:13', '0');
INSERT INTO `video` VALUES ('23', '6', '9', '01_数仓项目_课程介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:12', '0');
INSERT INTO `video` VALUES ('24', '6', '9', '02_数仓项目_采集课程介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:12', '0');
INSERT INTO `video` VALUES ('25', '6', '9', '03_数仓项目_数仓概念_业务数据', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:11', '0');
INSERT INTO `video` VALUES ('26', '7', '10', '01_尚硅谷_Sqoop_课程介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:10', '0');
INSERT INTO `video` VALUES ('27', '7', '10', '02_尚硅谷_Sqoop_安装', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:10', '0');
INSERT INTO `video` VALUES ('28', '7', '10', '03_尚硅谷_Sqoop_原理', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:09', '0');
INSERT INTO `video` VALUES ('29', '8', '11', '01 - Scala - 语言介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:09', '0');
INSERT INTO `video` VALUES ('30', '8', '11', '02 - Scala - map集合', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:08', '0');
INSERT INTO `video` VALUES ('31', '1', '12', '01_Spark之课程体系介绍', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '1', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:08', '0');
INSERT INTO `video` VALUES ('32', '1', '12', '02_Spark之课程原理', '3b71d85d93554e7dbb59becdf823f63d', '', '0', '0', '0', '0', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:07', '0');
INSERT INTO `video` VALUES ('33', '18', '15', '第一节：Java简介', '3b71d85d93554e7dbb59becdf823f63d', '1', '1', '1000', '1', '100', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-01 05:45:14', '0');
INSERT INTO `video` VALUES ('34', '18', '15', '第二节：表达式和赋值语句', '3b71d85d93554e7dbb59becdf823f63d', '7 - How Do I Find Time for My ', '2', '999', '1', '100', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-03 10:18:06', '0');
INSERT INTO `video` VALUES ('35', '18', '15', '第三节：String类', '3b71d85d93554e7dbb59becdf823f63d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '3', '888', '0', '100', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-01 05:45:16', '0');
INSERT INTO `video` VALUES ('36', '18', '15', '第四节：程序风格', '3b71d85d93554e7dbb59becdf823f63d', '00-day10总结.avi', '4', '666', '0', '100', '0', '1', '0', '2021-11-05 03:06:04', '2021-12-01 05:45:17', '0');
INSERT INTO `video` VALUES ('37', '19', '65', 'mysql安装', '3b71d85d93554e7dbb59becdf823f63d', null, '0', '0', '1', '0', '0', '1', '0', '2021-11-22 11:12:11', '2021-12-03 10:18:02', '0');
INSERT INTO `video` VALUES ('38', '19', '65', 'mysql配置', '3b71d85d93554e7dbb59becdf823f63d', '3.msyql配置.avi', '1', '0', '1', '0', '0', '1', '0', '2021-11-22 11:15:52', '2021-12-03 10:18:03', '0');
INSERT INTO `video` VALUES ('39', '19', '66', 'mysql内部结构', '3b71d85d93554e7dbb59becdf823f63d', '4.mysql的内部结构.avi', '0', '0', '1', '0', '0', '1', '0', '2021-11-22 11:17:34', '2021-12-03 10:18:04', '0');
INSERT INTO `video` VALUES ('40', '19', '66', '认识索引', '3b71d85d93554e7dbb59becdf823f63d', '8.认识索引.avi', '1', '0', '0', '0', '0', '1', '0', '2021-11-22 11:18:45', '2021-12-03 10:18:05', '0');
INSERT INTO `video` VALUES ('41', '19', '66', '导入测试数据', 'a4b66c13cfaf4071bdb7ce988e7d7444', '13.导入50w条记录.avi', '0', '0', '0', '201.2', '0', '1', '0', '2021-12-16 16:15:59', '2021-12-16 16:15:59', '0');
INSERT INTO `video` VALUES ('42', '20', '68', '安装geek', null, null, '0', '0', '0', '0', '0', '1', '0', '2022-07-11 21:58:13', '2022-07-11 21:58:13', '0');

-- ----------------------------
-- Table structure for video_visitor
-- ----------------------------
DROP TABLE IF EXISTS `video_visitor`;
CREATE TABLE `video_visitor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint DEFAULT NULL COMMENT '课程id',
  `video_id` bigint NOT NULL DEFAULT '0' COMMENT '视频id',
  `user_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '来访者用户id',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `join_time` varchar(30) DEFAULT NULL COMMENT '进入时间',
  `leave_time` varchar(30) DEFAULT NULL COMMENT '离开的时间',
  `duration` bigint DEFAULT NULL COMMENT '用户停留的时间(单位：秒)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频来访者记录表';

-- ----------------------------
-- Records of video_visitor
-- ----------------------------
INSERT INTO `video_visitor` VALUES ('1', '19', '37', '1', '晴天', '2021-11-22 21:39:27.089', null, '4', '2021-11-22 13:39:27', '2021-11-22 13:39:27', '0');
INSERT INTO `video_visitor` VALUES ('2', '19', '38', '1', '晴天', '2021-11-20 21:39:32.071', '2021-11-22 21:44:24.026', '696', '2021-11-22 13:39:32', '2021-11-25 02:12:41', '0');
INSERT INTO `video_visitor` VALUES ('3', '7', '26', '1', '晴天', '2021-11-23 18:09:46.899', '2021-11-23 18:10:13.004', '5', '2021-11-23 10:09:47', '2021-11-23 10:09:47', '0');
INSERT INTO `video_visitor` VALUES ('4', '19', '38', '24', '简', '2021-11-23 18:14:53.542', null, '2', '2021-11-23 10:14:53', '2021-11-23 10:14:53', '0');
INSERT INTO `video_visitor` VALUES ('5', '19', '37', '27', '******', '2021-11-23 18:54:29.848', '2021-11-23 18:54:34.864', '3', '2021-11-23 10:54:29', '2021-11-23 10:54:29', '0');
INSERT INTO `video_visitor` VALUES ('6', '19', '37', '1', '晴天', '2021-11-21 18:55:12.082', '2021-11-23 18:56:16.536', '33', '2021-11-23 10:55:12', '2021-11-25 02:02:25', '0');
INSERT INTO `video_visitor` VALUES ('7', '19', '38', '1', '晴天', '2021-11-22 18:55:13.395', '2021-11-23 18:56:45.709', '706', '2021-11-23 10:55:13', '2021-11-25 02:02:31', '0');
INSERT INTO `video_visitor` VALUES ('8', '19', '37', '1', '晴天', '2021-11-23 18:56:13.368', '2021-11-23 18:58:18.665', '13', '2021-11-23 10:56:13', '2021-11-23 10:56:13', '0');
INSERT INTO `video_visitor` VALUES ('9', '19', '39', '1', '晴天', '2021-11-23 18:56:21.513', '2021-11-23 18:56:43.032', '313', '2021-11-23 10:56:21', '2021-11-25 02:02:12', '0');
INSERT INTO `video_visitor` VALUES ('10', '19', '40', '1', '晴天', '2021-11-21 18:56:32.061', '2021-11-23 18:56:37.113', '202', '2021-11-23 10:56:32', '2021-11-25 02:02:55', '0');
INSERT INTO `video_visitor` VALUES ('11', '19', '40', '1', '晴天', '2021-11-23 18:58:23.67', '2021-11-23 19:38:19.261', '114', '2021-11-23 10:58:23', '2021-11-23 10:58:23', '0');
INSERT INTO `video_visitor` VALUES ('12', '19', '40', '1', '晴天', '2021-11-24 10:11:44.531', '2021-11-24 10:11:44.531', '115', '2021-11-24 02:11:58', '2021-11-25 02:03:06', '0');
INSERT INTO `video_visitor` VALUES ('13', '19', '38', '1', '晴天', '2021-11-26 09:11:55.905', '2021-11-26 21:05:35.152', '943', '2021-11-26 01:12:12', '2021-11-26 01:37:30', '0');
INSERT INTO `video_visitor` VALUES ('14', '4', '15', '1', '晴天', '2021-11-26 17:07:25.349', '2021-11-26 17:07:45.149', '304', '2021-11-26 09:07:25', '2021-11-26 09:07:25', '0');
INSERT INTO `video_visitor` VALUES ('15', '8', '30', '1', '晴天', '2021-11-26 17:14:35.189', null, '2', '2021-11-26 09:14:35', '2021-11-26 09:14:35', '0');
INSERT INTO `video_visitor` VALUES ('16', '19', '37', '29', null, '2021-11-26 18:38:39.719', null, '3', '2021-11-26 10:38:39', '2021-11-26 10:38:39', '0');
INSERT INTO `video_visitor` VALUES ('17', '19', '38', '1', '晴天', '2021-11-27 08:02:41.382', null, '944', '2021-11-27 00:02:41', '2021-11-27 00:02:41', '0');
INSERT INTO `video_visitor` VALUES ('18', '18', '33', '1', '晴天', '2021-12-01 13:49:37.599', null, '11', '2021-12-01 05:49:36', '2021-12-01 05:49:36', '0');
INSERT INTO `video_visitor` VALUES ('19', '18', '33', '1', '晴天', '2021-12-01 13:49:32.6', null, '6', '2021-12-01 05:49:36', '2021-12-01 05:49:36', '0');
INSERT INTO `video_visitor` VALUES ('20', '18', '33', '1', '晴天', '2021-12-01 13:49:27.665', null, '1', '2021-12-01 05:49:37', '2021-12-01 05:49:37', '0');
INSERT INTO `video_visitor` VALUES ('21', '18', '34', '1', '晴天', '2021-12-01 13:52:02.601', '2021-12-01 13:52:14.295', '13', '2021-12-01 05:52:02', '2021-12-01 05:52:02', '0');
INSERT INTO `video_visitor` VALUES ('22', '19', '38', '1', '晴天', '2021-12-16 09:26:31.258', null, '1', '2021-12-16 09:26:31', '2021-12-16 09:26:31', '0');
INSERT INTO `video_visitor` VALUES ('23', '19', '37', '27', '我是', '2021-12-28 11:42:31.06', null, '95', '2021-12-28 11:42:31', '2021-12-28 11:42:31', '0');
