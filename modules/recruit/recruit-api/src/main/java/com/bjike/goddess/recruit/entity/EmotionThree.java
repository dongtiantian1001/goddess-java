package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 情感标签三级
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:52 ]
 * @Description: [ 情感标签三级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_emotionthree")
public class EmotionThree extends BaseEntity {

    /**
     * 标签名称
     */
    @Column(name = "labelName", columnDefinition = "VARCHAR(255)   COMMENT '标签名称'")
    private String labelName;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}