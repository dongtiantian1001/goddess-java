package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 利润表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitBO extends BaseBO {

    /**
     * 列表展示的项目(+项目类型)
     */
    private String project;
//    /**
//     * 原本的项目
//     */
//    private String project1;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 本月数
     */
    private Double currentMonthAmount;

    /**
     * 本年累计数
     */
    private Double currentYearAmount;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 利润类型
     */
    private ProfitType profitType;

    /**
     * 运算类型
     */
    private Type type;

    public ProfitBO() {
    }

    public ProfitBO(String id, String project, Integer num, Double currentMonthAmount, Double currentYearAmount, String projectType, ProfitType profitType, Type type) {
        this.id = id;
        this.project = project;
        this.num = num;
        this.currentMonthAmount = currentMonthAmount;
        this.currentYearAmount = currentYearAmount;
        this.projectType = projectType;
        this.profitType = profitType;
        this.type = type;
    }

    public ProfitType getProfitType() {
        return profitType;
    }

    public void setProfitType(ProfitType profitType) {
        this.profitType = profitType;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    //    /**
//     * 1季度
//     */
//    private Double firstSeason;
//
//    /**
//     * 2季度
//     */
//    private Double secondSeason;
//
//    /**
//     * 3季度
//     */
//    private Double thirdSeason;
//
//    /**
//     * 4季度
//     */
//    private Double fourthSeason;

//    public Double getFirstSeason() {
//        return firstSeason;
//    }
//
//    public void setFirstSeason(Double firstSeason) {
//        this.firstSeason = firstSeason;
//    }
//
//    public Double getSecondSeason() {
//        return secondSeason;
//    }
//
//    public void setSecondSeason(Double secondSeason) {
//        this.secondSeason = secondSeason;
//    }
//
//    public Double getThirdSeason() {
//        return thirdSeason;
//    }
//
//    public void setThirdSeason(Double thirdSeason) {
//        this.thirdSeason = thirdSeason;
//    }
//
//    public Double getFourthSeason() {
//        return fourthSeason;
//    }
//
//    public void setFourthSeason(Double fourthSeason) {
//        this.fourthSeason = fourthSeason;
//    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getCurrentMonthAmount() {
        return currentMonthAmount;
    }

    public void setCurrentMonthAmount(Double currentMonthAmount) {
        this.currentMonthAmount = currentMonthAmount;
    }

    public Double getCurrentYearAmount() {
        return currentYearAmount;
    }

    public void setCurrentYearAmount(Double currentYearAmount) {
        this.currentYearAmount = currentYearAmount;
    }
}