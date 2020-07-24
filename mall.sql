-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.20 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mall 的数据库结构
DROP DATABASE IF EXISTS `mall`;
CREATE DATABASE IF NOT EXISTS `mall` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mall`;

-- 导出  表 mall.admin 结构
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) NOT NULL DEFAULT '0',
  `salt` char(20) NOT NULL DEFAULT '0',
  `password` char(32) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.admin 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`aid`, `aname`, `salt`, `password`, `phone`) VALUES
	(0, 'root', '5iapiRejp/pFWBKPK0Tw', '6659e6abf400c6a72a52d3db90763c02', '18146856052');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 导出  表 mall.cart 结构
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cpid` int NOT NULL DEFAULT '0',
  `cuid` int NOT NULL DEFAULT '0',
  `number` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`cid`),
  KEY `cpid` (`cpid`),
  KEY `cuid` (`cuid`),
  CONSTRAINT `cpid` FOREIGN KEY (`cpid`) REFERENCES `product` (`pid`) ON DELETE CASCADE,
  CONSTRAINT `cuid` FOREIGN KEY (`cuid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.cart 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- 导出  表 mall.order 结构
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL DEFAULT '0',
  `pid` int NOT NULL DEFAULT '0',
  `trade_time` date NOT NULL,
  `trade_num` int NOT NULL DEFAULT '1',
  `address` varchar(100) NOT NULL DEFAULT '0',
  `pay_or_not` tinyint NOT NULL DEFAULT '0',
  `deliver_or_not` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  KEY `pid` (`pid`),
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.order 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`oid`, `uid`, `pid`, `trade_time`, `trade_num`, `address`, `pay_or_not`, `deliver_or_not`) VALUES
	(1, 1, 4, '2020-07-23', 1, '成都', 0, 0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- 导出  表 mall.product 结构
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `sid` int NOT NULL DEFAULT '0',
  `pname` varchar(50) NOT NULL DEFAULT '0',
  `price` double NOT NULL,
  `number` int NOT NULL DEFAULT '0',
  `description` varchar(200) DEFAULT '0',
  `cover` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sid` (`sid`),
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `saler` (`sid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.product 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`pid`, `sid`, `pname`, `price`, `number`, `description`, `cover`) VALUES
	(1, 1, '衣服', 35, 2, '这是一件衣服', 'https://img.alicdn.com/imgextra/i3/384698632/O1CN01iNISXq2DdVP5H16DW_!!384698632-0-beehive-scenes.jpg_180x180xzq90.jpg_.webp'),
	(2, 1, '水杯', 20, 5, '这是一个水杯', 'https://img.alicdn.com/bao/uploaded/TB2jQl2ml0kpuFjy1zdXXXuUVXa_!!0-juitemmedia.jpg_180x180xzq90.jpg_.webp'),
	(3, 1, '设备', 15, 1, '这是一个设备', 'https://img.alicdn.com/bao/uploaded/TB2jH_uoFXXXXXfXpXXXXXXXXXX_!!0-dgshop.jpg_180x180xzq90.jpg_.webp'),
	(4, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp'),
	(5, 1, '水杯', 20, 5, '这是一个水杯', 'https://img.alicdn.com/bao/uploaded/TB2jQl2ml0kpuFjy1zdXXXuUVXa_!!0-juitemmedia.jpg_180x180xzq90.jpg_.webp'),
	(6, 1, '水杯', 20, 5, '这是一个水杯', 'https://img.alicdn.com/bao/uploaded/TB2jQl2ml0kpuFjy1zdXXXuUVXa_!!0-juitemmedia.jpg_180x180xzq90.jpg_.webp'),
	(7, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp'),
	(8, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp'),
	(9, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp'),
	(10, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp'),
	(11, 1, '显微镜', 150, 5, '这是一个显微镜', 'https://img.alicdn.com/bao/uploaded/i4/2564032885/TB2gfRiiwxlpuFjy0FoXXa.lXXa_!!2564032885.jpg_200x200q90.jpg_.webp');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 导出  表 mall.saler 结构
DROP TABLE IF EXISTS `saler`;
CREATE TABLE IF NOT EXISTS `saler` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(20) NOT NULL DEFAULT '0',
  `address` varchar(100) DEFAULT NULL,
  `bank_num` varchar(100) NOT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `register_time` date DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.saler 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `saler` DISABLE KEYS */;
INSERT INTO `saler` (`sid`, `sname`, `password`, `phone`, `email`, `address`, `bank_num`, `icon`, `register_time`) VALUES
	(1, 'test', 'test', '15683336525', '574658957@qq.com', '四川省成都市', '12345687945', NULL, '2020-07-23');
/*!40000 ALTER TABLE `saler` ENABLE KEYS */;

-- 导出  表 mall.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(20) NOT NULL DEFAULT '0',
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `register_time` date DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`uid`, `uname`, `password`, `phone`, `email`, `address`, `gender`, `birthday`, `icon`, `register_time`) VALUES
	(1, 'test', 'test', '15687426354', '254869871@qq.com', '四川省成都市双流区', '女', '2002-06-08', 'https://i1.hdslb.com/bfs/archive/5af948fcfd1cc4059257a5fef520633ae6252ed5.jpg', NULL),
	(4, 'user', '123', '15683331111', '526874598@qq.com', '四川省成都市双流区', '男', '2020-07-01', 'https://i1.hdslb.com/bfs/archive/5af948fcfd1cc4059257a5fef520633ae6252ed5.jpg', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
