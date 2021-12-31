package com.riotian.pojo;

import java.io.Serializable;

/**
 * 学生 （t_student表对应的Java实体类）
 */
public class Student implements Serializable {
    private Long id;//主键
    private String studentName;//姓名
    private String studentSex;//性别:男/女
    private String studentNo;//学号
    private String studentBuilding;//楼栋
    private String studentDormitory;//宿舍
    private String studentPhone;//电话
    private String studentText;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentBuilding() {
        return studentBuilding;
    }

    public void setStudentBuilding(String studentBuilding) {
        this.studentBuilding = studentBuilding;
    }

    public String getStudentDormitory() {
        return studentDormitory;
    }

    public void setStudentDormitory(String studentDormitory) {
        this.studentDormitory = studentDormitory;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentText() {
        return studentText;
    }

    public void setStudentText(String studentText) {
        this.studentText = studentText;
    }
}
