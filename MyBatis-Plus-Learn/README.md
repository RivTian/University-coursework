## 1、了解 Mybatis-plus

### 1.1、Mybatis-Plus 介绍

MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

官网：https://mybatis.plus/ 或 https://mp.baomidou.com/

![=](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302171647388.png)

> MP愿景
>
> 我们的愿景是成为 MyBatis 最好的搭档，就像 魂斗罗 中的 1P、2P，基友搭配，效率翻倍。

![](https://mybatis.plus/img/relationship-with-mybatis.png)

### 1.2、代码以及文档 

文档地址：https://mybatis.plus/guide/ 源码地址：https://github.com/baomidou/mybatis-plus

### 1.3、特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作



### 1.4、架构

![framework](https://mybatis.plus/img/mybatis-plus-framework.jpg)



## 2、快速上手

对于Mybatis整合MP有常常有三种用法，分别是Mybatis+MP、Spring+Mybatis+MP、Spring Boot+Mybatis+MP。

### 2.1、创建数据库以及表

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302171651684.png)

```SQL
-- 创建测试表
CREATE DATABASE `mybatis_plus` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use `mybatis_plus`;
CREATE TABLE `user` (
`id` bigint(20) NOT NULL COMMENT '主键ID',
`name` varchar(30) DEFAULT NULL COMMENT '姓名',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入测试数据
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

## 2.2、创建 SpringBoot 工程

使用 Spring Initializr 快速初始化一个 Spring Boot 工程

### 引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.5</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <groupId>com.riotian</groupId>
  <artifactId>MyBatis-Plus-Learn</artifactId>
  <version>0.0.1-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <scope>compile</scope>
    </dependency>

    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.4.2</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.20</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.76</version>
    </dependency>

    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <scope>runtime</scope>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.1.23</version>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.4.5</version>
      </plugin>
    </plugins>
  </build>

</project>
```

### 编写 application.yaml 文件

```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: kokoro
```

### 启动类设置

在Spring Boot启动类中添加@MapperScan注解，扫描mapper包

```java
@SpringBootApplication
@MapperScan("com.riotian.mplearn.mapper") // mapper 包存放的路径
public class MyBatisPlusLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusLearnApplication.class, args);
    }

}
```

### 添加实体

```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
```

User类编译之后的结果：

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302172120874.png)

### 添加 mapper

> BaseMapper是MyBatis-Plus提供的模板mapper，其中包含了基本的CRUD方法，泛型为操作的实体类型

```java
@Repository // 保障动态注入成功
public interface UserMapper extends BaseMapper<User> {

}
```

### 测试

```java
@SpringBootTest
public class MP_Test {

    @Autowired
    private UserMapper userMapper; // @Repository 保障注入成功

    @Test
    public void testSelectList(){
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302172123517.png)

> IDEA若在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确的执行。 为了避免报错，可以在mapper接口上添加 @Repository 注解

### 添加日志

在application.yml中配置日志输出

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302172125974.png)



## 3、基本 CRUD

### 3.1、BaseMapper

MyBatis-Plus中的基本CRUD在内置的BaseMapper中都已得到了实现，我们可以直接使用，接口如下：

> Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能

```java
package com.baomidou.mybatisplus.core.mapper;

public interface BaseMapper<T> {
    //插入一条记录  参数：实体  返回：int
    Integer insert(T entity);
 
    //根据 ID 删除  参数：主键ID  返回：int
    Integer deleteById(Serializable id);
    
     //根据 columnMap 条件，删除记录  参数：表字段 map 对象  返回：int
    Integer deleteByMap(@Param("cm") Map<String, Object> columnMap);
 
     //根据 entity 条件，删除记录  参数：实体对象封装操作类（可以为 null）  返回：int
    Integer delete(@Param("ew") Wrapper<T> wrapper);
 
     //删除（根据ID 批量删除）  参数：主键ID列表  返回：int
    Integer deleteBatchIds(List<? extends Serializable> idList);
 
