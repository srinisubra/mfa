-- phpMyAdmin SQL Dump
-- version 3.3.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 22, 2010 at 11:29 AM
-- Server version: 5.1.47
-- PHP Version: 5.3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `WebINFS`
--

-- --------------------------------------------------------

--
-- Table structure for table `user_credentials`
--

CREATE TABLE IF NOT EXISTS `user_credentials` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(15) NOT NULL DEFAULT 'user',
  `user_salt` varchar(50) NOT NULL,
  `pass_hash` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user_credentials`
--

INSERT INTO `user_credentials` (`user_id`, `user_name`, `user_salt`, `pass_hash`) VALUES
(1, 'admin', '312381', 'd033e22ae348aeb5660fc2140aec35850c4da997'),
(2, 'test_user1', '238973', '501349a004a637185534f9e913f923494d641cb6'),
(3, 'test_user2', '324674', 'e8e70e2664eb2eda5c1517d314bdc8fc58fef065'),
(4, 'amay', '798325', '97a6a647c4791f38024624a0d81f8fa9d8da32d7'),
(5, 'nadir', '987344', 'd24b3c8092d07463c72f2a0f73e5a811995f4ba2');

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE IF NOT EXISTS `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(50) NOT NULL,
  `phone_no` bigint(10) DEFAULT NULL,
  `device_id` varchar(20) DEFAULT NULL,
  `device_type` varchar(10) DEFAULT NULL,
  `zip_code` int(6) DEFAULT NULL,
  `secondary_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`id`, `email_id`, `phone_no`, `device_id`, `device_type`, `zip_code`, `secondary_email`) VALUES
(1, 'admin@somedomain.com', 7037030171, '49-015420-323751', 'GSM', 30363, NULL),
(2, 'test_user1@domain.com', 9893098930, '49-015420-982191', 'GSM', 30339, NULL),
(3, 'test_user2@gmail.com', 703703390, '49-015420-323423', 'GSM', 30319, NULL),
(4, 'amaysinghal@yahoo.com', 6787049444, 'A0000022236C4C', 'CDMA', 30318, NULL),
(5, 'nadirsaghar@gmail.com', 5712942818, 'A0000021336C4D', 'GSM', 30318, NULL);
