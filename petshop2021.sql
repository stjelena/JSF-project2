CREATE DATABASE  IF NOT EXISTS `petshop2021` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `petshop2021`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: petshop2021
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `komentari`
--

DROP TABLE IF EXISTS `komentari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `komentari` (
  `idK` int(11) NOT NULL AUTO_INCREMENT,
  `proizvod` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `tekst` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `korisnik` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idK`),
  KEY `kom` (`proizvod`),
  KEY `kor` (`korisnik`),
  CONSTRAINT `kom` FOREIGN KEY (`proizvod`) REFERENCES `proizvodi` (`naziv`),
  CONSTRAINT `kor` FOREIGN KEY (`korisnik`) REFERENCES `korisnici` (`kor_ime`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentari`
--

INSERT INTO `komentari` VALUES (1,'whiskas adult','Sjajno','2021-11-20','lui','Neobradjeno'),(2,'whiskas young','Ok','2021-11-24','novak','Prihvaceno'),(3,'pedigree adult','Nije lose','2021-11-13','novak','Prihvaceno');

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnici` (
  `kor_ime` varchar(45) COLLATE utf8_bin NOT NULL,
  `ime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prezime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mejl` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `tip` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `lozinka` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`kor_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` VALUES ('ana','Ana','Ivanovic','anchi@gmail.com','1987-11-06','kupac','ana123'),('lui','Luis','Hamilton','l@gmail.com','1985-01-07','kupac','lui123'),('maks','Maks','Verstapen','maks@gmail.com','1997-09-30','prodavac','maks123'),('novak','Novak','Djokovic','nole@gmail.com','1987-05-22','kupac','nole123'),('valt','Valteri','Botas','valt@gmail.com','1989-08-28','prodavac','valt123');

--
-- Table structure for table `narudzbinasadrzi`
--

DROP TABLE IF EXISTS `narudzbinasadrzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `narudzbinasadrzi` (
  `narudzbina` int(11) NOT NULL,
  `proizvod` varchar(45) COLLATE utf8_bin NOT NULL,
  `kolicina` int(11) DEFAULT NULL,
  PRIMARY KEY (`narudzbina`,`proizvod`),
  KEY `pro` (`proizvod`),
  CONSTRAINT `nar` FOREIGN KEY (`narudzbina`) REFERENCES `narudzbine` (`idN`),
  CONSTRAINT `pro` FOREIGN KEY (`proizvod`) REFERENCES `proizvodi` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbinasadrzi`
--

INSERT INTO `narudzbinasadrzi` VALUES (1,'pedigree adult',2),(2,'pedigree adult',1),(2,'pedigree young',3),(3,'whiskas adult',1);

--
-- Table structure for table `narudzbine`
--

DROP TABLE IF EXISTS `narudzbine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `narudzbine` (
  `idN` int(11) NOT NULL AUTO_INCREMENT,
  `kupac` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`idN`),
  KEY `korisnik` (`kupac`),
  CONSTRAINT `korisnik` FOREIGN KEY (`kupac`) REFERENCES `korisnici` (`kor_ime`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbine`
--

INSERT INTO `narudzbine` VALUES (1,'ana','2021-11-25'),(2,'ana','2021-11-26'),(3,'lui','2021-11-20');

--
-- Table structure for table `proizvodi`
--

DROP TABLE IF EXISTS `proizvodi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proizvodi` (
  `naziv` varchar(45) COLLATE utf8_bin NOT NULL,
  `opis` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  `promocija` int(11) DEFAULT NULL,
  PRIMARY KEY (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvodi`
--

INSERT INTO `proizvodi` VALUES ('pedigree adult','Hrana za starije pse',1000,1),('pedigree young','Hrana za stence',1200,0),('whiskas adult','Hrana za odrasle macke',800,0),('whiskas young','Hrana za macice',1000,1);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed