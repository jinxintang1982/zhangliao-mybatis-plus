SqlSession与事务的关系：

1、 同一事务中 不管调用多少次 mapper里的方法 ，最终都是用得同一个 sqlSession，即 一个事务中使用的是同一个sqlSession。
2、 如果没有开启事务，调用一次mapper里的方法将会新建一个 sqlSession 来执行方法。