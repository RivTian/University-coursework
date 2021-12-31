package com.riotian.pojo;

import java.io.Serializable;

/**
 * 维修 （t_repair 表对应的Java实体类）
 */
public class Repair implements Serializable {
    private Long id;//主键
    private String repairName;//申请人
    private String repairdorm;//申请寝室
    private String createDate;//申请时间
    private String repairText;//维修内容
    private String repairbool;// 是否处理过

    // Get, Set方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getRepairdorm() {
        return repairdorm;
    }

    public void setRepairdorm(String repairdorm) {
        this.repairdorm = repairdorm;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRepairText() {
        return repairText;
    }

    public void setRepairText(String repairText) {
        this.repairText = repairText;
    }

    public String getRepairbool() {
        return repairbool;
    }

    public void setRepairbool(String repairbool) {
        this.repairbool = repairbool;
    }
}
