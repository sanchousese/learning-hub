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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Answer`
--

LOCK TABLES `Answer` WRITE;
/*!40000 ALTER TABLE `Answer` DISABLE KEYS */;
INSERT INTO `Answer` VALUES (1,'Do',1,''),(2,'Kami',1,'\0'),(3,'God',1,'\0'),(4,'Guge',1,'\0'),(5,'Homi',2,'\0'),(6,'Code',2,''),(7,'Must',2,'\0'),(8,'Soje',2,''),(9,'3',3,'\0'),(10,'Exe file',4,'\0'),(11,'4',3,'\0'),(12,'Deb file',4,'\0'),(13,'5',3,'\0'),(14,'Terminal',4,'\0'),(15,'6',3,'\0'),(16,'CD Disk',4,'\0');
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
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `body` text,
  `date` timestamp NULL DEFAULT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idComment`),
  UNIQUE KEY `idComment_UNIQUE` (`idComment`),
  KEY `idCourseLink_idx` (`idCourse`),
  CONSTRAINT `idCourseLink` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`idCourse`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (1,'Comment UPDATE',NULL,2),(2,'COMMENT TEST',NULL,1),(3,'new',NULL,2),(4,'nio\n        ',NULL,2),(5,'nio 2',NULL,2),(6,'v',NULL,2),(7,'123',NULL,2),(8,'Comment test','2014-08-06 13:35:51',2);
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CommentLesson`
--

DROP TABLE IF EXISTS `CommentLesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CommentLesson` (
  `idCommentLesson` int(11) NOT NULL AUTO_INCREMENT,
  `body` text,
  `date` timestamp NULL DEFAULT NULL,
  `idLesson` int(11) NOT NULL,
  PRIMARY KEY (`idCommentLesson`),
  UNIQUE KEY `idCommentLesson_UNIQUE` (`idCommentLesson`),
  KEY `idLessonComment_idx` (`idLesson`),
  CONSTRAINT `idLessonComment` FOREIGN KEY (`idLesson`) REFERENCES `Lesson` (`idLesson`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CommentLesson`
--

LOCK TABLES `CommentLesson` WRITE;
/*!40000 ALTER TABLE `CommentLesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `CommentLesson` ENABLE KEYS */;
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
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `description` varchar(10000) NOT NULL,
  `price` int(11) DEFAULT '0',
  `rate` int(11) DEFAULT '4',
  `idSubject` int(11) NOT NULL,
  `mainImagePath` varchar(50) DEFAULT NULL,
  `mainVideoPath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCourse`),
  UNIQUE KEY `idCourse_UNIQUE` (`idCourse`),
  KEY `idSubject_idx` (`idSubject`),
  CONSTRAINT `idSubject` FOREIGN KEY (`idSubject`) REFERENCES `Subject` (`idSubject`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'Jersey','2005-12-20','2015-01-25','Some greate description',100,5,1,'1.jpg','1.mp4'),(2,'Steam on Ubuntu','2014-06-28','2014-08-28','A quick tutorial showing you how to get Steam on Ubuntu 12.04 LTS',0,2,1,'ie3gich1n3haj5lo1uibomo8c7.png','jo0oh8636qe3u14ejnq7ai0frr.mp4'),(3,'Transactions in MySQL','2014-09-04','2014-10-24','Transactions in MySQL',45,3,3,NULL,NULL),(4,'MySQL Stored Procedures and Functions','2014-10-26','2014-12-12','MySQL Stored Procedures and Functions',65,4,3,NULL,NULL),(5,'MySQL for beginners','2015-01-30','2015-04-12','Mysql for beginners',75,4,3,NULL,NULL),(6,'Oracle on the Web','2015-03-22','2015-05-18','Oracle on the Web',50,5,4,NULL,NULL),(7,'Object-Oriented PHP Basics','2014-09-16','2014-11-28','Learn the basics of OO coding in PHP',95,4,5,NULL,NULL),(8,'PHP Basics','2014-09-22','2014-12-21','Lets learn PHP!',80,5,5,NULL,NULL),(9,'Enhancing a Simple PHP Application','2014-12-24','2015-04-12','Enhancing a Simple PHP Application',100,3,5,NULL,NULL),(10,'PHP & The Stripe API','2015-03-14','2015-05-25','PHP & The Stripe API',60,4,5,NULL,NULL),(11,'Security in PHP Application','2015-03-20','2015-05-24','Building Security into your PHP Applications',70,4,5,NULL,NULL),(16,'Imageg',NULL,NULL,'hkj',67,0,1,'c405t13cj9o53culg97v547gs9.jpg',NULL),(17,'Boom',NULL,NULL,'Temp',45,0,1,NULL,NULL),(18,'Boom2',NULL,NULL,'Temp',45,0,1,NULL,NULL),(19,'Boom2',NULL,NULL,'Temp',45,0,1,NULL,NULL),(20,'yt',NULL,NULL,'io',89,0,1,NULL,NULL),(21,'Thkl',NULL,NULL,'io',56,0,1,NULL,NULL),(25,'rty',NULL,NULL,'ty',78,0,2,NULL,NULL),(26,'TestAdd',NULL,NULL,'Boom Boom Boom Boom Boom',12,12,1,'i6us1a31nnl81t4jmbecvolage.png','be4olbiljgahfe0v06mhjcm8sp.mp4');
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
-- Table structure for table `Discipline`
--

