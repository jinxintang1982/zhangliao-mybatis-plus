### 一、简介

MyBatis-Plus (opens new window)（简称 MP）是一个 MyBatis (opens new window)的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

我们可以通过MP提供的两种类对数据库表进行CRUD操作；

- ServiceImpl类：可以进行save,saveOrUpdate,Remove,Update,get,list,page,count,chain操作；
- Mapper类：可以进行insert，delete，update，select操作；

### 二、函数列表


##### ServiceImpl中实现的方法：

| 操作         | 函数                                                         |
| ------------ | ------------------------------------------------------------ |
| save         | //保存一个实体对象<br>1.boolean save(T entity);<br/>//批量保存一组实体对象<br>2.boolean saveBatch(Collection<T> entityList)<br/>//批量保存一组实体对象中的batchSize个3.boolean saveBatch(collection<T> entityList,int batchSize); |
| saveOrUpdate | 1.boolean saveOrUpdate(T entity);<br/>2.boolean saveOrUpdate(T entity,Wrapper<T> updateWrapper)<br/>3.boolean saveOrUpdateBatch(Collection<T> entityList);<br/>4.boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);<br>注：saveOrUpdate操作根据entity对象中定义"主键"属性，查找数据库中对应的数据记录；<br/>- 如果entity对象中的”主键“，找到对应的数据库记录，则更新；<br/>- 如果entity对象中的”主键“，没有找到对应的数据库记录，则保存该记录； |
| Remove       | //根据queryWrapper进行删除；(queryWrapper会在下一节中介绍)<br>1.boolean remove(Wrapper<T> queryWrapper);<br/>//根据指定id进行删除；<br>2.boolean removeById(Serializable id);<br/>//根据 columnMap 条件，删除记录<br>3.boolean removeByMap(Map<String, Object> columnMap);<br/>//根据idList列表中指定的多个Id主键进行删除；4.boolean removeByIds(Collection<? extends Serializable> idList);<br>注：使用columnMap时，应使用数据库列名，而不是实体类的属性名，例如： {"station_no":"10010","sex":"0"} |
| Update       | //根据updateWrapper进行更新；(updateWrapper在下一节中介绍);<br/>1.boolean update(Wrapper<T> updateWrapper);<br/>//根据whereWrapper进行更新；(whereWrapper在下一节中介绍)<br/>2.boolean update(T updateEntity, Wrapper<T> whereWrapper);<br/>//根据主键id进行更新；<br/>3.boolean updateById(T entity);<br/>//根据entityList中实例的”主键属性“，进行更新；<br>4.boolean updateBatchById(Collection<T> entityList);<br/>//根据entityList中实例的”主键属性“，更新batchSize数量的数据；<br>5.boolean updateBatchById(Collection<T> entityList, int batchSize); |
| Get          | //根据主键id进行查找；<br/>1.T getById(Serializable id);<br/>//根据queryWrapper进行查询<br>2.T getOne(Wrapper<T> queryWrapper);<br/>//在函数2的基础上，可以选择是否抛出异常；<br/>3.T getOne(Wrapper<T> queryWrapper, boolean throwEx);<br/>//根据queryWrapper进行查询,返回Map中String为属性，Object为值<br/>4.Map<String, Object> getMap(Wrapper<T> queryWrapper);<br/>//根据queryWrapper进行查询，并对查询到的结果执行参数2定义的方法<br/>5.<V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);<br>注：<br>在使用getOne()时，如果一条都没有查到，则不会抛出异常，返回null；<br/>getObj(queryWrapper, obj -> "用户Id：" +  obj.toString()); |
| List         | 查询所有<br/>1.List<T> list();<br/>根据queryWrapper查询；<br/>2.List<T> list(Wrapper<T> queryWrapper);<br/>.根据id列表查询；<br/>3.Collection<T> listByIds(Collection<? extends Serializable> idList);<br/>根据map中的属性和值进行查处<br/>4.Collection<T> listByMap(Map<String, Object> columnMap);<br/>查询所有，返回map<br>5.List<Map<String, Object>> listMaps();<br/>根据queryWrapper查询，返回map<br/>6.List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);<br/>查询所有，返回Object<br/>7.List<Object> listObjs();<br/>查询所有，返回第一个属性字段，并支持function方法；<br/>8.<V> List<V> listObjs(Function<? super Object, V> mapper);<br/>根据queryWrapper查询，返回object<br/>9.List<Object> listObjs(Wrapper<T> queryWrapper);<br/>根据queryWrapper查询，返回第一个属性字段，并执行function方法；<br>10.<V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper); |
| Page         | 1.IPage<T> page(IPage<T> page);<br/>2.IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);<br/>3.IPage<Map<String, Object>> pageMaps(IPage<T> page);<br/>4.IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper); |
| Count        | int count();<br/>int count(Wrapper<T> queryWrapper);         |
| Chain        | 1.QueryChainWrapper<T> query();<br/>query().eq("column", value).one();<br/>2.LambdaQueryChainWrapper<T> lambdaQuery(); <br/>lambdaQuery().eq(Entity::getId, value).list();<br/>3.UpdateChainWrapper<T> update();<br/>update().eq("column", value).remove();<br/>4.LambdaUpdateChainWrapper<T> lambdaUpdate();<br/>lambdaUpdate().eq(Entity::getId, value).update(entity); |

##### Mapper中定义的方法

| 操作   | 函数                                                         |
| ------ | ------------------------------------------------------------ |
| insert | int insert(T entity);                                        |
| Delete | int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);<br/>int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);<br/>int deleteById(Serializable id);<br/>int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap); |
| Update | int update(@Param(Constants.ENTITY) T updateEntity, @Param(Constants.WRAPPER) Wrapper<T> whereWrapper);<br>  int updateById(@Param(Constants.ENTITY) T entity); |
| Select | T selectById(Serializable id);<br/>T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);<br/>List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);<br/>List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);<br/>Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper); |

