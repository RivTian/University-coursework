package com.riotian.pojo;

import java.io.Serializable;

/**
 * 宿舍 （t_dormitory表对应的Java实体类）
 */
public class Dormitory implements Serializable {
    private Long id;//主键
    private String dormitoryName;//宿舍
    private String dormitoryBuilding;//楼栋
    private String dormitoryBedcount;//床位数
    private String dormitoryAdministrator;//宿管员
    private String dormitoryText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getDormitoryBuilding() {
        return dormitoryBuilding;
    }

    public void setDormitoryBuilding(String dormitoryBuilding) {
        this.dormitoryBuilding = dormitoryBuilding;
    }

    public String getDormitoryBedcount() {
        return dormitoryBedcount;
    }

    public void setDormitoryBedcount(String dormitoryBedcount) {
        this.dormitoryBedcount = dormitoryBedcount;
    }

    public String getDormitoryAdministrator() {
        return dormitoryAdministrator;
    }

    public void setDormitoryAdministrator(String dormitoryAdministrator) {
        this.dormitoryAdministrator = dormitoryAdministrator;
    }

    public String getDormitoryText() {
        return dormitoryText;
    }

    public void setDormitoryText(String dormitoryText) {
        this.dormitoryText = dormitoryText;
    }
}
