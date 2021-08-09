
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `no` varchar(32)  NULL DEFAULT '' COMMENT '站点编码',
  `name` varchar(32)  NULL DEFAULT '' COMMENT '名称',
  `lock_status` tinyint(1)  NULL DEFAULT '0' COMMENT '锁定状态:0未锁定，1锁定',
  `lock_job_id` bigint(20) DEFAULT '-1' COMMENT '锁定订单',
  `type` tinyint(2) DEFAULT NULL COMMENT '0发车 1空车 2送货',
  `description` varchar(255) DEFAULT NULL,
  `frame_no` varchar(40)  NULL DEFAULT '' COMMENT 'NO',
  `create_time` datetime NOT NULL  COMMENT '记录插入时间',
  `update_time`  datetime NOT NULL  COMMENT '记录更新时间',
  -- `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录插入时间',
  -- `update_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `version` int(10) DEFAULT 0,
  PRIMARY KEY (`id`)
) ;