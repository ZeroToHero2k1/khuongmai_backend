-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: khuongmaiapp
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `category_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('01ba55b7-31f8-4363-b2c2-b03f105dad83','Quần short'),('ecce4da1-249c-4b96-8af5-fda1d480dd5e','Áo polo');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `company_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `address` text COLLATE utf8mb3_unicode_ci,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('717e7567-7dad-4dd9-a2d0-1ff855b86b27','To Gia Kiet','CGV Cinema','conchocon123@gmail.com','0972099318','72 ham tu p2 q2 tphcm','2025-06-12 10:16:34'),('a44d8107-a0c6-481c-9394-50c43f3ef84a','calvin klevin','Thuan Kieu Cinema','conchocon123@gmail.com','0972099318','72 ham tu p2 q2 tphcm','2025-06-12 10:14:14');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `department_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('2c57c018-8c5a-48a2-a45d-f9361e297a43','DONNA'),('576260f9-c1ea-40c9-a6d3-cf8a01a6907b','500 Khương Mai'),('b9ee9a1e-e870-4e1b-aa8b-57384001749b','472 xưởng cắt'),('f6a97a01-861d-401c-b50c-ffc0e858b89e','Kho378');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `employee_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `employee_phone` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `department_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `date_joined` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('35c1e74f-9aba-4d86-9174-27fda6fb84e0','Trần Thị Hoàng Anh','0868029303','f6a97a01-861d-401c-b50c-ffc0e858b89e','2024-05-09',1),('4072b130-26bb-43a1-867b-cf25311853b6','anh Tòng','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('4a7d8f49-ce0a-48dc-97ec-5ad04d519377','cô Phượng lầu 3','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('50336d3a-a15b-4d73-adfd-20b7d230f156','cô Phương tóc xù','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('75a00ba9-bf21-4283-82aa-94c1dbea1bb4','Quang','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',0),('811f6441-8507-4759-a032-069d764b9a8d','anh đầu hói','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('a0c7dd2f-9a57-482f-a6d4-c05f55762fe9','anh tập gym','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('a85b5f40-a2bd-4a55-81de-4b0c5cd1850a','Tô Quý Phước','0777961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-12-25',1),('c6914ab8-90f3-407c-8802-cf03af5749ec','anh Hải','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1),('ddddc242-043f-4a3b-84f4-7989b53823a0','cô Phượng lầu 1','0727961748','f6a97a01-861d-401c-b50c-ffc0e858b89e','2023-07-25',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invalidated_tokens`
--

