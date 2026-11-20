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
  `superficieBarrio` int NOT NULL,
  `cantidadEdificiosBarrio` int DEFAULT NULL,
  `porcentajeAsfaltoBarrio` varchar(45) DEFAULT NULL,
  `numeroLeyCabaBarrio` varchar(45) DEFAULT NULL,
  `conexionRedAguaBarrio` bit(1) DEFAULT NULL,
  `cantidadMonoblocksBarrio` int DEFAULT NULL,
  `consorcioAdministracionBarrio` bit(1) DEFAULT NULL,
  `idComuna` int NOT NULL,
  `idTipoBarrio` int NOT NULL,
  PRIMARY KEY (`idBarrio`),
  KEY `idComuna_idx` (`idComuna`),
  KEY `idTipoBarrio_idx` (`idTipoBarrio`),
  CONSTRAINT `idComuna` FOREIGN KEY (`idComuna`) REFERENCES `comuna` (`idComuna`),
  CONSTRAINT `idTipoBarrio` FOREIGN KEY (`idTipoBarrio`) REFERENCES `tipobarrio` (`idtipoBarrio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrio`
--

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
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
  PRIMARY KEY (`idComuna`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
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
  `tipoSiutacionLaboral` int NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `dniPersona_UNIQUE` (`dniPersona`),
  KEY `idBarrio_idx` (`idBarrio`),
  KEY `tipoSituacionLaboral_idx` (`tipoSiutacionLaboral`),
  CONSTRAINT `idBarrio` FOREIGN KEY (`idBarrio`) REFERENCES `barrio` (`idBarrio`),
  CONSTRAINT `tipoSituacionLaboral` FOREIGN KEY (`tipoSiutacionLaboral`) REFERENCES `tiposituacionlaboral` (`idtipoSituacionLaboral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
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
  `idtipoSituacionLaboral` int NOT NULL AUTO_INCREMENT,
  `nombreTipoSituacionLaboral` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoSituacionLaboral`)
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

-- Dump completed on 2026-11-15 22:28:53
