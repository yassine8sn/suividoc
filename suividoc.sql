-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2018 at 10:27 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `suividoc`
--

-- --------------------------------------------------------

--
-- Table structure for table `acteur`
--

CREATE TABLE `acteur` (
  `role` varchar(31) NOT NULL,
  `cin` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `type` varchar(31) NOT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `cin_encadrant` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acteur`
--

INSERT INTO `acteur` (`role`, `cin`, `email`, `login`, `nom`, `password`, `prenom`, `type`, `sexe`, `cin_encadrant`) VALUES
('ADMIN', 'CINA', 'admin@admin.com', 'root', 'admin', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'root', 'professeur', 'male', '');

-- --------------------------------------------------------

--
-- Table structure for table `avancement`
--

CREATE TABLE `avancement` (
  `type` varchar(31) NOT NULL,
  `date` datetime DEFAULT NULL,
  `note` int(11) NOT NULL,
  `encadrant_cin` varchar(255) NOT NULL,
  `doctorant_cin` varchar(255) NOT NULL,
  `id_avn` int(11) NOT NULL,
  `date_valide` datetime DEFAULT NULL,
  `remarque` varchar(255) DEFAULT NULL,
  `valide` bit(1) NOT NULL,
  `mois` int(11) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `papier`
--

CREATE TABLE `papier` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `acteur_cin` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `lien` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

CREATE TABLE `rdv` (
  `id` bigint(20) NOT NULL,
  `debut` datetime DEFAULT NULL,
  `fin` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `encadrant_cin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rdv_doctorants`
--

CREATE TABLE `rdv_doctorants` (
  `rdv_id` bigint(20) NOT NULL,
  `doctorants_cin` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acteur`
--
ALTER TABLE `acteur`
  ADD PRIMARY KEY (`cin`);

--
-- Indexes for table `avancement`
--
ALTER TABLE `avancement`
  ADD PRIMARY KEY (`encadrant_cin`,`doctorant_cin`),
  ADD KEY `FK86r43ndybdirjuscagvqkvx1s` (`doctorant_cin`),
  ADD KEY `FK7pqyy1yr73fb4192gas67ihoc` (`id`);

--
-- Indexes for table `papier`
--
ALTER TABLE `papier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkm9301ml760fv54x1ii9tlccd` (`acteur_cin`);

--
-- Indexes for table `rdv`
--
ALTER TABLE `rdv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1brm5p8moo7uy1t62d9pxd3js` (`encadrant_cin`);

--
-- Indexes for table `rdv_doctorants`
--
ALTER TABLE `rdv_doctorants`
  ADD UNIQUE KEY `UK_h7k7yfy7h362k9isk9nypcqmq` (`doctorants_cin`),
  ADD KEY `FKfldbi12l68p2p148ya87whrjn` (`rdv_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `papier`
--
ALTER TABLE `papier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `avancement`
--
ALTER TABLE `avancement`
  ADD CONSTRAINT `FK7pqyy1yr73fb4192gas67ihoc` FOREIGN KEY (`id`) REFERENCES `papier` (`id`),
  ADD CONSTRAINT `FK7ytllcb88qckpjp5ha3unyjm6` FOREIGN KEY (`encadrant_cin`) REFERENCES `acteur` (`cin`),
  ADD CONSTRAINT `FK86r43ndybdirjuscagvqkvx1s` FOREIGN KEY (`doctorant_cin`) REFERENCES `acteur` (`cin`);

--
-- Constraints for table `papier`
--
ALTER TABLE `papier`
  ADD CONSTRAINT `FKkm9301ml760fv54x1ii9tlccd` FOREIGN KEY (`acteur_cin`) REFERENCES `acteur` (`cin`);

--
-- Constraints for table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `FK1brm5p8moo7uy1t62d9pxd3js` FOREIGN KEY (`encadrant_cin`) REFERENCES `acteur` (`cin`);

--
-- Constraints for table `rdv_doctorants`
--
ALTER TABLE `rdv_doctorants`
  ADD CONSTRAINT `FKdqh0jku77honhm739glup4d24` FOREIGN KEY (`doctorants_cin`) REFERENCES `acteur` (`cin`),
  ADD CONSTRAINT `FKfldbi12l68p2p148ya87whrjn` FOREIGN KEY (`rdv_id`) REFERENCES `rdv` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