DROP TABLE IF EXISTS `invalidated_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invalidated_tokens` (
  `id` varchar(64) COLLATE utf8mb3_unicode_ci NOT NULL,
  `expiry_time` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invalidated_tokens`
--

LOCK TABLES `invalidated_tokens` WRITE;
/*!40000 ALTER TABLE `invalidated_tokens` DISABLE KEYS */;
INSERT INTO `invalidated_tokens` VALUES ('21976ef1-e858-47a5-ac99-9ae99a46a7e9','2025-06-26 17:09:12.000000'),('4c816a51-1b1b-4cb4-8ab9-1ac9cd318d82','2025-06-26 17:12:15.000000'),('60e92ba8-dcb2-425b-a7ff-2210d00bc622','2025-06-26 17:15:39.000000');
/*!40000 ALTER TABLE `invalidated_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `product_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `warehouse_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity_in_stock` int DEFAULT '0',
  `last_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`,`warehouse_id`),
  KEY `warehouse_id` (`warehouse_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('1e75c3e7-da31-40cd-91fa-2572255bec9b','36007d9f-bb88-44da-8a38-7dfc168ab34e',489,'2025-07-02 10:01:50'),('1e75c3e7-da31-40cd-91fa-2572255bec9b','4b7bd4d5-0bb3-42fe-bf60-79debdbde352',289,'2025-07-02 10:01:50'),('ad1923a7-eb69-47ac-b25e-3caf54de5b40','4b7bd4d5-0bb3-42fe-bf60-79debdbde352',1,'2025-07-02 09:52:22'),('fa4ae3db-739d-4a2f-8ccb-4fe342ce4bcf','36007d9f-bb88-44da-8a38-7dfc168ab34e',1,'2025-07-02 09:48:55');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `material_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity_in_stock` int DEFAULT '0',
  `unit` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `supplier` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES ('cdf3bb44-c024-4480-a2b2-f64317c73a0c','kim 3m',0,'cây',120000.00,'anh Đen','để may đồ đấy anh bạn');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_image`
--

DROP TABLE IF EXISTS `material_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `material_id` varchar(36) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `fk_material` (`material_id`),
  CONSTRAINT `fk_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_image`
--

LOCK TABLES `material_image` WRITE;
/*!40000 ALTER TABLE `material_image` DISABLE KEYS */;
INSERT INTO `material_image` VALUES (4,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749894784/khuongmaiimg/blzerxbbxsvesagjnry4.jpg','cdf3bb44-c024-4480-a2b2-f64317c73a0c');
/*!40000 ALTER TABLE `material_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `customer_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `status_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `order_date` date DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `total_amount` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('4b79a825-5813-4182-8e57-71bf5f21c5b6','a44d8107-a0c6-481c-9394-50c43f3ef84a','71e44a55-e21e-464c-8403-0f015ed05dda','2025-07-02','2025-07-05',480000.00),('d3ca5019-95a9-4459-b989-4de38865f8f1','717e7567-7dad-4dd9-a2d0-1ff855b86b27','71e44a55-e21e-464c-8403-0f015ed05dda','2025-07-02','2025-07-05',1800000.00);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `order_detail_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `order_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `product_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  `warehouse_warehouse_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  KEY `FKri4n44544irkslirbkprrciur` (`warehouse_warehouse_id`),
  CONSTRAINT `FKri4n44544irkslirbkprrciur` FOREIGN KEY (`warehouse_warehouse_id`) REFERENCES `warehouse` (`warehouse_id`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES ('0397b31a-4357-4db9-8474-89d92ec54194','d3ca5019-95a9-4459-b989-4de38865f8f1','1e75c3e7-da31-40cd-91fa-2572255bec9b',14,120000.00,1680000.00,'36007d9f-bb88-44da-8a38-7dfc168ab34e'),('4c9a6b49-e58b-4365-8eb4-7553c46368e6','d3ca5019-95a9-4459-b989-4de38865f8f1','ad1923a7-eb69-47ac-b25e-3caf54de5b40',1,120000.00,120000.00,'4b7bd4d5-0bb3-42fe-bf60-79debdbde352'),('75516ec1-f356-4dba-a8eb-81e13510c100','4b79a825-5813-4182-8e57-71bf5f21c5b6','1e75c3e7-da31-40cd-91fa-2572255bec9b',2,120000.00,240000.00,'36007d9f-bb88-44da-8a38-7dfc168ab34e'),('d5943e3b-5dbf-4577-b1f3-76d59f6a2682','4b79a825-5813-4182-8e57-71bf5f21c5b6','1e75c3e7-da31-40cd-91fa-2572255bec9b',2,120000.00,240000.00,'36007d9f-bb88-44da-8a38-7dfc168ab34e');
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `permission_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `permission_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `UKl3pmqryh8vgle52647itattb9` (`permission_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES ('921ce035-5f8a-4ac7-802a-68ad099933f7','CREATE_USER'),('70390a31-d121-4ab4-84e5-f56d58e69497','DELETE_USER'),('a79c7dde-9bae-405f-adb8-d958caffea76','SEARCH_USER'),('9f016c4c-d7da-4f10-bd9f-3e91c1e67a84','UPDATE_USER');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `product_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `size` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `color` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `category_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `product_ibfk_1` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1e75c3e7-da31-40cd-91fa-2572255bec9b','PO005','M','Đen',120000.00,'ecce4da1-249c-4b96-8af5-fda1d480dd5e'),('ad1923a7-eb69-47ac-b25e-3caf54de5b40','PO004','M','Nâu',120000.00,'ecce4da1-249c-4b96-8af5-fda1d480dd5e'),('fa4ae3db-739d-4a2f-8ccb-4fe342ce4bcf','PO004','L','Đen',120000.00,'ecce4da1-249c-4b96-8af5-fda1d480dd5e');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `product_id` varchar(36) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `fk_product` (`product_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (4,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749379715/khuongmaiimg/audn9jmynnjsmodkv1vj.jpg','ad1923a7-eb69-47ac-b25e-3caf54de5b40'),(5,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749379716/khuongmaiimg/iyr7hqtygo1sdifpvywj.jpg','ad1923a7-eb69-47ac-b25e-3caf54de5b40'),(6,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749379718/khuongmaiimg/futhfwxynxwu6eea5cjh.jpg','ad1923a7-eb69-47ac-b25e-3caf54de5b40'),(21,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749466358/khuongmaiimg/ondp5lc7qhjec21tzvbj.jpg','fa4ae3db-739d-4a2f-8ccb-4fe342ce4bcf'),(22,'https://res.cloudinary.com/dhzgwqu4j/image/upload/v1749894274/khuongmaiimg/la3zfbym6plcmunflhag.jpg','1e75c3e7-da31-40cd-91fa-2572255bec9b');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productmaterial`
--

DROP TABLE IF EXISTS `productmaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productmaterial` (
  `product_material_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `product_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `material_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity_used` decimal(38,2) DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_material_id`),
  KEY `product_id` (`product_id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `productmaterial_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `productmaterial_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productmaterial`
--

LOCK TABLES `productmaterial` WRITE;
/*!40000 ALTER TABLE `productmaterial` DISABLE KEYS */;
/*!40000 ALTER TABLE `productmaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `role_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('00341972-7b0e-4726-91e1-a220b846077d','ADMIN'),('90582572-a246-42be-98ad-be3b04f62350','USER'),('2efd3e56-0250-46cc-a3ff-1817239517b3','WAREHOUSE STAFF');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `permission_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES ('00341972-7b0e-4726-91e1-a220b846077d','70390a31-d121-4ab4-84e5-f56d58e69497'),('00341972-7b0e-4726-91e1-a220b846077d','921ce035-5f8a-4ac7-802a-68ad099933f7'),('90582572-a246-42be-98ad-be3b04f62350','921ce035-5f8a-4ac7-802a-68ad099933f7'),('00341972-7b0e-4726-91e1-a220b846077d','9f016c4c-d7da-4f10-bd9f-3e91c1e67a84'),('90582572-a246-42be-98ad-be3b04f62350','9f016c4c-d7da-4f10-bd9f-3e91c1e67a84'),('00341972-7b0e-4726-91e1-a220b846077d','a79c7dde-9bae-405f-adb8-d958caffea76');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission_set`
--

DROP TABLE IF EXISTS `role_permission_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission_set` (
  `role_role_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `permission_set_permission_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`role_role_id`,`permission_set_permission_id`),
  KEY `FKnhst3yfg7wrbp6m8ufx4e8dk2` (`permission_set_permission_id`),
  CONSTRAINT `FKhgy3w4d6lpi8xxxqc1evji4h3` FOREIGN KEY (`role_role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKnhst3yfg7wrbp6m8ufx4e8dk2` FOREIGN KEY (`permission_set_permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission_set`
--

LOCK TABLES `role_permission_set` WRITE;
/*!40000 ALTER TABLE `role_permission_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('71e44a55-e21e-464c-8403-0f015ed05dda','MỚI TẠO'),('a6d40f0d-8784-4a54-8215-23211b8537f9','ĐANG XỬ LÝ'),('cb05912c-d700-4a23-8d4f-c48ef6219289','ĐÃ HỦY'),('d3dfb77a-0735-4324-a50c-347b14e02313','HOÀN TẤT');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_receipt`
--

DROP TABLE IF EXISTS `stock_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_receipt` (
  `receipt_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `created_by` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `receipt_date` datetime(6) DEFAULT NULL,
  `receipt_type` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`receipt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_receipt`
--

LOCK TABLES `stock_receipt` WRITE;
/*!40000 ALTER TABLE `stock_receipt` DISABLE KEYS */;
INSERT INTO `stock_receipt` VALUES ('5b1a8c17-6752-4beb-9e2e-e74340461e6c','haibeomatkinh','Nhập vải cotton lô 20250628','2025-07-02 09:07:40.213150','TRANSFER'),('7748ce51-6d7b-478f-ac08-3918292f2127','haibeomatkinh','Nhập vải cotton lô 20250628','2025-07-02 09:22:45.837890','IMPORT'),('af9680a1-7750-47bd-ab99-766c395fe8d4','haibeomatkinh','Xuất vải cotton lô 20250628','2025-07-02 09:02:43.961609','EXPORT'),('bbe74857-3671-4aa2-b767-b8d82ddc09f9','haibeomatkinh','Nhập vải cotton lô 20250628','2025-07-01 10:04:39.638918','IMPORT'),('c9d9ff84-834d-42e0-a7b5-5ead75172eed','haibeomatkinh','Nhập vải cotton lô 20250628','2025-07-01 10:02:10.920518','IMPORT');
/*!40000 ALTER TABLE `stock_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_receipt_detail`
--

DROP TABLE IF EXISTS `stock_receipt_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_receipt_detail` (
  `detail_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity` int NOT NULL,
  `from_warehouse_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `product_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `stock_receipt_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `to_warehouse_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK81onbk379syo1s9vjy5kvgfgn` (`from_warehouse_id`),
  KEY `FKteovjwdku82w0c1qm9objwvlk` (`product_id`),
  KEY `FKaq3ppd4fmdot8f40stqy891go` (`stock_receipt_id`),
  KEY `FKtn4mifrt3qpvmfobaymqiksln` (`to_warehouse_id`),
  CONSTRAINT `FK81onbk379syo1s9vjy5kvgfgn` FOREIGN KEY (`from_warehouse_id`) REFERENCES `warehouse` (`warehouse_id`),
  CONSTRAINT `FKaq3ppd4fmdot8f40stqy891go` FOREIGN KEY (`stock_receipt_id`) REFERENCES `stock_receipt` (`receipt_id`),
  CONSTRAINT `FKteovjwdku82w0c1qm9objwvlk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKtn4mifrt3qpvmfobaymqiksln` FOREIGN KEY (`to_warehouse_id`) REFERENCES `warehouse` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_receipt_detail`
--

LOCK TABLES `stock_receipt_detail` WRITE;
/*!40000 ALTER TABLE `stock_receipt_detail` DISABLE KEYS */;
INSERT INTO `stock_receipt_detail` VALUES ('33683681-753d-4fe0-b75e-5aaa451df6f9',300,NULL,'1e75c3e7-da31-40cd-91fa-2572255bec9b','c9d9ff84-834d-42e0-a7b5-5ead75172eed','4b7bd4d5-0bb3-42fe-bf60-79debdbde352'),('34ad8c54-ef9f-4847-a7bd-1cdd5c7ce4e3',1,'36007d9f-bb88-44da-8a38-7dfc168ab34e','1e75c3e7-da31-40cd-91fa-2572255bec9b','af9680a1-7750-47bd-ab99-766c395fe8d4',NULL),('36d3131f-0dee-4388-89ab-59867aab2628',2,NULL,'ad1923a7-eb69-47ac-b25e-3caf54de5b40','7748ce51-6d7b-478f-ac08-3918292f2127','4b7bd4d5-0bb3-42fe-bf60-79debdbde352'),('4d6b2aa0-cea9-4851-8c02-618a24c93346',4,'36007d9f-bb88-44da-8a38-7dfc168ab34e','1e75c3e7-da31-40cd-91fa-2572255bec9b','5b1a8c17-6752-4beb-9e2e-e74340461e6c','4b7bd4d5-0bb3-42fe-bf60-79debdbde352'),('5906ead6-0dc6-46f3-9cf1-ba2903dbba3e',1,'4b7bd4d5-0bb3-42fe-bf60-79debdbde352','1e75c3e7-da31-40cd-91fa-2572255bec9b','af9680a1-7750-47bd-ab99-766c395fe8d4',NULL),('6b2561dc-ce8c-4290-ac90-52814dac5e65',6,NULL,'fa4ae3db-739d-4a2f-8ccb-4fe342ce4bcf','7748ce51-6d7b-478f-ac08-3918292f2127','36007d9f-bb88-44da-8a38-7dfc168ab34e'),('a34e762f-95d3-4357-9f6f-e02a8898935a',2,NULL,'1e75c3e7-da31-40cd-91fa-2572255bec9b','bbe74857-3671-4aa2-b767-b8d82ddc09f9','4b7bd4d5-0bb3-42fe-bf60-79debdbde352'),('ca4975f3-b153-4920-9d74-5f7b39dd82d6',16,'4b7bd4d5-0bb3-42fe-bf60-79debdbde352','1e75c3e7-da31-40cd-91fa-2572255bec9b','5b1a8c17-6752-4beb-9e2e-e74340461e6c','36007d9f-bb88-44da-8a38-7dfc168ab34e'),('e001c962-446f-4794-89f5-4771f0f134a2',500,NULL,'1e75c3e7-da31-40cd-91fa-2572255bec9b','c9d9ff84-834d-42e0-a7b5-5ead75172eed','36007d9f-bb88-44da-8a38-7dfc168ab34e'),('e9037957-5b0d-43f4-ae00-641757b76e44',6,NULL,'1e75c3e7-da31-40cd-91fa-2572255bec9b','bbe74857-3671-4aa2-b767-b8d82ddc09f9','36007d9f-bb88-44da-8a38-7dfc168ab34e');
/*!40000 ALTER TABLE `stock_receipt_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `task_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `order_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `employee_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `status_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `order_id` (`order_id`),
  KEY `employee_id` (`employee_id`),
  KEY `fk_status_id` (`status_id`),
  CONSTRAINT `fk_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `task_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES ('0f99d1e6-9969-4a28-a604-3f75c4564375','4b79a825-5813-4182-8e57-71bf5f21c5b6','4a7d8f49-ce0a-48dc-97ec-5ad04d519377','71e44a55-e21e-464c-8403-0f015ed05dda','Xếp quần áo màu đen','2025-07-03','2025-07-06'),('39ff7b3f-2d56-4190-8190-fdfb222d826e','4b79a825-5813-4182-8e57-71bf5f21c5b6','4a7d8f49-ce0a-48dc-97ec-5ad04d519377','71e44a55-e21e-464c-8403-0f015ed05dda','Xếp quần áo màu hồng','2025-07-03','2025-07-06');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `employee_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `role_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `employee_id` (`employee_id`),
  UNIQUE KEY `uk_user_username` (`username`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('37074ec4-a1c8-428c-97be-83500f4b626e','haibeomatkinh','$2a$10$khh5X0DDIfhCShQvix/dD.h.7D/w5XoFQZH7JNWrQRzBa4hL/73Uu','c6914ab8-90f3-407c-8802-cf03af5749ec','00341972-7b0e-4726-91e1-a220b846077d'),('487a72dc-ecae-433a-9e45-d7a1f581e92a','cophuongtocxu','$2a$10$40NyUHnRVFa/hu4vHOmbK.liPsWp99qwj/RQsq8Vt45UPI9Ve2D42','50336d3a-a15b-4d73-adfd-20b7d230f156','2efd3e56-0250-46cc-a3ff-1817239517b3'),('8b0001d4-ba20-4ce3-996d-ad6ba3f00f59','vobatong123','$2a$10$4MPwKS9MjsdAUkwDwDJWBOdShVYazYIhtb1qHS7l1EaSSlBfD6DJ.','4072b130-26bb-43a1-867b-cf25311853b6','00341972-7b0e-4726-91e1-a220b846077d'),('a75f6b21-aaea-4ba4-a9b5-0693ee7aaa38','anhtapgyml1','$2a$10$E9/f8QPPZJI0UOt5tkxlLeNjo1N8PCLDrtLzupXkE/Nx.JuW6Hwj6','a0c7dd2f-9a57-482f-a6d4-c05f55762fe9','2efd3e56-0250-46cc-a3ff-1817239517b3'),('af71c5b5-ef8b-4d1c-831d-74b5022ce57d','toquyphuoc1611','$2a$10$JiiXIfco.B.CgUl4/yDb6uPpxLqj.9/P43yQyBv/G5zLhPLDQCY2C','a85b5f40-a2bd-4a55-81de-4b0c5cd1850a','00341972-7b0e-4726-91e1-a220b846077d'),('bb1e2da2-890c-4591-8c56-a58c7fd27684','tranthihoanganh','$2a$10$.9jYcRBkVBPvMeg4qU98GOtGJvcL/sBMcIbIYLzkM1h5ulhlHs1Xe','35c1e74f-9aba-4d86-9174-27fda6fb84e0','00341972-7b0e-4726-91e1-a220b846077d');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `warehouse_id` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `warehouse_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `location` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `manager_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES ('36007d9f-bb88-44da-8a38-7dfc168ab34e','kho 377/1','trong rừng sâu','Vua sư tử','1234567890'),('4b7bd4d5-0bb3-42fe-bf60-79debdbde352','kho 378','dưới biển','Vua hà mã','1234567890');
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-04 18:13:22
