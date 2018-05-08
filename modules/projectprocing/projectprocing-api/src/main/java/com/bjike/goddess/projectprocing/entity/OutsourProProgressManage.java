package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 外包,半外包项目结算进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:03 ]
 * @Description: [ 外包,半外包项目结算进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_outsourproprogressmanage")
public class OutsourProProgressManage extends BaseEntity {

    /**
     * 测算分类
     */
    @Column(name = "measureType", columnDefinition = "VARCHAR(255)   COMMENT '测算分类'")
    private String measureType;

    /**
     * 测算是否通过
     */
    @Column(name = "is_measureThrough", columnDefinition = "TINYINT(1)  COMMENT '测算是否通过'")
    private Boolean measureThrough;

    /**
     * 签订时间
     */
    @Column(name = "signedTime", columnDefinition = "DATE   COMMENT '签订时间'")
    private LocalDate signedTime;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务类型
     */
    @Column(name = "bussType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String bussType;

    /**
     * 业务方向科目
     */
    @Column(name = "bussDirection", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String bussDirection;

    /**
     * 总包单位名称
     */
    @Column(name = "unitTotalPackage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '总包单位名称'")
    private String unitTotalPackage;

    /**
     * 分包单位名称
     */
    @Column(name = "subcontractorName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分包单位名称'")
    private String subcontractorName;

    /**
     * 所属项目组
     */
    @Column(name = "projectTeam", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectTeam;

    /**
     * 内部项目名称
     */
    @Column(name = "internalName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalName;

    /**
     * 是否有合同派工
     */
    @Column(name = "contractDispa", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否有合同派工'")
    private Boolean contractDispa;

    /**
     * 市场编号
     */
    @Column(name = "marketNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场编号'")
    private String marketNum;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 专业/工期
     */
    @Column(name = "majors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业/工期'")
    private String majors;

    /**
     * 是否有合同立项
     */
    @Column(name = "is_contractTerm", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否有合同立项'")
    private Boolean contractTerm;

    /**
     * 供应商编号
     */
    @Column(name = "supplierNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商编号'")
    private String supplierNum;

    /**
     * 供应商地区
     */
    @Column(name = "supplierArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商地区'")
    private String supplierArea;

    /**
     * 供应商名称
     */
    @Column(name = "supplierName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商名称'")
    private String supplierName;

    /**
     * 供应商类型
     */
    @Column(name = "supplierType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商类型'")
    private String supplierType;

    /**
     * 业务联络人
     */
    @Column(name = "bussLiaison", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务联络人'")
    private String bussLiaison;

    /**
     * 是否确定合作
     */
    @Column(name = "is_cooperation", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否确定合作'")
    private Boolean cooperation;

    /**
     * 外包合同签订时间
     */
    @Column(name = "contractSigningDate", nullable = false, columnDefinition = "DATE   COMMENT '外包合同签订时间'")
    private LocalDate contractSigningDate;

    /**
     * 外包界面
     */
    @Column(name = "outInterface", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包界面'")
    private String outInterface;

    /**
     * 外包金额
     */
    @Column(name = "outsourAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '外包金额'")
    private Double outsourAmount;

    /**
     * 税点
     */
    @Column(name = "taxPoint", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税点'")
    private String taxPoint;

    /**
     * 增值税发票额
     */
    @Column(name = "vatInvoice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '增值税发票额'")
    private Double vatInvoice;

    /**
     * 管理费
     */
    @Column(name = "manageFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理费'")
    private Double manageFee;

    /**
     * 付款方式
     */
    @Column(name = "payTerms", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '付款方式'")
    private String payTerms;

    /**
     * 结算批次
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算批次'")
    private Integer settleBatch;

    /**
     * 预估开工时间
     */
    @Column(name = "estimatedDate",  columnDefinition = "DATE   COMMENT '预估开工时间'")
    private LocalDate estimatedDate;

    /**
     * 实际开工日期
     */
    @Column(name = "actualCommDate", columnDefinition = "DATE   COMMENT '实际开工日期'")
    private LocalDate actualCommDate;

    /**
     * 实际完工日期
     */
    @Column(name = "actualDate",  columnDefinition = "DATE   COMMENT '实际完工日期'")
    private LocalDate actualDate;

    /**
     * 预计完工时间
     */
    @Column(name = "estimatedComDate",columnDefinition = "DATE   COMMENT '预计完工时间'")
    private LocalDate estimatedComDate;

    /**
     * 项目经理意见
     */
    @Column(name = "projectManOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '项目经理意见'")
    private String projectManOpinion;

    /**
     * 是否完工
     */
    @Column(name = "is_completion",  columnDefinition = "TINYINT(1)  COMMENT '是否完工'")
    private Boolean completion;

    /**
     * 是否提供完工合格依据
     */
    @Column(name = "is_provideQualified", columnDefinition = "TINYINT(1)   COMMENT '是否提供完工合格依据'")
    private Boolean provideQualified;

    /**
     * 是否验收
     */
    @Column(name = "is_acceptance", columnDefinition = "TINYINT(1)  COMMENT '是否验收'")
    private Boolean acceptance;

    /**
     * 验收人
     */
    @Column(name = "acceptancetor", columnDefinition = "VARCHAR(255)   COMMENT '验收人'")
    private String acceptancetor;

    /**
     * 验收是否通过
     */
    @Column(name = "is_acceptanceThrough",  columnDefinition = "TINYINT(1)  COMMENT '验收是否通过'")
    private Boolean acceptanceThrough;

    /**
     * 是否到账
     */
    @Column(name = "is_account",  columnDefinition = "TINYINT(1)  COMMENT '是否到账'")
    private Boolean account;

    /**
     * 结算流程进度
     */
    @Column(name = "settlementProcess",  columnDefinition = "VARCHAR(255)   COMMENT '结算流程进度'")
    private String settlementProcess;

    /**
     * 是否收到增值税发票
     */
    @Column(name = "is_receivedInvoice",  columnDefinition = "TINYINT(1)  COMMENT '是否收到增值税发票'")
    private Boolean receivedInvoice;

    /**
     * 到账务模块发票时间
     */
    @Column(name = "accoutModuleDate",  columnDefinition = "DATE   COMMENT '到账务模块发票时间'")
    private LocalDate accoutModuleDate;

    /**
     * 是否付款完成
     */
    @Column(name = "is_payDone", columnDefinition = "TINYINT(1)  COMMENT '是否付款完成'")
    private Boolean payDone;

    /**
     * 结算完成时间
     */
    @Column(name = "clearDate",  columnDefinition = "DATE   COMMENT '结算完成时间'")
    private LocalDate clearDate;

    /**
     * 是否闭环
     */
    @Column(name = "is_closedLoop", columnDefinition = "TINYINT(1)  COMMENT '是否闭环'" )
    private Boolean closedLoop;

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public Boolean getMeasureThrough() {
        return measureThrough;
    }

    public void setMeasureThrough(Boolean measureThrough) {
        this.measureThrough = measureThrough;
    }

    public LocalDate getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(LocalDate signedTime) {
        this.signedTime = signedTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getBussDirection() {
        return bussDirection;
    }

    public void setBussDirection(String bussDirection) {
        this.bussDirection = bussDirection;
    }

    public String getUnitTotalPackage() {
        return unitTotalPackage;
    }

    public void setUnitTotalPackage(String unitTotalPackage) {
        this.unitTotalPackage = unitTotalPackage;
    }

    public String getSubcontractorName() {
        return subcontractorName;
    }

    public void setSubcontractorName(String subcontractorName) {
        this.subcontractorName = subcontractorName;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public Boolean getContractDispa() {
        return contractDispa;
    }

    public void setContractDispa(Boolean contractDispa) {
        this.contractDispa = contractDispa;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public Boolean getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Boolean contractTerm) {
        this.contractTerm = contractTerm;
    }

    public String getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    public String getSupplierArea() {
        return supplierArea;
    }

    public void setSupplierArea(String supplierArea) {
        this.supplierArea = supplierArea;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getBussLiaison() {
        return bussLiaison;
    }

    public void setBussLiaison(String bussLiaison) {
        this.bussLiaison = bussLiaison;
    }

    public Boolean getCooperation() {
        return cooperation;
    }

    public void setCooperation(Boolean cooperation) {
        this.cooperation = cooperation;
    }

    public LocalDate getContractSigningDate() {
        return contractSigningDate;
    }

    public void setContractSigningDate(LocalDate contractSigningDate) {
        this.contractSigningDate = contractSigningDate;
    }

    public String getOutInterface() {
        return outInterface;
    }

    public void setOutInterface(String outInterface) {
        this.outInterface = outInterface;
    }

    public Double getOutsourAmount() {
        return outsourAmount;
    }

    public void setOutsourAmount(Double outsourAmount) {
        this.outsourAmount = outsourAmount;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public Double getVatInvoice() {
        return vatInvoice;
    }

    public void setVatInvoice(Double vatInvoice) {
        this.vatInvoice = vatInvoice;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public String getPayTerms() {
        return payTerms;
    }

    public void setPayTerms(String payTerms) {
        this.payTerms = payTerms;
    }

    public Integer getSettleBatch() {
        return settleBatch;
    }

    public void setSettleBatch(Integer settleBatch) {
        this.settleBatch = settleBatch;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public LocalDate getActualCommDate() {
        return actualCommDate;
    }

    public void setActualCommDate(LocalDate actualCommDate) {
        this.actualCommDate = actualCommDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public LocalDate getEstimatedComDate() {
        return estimatedComDate;
    }

    public void setEstimatedComDate(LocalDate estimatedComDate) {
        this.estimatedComDate = estimatedComDate;
    }

    public String getProjectManOpinion() {
        return projectManOpinion;
    }

    public void setProjectManOpinion(String projectManOpinion) {
        this.projectManOpinion = projectManOpinion;
    }

    public Boolean getCompletion() {
        return completion;
    }

    public void setCompletion(Boolean completion) {
        this.completion = completion;
    }

    public Boolean getProvideQualified() {
        return provideQualified;
    }

    public void setProvideQualified(Boolean provideQualified) {
        this.provideQualified = provideQualified;
    }

    public Boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }

    public String getAcceptancetor() {
        return acceptancetor;
    }

    public void setAcceptancetor(String acceptancetor) {
        this.acceptancetor = acceptancetor;
    }

    public Boolean getAcceptanceThrough() {
        return acceptanceThrough;
    }

    public void setAcceptanceThrough(Boolean acceptanceThrough) {
        this.acceptanceThrough = acceptanceThrough;
    }

    public Boolean getAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public String getSettlementProcess() {
        return settlementProcess;
    }

    public void setSettlementProcess(String settlementProcess) {
        this.settlementProcess = settlementProcess;
    }

    public Boolean getReceivedInvoice() {
        return receivedInvoice;
    }

    public void setReceivedInvoice(Boolean receivedInvoice) {
        this.receivedInvoice = receivedInvoice;
    }

    public LocalDate getAccoutModuleDate() {
        return accoutModuleDate;
    }

    public void setAccoutModuleDate(LocalDate accoutModuleDate) {
        this.accoutModuleDate = accoutModuleDate;
    }

    public Boolean getPayDone() {
        return payDone;
    }

    public void setPayDone(Boolean payDone) {
        this.payDone = payDone;
    }

    public LocalDate getClearDate() {
        return clearDate;
    }

    public void setClearDate(LocalDate clearDate) {
        this.clearDate = clearDate;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }
}