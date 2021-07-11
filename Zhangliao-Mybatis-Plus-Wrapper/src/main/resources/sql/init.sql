
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` bigint(20) NOT NULL  COMMENT 'ID',
  `no` varchar(32) NOT NULL DEFAULT '' COMMENT '站点编码'
) ;