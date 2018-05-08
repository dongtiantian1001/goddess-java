package com.bjike.goddess.carinfo.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 测试业务传输对象
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 测试业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailBO extends BaseBO {

    /**
     * 岗位id
     */
    private String positionNameId;

    /**
     * 部门id
     */
    private String projectManageId;

    /**
     * 部门名称
     */
    private String positionName;

    /**
     * 岗位名称
     */
    private String projectManage;

    /**
     * 备注
     */
    private String remark;

    public String getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String positionNameId) {
        this.positionNameId = positionNameId;
    }

    public String getProjectManageId() {
        return projectManageId;
    }

    public void setProjectManageId(String projectManageId) {
        this.projectManageId = projectManageId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getProjectManage() {
        return projectManage;
    }

    public void setProjectManage(String projectManage) {
        this.projectManage = projectManage;
    }
}