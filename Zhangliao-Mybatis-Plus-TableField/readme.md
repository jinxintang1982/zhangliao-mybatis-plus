#### 说明
@TableField注解注解，用于在**插入**和**更新**数据时，进行指定字段的更新；

#### Db类
+ 字段上添加@TableField注解
```
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
``` 
#### 配置类
+ 添加配置类：继承于MetaObjectHandler
```
public class FillMetaObjectHandler implements MetaObjectHandler
```
+ 实现insertFill()或updateFill()函数
```
 @Override
 public void insertFill(MetaObject metaObject) {
    strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
 }
 
 @Override
 public void updateFill(MetaObject metaObject) {
    strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
 }
```
#### 表结构
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
  
  `version` int(10) DEFAULT 0,
  
  PRIMARY KEY (`id`)
  
) ;