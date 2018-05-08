package com.bjike.goddess.staffwelfare.vo;

/**
 * 个人节日表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonalFestivalVO {

    /**
     * id
     */
    private String id;
    /**
     * 节日名称
     */
    private String festivalName;

    /**
     * 节日时间
     */
    private String festivalDate;

    /**
     * 可见人员
     */
    private String visibleUsers;

    /**
     * 提醒时间
     */
    private String remindTime;

    /**
     * 提示语
     */
    private String remindInfo;

    /**
     * 是否开通一声祝福
     */
    private Boolean openWish;

    /**
     * 答谢语
     */
    private String thankStatement;

    /**
     * 备注
     */
    private String remark;

    /**
     * 节日人姓名
     */
    private String userName;

    /**
     * 节日人id
     */
    private String userId;

    public String getVisibleUsers() {
        return visibleUsers;
    }

    public void setVisibleUsers(String visibleUsers) {
        this.visibleUsers = visibleUsers;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(String festivalDate) {
        this.festivalDate = festivalDate;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindInfo() {
        return remindInfo;
    }

    public void setRemindInfo(String remindInfo) {
        this.remindInfo = remindInfo;
    }

    public Boolean getOpenWish() {
        return openWish;
    }

    public void setOpenWish(Boolean openWish) {
        this.openWish = openWish;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}