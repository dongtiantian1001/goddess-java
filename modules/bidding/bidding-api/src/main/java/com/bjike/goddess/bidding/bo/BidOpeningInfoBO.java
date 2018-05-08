package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 开标信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.802 ]
 * @Description: [ 开标信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoBO extends BaseBO {

    /**
     * 编号
     */
    private String biddingNumber;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;

    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessDirectionSubject;

    /**
     * 开标时间
     */
    private String bidOpeningTime;
    /**
     * 开标地点
     */
    private String bidOpeningPlace;

    /**
     * 委托人
     */
    private String principal;

    /**
     * 记录人
     */
    private String recorder;

    /**
     * 竞争公司
     */
    private String competitive;

    /**
     * 地市
     */
    private String cities;

    /**
     * 竞争公司报价
     */
    private Double competitivePrice;

    /**
     * 比率(%)
     */
    private Double ratio;
    /**
     * 招标价格
     */
    private Double biddingPrice;
    /**
     * 保证金金额
     */
    private Double marginPrice;
    /**
     * 保证金退回时间
     */
    private String backTimeDeposit;

    /**
     * 退回保证金金额
     */
    private Double returnMarginPrice;
    /**
     * 是否转为商机
     */
    private Boolean opportunity;
    /**
     * 是否进行项目测算
     */
    private Boolean projectEstimates;
    /**
     * 项目测算是否通过
     */
    private Boolean passProjectEstimates;
    /**
     * 招标书是否制作完成
     */
    private Boolean finish;
    /**
     * 招标是否通过
     */
    private Boolean biddingPass;
    /**
     * 更新时间
     */
    private String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDirectionSubject() {
        return businessDirectionSubject;
    }

    public void setBusinessDirectionSubject(String businessDirectionSubject) {
        this.businessDirectionSubject = businessDirectionSubject;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getBidOpeningPlace() {
        return bidOpeningPlace;
    }

    public void setBidOpeningPlace(String bidOpeningPlace) {
        this.bidOpeningPlace = bidOpeningPlace;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public Double getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(Double competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(Double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public Double getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(Double marginPrice) {
        this.marginPrice = marginPrice;
    }

    public String getBackTimeDeposit() {
        return backTimeDeposit;
    }

    public void setBackTimeDeposit(String backTimeDeposit) {
        this.backTimeDeposit = backTimeDeposit;
    }

    public Double getReturnMarginPrice() {
        return returnMarginPrice;
    }

    public void setReturnMarginPrice(Double returnMarginPrice) {
        this.returnMarginPrice = returnMarginPrice;
    }

    public Boolean getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Boolean opportunity) {
        this.opportunity = opportunity;
    }

    public Boolean getProjectEstimates() {
        return projectEstimates;
    }

    public void setProjectEstimates(Boolean projectEstimates) {
        this.projectEstimates = projectEstimates;
    }

    public Boolean getPassProjectEstimates() {
        return passProjectEstimates;
    }

    public void setPassProjectEstimates(Boolean passProjectEstimates) {
        this.passProjectEstimates = passProjectEstimates;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getBiddingPass() {
        return biddingPass;
    }

    public void setBiddingPass(Boolean biddingPass) {
        this.biddingPass = biddingPass;
    }
}