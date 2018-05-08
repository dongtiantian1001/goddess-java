package com.bjike.goddess.staffwelfare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 感谢语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ThankStatementTO extends BaseTO {

    /**
     * 填写人
     */
    private String createUser;

    /**
     * 感谢语
     */
    @NotBlank(message = "感谢语不能为空!", groups = {ADD.class, EDIT.class})
    private String thankStatement;

    /**
     * 是否共享为公有
     */
    @NotNull(message = "是否共享为公有不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean share;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 状态
     */
    private Status status;


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
}