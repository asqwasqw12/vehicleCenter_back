-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.15 - MySQL Community Server - GPL
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table vehicle_center.sys_user: ~12 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
REPLACE INTO `sys_user` (`id`, `name`, `nick_name`, `real_name`, `avatar`, `password`, `salt`, `email`, `mobile`, `status`, `dept_id`, `create_by`, `create_time`, `last_update_by`, `last_update_time`, `del_flag`) VALUES
	(1, 'admin', '超管', NULL, NULL, 'bd1718f058d8a02468134432b8656a86', 'YzcmCZNvbXocrsz9dm8e', 'admin@qq.com', '13612345678', 1, 4, 'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', 0),
	(22, 'zhangsan1', '张三1', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 7, 'admin', '2018-09-23 19:43:00', 'admin', '2019-01-10 11:41:13', 0),
	(23, 'zhangsan2', '张三2', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 7, 'admin', '2018-09-23 19:43:44', 'admin', '2018-09-23 19:43:52', 0),
	(24, 'zhangsan3', '张三3', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 7, 11, 'admin', '2018-09-23 19:44:23', 'admin', '2018-09-23 19:44:29', 0),
	(25, 'zhangsan4', '张三4', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 8, 'admin', '2018-09-23 19:45:32', 'admin', '2019-01-10 17:59:14', 0),
	(26, 'lisi1', '李四1', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 10, 'admin', '2018-09-23 19:45:48', 'admin', '2018-09-23 19:45:57', 0),
	(27, 'lisi2', '李四2', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 8, 'admin', '2018-09-23 19:46:09', 'admin', '2018-09-23 19:46:17', 0),
	(28, 'lisi3', '李四3', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 10, 'admin', '2018-09-23 19:46:38', 'admin', '2018-11-04 15:33:17', 0),
	(29, 'lisi4', '李四4', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 10, 'admin', '2018-09-23 19:46:54', 'admin', '2018-09-23 19:47:03', 0),
	(30, 'wangwu1', '王五1', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 11, 'admin', '2018-09-23 19:47:28', 'admin', '2018-09-23 19:48:04', 0),
	(31, 'wangwu2', '王五2', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 11, 'admin', '2018-09-23 19:47:44', 'admin', '2018-09-23 19:47:58', 0),
	(32, 'wwangwu3', '王五3', NULL, NULL, 'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', 1, 11, 'admin', '2018-09-23 19:48:38', 'admin', '2018-09-23 19:49:02', 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
