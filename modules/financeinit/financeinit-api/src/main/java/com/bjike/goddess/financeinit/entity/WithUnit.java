package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.ScaleShape;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 往来单位
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_withunit")
public class WithUnit extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 税号
     */
    @Column(name = "ein", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税号'")
    private String ein;

    /**
     * 电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话'")
    private String phone;

    /**
     * 地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 开户银行
     */
    @Column(name = "bank", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开户银行'")
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "account", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行账号'")
    private String account;

    /**
     * 公司规模形式
     */
    @Column(name = "scaleShape", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '公司规模形式'")
    private ScaleShape scaleShape;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ScaleShape getScaleShape() {
        return scaleShape;
    }

    public void setScaleShape(ScaleShape scaleShape) {
        this.scaleShape = scaleShape;
    }
}