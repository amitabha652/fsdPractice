-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.53-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema fsd_hibernate
--

CREATE DATABASE IF NOT EXISTS fsd_hibernate;
USE fsd_hibernate;

--
-- Definition of table `book_table`
--

DROP TABLE IF EXISTS `book_table`;
CREATE TABLE `book_table` (
  `BOOKID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `VOLUME` int(11) DEFAULT NULL,
  `PUBLISHDATE` date DEFAULT NULL,
  `SUBJECTID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`BOOKID`),
  KEY `FKggx4d3tmebf9hy0ojdbgsoyy6` (`SUBJECTID`),
  CONSTRAINT `FKggx4d3tmebf9hy0ojdbgsoyy6` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject_table` (`SUBJECTID`),
  CONSTRAINT `FK_q2n209eenkbrwikero2w2e9j4` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject_table` (`SUBJECTID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_table`
--

/*!40000 ALTER TABLE `book_table` DISABLE KEYS */;
INSERT INTO `book_table` (`BOOKID`,`TITLE`,`PRICE`,`VOLUME`,`PUBLISHDATE`,`SUBJECTID`) VALUES 
 (1,'Hindi Prose-II',88,1,'1999-05-04',1),
 (2,'Hindi Prose-I',77,2,'1987-07-01',1),
 (3,'Geography for Newbies Vol.I',113,1,'1998-09-01',2),
 (4,'Geography for Beginers vol2',79,2,'1999-07-01',2),
 (6,'Learning Guitar - PART-II',100,1,'2000-12-11',8),
 (7,'Learning Piano',78,9,'2015-11-17',8),
 (8,'Learning Viola',77,9,'2016-11-13',NULL);
/*!40000 ALTER TABLE `book_table` ENABLE KEYS */;


--
-- Definition of table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`cart_id`,`total`,`name`) VALUES 
 (1,50,'MyCart');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;


--
-- Definition of table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES 
 (1),
 (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;


--
-- Definition of table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(255) DEFAULT NULL,
  `item_total` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `cart_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fmmx5k388fiwrvnwqa603oifh` (`cart_id`),
  CONSTRAINT `FK_fmmx5k388fiwrvnwqa603oifh` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`,`item_id`,`item_total`,`quantity`,`cart_id`) VALUES 
 (1,'I1',10,1,1),
 (2,'I2',20,2,1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;


--
-- Definition of table `subject_table`
--

DROP TABLE IF EXISTS `subject_table`;
CREATE TABLE `subject_table` (
  `SUBJECTID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBTITLE` varchar(255) DEFAULT NULL,
  `DURATIONINHOURS` int(11) DEFAULT NULL,
  PRIMARY KEY (`SUBJECTID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject_table`
--

/*!40000 ALTER TABLE `subject_table` DISABLE KEYS */;
INSERT INTO `subject_table` (`SUBJECTID`,`SUBTITLE`,`DURATIONINHOURS`) VALUES 
 (1,'Hindi-Literature',100),
 (2,'Geography-Primary',50),
 (6,'History',19),
 (7,'FSD Music Subject',10),
 (8,'FSD Musicalities',78);
/*!40000 ALTER TABLE `subject_table` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
