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
	(0, 'root', 'dvsN3TjAFdOTNSffZVsZ', 'c0e93e85970b26571bc3315c4bade716', '18146856052');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.cart 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`cid`, `cpid`, `cuid`, `number`) VALUES
	(1, 7, 6, 2),
	(2, 4, 6, 1);
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
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.order 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`oid`, `uid`, `pid`, `trade_time`, `trade_num`, `address`, `pay_or_not`, `deliver_or_not`) VALUES
	(2, 1, 1, '2020-07-25', 1, '四川省成都市双流区', 1, 0),
	(4, 1, 1, '2020-07-25', 1, '四川省成都市双流区', 1, 0);
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
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `saler` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.product 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`pid`, `sid`, `pname`, `price`, `number`, `description`, `cover`) VALUES
	(1, 1, '包', 20, 5, '设计新款手工头层疯马皮复古横款粗犷原创单肩包斜挎包真牛皮男包', 'https://img.alicdn.com/bao/uploaded/i4/TB199RhPXXXXXa4apXXYXGcGpXX_M2.SS2_200x200q90.jpg_.webp'),
	(2, 1, '分线器', 20, 5, 'USB3.0分线器笔记本电脑一拖四集线HUB转换扩展多外接口', 'https://img.alicdn.com/bao/uploaded/i4/TB1iYVbOXXXXXcoXXXXXXXXXXXX_!!0-item_pic.jpg_200x200q90.jpg_.webp'),
	(3, 1, '拖鞋', 15, 1, '可爱棉拖鞋秋冬季韩版毛毛拖鞋女室内月子平底保暖防滑居家时尚潮', 'https://img.alicdn.com/bao/uploaded/i1/2455463936/TB1bkVtaStYBeNjSspkXXbU8VXa_!!0-item_pic.jpg_200x200q90.jpg_.webp'),
	(4, 1, '沙发', 150, 5, '现货美式欧式简约现代田园单人沙发老虎椅高背椅卧室客厅书房椅子', 'https://img.alicdn.com/imgextra/i4/53263072/O1CN01aXBLBB1YZ1IC4uxqd_!!0-saturn_solar.jpg_260x260.jpg'),
	(5, 1, '电脑椅', 20, 5, '西昊人体工学椅子电脑椅家用转椅学生学习书桌椅办公写字升降座椅', 'https://img.alicdn.com/imgextra/i2/27907426/O1CN01JM3G7Z24j9lHNrEd4_!!0-saturn_solar.jpg_260x260.jpg'),
	(6, 1, '鞋', 20, 5, '高帮帆布鞋男韩版潮流百搭2020新款夏季透气潮鞋男士高邦休闲板鞋', 'https://gma.alicdn.com/bao/uploaded/i3/362170059/O1CN01D1ozue1CJ3ybHzYvC_!!0-saturn_solar.jpg_200x200.jpg_.webp'),
	(7, 1, '拉杆箱', 150, 5, '行李箱ins网红新款20寸学生拉杆箱男女韩版24寸拉链旅行密码皮箱', 'https://img.alicdn.com/imgextra/i1/914570175/O1CN01J45zKb1DABunuJEJe_!!0-saturn_solar.jpg_260x260.jpg'),
	(8, 1, '面膜', 150, 5, '自然堂富勒烯小灯泡面膜5片石墨烯提亮细致抗氧化发光黑科技官网', 'https://img.alicdn.com/imgextra/i2/42258719/O1CN015T4ViS2EHLuIoKIPu_!!0-saturn_solar.jpg_260x260.jpg'),
	(9, 1, '衬衫', 150, 5, '保罗竹纤维免烫男士短袖白衬衫夏季弹力商务正装抗皱职业工装衬衣', 'https://img.alicdn.com/imgextra/i2/111890288/O1CN01GRaSux1DzwfQDFfK0_!!0-saturn_solar.jpg_260x260.jpg'),
	(10, 1, '外套', 150, 10, '男款青少年渐变长袖衬衫春季薄款外套男士潮款宽松休闲衬衫', 'https://img.alicdn.com/imgextra/i1/1264160096/O1CN01ROdp9F1Ca0dEYQbui_!!0-saturn_solar.jpg_260x260.jpg'),
	(13, 1, '风扇', 22, 5, 'SEEDEN西点空气循环扇家用台式电风扇静音摇头涡流对流扇直流变频', 'https://img.alicdn.com/imgextra/i4/255700126/O1CN01Xt7Y361CnkVx1br5A_!!0-saturn_solar.jpg_260x260.jpg'),
	(15, 2, '小风扇', 20, 6, '无叶挂脖小风扇USB随身头戴迷你便携式可充电超静音办公室桌面学生小型懒人挂脖子大风力空调运动厨房小电扇', 'https://img.alicdn.com/imgextra/i2/52651592/O1CN01AcExlC1NdB8wesU9U_!!0-saturn_solar.jpg_260x260.jpg');
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
  `register_time` date DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.saler 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `saler` DISABLE KEYS */;
INSERT INTO `saler` (`sid`, `sname`, `password`, `phone`, `email`, `address`, `bank_num`, `register_time`) VALUES
	(1, 'test', 'test', '15683336525', '574658957@qq.com', '四川省成都市', '12345687945', '2020-07-23'),
	(2, 'saler', 'saler', '15684268962', '584629871@qq.com', '四川省', '12356489879', '2020-07-25');
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
  `icon` varchar(500) DEFAULT NULL,
  `register_time` date DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 正在导出表  mall.user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`uid`, `uname`, `password`, `phone`, `email`, `address`, `gender`, `birthday`, `icon`, `register_time`) VALUES
	(1, 'user', '123', '15683332222', '526874598@qq.cn', '四川省成都市', '女', '2020-07-04', 'https://i1.hdslb.com/bfs/archive/5af948fcfd1cc4059257a5fef520633ae6252ed5.jpg', '2020-07-25'),
	(5, 'root', '123', '15686518915', '124565294@qq.com', '', '女', '2020-07-02', 'https://i1.hdslb.com/bfs/archive/5af948fcfd1cc4059257a5fef520633ae6252ed5.jpg', '2020-07-25'),
	(6, 'test', 'test', '15687426311', '123465795@qq.com', '', '男', '2020-07-01', '', '2020-07-24'),
	(7, '张三', '123', '18957896251', '', '', '男', '2020-07-01', '', '2020-07-25');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
