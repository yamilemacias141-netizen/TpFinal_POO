-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: tp_final
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `barrio`
--

DROP TABLE IF EXISTS `barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barrio` (
  `idBarrio` int NOT NULL AUTO_INCREMENT,
  `nombreBarrio` varchar(45) NOT NULL,
  `superficieEnHectarias` float NOT NULL,
  `cantidadEdificiosBarrio` int DEFAULT NULL,
  `porcentajeAsfaltoBarrio` decimal(5,2) DEFAULT NULL,
  `numeroLeyCabaBarrio` varchar(45) DEFAULT NULL,
  `conexionRedAguaBarrio` bit(1) DEFAULT NULL,
  `cantidadMonoblocksBarrio` int DEFAULT NULL,
  `consorcioAdministracionBarrio` bit(1) DEFAULT NULL,
  `idComuna` int NOT NULL,
  `idTipoBarrio` int NOT NULL,
  PRIMARY KEY (`idBarrio`),
  UNIQUE KEY `nombreBarrio_UNIQUE` (`nombreBarrio`),
  KEY `idComuna_idx` (`idComuna`),
  KEY `idTipoBarrio_idx` (`idTipoBarrio`),
  CONSTRAINT `idComuna` FOREIGN KEY (`idComuna`) REFERENCES `comuna` (`idComuna`),
  CONSTRAINT `idTipoBarrio` FOREIGN KEY (`idTipoBarrio`) REFERENCES `tipobarrio` (`idtipoBarrio`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrio`
--

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
INSERT INTO `barrio` VALUES (14,'sada',1,NULL,NULL,'sa',_binary '',NULL,NULL,1,2),(19,'sadsa',17,NULL,NULL,'sadadsa',_binary '',NULL,NULL,1,2),(20,'santy',17,NULL,NULL,'sasada',_binary '',NULL,NULL,1,2);
/*!40000 ALTER TABLE `barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comuna` (
  `idComuna` int NOT NULL AUTO_INCREMENT,
  `nombreSedeComuna` varchar(45) NOT NULL,
  `numeroComuna` varchar(45) NOT NULL,
  PRIMARY KEY (`idComuna`),
  UNIQUE KEY `numeroComuna_UNIQUE` (`numeroComuna`),
  UNIQUE KEY `nombreSedeComuna_UNIQUE` (`nombreSedeComuna`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
INSERT INTO `comuna` VALUES (1,'Flores','1'),(36,'Sede 67','67');
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idpersona` int NOT NULL AUTO_INCREMENT,
  `nombrePersona` varchar(45) NOT NULL,
  `fechaNacimientoPersona` date NOT NULL,
  `dniPersona` varchar(8) NOT NULL,
  `idBarrio` int NOT NULL,
  `idTipoSituacionLaboral` int NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `dniPersona_UNIQUE` (`dniPersona`),
  KEY `idBarrio_idx` (`idBarrio`),
  KEY `tipoSituacionLaboral_idx` (`idTipoSituacionLaboral`),
  CONSTRAINT `idBarrio` FOREIGN KEY (`idBarrio`) REFERENCES `barrio` (`idBarrio`),
  CONSTRAINT `tipoSituacionLaboral` FOREIGN KEY (`idTipoSituacionLaboral`) REFERENCES `tiposituacionlaboral` (`idTipoSituacionLaboral`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (4,'asaa','2026-01-01','8',19,1),(5,'santyflo','2007-01-01','5',19,1),(6,'s','1999-04-08','6',19,3);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipobarrio`
--

DROP TABLE IF EXISTS `tipobarrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipobarrio` (
  `idtipoBarrio` int NOT NULL AUTO_INCREMENT,
  `nombreTipoBarrio` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoBarrio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipobarrio`
--

LOCK TABLES `tipobarrio` WRITE;
/*!40000 ALTER TABLE `tipobarrio` DISABLE KEYS */;
INSERT INTO `tipobarrio` VALUES (1,'Trazado formal'),(2,'Urbanizacion planificada'),(3,'Complejo habitacional');
/*!40000 ALTER TABLE `tipobarrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposituacionlaboral`
--

DROP TABLE IF EXISTS `tiposituacionlaboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiposituacionlaboral` (
  `idTipoSituacionLaboral` int NOT NULL AUTO_INCREMENT,
  `nombreTipoSituacionLaboral` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoSituacionLaboral`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposituacionlaboral`
--

LOCK TABLES `tiposituacionlaboral` WRITE;
/*!40000 ALTER TABLE `tiposituacionlaboral` DISABLE KEYS */;
INSERT INTO `tiposituacionlaboral` VALUES (1,'Ocupado'),(2,'Desocupado'),(3,'Subocupado');
/*!40000 ALTER TABLE `tiposituacionlaboral` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-24  2:52:36