     //根据 ID 修改  参数：实体对象  返回：int
    Integer updateById(T entity);
 
     //根据 whereEntity 条件，更新记录  参数：实体对象,实体对象封装操作类（可以为 null） 返回：int
    Integer update(@Param("et") T entity, @Param("ew") Wrapper<T> wrapper);
 
     //根据 ID 查询  参数：主键ID  返回：T
    T selectById(Serializable id);
 
     //查询（根据ID 批量查询）  参数：主键ID列表  返回：List<T>
    List<T> selectBatchIds(List<? extends Serializable> idList);
 
     //查询（根据 columnMap 条件）  参数：表字段 map 对象  返回：List<T>
    List<T> selectByMap(@Param("cm") Map<String, Object> columnMap);
 
     //根据 entity 条件，查询一条记录  参数：实体对象  返回：T
    T selectOne(@Param("ew") T entity);

     //根据 Wrapper 条件，查询总记录数  参数：实体对象  返回：int
    Integer selectCount(@Param("ew") Wrapper<T> wrapper);
 
     //根据 entity 条件，查询全部记录  参数：实体对象封装操作类（可以为 null）  返回：List<T>
    List<T> selectList(@Param("ew") Wrapper<T> wrapper);
 
     //根据 Wrapper 条件，查询全部记录  参数：实体对象封装操作类（可以为 null） 返回：List<T>
    List<Map<String, Object>> selectMaps(@Param("ew") Wrapper<T> wrapper);
 
     //根据 Wrapper 条件，查询全部记录  参数：实体对象封装操作类（可以为 null）  返回：List<Object>
    List<Object> selectObjs(@Param("ew") Wrapper<T> wrapper);
 
    /** 
     * 用法：(new RowBounds(offset, limit), ew);
     * 根据 entity 条件，查询全部记录（并翻页）
     * @param rowBounds
     * 分页查询条件（可以为 RowBounds.DEFAULT）
     * @param wrapper
     * 实体对象封装操作类（可以为 null）
     * @return List<T>
     */
     //根据 ID 删除  参数：主键ID  返回：int
    List<T> selectPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);
 
    /** -- 不常用,
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     * @param rowBounds
     * 分页查询条件（可以为 RowBounds.DEFAULT）
     * @param wrapper
     * 实体对象封装操作类
     * @return List<Map<String, Object>>
     */
     //根据 ID 删除  参数：主键ID  返回：int
    List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);
}

```

```java
// 用法举例
// 接口：
public interface UserDao extends BaseMapper<User> {
	//这里面不用做任何操作
}

