package com.bjike.goddess.user.vo.rbac;

/**
 * 组角色值实体
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupRoleVO {
    /**
     * 组角色id
     */
    private String id;
    /**
     * 组id
     */
    private String groupId;
    /**
     * 角色id
     */
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
