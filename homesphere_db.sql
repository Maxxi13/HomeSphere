-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2023 at 04:10 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `homesphere_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `county_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `county_id`) VALUES
(1, 'Alba Iulia', 1),
(2, 'Arad', 2),
(3, 'Pitesti', 3),
(4, 'Bacau', 4),
(5, 'Oradea', 5),
(6, 'Bistrita', 6),
(7, 'Botosani', 7),
(8, 'Brasov', 8),
(9, 'Braila', 9),
(10, 'Bucuresti', 10),
(11, 'Buzau', 11),
(12, 'Resita', 12),
(13, 'Calarasi', 13),
(14, 'Cluj', 14),
(15, 'Constanta', 15),
(16, 'Sfantu Gheorghe', 16),
(17, 'Targoviste', 17),
(18, 'Craiova', 18),
(19, 'Galati', 19),
(20, 'Giurgiu', 20),
(21, 'Targu Jiu', 21),
(22, 'Miercurea Ciuc', 22),
(23, 'Deva', 23),
(24, 'Slobozia', 24),
(25, 'Iasi', 25),
(26, 'Buftea', 26),
(27, 'Baia Mare', 27),
(28, 'Drobeta Turnu Severin', 28),
(29, 'Targu Mures', 29),
(30, 'Piatra Neamt', 30),
(31, 'Slatina', 31),
(32, 'Ploiesti', 32),
(33, 'Satu Mare', 33),
(34, 'Zalau', 34),
(35, 'Sibiu', 35),
(36, 'Suceava', 36),
(37, 'Alexandria', 37),
(38, 'Timisoara', 38),
(39, 'Tulcea', 39),
(40, 'Vaslui', 40),
(41, 'Ramnicu Valcea', 41),
(42, 'Focsani', 42);

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts` (
  `id` int(11) NOT NULL,
  `service_provider_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `date_and_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `number_of_hours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `counties`
--

CREATE TABLE `counties` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `counties`
--

INSERT INTO `counties` (`id`, `name`) VALUES
(1, 'Alba'),
(2, 'Arad'),
(3, 'Arges'),
(4, 'Bacau'),
(5, 'Bihor'),
(6, 'Bistrita-Nasaud'),
(7, 'Botosani'),
(9, 'Braila'),
(8, 'Brasov'),
(10, 'Bucuresti'),
(11, 'Buzau'),
(13, 'Calarasi'),
(12, 'Caras-Severin'),
(14, 'Cluj'),
(15, 'Constanta'),
(16, 'Covasna'),
(17, 'Dambovita'),
(18, 'Dolj'),
(19, 'Galati'),
(20, 'Giurgiu'),
(21, 'Gorj'),
(22, 'Harghita'),
(23, 'Hunedoara'),
(24, 'Ialomita'),
(25, 'Iasi'),
(26, 'Ilfov'),
(27, 'Maramures'),
(28, 'Mehedinti'),
(29, 'Mures'),
(30, 'Neamt'),
(31, 'Olt'),
(32, 'Prahova'),
(34, 'Salaj'),
(33, 'Satu Mare'),
(35, 'Sibiu'),
(36, 'Suceava'),
(37, 'Teleorman'),
(38, 'Timis'),
(39, 'Tulcea'),
(41, 'Valcea'),
(40, 'Vaslui'),
(42, 'Vrancea');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `city_id` int(11) NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers_service_providers`
--

CREATE TABLE `customers_service_providers` (
  `id_customer` int(11) NOT NULL,
  `id_service_provider` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_providers`
--

CREATE TABLE `service_providers` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `service_type_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `hourly_price` double(10,0) NOT NULL,
  `rating` double(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_providers_type`
--

CREATE TABLE `service_providers_type` (
  `id_provider` int(11) NOT NULL,
  `id_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_types`
--

CREATE TABLE `service_types` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service_types`
--

INSERT INTO `service_types` (`id`, `name`) VALUES
(1, 'Babysitting'),
(2, 'Plumbing'),
(3, 'Electricianing'),
(4, 'Carpentering'),
(5, 'Whitewashing');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cities_counties` (`county_id`);

--
-- Indexes for table `contracts`
--
ALTER TABLE `contracts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_contract_service_providers` (`service_provider_id`),
  ADD KEY `fk_contract_customer` (`customer_id`);

--
-- Indexes for table `counties`
--
ALTER TABLE `counties`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_customers_cities` (`city_id`);

--
-- Indexes for table `customers_service_providers`
--
ALTER TABLE `customers_service_providers`
  ADD PRIMARY KEY (`id_customer`,`id_service_provider`),
  ADD KEY `fk_service_providers_customers_service_providers` (`id_service_provider`);

--
-- Indexes for table `service_providers`
--
ALTER TABLE `service_providers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_service_providers_cities` (`city_id`);

--
-- Indexes for table `service_providers_type`
--
ALTER TABLE `service_providers_type`
  ADD PRIMARY KEY (`id_provider`,`id_type`),
  ADD KEY `fk_service_providers_type_service_type` (`id_type`);

--
-- Indexes for table `service_types`
--
ALTER TABLE `service_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `service_providers`
--
ALTER TABLE `service_providers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `service_types`
--
ALTER TABLE `service_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `fk_adims_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `fk_cities_counties` FOREIGN KEY (`county_id`) REFERENCES `counties` (`id`);

--
-- Constraints for table `contracts`
--
ALTER TABLE `contracts`
  ADD CONSTRAINT `fk_contract_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `fk_contract_service_providers` FOREIGN KEY (`service_provider_id`) REFERENCES `service_providers` (`id`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_customers_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `fk_customers_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `customers_service_providers`
--
ALTER TABLE `customers_service_providers`
  ADD CONSTRAINT `fk_customers_customers_service_providers` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_service_providers_customers_service_providers` FOREIGN KEY (`id_service_provider`) REFERENCES `service_providers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `service_providers`
--
ALTER TABLE `service_providers`
  ADD CONSTRAINT `fk_service_providers_cities` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `fk_service_providers_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `service_providers_type`
--
ALTER TABLE `service_providers_type`
  ADD CONSTRAINT `fk_service_providers_type_service_providers` FOREIGN KEY (`id_provider`) REFERENCES `service_providers` (`id`),
  ADD CONSTRAINT `fk_service_providers_type_service_type` FOREIGN KEY (`id_type`) REFERENCES `service_types` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