DROP TABLE IF EXISTS `Discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Discipline` (
  `idDiscipline` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `idSpecialty` int(11) NOT NULL,
  PRIMARY KEY (`idDiscipline`),
  UNIQUE KEY `idDiscipline_UNIQUE` (`idDiscipline`),
  KEY `idSpecialty_idx` (`idSpecialty`),
  CONSTRAINT `idSpecialty` FOREIGN KEY (`idSpecialty`) REFERENCES `Specialty` (`idSpecialty`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Discipline`
--

LOCK TABLES `Discipline` WRITE;
/*!40000 ALTER TABLE `Discipline` DISABLE KEYS */;
INSERT INTO `Discipline` VALUES (1,'Java','Java is java',1),(2,'C#','C# is VS',1),(3,'PHP','PHP is a server scripting language',1),(4,'HTML','HyperText Markup Language',1),(5,'Database','Organized collection of data',1);
/*!40000 ALTER TABLE `Discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lesson`
--

DROP TABLE IF EXISTS `Lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lesson` (
  `idLesson` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `lessonVideo` varchar(45) DEFAULT NULL,
  `idCourse` int(11) NOT NULL,
  PRIMARY KEY (`idLesson`),
  UNIQUE KEY `idModule_UNIQUE` (`idLesson`),
  KEY `fk_Module_1_idx` (`idCourse`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lesson`
--

LOCK TABLES `Lesson` WRITE;
/*!40000 ALTER TABLE `Lesson` DISABLE KEYS */;
INSERT INTO `Lesson` VALUES (1,'FormData','Some desc',NULL,1),(2,'Insert OK','Bla OK',NULL,1),(3,'Steam','Check functionality of steam on Linux OS',NULL,2);
/*!40000 ALTER TABLE `Lesson` ENABLE KEYS */;
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
  CONSTRAINT `fk_Question_1` FOREIGN KEY (`idTest`) REFERENCES `Test` (`idTest`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
INSERT INTO `Question` VALUES (1,'What',3),(2,'Why',3),(3,'How many commands you need to execute on terminal to install Steam?',4),(4,'What you need to install steam on Linux?',4);
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
-- Table structure for table `Specialty`
--

DROP TABLE IF EXISTS `Specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Specialty` (
  `idSpecialty` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`idSpecialty`),
  UNIQUE KEY `idSpecialty_UNIQUE` (`idSpecialty`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Specialty`
--

LOCK TABLES `Specialty` WRITE;
/*!40000 ALTER TABLE `Specialty` DISABLE KEYS */;
INSERT INTO `Specialty` VALUES (1,'IT','Thing about complex things'),(2,'Psychology','Know your self');
/*!40000 ALTER TABLE `Specialty` ENABLE KEYS */;
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
  `idDiscipline` int(11) NOT NULL,
  PRIMARY KEY (`idSubject`),
  UNIQUE KEY `idSubject_UNIQUE` (`idSubject`),
  KEY `idDiscipline_idx` (`idDiscipline`),
  CONSTRAINT `idDiscipline` FOREIGN KEY (`idDiscipline`) REFERENCES `Discipline` (`idDiscipline`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
INSERT INTO `Subject` VALUES (1,'Rest','Rest is secure','',1),(2,'Hiberanate','Hibernate isn\'t JPA','',1),(3,'MySQL','MySQL','',5),(4,'Oracle','Oracle','',5),(5,'PHP_subject','PHP_subject','',3);
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
  `name` varchar(45) DEFAULT NULL,
  `idLesson` int(11) NOT NULL,
  PRIMARY KEY (`idTest`),
  UNIQUE KEY `idTest_UNIQUE` (`idTest`),
  KEY `fk_Test_1_idModule_idx` (`idLesson`),
  CONSTRAINT `fk_Test_1_id_Lesson` FOREIGN KEY (`idLesson`) REFERENCES `Lesson` (`idLesson`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test`
--

LOCK TABLES `Test` WRITE;
/*!40000 ALTER TABLE `Test` DISABLE KEYS */;
INSERT INTO `Test` VALUES (3,'Test 1',2),(4,'Test 1',3);
/*!40000 ALTER TABLE `Test` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'root','a2682158d73836ea511157606605055','mail@mail.com',0,1),(2,'simple','52378623b09f547e653611a99c592e2','goop@goop.com',0,2),(3,'vasa','d9d1b168eac8f197e0576b56cfc23ece','some@mail.com',1000,4);
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
INSERT INTO `UserCategory` VALUES (1,'Moderator'),(2,'Teacher'),(4,'Student');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCourse`
--

LOCK TABLES `UserCourse` WRITE;
/*!40000 ALTER TABLE `UserCourse` DISABLE KEYS */;
INSERT INTO `UserCourse` VALUES (16,1,2),(20,3,4),(23,1,1);
/*!40000 ALTER TABLE `UserCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserLesson`
--

DROP TABLE IF EXISTS `UserLesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserLesson` (
  `idUserLesson` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idLesson` int(11) NOT NULL,
  `score` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUserLesson`),
  UNIQUE KEY `idUserLesson_UNIQUE` (`idUserLesson`),
  KEY `fk_UserLesson_1_User_idx` (`idUser`),
  KEY `fk_UserLesson_1_Lesson_idx` (`idLesson`),
  CONSTRAINT `fk_UserLesson_1_Lesson` FOREIGN KEY (`idLesson`) REFERENCES `Lesson` (`idLesson`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_UserLesson_1_User` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserLesson`
--

LOCK TABLES `UserLesson` WRITE;
/*!40000 ALTER TABLE `UserLesson` DISABLE KEYS */;
INSERT INTO `UserLesson` VALUES (1,1,1,'78.25');
/*!40000 ALTER TABLE `UserLesson` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-08  9:32:09