//具体实现方法中:
QueryWrapper<User> queryWrapper=new QueryWrapper<>();
queryWrapper.lambda().eq(User::getName,"zhangsan");
List<User> userList = UserDao.selectList(queryWrapper); //调用UserDao中的方法
```

### 3.2、插入

```java
@Test
public void Insert() {
    User user = new User();
    user.setAge(23);
    user.setName("zhangsan");
    user.setEmail("zhangsan@gmail.com");
    // insert into user(id,name,age,email) values (?,?,?,?)
    int count = userMapper.insert(user);
    System.out.println("受影响的行数"+count);
    // 1626576039783362562
    System.out.println("ID 自动获取: " + user.getId());
}
```

> 最终执行的结果，所获取的id为 1626576039783362562 
>
> 这是因为MyBatis-Plus在实现插入数据时，会默认基于雪花算法的策略生成id

### 3.3、删除

#### 3.3.1 by ID 删除

```java
@Test
public void testDeleteById() {
    // 通过 id 删除用户信息
    // Delete from user where id = ?
    int count = userMapper.deleteById(1626576039783362562L);
    System.out.println("受影响的行数"+count);
}
```

#### 3.3.2 by ID 批量删除记录

```java
@Test
public void testDeleteBatchIds() {
    // 通过多个 id 批量删除
    // Delete from user where id in (?, ?, ?)
    List<Long> idLists = Arrays.asList(1L, 2L, 3L);
    int count = userMapper.deleteBatchIds(idLists);
    System.out.println("受影响的行数"+count);
}
```

#### 3.3.3 by map 条件删除记录

```java
@Test
public void testDeleteByMap(){
    //根据map集合中所设置的条件删除记录
    //DELETE FROM user WHERE name = ? AND age = ?
    Map<String,Object> map = new HashMap<>();
    map.put("age",23);
    map.put("name","zhangsan");
    int count = userMapper.deleteByMap(map);
    System.out.println("受影响的行数"+count);
}
```

### 3.4、修改

```java
@Test
public void testUpdateById(){
    User user = new User();
    user.setId(4L);
    user.setAge(10);
    user.setEmail(null);
    int count = userMapper.updateById(user);
    System.out.println("受影响的行数"+count);
}
```

### 3.5、查询

#### 3.5.1 根据id查询用户信息

```java
@Test
public void testSelectById(){
    //根据id查询用户信息
    //SELECT id,name,age,email FROM user WHERE id=?
    User user = userMapper.selectById(4L);
    System.out.println(user);
}
```

#### 3.5.2 根据多个id查询多个用户信息

```java
@Test
public void testSelectBatchIds(){
    //根据多个id查询多个用户信息
    //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
    List<Long> idList = Arrays.asList(4L, 5L);
    List<User> list = userMapper.selectBatchIds(idList);
    list.forEach(System.out::println);
}
```

#### 3.5.3 通过map条件查询用户信息

```java
@Test
public void testSelectByMap(){
    //通过map条件查询用户信息
    //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    Map<String, Object> map = new HashMap<>();
    map.put("age", 22);
    map.put("name", "admin");
    List<User> list = userMapper.selectByMap(map);
    list.forEach(System.out::println);
}
```

#### 3.5.3 查询所有数据

```java
@Test
public void testSelectList(){
    //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
    userMapper.selectList(null).forEach(System.out::println);
}
```



> 通过观察BaseMapper中的方法，大多方法中都有Wrapper类型的形参，此为条件构造器，可针对于SQL语句设置不同的条件，若没有条件，则可以为该形参赋值null，即查询（删除/修改）所有数据

### 3.6、通用Service

> 说明:
>
> - 通用 Service CRUD 封装[IService (opens new window)](https://gitee.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-extension/src/main/java/com/baomidou/mybatisplus/extension/service/IService.java)接口，进一步封装 CRUD 采用 `get 查询单行` `remove 删除` `list 查询集合` `page 分页` 前缀命名方式区分 `Mapper` 层避免混淆，
> - 泛型 `T` 为任意实体对象
> - 建议如果存在自定义通用 Service 方法的可能，请创建自己的 `IBaseService` 继承 `Mybatis-Plus` 提供的基类
> - 对象 `Wrapper` 为 [条件构造器](https://baomidou.com/01.指南/02.核心功能/wrapper.html)

### 3.7、创建Service接口和实现类

```java
// UserService.java
/**
 * UserService继承IService模板提供的基础功能
 */
public interface UserService extends IService<User> {
}
```

```java
// UserServiceImpl.java
/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements
    UserService {
    
}
```

### 3.8、测试查询记录数

```java
@Autowired
private UserService userService;

