package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 调研分析
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyAnalyseTO extends BaseTO {

    /**
     * 调研计划id
     */
    @NotNull(message = "调研计划id不能为空", groups = {ADD.class, EDIT.class})
    private String planId;

    /**
     * 分析结果1
     */
    @NotNull(message = "分析结果1不能为空", groups = {ADD.class, EDIT.class})
    private String resultOne;

    /**
     * 分析结果2
     */
    private String resultTwo;

    /**
     * 备注
     */
    private String remark;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getResultOne() {
        return resultOne;
    }

    public void setResultOne(String resultOne) {
        this.resultOne = resultOne;
    }

    public String getResultTwo() {
        return resultTwo;
    }

    public void setResultTwo(String resultTwo) {
        this.resultTwo = resultTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}