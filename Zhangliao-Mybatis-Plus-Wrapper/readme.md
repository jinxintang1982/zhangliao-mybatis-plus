一、简介

上一节中，我们提到CRUD操作时，可以通过传入UpdateWrapper或QueryWrapper作为更新或查询的条件，从而获取到相应的操作结果，这一节，我们就来介绍一上两种Wrapper的具体使用方法；

QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件

二、AbstractWrapper

参数的统一说明：

- boolean condition：`boolean condition`表示该条件**是否**加入最后生成的sql中。

  例如：query.eq(StringUtils.isNotBlank(name), Entity::getName, name) ，当name为空时，这个eq不会生成到where条件中；

- null2IsNull:当参数中Map的value值为空时，是否参添加到where条件：

  例如：

  默认情况：allEq({id:1,name:"老王",age:null})`--->`id = 1 and name = '老王' and age is null

  null2IsNull == false时：allEq({id:1,name:"老王",age:null}, false)--->id = 1 and name = '老王'

用于生成where条件：

| 条件        | 函数                                                         | 说明                                                         |
| ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| allEq       | allEq(Map<R, V> params) <br>allEq(Map<R, V> params, boolean null2IsNull)<br> allEq(boolean condition, Map<R, V> params, boolean null2IsNull) | 全部 =，map中的参数，生成where时，and相连；                  |
| eq          | eq(R column, Object val) <br>eq(boolean condition, R column, Object val) | 等于 =                                                       |
| ne          | ne(R column, Object val) <br>ne(boolean condition, R column, Object val) | 不等于 <>                                                    |
| gt          | gt(R column, Object val) <br>gt(boolean condition, R column, Object val) | 大于 >                                                       |
| ge          | ge(R column, Object val) <br>ge(boolean condition, R column, Object val) | 大于等于 >=                                                  |
| lt          | lt(R column, Object val)<br> lt(boolean condition, R column, Object val) | 小于 <                                                       |
| le          | le(R column, Object val) <br>le(boolean condition, R column, Object val) | 小于等于 <=                                                  |
| between     | between(R column, Object val1, Object val2)<br>between(boolean condition, R column, Object val1, Object val2) | BETWEEN 值1 AND 值2                                          |
| notBetween  | notBetween(R column, Object val1, Object val2)<br>notBetween(boolean condition, R column, Object val1, Object val2) | NOT BETWEEN 值1 AND 值2                                      |
| like        | like(R column, Object val)<br/> like(boolean condition, R column, Object val) | LIKE '%值%'                                                  |
| notLike     | notLike(R column, Object val) <br/>notLike(boolean condition, R column, Object val) | notLike                                                      |
| likeLeft    | likeLeft(R column, Object val) likeLeft(boolean condition, R column, Object val) | LIKE '%值'                                                   |
| likeRight   | likeRight(R column, Object val) likeRight(boolean condition, R column, Object val) | LIKE '值%'                                                   |
| isNull      | isNull(R column)<br/> isNull(boolean condition, R column)    | 字段 IS NULL                                                 |
| isNotNull   | isNotNull(R column)<br/> isNotNull(boolean condition, R column) | 字段 IS NOT NULL                                             |
| in          | in(R column, Collection<?> value)<br/>in(boolean condition, R column,Collection< ? >value<br>in(R column, Object... values) <br/>in(boolean condition, R column, Object... values) | 字段 IN (value.get(0), value.get(1), ...)<br>in("age",{1,2,3})`--->`age in (1,2,3)<br>in("age", 1, 2, 3)`--->`age in (1,2,3) |
| notIn       | notIn(R column, Collection<?> value) notIn(boolean condition, R column, Collection<?> value)<br/>notIn(R column, Object... values) <br/>notIn(boolean condition, R column, Object... values) | 字段 NOT IN (value.get(0), value.get(1), ...)                |
| inSql       | inSql(R column, String inValue) <br/>inSql(boolean condition, R column, String inValue) | 字段 IN ( sql语句 )<br/>例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)<br/>例: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3) |
| notInSql    | notInSql(R column, String inValue) <br/>notInSql(boolean condition, R column, String inValue) | 字段 NOT IN ( sql语句 )                                      |
| groupBy     | groupBy(R... columns) <br/>groupBy(boolean condition, R... columns) | 分组：GROUP BY                                               |
| orderByAsc  | orderByAsc(R... columns) <br/>orderByAsc(boolean condition, R... columns) | ORDER BY 字段, ... ASC                                       |
| orderByDesc | orderByDesc(R... columns) <br/>orderByDesc(boolean condition, R... columns) | 排序：ORDER BY 字段, ... DESC                                |
| orderBy     | orderBy(boolean condition, boolean isAsc, R... columns)      | 排序：ORDER BY 字段                                          |
| having      | having(String sqlHaving, Object... params)<br/> having(boolean condition, String sqlHaving, Object... params) | HAVING ( sql语句 )<br>having("sum(age) > 10")->having sum(age) > 10 |
| func        | func(Consumer<Children> consumer) <br/>func(boolean condition, Consumer<Children> consumer) | func 方法(主要方便在出现if...else下调用不同方法能不断链) 例: `func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})` |
| or          | or() or(boolean condition)or(Consumer<Param> consumer) <br/>or(boolean condition, Consumer<Param> consumer) | 拼接 OR<br>eq("id",1).or().eq("name","老王")->id = 1 or name = '老王' |
| and         | and(Consumer<Param> consumer) <br/>and(boolean condition, Consumer<Param> consumer) | AND 嵌套<br>and(i -> i.eq("name", "李白").ne("status", "活着"))`--->`and (name = '李白' and status <> '活着') |
| nested      | nested(Consumer<Param> consumer) <br/>nested(boolean condition, Consumer<Param> consumer) | 正常嵌套 不带 AND 或者 OR<br>nested(i -> i.eq("name", "李白").ne("status", "活着"))`--->`(name = '李白' and status <> '活着') |
| apply       | apply(String applySql, Object... params) <br/>apply(boolean condition, String applySql, Object... params) | 拼接 sql<br>例: apply("id = 1")--->id = 1<br/>例: apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")<br/>例: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'") |
| last        | last(String lastSql)<br/> last(boolean condition, String lastSql) | 无视优化规则直接拼接到 sql 的最后<br>只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用<br>last("limit 1") |
| exists      | exists(String existsSql) <br/>exists(boolean condition, String existsSql) | 拼接 EXISTS <br>例: exists("select id from table where age = 1")--->exists (select id from table where age = 1) |
| notExists   | notExists(String notExistsSql) <br/>notExists(boolean condition, String notExistsSql) | 拼接 NOT EXISTS ( sql语句 )<br>例: notExists("select id from table where age = 1")--->not exists (select id from table where age = 1) |

三、QueryWrapper

Select设置查询字段

```java
select(String... sqlSelect)
select(Predicate<TableFieldInfo> predicate)
select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
```

第二种和第三种用法可以针对字段多的情况，用于排除字段的方式，例:

```java
queryWrapper.select(info -> info.getProperty().startsWith("test"));
```

```java
queryWrapper.select(User.class, info->!info.getColumn()
            .equals("email") && !info.getColumn().equals("create_time"));
```

四、UpdateWrapper

1. 设置SET 字段

```
set(String column, Object val)
set(boolean condition, String column, Object val)
```

- 例: `set("name", "老李头")`
- 例: `set("name", "")`--->数据库字段值变为**空字符串**
- 例: `set("name", null)`--->数据库字段值变为`null`

2.设置SET的SQL

```java
setSql(String sql)
```

例:setSql("name = '老李头'")