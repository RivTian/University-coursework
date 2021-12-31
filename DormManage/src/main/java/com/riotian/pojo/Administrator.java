package com.riotian.pojo;

import java.io.Serializable;

/**
 * 宿管员 （t_administrator表对应的Java实体类）
 */
public class Administrator implements Serializable {
    private Long id;//主键
    private String administratorName;//姓名
    private String administratorSex;//性别:男/女
    private String administratorPhone;//电话
    private String administratorBuilding;//楼栋
    private String administratorText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getAdministratorSex() {
        return administratorSex;
    }

    public void setAdministratorSex(String administratorSex) {
        this.administratorSex = administratorSex;
    }

    public String getAdministratorPhone() {
        return administratorPhone;
    }

    public void setAdministratorPhone(String administratorPhone) {
        this.administratorPhone = administratorPhone;
    }

    public String getAdministratorBuilding() {
        return administratorBuilding;
    }

    public void setAdministratorBuilding(String administratorBuilding) {
        this.administratorBuilding = administratorBuilding;
    }

    public String getAdministratorText() {
        return administratorText;
    }

    public void setAdministratorText(String administratorText) {
        this.administratorText = administratorText;
    }
}
