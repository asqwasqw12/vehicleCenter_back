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

-- Dumping data for table vehicle_center.sys_menu: ~42 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
REPLACE INTO `sys_menu` (`id`, `name`, `parent_id`, `url`, `location`, `perms`, `type`, `icon`, `order_num`, `create_by`, `create_time`, `last_update_by`, `last_update_time`, `del_flag`) VALUES
	(1, '系统管理', 0, '/sys', NULL, NULL, 0, 'el-icon-setting', 0, NULL, NULL, NULL, NULL, 0),
	(2, '用户管理', 1, 'user', 'sys/user', NULL, 1, 'el-icon-service', 1, NULL, NULL, NULL, NULL, 0),
	(3, '查看', 2, NULL, NULL, 'sys:user:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(4, '新增', 2, NULL, NULL, 'sys:user:add', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(5, '修改', 2, NULL, NULL, 'sys:user:edit', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(6, '删除', 2, NULL, NULL, 'sys:user:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(7, '单位管理', 1, 'dept', 'sys/dept', NULL, 1, 'el-icon-news', 2, NULL, NULL, NULL, NULL, 0),
	(8, '查看', 7, NULL, NULL, 'sys:dept:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(9, '新增', 7, NULL, NULL, 'sys:dept:add', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(10, '修改', 7, NULL, NULL, 'sys:dept:edit', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(11, '删除', 7, NULL, NULL, 'sys:dept:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(12, '角色管理', 1, 'role', 'sys/role', NULL, 1, 'el-icon-view', 3, NULL, NULL, NULL, NULL, 0),
	(13, '查看', 12, NULL, NULL, 'sys:role:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(14, '新增', 12, NULL, NULL, 'sys:role:add', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(15, '修改', 12, NULL, NULL, 'sys:role:edit', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(16, '删除', 12, NULL, NULL, 'sys:role:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(17, '菜单管理', 1, 'menu', 'sys/menu', NULL, 1, 'el-icon-menu', 4, NULL, NULL, NULL, NULL, 0),
	(18, '查看', 17, NULL, NULL, 'sys:menu:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(19, '新增', 17, NULL, NULL, 'sys:menu:add', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(20, '修改', 17, NULL, NULL, 'sys:menu:edit', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(21, '删除', 17, NULL, NULL, 'sys:menu:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(22, '字典管理', 1, 'dict', 'sys/dict', NULL, 1, 'el-icon-edit-outline', 5, NULL, NULL, NULL, NULL, 0),
	(23, '查看', 22, NULL, NULL, 'sys:dict:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(24, '新增', 22, NULL, NULL, 'sys:dict:add', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(25, '修改', 22, NULL, NULL, 'sys:dict:edit', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(26, '删除', 22, NULL, NULL, 'sys:dict:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(32, '登录日志', 38, 'loginlog', 'monitor/loginlog', NULL, 1, 'el-icon-info', 1, NULL, NULL, 'admin', '2018-09-23 19:32:28', 0),
	(33, '查看', 32, NULL, NULL, 'sys:loginlog:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(34, '删除', 32, NULL, NULL, 'sys:loginlog:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(35, '操作日志', 38, 'log', 'monitor/log', NULL, 1, 'el-icon-info', 2, NULL, NULL, 'admin', '2018-09-23 19:32:28', 0),
	(36, '查看', 35, NULL, NULL, 'sys:log:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(37, '删除', 35, NULL, NULL, 'sys:log:delete', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(38, '系统监控', 0, '/monitor', NULL, '', 0, 'el-icon-info', 1, 'admin', '2018-12-27 10:57:29', 'admin', '2019-01-10 17:31:04', 0),
	(39, '数据监控', 38, '/druid', 'monitor/sql', NULL, 1, 'el-icon-warning', 3, NULL, NULL, 'admin', '2018-12-27 11:03:45', 0),
	(40, '查看', 39, NULL, NULL, 'sys:druid:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(42, '查看', 41, NULL, NULL, 'sys:monitor:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(46, '接口文档', 38, '/swagger', 'monitor/swagger', NULL, 1, 'el-icon-document', 4, NULL, NULL, 'admin', '2018-12-27 11:04:18', 0),
	(47, '查看', 46, NULL, NULL, 'sys:swagger:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(50, '车辆监管', 0, '/car', NULL, '', 0, 'el-icon-view', 2, 'admin', '2018-11-15 14:39:30', 'admin', '2018-11-15 14:56:18', 0),
	(51, '查看', 52, NULL, NULL, 'sys:car_overview:view', 2, NULL, 0, NULL, NULL, NULL, NULL, 0),
	(52, '车辆监察', 50, 'car_overview', 'car/car_overview', NULL, 1, 'el-icon-view', 1, NULL, '2018-11-15 14:39:30', NULL, '2018-11-15 14:56:18', 0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
