-- ----------------------------
-- Table structure for stone_user
-- ----------------------------
DROP TABLE IF EXISTS `stone_user`;
CREATE TABLE `stone_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(120) DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

