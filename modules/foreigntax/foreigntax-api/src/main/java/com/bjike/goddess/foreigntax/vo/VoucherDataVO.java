package com.bjike.goddess.foreigntax.vo;

import java.util.List;

/**
 * 税金管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-25 03:25 ]
 * @Description: [ 税金管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VoucherDataVO {

    /**
     * 日期
     */
    private String voucherDate;

    /**
     * 摘要
     */
    private String sumary;

    /**
     * 会计科目数组
     */
    private List<String> subjects;

    /**
     * 借方金额数组
     */
    private List<Double> borrowMoneys;

    /**
     * 贷方金额数组
     */
    private List<Double> loanMoneys;

    /**
     * 地区
     */
    private String areas;
    /**
     * 项目数组
     */
    private String projects;
    /**
     * 项目组
     */
    private String groups;

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Double> getBorrowMoneys() {
        return borrowMoneys;
    }

    public void setBorrowMoneys(List<Double> borrowMoneys) {
        this.borrowMoneys = borrowMoneys;
    }

    public List<Double> getLoanMoneys() {
        return loanMoneys;
    }

    public void setLoanMoneys(List<Double> loanMoneys) {
        this.loanMoneys = loanMoneys;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}