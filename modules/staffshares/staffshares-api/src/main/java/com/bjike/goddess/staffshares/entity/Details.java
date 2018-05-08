package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 交易详情
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffshares_details")
public class Details extends BaseEntity {

    /**
     * 票面价值
     */
    @Column(name = "facevalue", columnDefinition = "DECIMAL(10,2)   COMMENT '票面价值'")
    private Double facevalue;


    /**
     * 方案代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案代码'")
    private String code;

    /**
     * 方案名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String name;

    /**
     * 出售/发行人
     */
    @Column(name = "publisher", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '出售/发行人'")
    private String publisher;

    /**
     * 出售/发行数量
     */
    @Column(name = "", nullable = false, columnDefinition = "BIGINT(20)   COMMENT '出售/发行数量'")
    private Long number;

    /**
     * 出售/发行价格
     */
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出售/发行价格'")
    private Double price;

    /**
     * 出售/发行时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '出售/发行时间'")
    private LocalDate time;

    /**
     * 剩余出售量
     */
    @Column(name = "", nullable = false, columnDefinition = "BIGINT(20)   COMMENT '剩余出售量'")
    private Long sharesNum;


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

    public Long getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(Long sharesNum) {
        this.sharesNum = sharesNum;
    }

    public Double getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(Double facevalue) {
        this.facevalue = facevalue;
    }
}