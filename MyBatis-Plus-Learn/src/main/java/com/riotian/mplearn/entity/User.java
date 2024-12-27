package com.riotian.mplearn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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