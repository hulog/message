CREATE DATABASE  IF NOT EXISTS `message`
USE `message`;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
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

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) NOT NULL,
  `cpassword` varchar(32) NOT NULL,
  `createtime` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `einterface`
--

DROP TABLE IF EXISTS `einterface`;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `mcontent` varchar(255) NOT NULL,
  `oid` int(11) NOT NULL,
  `sendtime` datetime NOT NULL,
  `sendway` int(11) NOT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `mid` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Table structure for table `objectgroup`
--

DROP TABLE IF EXISTS `objectgroup`;
CREATE TABLE `objectgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `oid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Table structure for table `ogroup`
--

DROP TABLE IF EXISTS `ogroup`;
CREATE TABLE `ogroup` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `gname` varchar(50) NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `gid` (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
