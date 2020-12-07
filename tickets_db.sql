-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2020 at 11:20 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tickets_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `blocked_devices`
--

CREATE TABLE `blocked_devices` (
  `id` int(11) NOT NULL,
  `uuid` varchar(36) NOT NULL,
  `blocked_until` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `time_duration` int(11) NOT NULL,
  `stake_limit` int(11) NOT NULL,
  `hot_percentage` int(11) NOT NULL,
  `restriction_expires` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`id`, `time_duration`, `stake_limit`, `hot_percentage`, `restriction_expires`) VALUES
(1, 600, 1000, 80, 3600);

--
-- Triggers `config`
--
DELIMITER $$
CREATE TRIGGER `checkData` BEFORE UPDATE ON `config` FOR EACH ROW BEGIN
   
                        
    IF new.time_duration<300 or new.time_duration>86400 THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Value time_duration must be between 300 and 86400';
    ELSEIF new.stake_limit<1 or new.stake_limit>1000000000 THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Value stake_limit must be between 1 and 10000000'; 
    ELSEIF new.hot_percentage<1 or new.hot_percentage>80 THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Value hot_percentage must be between 1 and 100';
    ELSEIF new.restriction_expires<60 and new.restriction_expires!=0 THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Value restriction_expires must be bigger than 59 or equal to 0(never expires)';        
            
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id_PK` int(11) NOT NULL,
  `id` varchar(36) DEFAULT NULL,
  `device_id` varchar(36) DEFAULT NULL,
  `stake` double DEFAULT NULL,
  `ticket_date_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blocked_devices`
--
ALTER TABLE `blocked_devices`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uuid` (`uuid`);

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id_PK`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blocked_devices`
--
ALTER TABLE `blocked_devices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `config`
--
ALTER TABLE `config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id_PK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=244;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
