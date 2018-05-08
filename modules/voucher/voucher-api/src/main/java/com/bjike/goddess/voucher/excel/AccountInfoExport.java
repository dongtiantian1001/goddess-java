package com.bjike.goddess.voucher.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 明细账信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountInfoExport {

    /**
     * 日期
     */
    @ExcelHeader(name = "日期",notNull = true)
    private String voucherDate;
    /**
     * 凭证字
     */
    @ExcelHeader(name = "凭证字",notNull = true)
    private String voucherWord;

    /**
     * 凭证号
     */
   // @ExcelHeader(name = "凭证号",notNull = true)
   // private Double voucherNum;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projectName;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门",notNull = true)
    private String projectGroup;

    /**
     * 摘要
     */
    @ExcelHeader(name = "摘要",notNull = true)
    private String sumary;

    /**
     * 一级科目
     */
    @ExcelHeader(name = "一级科目",notNull = true)
    private String firstSubject;

    /**
     * 二级科目
     */
    @ExcelHeader(name = "二级科目",notNull = true)
    private String secondSubject;

    /**
     * 三级科目
     */
    @ExcelHeader(name = "三级科目",notNull = true)
    private String thirdSubject;

    /**
     * 借方金额
     */
    @ExcelHeader(name = "借方金额")
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    @ExcelHeader(name = "贷方金额")
    private Double loanMoney;
    /**
     * 方向
     */
    @ExcelHeader(name = "方向")
    private String direction;
    /**
     * 余额
     */
    @ExcelHeader(name = "余额")
    private Double balance;

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherWord() {
        return voucherWord;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

//    public Double getVoucherNum() { return voucherNum;
//    }
//
//    public void setVoucherNum(Double voucherNum) {
//        this.voucherNum = voucherNum;
//    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}