@Test
public void testGetCount(){
    System.out.println("总记录数：" + userService.count());
}
```

### 3.9、测试批量插入

```java
@Test
public void testSaveBatch(){
    // SQL长度有限制，海量数据插入单条SQL无法实行，
    // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
    ArrayList<User> users = new ArrayList<>();
    for(int i = 0;i < 5;i ++ ) {
        User user = new User();
        user.setName("demo-0"+i);
        user.setAge(20 + i);
        users.add(user);
    }
    //SQL:INSERT INTO t_user ( username, age ) VALUES ( ?, ? )
    userService.saveBatch(users);
}
```

## 4、常用注解

### 4.1、@TableName

> 经过以上的测试，在使用MyBatis-Plus实现基本的CRUD时，我们并没有指定要操作的表，
>
> 只是在 Mapper接口继承BaseMapper时，设置了泛型User，而操作的表为user表 
>
> 由此得出结论，MyBatis-Plus在确定操作的表时，
>
> 由BaseMapper的泛型决定，即实体类型决 定，且默认操作的表名和实体类型的类名一致

#### 4.1.1、Question

> 若实体类类型的类名和要操作的表的表名不一致，会出现什么问题？
>
> 我们将数据库中表user更名为t_user，测试查询功能
>
> 程序抛出异常，Table 'mybatis_plus.user' doesn't exist，因为现在的表名为 t_user，而默认操作的表名和实体类型的类名一致，即user表



#### 4.1.2、通过@TableName解决问题

> 在实体类类型上添加@TableName("t_user")，标识实体类对应的表，即可成功执行SQL语句

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180928351.png)

#### 4.1.3、通过全局配置解决问题

> 在开发的过程中，我们经常遇到以上的问题，即实体类所对应的表都有固定的前缀，例如 `t_` 或 `tbl_` 
>
> 此时，可以使用 MP 提供的全局配置，为实体类所对应的表名设置默认的前缀，那么就 不需要在每个实体类上通过@TableName标识实体类对应的表

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
```

### 4.2、@Tabled

> 经过以上的测试，MyBatis-Plus在实现CRUD时，会默认将id作为主键列，并在插入数据时，默认基于[雪花算法的策略生成id](https://www.cnblogs.com/RioTian/p/17131704.html)

#### 4.2.1、Question

> 若实体类和表中表示主键的不是id，而是其他字段，例如uid，MyBatis-Plus会自动识别uid为主键列吗？ 
>
> 我们实体类中的属性id改为uid，将表中的字段id也改为uid，测试添加功能
>
> 程序抛出异常，Field 'uid' doesn't have a default value，说明MyBatis-Plus没有将uid作为主键 赋值

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180931636.png)

#### 4.2.2、通过@TableId解决问题

在实体类中uid属性上通过@TableId将其标识为主键，即可成功执行SQL语句



|            值             |                             描述                             |
| :-----------------------: | :----------------------------------------------------------: |
| IdType.ASSIGN_ID（默 认） |   基于雪花算法的策略生成数据id，与数据库id是否设置自增无关   |
|        IdType.AUTO        | 使用数据库的自增策略，注意，该类型请确保数据库设置了id自增， 否则无效 |

配置全局主键策略：

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
      # 配置MyBatis-Plus的主键策略
      id-type: ASSIGN_ID
```



### 4.3、雪花算法

详细介绍：[Here](https://www.cnblogs.com/RioTian/p/17131704.html)



### 4.4、@TableField

> 经过以上的测试，我们可以发现，MyBatis-Plus在执行SQL语句时，要保证实体类中的属性名和 表中的字段名一致
>
> 如果实体类中的属性名和字段名不一致的情况，会出现什么问题呢？

#### 4.4.1 情况一

> 若实体类中的属性使用的是驼峰命名风格，而表中的字段使用的是下划线命名风格
>
> 例如实体类属性userName，表中字段user_name
>
> 此时MyBatis-Plus会自动将下划线命名风格转化为驼峰命名风格
>
> 相当于在MyBatis中配置

```yaml
mybatis-plus:
  configuration:
    #在映射实体或者属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: ASSIGN_ID
```

#### 4.4.2 情况二

> 若实体类中的属性和表中的字段不满足情况1 
>
> 例如实体类属性name，表中字段username 
>
> 此时需要在实体类属性上使用 `@TableField("username")` 设置属性所对应的字段名

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180940011.png)



### 4.5、@TableLogic

#### 4.5.1 逻辑删除

* 物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据
* 逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库 中仍旧能看到此条数据记录
* 使用场景：可以进行数据恢复

#### 4.5.2 实现逻辑删除

> Step1：数据库中创建逻辑删除状态列，设置默认值为0

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180946187.png)

> Step2：实体类中添加逻辑删除属性

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180947563.png)

> Step3：测试

测试删除功能，真正执行的是修改

```sql
# testDeleteBatchIds 这个test函数
UPDATE t_user SET is_deleted=1 WHERE id=? AND is_deleted=0
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180953507.png)

