-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bus
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `coachs`
--

DROP TABLE IF EXISTS `coachs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coachs` (
  `idCoach` int NOT NULL AUTO_INCREMENT,
  `numberCoach` int DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `typeOfCoach` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idCoach`),
  KEY `FK_coach_typeofcoach` (`typeOfCoach`),
  CONSTRAINT `FK_coach_typeofcoach` FOREIGN KEY (`typeOfCoach`) REFERENCES `typeofcoach` (`nameTypeOfCoach`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coachs`
--

LOCK TABLES `coachs` WRITE;
/*!40000 ALTER TABLE `coachs` DISABLE KEYS */;
INSERT INTO `coachs` VALUES (1,1,10,'Xe Giường Nằm'),(2,2,10,'Xe Khách'),(3,3,10,'Xe Giường Nằm'),(4,4,10,'Xe Khách'),(5,5,10,'Xe Giường Nằm'),(6,6,10,'Xe Khách');
/*!40000 ALTER TABLE `coachs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coachstripcoachseat`
--

DROP TABLE IF EXISTS `coachstripcoachseat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coachstripcoachseat` (
  `idCSCS` int NOT NULL AUTO_INCREMENT,
  `idCoach` int DEFAULT NULL,
  `idCoachStrips` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `departureTime` datetime DEFAULT NULL,
  `statusSeat` int DEFAULT NULL,
  `nameSeat` int DEFAULT NULL,
  `idStaff` int DEFAULT NULL,
  PRIMARY KEY (`idCSCS`),
  KEY `FK_CCT_coach` (`idCoach`),
  KEY `FK_CoachOfCoachStrips_staff` (`idStaff`),
  KEY `FK_coach_coach_strips_2` (`idCoachStrips`),
  CONSTRAINT `FK_CCT_coach` FOREIGN KEY (`idCoach`) REFERENCES `coachs` (`idCoach`),
  CONSTRAINT `FK_coach_coach_strips_2` FOREIGN KEY (`idCoachStrips`) REFERENCES `coachstrips` (`idCoachStips`),
  CONSTRAINT `FK_CoachOfCoachStrips_staff` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`idStaff`)
) ENGINE=InnoDB AUTO_INCREMENT=1201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coachstripcoachseat`
--

LOCK TABLES `coachstripcoachseat` WRITE;
/*!40000 ALTER TABLE `coachstripcoachseat` DISABLE KEYS */;
INSERT INTO `coachstripcoachseat` VALUES (1,1,1,450000,'2023-04-08 05:00:00',1,1,6),(2,1,1,450000,'2023-04-08 05:00:00',1,2,6),(3,1,1,450000,'2023-04-08 05:00:00',1,3,6),(4,1,1,450000,'2023-04-08 05:00:00',1,4,6),(5,1,1,450000,'2023-04-08 05:00:00',1,5,6),(6,1,1,450000,'2023-04-08 05:00:00',1,6,6),(7,1,1,450000,'2023-04-08 05:00:00',1,7,6),(8,1,1,450000,'2023-04-08 05:00:00',1,8,6),(9,1,1,450000,'2023-04-08 05:00:00',0,9,6),(10,1,1,450000,'2023-04-08 05:00:00',0,10,6),(11,2,1,450000,'2023-04-08 15:00:00',1,1,7),(12,2,1,450000,'2023-04-08 15:00:00',1,2,7),(13,2,1,450000,'2023-04-08 15:00:00',1,3,7),(14,2,1,450000,'2023-04-08 15:00:00',1,4,7),(15,2,1,450000,'2023-04-08 15:00:00',1,5,7),(16,2,1,450000,'2023-04-08 15:00:00',1,6,7),(17,2,1,450000,'2023-04-08 15:00:00',1,7,7),(18,2,1,450000,'2023-04-08 15:00:00',1,8,7),(19,2,1,450000,'2023-04-08 15:00:00',0,9,7),(20,2,1,450000,'2023-04-08 15:00:00',0,10,7),(21,3,2,450000,'2023-04-08 07:00:00',1,1,8),(22,3,2,450000,'2023-04-08 07:00:00',1,2,8),(23,3,2,450000,'2023-04-08 07:00:00',1,3,8),(24,3,2,450000,'2023-04-08 07:00:00',1,4,8),(25,3,2,450000,'2023-04-08 07:00:00',1,5,8),(26,3,2,450000,'2023-04-08 07:00:00',1,6,8),(27,3,2,450000,'2023-04-08 07:00:00',1,7,8),(28,3,2,450000,'2023-04-08 07:00:00',1,8,8),(29,3,2,450000,'2023-04-08 07:00:00',0,9,8),(30,3,2,450000,'2023-04-08 07:00:00',0,10,8),(31,4,2,450000,'2023-04-08 19:00:00',1,1,9),(32,4,2,450000,'2023-04-08 19:00:00',1,2,9),(33,4,2,450000,'2023-04-08 19:00:00',1,3,9),(34,4,2,450000,'2023-04-08 19:00:00',1,4,9),(35,4,2,450000,'2023-04-08 19:00:00',1,5,9),(36,4,2,450000,'2023-04-08 19:00:00',1,6,9),(37,4,2,450000,'2023-04-08 19:00:00',1,7,9),(38,4,2,450000,'2023-04-08 19:00:00',1,8,9),(39,4,2,450000,'2023-04-08 19:00:00',0,9,9),(40,4,2,450000,'2023-04-08 19:00:00',0,10,9),(41,5,3,450000,'2023-04-08 10:00:00',1,1,10),(42,5,3,450000,'2023-04-08 10:00:00',1,2,10),(43,5,3,450000,'2023-04-08 10:00:00',1,3,10),(44,5,3,450000,'2023-04-08 10:00:00',1,4,10),(45,5,3,450000,'2023-04-08 10:00:00',1,5,10),(46,5,3,450000,'2023-04-08 10:00:00',1,6,10),(47,5,3,450000,'2023-04-08 10:00:00',1,7,10),(48,5,3,450000,'2023-04-08 10:00:00',1,8,10),(49,5,3,450000,'2023-04-08 10:00:00',0,9,10),(50,5,3,450000,'2023-04-08 10:00:00',0,10,10),(51,6,3,450000,'2023-04-08 22:00:00',1,1,11),(52,6,3,450000,'2023-04-08 22:00:00',1,2,11),(53,6,3,450000,'2023-04-08 22:00:00',1,3,11),(54,6,3,450000,'2023-04-08 22:00:00',1,4,11),(55,6,3,450000,'2023-04-08 22:00:00',1,5,11),(56,6,3,450000,'2023-04-08 22:00:00',1,6,11),(57,6,3,450000,'2023-04-08 22:00:00',1,7,11),(58,6,3,450000,'2023-04-08 22:00:00',1,8,11),(59,6,3,450000,'2023-04-08 22:00:00',0,9,11),(60,6,3,450000,'2023-04-08 22:00:00',0,10,11),(61,1,1,450000,'2023-04-09 05:00:00',1,1,6),(62,1,1,450000,'2023-04-09 05:00:00',1,2,6),(63,1,1,450000,'2023-04-09 05:00:00',1,3,6),(64,1,1,450000,'2023-04-09 05:00:00',1,4,6),(65,1,1,450000,'2023-04-09 05:00:00',1,5,6),(66,1,1,450000,'2023-04-09 05:00:00',1,6,6),(67,1,1,450000,'2023-04-09 05:00:00',1,7,6),(68,1,1,450000,'2023-04-09 05:00:00',1,8,6),(69,1,1,450000,'2023-04-09 05:00:00',0,9,6),(70,2,1,450000,'2023-04-09 05:00:00',0,10,6),(71,2,1,450000,'2023-04-09 15:00:00',1,1,7),(72,2,1,450000,'2023-04-09 15:00:00',1,2,7),(73,2,1,450000,'2023-04-09 15:00:00',1,3,7),(74,2,1,450000,'2023-04-09 15:00:00',1,4,7),(75,2,1,450000,'2023-04-09 15:00:00',1,5,7),(76,2,1,450000,'2023-04-09 15:00:00',1,6,7),(77,2,1,450000,'2023-04-09 15:00:00',1,7,7),(78,2,1,450000,'2023-04-09 15:00:00',1,8,7),(79,2,1,450000,'2023-04-09 15:00:00',0,9,7),(80,3,1,450000,'2023-04-09 15:00:00',0,10,7),(81,3,2,450000,'2023-04-09 07:00:00',1,1,8),(82,3,2,450000,'2023-04-09 07:00:00',1,2,8),(83,3,2,450000,'2023-04-09 07:00:00',1,3,8),(84,3,2,450000,'2023-04-09 07:00:00',1,4,8),(85,3,2,450000,'2023-04-09 07:00:00',1,5,8),(86,3,2,450000,'2023-04-09 07:00:00',1,6,8),(87,3,2,450000,'2023-04-09 07:00:00',1,7,8),(88,3,2,450000,'2023-04-09 07:00:00',1,8,8),(89,3,2,450000,'2023-04-09 07:00:00',0,9,8),(90,3,2,450000,'2023-04-09 07:00:00',0,10,8),(91,4,2,450000,'2023-04-09 19:00:00',1,1,9),(92,4,2,450000,'2023-04-09 19:00:00',1,2,9),(93,4,2,450000,'2023-04-09 19:00:00',1,3,9),(94,4,2,450000,'2023-04-09 19:00:00',1,4,9),(95,4,2,450000,'2023-04-09 19:00:00',1,5,9),(96,4,2,450000,'2023-04-09 19:00:00',1,6,9),(97,4,2,450000,'2023-04-09 19:00:00',1,7,9),(98,4,2,450000,'2023-04-09 19:00:00',1,8,9),(99,4,2,450000,'2023-04-09 19:00:00',0,9,9),(100,5,2,450000,'2023-04-09 19:00:00',0,10,9),(101,5,3,450000,'2023-04-09 10:00:00',1,1,10),(102,5,3,450000,'2023-04-09 10:00:00',1,2,10),(103,5,3,450000,'2023-04-09 10:00:00',1,3,10),(104,5,3,450000,'2023-04-09 10:00:00',1,4,10),(105,5,3,450000,'2023-04-09 10:00:00',1,5,10),(106,5,3,450000,'2023-04-09 10:00:00',1,6,10),(107,5,3,450000,'2023-04-09 10:00:00',1,7,10),(108,5,3,450000,'2023-04-09 10:00:00',1,8,10),(109,5,3,450000,'2023-04-09 10:00:00',0,9,10),(110,5,3,450000,'2023-04-09 10:00:00',0,10,10),(111,6,3,450000,'2023-04-09 22:00:00',1,1,11),(112,6,3,450000,'2023-04-09 22:00:00',1,2,11),(113,6,3,450000,'2023-04-09 22:00:00',1,3,11),(114,6,3,450000,'2023-04-09 22:00:00',1,4,11),(115,6,3,450000,'2023-04-09 22:00:00',1,5,11),(116,6,3,450000,'2023-04-09 22:00:00',1,6,11),(117,6,3,450000,'2023-04-09 22:00:00',1,7,11),(118,6,3,450000,'2023-04-09 22:00:00',1,8,11),(119,6,3,450000,'2023-04-09 22:00:00',0,9,11),(120,6,3,450000,'2023-04-09 22:00:00',0,10,11),(121,1,1,450000,'2023-04-10 05:00:00',1,1,6),(122,1,1,450000,'2023-04-10 05:00:00',1,2,6),(123,1,1,450000,'2023-04-10 05:00:00',1,3,6),(124,1,1,450000,'2023-04-10 05:00:00',1,4,6),(125,1,1,450000,'2023-04-10 05:00:00',1,5,6),(126,1,1,450000,'2023-04-10 05:00:00',1,6,6),(127,1,1,450000,'2023-04-10 05:00:00',1,7,6),(128,1,1,450000,'2023-04-10 05:00:00',1,8,6),(129,1,1,450000,'2023-04-10 05:00:00',0,9,6),(130,2,1,450000,'2023-04-10 05:00:00',0,10,6),(131,2,1,450000,'2023-04-10 15:00:00',1,1,7),(132,2,1,450000,'2023-04-10 15:00:00',1,2,7),(133,2,1,450000,'2023-04-10 15:00:00',1,3,7),(134,2,1,450000,'2023-04-10 15:00:00',1,4,7),(135,2,1,450000,'2023-04-10 15:00:00',1,5,7),(136,2,1,450000,'2023-04-10 15:00:00',1,6,7),(137,2,1,450000,'2023-04-10 15:00:00',1,7,7),(138,2,1,450000,'2023-04-10 15:00:00',1,8,7),(139,2,1,450000,'2023-04-10 15:00:00',0,9,7),(140,3,1,450000,'2023-04-10 15:00:00',0,10,7),(141,3,2,450000,'2023-04-10 07:00:00',1,1,8),(142,3,2,450000,'2023-04-10 07:00:00',1,2,8),(143,3,2,450000,'2023-04-10 07:00:00',1,3,8),(144,3,2,450000,'2023-04-10 07:00:00',1,4,8),(145,3,2,450000,'2023-04-10 07:00:00',1,5,8),(146,3,2,450000,'2023-04-10 07:00:00',1,6,8),(147,3,2,450000,'2023-04-10 07:00:00',1,7,8),(148,3,2,450000,'2023-04-10 07:00:00',1,8,8),(149,3,2,450000,'2023-04-10 07:00:00',0,9,8),(150,3,2,450000,'2023-04-10 07:00:00',0,10,8),(151,4,2,450000,'2023-04-10 19:00:00',1,1,9),(152,4,2,450000,'2023-04-10 19:00:00',1,2,9),(153,4,2,450000,'2023-04-10 19:00:00',1,3,9),(154,4,2,450000,'2023-04-10 19:00:00',1,4,9),(155,4,2,450000,'2023-04-10 19:00:00',1,5,9),(156,4,2,450000,'2023-04-10 19:00:00',1,6,9),(157,4,2,450000,'2023-04-10 19:00:00',1,7,9),(158,4,2,450000,'2023-04-10 19:00:00',1,8,9),(159,4,2,450000,'2023-04-10 19:00:00',0,9,9),(160,5,2,450000,'2023-04-10 19:00:00',0,10,9),(161,5,3,450000,'2023-04-10 10:00:00',1,1,10),(162,5,3,450000,'2023-04-10 10:00:00',1,2,10),(163,5,3,450000,'2023-04-10 10:00:00',1,3,10),(164,5,3,450000,'2023-04-10 10:00:00',1,4,10),(165,5,3,450000,'2023-04-10 10:00:00',1,5,10),(166,5,3,450000,'2023-04-10 10:00:00',1,6,10),(167,5,3,450000,'2023-04-10 10:00:00',1,7,10),(168,5,3,450000,'2023-04-10 10:00:00',1,8,10),(169,5,3,450000,'2023-04-10 10:00:00',0,9,10),(170,5,3,450000,'2023-04-10 10:00:00',0,10,10),(171,6,3,450000,'2023-04-10 22:00:00',1,1,11),(172,6,3,450000,'2023-04-10 22:00:00',1,2,11),(173,6,3,450000,'2023-04-10 22:00:00',1,3,11),(174,6,3,450000,'2023-04-10 22:00:00',1,4,11),(175,6,3,450000,'2023-04-10 22:00:00',1,5,11),(176,6,3,450000,'2023-04-10 22:00:00',1,6,11),(177,6,3,450000,'2023-04-10 22:00:00',1,7,11),(178,6,3,450000,'2023-04-10 22:00:00',1,8,11),(179,6,3,450000,'2023-04-10 22:00:00',0,9,11),(180,6,3,450000,'2023-04-10 22:00:00',0,10,11);
/*!40000 ALTER TABLE `coachstripcoachseat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coachstrips`
--

DROP TABLE IF EXISTS `coachstrips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coachstrips` (
  `idCoachStips` int NOT NULL AUTO_INCREMENT,
  `distance` int DEFAULT NULL,
  `arrivalTime` time DEFAULT NULL,
  `idStationsStart` int DEFAULT NULL,
  `idStationsEnd` int DEFAULT NULL,
  PRIMARY KEY (`idCoachStips`),
  KEY `FK_coachstrips_station_start` (`idStationsStart`),
  KEY `FK_coachstrips_station_end` (`idStationsEnd`),
  CONSTRAINT `FK_coachstrips_station_end` FOREIGN KEY (`idStationsEnd`) REFERENCES `stations` (`idStations`),
  CONSTRAINT `FK_coachstrips_station_start` FOREIGN KEY (`idStationsStart`) REFERENCES `stations` (`idStations`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coachstrips`
--

LOCK TABLES `coachstrips` WRITE;
/*!40000 ALTER TABLE `coachstrips` DISABLE KEYS */;
INSERT INTO `coachstrips` VALUES (1,820,'00:00:00',1,2),(2,1610,'00:00:00',1,3),(3,1100,'00:00:00',1,4);
/*!40000 ALTER TABLE `coachstrips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `idCustomer` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` int DEFAULT NULL,
  `addressCus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Nguyễn Thị Hương',984376291,'Hà Nội'),(2,'Trần Văn Hải',986219763,'Bình Thuận'),(3,'Lê Thị Mai',983765431,'Ninh Thuận'),(4,'Phạm Văn Hưng',985432178,'Đà Nẳng'),(5,'Đinh Thị Hạnh',982187439,'Bình Dương'),(6,'Vũ Minh Đức',986547213,'Quảng Nam'),(7,'Hoàng Thị Phương',987654321,'Hà Tĩnh'),(8,'Nguyễn Văn Hùng',982349156,'Bắc Giang'),(9,'Bùi Thị Thu',986541237,'Cao Bằng'),(10,'Lê Văn Tuấn',984444312,'TP.HCM'),(11,'Nguyễn Thị Ánh',982345671,'Nha Trang'),(12,'Vũ Thị Mai',987654132,'Huế'),(13,'Trần Thanh Tùng',984562713,'Tây Ninh'),(14,'Phạm Thị Hạnh',983478562,'Đồng Nai'),(15,'Đinh Văn Đức',985237641,'TP.HCM'),(16,'Lê Văn Anh',983216547,'Gia Lai'),(17,'Nguyễn Thị Ngọc',982134765,'Cà Mau'),(18,'Bùi Văn Hưng',984765321,'Khánh Hòa'),(19,'Lê Thị An',985678432,'Kom Tum'),(20,'Trần Văn Phúc',987644413,'Vũng Tàu'),(21,'Nguyễn Văn Nam',985678921,'Bình Định'),(22,'Trần Thị Trang',983450671,'Hà Nội'),(23,'Phạm Văn Thành',984567219,'Hải Phòng'),(24,'Lê Thị Anh Thư',985670345,'TP.HCM'),(25,'Hoàng Văn Tuấn',987238156,'Đắk Lắk'),(26,'Đặng Thị Hạnh',982356789,'Quảng Ninh'),(27,'Trương Văn Hải',984267315,'Lâm Đồng'),(28,'Lý Thị Lan',986421537,'Đà Nẳng'),(29,'Nguyễn Văn Trung',982133456,'Bình Dương'),(30,'Phan Thị Thu',986782314,'Phú Yên'),(31,'Võ Văn Thắng',987654123,'Khánh Hòa'),(32,'Nguyễn Thị Nhung',985432167,'Quảng Bình'),(33,'Trần Văn Đức',983444462,'Ninh Bình'),(34,'Lê Thị Hoa',982187654,'Bình Phước'),(35,'Nguyễn Văn Tài',984562731,'Hậu Giang'),(36,'Phạm Thị Bích',984447416,'An Giang'),(37,'Đào Văn Tùng',987654312,'Nam Định'),(38,'Hoàng Thị Loan',984444427,'Thanh Hóa'),(39,'Nguyễn Văn Huy',982444615,'Thái Nguyên'),(40,'Trần Thị Hồng',986541273,'Đồng Tháp'),(41,'Lê Văn Phú',983216475,'Vĩnh Long'),(42,'Nguyễn Thị Hải',982134657,'Bà Rịa Vũng Tàu'),(43,'Trương Văn An',983450672,'Hà Nội'),(44,'Phạm Thị Hằng',986541234,'Hải Dương'),(45,'Nguyễn Văn Đạt',987654320,'Hưng Yên'),(46,'Đinh Thị Mai Anh',984444313,'Hà Nam'),(47,'Vũ Thị Hồng',982345670,'Hà Tĩnh'),(48,'Lê Văn Nam',986219761,'Thái Bình');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `nameRoles` varchar(255) NOT NULL,
  PRIMARY KEY (`nameRoles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('Admin'),('Nhân Viên'),('Tài Xế');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `idStaff` int NOT NULL AUTO_INCREMENT,
  `passWord` int DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `addressUser` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `nameStaff` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `brithStaff` date DEFAULT NULL,
  PRIMARY KEY (`idStaff`),
  KEY `idx_roles` (`roles`),
  CONSTRAINT `FK_staff_role` FOREIGN KEY (`roles`) REFERENCES `roles` (`nameRoles`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,12345678,'nva','Hà Nội','Nhân viên','Nguyễn Văn An',25,'Nam',987654321,'2001-01-01'),(2,87654321,'ttb','Hồ Chí Minh','Nhân viên','Trần Thị Bình',30,'Nữ',123456789,'1995-06-30'),(3,13572468,'lvc','Hà Nội','Nhân viên','Lê Văn Cẩn',28,'Nam',987123456,'1997-08-15'),(4,56781234,'ptd','Đà Nẵng','Nhân viên','Phạm Thị Diểm',22,'Nữ',129876543,'2000-02-29'),(5,24681357,'nte','Hải Phòng','Nhân viên','Nguyễn Thành Én',29,'Nam',976543210,'1994-05-20'),(6,13579753,'tvt','Huế','Tài Xế','Trần Văn Thuận',26,'Nam',123456780,'1996-09-12'),(7,75395128,'ltg','Cần Thơ','Tài Xế','Lê Thị Giang',32,'Nữ',908765432,'1990-11-25'),(8,36985214,'pvh','Hà Nội','Tài Xế','Phạm Văn Hoài',27,'Nam',987654321,'1994-07-14'),(9,98765432,'nti','Hải Phòng','Tài Xế','Nguyễn Thị Iến',31,'Nữ',123456789,'1991-12-24'),(10,45678912,'tvk','Hồ Chí Minh','Tài Xế','Trần Văn Kiên',23,'Nam',987123456,'1999-03-07'),(11,123456,'nln','Hà Nội','Tài xế','Nguyễn Lê Nhung',26,'Nữ',987654321,'1995-03-25'),(12,234567,'ptl','Đà Nẵng','Tài xế','Phạm Thanh Lộc',29,'Nam',123456789,'1993-09-17'),(13,345678,'lth','Hà Nội','Tài xế','Lê Thị Hà',28,'Nữ',976543210,'1994-11-23'),(14,456789,'ntn','Hồ Chí Minh','Tài xế','Nguyễn Thị Nhung',30,'Nữ',908765432,'1992-06-10'),(15,567890,'tvn','Hà Nội','Tài xế','Trần Văn Nam',32,'Nam',891234567,'1990-12-31'),(16,868196036,'nnl','Bình Thuận','Admin','Nguyễn Ngọc Luân',21,'Nam',868196036,'2000-11-07');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stations` (
  `idStations` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idStations`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES (1,'Bến xe Miền Đông','TP.Hồ Chí Minh'),(2,'Bến xe Mỹ Đình','Hà Nội'),(3,'Bến xe Nước Ngầm','Vũng Tàu'),(4,'Bến xe Phía Nam','Cần Thơ');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `idStationBuy` int DEFAULT NULL,
  `bookingDate` datetime DEFAULT NULL,
  `idCustomer` int DEFAULT NULL,
  `idStaff` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `idCoachStripCoachSeat` int DEFAULT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `FK_staff_ticket` (`idStaff`),
  KEY `FK__Ticket_Customer` (`idCustomer`),
  KEY `FK_ticket_coachofcoachstrips` (`idCoachStripCoachSeat`),
  KEY `FK_ticket_stations` (`idStationBuy`),
  CONSTRAINT `FK__Ticket_Customer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`),
  CONSTRAINT `FK_ticket_coachofcoachstrips` FOREIGN KEY (`idCoachStripCoachSeat`) REFERENCES `coachstripcoachseat` (`idCSCS`),
  CONSTRAINT `FK_ticket_staff` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`idStaff`),
  CONSTRAINT `FK_ticket_stations` FOREIGN KEY (`idStationBuy`) REFERENCES `stations` (`idStations`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,1,'2023-04-08 03:00:00',1,1,1,1),(2,1,'2023-04-08 03:00:00',2,1,1,2),(3,1,'2023-04-08 03:00:00',3,1,1,3),(4,1,'2023-04-08 03:00:00',4,1,1,4),(5,1,'2023-04-08 03:00:00',5,1,1,5),(6,1,'2023-04-08 03:00:00',6,1,1,6),(7,1,'2023-04-08 03:00:00',7,1,0,7),(8,1,'2023-04-08 03:00:00',8,1,0,8),(9,1,'2023-04-08 13:00:00',9,1,1,11),(10,1,'2023-04-08 13:00:00',10,1,1,12),(11,1,'2023-04-08 13:00:00',11,1,1,13),(12,1,'2023-04-08 13:00:00',12,1,1,14),(13,1,'2023-04-08 13:00:00',13,1,1,15),(14,1,'2023-04-08 13:00:00',14,1,1,16),(15,1,'2023-04-08 13:00:00',15,1,0,17),(16,1,'2023-04-08 13:00:00',16,1,0,18),(17,1,'2023-04-08 05:00:00',17,1,1,21),(18,1,'2023-04-08 05:00:00',18,1,1,22),(19,1,'2023-04-08 05:00:00',19,1,1,23),(20,1,'2023-04-08 05:00:00',20,1,1,24),(21,1,'2023-04-08 05:00:00',21,1,1,25),(22,1,'2023-04-08 05:00:00',22,1,1,26),(23,1,'2023-04-08 05:00:00',23,1,0,27),(24,1,'2023-04-08 05:00:00',24,1,0,28),(25,1,'2023-04-08 17:00:00',25,1,1,31),(26,1,'2023-04-08 17:00:00',26,1,1,32),(27,1,'2023-04-08 17:00:00',27,1,1,33),(28,1,'2023-04-08 17:00:00',28,1,1,34),(29,1,'2023-04-08 17:00:00',29,1,1,35),(30,1,'2023-04-08 17:00:00',30,1,1,36),(31,1,'2023-04-08 17:00:00',31,1,0,37),(32,1,'2023-04-08 17:00:00',32,1,0,38),(33,1,'2023-04-08 08:00:00',33,1,1,41),(34,1,'2023-04-08 08:00:00',34,1,1,42),(35,1,'2023-04-08 08:00:00',35,1,1,43),(36,1,'2023-04-08 08:00:00',36,1,1,44),(37,1,'2023-04-08 08:00:00',37,1,1,45),(38,1,'2023-04-08 08:00:00',38,1,1,46),(39,1,'2023-04-08 08:00:00',39,1,0,47),(40,1,'2023-04-08 08:00:00',40,1,0,48),(41,1,'2023-04-08 20:00:00',41,1,1,51),(42,1,'2023-04-08 20:00:00',42,1,1,52),(43,1,'2023-04-08 20:00:00',43,1,1,53),(44,1,'2023-04-08 20:00:00',44,1,1,54),(45,1,'2023-04-08 20:00:00',45,1,1,55),(46,1,'2023-04-08 20:00:00',46,1,1,56),(47,1,'2023-04-08 20:00:00',47,1,0,57),(48,1,'2023-04-08 20:00:00',48,1,0,58),(49,1,'2023-04-09 03:00:00',1,2,1,61),(50,1,'2023-04-09 03:00:00',2,2,1,62),(51,1,'2023-04-09 03:00:00',3,2,1,63),(52,1,'2023-04-09 03:00:00',4,2,1,64),(53,1,'2023-04-09 03:00:00',5,2,1,65),(54,1,'2023-04-09 03:00:00',6,2,1,66),(55,1,'2023-04-09 03:00:00',7,2,0,67),(56,1,'2023-04-09 03:00:00',8,2,0,68),(57,1,'2023-04-09 03:00:00',9,2,1,71),(58,1,'2023-04-09 03:00:00',10,2,1,72),(59,1,'2023-04-09 03:00:00',11,2,1,73),(60,1,'2023-04-09 03:00:00',12,2,1,74),(61,1,'2023-04-09 03:00:00',13,2,1,75),(62,1,'2023-04-09 03:00:00',14,2,1,76),(63,1,'2023-04-09 03:00:00',15,2,0,77),(64,1,'2023-04-09 03:00:00',16,2,0,78),(65,1,'2023-04-09 05:00:00',17,2,1,81),(66,1,'2023-04-09 05:00:00',18,2,1,82),(67,1,'2023-04-09 05:00:00',19,2,1,83),(68,1,'2023-04-09 05:00:00',20,2,1,84),(69,1,'2023-04-09 05:00:00',21,2,1,85),(70,1,'2023-04-09 05:00:00',22,2,1,86),(71,1,'2023-04-09 05:00:00',23,2,0,87),(72,1,'2023-04-09 05:00:00',24,2,0,88),(73,1,'2023-04-09 17:00:00',25,2,1,91),(74,1,'2023-04-09 17:00:00',26,2,1,92),(75,1,'2023-04-09 17:00:00',27,2,1,93),(76,1,'2023-04-09 17:00:00',28,2,1,94),(77,1,'2023-04-09 17:00:00',29,2,1,95),(78,1,'2023-04-09 17:00:00',30,2,1,96),(79,1,'2023-04-09 17:00:00',31,2,0,97),(80,1,'2023-04-09 17:00:00',32,2,0,98),(81,1,'2023-04-09 08:00:00',33,2,1,101),(82,1,'2023-04-09 08:00:00',34,2,1,102),(83,1,'2023-04-09 08:00:00',35,2,1,103),(84,1,'2023-04-09 08:00:00',36,2,1,104),(85,1,'2023-04-09 08:00:00',37,2,1,105),(86,1,'2023-04-09 08:00:00',38,2,1,106),(87,1,'2023-04-09 08:00:00',39,2,0,107),(88,1,'2023-04-09 08:00:00',40,2,0,108),(89,1,'2023-04-09 20:00:00',41,2,1,111),(90,1,'2023-04-09 20:00:00',42,2,1,112),(91,1,'2023-04-09 20:00:00',43,2,1,113),(92,1,'2023-04-09 20:00:00',44,2,1,114),(93,1,'2023-04-09 20:00:00',45,2,1,115),(94,1,'2023-04-09 20:00:00',46,2,1,116),(95,1,'2023-04-09 20:00:00',47,2,0,117),(96,1,'2023-04-09 20:00:00',48,2,0,118),(97,1,'2023-04-10 03:00:00',1,3,1,121),(98,1,'2023-04-10 03:00:00',2,3,1,122),(99,1,'2023-04-10 03:00:00',3,3,1,123),(100,1,'2023-04-10 03:00:00',4,3,1,124),(101,1,'2023-04-10 03:00:00',5,3,1,125),(102,1,'2023-04-10 03:00:00',6,3,1,126),(103,1,'2023-04-10 03:00:00',7,3,0,127),(104,1,'2023-04-10 03:00:00',8,3,0,128),(105,1,'2023-04-10 13:00:00',9,3,1,131),(106,1,'2023-04-10 13:00:00',10,3,1,132),(107,1,'2023-04-10 13:00:00',11,3,1,133),(108,1,'2023-04-10 13:00:00',12,3,1,134),(109,1,'2023-04-10 13:00:00',13,3,1,135),(110,1,'2023-04-10 13:00:00',14,3,1,136),(111,1,'2023-04-10 13:00:00',15,3,0,137),(112,1,'2023-04-10 13:00:00',16,3,0,138),(113,1,'2023-04-10 05:00:00',17,3,1,141),(114,1,'2023-04-10 05:00:00',18,3,1,142),(115,1,'2023-04-10 05:00:00',19,3,1,143),(116,1,'2023-04-10 05:00:00',20,3,1,144),(117,1,'2023-04-10 05:00:00',21,3,1,145),(118,1,'2023-04-10 05:00:00',22,3,1,146),(119,1,'2023-04-10 05:00:00',23,3,0,147),(120,1,'2023-04-10 05:00:00',24,3,0,148),(121,1,'2023-04-10 17:00:00',25,3,1,151),(122,1,'2023-04-10 17:00:00',26,3,1,152),(123,1,'2023-04-10 17:00:00',27,3,1,153),(124,1,'2023-04-10 17:00:00',28,3,1,154),(125,1,'2023-04-10 17:00:00',29,3,1,155),(126,1,'2023-04-10 17:00:00',30,3,1,156),(127,1,'2023-04-10 17:00:00',31,3,0,157),(128,1,'2023-04-10 17:00:00',32,3,0,158),(129,1,'2023-04-10 08:00:00',33,3,1,161),(130,1,'2023-04-10 08:00:00',34,3,1,162),(131,1,'2023-04-10 08:00:00',35,3,1,163),(132,1,'2023-04-10 08:00:00',36,3,1,164),(133,1,'2023-04-10 08:00:00',37,3,1,165),(134,1,'2023-04-10 08:00:00',38,3,1,166),(135,1,'2023-04-10 08:00:00',39,3,0,167),(136,1,'2023-04-10 08:00:00',40,3,0,168),(137,1,'2023-04-10 20:00:00',41,3,1,171),(138,1,'2023-04-10 20:00:00',42,3,1,172),(139,1,'2023-04-10 20:00:00',43,3,1,173),(140,1,'2023-04-10 20:00:00',44,3,1,174),(141,1,'2023-04-10 20:00:00',45,3,1,175),(142,1,'2023-04-10 20:00:00',46,3,1,176),(143,1,'2023-04-10 20:00:00',47,3,0,177),(144,1,'2023-04-10 20:00:00',48,3,0,178);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeofcoach`
--

DROP TABLE IF EXISTS `typeofcoach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeofcoach` (
  `nameTypeOfCoach` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`nameTypeOfCoach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeofcoach`
--

LOCK TABLES `typeofcoach` WRITE;
/*!40000 ALTER TABLE `typeofcoach` DISABLE KEYS */;
INSERT INTO `typeofcoach` VALUES ('Xe Giường Nằm'),('Xe Khách');
/*!40000 ALTER TABLE `typeofcoach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-09  0:23:46
