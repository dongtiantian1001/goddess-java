package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


/**
 * 考核指标
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_checkindex")
public class CheckIndex extends BaseEntity {

    /**
     * 考核名称
     */
    @Column(name = "checkName",  columnDefinition = "VARCHAR(255)   COMMENT '考核名称'")
    private String checkName;

    /**
     * 对赌标签
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_id")
    private Set<LabelOG> labelOG;


    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Set<LabelOG> getLabelOG() {
        return labelOG;
    }

    public void setLabelOG(Set<LabelOG> labelOG) {
        this.labelOG = labelOG;
    }
}