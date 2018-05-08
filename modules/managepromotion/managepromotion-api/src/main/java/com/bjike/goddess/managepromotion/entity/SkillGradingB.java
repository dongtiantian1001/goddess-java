package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 技能定级B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillgradingb")
public class SkillGradingB extends BaseEntity {

    /**
     * 技能等级
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能等级'")
    private String skillLevel;
    /**
     * 转正后间隔时间
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正后间隔时间'")
    private Integer intervalAfterTransfer;

    /**
     * 技能定级A
     */
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "skillGradingA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '技能定级A'")
    private SkillGradingA skillGradingA;

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getIntervalAfterTransfer() {
        return intervalAfterTransfer;
    }

    public void setIntervalAfterTransfer(Integer intervalAfterTransfer) {
        this.intervalAfterTransfer = intervalAfterTransfer;
    }

    public SkillGradingA getSkillGradingA() {
        return skillGradingA;
    }

    public void setSkillGradingA(SkillGradingA skillGradingA) {
        this.skillGradingA = skillGradingA;
    }
}