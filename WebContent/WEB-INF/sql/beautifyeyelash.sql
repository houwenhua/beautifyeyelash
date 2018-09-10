/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.16-log : Database - beautifyeyelash
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`beautifyeyelash` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `beautifyeyelash`;

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(20) NOT NULL COMMENT '商品名',
  `price` double NOT NULL COMMENT '价格',
  `number` int(11) NOT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

/*Table structure for table `consume` */

DROP TABLE IF EXISTS `consume`;

CREATE TABLE `consume` (
  `id` int(11) NOT NULL,
  `Integral` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `money` decimal(19,2) NOT NULL,
  `serviceid` int(11) DEFAULT NULL,
  `vipid` int(11) DEFAULT NULL,
  `waiterid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnfivrefr9msfc70yf4viwjme3` (`waiterid`),
  KEY `FKrn9jf3ac3bxlpvp19coib6fte` (`serviceid`),
  KEY `FK6aa1vmw9krd81i5ww2r0y65jx` (`vipid`),
  CONSTRAINT `FK6aa1vmw9krd81i5ww2r0y65jx` FOREIGN KEY (`vipid`) REFERENCES `vip` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKnfivrefr9msfc70yf4viwjme3` FOREIGN KEY (`waiterid`) REFERENCES `waiter` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKrn9jf3ac3bxlpvp19coib6fte` FOREIGN KEY (`serviceid`) REFERENCES `service` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consume` */

insert  into `consume`(`id`,`Integral`,`date`,`money`,`serviceid`,`vipid`,`waiterid`) values (25,'4500','2018-03-29 11:07:51','4500.00',4,7,2),(27,'600','2018-03-30 14:51:08','600.00',5,29,2),(37,'900','2018-03-27 11:15:55','900.00',1,32,1),(38,'800','2018-03-27 11:16:16','800.00',1,31,1),(41,'300','2018-03-27 11:41:55','300.00',1,21,1),(44,'600','2018-03-27 15:36:24','600.00',4,10,2),(47,'600','2018-03-29 12:55:53','600.00',5,7,2),(48,'600','2018-03-29 12:57:03','600.00',13,22,1),(50,'66','2018-03-29 15:24:59','66.00',1,8,1),(51,'33','2018-03-29 15:29:33','99.00',1,7,1),(52,'900','2018-03-29 15:30:53','900.00',1,8,1),(53,'99','2018-03-29 15:41:24','99.00',1,10,1),(55,'600','2018-03-30 10:59:40','600.00',5,10,1),(56,'600','2018-03-30 11:02:19','600.00',2,24,1),(58,'600','2018-03-30 14:06:55','600.00',1,22,1),(59,'600','2018-03-30 14:37:29','600.00',4,10,2),(60,'600','2018-03-30 14:08:24','600.00',1,24,1),(61,'600','2018-03-30 14:09:39','600.00',1,21,1),(62,'300','2018-03-30 14:10:50','300.00',1,25,1),(63,'300','2018-03-30 14:12:34','300.00',1,7,1),(66,'600','2018-03-30 14:28:34','600.00',1,29,1),(67,'600','2018-03-30 14:35:24','600.00',1,8,1),(69,'600','2018-03-30 15:14:25','600.00',1,8,1),(72,'600','2018-03-30 15:20:47','600.00',1,22,1),(73,'300','2018-03-30 15:23:33','300.00',1,5,1),(74,'600','2018-03-30 15:24:17','600.00',1,8,1),(75,'600','2018-03-30 15:27:32','600.00',1,8,1),(76,'600','2018-03-30 15:28:53','600.00',1,22,1),(77,'900','2018-03-30 15:31:35','900.00',1,10,1),(78,'600','2018-03-30 15:36:37','600.00',1,8,1),(79,'600','2018-03-30 15:43:45','600.00',1,7,1),(80,'900','2018-03-30 15:44:50','900.00',1,10,1),(81,'900','2018-03-30 16:08:16','900.00',1,10,1),(83,'300','2018-03-30 16:09:55','300.00',1,5,1),(84,'600','2018-03-30 16:14:06','600.00',1,10,1),(85,'333','2018-03-30 16:16:33','333.00',5,21,1),(86,'600','2018-03-30 16:16:51','600.00',1,7,1),(87,'77','2018-03-30 16:17:09','77.00',1,10,1),(88,'44','2018-03-30 16:17:48','44.00',1,31,1),(89,'600','2018-03-30 16:18:36','600.00',1,8,1),(90,'66','2018-03-30 16:18:56','66.00',1,24,1),(91,'600','2018-03-30 16:21:33','600.00',1,30,1),(92,'600','2018-03-30 17:37:18','600.00',1,10,1),(93,'600','2018-03-30 17:40:15','600.00',1,8,1),(94,'600','2018-03-30 17:43:30','600.00',1,25,1),(111,'600','2018-04-06 10:17:46','600.00',1,43,4),(113,'999','2018-04-08 14:24:04','999.00',1,21,1),(114,'888','2018-04-08 14:24:46','888.00',15,7,1),(116,'8888','2018-04-08 14:53:17','8888.00',14,21,1),(117,'800','2018-04-08 16:30:26','800.00',13,21,1),(254,'600','2018-05-11 10:00:22','600.00',1,7,1),(260,'800','2018-05-12 00:08:58','800.00',1,25,1),(262,'600','2018-05-12 00:09:30','600.00',2,7,2),(266,'800','2018-05-12 11:28:13','800.00',4,10,2),(273,'200','2018-05-12 12:20:29','200.00',1,7,4),(275,'200','2018-05-12 12:31:57','200.00',1,7,1),(277,'200','2018-05-16 19:34:56','200.00',13,21,4),(280,'3000','2018-05-12 12:54:34','3000.00',1,10,6),(319,'200','2018-05-12 23:21:19','200.00',1,7,8),(325,'1000','2018-04-01 15:58:04','1000.00',1,5,6),(328,'3000','2018-05-14 12:26:47','3000.00',1,5,NULL),(331,'600','2018-05-14 13:21:23','600.00',1,10,NULL),(334,'900','2018-05-14 22:16:08','900.00',1,21,NULL),(342,'600','2018-05-14 22:30:25','600.00',2,21,1),(346,'900','2018-05-14 23:25:42','900.00',1,NULL,1);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `ginprice` decimal(19,2) NOT NULL,
  `goutprivce` decimal(19,2) NOT NULL,
  `name` varchar(50) NOT NULL,
  `productiondate` varchar(255) DEFAULT NULL,
  `productiontotal` bigint(20) NOT NULL,
  `storagetime` varchar(50) NOT NULL,
  `supplier` varchar(50) DEFAULT NULL,
  `purchaseid` int(11) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `goodsclassid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6lly0jmo1fcsvnsiu1xsajblg` (`purchaseid`),
  KEY `FKgs9eglkvelmwhrs2ip833i0na` (`goodsclassid`),
  CONSTRAINT `FK6lly0jmo1fcsvnsiu1xsajblg` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`id`),
  CONSTRAINT `FKgs9eglkvelmwhrs2ip833i0na` FOREIGN KEY (`goodsclassid`) REFERENCES `goodsclass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`ginprice`,`goutprivce`,`name`,`productiondate`,`productiontotal`,`storagetime`,`supplier`,`purchaseid`,`brand`,`goodsclassid`) values (350,'14.00','25.00','面膜','2018-05-08',30,'2018-04-09 21:34:15','阿里',121,'美宝莲',NULL),(354,'50.00','80.00','美白粉','2018-05-07',40,'2018-04-09 21:34:15','阿里',121,'美宝莲',NULL),(356,'25.00','80.00','护肤霜','2018-05-07',50,'2018-04-09 21:34:15','阿里',121,'美宝莲',NULL),(361,'30.00','60.00','护肤霜','2018-05-01',80,'2018-04-09 21:34:15','阿里',121,'雅诗兰黛',NULL),(376,'30.00','50.00','护肤霜','2018-05-08',50,'2018-04-08 21:33:09','百度集团',120,'雅诗兰黛',NULL),(378,'20.00','30.00','卸妆水','2018-05-01',50,'2018-04-08 21:33:09','百度集团',120,'美宝莲',NULL),(380,'20.00','30.00','发胶','2018-05-08',50,'2018-04-08 21:33:09','百度集团',120,'美宝莲',NULL);

