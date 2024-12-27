package com.riotian.mplearn.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue // 将注解标识的属性的值代入
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