测试查询功能，被逻辑删除的数据默认不会被查询

```sql
# testSelectList 函数
SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180953388.png)





## 5、条件构造器和常用接口

### 5.1、wapper介绍

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302180957098.png)

| name                  | explain                                  |
| :-------------------- | :--------------------------------------- |
| Wrapper               | 条件构造抽象类，最顶端父类               |
| AbstractWrapper       | 用于查询条件封装，生成 sql 的 where 条件 |
| QueryWrapper          | 查询条件封装                             |
| UpdateWrapper         | Update 条件封装                          |
| AbstractLambdaWrapper | 使用Lambda 语法                          |
| LambdaQueryWrapper    | 用于Lambda语法使用的查询Wrapper          |
| LambdaUpdateWrapper   | Lambda 更新封装Wrapper                   |

### 5.2. QueryWrapper

#### 例1：组装查询条件

```java
@Test
public void test01(){
    //查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
    //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("username", "a")
        .between("age",20,30)
        .isNotNull("email");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(user -> System.out.println(user));
    //users.forEach(System.out::println);
}
```

> 由于前面设置了 is_deleted 逻辑删除，所以这里查询到的是，为经过逻辑删除的信息

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181009854.png)

#### 例2：组装排序条件

```java
@Test
public void test02(){
    //按照年龄升序查询用户，如果年龄相同则按id 降序排列
    //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age ASC,id DESC
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("age").orderByAsc("id");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181012657.png)

#### 例3：组装删除条件

```java
@Test
public void test03() {
    //删除email为空的用户
    //UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
    // Delete from t_user where (email is NULL)
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.isNull("email");
    // 条件构造器也可以构建删除语句的条件
    System.out.println("受影响的行数" + userMapper.delete(queryWrapper));
}
```

> 由于前面设置了逻辑删除，所以这里的删除，就仅是逻辑删除，而不是物理删除

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181016053.png)

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181017393.png)



#### 例4：条件的优先级

```java
@Test
public void test04() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
    //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND age > ? OR email IS NULL)
    queryWrapper
        .like("username","a")
        .gt("age",20)
        .isNull("email");
    User user = new User();
    user.setAge(18);
    user.setEmail("user@fickler.com");
    System.out.println("受影响的行数" + userMapper.update(user,queryWrapper));
}
```

> querywrapper 不仅可以用于查询，也可以用于更改

```java
@Test
public void test05() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
    //UPDATE t_user SET age=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
    //lambda表达式内的逻辑优先运算
    queryWrapper.like("username", "a").and(i -> i.gt("age", 20).or().isNull("email"));
    User user = new User();
    user.setAge(18);
    user.setEmail("user@fickler.com");
    System.out.println("受影响的行数" + userMapper.update(user,queryWrapper));
}
```

#### 例5：组装select子句

