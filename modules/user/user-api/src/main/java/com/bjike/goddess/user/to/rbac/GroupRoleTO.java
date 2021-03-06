package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 组角色传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupRoleTO extends BaseTO {
    /**
     * 组id
     */
    @NotBlank(message = "组id不能为空", groups = {ADD.class, EDIT.class})
    private String groupId;
    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空", groups = {ADD.class, EDIT.class})
    private String roleId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
