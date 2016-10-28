# 项目初始化sql
CREATE DATABASE bookshare;

INSERT INTO `account` values('admin','admin@unionpaysmart.com','胡龙','123',0);
INSERT INTO `account` values('chenmj','chenmj@unionpaysmart.com','陈敏杰','123',1);
INSERT INTO `account` values('wangjm','wangjm@unionpaysmart.com','王竞明','123',1);
INSERT INTO `account` values('qidongmei','qidongmei@unionpaysmart.com','祁冬梅','123',1);
INSERT INTO `account` values('hulong','hulong@unionpaysmart.com','胡龙','123',1);

#新增book.author,comment.cmtDate
INSERT INTO `book` VALUES(1,'孙悟空','Java From In To Out','浙江大学出版社',0,1,'wangjm');
INSERT INTO `book` VALUES(2,'孔上云','运筹学','江南大学出版社',0,1,'chenmj');
INSERT INTO `book` VALUES(3,'上官云儿','数据库原理','黑龙江大学出版社',0,1,'hulong');
INSERT INTO `book` VALUES(4,'欧阳震恶','编译原理','江南大学出版社',0,1,'wangjm');
INSERT INTO `book` VALUES(5,'唐僧','数据库设计与构建','延边出版社',0,1,'chenmj');
INSERT INTO `book` VALUES(6,'谢希仁','Java语言程序设计','清华大学出版社',0,1,'chenmj');
INSERT INTO `book` VALUES(7,'张震岗','大棚自动化','江南大学出版社',0,1,'qidongmei');
INSERT INTO `book` VALUES(8,'凯撒','计算机组成原理','黑龙江大学出版社',0,1,'chenmj');
INSERT INTO `book` VALUES(9,'钱雪中','JPA手册','江南大学出版社',0,1,'hulong');
INSERT INTO `book` VALUES(10,'Jack','自动化设计','浙江大学出版社',0,1,'qidongmei');
INSERT INTO `book` VALUES(11,'Lord','计算机网络','江南大学出版社',0,1,'chenmj');
INSERT INTO `book` VALUES(12,'Tim','信息安全','东南大学出版社',0,1,'hulong');
INSERT INTO `book` VALUES(13,'Marry','并发编程','延边出版社',0,1,'qidongmei');
INSERT INTO `book` VALUES(14,'罗贯中','三国演义','商务出版社',0,0,'wangjm');
INSERT INTO `book` VALUES(15,'诸葛亮','三个臭皮匠','商务出版社',0,0,'chenmj');
INSERT INTO `book` VALUES(16,'诸葛亮','我与司马老贼','商务出版社',0,0,'hulong');
INSERT INTO `book` VALUES(17,'诸葛亮','刘备那些事儿','延边出版社',0,0,'wangjm');
INSERT INTO `book` VALUES(18,'葛优','非诚勿扰','浙江大学出版社',0,0,'chenmj');
INSERT INTO `book` VALUES(19,'孙尚香','亮哥诸葛也','黑龙江大学出版社',0,0,'qidongmei');
INSERT INTO `book` VALUES(20,'杰克','Rose是肉丝吗？','商务出版社',0,0,'wangjm');
INSERT INTO `book` VALUES(21,'点对点','德国色调','浙江大学出版社',0,0,'chenmj');

