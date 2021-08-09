
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` bigint(20) NOT NULL  COMMENT 'ID',
  `no` varchar(32) NOT NULL DEFAULT '' COMMENT '站点编码'
) ;