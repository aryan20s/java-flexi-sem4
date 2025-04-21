-- MySQL dump 10.13  Distrib 9.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: movies2
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actors` (
  `PersonID` int NOT NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `actors_ibfk_1` FOREIGN KEY (`PersonID`) REFERENCES `persons` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (5),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20);
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actsin`
--

DROP TABLE IF EXISTS `actsin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actsin` (
  `MovieID` int NOT NULL,
  `ActorID` int NOT NULL,
  `CharacterPlayed` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MovieID`,`ActorID`),
  KEY `ActorID` (`ActorID`),
  CONSTRAINT `actsin_ibfk_1` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`MovieID`),
  CONSTRAINT `actsin_ibfk_2` FOREIGN KEY (`ActorID`) REFERENCES `actors` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actsin`
--

LOCK TABLES `actsin` WRITE;
/*!40000 ALTER TABLE `actsin` DISABLE KEYS */;
INSERT INTO `actsin` VALUES (1,10,'Royal Tenenbaum'),(1,11,'Margot Tenenbaum'),(1,12,'Richie Tenenbaum'),(2,5,'Ned Plimpton'),(2,13,'Steve Zissou'),(2,14,'Jane Winslett-Richardson'),(3,15,'Dom Cobb'),(3,16,'Arthur'),(3,17,'Eames'),(4,16,'John Blake'),(4,17,'Bane'),(4,18,'Bruce Wayne / Batman'),(5,17,'Farrier'),(5,19,'Shivering Soldier'),(5,20,'Tommy');
/*!40000 ALTER TABLE `actsin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `BookingID` int NOT NULL,
  `ShowID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `ShowID` (`ShowID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`ShowID`) REFERENCES `shows` (`ShowID`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `users` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviereview`
--

DROP TABLE IF EXISTS `moviereview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviereview` (
  `UserID` int NOT NULL,
  `MovieID` int NOT NULL,
  `MovieScore` decimal(3,1) DEFAULT NULL,
  `ReviewText` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`,`MovieID`),
  KEY `MovieID` (`MovieID`),
  CONSTRAINT `moviereview_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`PersonID`),
  CONSTRAINT `moviereview_ibfk_2` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`MovieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviereview`
--

LOCK TABLES `moviereview` WRITE;
/*!40000 ALTER TABLE `moviereview` DISABLE KEYS */;
INSERT INTO `moviereview` VALUES (1,1,8.5,'Quirky and heartfelt, a true Wes Anderson classic!'),(1,2,7.0,NULL),(1,3,9.5,NULL),(1,4,8.8,'Epic conclusion, though not as sharp as the previous.'),(1,5,8.0,NULL),(2,1,7.8,NULL),(2,2,8.3,'Visually stunning but a bit meandering.'),(2,3,9.0,NULL),(2,4,8.5,NULL),(2,5,8.8,'Intense and immersive wartime experience.'),(3,1,9.0,NULL),(3,2,6.5,NULL),(3,3,9.8,'Mind-bending masterpiece with incredible visuals.'),(3,4,9.3,NULL),(3,5,9.0,NULL);
/*!40000 ALTER TABLE `moviereview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `MovieID` int NOT NULL,
  `Moviename` varchar(100) DEFAULT NULL,
  `Genre` varchar(50) DEFAULT NULL,
  `Duration` int DEFAULT NULL,
  `CountryOfOrigin` varchar(50) DEFAULT NULL,
  `ReleaseDate` date DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MovieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'The Royal Tenenbaums','Comedy-Drama',110,'USA','2001-12-14','An eccentric family of former child prodigies reunites when their father announces he has a terminal illness. Directed by Wes Anderson.'),(2,'The Life Aquatic with Steve Zissou','Adventure-Comedy',119,'USA','2004-12-25','Oceanographer Steve Zissou seeks revenge on the \"jaguar shark\" that ate his partner, while documenting his adventures. Wes Anderson\'s aquatic adventure.'),(3,'Inception','Science Fiction',148,'USA','2010-07-16','A thief who enters the subconscious of targets through dreams is tasked with planting an idea in someone\'s mind. Christopher Nolan\'s mind-bending thriller.'),(4,'The Dark Knight Rises','Action',165,'USA','2012-07-20','Eight years after Batman\'s disappearance, Bruce Wayne faces Bane\'s terrorist threat to Gotham City in Nolan\'s epic conclusion to the Dark Knight trilogy.'),(5,'Dunkirk','War/Drama',106,'UK','2017-07-21','Allied soldiers from Belgium, Britain, and France are surrounded by the German army during WWII\'s Dunkirk evacuation. Nolan\'s intense war drama.');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviestaff`
--

DROP TABLE IF EXISTS `moviestaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviestaff` (
  `PersonID` int NOT NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `moviestaff_ibfk_1` FOREIGN KEY (`PersonID`) REFERENCES `persons` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviestaff`
--

LOCK TABLES `moviestaff` WRITE;
/*!40000 ALTER TABLE `moviestaff` DISABLE KEYS */;
INSERT INTO `moviestaff` VALUES (4),(5),(6),(7),(8),(9);
/*!40000 ALTER TABLE `moviestaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persons` (
  `PersonID` int NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'Tom Brown','1990-04-10'),(2,'Amy Lee','1987-09-22'),(3,'Jake Miller','1995-12-05'),(4,'Wes Anderson','1969-05-01'),(5,'Owen Wilson','1968-11-18'),(6,'Noah Baumbach','1969-09-03'),(7,'Christopher Nolan','1970-07-30'),(8,'Emma Thomas','1971-12-09'),(9,'Hans Zimmer','1957-09-12'),(10,'Gene Hackman','1930-01-30'),(11,'Gwyneth Paltrow','1972-09-27'),(12,'Luke Wilson','1971-09-21'),(13,'Bill Murray','1950-09-21'),(14,'Cate Blanchett','1969-05-14'),(15,'Leonardo DiCaprio','1974-11-11'),(16,'Joseph Gordon-Levitt','1981-02-17'),(17,'Tom Hardy','1977-09-15'),(18,'Christian Bale','1974-01-30'),(19,'Cillian Murphy','1976-05-25'),(20,'Fionn Whitehead','1997-07-18'),(21,'ABC DEF','2005-01-01');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shows` (
  `ShowID` int NOT NULL,
  `Starttime` time DEFAULT NULL,
  `Showdate` date DEFAULT NULL,
  `Price` decimal(5,2) DEFAULT NULL,
  `TheatreID` int DEFAULT NULL,
  `MovieID` int DEFAULT NULL,
  PRIMARY KEY (`ShowID`),
  KEY `TheatreID` (`TheatreID`),
  KEY `MovieID` (`MovieID`),
  CONSTRAINT `shows_ibfk_1` FOREIGN KEY (`TheatreID`) REFERENCES `theatres` (`TheatreID`),
  CONSTRAINT `shows_ibfk_2` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`MovieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (1,'10:00:00','2025-04-26',12.50,1,1),(2,'14:00:00','2025-04-26',14.00,1,2),(3,'16:00:00','2025-04-27',16.00,1,3),(4,'19:00:00','2025-04-28',18.00,1,4),(5,'22:00:00','2025-04-29',20.00,1,5),(6,'10:00:00','2025-04-30',12.50,1,1),(7,'14:00:00','2025-05-01',14.00,1,2),(8,'15:30:00','2025-04-26',15.00,2,3),(9,'18:30:00','2025-04-27',17.50,2,4),(10,'11:00:00','2025-04-28',13.00,2,5),(11,'20:00:00','2025-04-29',19.00,2,1),(12,'13:30:00','2025-04-30',14.50,2,2),(13,'16:00:00','2025-05-01',16.00,2,3),(14,'19:30:00','2025-05-02',18.50,2,4),(15,'12:00:00','2025-04-26',12.00,3,5),(16,'15:00:00','2025-04-27',15.00,3,1),(17,'18:00:00','2025-04-28',17.00,3,2),(18,'21:00:00','2025-04-29',20.00,3,3),(19,'10:30:00','2025-04-30',11.50,3,4),(20,'14:00:00','2025-05-01',14.00,3,5);
/*!40000 ALTER TABLE `shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatres`
--

DROP TABLE IF EXISTS `theatres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatres` (
  `TheatreID` int NOT NULL,
  `TheatreName` varchar(100) DEFAULT NULL,
  `TheatreAddress` varchar(200) DEFAULT NULL,
  `NumOfSeats` int DEFAULT NULL,
  PRIMARY KEY (`TheatreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatres`
--

LOCK TABLES `theatres` WRITE;
/*!40000 ALTER TABLE `theatres` DISABLE KEYS */;
INSERT INTO `theatres` VALUES (1,'Cineplex Downtown','123 Main St, Cityville',150),(2,'Metro Cinema','456 Elm Ave, Townsville',200),(3,'Starlight Theater','789 Oak Blvd, Villagetown',250);
/*!40000 ALTER TABLE `theatres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `PersonID` int NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PersonID`),
  UNIQUE KEY `Username` (`Username`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`PersonID`) REFERENCES `persons` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tombrown90','123'),(2,'amylee87','123'),(3,'jakem95','123'),(21,'user','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workedon`
--

DROP TABLE IF EXISTS `workedon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workedon` (
  `MovieID` int NOT NULL,
  `MovieStaffID` int NOT NULL,
  `RoleInProduction` varchar(100) NOT NULL,
  PRIMARY KEY (`MovieID`,`MovieStaffID`,`RoleInProduction`),
  KEY `MovieStaffID` (`MovieStaffID`),
  CONSTRAINT `workedon_ibfk_1` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`MovieID`),
  CONSTRAINT `workedon_ibfk_2` FOREIGN KEY (`MovieStaffID`) REFERENCES `moviestaff` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workedon`
--

LOCK TABLES `workedon` WRITE;
/*!40000 ALTER TABLE `workedon` DISABLE KEYS */;
INSERT INTO `workedon` VALUES (1,4,'Director'),(1,4,'Writer'),(2,4,'Director'),(2,4,'Writer'),(1,5,'Co-Writer'),(2,6,'Co-Writer'),(3,7,'Director'),(3,7,'Writer'),(4,7,'Director'),(4,7,'Writer'),(5,7,'Director'),(5,7,'Writer'),(3,8,'Producer'),(4,8,'Producer'),(5,8,'Producer'),(3,9,'Composer'),(4,9,'Composer'),(5,9,'Composer');
/*!40000 ALTER TABLE `workedon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-21 23:31:23
