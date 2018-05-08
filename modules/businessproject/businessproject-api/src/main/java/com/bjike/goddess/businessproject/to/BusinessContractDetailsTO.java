package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.TaskContract;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 合同明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractDetailsTO extends BaseTO {

    /**
     * 测算分类
     */
    @NotNull(message = "测算分类",groups = {EDIT.class})
    private String measureClassify;

    /**
     * 测算是否通过
     */
    @NotNull(message = "测算是否通过",groups = {EDIT.class})
    private Boolean measurePass;

    /**
     * 通报时间
     */
    @NotNull(message = "通报时间",groups = {EDIT.class})
    private String notificationTime;

    /**
     * 是否通报
     */
    @NotNull(message = "是否通报",groups = {EDIT.class})
    private Boolean notification;

    /**
     * 分包单位名称
     */
    @NotNull(message = "分包单位名称",groups = {EDIT.class})
    private String subCompany;

    /**
     * 是否有共同分包单位
     */
    @NotNull(message = "是否有共同分包单位",groups = {EDIT.class})
    private Boolean commonSubcontractor;

    /**
     * 共同分包单位名称
     */
    @NotNull(message = "共同分包单位名称",groups = {EDIT.class})
    private String commonSubcontractorName;

    /**
     * 派工归属清理是否完成
     */
    @NotNull(message = "派工归属清理是否完成",groups = {EDIT.class})
    private Boolean taskFinish;

    /**
     * 客户名称
     */
    @NotNull(message = "客户名称",groups = {EDIT.class})
    private String customerName;

    /**
     * 市场编号
     */
    @NotNull(message = "市场编号",groups = {EDIT.class})
    private String marketNum;

    /**
     * 内部项目编号
     */
    @NotNull(message = "内部项目编号",groups = {EDIT.class})
    private String internalProjectNum;

    /**
     * 内部合同编号
     */
    @NotNull(message = "内部合同编号",groups = {EDIT.class})
    private String internalContractNum;

    /**
     * 实际规模数量
     */
    @NotNull(message = "实际规模数量",groups = {EDIT.class})
    private Double scale;

    /**
     * 实际规模数和合同规模数是否有差异
     */
    @NotNull(message = "实际规模数和合同规模数是否有差异",groups = {EDIT.class})
    private Boolean scaleBalance;

    /**
     * 是否解决差异问题
     */
    @NotNull(message = "是否解决差异问题",groups = {EDIT.class})
    private Boolean solutionBalance;

    /**
     * 合同外部项目名称
     */
    @NotNull(message = "合同外部项目名称",groups = {EDIT.class})
    private String outerProject;

    /**
     * 内部项目名称
     */
    @NotNull(message = "内部项目名称",groups = {EDIT.class})
    private String innerProject;

    /**
     * 合同经手人
     */
    @NotNull(message = "合同经手人",groups = {EDIT.class})
    private String handlers;

    /**
     * 合同是否已归档
     */
    @NotNull(message = "合同是否已归档",groups = {EDIT.class})
    private Boolean archive;

    /**
     * 合同归档数量
     */
    @NotNull(message = "合同归档数量",groups = {EDIT.class})
    private Double archiveNum;

    /**
     * 存储位置
     */
    @NotNull(message = "存储位置",groups = {EDIT.class})
    private String storageLocation;

    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public Boolean getMeasurePass() {
        return measurePass;
    }

    public void setMeasurePass(Boolean measurePass) {
        this.measurePass = measurePass;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public Boolean getCommonSubcontractor() {
        return commonSubcontractor;
    }

    public void setCommonSubcontractor(Boolean commonSubcontractor) {
        this.commonSubcontractor = commonSubcontractor;
    }

    public String getCommonSubcontractorName() {
        return commonSubcontractorName;
    }

    public void setCommonSubcontractorName(String commonSubcontractorName) {
        this.commonSubcontractorName = commonSubcontractorName;
    }

    public Boolean getTaskFinish() {
        return taskFinish;
    }

    public void setTaskFinish(Boolean taskFinish) {
        this.taskFinish = taskFinish;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getInternalProjectNum() {
        return internalProjectNum;
    }

    public void setInternalProjectNum(String internalProjectNum) {
        this.internalProjectNum = internalProjectNum;
    }

    public String getInternalContractNum() {
        return internalContractNum;
    }

    public void setInternalContractNum(String internalContractNum) {
        this.internalContractNum = internalContractNum;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Boolean getScaleBalance() {
        return scaleBalance;
    }

    public void setScaleBalance(Boolean scaleBalance) {
        this.scaleBalance = scaleBalance;
    }

    public Boolean getSolutionBalance() {
        return solutionBalance;
    }

    public void setSolutionBalance(Boolean solutionBalance) {
        this.solutionBalance = solutionBalance;
    }

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getHandlers() {
        return handlers;
    }

    public void setHandlers(String handlers) {
        this.handlers = handlers;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Double getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(Double archiveNum) {
        this.archiveNum = archiveNum;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }
}