-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gadgetbadgetsys
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` varchar(5) NOT NULL,
  `productName` varchar(25) NOT NULL,
  `category` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `unitPrice` float NOT NULL,
  `rID` varchar(5) NOT NULL,
  PRIMARY KEY (`productID`),
  KEY `rID_idx` (`rID`),
  CONSTRAINT `rID` FOREIGN KEY (`rID`) REFERENCES `research` (`rID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('p0001','sun crackers','biscuits','this a food item',50,'r0002'),('p0002','windy Solor','Cooling fan','Electrical item',3500,'r0005'),('p0004','plastic Slippers','Slippers','description 1',1200,'r0001'),('p0005','Tube light','science','Working great',457.85,'r0001'),('p0006','door','wood','description 3',45000,'r0004');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `research`
--

DROP TABLE IF EXISTS `research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `research` (
  `rID` varchar(5) NOT NULL,
  `field` varchar(45) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `fundTotal` float NOT NULL,
  `publishedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cmpl_stats` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `research`
--

LOCK TABLES `research` WRITE;
/*!40000 ALTER TABLE `research` DISABLE KEYS */;
INSERT INTO `research` VALUES ('r0001','cdf','abcd',1230,'2021-04-22 00:51:39','completed'),('r0002','covid19','science',444444,'2021-04-22 00:33:18','completed'),('r0003','food','Food Science',25000,'2021-04-22 00:33:18','completed'),('r0004','perfume','cosmatics',4500,'2021-04-22 00:36:40','completed'),('r0005','bottle','plastic',350,'2021-04-22 00:37:56','completed');
/*!40000 ALTER TABLE `research` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-22  1:34:57
