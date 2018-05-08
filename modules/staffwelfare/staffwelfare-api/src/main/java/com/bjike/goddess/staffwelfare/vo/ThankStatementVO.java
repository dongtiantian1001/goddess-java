package com.bjike.goddess.staffwelfare.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 感谢语表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ThankStatementVO {

    /**
     * id
     */
    private String id;
    /**
     * 填写人
     */
    private String createUser;

    /**
     * 感谢语
     */
    private String thankStatement;

    /**
     * 是否共享为公有
     */
    private Boolean share;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 状态
     */
    private Status status;

    /**
     * 更新时间
     */
    private String updateDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }

    public Boolean getShare() {
        return share;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}