package com.bjike.goddess.attainment.to;

import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 调研需求
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyDemandTO extends BaseTO {

    /**
     * 调研类型id
     */
    @NotNull(message = "调研类型id不能为空", groups = {ADD.class, EDIT.class})
    private String typeId;

    /**
     * 调研需求类型id
     */
    @NotNull(message = "调研需求类型id不能为空", groups = {ADD.class, EDIT.class})
    private String demandId;

    /**
     * 调研主题
     */
    @NotNull(message = "调研主题不能为空", groups = {ADD.class, EDIT.class})
    private String theme;

    /**
     * 调研目的
     */
    @NotNull(message = "调研目的不能为空", groups = {ADD.class, EDIT.class})
    private String purpose;

    /**
     * 调研范围
     */
    @NotNull(message = "调研范围不能为空", groups = {ADD.class, EDIT.class})
    private ScopeType scope;

    /**
     * 调研范围准确对象
     */
    @NotNull(message = "调研范围准确对象不能为空", groups = {ADD.class, EDIT.class})
    private String[] scopeNames;

    /**
     * 备注
     */
    private String remark;


    public String[] getScopeNames() {
        return scopeNames;
    }

    public void setScopeNames(String[] scopeNames) {
        this.scopeNames = scopeNames;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public ScopeType getScope() {
        return scope;
    }

    public void setScope(ScopeType scope) {
        this.scope = scope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}