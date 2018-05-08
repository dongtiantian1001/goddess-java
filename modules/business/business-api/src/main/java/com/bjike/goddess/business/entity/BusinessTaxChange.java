package com.bjike.goddess.business.entity;

import com.bjike.goddess.business.enums.ChangeDataName;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 工商税务变更
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "business_businesstaxchange")
public class BusinessTaxChange extends BaseEntity {

    /**
     * 工商注册id
     */
    @Column(name = "businessRegisterId", columnDefinition = "VARCHAR(255)   COMMENT '工商注册id'")
    private String businessRegisterId;
    /**
     * 变更日期
     */
    @Column(name = "changeDate", columnDefinition = "DATE   COMMENT '变更日期'")
    private LocalDate changeDate;

    /**
     * 变更原因
     */
    @Column(name = "changeCause", columnDefinition = "VARCHAR(255)   COMMENT '变更原因'")
    private String changeCause;
    /**
     * 变更资料名称
     */
    @Column(name = "changeDataName",  columnDefinition = "TINYINT(2)   COMMENT '变更资料名称'")
    private ChangeDataName changeDataName;

    /**
     * 变更前内容
     */
    @Column(name = "changeBeforeContent", columnDefinition = "VARCHAR(255)   COMMENT '变更前内容'")
    private String changeBeforeContent;

    /**
     * 变更后内容
     */
    @Column(name = "changeAfterContent", columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String changeAfterContent;

    /**
     * 负责经办人
     */
    @Column(name = "responsibleAgent", columnDefinition = "VARCHAR(255)   COMMENT '负责经办人'")
    private String responsibleAgent;

    /**
     * 负责经办人联系方式
     */
    @Column(name = "responsiblePhone", columnDefinition = "VARCHAR(255)   COMMENT '负责经办人联系方式'")
    private String responsiblePhone;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getBusinessRegisterId() {
        return businessRegisterId;
    }

    public void setBusinessRegisterId(String businessRegisterId) {
        this.businessRegisterId = businessRegisterId;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeCause() {
        return changeCause;
    }

    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }

    public ChangeDataName getChangeDataName() {
        return changeDataName;
    }

    public void setChangeDataName(ChangeDataName changeDataName) {
        this.changeDataName = changeDataName;
    }

    public String getChangeBeforeContent() {
        return changeBeforeContent;
    }

    public void setChangeBeforeContent(String changeBeforeContent) {
        this.changeBeforeContent = changeBeforeContent;
    }

    public String getChangeAfterContent() {
        return changeAfterContent;
    }

    public void setChangeAfterContent(String changeAfterContent) {
        this.changeAfterContent = changeAfterContent;
    }

    public String getResponsibleAgent() {
        return responsibleAgent;
    }

    public void setResponsibleAgent(String responsibleAgent) {
        this.responsibleAgent = responsibleAgent;
    }

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}