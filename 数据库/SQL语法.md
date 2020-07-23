# 一、模式

数据如何存储、存储什么样的数据以及数据如何分解信息，数据库和表都有模式

## 二、创建表

`CREATE TABLE myTable (`

`ID int NOT NULL AUTO_INCREMENT,`

`col1 INT NOT NULL DEFAULT 1,`

`col2 DATE NULL,`

`PRIMARY KEY (ÌD`));`

# 三、修改表

添加列 

```mysql
ALTER TABLE myTable
ADD col CHAR(20);
```

删除列

```mysql
ALTER TABLE myTable
DROP COLUMN col;
```

删除表

```mysql
DROP TABLE myTable;
```

# 四、CRUD

1、选择不重复的属性 ：distinct
   例如：select distinct country from customer；

2、选择不等于某个值：NOT
   例如：select * from Customer where NOT City = ’Berlin'；

3、根据某个属性排序：order by
   例如：select * from Customer order by City （DESC降序）；

4、插入一条信息；insert into tables （） values （）； 

5、选择某个属性为空的信息：NULL
   例如：select * from Customer where City IS NULL；

6、选择最小的：MIN
   例如：select MIN（price） from customer；

7、筛选第二个字母为a的，_a%
   例如：select * from Customer where City like “_a%";

8、筛选第一个字母为a or c or s
   例如：select * from Customer where City like “[acs]%";

9、筛选第一个字母是一个区间范围内的 [];
   例如：select * from Customer where City like "[a-f]%";

10、筛选第一个字母不是某些指定的字母[^]
   例如：select * from Customer  where City like [^acf]%";

11、筛选包含指定的属性值的信息：in
   例如：select * from Customer where City in （‘France’，‘Norway）；

12、left join ,满足某个字段在两个表中相等
   例如：select * from Orders left join Customers on orders.CustomerID = Customers.CustomerID;

13、left join ,right join, inner join 的区别

14、创建一个数据库 create database tableName;
   例如：create database testDB;

15、删除一个数据库 drop databse tableName;
   例如：drop database testDB;

16、删除一个数据库表 drop table tableName；
   例如：drop table testDB；

17、truncate 删除
   例如：truncate table Persons；

18、给表新增加一个字段 ALTER TABLE TABLEName ADD 字段名 字段类型;
    例如：ALTER TABLE Persons ADD Birthday DATE;

19、删除表的一个字段 ALTER TABLE DROP COLUMN 字段名字；
   例如：ALTER TABLE DROP COLUMN birthday；

# 五、过滤

where 操作符：=  <   >    <>!=    <=!>    >=!<   between  is NULL

# 六、计算字段

在数据库服务器完成数据的转化和格式化工作比客户端快得多，并且抓换和格式化后的数据量更少 可以减少网络通信量

CONCAT（）用于连接两个字段，许多数据库会使用空格把一个值填充为列宽，因此连接的结果会出现一些不必要的空格，使用TRIM（）去除首尾空格

```mysql
SELECT CONCAT(TRIM(COl1),'(',TRIM(col2),')') AS concat_Col 
FROM TABLE;

```

# 七、链接

连接多个表，使用join关键字，并且条件语句使用on 而不是where

链接可以转换子查询，并且比子查询的效率要快

## 内链接

inner join

## 自连接

连接的表是自身

## 自然链接

把同名列通过等值测试链接起来；

内链接和自然链接的区别：内链接提供连接的列，而自然链接自动连接所有同名列

## 外链接

保留没有关联的行，left outer join ，right outer join 以及全外链接

# 八、组合查询

union 联合两个查询 M+N

# 九、视图

虚拟的表，本身不包含数据，不能进行索引操作；

优势：1、简化复杂sql操作，比如复杂链接

​            2、只使用实际表的一部分数据

​            3、更改数据格式和表示

# 十、存储过程

一系列SQL操作的批处理；代码封装，保证安全性；代码复用；预先编译

# 十一、游标

在存储过程中使用游标可以对一个结果进行移动遍历

游标主要用于交互式应用，其中用户需要对数据集中的任意行进行浏览和修改

使用游标步骤；

1、声明游标，这个过程没有实际检索出数据

2、打开游标

3、取出数据

4、关闭游标

```mysql
delimeter
create procedure myprocedure (out ret int)
     begin 
         declare done boolean default 0;
         declare mycursor cursor for
         select col1 from table;
         declare continue handler for sqlstate '0200' set done =1;
         
         open mycursor;
         repeat 
             fetch mycursor into ret;
             select ret;
         util done end repeat;
         close mycursor;
      end
 delimeter;
```

# 十二、触发器

必须指定在执行语句之前还是之后自动执行，之前用before，之后用after。before 用于数据验证和净化，after用于审计跟踪，将修改记录到另外一张表中

insert触发器包含一个名为NEW的虚拟表

```mysql
create TRIGGER myTrigger AFTER INSERT ON myTable
FOR EACH ROW SELECT NEW.col into @RESULT;

SELECT @RESULT;
```

DELETE 触发一个名为OLD的虚拟表，并且是只读的

UPDATE 触发包含OLD和NEW的虚拟表

# 十三、事务管理

事务：一组SQL语句

回退：撤销制定SQL的过程

提交：未存储的SQL写入数据库表

保留点（SAVEPOINT）：事务处理中设置的临时占位符（placeholder）对它发布回退

# 十四、字符集

字符集为字母和符号的集合；编码为某个字符集成员的内部表示；校对字符指定如何比较，主要用于排序和分组

