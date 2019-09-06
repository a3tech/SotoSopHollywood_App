-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2019 at 05:28 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbaplikasipengelolaanrestoran`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` char(5) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `jabatan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `username`, `password`, `jabatan`) VALUES
('A0001', 'Annas Al Amin', 'a3isagod', 'admin', 'admin'),
('A0002', 'bagushekel', 'narkobagus', 'admin', 'karyawan'),
('A0003', 'ridho', 'ridoma', 'admin', 'karyawan');

-- --------------------------------------------------------

--
-- Table structure for table `item_menu_delivery`
--

CREATE TABLE `item_menu_delivery` (
  `node` char(5) DEFAULT NULL,
  `kd_menu` char(5) DEFAULT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_menu_delivery`
--

INSERT INTO `item_menu_delivery` (`node`, `kd_menu`, `jumlah`) VALUES
('D0001', 'M0008', 1),
('D0001', 'M0009', 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_menu_ditempat`
--

CREATE TABLE `item_menu_ditempat` (
  `no_nota` char(5) DEFAULT NULL,
  `kd_menu` char(5) DEFAULT NULL,
  `no_meja` varchar(2) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_menu_ditempat`
--

INSERT INTO `item_menu_ditempat` (`no_nota`, `kd_menu`, `no_meja`, `jumlah`) VALUES
('N0001', 'M0008', '1', 1),
('N0001', 'M0009', '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `kd_menu` char(5) NOT NULL,
  `id_admin` char(5) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga_menu` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`kd_menu`, `id_admin`, `nama_menu`, `harga_menu`) VALUES
('M0001', 'A0001', 'Rendang Sapi', '20000'),
('M0002', 'A0001', 'Pempek Kapal Selam', '15000'),
('M0003', 'A0001', 'Sate Ayam', '18000'),
('M0004', 'A0001', 'Gado-Gado', '15000'),
('M0005', 'A0001', 'Nasi Goreng Ayam', '16000'),
('M0006', 'A0001', 'Sop Buntut', '20000'),
('M0007', 'A0001', 'Soto Ayam', '17000'),
('M0008', 'A0001', 'Gudeg', '20000'),
('M0009', 'A0001', 'Es Teh', '3000'),
('M0010', 'A0001', 'Es Jeruk', '3500');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` char(5) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`) VALUES
('P0001', 'Kiplihehehe', '12345'),
('P0002', 'aw', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `tarif_antar`
--

CREATE TABLE `tarif_antar` (
  `kd_wilayah` char(5) NOT NULL,
  `id_admin` char(5) NOT NULL,
  `nama_wilayah` varchar(100) NOT NULL,
  `tarif_kirim` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tarif_antar`
--

INSERT INTO `tarif_antar` (`kd_wilayah`, `id_admin`, `nama_wilayah`, `tarif_kirim`) VALUES
('W0001', 'A0001', 'Jogja', 15000),
('W0002', 'A0001', 'Sleman', 20000),
('W0003', 'A0001', 'Mbantul', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_delivery`
--

CREATE TABLE `transaksi_delivery` (
  `node` char(5) NOT NULL,
  `kd_wilayah` char(5) DEFAULT NULL,
  `id_pelanggan` char(5) DEFAULT NULL,
  `id_admin` char(5) DEFAULT NULL,
  `tanggal` date NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_delivery`
--

INSERT INTO `transaksi_delivery` (`node`, `kd_wilayah`, `id_pelanggan`, `id_admin`, `tanggal`, `alamat`) VALUES
('D0001', 'W0003', 'P0002', 'A0002', '2019-01-04', '');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_ditempat`
--

CREATE TABLE `transaksi_ditempat` (
  `no_nota` char(5) NOT NULL,
  `id_admin` char(5) NOT NULL,
  `id_pelanggan` char(5) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_ditempat`
--

INSERT INTO `transaksi_ditempat` (`no_nota`, `id_admin`, `id_pelanggan`, `tanggal`) VALUES
('N0001', 'A0002', 'P0002', '2019-01-04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `item_menu_delivery`
--
ALTER TABLE `item_menu_delivery`
  ADD KEY `no_nota_delivery` (`node`),
  ADD KEY `kd_menu` (`kd_menu`);

--
-- Indexes for table `item_menu_ditempat`
--
ALTER TABLE `item_menu_ditempat`
  ADD KEY `no_nota` (`no_nota`),
  ADD KEY `kd_menu` (`kd_menu`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`kd_menu`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `tarif_antar`
--
ALTER TABLE `tarif_antar`
  ADD PRIMARY KEY (`kd_wilayah`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indexes for table `transaksi_delivery`
--
ALTER TABLE `transaksi_delivery`
  ADD PRIMARY KEY (`node`),
  ADD KEY `kd_wilayah` (`kd_wilayah`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_karyawan` (`id_admin`);

--
-- Indexes for table `transaksi_ditempat`
--
ALTER TABLE `transaksi_ditempat`
  ADD PRIMARY KEY (`no_nota`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_karyawan` (`id_admin`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item_menu_delivery`
--
ALTER TABLE `item_menu_delivery`
  ADD CONSTRAINT `item_menu_delivery_ibfk_1` FOREIGN KEY (`node`) REFERENCES `transaksi_delivery` (`node`) ON DELETE CASCADE,
  ADD CONSTRAINT `item_menu_delivery_ibfk_2` FOREIGN KEY (`kd_menu`) REFERENCES `menu` (`kd_menu`);

--
-- Constraints for table `item_menu_ditempat`
--
ALTER TABLE `item_menu_ditempat`
  ADD CONSTRAINT `item_menu_ditempat_ibfk_1` FOREIGN KEY (`no_nota`) REFERENCES `transaksi_ditempat` (`no_nota`) ON DELETE CASCADE,
  ADD CONSTRAINT `item_menu_ditempat_ibfk_2` FOREIGN KEY (`kd_menu`) REFERENCES `menu` (`kd_menu`);

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

--
-- Constraints for table `tarif_antar`
--
ALTER TABLE `tarif_antar`
  ADD CONSTRAINT `tarif_antar_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

--
-- Constraints for table `transaksi_delivery`
--
ALTER TABLE `transaksi_delivery`
  ADD CONSTRAINT `transaksi_delivery_ibfk_1` FOREIGN KEY (`kd_wilayah`) REFERENCES `tarif_antar` (`kd_wilayah`),
  ADD CONSTRAINT `transaksi_delivery_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_delivery_ibfk_3` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

--
-- Constraints for table `transaksi_ditempat`
--
ALTER TABLE `transaksi_ditempat`
  ADD CONSTRAINT `transaksi_ditempat_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_ditempat_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
