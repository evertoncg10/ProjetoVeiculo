-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 31-Jan-2017 às 17:25
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `veiculodb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_veiculo`
--

CREATE TABLE `tb_veiculo` (
  `id` int(11) NOT NULL,
  `data_cadastro` timestamp NOT NULL,
  `placa` varchar(10) NOT NULL,
  `renavam` int(11) NOT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `opcionais` varchar(255) DEFAULT NULL,
  `valor_venda` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_veiculo`
--

INSERT INTO `tb_veiculo` (`id`, `data_cadastro`, `placa`, `renavam`, `modelo`, `opcionais`, `valor_venda`) VALUES
(5, '2017-05-18 03:00:00', 'BBB-0000', 1234567890, 'Chevrolet Onix LT 1.4', 'Ar Condicionado;Vidros Elétricos;Air Bag;', 30000),
(6, '2017-07-14 03:00:00', 'MCO-1234', 1234567890, 'Chevrolet Prisma LT 1.4', 'Ar Condicionado;Air Bag;', 12000),
(7, '2017-01-27 02:00:00', 'DDD-1111', 1234567890, 'Wolksvagem Gol Trendline 1.0', 'Ar Condicionado;Vidros Elétricos;Air Bag;', 50000),
(8, '2017-05-13 03:00:00', 'AAA-0994', 1234567890, 'Wolksvagem Jetta Trendline 1.4 TSI', 'Ar Condicionado;', 20000),
(10, '2017-01-30 13:33:05', 'OOO-6666', 1234567890, 'Chevrolet Onix LT 1.4', 'Ar Condicionado;Vidros Elétricos;Air Bag;', 15000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_veiculo`
--
ALTER TABLE `tb_veiculo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_veiculo`
--
ALTER TABLE `tb_veiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
