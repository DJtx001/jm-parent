-- MySQL dump 10.13  Distrib 8.4.5, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cpjm-parent
-- ------------------------------------------------------
-- Server version	8.4.5

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


create database if not exists `cpjm-parent`; 

use `cpjm-parent`;

--
-- Table structure for table `activity`
--

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

--
-- Dumping data for table `activity`
--

/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` (`id`, `channel`, `name`, `start_time`, `end_time`, `description`, `type`, `discount`, `voucher`, `create_time`, `update_time`) VALUES (1,1,'618-AI训练营折扣','2025-05-31 16:00:00','2025-06-30 15:59:59','618-AI训练营折扣',1,8.5,NULL,'2025-05-14 17:49:58','2025-05-14 17:49:58'),(2,2,'B站推广介绍','2025-05-19 16:00:00','2025-06-05 15:59:59','B站推广介绍-全新课程升级',2,NULL,300,'2025-05-15 10:51:23','2025-05-15 10:51:23'),(3,1,'测试活动1','2025-06-01 00:00:00','2025-06-30 23:59:59','描述1',1,7.5,200,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(4,2,'测试活动2','2025-07-01 00:00:00','2025-07-31 23:59:59','描述2',2,NULL,250,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(5,1,'测试活动3','2025-08-01 00:00:00','2025-08-31 23:59:59','描述3',1,9.0,NULL,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(6,2,'测试活动4','2025-09-01 00:00:00','2025-09-30 23:59:59','描述4',2,6.5,150,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(7,1,'测试活动5','2025-10-01 00:00:00','2025-10-31 23:59:59','描述5',1,8.0,300,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(8,2,'测试活动6','2025-11-01 00:00:00','2025-11-30 23:59:59','描述6',2,NULL,100,'2025-05-15 11:09:04','2025-05-15 11:09:04'),(12,1,'暑期超值优惠活动','2025-06-24 16:00:00','2025-07-25 15:59:59','暑期超值优惠活动, 主要是针对于暑期大学生',2,NULL,800,'2025-06-22 14:47:34','2025-07-20 17:58:18'),(13,1,'1024程序员节','2025-10-09 16:00:00','2025-10-24 15:59:59','1024程序员节66666',2,NULL,800,'2025-07-30 20:41:43','2025-08-02 15:02:55'),(14,1,'666','2025-08-01 16:00:00','2025-09-01 16:00:00','666666',1,9.0,NULL,'2025-08-02 15:45:18','2025-08-02 15:45:18');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;

--
-- Table structure for table `business`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商机表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` (`id`, `name`, `phone`, `gender`, `age`, `wechat`, `qq`, `subject`, `course_id`, `degree`, `job_status`, `channel`, `remark`, `status`, `user_id`, `clue_id`, `next_time`, `create_time`, `update_time`) VALUES (1,'小五','17713492901',1,22,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',3,8,NULL,'2025-03-20 10:10:10','2025-06-10 21:04:18','2025-06-21 17:41:48'),(2,'小六子','17723692901',1,22,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',1,NULL,NULL,NULL,'2025-06-10 21:04:18','2025-06-21 17:41:48'),(3,'伍六一','17763292901',1,22,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',1,NULL,NULL,NULL,'2025-06-10 21:04:18','2025-06-21 17:41:48'),(4,'张思','17738992901',2,19,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',1,NULL,NULL,NULL,'2025-06-10 21:04:18','2025-06-21 17:41:48'),(5,'齐白','17751092901',1,22,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',1,NULL,NULL,NULL,'2025-06-10 21:04:18','2025-06-21 17:41:48'),(6,'奚梦','17709092901',1,22,'jsdhfsf2324','3456789432',1,1,4,2,1,'无',1,NULL,NULL,NULL,'2025-06-10 21:04:18','2025-06-21 17:41:48'),(7,'钱四','13688889991',1,21,'wxdjjd92922','2345643236',2,9,4,2,1,'B站关注的小伙伴儿',1,NULL,4,NULL,'2025-06-10 21:15:03','2025-06-21 18:16:35'),(8,'王原大','13390901245',1,23,'wx237847834','234567865',1,1,4,2,1,'',1,NULL,NULL,NULL,'2025-06-21 17:26:34','2025-06-21 22:07:00'),(9,'林婉儿','15816382891',2,20,'linwaner112','1234567543',1,17,4,1,2,'学习RAG相关技术，转型AI',1,NULL,NULL,NULL,'2025-06-21 17:27:41','2025-06-21 17:31:35'),(10,'张三','13800138001',1,25,'zhangsan123','123456789',1,1,4,1,1,'对Java课程感兴趣',1,NULL,NULL,NULL,'2025-03-25 09:15:22','2025-06-21 17:44:48'),(11,'李四','13800138002',2,22,'lisi456','987654321',2,2,3,0,2,'前端开发方向',1,NULL,NULL,NULL,'2025-03-24 14:30:45','2025-06-21 20:19:59'),(12,'王五','13800138003',1,28,'wangwu789','456123789',3,3,4,1,1,'咨询AI课程',3,8,NULL,'2025-08-20 00:00:00','2025-03-26 10:15:00','2025-08-06 21:11:32'),(13,'赵六','13800138004',2,24,'zhaoliu666','789456123',4,4,4,1,1,'大数据分析方向',1,NULL,NULL,NULL,'2025-03-25 16:45:12','2025-03-25 16:45:12'),(14,'钱七','13800138005',1,26,'qianqi777','321654987',2,2,3,1,2,'Python爬虫需求',1,NULL,NULL,NULL,'2025-03-23 11:20:33','2025-06-21 21:27:25'),(15,'孙八','13800138006',2,23,'sunba888','654987321',6,6,2,1,1,'软件测试方向',1,NULL,NULL,NULL,'2025-03-24 09:45:18','2025-06-21 18:14:29'),(16,'周九','13800138007',1,27,'zhoujiu999','987321654',7,7,4,1,2,'新媒体运营',1,NULL,NULL,NULL,'2025-03-22 13:30:45','2025-06-21 17:44:56'),(17,'吴十','13800138008',2,21,'wushi1010','123789456',8,8,4,0,1,'产品经理课程',1,NULL,NULL,NULL,'2025-03-26 11:05:33','2025-03-26 11:05:33'),(18,'郑十一','13800138009',1,29,'zhengsy11','456789123',9,9,3,1,2,'UI设计学习',1,NULL,NULL,NULL,'2025-03-21 15:20:10','2025-06-21 18:07:42'),(19,'王芳','13800138010',2,25,'wangfang12','789123456',1,1,4,1,1,'Java进阶课程',1,NULL,NULL,NULL,'2025-03-20 10:10:10','2025-06-21 18:06:45'),(20,'李明','13800138011',1,24,'liming123','321456789',2,2,3,0,1,'前端框架学习',1,NULL,NULL,NULL,'2025-03-19 14:25:40','2025-06-21 17:45:07'),(21,'张伟','13800138012',1,26,'zhangwei456','654321987',1,4,4,1,2,'机器学习方向',3,8,NULL,'2025-08-14 00:00:00','2025-03-18 16:30:15','2025-08-06 21:11:15'),(22,'刘洋','13800138013',2,23,'liuyang789','987654123',4,4,4,1,1,'数据分析师方向',1,NULL,NULL,NULL,'2025-03-25 13:20:45','2025-03-25 13:20:45'),(23,'陈晨','13800138014',1,27,'chenchen14','123456987',1,4,3,2,2,'Python自动化',1,NULL,NULL,NULL,'2025-03-17 11:15:30','2025-06-22 15:14:02'),(24,'杨光','13800138015',2,22,'yangguang15','456987321',6,6,2,1,1,'软件测试入门',1,NULL,NULL,NULL,'2025-03-16 09:40:25','2025-06-22 14:55:38'),(25,'周杰','13800138016',1,28,'zhoujie16','789321654',1,18,4,2,2,'短视频运营',3,8,NULL,'2025-08-10 00:00:00','2025-03-15 14:50:10','2025-08-06 21:10:52'),(26,'吴倩','13800138017',2,24,'wuqian17','321789456',8,8,4,0,1,'产品设计咨询',1,NULL,NULL,NULL,'2025-03-24 15:10:20','2025-03-24 15:10:20'),(27,'郑凯','13800138018',1,26,'zhengkai18','654123987',9,9,3,1,2,'UI交互设计',1,NULL,NULL,NULL,'2025-03-14 10:30:45','2025-06-22 14:55:45'),(28,'王磊','13800138019',1,25,'wanglei19','987123654',1,2,4,1,1,'Java架构师',3,8,NULL,'2025-08-15 00:00:00','2025-03-13 13:20:15','2025-08-03 21:53:11'),(29,'李娜','13800138020',2,23,'lina20','123987456',2,1,3,0,2,'前端全栈开发',4,8,NULL,'2025-08-11 00:00:00','2025-03-12 16:40:30','2025-08-06 21:14:05'),(30,'承娟','13909018929',27,19,'cj2839232323','2595964758',1,1,4,2,2,'无',1,NULL,NULL,NULL,'2025-06-21 20:07:27','2025-06-21 20:17:22'),(31,'齐欧式','13511110000',1,30,'wxqi299232','2435676543',2,NULL,NULL,NULL,2,NULL,1,NULL,NULL,NULL,'2025-06-22 14:52:08','2025-06-22 14:55:28'),(32,'张岱','13398980102',1,22,'wx13728785434','234567543',1,NULL,1,1,1,NULL,4,8,NULL,NULL,'2025-07-20 20:59:46','2025-08-04 14:58:34'),(33,'刘大','15509091211',1,20,'wx238434546','234567543',1,2,1,1,1,'无',2,8,NULL,NULL,'2025-08-02 22:09:10','2025-08-03 18:27:46'),(34,'王大友','13666669999',1,25,'wx287389493','2435675432',1,2,1,1,1,'在职的同学',5,8,14,'2025-08-10 00:00:00','2025-08-05 09:17:36','2025-08-06 21:14:41'),(35,'11111','13809091211',1,11,'wx839849343','87654322345',2,NULL,NULL,NULL,1,NULL,2,8,17,NULL,'2025-08-05 09:18:34','2025-08-06 21:10:01');
/*!40000 ALTER TABLE `business` ENABLE KEYS */;

--
-- Table structure for table `business_track_record`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商机跟进记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_track_record`
--

/*!40000 ALTER TABLE `business_track_record` DISABLE KEYS */;
INSERT INTO `business_track_record` (`id`, `business_id`, `user_id`, `track_status`, `key_items`, `next_time`, `record`, `create_time`) VALUES (1,1,8,1,'[课程, 时间]','2025-06-23 10:00:00','了解了课程及上课时间','2025-03-20 10:10:10'),(2,1,8,1,'[课程, 薪资, 师资, 位置]','2025-06-25 10:00:00','了解了就业薪资','2025-03-20 10:10:10'),(3,28,8,1,'[课程, 价格, 位置]','2025-08-12 00:00:00','11111','2025-08-03 18:29:33'),(4,28,8,2,'[课程, 价格, 位置]','2025-08-13 00:00:00','6666','2025-08-03 18:29:45'),(5,28,8,1,'[课程, 价格, 位置, 时间, 师资]','2025-08-15 00:00:00','了解个方向的信息','2025-08-03 21:53:11'),(6,25,8,1,'[课程, 价格, 位置]','2025-08-10 00:00:00','了解了课程','2025-08-06 21:10:52'),(7,21,8,1,'[价格, 师资, 项目]','2025-08-14 00:00:00','了解了项目','2025-08-06 21:11:15'),(8,12,8,1,'[时间, 师资, 项目]','2025-08-20 00:00:00','沟通了上课的时间安排','2025-08-06 21:11:32'),(9,29,8,1,'[价格, 位置]','2025-08-11 00:00:00','了解了课程价格','2025-08-06 21:11:54'),(10,34,8,1,'[课程, 价格]','2025-08-10 00:00:00','想近期学习的','2025-08-06 21:14:37');
/*!40000 ALTER TABLE `business_track_record` ENABLE KEYS */;

--
-- Table structure for table `clue`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='线索表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clue`
--

/*!40000 ALTER TABLE `clue` DISABLE KEYS */;
INSERT INTO `clue` (`id`, `phone`, `channel`, `activity_id`, `name`, `gender`, `age`, `wechat`, `qq`, `user_id`, `status`, `subject`, `level`, `next_time`, `create_time`, `update_time`) VALUES (1,'14700000001',2,3,'张思',1,23,'wx2324342323','3434343423',2,3,NULL,NULL,'2025-06-25 10:00:00','2025-05-24 17:13:50','2025-06-21 15:11:34'),(2,'13589890912',1,2,'李久阶',1,22,'wx28392839','2323232323',2,2,NULL,NULL,NULL,'2025-05-24 17:16:03','2025-08-06 21:06:28'),(3,'13909120913',2,3,'李迪',1,22,'wx17327323','2456754323',2,3,1,2,'2025-08-09 00:00:00','2025-05-28 11:54:02','2025-08-06 21:08:52'),(4,'13688889991',1,2,'钱四',1,21,'wxdjjd92922','2345643236',NULL,1,1,NULL,NULL,'2025-05-28 14:16:55','2025-06-10 21:15:03'),(5,'15509091231',1,4,'欧斯卡',2,22,'wx283232423','23456789657',NULL,1,NULL,NULL,NULL,'2025-05-28 19:41:46','2025-05-29 09:48:05'),(6,'17709092901',1,10,'奚梦',1,22,'jsdhfsf2324','3456789432',NULL,1,1,NULL,NULL,'2025-05-28 19:44:15','2025-06-10 21:04:18'),(7,'13589898881',2,11,'伊斯科',1,19,'wx8943895345','34343232536',NULL,1,2,NULL,NULL,'2025-05-28 19:45:06','2025-06-09 14:42:38'),(8,'13511110000',2,13,'齐欧式',1,30,'wxqi299232','2435676543',NULL,1,2,NULL,NULL,'2025-05-29 10:45:07','2025-06-22 14:52:08'),(9,'13511110001',1,2,'李秋菊',2,22,'liqiuju23874','234567865',NULL,1,1,NULL,NULL,'2025-05-29 10:47:22','2025-06-22 14:51:37'),(10,'13398980102',1,2,'张岱',1,22,'wx13728785434','234567543',2,3,2,3,'2025-08-10 00:00:00','2025-06-16 15:31:22','2025-08-06 21:09:12'),(11,'15508761231',1,1,'卫丹',2,24,'wxweidan1212','8450313640',2,3,1,3,'2025-08-14 00:00:00','2025-07-16 18:46:10','2025-08-06 21:07:30'),(12,'17792098192',2,3,'张吉',1,21,'wx289483544','245676856',NULL,1,NULL,NULL,NULL,'2025-07-20 15:17:56','2025-07-20 15:43:56'),(13,'15508761288',1,1,'卫丹66',2,24,'wxweidan1212','8450313640',2,3,7,3,'2025-08-10 00:00:00','2025-08-02 16:58:18','2025-08-06 21:09:36'),(14,'13666669999',1,1,'王大友',1,25,'wx287389493','2435675432',5,5,1,2,'2025-08-10 16:00:00','2025-08-02 17:00:27','2025-08-05 09:17:36'),(15,'13808088080',1,1,'李武',1,19,'','',2,2,NULL,NULL,NULL,'2025-08-02 18:05:35','2025-08-06 21:06:31'),(16,'13890909100',1,1,'2222',1,21,'wx2378289347','23565434',5,4,1,2,'2025-08-06 11:55:02','2025-08-02 18:06:26','2025-08-04 14:52:47'),(17,'13809091211',1,1,'11111',1,11,'wx839849343','87654322345',5,5,2,3,'2025-08-05 00:00:00','2025-08-02 18:10:36','2025-08-05 09:17:59'),(18,'15508761289',1,1,'卫丹66',2,24,'wxweidan1215','8450313620',5,4,2,3,'2025-08-04 00:00:00','2025-08-02 18:12:57','2025-08-04 21:38:37'),(19,'13609091111',1,1,'1111111',1,11,'','',2,2,NULL,NULL,NULL,'2025-08-05 17:29:58','2025-08-06 21:06:25');
/*!40000 ALTER TABLE `clue` ENABLE KEYS */;

--
-- Table structure for table `clue_track_record`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='线索跟进记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clue_track_record`
--

/*!40000 ALTER TABLE `clue_track_record` DISABLE KEYS */;
INSERT INTO `clue_track_record` (`id`, `clue_id`, `user_id`, `subject`, `level`, `record`, `next_time`, `type`, `false_reason`, `create_time`) VALUES (1,1,2,1,1,'无','2025-05-29 10:00:00',1,NULL,'2025-05-29 10:25:00'),(2,1,2,1,1,'有意向,目前大三','2025-05-30 10:00:00',1,NULL,'2025-05-29 10:25:00'),(3,14,1,1,3,'了解了一下java方向的就业情况','2025-08-05 16:00:00',1,NULL,'2025-08-03 11:21:10'),(4,14,1,1,2,'考虑具体的校区','2025-08-10 16:00:00',1,NULL,'2025-08-03 11:22:39'),(5,16,1,1,3,'无','2025-08-03 11:55:02',1,NULL,'2025-08-03 12:25:06'),(6,17,1,2,3,'了解了Python','2025-08-05 00:00:00',1,NULL,'2025-08-03 14:36:23'),(7,16,1,1,3,'111','2025-08-03 11:55:02',1,NULL,'2025-08-03 17:39:18'),(8,16,5,1,2,'66666','2025-08-06 11:55:02',1,NULL,'2025-08-03 18:15:50'),(9,16,5,1,2,'8888','2025-08-06 11:55:02',1,NULL,'2025-08-03 18:16:38'),(10,16,5,NULL,NULL,'不想学了',NULL,0,5,'2025-08-04 14:52:47'),(11,18,5,NULL,NULL,'其他原因',NULL,0,5,'2025-08-04 21:38:37'),(12,11,2,1,3,'了解了下java的前景','2025-08-09 00:00:00',1,NULL,'2025-08-06 21:07:10'),(13,11,2,1,3,'了解了java的薪资情况','2025-08-14 00:00:00',1,NULL,'2025-08-06 21:07:30'),(14,3,2,1,2,'考虑学习java','2025-08-09 00:00:00',1,NULL,'2025-08-06 21:08:52'),(15,10,2,2,3,'考虑学习Python大模型开发','2025-08-10 00:00:00',1,NULL,'2025-08-06 21:09:12'),(16,13,2,7,3,'了解了一下运维的岗位','2025-08-10 00:00:00',1,NULL,'2025-08-06 21:09:36');
/*!40000 ALTER TABLE `clue_track_record` ENABLE KEYS */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '课程id, 主键',
  `subject` tinyint unsigned NOT NULL COMMENT '课程学科，1:AI智能应用开发(Java), 2:AI大模型开发(Python)，3:AI鸿蒙开发，4:AI大数据，5:AI嵌入式，6:AI测试，7:AI运维',
  `name` varchar(20) NOT NULL COMMENT '课程名称',
  `price` int unsigned NOT NULL COMMENT '课程价格（元）',
  `target` tinyint unsigned NOT NULL COMMENT '适用人群, 1:小白学员, 2:中级程序员',
  `description` varchar(100) DEFAULT NULL COMMENT '课程介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`id`, `subject`, `name`, `price`, `target`, `description`, `create_time`, `update_time`) VALUES (1,2,'Python核心与AI开发基础',299,1,'主要讲解Python核心与AI开发基础666','2025-05-09 21:30:00','2025-08-02 11:31:01'),(2,1,'AI驱动Web开发',299,2,'AI驱动Web开发， 主要讲解Web开发的核心知识及Web项目的设计、开发、测试、部署','2025-05-22 11:01:40','2025-08-02 11:31:01'),(3,1,'企业级物联网项目',299,2,'企业级物联网项目，主要讲解物联网项目的设计、开发、测试、部署全流程交付','2025-05-22 11:02:44','2025-08-02 11:31:01'),(4,1,'SpringAI大模型应用开发',2287,3,'SpringAI大模型应用开发','2025-05-22 11:08:26','2025-05-22 11:08:26'),(5,2,'数据分析',2198,2,'基于Python数据分析的','2025-05-22 21:44:49','2025-05-22 21:45:50'),(6,3,'前端基础',226,1,'前端基础，主要讲解HTML、CSS、JS等前端开发基础知识','2025-05-23 20:07:47','2025-08-02 09:49:47'),(7,2,'LangChain入门',1800,2,'LangChain入门，学习该框架如何操作AI大模型','2025-05-26 21:43:57','2025-08-02 09:49:45'),(8,1,'LangChain4j',299,2,'LangChain4j从入门到进阶, 适合AI初学者','2025-06-19 19:03:17','2025-06-19 19:03:17'),(9,1,'RAG增强检索',399,2,'RAG','2025-06-19 19:04:24','2025-08-02 09:51:45'),(10,1,'SpringCloud微服务框架',1200,2,'SpringCloud微服务框架及分布式解决方案','2025-06-22 14:41:16','2025-06-22 14:41:16'),(11,1,'微服务智能项目集',2899,2,'微服务智能项目集','2025-06-22 14:44:06','2025-08-01 14:48:04'),(12,1,'AI智能体项目-天机AI助理',4999,2,'AI智能体项目-天机AI助理','2025-06-22 14:44:31','2025-06-22 14:44:31'),(13,1,'Tool Calling实战',199,2,'Tool Calling实战讲解','2025-07-16 11:38:33','2025-07-16 11:38:33'),(14,1,'星辰WMS',289,1,'星辰WMS','2025-08-01 14:48:49','2025-08-01 14:48:49'),(16,4,'Hadoop',299,2,'Hadoop入门到高级','2025-08-01 14:58:44','2025-08-01 14:58:44'),(18,1,'Function calling进阶',599,3,'Function calling进阶','2025-08-06 10:35:09','2025-08-06 10:35:09');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id, 主键',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `name` varchar(20) NOT NULL COMMENT '客户姓名',
  `channel` tinyint unsigned DEFAULT NULL COMMENT '渠道来源，1:线上活动, 2:推广介绍',
  `gender` tinyint unsigned DEFAULT NULL COMMENT '性别，1:男, 2:女',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `degree` tinyint unsigned DEFAULT NULL COMMENT '学历, 1:高中、2:中专、3:大专、4:本科、5:硕士、6:博士、7:其他',
  `job_status` tinyint unsigned DEFAULT NULL COMMENT '在职情况, 1: 在职, 0: 离职',
  `subject` tinyint unsigned DEFAULT NULL COMMENT '意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维',
  `course_id` int unsigned DEFAULT NULL COMMENT '课程id',
  `business_id` int unsigned DEFAULT NULL COMMENT '商机id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `phone`, `name`, `channel`, `gender`, `age`, `wechat`, `qq`, `degree`, `job_status`, `subject`, `course_id`, `business_id`, `create_time`, `update_time`) VALUES (1,'13688889991','钱四',1,1,21,'wxdjjd92922','2345643236',4,2,2,9,7,'2025-06-21 18:16:35','2025-06-21 18:16:35'),(2,'13800138002','李四',2,2,22,'lisi456','987654321',3,0,2,2,11,'2025-06-21 20:19:59','2025-06-21 20:19:59'),(3,'13800138005','钱七',2,1,26,'qianqi777','321654987',3,1,2,2,14,'2025-06-21 21:27:25','2025-06-21 21:27:25'),(4,'13800138010','王芳',1,2,25,'wangfang12','789123456',4,1,1,1,19,'2025-06-21 18:06:45','2025-06-21 18:31:20'),(5,'15501101213','邓光明',1,1,22,'wx28392483','4532456',4,2,1,17,NULL,'2025-06-21 18:17:50','2025-06-21 18:31:25'),(6,'13567210012','库明',1,1,22,'kujiaming1122','3353439142',4,2,1,1,NULL,'2025-06-21 21:55:50','2025-06-21 22:01:21'),(7,'13800138012','张伟',2,1,26,'zhangwei456','654321987',4,1,1,4,21,'2025-06-22 15:18:04','2025-06-22 15:18:04'),(8,'13666669999','王大友',1,1,25,'wx287389493','2435675432',1,1,1,2,34,'2025-08-06 21:14:41','2025-08-06 21:14:41');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id，主键',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name`, `status`, `create_time`, `update_time`) VALUES (1,'研发部',1,'2025-04-26 15:45:31','2025-07-26 15:27:40'),(2,'市场部',1,'2025-04-26 15:45:31','2025-07-26 15:27:40'),(3,'销售一部',1,'2025-04-26 15:45:31','2025-07-26 15:27:46'),(4,'人力资源部',1,'2025-04-26 15:45:31','2025-07-26 15:27:46'),(5,'财务部',1,'2025-04-26 15:45:31','2025-07-26 15:28:04'),(6,'客服部',0,'2025-04-26 15:45:31','2025-07-26 15:28:04'),(7,'技术支持部',1,'2025-04-26 15:45:31','2025-07-26 15:28:08'),(8,'产品部',1,'2025-04-26 15:45:31','2025-07-26 15:28:08'),(9,'运营部',0,'2025-04-26 15:45:31','2025-08-06 21:36:43'),(11,'法务部',0,'2025-04-26 15:45:31','2025-08-06 21:36:40'),(12,'设计部',1,'2025-04-26 15:45:31','2025-07-26 15:28:18'),(13,'公关部',1,'2025-04-26 15:45:31','2025-08-06 11:50:27'),(15,'战略部',1,'2025-04-26 15:45:31','2025-07-27 18:16:40'),(16,'市场一部',1,'2025-07-09 11:32:42','2025-07-26 15:28:25'),(17,'服务中心',1,'2025-07-27 09:56:01','2025-07-27 09:56:01'),(20,'教研一部',1,'2025-07-27 10:39:43','2025-07-27 10:39:43'),(21,'教研二部',1,'2025-07-27 10:40:24','2025-07-27 10:40:24'),(44,'战略投资部',1,'2025-08-06 17:49:27','2025-08-06 21:35:18'),(47,'公关部66',1,'2025-08-07 18:20:12','2025-08-07 19:43:28');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

--
-- Table structure for table `operate_log`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` (`id`, `operate_user_id`, `operate_time`, `class_name`, `method_name`, `method_params`, `return_value`, `cost_time`) VALUES (1,8,'2025-08-06 17:49:27','com.qk.controller.DeptController','add','[Dept(id=null, name=战略投资部, status=1, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',21),(2,8,'2025-08-06 17:49:31','com.qk.controller.DeptController','update','[Dept(id=43, name=666777, status=1, createTime=2025-08-06T12:31:05, updateTime=2025-08-06T12:31:05)]','Result(code=1, msg=success, data=null)',16),(3,8,'2025-08-06 17:49:35','com.qk.controller.DeptController','update','[Dept(id=43, name=666777, status=0, createTime=2025-08-06T12:31:05, updateTime=2025-08-06T17:49:31)]','Result(code=1, msg=success, data=null)',14),(4,8,'2025-08-06 17:49:37','com.qk.controller.DeptController','deleteById','[43]','Result(code=1, msg=success, data=null)',12),(5,8,'2025-08-06 17:49:45','com.qk.controller.RoleController','addRole','[Role(id=null, name=测试666, label=666, remark=6666, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',9),(6,8,'2025-08-06 17:49:49','com.qk.controller.RoleController','updateRole','[Role(id=8, name=测试666777, label=666777, remark=6666777, createTime=2025-08-06T17:49:45, updateTime=2025-08-06T17:49:45)]','Result(code=1, msg=success, data=null)',13),(7,8,'2025-08-06 17:49:51','com.qk.controller.RoleController','deleteRole','[8]','Result(code=1, msg=success, data=null)',21),(8,8,'2025-08-06 17:49:58','com.qk.controller.UserController','updateUser','[User(id=12, username=huaqian, password=1007f13423a57bc0390d9510f96d0322, name=花千朵, phone=13800000010, email=huaqian@example.com, gender=0, status=1, deptId=13, roleId=2, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/63137d09-0734-4910-9502-b9b8d063b132.png, remark=Remark 10, createTime=2025-05-14T15:49:31, updateTime=2025-08-05T20:33:01, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',18),(9,2,'2025-08-06 21:35:18','com.qk.controller.DeptController','update','[Dept(id=44, name=战略投资部, status=1, createTime=2025-08-06T17:49:27, updateTime=2025-08-06T17:49:27)]','Result(code=1, msg=success, data=null)',16),(10,2,'2025-08-06 21:35:30','com.qk.controller.RoleController','addRole','[Role(id=null, name=测试角色, label=test, remark=测试角色, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',19),(11,2,'2025-08-06 21:35:37','com.qk.controller.RoleController','updateRole','[Role(id=9, name=测试角色, label=role_test, remark=测试角色, createTime=2025-08-06T21:35:30, updateTime=2025-08-06T21:35:30)]','Result(code=1, msg=success, data=null)',16),(12,2,'2025-08-06 21:35:39','com.qk.controller.RoleController','updateRole','[Role(id=9, name=测试角色, label=role_test, remark=测试角色, createTime=2025-08-06T21:35:30, updateTime=2025-08-06T21:35:37)]','Result(code=1, msg=success, data=null)',9),(13,2,'2025-08-06 21:35:40','com.qk.controller.RoleController','updateRole','[Role(id=9, name=测试角色, label=role_test, remark=测试角色, createTime=2025-08-06T21:35:30, updateTime=2025-08-06T21:35:39)]','Result(code=1, msg=success, data=null)',10),(14,2,'2025-08-06 21:35:41','com.qk.controller.RoleController','updateRole','[Role(id=9, name=测试角色, label=role_test, remark=测试角色, createTime=2025-08-06T21:35:30, updateTime=2025-08-06T21:35:40)]','Result(code=1, msg=success, data=null)',10),(15,2,'2025-08-06 21:36:02','com.qk.controller.UserController','updateUser','[User(id=7, username=gongsunsheng, password=ee3a229f2f3d2bd7c9dd3eeb4eda02b3, name=公孙胜, phone=13800000004, email=gongsunsheng@example.com, gender=1, status=1, deptId=5, roleId=3, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/284efce8-0b0c-4076-bbf6-3e104446ce70.png, remark=Remark 4, createTime=2025-05-14T15:49:31, updateTime=2025-07-14T18:13:45, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',10),(16,2,'2025-08-06 21:36:10','com.qk.controller.UserController','updateUser','[User(id=6, username=wuyong, password=d74bcf7b7f805922e4ad42315f3a8cde, name=吴用, phone=13800000003, email=wuyong@example.com, gender=1, status=0, deptId=7, roleId=3, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/cb074a33-4f30-4b3e-b9f1-bf8bf9d0f4ff.png, remark=Remark 3, createTime=2025-05-14T15:49:31, updateTime=2025-07-14T18:13:45, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',10),(17,2,'2025-08-06 21:36:14','com.qk.controller.UserController','updateUser','[User(id=6, username=wuyong, password=d74bcf7b7f805922e4ad42315f3a8cde, name=吴用, phone=13800000003, email=wuyong@example.com, gender=1, status=1, deptId=7, roleId=3, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/cb074a33-4f30-4b3e-b9f1-bf8bf9d0f4ff.png, remark=Remark 3, createTime=2025-05-14T15:49:31, updateTime=2025-08-06T21:36:10, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',17),(18,2,'2025-08-06 21:36:23','com.qk.controller.RoleController','updateRole','[Role(id=4, name=普通用户, label=common_user, remark=普通公司员工, createTime=2025-06-21T15:25:30, updateTime=2025-08-06T11:52:54)]','Result(code=1, msg=success, data=null)',9),(19,2,'2025-08-06 21:36:28','com.qk.controller.DeptController','update','[Dept(id=11, name=法务部, status=1, createTime=2025-04-26T15:45:31, updateTime=2025-07-26T15:28:18)]','Result(code=1, msg=success, data=null)',15),(20,2,'2025-08-06 21:36:31','com.qk.controller.DeptController','update','[Dept(id=9, name=运营部, status=1, createTime=2025-04-26T15:45:31, updateTime=2025-07-26T15:28:13)]','Result(code=1, msg=success, data=null)',9),(21,2,'2025-08-06 21:36:40','com.qk.controller.DeptController','update','[Dept(id=11, name=法务部, status=0, createTime=2025-04-26T15:45:31, updateTime=2025-08-06T21:36:28)]','Result(code=1, msg=success, data=null)',17),(22,2,'2025-08-06 21:36:43','com.qk.controller.DeptController','update','[Dept(id=9, name=运营部, status=0, createTime=2025-04-26T15:45:31, updateTime=2025-08-06T21:36:31)]','Result(code=1, msg=success, data=null)',9),(23,NULL,'2025-08-07 18:20:12','com.qk.controller.DeptController','add','[Dept(id=null, name=公关部666, status=1, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',13),(24,NULL,'2025-08-07 18:30:14','com.qk.controller.DeptController','update','[Dept(id=47, name=公关部66, status=1, createTime=2025-08-07T18:20:12, updateTime=2025-08-07T18:20:12)]','Result(code=1, msg=success, data=null)',13),(25,NULL,'2025-08-07 18:34:56','com.qk.controller.DeptController','add','[Dept(id=null, name=222, status=1, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',18),(26,NULL,'2025-08-07 18:35:01','com.qk.controller.DeptController','deleteById','[48]','Result(code=1, msg=success, data=null)',22),(27,NULL,'2025-08-07 19:43:28','com.qk.controller.DeptController','update','[Dept(id=47, name=公关部66, status=1, createTime=2025-08-07T18:20:12, updateTime=2025-08-07T18:30:14)]','Result(code=1, msg=success, data=null)',19),(28,NULL,'2025-08-07 19:44:05','com.qk.controller.DeptController','add','[Dept(id=null, name=666, status=1, createTime=null, updateTime=null)]','Result(code=1, msg=success, data=null)',12),(29,NULL,'2025-08-07 19:44:16','com.qk.controller.DeptController','update','[Dept(id=49, name=666666, status=1, createTime=2025-08-07T19:44:05, updateTime=2025-08-07T19:44:05)]','Result(code=1, msg=success, data=null)',10),(30,NULL,'2025-08-07 19:44:19','com.qk.controller.DeptController','update','[Dept(id=49, name=666666, status=0, createTime=2025-08-07T19:44:05, updateTime=2025-08-07T19:44:16)]','Result(code=1, msg=success, data=null)',18),(31,NULL,'2025-08-07 19:44:22','com.qk.controller.DeptController','update','[Dept(id=49, name=666666, status=1, createTime=2025-08-07T19:44:05, updateTime=2025-08-07T19:44:19)]','Result(code=1, msg=success, data=null)',10),(32,NULL,'2025-08-07 19:44:24','com.qk.controller.DeptController','deleteById','[49]','Result(code=1, msg=success, data=null)',16),(33,NULL,'2025-08-10 12:42:20','com.qk.controller.UserController','add','[User(id=null, username=linghu, password=linghu123, name=令狐, phone=17799990001, email=17799990001@163.com, gender=1, status=1, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/e7d7b18d-209a-4fd4-aacf-1394422a5df5.png, remark=令狐兄弟刚入职, createTime=null, updateTime=null, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',29),(34,NULL,'2025-08-10 12:42:39','com.qk.controller.UserController','updateUser','[User(id=21, username=linghu, password=f53737bf004999637930b5aa548d255b, name=令狐, phone=17799990002, email=17799990002@163.com, gender=1, status=1, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/e7d7b18d-209a-4fd4-aacf-1394422a5df5.png, remark=令狐兄弟刚入职，普通员工, createTime=2025-08-10T12:42:20, updateTime=2025-08-10T12:42:20, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',17),(35,NULL,'2025-08-10 12:44:36','com.qk.controller.UserController','add','[User(id=null, username=tianboguang, password=tianboguang123, name=田伯光, phone=17799990001, email=17799990001@163.com, gender=1, status=0, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/37297a7d-7c2e-4bbe-9faa-fd4c83ea3ce2.png, remark=田伯光刚入职的普通员工, createTime=null, updateTime=null, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',14),(36,NULL,'2025-08-10 12:44:40','com.qk.controller.UserController','updateUser','[User(id=22, username=tianboguang, password=7dbe4ba5bba47d22b5aba7a97e22c069, name=田伯光, phone=17799990001, email=17799990001@163.com, gender=1, status=1, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/37297a7d-7c2e-4bbe-9faa-fd4c83ea3ce2.png, remark=田伯光刚入职的普通员工, createTime=2025-08-10T12:44:36, updateTime=2025-08-10T12:44:36, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',11),(37,NULL,'2025-08-10 12:44:45','com.qk.controller.UserController','deleteByIds','[[22]]','Result(code=1, msg=success, data=null)',15),(38,NULL,'2025-08-10 12:45:18','com.qk.controller.UserController','add','[User(id=null, username=1111, password=1111123, name=11, phone=18708928292, email=18708928292@itcast.cn, gender=1, status=0, deptId=1, roleId=4, image=, remark=, createTime=null, updateTime=null, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',11),(39,NULL,'2025-08-10 12:45:23','com.qk.controller.UserController','deleteByIds','[[23, 21]]','Result(code=1, msg=success, data=null)',14),(40,1,'2025-08-10 16:20:47','com.qk.controller.UserController','add','[User(id=null, username=linghu, password=null, name=令狐公子, phone=17798980909, email=17798980909@qq.com, gender=1, status=1, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/96ab397c-2730-448c-bc5d-0528b79d75a4.png, remark=令狐公子刚入职的, createTime=null, updateTime=null, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',33),(41,1,'2025-08-10 16:22:56','com.qk.controller.UserController','add','[User(id=null, username=tianboguang, password=null, name=田伯光, phone=13309091091, email=13309091091@163.com, gender=1, status=1, deptId=1, roleId=4, image=https://web2025-test.oss-cn-beijing.aliyuncs.com/406dce01-61cf-4efa-983f-a1790c526095.png, remark=田伯光-13309091091, createTime=null, updateTime=null, deptName=null, roleName=null, roleLabel=null)]','Result(code=1, msg=success, data=null)',13);
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;

--
-- Table structure for table `role`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `label`, `remark`, `create_time`, `update_time`) VALUES (1,'管理员','admin','管理员, 用于管理整个系统数据','2025-05-09 19:47:28','2025-05-09 19:47:28'),(2,'线索专员','clue_operator','线索专员','2025-05-09 19:48:00','2025-05-09 19:48:00'),(3,'商机专员','business_operator','商机专员','2025-05-09 20:03:04','2025-05-09 20:03:04'),(4,'普通用户','common_user','普通公司员工','2025-06-21 15:25:30','2025-08-06 21:36:23'),(9,'测试角色','role_test','测试角色','2025-08-06 21:35:30','2025-08-06 21:35:41');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Table structure for table `user`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `name`, `phone`, `email`, `gender`, `status`, `dept_id`, `role_id`, `image`, `remark`, `create_time`, `update_time`) VALUES (1,'zhangsan','4e7bdb88640b376ac6646b8f1ecfb558','张三','13800138001','admin@example.com',1,1,15,1,'https://web2025-test.oss-cn-beijing.aliyuncs.com/ee089310-479b-4672-a74a-dc680fa6a18a.png','系统管理员','2025-05-12 11:39:03','2025-06-21 15:25:55'),(2,'lisi','c3cb6d12c40908943b64bc0681af47db','李四','13800138002','editor1@example.com',1,1,4,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/62fcb582-074c-489a-8ea8-721bc8bba042.png','内容编辑','2025-05-12 11:39:03','2025-06-21 15:26:00'),(3,'sunwuji','368a7fccd730d807f41e2161798e13ca','孙无忌','18809091111','sunwuji@163.com',1,1,5,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/e65781f3-c87d-46e2-9d53-cf4329ce64e6.png','孙无忌刚入职，先在销售部磨炼磨炼','2025-05-12 16:46:21','2025-07-14 18:13:45'),(4,'songjiang','a91b1cfe31e6b534537ab992e06380f8','宋江','13800000001','songjiang@example.com',1,1,5,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/0bdd90db-17e1-407c-b922-27d987c9cc45.png','测试一下备注信息','2025-05-14 15:49:31','2025-07-14 18:13:45'),(5,'lujunyi','ef73dd11149e096116f755a171eb3fec','卢俊义','13800000002','lujunyi@example.com',1,1,3,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/1564f4d9-7d07-4ea4-9e23-ecebe39d3520.png','玉麒麟卢俊义','2025-05-14 15:49:31','2025-08-03 08:59:16'),(6,'wuyong','d74bcf7b7f805922e4ad42315f3a8cde','吴用','13800000003','wuyong@example.com',1,1,7,3,'https://web2025-test.oss-cn-beijing.aliyuncs.com/cb074a33-4f30-4b3e-b9f1-bf8bf9d0f4ff.png','Remark 3','2025-05-14 15:49:31','2025-08-06 21:36:14'),(7,'gongsunsheng','ee3a229f2f3d2bd7c9dd3eeb4eda02b3','公孙胜','13800000004','gongsunsheng@example.com',1,1,5,3,'https://web2025-test.oss-cn-beijing.aliyuncs.com/284efce8-0b0c-4076-bbf6-3e104446ce70.png','Remark 4','2025-05-14 15:49:31','2025-08-06 21:36:02'),(8,'linchong','786271307576f1ee762341173a2caa48','林冲','13800000006','linchong@example.com',1,1,4,3,'https://web2025-test.oss-cn-beijing.aliyuncs.com/d2fbc991-c6de-4871-bd91-4a7d186b1f90.png','Remark 6','2025-05-14 15:49:31','2025-08-05 08:36:19'),(9,'qinming','23b9c4ea1f4d1844acd20a5314da8553','秦明','13800000007','qinming@example.com',1,1,5,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/d87de2cc-79a4-4a56-af9c-eb6aca52db66.png','Remark 7','2025-05-14 15:49:31','2025-07-14 18:13:45'),(10,'huarong','da751f7a71fd84bb168f70d01aa81a0e','花荣','13800000008','huayong@example.com',1,1,15,1,'https://web2025-test.oss-cn-beijing.aliyuncs.com/4775c1a5-4944-4aec-9753-9fa622dbbe75.png','Remark 8','2025-05-14 15:49:31','2025-07-14 18:13:45'),(11,'huyan','fbb39f3019cc4f5444dbd6b623f71a1a','呼延灼','13800000009','huyan@example.com',1,0,8,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/446aa6db-85b0-451f-bc11-92a52a518192.png','Remark 9','2025-05-14 15:49:31','2025-07-14 18:13:45'),(12,'huaqian','1007f13423a57bc0390d9510f96d0322','花千朵','13800000010','huaqian@example.com',0,1,13,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/63137d09-0734-4910-9502-b9b8d063b132.png','Remark 10','2025-05-14 15:49:31','2025-08-06 17:49:58'),(13,'shaqianmo','29d6fb85ac49d6d82b9b6dfd5f62939a','杀阡陌','13509091456','shaqianmo@163.com',0,1,5,2,'https://web2025-test.oss-cn-beijing.aliyuncs.com/ff8646a3-629b-4124-a2cb-f0365f9075c2.png','','2025-06-19 17:48:39','2025-07-14 18:13:45'),(14,'baizihua','f1659bd8a56022d817d6bd124c6181e8','白子画','13609091206','baizihua@163.com',1,1,3,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/421c0945-6f25-405c-b439-9b8b997338aa.png','系统研发','2025-06-22 14:33:49','2025-08-05 20:32:44'),(15,'huaqiangu','be6485aab12634baf922b3dbe407fcda','花千骨','15809092819','huaqiangu@163.com',0,1,6,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/735320dc-ac08-4b73-bdd7-7f0cf054274f.png','花千骨是一个普通用户','2025-06-24 10:40:20','2025-08-05 20:32:40'),(16,'kuangyetian','5e3c47c0d266439d7ce704972c92086a','旷野天','13689281921','kuangyetian@163.com',1,1,9,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/5d1f8e0f-2227-4469-8c90-7a02867fbe70.png','新入职的技术员旷野天','2025-06-27 19:38:34','2025-08-05 20:32:37'),(24,'linghu','f53737bf004999637930b5aa548d255b','令狐公子','17798980909','17798980909@qq.com',1,1,1,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/96ab397c-2730-448c-bc5d-0528b79d75a4.png','令狐公子刚入职的','2025-08-10 16:20:47','2025-08-10 16:20:47'),(25,'tianboguang','7dbe4ba5bba47d22b5aba7a97e22c069','田伯光','13309091091','13309091091@163.com',1,1,1,4,'https://web2025-test.oss-cn-beijing.aliyuncs.com/406dce01-61cf-4efa-983f-a1790c526095.png','田伯光-13309091091','2025-08-10 16:22:56','2025-08-10 16:22:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-10 19:07:08
