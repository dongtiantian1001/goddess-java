package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 调研方式
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttainmentWayTO extends BaseTO {

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否使用问卷调查
     */
    @NotNull(message = "是否使用问卷调查不能为空", groups = {ADD.class, EDIT.class})
    private Boolean employ;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isEmploy() {
        return employ;
    }

    public void isEmploy(Boolean employ) {
        this.employ = employ;
    }

    public Boolean getEmploy() {
        return employ;
    }

    public void setEmploy(Boolean employ) {
        this.employ = employ;
    }
}