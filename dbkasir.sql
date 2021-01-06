-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Jun 2020 pada 08.18
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbkasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `idbarang` int(10) NOT NULL,
  `namabarang` varchar(50) DEFAULT NULL,
  `jenisbarang` varchar(50) DEFAULT NULL,
  `harga` int(10) DEFAULT NULL,
  `stock` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`idbarang`, `namabarang`, `jenisbarang`, `harga`, `stock`) VALUES
(12568, 'chitato', 'Makanan', 7500, 15),
(12569, 'oreo', 'Makanan', 8000, 14),
(12570, 'doritos', 'Makanan', 10000, 30),
(12571, 'ring bee', 'Makanan', 9500, 26),
(12572, 'tango', 'Makanan', 5500, 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_user` int(11) NOT NULL,
  `id_pegawai` int(11) NOT NULL,
  `nama_lengkap` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `no_hp` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_user`, `id_pegawai`, `nama_lengkap`, `email`, `alamat`, `no_hp`) VALUES
(1001, 201001, 'mirah wibu', 'mirahwibu@gmail.com', 'jalanin aja dulu', '082146847101'),
(1002, 201002, 'Ghani Rasyid Hika', 'ghanirasyidhika@gmail.com', 'jalan kesana kemari', '089527974378'),
(1003, 201003, 'josh susu', 'josh@gmail.com', 'sebelan kantin bu\'e', '08654231');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(10) NOT NULL,
  `nama_barang` varchar(30) DEFAULT NULL,
  `harga_satuan` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pembelian`
--

INSERT INTO `pembelian` (`id_transaksi`, `id_barang`, `nama_barang`, `harga_satuan`, `qty`, `harga`) VALUES
(1, 12569, 'oreo', 8000, 5, 40000),
(1, 12568, 'chitato', 7500, 4, 30000),
(2, 12569, 'oreo', 8000, 1, 8000),
(2, 12568, 'chitato', 7500, 2, 15000);

--
-- Trigger `pembelian`
--
DELIMITER $$
CREATE TRIGGER `belisatuan` AFTER INSERT ON `pembelian` FOR EACH ROW BEGIN 
	UPDATE barang SET stock=stock-new.qty
    WHERE idbarang=new.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `pengembalian` AFTER DELETE ON `pembelian` FOR EACH ROW BEGIN
	UPDATE barang SET stock=stock+old.qty
    WHERE idbarang=old.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `penjualan` AFTER UPDATE ON `pembelian` FOR EACH ROW BEGIN 
	UPDATE barang SET stock=stock+old.qty-new.qty
    WHERE idbarang=new.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `idtransaksi` int(11) NOT NULL,
  `id_pegawai` varchar(20) DEFAULT NULL,
  `total_belanja` int(11) DEFAULT NULL,
  `tanggal_waktu` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `id_pegawai`, `total_belanja`, `tanggal_waktu`) VALUES
(1, '1001', 70000, '2020-06-05 18:50:12'),
(2, '1001', 23000, '2020-06-05 18:50:36');

-- --------------------------------------------------------

--
-- Struktur dari tabel `userlevel`
--

CREATE TABLE `userlevel` (
  `id_user` int(11) NOT NULL,
  `pass_user` char(10) DEFAULT NULL,
  `nama_user` varchar(30) NOT NULL,
  `level_user` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `userlevel`
--

INSERT INTO `userlevel` (`id_user`, `pass_user`, `nama_user`, `level_user`) VALUES
(1001, 'pokendut', 'MIRAH WIBU', 2),
(1002, 'hikaaa', 'Ghani Rasyid Hika', 1),
(1003, '1003', 'josh susu', 3);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`idbarang`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtransaksi`);

--
-- Indeks untuk tabel `userlevel`
--
ALTER TABLE `userlevel`
  ADD PRIMARY KEY (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