```java
@Test
public void test06(){
    //查询用户信息的username和age字段
    //SELECT username,age FROM t_user WHERE is_deleted=0
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("username", "age");
    //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到列值为null
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181027359.png)

#### 例6：实现子查询

```java
@Test
public void test07(){
    //查询id小于等于3的用户信息
    //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 3))
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.inSql("id", "select id from user where id <= 3");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```



### 5.3. UpdateWrapper

```java
@Test
public void test08() {
    //将（年龄大于20或邮箱为null）并且用户名中包含有lisi的用户信息修改
    //组装set子句以及修改条件
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    //lambda表达式内的逻辑优先运算
    updateWrapper
        .set("age","18")
        .set("email","user@gmail.com")
        .like("username","lisi")
        .and(i -> i.gt("age",20).or().isNull("email"));
    //这里必须要创建User对象，否则无法应用自动填充。如果没有自动填充，可以设置为null
    //UPDATE t_user SET username=?, age=?,email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
    //User user = new User();
    //user.setName("张三");
    //int result = userMapper.update(user, updateWrapper);
    //UPDATE t_user SET age=?,email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
    System.out.println(userMapper.update(null, updateWrapper));
}
```

### 5.4. Condition

> 在真正开发的过程中，组装条件是常见的功能，而这些条件数据来源与用户输入，是可选的，因此我们在组装这些条件时，必须先判断用户是否选择了这些条件，若选择则需要组装该条件，若没有选择则一定不能组装，以避免影响 SQL 执行的结果

#### 5.4.1 思路一

```java
@Test
public void test09() {
    //定义查询条件，有可能为null（用户为输入）
    String username = null;
    Integer ageBegin = 10;
    Integer ageEnd = 24;
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    //StringUtils.isNotBlank()判断字符串是否不为空且长度不为0且不由空白符(whitespace)构成
    if (StringUtils.isNotBlank(username)) {
        queryWrapper.like("username", "a");
    }
    if (ageBegin != null) {
        queryWrapper.ge("age", ageBegin);
    }
    if (ageEnd != null) {
        queryWrapper.le("age", ageEnd);
    }
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(user -> System.out.println(user));
}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181041276.png)

#### 5.4.2 思路二

> 上面的实现方案没有问题，但是代码比较复杂，我们可以使用带 condition 参数的重载方法构建查询条件，简化代码的编写

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181044834.png)

```java
@Test
public void test10() {
    //定义查询条件，有可能为null（用户为输入）
    String username = null;
    Integer ageBegin = 10;
    Integer ageEnd = 24;
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    //StringUtils.isNotBlank()判断字符串是否不为空且长度不为0且不由空白符(whitespace)构成
    queryWrapper.like(StringUtils.isNotBlank(username), "user_name", "a").ge(ageBegin != null, "age", ageBegin).le(ageEnd != null, "age", ageEnd);
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

### 5.5、 LambdaQueryWrapper

![](https://ask.qcloudimg.com/http-save/yehe-10074098/5ed2dea4ee193364e91bcfacd042af48.png?imageView2/2/w/1620)

我们在往构造器里设置参数的时候，有可能会将字段名写错，这个时候就可以用到LambdaQueryWrapper和LambdaUpdateWrapper了。

```java
@Test
public void test11() {

    //定义查询条件，有可能为null（用户为输入）
    String username = null;
    Integer ageBegin = 10;
    Integer ageEnd = 24;
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //StringUtils.isNotBlank()判断字符串是否不为空且长度不为0且不由空白符(whitespace)构成
    lambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, "a").ge(ageBegin != null, User::getAge, ageBegin).le(ageEnd != null, User::getAge, ageEnd);
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
    List<User> users = userMapper.selectList(lambdaQueryWrapper);
    users.forEach(user -> System.out.println(user));

}

        queryWrapper
            .like(StringUtils.isNotBlank(username),User::getName,username)
            .ge(ageBegin != null, User::getAge, ageBegin)
            .le(ageEnd != null, User::getAge, ageEnd);
