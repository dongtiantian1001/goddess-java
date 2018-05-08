package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 档案收集
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_gather")
public class ArchiveGather extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 自愿放弃购买社保协议书
     */
    @Column(name = "is_abandon", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '自愿放弃购买社保协议书'")
    private Boolean abandon;

    /**
     * 转正定级档案收集
     */
    @Column(name = "is_become", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '转正定级档案收集'")
    private Boolean become;

    /**
     * 调动或任命
     */
    @Column(name = "transfer", columnDefinition = "VARCHAR(255)   COMMENT '调动或任命'")
    private String transfer;

    /**
     * 岗位晋升
     */
    @Column(name = "promotion", columnDefinition = "VARCHAR(255)   COMMENT '岗位晋升'")
    private String promotion;

    /**
     * 技能评定
     */
    @Column(name = "evaluate", columnDefinition = "VARCHAR(255)   COMMENT '技能评定'")
    private String evaluate;

    /**
     * 特批补助
     */
    @Column(name = "subsidy", columnDefinition = "VARCHAR(255)   COMMENT '特批补助'")
    private String subsidy;

    /**
     * 技能证书
     */
    @Column(name = "skill", columnDefinition = "VARCHAR(255)   COMMENT '技能证书'")
    private String skill;

    /**
     * 其他档案
     */
    @Column(name = "other", columnDefinition = "VARCHAR(255)   COMMENT '其他档案'")
    private String other;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAbandon() {
        return abandon;
    }

    public void setAbandon(Boolean abandon) {
        this.abandon = abandon;
    }

    public Boolean getBecome() {
        return become;
    }

    public void setBecome(Boolean become) {
        this.become = become;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}