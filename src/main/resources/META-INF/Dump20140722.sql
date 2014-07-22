CREATE DATABASE  IF NOT EXISTS `LearningDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `LearningDB`;
-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: LearningDB
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

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
-- Table structure for table `Answer`
--

DROP TABLE IF EXISTS `Answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Answer` (
  `idAnswer` int(11) NOT NULL AUTO_INCREMENT,
  `ans` text NOT NULL,
  `idQuestion` int(11) NOT NULL,
  `correct` bit(1) NOT NULL,
  PRIMARY KEY (`idAnswer`),
  UNIQUE KEY `idAnswer_UNIQUE` (`idAnswer`),
  KEY `idQuestion_idx` (`idQuestion`),
  CONSTRAINT `idQuestion` FOREIGN KEY (`idQuestion`) REFERENCES `Question` (`idQuestion`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Answer`
--

LOCK TABLES `Answer` WRITE;
/*!40000 ALTER TABLE `Answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `Answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoryRule`
--

DROP TABLE IF EXISTS `CategoryRule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryRule` (
  `idCategoryRule` int(11) NOT NULL AUTO_INCREMENT,
  `idUserCategory` int(11) DEFAULT NULL,
  `idRuleType` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategoryRule`),
  UNIQUE KEY `idCategoryRule_UNIQUE` (`idCategoryRule`),
  KEY `idUserCategoty_idx` (`idUserCategory`),
  KEY `idRuleType_idx` (`idRuleType`),
  CONSTRAINT `idRuleType` FOREIGN KEY (`idRuleType`) REFERENCES `RuleType` (`idRuleType`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idUserCategoty` FOREIGN KEY (`idUserCategory`) REFERENCES `UserCategory` (`idUserCategory`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryRule`
--

LOCK TABLES `CategoryRule` WRITE;
/*!40000 ALTER TABLE `CategoryRule` DISABLE KEYS */;
INSERT INTO `CategoryRule` VALUES (1,1,1);
/*!40000 ALTER TABLE `CategoryRule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `idCourse` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  `idSubject` int(11) NOT NULL,
  PRIMARY KEY (`idCourse`),
  UNIQUE KEY `idCourse_UNIQUE` (`idCourse`),
  KEY `idSubject_idx` (`idSubject`),
  CONSTRAINT `idSubject` FOREIGN KEY (`idSubject`) REFERENCES `Subject` (`idSubject`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'SQL','2005-12-20','2015-01-25','Some greate description',100,100,2),(3,'PHP','3914-06-28','3914-08-28','Some very good description',50,5,1);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseComment`
--

DROP TABLE IF EXISTS `CourseComment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CourseComment` (
  `idCourseComment` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idCourseComment`),
  UNIQUE KEY `idCourseComment_UNIQUE` (`idCourseComment`),
  KEY `idCourse_idx` (`idCourse`),
  CONSTRAINT `idCourse3` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`idCourse`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseComment`
--

LOCK TABLES `CourseComment` WRITE;
/*!40000 ALTER TABLE `CourseComment` DISABLE KEYS */;
/*!40000 ALTER TABLE `CourseComment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MultimediaCourse`
--

DROP TABLE IF EXISTS `MultimediaCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MultimediaCourse` (
  `idMultimediaCourse` int(11) NOT NULL AUTO_INCREMENT,
  `idCourse` int(11) NOT NULL,
  `path` tinyint(4) NOT NULL,
  `idMultimediaType` int(11) NOT NULL,
  PRIMARY KEY (`idMultimediaCourse`),
  UNIQUE KEY `idMultimediaCourse_UNIQUE` (`idMultimediaCourse`),
  KEY `idCourse_idx` (`idCourse`),
  KEY `idMultimediaType_idx` (`idMultimediaType`),
  CONSTRAINT `idCourse2` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idMultimediaType` FOREIGN KEY (`idMultimediaType`) REFERENCES `MultimediaType` (`idMultimediaType`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MultimediaCourse`
--

LOCK TABLES `MultimediaCourse` WRITE;
/*!40000 ALTER TABLE `MultimediaCourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `MultimediaCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MultimediaType`
--

DROP TABLE IF EXISTS `MultimediaType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MultimediaType` (
  `idMultimediaType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idMultimediaType`),
  UNIQUE KEY `idMultimediaType_UNIQUE` (`idMultimediaType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MultimediaType`
--

LOCK TABLES `MultimediaType` WRITE;
/*!40000 ALTER TABLE `MultimediaType` DISABLE KEYS */;
/*!40000 ALTER TABLE `MultimediaType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Question`
--

DROP TABLE IF EXISTS `Question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Question` (
  `idQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `que` text NOT NULL,
  `idTest` int(11) NOT NULL,
  PRIMARY KEY (`idQuestion`),
  UNIQUE KEY `idQuestion_UNIQUE` (`idQuestion`),
  KEY `idTest_idx` (`idTest`),
  CONSTRAINT `idTest` FOREIGN KEY (`idTest`) REFERENCES `Test` (`idTest`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RuleType`
--

DROP TABLE IF EXISTS `RuleType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RuleType` (
  `idRuleType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`idRuleType`),
  UNIQUE KEY `idRuleType_UNIQUE` (`idRuleType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RuleType`
--

LOCK TABLES `RuleType` WRITE;
/*!40000 ALTER TABLE `RuleType` DISABLE KEYS */;
INSERT INTO `RuleType` VALUES (1,'addNews',0),(2,'Insert OK',1);
/*!40000 ALTER TABLE `RuleType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Session` (
  `idUser` int(11) NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastAccessedTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `sessionId` varchar(45) NOT NULL,
  PRIMARY KEY (`sessionId`),
  KEY `idUser_idx` (`idUser`),
  KEY `idUser_idx2` (`idUser`),
  CONSTRAINT `idUserSession` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subject` (
  `idSubject` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `logoPath` tinytext NOT NULL,
  PRIMARY KEY (`idSubject`),
  UNIQUE KEY `idSubject_UNIQUE` (`idSubject`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
INSERT INTO `Subject` VALUES (1,'Programming','JDBC','/img/1.jpg'),(2,'Subject Ins','Some awesome description','/img/2.jpg');
/*!40000 ALTER TABLE `Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Test`
--

DROP TABLE IF EXISTS `Test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Test` (
  `idTest` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idTestGroup` int(11) NOT NULL,
  PRIMARY KEY (`idTest`),
  UNIQUE KEY `idTest_UNIQUE` (`idTest`),
  KEY `idTestGroup_idx` (`idTestGroup`),
  CONSTRAINT `idTestGroup` FOREIGN KEY (`idTestGroup`) REFERENCES `TestGroup` (`idTestGroup`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test`
--

LOCK TABLES `Test` WRITE;
/*!40000 ALTER TABLE `Test` DISABLE KEYS */;
/*!40000 ALTER TABLE `Test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TestGroup`
--

DROP TABLE IF EXISTS `TestGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TestGroup` (
  `idTestGroup` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idTestGroup`),
  UNIQUE KEY `idTestGroup_UNIQUE` (`idTestGroup`),
  KEY `idCourse4_idx` (`idCourse`),
  CONSTRAINT `idCourse4` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TestGroup`
--

LOCK TABLES `TestGroup` WRITE;
/*!40000 ALTER TABLE `TestGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `TestGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
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
  CONSTRAINT `idUserCategory` FOREIGN KEY (`idUserCategory`) REFERENCES `UserCategory` (`idUserCategory`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'root','pook','mail@mail.com',0,1),(2,'simple','pimple','goop@goop.com',0,2),(3,'vasa','gfhjkm','some@mail.com',1000,4);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserCategory`
--

DROP TABLE IF EXISTS `UserCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserCategory` (
  `idUserCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUserCategory`),
  UNIQUE KEY `idUserCategory_UNIQUE` (`idUserCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCategory`
--

LOCK TABLES `UserCategory` WRITE;
/*!40000 ALTER TABLE `UserCategory` DISABLE KEYS */;
INSERT INTO `UserCategory` VALUES (1,'Admin'),(2,'Moderator'),(4,'Student');
/*!40000 ALTER TABLE `UserCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserCourse`
--

DROP TABLE IF EXISTS `UserCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserCourse` (
  `idUserCourse` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idUserCourse`),
  UNIQUE KEY `idUserCourse_UNIQUE` (`idUserCourse`),
  KEY `idUser_idx` (`idUser`),
  KEY `idCourse_idx` (`idCourse`),
  CONSTRAINT `idCourse` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCourse`
--

LOCK TABLES `UserCourse` WRITE;
/*!40000 ALTER TABLE `UserCourse` DISABLE KEYS */;
INSERT INTO `UserCourse` VALUES (3,1,1);
/*!40000 ALTER TABLE `UserCourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-22 23:24:06
