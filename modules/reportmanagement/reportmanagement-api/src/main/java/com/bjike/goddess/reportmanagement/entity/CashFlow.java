package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 现金流量表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-21 03:54 ]
 * @Description: [ 现金流量表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashflow")
public class CashFlow extends BaseEntity {

    /**
     * 序号
     */
    @Column(name = "seqNum", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private Integer seqNum;

    /**
     * id
     */
    @Column(name = "projectId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT 'id'")
    private String projectId;

    /**
     * 项目
     */
    @Column(name = "projectName", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String projectName;

    /**
     * 行次
     */
    @Column(name = "num", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '行次'")
    private Integer num;

    /**
     * 金额
     */
    @Column(name = "money", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '开始时间'")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '结束时间'")
    private String endTime;

    private String systemId;


    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}