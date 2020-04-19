-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2019 at 05:09 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flight_ms`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empId` varchar(5) NOT NULL,
  `employeeName` varchar(20) NOT NULL,
  `designation` varchar(30) NOT NULL,
  `salary` double(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empId`, `employeeName`, `designation`, `salary`) VALUES
('E01', 'Mr. A', 'Manager', 20310.00),
('E02', 'Mr. B', 'Receptionist', 5001.45),
('E03', 'Mr. C', 'Accountant', 5000.35),
('E04', 'Mr. D', 'Receptionist', 42000.00),
('E05', 'Mr. E', 'manager', 6500.00),
('E06', 'Mr. F', 'Superviser', 65000.25),
('E07', 'Mr. G', 'MANAGER', 12000.50),
('E08', 'Mr. H', 'Clerk', 25000.45),
('E09', 'Mr. I', 'Pilot', 3500.50),
('E10', 'Mr. J', 'Air Hostess', 65000.00);

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `flightNumber` varchar(15) NOT NULL,
  `journeyFrom` varchar(30) NOT NULL,
  `journeyDate` varchar(12) NOT NULL,
  `departureTimeHrs` int(2) NOT NULL,
  `departureTimeMins` int(2) NOT NULL,
  `journeyTo` varchar(30) NOT NULL,
  `arrivalDate` varchar(12) NOT NULL,
  `arrivalTimeHrs` int(2) NOT NULL,
  `arrivalTimeMins` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`flightNumber`, `journeyFrom`, `journeyDate`, `departureTimeHrs`, `departureTimeMins`, `journeyTo`, `arrivalDate`, `arrivalTimeHrs`, `arrivalTimeMins`) VALUES
('AK-101', 'Dhaka', '01/10/2019', 9, 45, 'Dubai', '01/10/2019', 23, 15),
('AK-102', 'Malaysia', '02/10/1029', 22, 10, 'Dhaka', '03/10/2019', 4, 45),
('AK-103', 'Chittagong', '03/10/2019', 18, 30, 'Singapore', '04/10/2019', 3, 45),
('AK-104', 'Dhaka', '04/10/2019', 8, 45, 'Paris', '05/10/2019', 10, 30),
('AK-105', 'Canada', '05/10/2019', 8, 45, 'Dhaka', '07/10/2019', 19, 30),
('BK-101', 'Khulna', '03/10/2019', 15, 45, 'Dhaka', '03/10/2019', 17, 35),
('BK-102', 'Jessore', '02/10/2019', 10, 20, 'Rajshahi', '02/10/2019', 12, 10),
('BK-103', 'Dhaka', '02/10/2019', 9, 30, 'Barisal', '02/10/2019', 13, 45),
('BK-104', 'Dhaka', '01/10/2019', 10, 25, 'Sydney', '03/10/2019', 21, 35),
('BK-105', 'Kolkata', '01/10/2019', 9, 45, 'Dhaka', '01/10/2019', 13, 30),
('CK-101', 'Dhaka', '10/10/2019', 10, 45, 'Turkey', '12/10/2019', 12, 12),
('CK-102', 'Dhaka', '10/10/2019', 10, 12, 'Sylhet', '10/10/2019', 12, 45),
('CK-103', 'Dhaka', '10/10/2019', 10, 20, 'Chittagong', '10/10/2019', 14, 30),
('CK-104', 'Dhaka', '10/10/2019', 11, 20, 'Sylhet', '10/10/2019', 12, 45),
('CK-105', 'Dhaka', '10/10/2019', 12, 45, 'Sylhet', '11/10/2019', 14, 15),
('CK-106', 'Dhaka', '10/10/2019', 12, 45, 'Comilla', '12/10/2019', 14, 45),
('EK-101', 'Dhaka', '05/10/2019', 10, 30, 'Canada', '07/10/2019', 15, 45),
('EK-102', 'Sylhet', '05/10/2019', 10, 45, 'Dhaka', '05/10/2019', 12, 30),
('EK-103', 'Chittagong', '04/10/2019', 13, 5, 'Khulna', '04/10/2019', 16, 45),
('EK-104', 'Rajshahi', '03/10/2019', 12, 15, 'Sylhet', '03/10/2019', 14, 30),
('EK-105', 'Dhaka', '04/10/2019', 18, 45, 'Melbourne', '06/10/2019', 2, 30);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `personId` varchar(5) NOT NULL,
  `password` varchar(5) NOT NULL,
  `status` int(1) NOT NULL,
  `code` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`personId`, `password`, `status`, `code`) VALUES
('E01', '123', 1, 0),
('E02', '123', 0, 0),
('E03', '14613', 0, 11707),
('E04', '19331', 0, 14407),
('E05', '11966', 1, 10000),
('E06', '13871', 0, 12157),
('E07', '19004', 1, 18043),
('E08', '13197', 0, 19544),
('E09', '11815', 0, 13644),
('E10', '11405', 0, 12980);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`flightNumber`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`personId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