/*Table structure for table `goodsclass` */

DROP TABLE IF EXISTS `goodsclass`;

CREATE TABLE `goodsclass` (
  `id` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodsclass` */

insert  into `goodsclass`(`id`,`brand`,`name`,`number`) values (349,'美宝莲','面膜',28),(352,'美宝莲','护肤霜',48),(355,'美宝莲','美白粉',37),(360,'雅诗兰黛','护肤霜',126),(377,'美宝莲','卸妆水',48),(379,'美宝莲','发胶',49);

/*Table structure for table `goodsrecord` */

DROP TABLE IF EXISTS `goodsrecord`;

CREATE TABLE `goodsrecord` (
  `id` int(11) NOT NULL,
  `grdate` varchar(30) NOT NULL,
  `grmoney` decimal(19,2) NOT NULL,
  `reallmoney` varchar(30) NOT NULL,
  `waiterid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk4sigygb139acfm8tvrmphcq8` (`waiterid`),
  CONSTRAINT `FKk4sigygb139acfm8tvrmphcq8` FOREIGN KEY (`waiterid`) REFERENCES `waiter` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodsrecord` */

insert  into `goodsrecord`(`id`,`grdate`,`grmoney`,`reallmoney`,`waiterid`) values (365,'2018-05-17 00:52:56','80.00','80',4),(368,'2018-05-17 00:54:25','280.00','280',2),(372,'2018-05-17 01:40:13','105.00','105',1),(381,'2018-05-17 01:46:18','90.00','90',4),(385,'2018-05-29 16:55:10','140.00','140',6),(390,'2018-05-30 09:10:18','85.00','85',1);

/*Table structure for table `goodsrecorddetail` */

DROP TABLE IF EXISTS `goodsrecorddetail`;

CREATE TABLE `goodsrecorddetail` (
  `id` int(11) NOT NULL,
  `grdate` varchar(50) DEFAULT NULL,
  `grmoney` decimal(19,2) NOT NULL,
  `name` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `goodsRecordid` int(11) DEFAULT NULL,
  `price` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhe3fx6i2bd9mm08s1h49mg7df` (`goodsid`),
  KEY `FKdyf0uvh6bogu17p505rwx1r7d` (`goodsRecordid`),
  CONSTRAINT `FKdyf0uvh6bogu17p505rwx1r7d` FOREIGN KEY (`goodsRecordid`) REFERENCES `goodsrecord` (`id`),
  CONSTRAINT `FKhe3fx6i2bd9mm08s1h49mg7df` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodsrecorddetail` */

insert  into `goodsrecorddetail`(`id`,`grdate`,`grmoney`,`name`,`number`,`goodsid`,`goodsRecordid`,`price`) values (366,'2018-05-17 00:52:56','80.00','美白粉--美宝莲',1,354,365,'80'),(369,'2018-05-17 00:54:25','120.00','护肤霜--雅诗兰黛',2,361,368,'60'),(370,'2018-05-17 00:54:18','160.00','护肤霜--美宝莲',2,356,368,'80'),(373,'2018-05-17 01:40:13','80.00','美白粉--美宝莲',1,354,372,'80'),(374,'2018-05-17 01:39:50','25.00','面膜--美宝莲',1,350,372,'25'),(382,'2018-05-17 01:46:14','30.00','卸妆水--美宝莲',1,378,381,'30'),(383,'2018-05-17 01:46:18','60.00','护肤霜--雅诗兰黛',1,361,381,'60'),(386,'2018-05-29 16:54:59','80.00','美白粉--美宝莲',1,354,385,'80'),(387,'2018-05-29 16:55:10','30.00','发胶--美宝莲',1,380,385,'30'),(388,'2018-05-29 16:55:04','30.00','卸妆水--美宝莲',1,378,385,'30'),(391,'2018-05-30 09:10:11','25.00','面膜--美宝莲',1,350,390,'25'),(392,'2018-05-30 09:10:18','60.00','护肤霜--雅诗兰黛',1,361,390,'60');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (394);

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `producer` varchar(50) NOT NULL,
  `productiondate` varchar(50) DEFAULT NULL,
  `remark` text,
  `serviceid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd70o76y30il25s96am0kk6xiy` (`serviceid`),
  CONSTRAINT `FKd70o76y30il25s96am0kk6xiy` FOREIGN KEY (`serviceid`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `material` */

insert  into `material`(`id`,`brand`,`name`,`producer`,`productiondate`,`remark`,`serviceid`) values (1,'美宝莲','美白粉','****有限公司','2018-5-16',NULL,14),(2,'美宝莲','粉底膏','****有限公司','2018-05-13 00:00:00','',14);

/*Table structure for table `operator` */

DROP TABLE IF EXISTS `operator`;

CREATE TABLE `operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(20) NOT NULL COMMENT '员工姓名',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `sex` varchar(1) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `storeid` int(11) NOT NULL COMMENT '所属店面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `operator` */

insert  into `operator`(`id`,`name`,`phone`,`sex`,`age`,`storeid`) values (1,'张三','13134656','男',22,1),(2,'李四·','121212','男',34,7),(8,'王五','11111111','女',25,8),(9,'刘琦','45646546','男',28,1);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `id` int(11) NOT NULL,
  `date` varchar(30) NOT NULL,
  `money` decimal(19,2) NOT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `purchasename` varchar(30) NOT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase` */

insert  into `purchase`(`id`,`date`,`money`,`provider`,`purchasename`,`remark`) values (120,'2018-04-08 21:33:09','60000.00','百度集团','时崎狂三','还是可以'),(121,'2018-04-09 21:34:15','40000.00','阿里','小埋','很好'),(122,'2018-04-17 21:35:54','49999.00','淘宝','张三','非常便宜');

/*Table structure for table `salary` */

DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `minsalary` decimal(19,2) NOT NULL,
  `reward` decimal(19,2) NOT NULL,
  `summoney` decimal(19,2) NOT NULL,
  `waiterid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5epjkwicivaso7n8an6rhmqq` (`waiterid`),
  CONSTRAINT `FKl5epjkwicivaso7n8an6rhmqq` FOREIGN KEY (`waiterid`) REFERENCES `waiter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salary` */

insert  into `salary`(`id`,`minsalary`,`reward`,`summoney`,`waiterid`) values (1,'2000.00','3000.00','5000.00',1),(258,'3000.00','0.00','3000.00',2),(271,'2000.00','0.00','2000.00',4),(281,'3000.00','0.00','3000.00',6),(313,'3000.00','0.00','3000.00',8);

/*Table structure for table `salarydetail` */

DROP TABLE IF EXISTS `salarydetail`;

CREATE TABLE `salarydetail` (
  `id` int(11) NOT NULL,
  `allmoney` decimal(19,2) NOT NULL,
  `date` varchar(30) NOT NULL,
  `rate` decimal(19,2) NOT NULL,
  `reward` decimal(19,2) NOT NULL,
  `state` varchar(1) NOT NULL,
  `consumeid` int(11) DEFAULT NULL,
  `grid` int(11) DEFAULT NULL,
  `visitorid` int(11) DEFAULT NULL,
  `salaryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhg9n1uo8drjvey3w0cpuhnho8` (`consumeid`),
  KEY `FK7jhfw13rpx8y2stu5q33rr72l` (`grid`),
  KEY `FK98ceh4sio66k75p5qskobsdyb` (`visitorid`),
  KEY `FK3j6y1sdlvoktoqk8ng1b8nj26` (`salaryid`),
  CONSTRAINT `FK3j6y1sdlvoktoqk8ng1b8nj26` FOREIGN KEY (`salaryid`) REFERENCES `salary` (`id`),
  CONSTRAINT `FK7jhfw13rpx8y2stu5q33rr72l` FOREIGN KEY (`grid`) REFERENCES `goodsrecord` (`id`),
  CONSTRAINT `FK98ceh4sio66k75p5qskobsdyb` FOREIGN KEY (`visitorid`) REFERENCES `visitor` (`id`),
  CONSTRAINT `FKhg9n1uo8drjvey3w0cpuhnho8` FOREIGN KEY (`consumeid`) REFERENCES `consume` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salarydetail` */

insert  into `salarydetail`(`id`,`allmoney`,`date`,`rate`,`reward`,`state`,`consumeid`,`grid`,`visitorid`,`salaryid`) values (367,'80.00','2018-05-17 00:52:56','0.10','8.00','0',NULL,365,NULL,271),(371,'280.00','2018-05-17 00:54:25','0.10','28.00','0',NULL,368,NULL,258),(375,'105.00','2018-05-17 01:40:13','0.10','10.50','0',NULL,372,NULL,1),(384,'90.00','2018-05-17 01:46:18','0.10','9.00','0',NULL,381,NULL,271),(389,'140.00','2018-05-29 16:55:10','0.10','14.00','0',NULL,385,NULL,281),(393,'85.00','2018-05-30 09:10:18','0.10','8.50','0',NULL,390,NULL,1);

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `servicename` varchar(30) NOT NULL COMMENT '服务名称',
  `price` double NOT NULL COMMENT '价格',
  `imagepath` varchar(50) NOT NULL COMMENT '图片路径',
  `vipprice` double NOT NULL COMMENT 'vip价格',
  `remark` text NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `service` */

insert  into `service`(`id`,`servicename`,`price`,`imagepath`,`vipprice`,`remark`) values (1,'做眉毛',6666,'default.jpg',5988,''),(2,'修睫毛',3333,'default.jpg',2999,''),(4,'秀额头',9999,'default.jpg',8888,''),(5,'美睫',9999,'default.jpg',8888,''),(13,'头发打蜡',9999,'default.jpg',8888,'天然发蜡'),(14,'做粉底',9999,'default.jpg',6666,'All Hours粉底液\r\n'),(15,'种睫毛',9999,'default.jpg',8888,'');

/*Table structure for table `serviceimage` */

DROP TABLE IF EXISTS `serviceimage`;

CREATE TABLE `serviceimage` (
  `serviceimageid` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `serviceid` int(11) NOT NULL COMMENT '服务id',
  `filename` varchar(32) NOT NULL COMMENT '原图片名',
  `servicename` varchar(64) NOT NULL COMMENT '数据库图片名',
  PRIMARY KEY (`serviceimageid`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `serviceimage` */

insert  into `serviceimage`(`serviceimageid`,`serviceid`,`filename`,`servicename`) values (70,1,'9450.jpg','1514806623437.jpg'),(75,5,'1360389729630.jpg','1514807678609.jpg'),(78,2,'m.jpg','1521770744544.jpg'),(81,14,'360壁纸_a214975.jpg','1523001343111.jpg'),(82,4,'286726.jpg','1523001405471.jpg'),(83,13,'231817.jpg','1523001449203.jpg');

/*Table structure for table `shopmanager` */

DROP TABLE IF EXISTS `shopmanager`;

CREATE TABLE `shopmanager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(20) NOT NULL COMMENT '店长姓名',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `sex` varchar(1) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `storeid` int(11) NOT NULL COMMENT '所管理店面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `shopmanager` */

insert  into `shopmanager`(`id`,`name`,`phone`,`sex`,`age`,`storeid`) values (10,'周某某','546545646','男',23,7),(14,'大黄瓜','312312312','女',23,2);

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '店名',
  `address` varchar(50) NOT NULL COMMENT '店面地址',
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `store` */

insert  into `store`(`id`,`name`,`address`,`phone`) values (1,'3D美睫总店','重庆沙坪坝','18800569987'),(2,'美睫第一分店','重庆南坪','18800569987'),(6,'美睫第二分店','重庆南火车站','18800569987'),(7,'美睫第三分店','重庆北火车站','18800569987'),(8,'美睫第四分店','重庆沙坪坝北区','18800569987'),(9,'美睫第五分店','重庆市慈溪口东门','18800569987');

/*Table structure for table `t_nav` */

DROP TABLE IF EXISTS `t_nav`;

CREATE TABLE `t_nav` (
  `id` int(11) NOT NULL COMMENT '导航ID',
  `text` varchar(20) NOT NULL COMMENT '导航名',
  `state` char(10) NOT NULL COMMENT '状态',
  `iconCls` char(20) NOT NULL COMMENT '图标',
  `url` char(50) NOT NULL COMMENT 'url',
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_nav` */

insert  into `t_nav`(`id`,`text`,`state`,`iconCls`,`url`,`tid`) values (1,'基本信息管理','closed','icon-group','',0),(2,'会员信息','open','icon-user','vip.jsp',1),(3,'服务类型信息','open','icon-user','service.jsp',1),(4,'店面信息','open','icon-user','store.jsp',1),(5,'员工信息','closed','icon-group','',100),(6,'技术员工信息','open','icon-user','operator.jsp',100),(7,'服务员工信息','open','icon-user','waiter.jsp',1),(8,'店长信息','open','icon-user','shopmanager.jsp',100),(9,'消费记录','closed','icon-group','',0),(10,'会员消费记录','open','icon-user','consume.jsp',9),(11,'游客消费记录','open','icon-user','visitor.jsp',9),(12,'库存管理','closed','icon-group','',0),(14,'采购表','open','icon-user','purchase.jsp',12),(15,'业务统计','closed','icon-group','',0),(16,'服务统计','open','icon-user','servicestatistics.jsp',15),(17,'商品消费记录','open','icon-user','goodsrecord.jsp',9),(18,'商品销售统计','open','icon-user','goodsrecordstatistics.jsp',15),(19,'系统管理','closed','icon-group','',0),(20,'用户管理','open','icon-user','user.jsp',19),(21,'库存查看','open','icon-user','goodsclass.jsp',12),(22,'工资管理','closed','icon-group','',0),(23,'工资记录详情','open','icon-user','salarydetail.jsp',22),(24,'工资信息','open','icon-user','salary.jsp',22),(25,'材料信息','open','icon-user','material.jsp',1);

/*Table structure for table `vip` */

DROP TABLE IF EXISTS `vip`;

CREATE TABLE `vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(20) NOT NULL COMMENT '会员名字',
  `age` int(11) NOT NULL COMMENT '年龄',
  `sex` varchar(1) NOT NULL COMMENT '性别',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `remark` text NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `vip` */

insert  into `vip`(`id`,`name`,`age`,`sex`,`phone`,`remark`) values (5,'时崎狂三',20,'女','13999806683','约会大作战'),(7,'维多利加',18,'女','234324','哥特萝莉事件簿'),(8,'小埋',18,'女','134213','干物妹'),(10,'海老名菜菜',18,'女','4323432','干物妹'),(21,'雏田',18,'女','312312','火影忍者'),(22,'小樱',18,'女','22222222','火影忍者'),(24,'婷婷',23,'女','231232','记得是开发的'),(25,'saber',18,'女','31321648797','fate'),(29,'椎名真白',18,'女','56456464','樱花庄的宠物女孩'),(30,'托尔',350,'女','321312312','小林家的妹抖龙'),(31,'康娜酱',200,'女','2321312','小林家的妹抖龙'),(32,'娜美',19,'女','656455655','海贼王'),(43,'小杰',23,'男','45345345345','大发送到');

/*Table structure for table `visitor` */

DROP TABLE IF EXISTS `visitor`;

CREATE TABLE `visitor` (
  `id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `money` decimal(19,2) NOT NULL,
  `name` varchar(255) NOT NULL,
  `serviceid` int(11) DEFAULT NULL,
  `waiterid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeylaufxx5c04h065hswjoun15` (`waiterid`),
  KEY `FKaow06ntpvwd0ewdawmo2tfvsq` (`serviceid`),
  CONSTRAINT `FKaow06ntpvwd0ewdawmo2tfvsq` FOREIGN KEY (`serviceid`) REFERENCES `service` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKeylaufxx5c04h065hswjoun15` FOREIGN KEY (`waiterid`) REFERENCES `waiter` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `visitor` */

insert  into `visitor`(`id`,`date`,`money`,`name`,`serviceid`,`waiterid`) values (101,'2018-04-04 10:31:20','600.00','刘三姐',5,2),(103,'2018-04-04 10:32:40','800.00','艾丽铎',2,2),(110,'2018-04-06 10:15:06','600.00','小芳',1,1),(115,'2018-04-08 14:25:38','888.00','阿狸',13,1),(270,'2018-05-12 11:40:37','800.00','哥特咯里',1,1),(283,'2018-05-12 23:27:50','300.00','爱美丽',1,6),(323,'2018-05-14 22:13:35','200.00','大侠',1,8),(336,'2018-05-14 22:16:23','300.00','10',1,NULL),(344,'2018-05-14 22:29:34','300.00','20',NULL,1);

/*Table structure for table `waiter` */

DROP TABLE IF EXISTS `waiter`;

CREATE TABLE `waiter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `age` int(11) NOT NULL,
  `salary` double NOT NULL,
  `storeid` int(11) NOT NULL,
  `job` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `waiter` */

insert  into `waiter`(`id`,`name`,`phone`,`sex`,`age`,`salary`,`storeid`,`job`) values (1,'小美','16335889988','女',22,2500,1,'总店长'),(2,'郭美美','18800299740','女',26,2555,6,'店长'),(4,'战三','31231232','男',23,3444,9,'服务员'),(6,'方世玉','123323123','男',25,4444,2,'服务员'),(8,'阿狸','13558983470','男',23,5000,9,'服务员');

/*Table structure for table `xt_user` */

DROP TABLE IF EXISTS `xt_user`;

CREATE TABLE `xt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `loginname` varchar(20) NOT NULL COMMENT '登录名',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `email` varchar(20) NOT NULL COMMENT '邮箱',
  `jurisdiction` varchar(15) NOT NULL COMMENT '权限',
  `state` int(10) NOT NULL DEFAULT '0' COMMENT '登录状态',
  `storeid` int(11) NOT NULL COMMENT '店面ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `xt_user` */

insert  into `xt_user`(`id`,`name`,`loginname`,`password`,`email`,`jurisdiction`,`state`,`storeid`) values (1,'店长','admin','123456','houwenhua228@163.com','系统管理员',0,1),(2,'李四','lisi','123456','adfdfs@qq.com','管理员',0,2),(4,'张三','zhang','123456','11111111111@qq.com','管理员',0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
