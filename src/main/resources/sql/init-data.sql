CREATE DATABASE  IF NOT EXISTS `message`
USE `message`;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`aid`, `aname`, `apassword`, `email`, `message`, `wechat`) VALUES (1,'admin','670b14728ad9902aecba32e22fa4f6bd',NULL,NULL,NULL);

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`cid`, `cname`, `cpassword`, `createtime`, `modtime`) VALUES (1,'hl','ff92a240d11b05ebd392348c35f781b2','2015-01-20 00:00:00','2016-11-10 09:55:42');
INSERT INTO `client` (`cid`, `cname`, `cpassword`, `createtime`, `modtime`) VALUES (2,'bubingy','ff92a240d11b05ebd392348c35f781b2','2016-11-08 10:23:28','2016-11-08 10:23:28');

--
-- Dumping data for table `einterface`
--

INSERT INTO `einterface` (`infid`, `host`, `infname`, `infpassword`, `inftype`, `infurl`, `port`, `title`) VALUES (1,NULL,'appid','secret',1,'https://qyapi.weixin.qq.com/cgi-bin/',NULL,NULL);
INSERT INTO `einterface` (`infid`, `host`, `infname`, `infpassword`, `inftype`, `infurl`, `port`, `title`) VALUES (2,NULL,'23486620','128dd6fde0561a4d4b94772f3aa3595d',3,'http://gw.api.taobao.com/router/rest',NULL,NULL);
INSERT INTO `einterface` (`infid`, `host`, `infname`, `infpassword`, `inftype`, `infurl`, `port`, `title`) VALUES (4,'1','1@q.c','1',2,NULL,'1','1');

--
-- Dumping data for table `msg`
--

INSERT INTO `msg` (`mid`, `cid`, `mcontent`, `oid`, `sendtime`, `sendway`) VALUES (14,1,'hello',1,'2016-10-21 10:27:07',1);
INSERT INTO `msg` (`mid`, `cid`, `mcontent`, `oid`, `sendtime`, `sendway`) VALUES (33,1,'您好，我是unionpaysmart，来自群发，谢谢',1,'2016-10-21 11:43:35',2);
INSERT INTO `msg` (`mid`, `cid`, `mcontent`, `oid`, `sendtime`, `sendway`) VALUES (34,1,'您好，我是unionpaysmart，来自群发，谢谢',2,'2016-10-21 11:43:35',2);
INSERT INTO `msg` (`mid`, `cid`, `mcontent`, `oid`, `sendtime`, `sendway`) VALUES (51,1,'Congratulations,中英文测试正常',2,'2016-11-08 11:42:57',2);
INSERT INTO `msg` (`mid`, `cid`, `mcontent`, `oid`, `sendtime`, `sendway`) VALUES (56,1,'Congratulations,中英文测试正常',2,'2016-11-08 11:43:36',2);

--
-- Dumping data for table `object`
--

INSERT INTO `object` (`oid`, `brand`, `createtime`, `email`, `message`, `modtime`, `oname`, `wchat`) VALUES (1,'','2015-01-20 00:00:00','mm@unionpaysmart.com','18300101100','2015-01-20 00:00:00','mm','mm');
INSERT INTO `object` (`oid`, `brand`, `createtime`, `email`, `message`, `modtime`, `oname`, `wchat`) VALUES (2,'','2015-01-20 00:00:00','xx@unionpaysmart.com','18310101100','2015-01-20 00:00:00','xx','xx');

--
-- Dumping data for table `objectgroup`
--

INSERT INTO `objectgroup` (`id`, `gid`, `oid`) VALUES (10,2,1);
INSERT INTO `objectgroup` (`id`, `gid`, `oid`) VALUES (14,3,2);

--
-- Dumping data for table `ogroup`
--

INSERT INTO `ogroup` (`gid`, `createtime`, `gname`, `modtime`) VALUES (2,'2016-10-21 14:05:42','突击分队','2016-11-08 16:20:01');
INSERT INTO `ogroup` (`gid`, `createtime`, `gname`, `modtime`) VALUES (3,'2016-11-10 11:23:31','双十一秒杀分队','2016-11-10 11:23:31');
