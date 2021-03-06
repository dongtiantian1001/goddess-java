package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 公司干股交易情况表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-11 04:38 ]
 * @Description: [ 公司干股交易情况表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffshares_companysscheme")
public class CompanysScheme extends BaseEntity {

    /**
     * 方案代码
     */
    @Column(name = "code", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '方案代码'")
    private String code;

    /**
     * 方案名称
     */
    @Column(name = "name", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String name;

    /**
     * 出售/发行人
     */
    @Column(name = "publisher", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '出售/发行人'")
    private String publisher;

    /**
     * 出售/发行数量
     */
    @Column(name = "", nullable = true, columnDefinition = "BIGINT(20)   COMMENT '出售/发行数量'")
    private Long number;

    /**
     * 出售/发行价格
     */
    @Column(name = "price", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '出售/发行价格'")
    private Double price;

    /**
     * 出售/发行时间
     */
    @Column(name = "time", nullable = true, columnDefinition = "DATE   COMMENT '出售/发行时间'")
    private LocalDate time;

    /**
     * 已交易数量
     */
    @Column(name = "", nullable = true, columnDefinition = "BIGINT(20)   COMMENT '已交易数量'")
    private Long quantityNum;

    /**
     * 未交易数量
     */
    @Column(name = "", nullable = true, columnDefinition = "BIGINT(20)   COMMENT '未交易数量'")
    private Long sharesNum;

    /**
     * 交易收入额
     */
    @Column(name = "money", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '交易收入额'")
    private Double money;

    /**
     * 状态
     */
    @Column(name = "status", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Long getQuantityNum() {
        return quantityNum;
    }

    public void setQuantityNum(Long quantityNum) {
        this.quantityNum = quantityNum;
    }

    public Long getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(Long sharesNum) {
        this.sharesNum = sharesNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}