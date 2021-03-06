package com.bjike.goddess.rotation.vo;

import com.bjike.goddess.rotation.enums.AuditType;

/**
 * 岗位轮换自荐表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CoverRotationVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 转正时间
     */
    private String regularTime;

    /**
     * 目前岗位层级
     */
    private String arrangement;

    /**
     * 申请轮换等级数据id
     */
    private String applyLevelId;

    /**
     * 申请轮换等级
     */
    private String applyLevelArrangement;

    /**
     * 申请轮换原因
     */
    private String reason;

    /**
     * 轮换后岗位等级数据id
     */
    private String rotationLevelId;

    /**
     * 轮换后岗位等级
     */
    private String rotationLevelArrangement;

    /**
     * 总经办
     */
    private String general;

    /**
     * 总经办意见
     */
    private String opinion;

    /**
     * 是否通过
     */
    private AuditType audit;

    /**
     * 轮换时间
     */
    private String rotationDate;


    /**
     * 申请时间
     */
    private String applyTime;

    /**
     * 获得时间
     */
    private String getTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getApplyLevelId() {
        return applyLevelId;
    }

    public void setApplyLevelId(String applyLevelId) {
        this.applyLevelId = applyLevelId;
    }

    public String getApplyLevelArrangement() {
        return applyLevelArrangement;
    }

    public void setApplyLevelArrangement(String applyLevelArrangement) {
        this.applyLevelArrangement = applyLevelArrangement;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRotationLevelId() {
        return rotationLevelId;
    }

    public void setRotationLevelId(String rotationLevelId) {
        this.rotationLevelId = rotationLevelId;
    }

    public String getRotationLevelArrangement() {
        return rotationLevelArrangement;
    }

    public void setRotationLevelArrangement(String rotationLevelArrangement) {
        this.rotationLevelArrangement = rotationLevelArrangement;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(String regularTime) {
        this.regularTime = regularTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }
}