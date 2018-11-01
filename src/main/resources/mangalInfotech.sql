-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.24 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mangalinoftech
CREATE DATABASE IF NOT EXISTS `mangalinoftech` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mangalinoftech`;

-- Dumping structure for table mangalinoftech.city
CREATE TABLE IF NOT EXISTS `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.city: ~2 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`city_id`, `city_name`) VALUES
	(1, 'Pune'),
	(2, 'Nashik');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.e_tendar_credentials
CREATE TABLE IF NOT EXISTS `e_tendar_credentials` (
  `customer_id` int(11) unsigned NOT NULL,
  `t_user_id` varchar(50) NOT NULL,
  `t_password` varchar(50) NOT NULL,
  KEY `FK1_CUST_ID` (`customer_id`),
  CONSTRAINT `FK1_CUST_ID` FOREIGN KEY (`customer_id`) REFERENCES `e_tendar_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.e_tendar_credentials: ~0 rows (approximately)
/*!40000 ALTER TABLE `e_tendar_credentials` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_tendar_credentials` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.e_tendar_customer
CREATE TABLE IF NOT EXISTS `e_tendar_customer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL DEFAULT '0',
  `middle_name` varchar(50) NOT NULL DEFAULT '0',
  `last_name` varchar(50) NOT NULL DEFAULT '0',
  `mobile_number` varchar(12) NOT NULL DEFAULT '0',
  `city` int(11) NOT NULL DEFAULT '0',
  `address` varchar(150) DEFAULT '0',
  `zip_code` varchar(10) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK1_CUSTOMER_CITY` (`city`),
  CONSTRAINT `FK1_CUSTOMER_CITY` FOREIGN KEY (`city`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.e_tendar_customer: ~0 rows (approximately)
/*!40000 ALTER TABLE `e_tendar_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_tendar_customer` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.offices
CREATE TABLE IF NOT EXISTS `offices` (
  `office_id` int(11) NOT NULL AUTO_INCREMENT,
  `office_contact_no` varchar(50) NOT NULL DEFAULT '0',
  `office_address` varchar(150) NOT NULL DEFAULT '0',
  `office_city` int(11) NOT NULL DEFAULT '0',
  `head_office` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`office_id`),
  KEY `FK1_CITY` (`office_city`),
  CONSTRAINT `FK1_CITY` FOREIGN KEY (`office_city`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.offices: ~0 rows (approximately)
/*!40000 ALTER TABLE `offices` DISABLE KEYS */;
/*!40000 ALTER TABLE `offices` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.office_partner_details
CREATE TABLE IF NOT EXISTS `office_partner_details` (
  `office_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `residence_address` varchar(150) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  KEY `FK1_OFFICE_ID` (`office_id`),
  KEY `FK2_CITY_ID` (`city_id`),
  CONSTRAINT `FK1_OFFICE_ID` FOREIGN KEY (`office_id`) REFERENCES `offices` (`office_id`),
  CONSTRAINT `FK2_CITY_ID` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.office_partner_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `office_partner_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `office_partner_details` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`, `role_description`) VALUES
	(1, 'ADMIN', 'ADMINISTRATOR'),
	(2, 'USER', 'Application User.');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.staffmember
CREATE TABLE IF NOT EXISTS `staffmember` (
  `staff_member_id` int(11) NOT NULL AUTO_INCREMENT,
  `DOJ` date NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  `residence_address` varchar(150) NOT NULL,
  `app_user` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  `app_user_id` varchar(50) DEFAULT '0',
  `app_password` varchar(100) DEFAULT '0',
  PRIMARY KEY (`staff_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.staffmember: ~1 rows (approximately)
/*!40000 ALTER TABLE `staffmember` DISABLE KEYS */;
INSERT INTO `staffmember` (`staff_member_id`, `DOJ`, `first_name`, `middle_name`, `last_name`, `mobile_number`, `residence_address`, `app_user`, `app_user_id`, `app_password`) VALUES
	(1, '2018-10-23', 'Sushil', 'Baba', 'Nagarkar', '9503274161', 'Pune', 1, 'sushnaga', '$2a$10$6N0ZXpbA7M7LH888sSi/uu18B95pOP0vusBY0APUSn0nUp.kou7bO');
/*!40000 ALTER TABLE `staffmember` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.staff_member_role_mapping
CREATE TABLE IF NOT EXISTS `staff_member_role_mapping` (
  `staff_member_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK1_STAFF_MEMBER` (`staff_member_id`),
  KEY `FK2_ROLE_ID` (`role_id`),
  CONSTRAINT `FK1_STAFF_MEMBER` FOREIGN KEY (`staff_member_id`) REFERENCES `staffmember` (`staff_member_id`),
  CONSTRAINT `FK2_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.staff_member_role_mapping: ~1 rows (approximately)
/*!40000 ALTER TABLE `staff_member_role_mapping` DISABLE KEYS */;
INSERT INTO `staff_member_role_mapping` (`staff_member_id`, `role_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `staff_member_role_mapping` ENABLE KEYS */;

-- Dumping structure for table mangalinoftech.tendar_submitted
CREATE TABLE IF NOT EXISTS `tendar_submitted` (
  `e_cust_id` int(11) unsigned NOT NULL,
  `tender_id` varchar(50) NOT NULL,
  `tendar_name` varchar(50) NOT NULL,
  `tendar_desciption` varchar(100) NOT NULL,
  `tendar_application_fee` varchar(50) NOT NULL,
  `tendar_total_amount` varchar(50) NOT NULL,
  `tendar_commission` varchar(50) NOT NULL,
  `commssion_paid` varchar(50) NOT NULL,
  `commission_balance` varchar(50) NOT NULL,
  `applied_date` datetime NOT NULL,
  KEY `FK1_Customer_ID` (`e_cust_id`),
  CONSTRAINT `FK1_Customer_ID` FOREIGN KEY (`e_cust_id`) REFERENCES `e_tendar_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table mangalinoftech.tendar_submitted: ~0 rows (approximately)
/*!40000 ALTER TABLE `tendar_submitted` DISABLE KEYS */;
/*!40000 ALTER TABLE `tendar_submitted` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
