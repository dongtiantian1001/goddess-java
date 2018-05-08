package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 邀请码表
 *
 * @Author: [ chenayng ]
 * @Date: [ 2018-01-13 11:58 ]
 * @Description: [ 邀请码表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "user_sharecode")
public class ShareCode extends BaseEntity {

    /**
     * 用户ID
     */
    @Column(name = "userId", columnDefinition = "VARCHAR(255)   COMMENT '用户ID'")
    private String userId;

    /**
     * 邀请码
     */
    @Column(name = "shareCode", columnDefinition = "VARCHAR(36)   COMMENT '邀请码'")
    private String shareCode;

    /**
     * 用户积分；
     */
    @Column(name = "integral", columnDefinition = "VARCHAR(255)   COMMENT '用户积分；'")
    private String integral;

    /**
     * 邀请人积分；
     */
    @Column(name = "inviterIntegral", columnDefinition = "VARCHAR(255)   COMMENT '邀请人积分；'")
    private String inviterIntegral;

    /**
     * 邀请人邀请码
     */
    @Column(name = "inviterShareCode", columnDefinition = "VARCHAR(36)   COMMENT '邀请人邀请码'")
    private String inviterShareCode;

    /**
     * 邀请人ID
     */
    @Column(name = "inviterId", columnDefinition = "VARCHAR(255)   COMMENT '邀请人ID'")
    private String inviterId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviterShareCode(String authCode) {
        return inviterShareCode;
    }

    public void setInviterShareCode(String inviterShareCode) {
        this.inviterShareCode = inviterShareCode;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getInviterIntegral() {
        return inviterIntegral;
    }

    public void setInviterIntegral(String inviterIntegral) {
        this.inviterIntegral = inviterIntegral;
    }

    public String getInviterShareCode() {
        return inviterShareCode;
    }
}