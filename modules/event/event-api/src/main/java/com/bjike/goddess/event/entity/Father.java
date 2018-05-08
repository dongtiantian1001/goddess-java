package com.bjike.goddess.event.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 事件父表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 事件父表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "event_father")
public class Father extends BaseEntity {

    /**
     * 模块名称中文名
     */
    @Column(name = "projectChineseName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块名称中文名'")
    private String projectChineseName;
    /**
     * 模块名称英文名
     */
    @Column(name = "projectEnglishName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块名称英文名'")
    private String projectEnglishName;
    /**
     * 功能名称中文名
     */
    @Column(name = "functionChineseName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能名称中文名'")
    private String functionChineseName;

    /**
     * 功能名称英文名
     */
    @Column(name = "functionEnglishName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能名称英文名'")
    private String functionEnglishName;

    /**
     * 任务创建时间
     * 2017-12-28 新增字段 用于区别任务创建日期
     * @Author:zhangzhiguang
     */
    @Column(name = "startDate", nullable = false, columnDefinition = "DATE   COMMENT '开始时间'")
    private LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getProjectChineseName() {
        return projectChineseName;
    }

    public void setProjectChineseName(String projectChineseName) {
        this.projectChineseName = projectChineseName;
    }

    public String getProjectEnglishName() {
        return projectEnglishName;
    }

    public void setProjectEnglishName(String projectEnglishName) {
        this.projectEnglishName = projectEnglishName;
    }

    public String getFunctionChineseName() {
        return functionChineseName;
    }

    public void setFunctionChineseName(String functionChineseName) {
        this.functionChineseName = functionChineseName;
    }

    public String getFunctionEnglishName() {
        return functionEnglishName;
    }

    public void setFunctionEnglishName(String functionEnglishName) {
        this.functionEnglishName = functionEnglishName;
    }
}