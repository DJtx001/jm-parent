
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id, 主键',
  `channel` tinyint unsigned NOT NULL COMMENT '渠道来源, 1:线上活动, 2:推广介绍',
  `name` varchar(20) NOT NULL COMMENT '活动名称',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `description` varchar(100) NOT NULL COMMENT '活动简介',
  `type` tinyint unsigned NOT NULL COMMENT '活动类型, 1:课程折扣, 2:代金券',
  `discount` double(3,1) DEFAULT NULL COMMENT '课程折扣',
  `voucher` int unsigned DEFAULT NULL COMMENT '代金券金额（元）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '线索id, 主键',
  `name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `gender` tinyint unsigned DEFAULT NULL COMMENT '性别，1:男, 2:女',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `wechat` varchar(30) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(15) DEFAULT NULL COMMENT 'qq号',
  `subject` tinyint unsigned DEFAULT NULL COMMENT '意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维',
  `course_id` int unsigned DEFAULT NULL COMMENT '意向课程, 课程id',
  `degree` tinyint unsigned DEFAULT NULL COMMENT '学历, 1:高中、2:中专、3:大专、4:本科、5:硕士、6:博士、7:其他',
  `job_status` tinyint unsigned DEFAULT NULL COMMENT '在职情况, 1: 在职, 0: 离职',
  `channel` tinyint unsigned NOT NULL COMMENT '渠道来源，1:线上活动, 2:推广介绍',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '商机状态，1:待分配, 2:待跟进, 3:跟进中, 4:回收, 5:转客户',
  `user_id` int unsigned DEFAULT NULL COMMENT '归属人id，关联用户id',
  `clue_id` int unsigned DEFAULT NULL COMMENT '归属线索id',
  `next_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商机表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `business_track_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_track_record` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '跟进记录id, 主键',
  `business_id` int unsigned NOT NULL COMMENT '商机id，关联商机id',
  `user_id` int unsigned NOT NULL COMMENT '跟进人id，关联用户id',
  `track_status` tinyint unsigned NOT NULL COMMENT '跟进状态, 1:接通, 2:拒绝, 3:无人接听',
  `key_items` varchar(50) DEFAULT NULL COMMENT '沟通重点',
  `next_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `record` varchar(50) DEFAULT NULL COMMENT '沟通纪要',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商机跟进记录表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `clue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clue` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '线索ID, 主键',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `channel` tinyint unsigned NOT NULL COMMENT '渠道来源，1:线上活动, 2:推广介绍',
  `activity_id` int unsigned DEFAULT NULL COMMENT '活动信息，关联活动的ID',
  `name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `gender` tinyint unsigned DEFAULT NULL COMMENT '性别，1:男, 2:女',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `user_id` int unsigned DEFAULT NULL COMMENT '归属人ID，关联用户ID',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '线索状态，1:待分配, 2:待跟进, 3:跟进中, 4:伪线索, 5:转为商机',
  `subject` tinyint unsigned DEFAULT NULL COMMENT '意向学科，1:AI智能应用开发(Java), 2:AI大模型开发(Python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维',
  `level` tinyint unsigned DEFAULT NULL COMMENT '意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油',
  `next_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='线索表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `clue_track_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clue_track_record` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '跟进记录ID, 主键',
  `clue_id` int unsigned NOT NULL COMMENT '线索ID，关联线索ID',
  `user_id` int unsigned NOT NULL COMMENT '跟进人ID，关联用户ID',
  `subject` tinyint unsigned DEFAULT NULL COMMENT '意向学科，1:AI智能应用开发(Java), 2:AI大模型开发(Python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维',
  `level` tinyint unsigned DEFAULT NULL COMMENT '意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油',
  `record` varchar(100) DEFAULT NULL COMMENT '跟进记录',
  `next_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `type` tinyint unsigned DEFAULT NULL COMMENT '跟进类型, 1:正常跟进、0:伪线索',
  `false_reason` tinyint unsigned DEFAULT NULL COMMENT '伪线索原因, 1:空号、2:停机、3:竞品、4:无法联系、5:其他',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='线索跟进记录表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程id, 主键,自增',
  `subject` tinyint unsigned NOT NULL COMMENT '学科选择',
  `name` varchar(20) NOT NULL COMMENT '课程名称',
  `price` int unsigned NOT NULL COMMENT '课程价格（元）',
  `target` varchar(20) NOT NULL COMMENT '适用人群',
  `description` varchar(100) DEFAULT NULL COMMENT '课程介绍',
  `create_time` datetime NOT NULL COMMENT '操作类型',
  `update_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '客户id, 主键',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `channel` tinyint unsigned NOT NULL COMMENT '渠道来源，1:线上活动, 2:推广介绍',
  `name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `gender` tinyint unsigned DEFAULT NULL COMMENT '性别，1:男, 2:女',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `wechat` varchar(30) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(15) DEFAULT NULL COMMENT 'qq号',
  `degree` tinyint unsigned DEFAULT NULL COMMENT '学历, 1:高中、2:中专、3:大专、4:本科、5:硕士、6:博士、7:其他',
  `job_status` tinyint unsigned DEFAULT NULL COMMENT '在职情况, 1: 在职, 0: 离职',
  `subject` tinyint unsigned DEFAULT NULL COMMENT '意向学科，1:AI智能应用开发(java), 2:AI大模型开发(python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维',
  `course_id` int unsigned DEFAULT NULL COMMENT '意向课程, 课程id',
  `business_id` int unsigned DEFAULT NULL COMMENT '来源商机id, 关联商机id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键，默认自增',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '部门状态，1为正常，0为异常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '部门状态更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operate_user_id` int unsigned DEFAULT NULL COMMENT '操作用户ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '操作的类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(1000) DEFAULT NULL COMMENT '方法参数',
  `return_value` varchar(2000) DEFAULT NULL COMMENT '返回值',
  `cost_time` bigint DEFAULT NULL COMMENT '方法执行耗时, 单位:ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id, 主键',
  `name` varchar(10) NOT NULL COMMENT '角色名称',
  `label` varchar(20) NOT NULL COMMENT '角色标识',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id, 主键',
  `username` varchar(20) NOT NULL COMMENT '用户名，唯一',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `phone` char(11) NOT NULL COMMENT '手机号，唯一',
  `email` varchar(50) NOT NULL COMMENT '邮箱，唯一',
  `gender` tinyint unsigned NOT NULL COMMENT '性别，1: 男，2: 女',
  `status` tinyint unsigned NOT NULL COMMENT '状态，1: 正常，0: 停用',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门id，关联部门表主键',
  `role_id` int unsigned DEFAULT NULL COMMENT '角色id，关联角色表主键',
  `image` varchar(255) DEFAULT NULL COMMENT '头像url',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注，50字以内',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

