package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 账户来源
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_account")
public class Account extends BaseEntity {

    /**
     * 用户名称(账户来源)
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户名称'")
    private String name;

    /**
     * 帐号
     */
    @Column(name = "account",  columnDefinition = "VARCHAR(255)   COMMENT '帐号'")
    private String account;

    /**
     * 开户行地址
     */
    @Column(name = "bankAddr",  columnDefinition = "VARCHAR(255)   COMMENT '开户行地址'")
    private String bankAddr;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 一级分类
     */
    @JoinColumn(name = "firstSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级分类'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 公司编号
     */
    @Column(name = "systemId", updatable = false, columnDefinition = "VARCHAR(20)   COMMENT '公司编号'")
    private String systemId;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getBankAddr() {
        return bankAddr;
    }

    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}