# 一、事务的隔离级别
+ 事务的隔离级别：读已提交和可重复的（常用）
+ IsolationService类：defaultIsolation()函数

    1.线程1和2同时启动事务；
    
    2.线程1进行更新操作；
    
    3.线程2在线程1“更新后”，“提交事务”前(和后)进行两次查询；
    
    4.两次查询结果，根据数据库默认事务隔离级别而定；

> mysql默认隔离级别为RR（read repeated），两次查询结果相同（可重复读）；

> h2默认隔离级别为RC(read committed),两次查询结果不同（读已提交）；

# 二、事务的失效
+ 服务类没有交给Spring容器管理；
+ 从外部直接调用的函数没有事务；