```

![](https://ask.qcloudimg.com/http-save/yehe-10074098/fe6d6deb1caeedb480f2565bfbb01e59.png?imageView2/2/w/1620)

> 使用 `User::getName` 的方式来确定属性名，一定程度上避免了实体类中属性名和数据库中的属性名不同的问题

### 5.6. LambdaUpdateWrapper

```java
@Test
public void test12() {
    // 组装 set 子句
    LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper
        .set(User::getAge, 18)
        .set(User::getEmail, "user@Gmail.com")
        .like(User::getName,"a")
        .and(i -> i.lt(User::getAge,24).or().isNull(User::getEmail));
    //lambda表达式内的逻辑优先运算
    User user = new User();
    System.out.println("受影响的行数: " + userMapper.update(user,updateWrapper));
}
```



## 6、MP插件：Interceptor

### 6.1 分页插件

> MyBatis Plus自带分页插件，只要简单的配置即可实现分页功能

#### 6.1.1 属性介绍

| 属性名   | 类型     | 默认值 | 描述                                                         |
| -------- | -------- | ------ | ------------------------------------------------------------ |
| overflow | boolean  | false  | 溢出总页数后是否进行处理(默认不处理,参见 `插件#continuePage` 方法) |
| maxLimit | Long     |        | 单页分页条数限制(默认无限制,参见 `插件#handlerLimit` 方法)   |
| dbType   | DbType   |        | 数据库类型(根据类型获取应使用的分页方言,参见 `插件#findIDialect` 方法) |
| dialect  | IDialect |        | 方言实现类(参见 `插件#findIDialect` 方法)                    |

#### 6.1.2 添加配置类

```java
// MPConfig 配置类
@Configuration
@MapperScan("com.riotian.mplearn.mapper") //可以将主类中的注解移到此处
public class MPConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
```

#### 6.1.3 测试

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302181116046.png)

### 6.2、XML分页

在有些业务场景下吗，分页插件无法满足我们的需求的时候，这个时候就可以选择自定义分页。

> 实际上不使用这个方法。
>
> 在 Impl 中实现方法即可。

#### 6.2.1 UserMapper中定义接口方法

```java

```



#### 6.2.2 UserMapper.xml中编写SQL



#### 6.2.3 测试





### 6.3 乐观锁

了解乐观锁，我们先来了解它的使用场景。

#### 6.3.1 **场景**

假如，我和你都要去操作数据库的同一个数值，比如商品的价格，现在商品的价格定在100，你要对其进行增加，而我要对其进行减少，你我同时从数据取出商品的价格100，这时候你先进行了增加，例如150，而我后面对其进行删减，例如减50，这个时候没有锁，我改的数值会直接覆盖你修改的数值。本来应该是150-50 = 100，而现在则是100 - 50 = 50 ，然后50直接覆盖你的150；



#### 6.3.2 乐观锁与悲观锁

上面的举例，如果是乐观锁，我保存价格前，会检查下价格是否被人修改过了。如果被修改过

了，则重新取出的被修改后的价格，150元，这样我会将100元存入数据库。

如果是悲观锁，你取出数据后，我只能等你操作完之后，才能对价格进行操作，也会保证

最终的价格是100元。

#### 6.3.3 模拟修改冲突

**首先我们往数据库增加一张商品表吧。**

```sql
CREATE TABLE t_product
(
    id      BIGINT(20)  NOT NULL COMMENT '主键ID',
    NAME    VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
    price   INT(11)          DEFAULT 0 COMMENT '价格',
    version INT(11)          DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (id)
);
INSERT INTO t_product (id, NAME, price) VALUES (1, '联想拯救者Y7000', 100);
```

实体类

```java
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer version;
}
```

**添加mapper**

```java
public interface ProductMapper extends BaseMapper<Product> {
}
```

**测试**

