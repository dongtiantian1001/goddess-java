package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 推荐信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OldRecommendInfoBO extends BaseBO {

    /**
     * 推荐要求id
     */
    private String requireId;

    /**
     * 推荐时间
     */
    private String createTime;

    /**
     * 推荐目的
     */
    private String purpose;

    /**
     * 推荐类型
     */
    private String typeName;

    /**
     * 推荐人
     */
    private String recommendUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 推荐内容
     */
    private List<OldRecommendContentBO> contentList;

    /**
     * 是否采纳
     */
    private Boolean accept;

    /**
     * 是否符合奖励要求
     */
    private Boolean conform;

    /**
     * 原因
     */
    private String reason;

    /**
     * 推荐开通时间
     */
    private String openTime;

    /**
     * 推荐关闭时间
     */
    private String closeTime;

    /**
     * 奖励类型
     */
    private String awardType;

    /**
     * 奖励内容
     */
    private String awardContent;

    /**
     * 奖励数量
     */
    private Integer awardAmount;

    /**
     * 奖励发放方式
     */
    private String awardSendWay;

    /**
     * 奖励时间
     */
    private String awardTime;

    /**
     * 是否获得奖励
     */
    private Boolean getAward;

    /**
     * 奖励信息id
     */
    private String awardInfoId;

    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OldRecommendContentBO> getContentList() {
        return contentList;
    }

    public void setContentList(List<OldRecommendContentBO> contentList) {
        this.contentList = contentList;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getAwardSendWay() {
        return awardSendWay;
    }

    public void setAwardSendWay(String awardSendWay) {
        this.awardSendWay = awardSendWay;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public Boolean getGetAward() {
        return getAward;
    }

    public void setGetAward(Boolean getAward) {
        this.getAward = getAward;
    }

    public String getAwardInfoId() {
        return awardInfoId;
    }

    public void setAwardInfoId(String awardInfoId) {
        this.awardInfoId = awardInfoId;
    }
}