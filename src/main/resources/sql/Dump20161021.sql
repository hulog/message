CREATE DATABASE  IF NOT EXISTS `message` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `message`;
-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: message
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(30) NOT NULL,
  `apassword` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `message` varchar(50) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aid` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','670b14728ad9902aecba32e22fa4f6bd',NULL,NULL,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) NOT NULL,
  `cpassword` varchar(32) NOT NULL,
  `createtime` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'hl','670b14728ad9902aecba32e22fa4f6bd','2015-01-20 00:00:00','2015-01-20 00:00:00');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `einterface`
--

DROP TABLE IF EXISTS `einterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `einterface` (
  `infid` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(50) DEFAULT NULL,
  `infname` varchar(50) NOT NULL,
  `infpassword` varchar(255) NOT NULL,
  `inftype` int(11) NOT NULL,
  `infurl` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`infid`),
  UNIQUE KEY `infid` (`infid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `einterface`
--

LOCK TABLES `einterface` WRITE;
/*!40000 ALTER TABLE `einterface` DISABLE KEYS */;
INSERT INTO `einterface` VALUES (1,NULL,'wx009fc79f1b5f33f6','eNLN9E04V9CPZH8dYn7pE7Zy035jxtyoFEZtmx-aYybg8kvQo3nBuTevjxLXUyId',1,'https://qyapi.weixin.qq.com/cgi-bin/',NULL,NULL),(2,NULL,'23486620','128dd6fde0561a4d4b94772f3aa3595d',3,'http://gw.api.taobao.com/router/rest',NULL,NULL),(3,'smtp.qiye.163.com','wangjm@unionpaysmart.com','Wang246732',2,'','25','消息');
/*!40000 ALTER TABLE `einterface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `mcontent` varchar(255) NOT NULL,
  `oid` int(11) NOT NULL,
  `sendtime` datetime NOT NULL,
  `sendway` int(11) NOT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `mid` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

LOCK TABLES `msg` WRITE;
/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
INSERT INTO `msg` VALUES (1,1,'nihao',1,'2016-10-21 10:18:04',1),(2,1,'nihao',1,'2016-10-21 10:18:04',2),(3,1,'nihao',1,'2016-10-21 10:18:05',3),(4,1,'nihao',1,'2016-10-21 10:18:05',1),(5,1,'nihao',1,'2016-10-21 10:18:05',2),(6,1,'nihao',1,'2016-10-21 10:18:05',3),(7,1,'hello',1,'2016-10-21 10:24:18',1),(8,1,'hello',1,'2016-10-21 10:24:19',2),(9,1,'hello',1,'2016-10-21 10:26:36',3),(10,1,'hello',1,'2016-10-21 10:26:36',1),(11,1,'hello',1,'2016-10-21 10:26:36',2),(12,1,'hello',1,'2016-10-21 10:27:06',1),(13,1,'hello',1,'2016-10-21 10:27:07',3),(14,1,'hello',1,'2016-10-21 10:27:07',1),(15,1,'hello',1,'2016-10-21 10:27:07',3),(16,1,'ss',1,'2016-10-21 10:30:37',1),(17,1,'ss',1,'2016-10-21 10:30:37',3),(18,1,'ss',1,'2016-10-21 10:30:37',1),(19,1,'ss',1,'2016-10-21 10:30:37',3),(20,1,'ss',1,'2016-10-21 10:40:32',1),(21,1,'ss',1,'2016-10-21 10:40:32',2),(22,1,'长一点',1,'2016-10-21 10:41:26',3),(23,1,'hr\\',1,'2016-10-21 10:59:39',1),(24,1,'hr\\',1,'2016-10-21 10:59:40',2),(25,1,'hr\\',1,'2016-10-21 10:59:57',3),(26,1,'mm',1,'2016-10-21 11:08:01',3),(27,1,'mm',1,'2016-10-21 11:08:34',3),(28,1,'nihooo',1,'2016-10-21 11:12:54',3),(29,1,'daf',1,'2016-10-21 11:24:26',3),(30,1,'发生的发生地方',1,'2016-10-21 11:32:11',3),(31,1,'您好，我是unionpaysmart，来自群发，谢谢',1,'2016-10-21 11:43:35',1),(32,1,'您好，我是unionpaysmart，来自群发，谢谢',2,'2016-10-21 11:43:35',1),(33,1,'您好，我是unionpaysmart，来自群发，谢谢',1,'2016-10-21 11:43:35',2),(34,1,'您好，我是unionpaysmart，来自群发，谢谢',2,'2016-10-21 11:43:35',2),(35,1,'1',1,'2016-10-21 14:08:08',1),(36,1,'1',2,'2016-10-21 14:08:08',1);
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `object` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(30) NOT NULL,
  `createtime` datetime NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `message` varchar(50) DEFAULT NULL,
  `modtime` datetime NOT NULL,
  `oname` varchar(30) NOT NULL,
  `wchat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  UNIQUE KEY `oid` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (1,'','2015-01-20 00:00:00','qidongmei@unionpaysmart.com','18352538548','2015-01-20 00:00:00','qidongmei','qidongmei'),(2,'','2015-01-20 00:00:00','hulong@unionpaysmart.com','18352560830','2016-10-21 14:10:28','胡龙','hulong'),(4,'','2016-10-21 14:11:52','wangjm@unionpaysmart.com','18352537957','2016-10-21 14:11:52','王竞明','wangjingming');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objectgroup`
--

DROP TABLE IF EXISTS `objectgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objectgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objectgroup`
--

LOCK TABLES `objectgroup` WRITE;
/*!40000 ALTER TABLE `objectgroup` DISABLE KEYS */;
INSERT INTO `objectgroup` VALUES (3,1,1),(4,1,2);
/*!40000 ALTER TABLE `objectgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogroup`
--

DROP TABLE IF EXISTS `ogroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ogroup` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `gname` varchar(50) NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `gid` (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogroup`
--

LOCK TABLES `ogroup` WRITE;
/*!40000 ALTER TABLE `ogroup` DISABLE KEYS */;
INSERT INTO `ogroup` VALUES (1,'2016-10-21 14:05:42','前期1','2016-10-21 14:06:40');
/*!40000 ALTER TABLE `ogroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-21 14:16:09