```java
@Autowired
private ProductMapper productMapper;

@Test
public void testConcurrentUpdate() {

    //1、小李
    Product p1 = productMapper.selectById(1L);
    System.out.println("小李取出的价格：" + p1.getPrice());

    //2、小王
    Product p2 = productMapper.selectById(1L);
    System.out.println("小王取出的价格：" + p2.getPrice());

    //3、小李将价格加了50元，存入了数据库
    p1.setPrice(p1.getPrice() + 50);
    int result1 = productMapper.updateById(p1);
    System.out.println("小李修改结果：" + result1);
    //4、小王将商品减了30元，存入了数据库
    p2.setPrice(p2.getPrice() - 30);
    int result2 = productMapper.updateById(p2);
    System.out.println("小王修改结果：" + result2);
    //最后的结果
    Product p3 = productMapper.selectById(1L);
     //价格覆盖，最后的结果：70
    System.out.println("最后的结果：" + p3.getPrice());

}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302191234727.png)

#### 6.3.4 乐观锁实现流程

1、首先数据库中要有version字段，而这个我已经添加了。

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302191235708.png)

2、 取出记录时，获取当前version

```sql
SELECT id,`name`,price,`version` FROM product WHERE id=1
```

复制

3、 更新时，version + 1，如果where语句中的version版本不对，则更新失败

```sql
UPDATE product SET price=price+50, version=version + 1 WHERE id=1 AND 
version=1 
```

#### 6.3.5 Mybatis-Plus实现乐观锁

1、首先我们要修改实体类属性名version，我们要加一个注解。

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302191236999.png)

1、首先我们要添加乐观锁的插件配置

```java
@Configuration
@MapperScan("com.riotian.mplearn.mapper") //可以将主类中的注解移到此处
public class MPConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 用于配置mybatisPlus中的插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}
```

2、测试修改冲突

这里我们要稍微优化一下测试流程。

> 记得把数据库的数据初始一下

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302191239286.png)

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302191240137.png)



## 7、通用枚舉

表中的有些字段值是固定的，例如性别（男或女），此时我们可以使用MyBatis-Plus的通用枚举来实现

### 7.1 数据库表添加字段 sex

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302201000434.png)



### 7.2 创建通用枚举类型

```java
// User.java
@Data
public class User {
    // @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("username")
    private String name;
    private Integer age;
    private String email;

    private SexEnum sex;

    @TableLogic
    private Integer isDeleted;
}
// SexEnum.java
@Getter
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
```

### 7.3 配置扫描通用枚举

```yaml
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
      # 配置MyBatis-Plus的主键策略
      id-type: assign_id
  # 配置扫描通用枚举
  # @EnumValue是Mybatis-plus 下的枚举转换，要在mybatis-plus.type-enums-package中配置扫描包 @EnumValue才会生效
  type-enums-package: com.riotian.mplearn.entity
```

### 7.4 测试

```java
@Test
public void testSexEnum(){
    User user = new User();
    user.setName("admin");
    user.setAge(20);
    //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
    user.setSex(SexEnum.MALE);
    //INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
    //Parameters: Enum(String), 20(Integer), 1(Integer)
    userMapper.insert(user);
}
```

> @EnumValue是Mybatis-plus 下的枚举转换，要在mybatis-plus.type-enums-package中配置扫描包 @EnumValue才会生效





## 8、代码生成器

### 8.1 引入依赖

```xml
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-generator</artifactId>
  <version>3.5.1</version> <!-- 测试下来最低这个版本合适 -->
</dependency>

<dependency>
  <groupId>org.freemarker</groupId>
  <artifactId>freemarker</artifactId>
  <version>2.3.31</version>
</dependency>
```

### 8.2  快速生成

```java
public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true", "username", "password")
            .globalConfig(builder -> {
                builder.author("riotian") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("D:\\Coding_Fin_Semester\\demo"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.riotian") // 设置父包名
                    .moduleName("mybatisplus") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Coding_Fin_Semester\\demo")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_user") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}
```

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302201025216.png)



## 9、MyBatisx

> MyBatis-Plus为我们提供了强大的mapper和service模板，能够大大的提高开发效率 
>
> 但是在真正开发过程中，MyBatis-Plus并不能为我们解决所有问题，
>
> 例如一些复杂的SQL，多表联查，我们就需要自己去编写代码和SQL语句，我们该如何快速的解决这个问题呢
>
> 这个时候MybatisX 是一款基于 IDEA 的快速开发插件，为效率而生。

安装方法：打开 IDEA，进入 File -> Settings -> Plugins -> Browse Repositories，输入 `mybatisx` 搜索并安装。

MyBatisX插件官网文档：https://baomidou.com/pages/ba5b24/

![](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/202302201040740.png)