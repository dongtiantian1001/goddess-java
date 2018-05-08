package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 商务项目合同基本信息管理导入excel模板
 * @Author: [chenjunhao]
 * @Date: [2017-06-15 16:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BaseInfoManageLeadExcel extends BaseTO{

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型", notNull = true)
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目", notNull = true)
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @ExcelHeader(name = "合同外部项目名称", notNull = true)
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @ExcelHeader(name = "合同外部项目编号", notNull = true)
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @ExcelHeader(name = "对应销售合同编号", notNull = true)
    private String saleContractNum;

    /**
     * 合作方式
     */
    @ExcelHeader(name = "合作方式", notNull = true)
    private BusinessCooperate businessCooperate;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String innerProject;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 所属项目组
     */
    @ExcelHeader(name = "所属项目组", notNull = true)
    private String projectGroup;

    /**
     * 签订时间
     */
    @ExcelHeader(name = "签订时间", notNull = true)
    private LocalDate siginTime;

    /**
     * 合同金额
     */
    @ExcelHeader(name = "合同金额", notNull = true)
    private Double money;

    /**
     * 开工时间
     */
    @ExcelHeader(name = "开工时间", notNull = true)
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间", notNull = true)
    private LocalDate endProjectTime;

    /**
     * 合同期限
     */
    @ExcelHeader(name = "合同期限", notNull = true)
    private String contractRang;

    /**
     * 甲方公司名称
     */
    @ExcelHeader(name = "甲方公司名称", notNull = true)
    private String firstCompany;

    /**
     * 甲方联系人
     */
    @ExcelHeader(name = "甲方联系人", notNull = true)
    private String firstRelation;

    /**
     * 甲方联系人电话
     */
    @ExcelHeader(name = "甲方联系人电话", notNull = true)
    private String firstTel;

    /**
     * 乙方公司名称
     */
    @ExcelHeader(name = "乙方公司名称", notNull = true)
    private String secondCompany;

    /**
     * 项目负责人
     */
    @ExcelHeader(name = "项目负责人", notNull = true)
    private String projectCharge;

    /**
     * 项目负责人电话
     */
    @ExcelHeader(name = "项目负责人电话", notNull = true)
    private String projectChargeTel;

    /**
     * 客户名称
     */
    @ExcelHeader(name = "客户名称", notNull = true)
    private String customerName;

    /**
     * 合同内容
     */
    @ExcelHeader(name = "合同内容", notNull = true)
    private String contractText;

    /**
     * 税率
     */
    @ExcelHeader(name = "税率", notNull = true)
    private Double rate;

    /**
     * 合同属性
     */
    @ExcelHeader(name = "合同属性", notNull = true)
    private ContractProperty contractProperty;

    /**
     * 支付方式
     */
    @ExcelHeader(name = "支付方式", notNull = true)
    private PayWays payWays;

    /**
     * 付款比例
     */
    @ExcelHeader(name = "付款比例", notNull = true)
    private String payRate;

    /**
     * 结算费用来源
     */
    @ExcelHeader(name = "结算费用来源", notNull = true)
    private PayFeeOrigin payFeeOrigin;

    /**
     * 合同是否已归档
     */
    @ExcelHeader(name = "合同是否已归档", notNull = true)
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @ExcelHeader(name = "合同归档数量", notNull = true)
    private Double fileCount;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    /**
     * 临时合同编号
     */
    @ExcelHeader(name = "临时合同编号")
    private String tempContractNum;
    /**
     * 立项情况
     */
    @ExcelHeader(name = "立项情况",notNull = true)
    private MakeContract makeContract;
    /**
     * 派工单号
     */
    @ExcelHeader(name = "派工单号")
    private String taskNum;
    /**
     * 项目状态
     */
    @ExcelHeader(name = "项目状态",notNull = true)
    private ProjectStatus projectStatus;
    /**
     * 合同规模数量
     */
    @ExcelHeader(name = "合同规模数量",notNull = true)
    private Double contractScale;
    /**
     * 规模数量
     */
    @ExcelHeader(name = "规模数量",notNull = true)
    private Double scale;
    /**
     * 专业
     */
    @ExcelHeader(name = "专业",notNull = true)
    private String major;

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Double getContractScale() {
        return contractScale;
    }

    public void setContractScale(Double contractScale) {
        this.contractScale = contractScale;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getOutProjectNum() {
        return outProjectNum;
    }

    public void setOutProjectNum(String outProjectNum) {
        this.outProjectNum = outProjectNum;
    }

    public String getSaleContractNum() {
        return saleContractNum;
    }

    public void setSaleContractNum(String saleContractNum) {
        this.saleContractNum = saleContractNum;
    }

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public LocalDate getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(LocalDate siginTime) {
        this.siginTime = siginTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(LocalDate startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public LocalDate getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(LocalDate endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getContractRang() {
        return contractRang;
    }

    public void setContractRang(String contractRang) {
        this.contractRang = contractRang;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getFirstRelation() {
        return firstRelation;
    }

    public void setFirstRelation(String firstRelation) {
        this.firstRelation = firstRelation;
    }

    public String getFirstTel() {
        return firstTel;
    }

    public void setFirstTel(String firstTel) {
        this.firstTel = firstTel;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectChargeTel() {
        return projectChargeTel;
    }

    public void setProjectChargeTel(String projectChargeTel) {
        this.projectChargeTel = projectChargeTel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractText() {
        return contractText;
    }

    public void setContractText(String contractText) {
        this.contractText = contractText;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public PayWays getPayWays() {
        return payWays;
    }

    public void setPayWays(PayWays payWays) {
        this.payWays = payWays;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public PayFeeOrigin getPayFeeOrigin() {
        return payFeeOrigin;
    }

    public void setPayFeeOrigin(PayFeeOrigin payFeeOrigin) {
        this.payFeeOrigin = payFeeOrigin;
    }

    public String getFileCondition() {
        return fileCondition;
    }

    public void setFileCondition(String fileCondition) {
        this.fileCondition = fileCondition;
    }

    public Double getFileCount() {
        return fileCount;
    }

    public void setFileCount(Double fileCount) {
        this.fileCount = fileCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTempContractNum() {
        return tempContractNum;
    }

    public void setTempContractNum(String tempContractNum) {
        this.tempContractNum = tempContractNum;
    }
}
