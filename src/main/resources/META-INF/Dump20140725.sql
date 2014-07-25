CREATE DATABASE  IF NOT EXISTS `learningdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `learningdb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: learningdb
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `idAnswer` int(11) NOT NULL AUTO_INCREMENT,
  `ans` text NOT NULL,
  `idQuestion` int(11) NOT NULL,
  `correct` bit(1) NOT NULL,
  PRIMARY KEY (`idAnswer`),
  UNIQUE KEY `idAnswer_UNIQUE` (`idAnswer`),
  KEY `idQuestion_idx` (`idQuestion`),
  CONSTRAINT `idQuestion` FOREIGN KEY (`idQuestion`) REFERENCES `question` (`idQuestion`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryrule`
--

DROP TABLE IF EXISTS `categoryrule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoryrule` (
  `idCategoryRule` int(11) NOT NULL AUTO_INCREMENT,
  `idUserCategory` int(11) DEFAULT NULL,
  `idRuleType` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategoryRule`),
  UNIQUE KEY `idCategoryRule_UNIQUE` (`idCategoryRule`),
  KEY `idUserCategoty_idx` (`idUserCategory`),
  KEY `idRuleType_idx` (`idRuleType`),
  CONSTRAINT `idRuleType` FOREIGN KEY (`idRuleType`) REFERENCES `ruletype` (`idRuleType`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idUserCategoty` FOREIGN KEY (`idUserCategory`) REFERENCES `usercategory` (`idUserCategory`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryrule`
--

LOCK TABLES `categoryrule` WRITE;
/*!40000 ALTER TABLE `categoryrule` DISABLE KEYS */;
INSERT INTO `categoryrule` VALUES (1,1,1);
/*!40000 ALTER TABLE `categoryrule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `idCourse` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `description` varchar(10000) NOT NULL,
  `price` int(11) DEFAULT '0',
  `rate` int(11) DEFAULT '4',
  `idSubject` int(11) NOT NULL,
  `mainImagePath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCourse`),
  UNIQUE KEY `idCourse_UNIQUE` (`idCourse`),
  KEY `idSubject_idx` (`idSubject`),
  CONSTRAINT `idSubject` FOREIGN KEY (`idSubject`) REFERENCES `subject` (`idSubject`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Jersey','2005-12-20','2015-01-25','Some greate description',100,5,1,NULL),(2,'Json','2014-06-28','2014-08-28','Some very good description',50,2,1,NULL),(3,'Transactions in MySQL','2014-09-04','2014-10-24','Transactions in MySQL',45,3,3,NULL),(4,'MySQL Stored Procedures and Functions','2014-10-26','2014-12-12','MySQL Stored Procedures and Functions',65,4,3,NULL),(5,'MySQL for beginners','2015-01-30','2015-04-12','Mysql for beginners',75,4,3,NULL),(6,'Oracle on the Web','2015-03-22','2015-05-18','Oracle on the Web',50,5,4,NULL),(7,'Object-Oriented PHP Basics','2014-09-16','2014-11-28','Learn the basics of OO coding in PHP',95,4,5,NULL),(8,'PHP Basics','2014-09-22','2014-12-21','Lets learn PHP!',80,5,5,NULL),(9,'Enhancing a Simple PHP Application','2014-12-24','2015-04-12','Enhancing a Simple PHP Application',100,3,5,NULL),(10,'PHP & The Stripe API','2015-03-14','2015-05-25','PHP & The Stripe API',60,4,5,NULL),(11,'Security in PHP Application','2015-03-20','2015-05-24','Building Security into your PHP Applications',70,4,5,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursecomment`
--

DROP TABLE IF EXISTS `coursecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursecomment` (
  `idCourseComment` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idCourseComment`),
  UNIQUE KEY `idCourseComment_UNIQUE` (`idCourseComment`),
  KEY `idCourse_idx` (`idCourse`),
  CONSTRAINT `idCourse3` FOREIGN KEY (`idCourse`) REFERENCES `course` (`idCourse`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursecomment`
--

LOCK TABLES `coursecomment` WRITE;
/*!40000 ALTER TABLE `coursecomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discipline` (
  `idDiscipline` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `idSpecialty` int(11) NOT NULL,
  PRIMARY KEY (`idDiscipline`),
  UNIQUE KEY `idDiscipline_UNIQUE` (`idDiscipline`),
  KEY `idSpecialty_idx` (`idSpecialty`),
  CONSTRAINT `idSpecialty` FOREIGN KEY (`idSpecialty`) REFERENCES `specialty` (`idSpecialty`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline`
--

LOCK TABLES `discipline` WRITE;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` VALUES (1,'Java','Java is java',1),(2,'C#','C# is VS',1),(3,'PHP','PHP is a server scripting language',1),(4,'HTML','HyperText Markup Language',1),(5,'Database','Organized collection of data',1);
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multimediacourse`
--

DROP TABLE IF EXISTS `multimediacourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multimediacourse` (
  `idMultimediaCourse` int(11) NOT NULL AUTO_INCREMENT,
  `idCourse` int(11) NOT NULL,
  `path` tinyint(4) NOT NULL,
  `idMultimediaType` int(11) NOT NULL,
  PRIMARY KEY (`idMultimediaCourse`),
  UNIQUE KEY `idMultimediaCourse_UNIQUE` (`idMultimediaCourse`),
  KEY `idCourse_idx` (`idCourse`),
  KEY `idMultimediaType_idx` (`idMultimediaType`),
  CONSTRAINT `idCourse2` FOREIGN KEY (`idCourse`) REFERENCES `course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idMultimediaType` FOREIGN KEY (`idMultimediaType`) REFERENCES `multimediatype` (`idMultimediaType`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multimediacourse`
--

LOCK TABLES `multimediacourse` WRITE;
/*!40000 ALTER TABLE `multimediacourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `multimediacourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multimediatype`
--

DROP TABLE IF EXISTS `multimediatype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multimediatype` (
  `idMultimediaType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idMultimediaType`),
  UNIQUE KEY `idMultimediaType_UNIQUE` (`idMultimediaType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multimediatype`
--

LOCK TABLES `multimediatype` WRITE;
/*!40000 ALTER TABLE `multimediatype` DISABLE KEYS */;
/*!40000 ALTER TABLE `multimediatype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `idQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `que` text NOT NULL,
  `idTest` int(11) NOT NULL,
  PRIMARY KEY (`idQuestion`),
  UNIQUE KEY `idQuestion_UNIQUE` (`idQuestion`),
  KEY `idTest_idx` (`idTest`),
  CONSTRAINT `idTest` FOREIGN KEY (`idTest`) REFERENCES `test` (`idTest`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruletype`
--

DROP TABLE IF EXISTS `ruletype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruletype` (
  `idRuleType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`idRuleType`),
  UNIQUE KEY `idRuleType_UNIQUE` (`idRuleType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruletype`
--

LOCK TABLES `ruletype` WRITE;
/*!40000 ALTER TABLE `ruletype` DISABLE KEYS */;
INSERT INTO `ruletype` VALUES (1,'addNews',0),(2,'Insert OK',1);
/*!40000 ALTER TABLE `ruletype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `idUser` int(11) NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastAccessedTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `sessionId` varchar(45) NOT NULL,
  PRIMARY KEY (`sessionId`),
  KEY `idUser_idx` (`idUser`),
  KEY `idUser_idx2` (`idUser`),
  CONSTRAINT `idUserSession` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialty` (
  `idSpecialty` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`idSpecialty`),
  UNIQUE KEY `idSpecialty_UNIQUE` (`idSpecialty`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (1,'IT','Thing about complex things'),(2,'Psychology','Know your self');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `idSubject` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `logoPath` tinytext NOT NULL,
  `idDiscipline` int(11) NOT NULL,
  PRIMARY KEY (`idSubject`),
  UNIQUE KEY `idSubject_UNIQUE` (`idSubject`),
  KEY `idDiscipline_idx` (`idDiscipline`),
  CONSTRAINT `idDiscipline` FOREIGN KEY (`idDiscipline`) REFERENCES `discipline` (`idDiscipline`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Rest','Rest is secure','',1),(2,'Hiberanate','Hibernate isn\'t JPA','',1),(3,'MySQL','MySQL','',5),(4,'Oracle','Oracle','',5),(5,'PHP_subject','PHP_subject','',3);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `idTest` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idTestGroup` int(11) NOT NULL,
  PRIMARY KEY (`idTest`),
  UNIQUE KEY `idTest_UNIQUE` (`idTest`),
  KEY `idTestGroup_idx` (`idTestGroup`),
  CONSTRAINT `idTestGroup` FOREIGN KEY (`idTestGroup`) REFERENCES `testgroup` (`idTestGroup`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testgroup`
--

DROP TABLE IF EXISTS `testgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testgroup` (
  `idTestGroup` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idTestGroup`),
  UNIQUE KEY `idTestGroup_UNIQUE` (`idTestGroup`),
  KEY `idCourse4_idx` (`idCourse`),
  CONSTRAINT `idCourse4` FOREIGN KEY (`idCourse`) REFERENCES `course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testgroup`
--

LOCK TABLES `testgroup` WRITE;
/*!40000 ALTER TABLE `testgroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `testgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `money` int(11) NOT NULL,
  `idUserCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `idUserCategory_idx` (`idUserCategory`),
  CONSTRAINT `idUserCategory` FOREIGN KEY (`idUserCategory`) REFERENCES `usercategory` (`idUserCategory`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','a2682158d73836ea511157606605055','mail@mail.com',0,1),(2,'simple','52378623b09f547e653611a99c592e2','goop@goop.com',0,2),(3,'vasa','d9d1b168eac8f197e0576b56cfc23ece','some@mail.com',1000,4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercategory`
--

DROP TABLE IF EXISTS `usercategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercategory` (
  `idUserCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUserCategory`),
  UNIQUE KEY `idUserCategory_UNIQUE` (`idUserCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercategory`
--

LOCK TABLES `usercategory` WRITE;
/*!40000 ALTER TABLE `usercategory` DISABLE KEYS */;
INSERT INTO `usercategory` VALUES (1,'Moderator'),(2,'Teacher'),(4,'Student');
/*!40000 ALTER TABLE `usercategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercourse`
--

DROP TABLE IF EXISTS `usercourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercourse` (
  `idUserCourse` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idUserCourse`),
  UNIQUE KEY `idUserCourse_UNIQUE` (`idUserCourse`),
  KEY `idUser_idx` (`idUser`),
  KEY `idCourse_idx` (`idCourse`),
  CONSTRAINT `idCourse` FOREIGN KEY (`idCourse`) REFERENCES `course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercourse`
--

LOCK TABLES `usercourse` WRITE;
/*!40000 ALTER TABLE `usercourse` DISABLE KEYS */;
INSERT INTO `usercourse` VALUES (3,1,1);
/*!40000 ALTER TABLE `usercourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-25 15:58:19