package com.riotian.mplearn;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riotian.mplearn.entity.Product;
import com.riotian.mplearn.entity.SexEnum;
import com.riotian.mplearn.entity.User;
import com.riotian.mplearn.mapper.ProductMapper;
import com.riotian.mplearn.mapper.UserMapper;
import com.riotian.mplearn.service.UserService;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MP_Test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelectList(){
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void Insert() {
        User user = new User();
        user.setAge(23);
        user.setName("lisi");
        user.setEmail("lisi@gmail.com");
        // insert into user(id,name,age,email) values (?,?,?,?)
        int count = userMapper.insert(user);
        System.out.println("受影响的行数"+count);
        // 1626576039783362562
        System.out.println("ID 自动获取: " + user.getId());
    }

    @Test
    public void testDeleteById() {
        // 通过 id 删除用户信息
        // Delete from user where id = ?
        int count = userMapper.deleteById(1626576039783362562L);
        System.out.println("受影响的行数"+count);
    }

    @Test
    public void testDeleteBatchIds() {
        // 通过多个 id 批量删除
        // Delete from user where id in (?, ?, ?)
        List<Long> idLists = Arrays.asList(1L, 2L, 3L);
        int count = userMapper.deleteBatchIds(idLists);
        System.out.println("受影响的行数"+count);
    }

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

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(4L);
        user.setAge(10);
        user.setEmail(null);
        int count = userMapper.updateById(user);
        System.out.println("受影响的行数"+count);
    }

    @Test
    public void testSelectById(){
        //根据id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(4L);
        System.out.println(user);
    }


    @Test
    public void testSelectBatchIds(){
        //根据多个id查询多个用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
        List<Long> idList = Arrays.asList(4L, 5L);
        List<User> list = userMapper.selectBatchIds(idList);
        list.forEach(System.out::println);
    }



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

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){
        System.out.println("总记录数：" + userService.count());
    }

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

    @Test
    public void test02(){
        //按照年龄升序查询用户，如果年龄相同则按id 降序排列
        //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age ASC,id DESC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

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

    @Test
    public void test07(){
        //查询id小于等于3的用户信息
        //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 3))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <= 3");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    @Test
    public void test08() {
        //将（年龄大于20或邮箱为null）并且用户名中包含有lisi的用户信息修改
        //组装set子句以及修改条件
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
        //SELECT uid AS id,username AS name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }
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

    @Test
    public void test11() {

        //定义查询条件，有可能为null（用户为输入）
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //StringUtils.isNotBlank()判断字符串是否不为空且长度不为0且不由空白符(whitespace)构成
        lambdaQueryWrapper
            .like(StringUtils.isNotBlank(username),User::getName,username)
            .ge(ageBegin != null, User::getAge, ageBegin)
            .le(ageEnd != null, User::getAge, ageEnd);
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(user -> System.out.println(user));
    }

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

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        // 获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页: " + page.getCurrent());
        System.out.println("每页显示的条数: " + page.getSize());
        System.out.println("总记录数: " + page.getTotal());
        System.out.println("总页数: " + page.getPages());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());
    }

    // Todo: 有bug 未修
    @Test
    public void testSelectPageVo(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPageVo(page,20);
        // 获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页: " + page.getCurrent());
        System.out.println("每页显示的条数: " + page.getSize());
        System.out.println("总记录数: " + page.getTotal());
        System.out.println("总页数: " + page.getPages());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());
    }

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
        if(result2 == 0) {
            // 失败重试，重新获取 version 并更新
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result2 = productMapper.updateById(p2);
        }
        System.out.println("Again: 小王修改结果：" + result2);
        //最后的结果
        Product p3 = productMapper.selectById(1L);
         //价格覆盖，最后的结果：70
        System.out.println("最后的结果：" + p3.getPrice());

    }

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

}