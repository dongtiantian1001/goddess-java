package com.bjike.goddess.voucher.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.voucher.enums.AuditStatus;
import com.bjike.goddess.voucher.enums.CheckStatus;
import com.bjike.goddess.voucher.enums.TransferStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


/**
 * 记账凭证生成
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "voucher_vouchergenerate")
public class VoucherGenerate extends BaseEntity {

    /**
     * 凭证字
     */
    @Column(name = "voucherWord", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '凭证字'")
    private String voucherWord;

    /**
     * 凭证字号
     */
    @Column(name = "voucherNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '凭证字号'")
    private Double voucherNum;

    /**
     * 凭证日期
     */
    @Column(name = "voucherDate", nullable = false, columnDefinition = "DATE   COMMENT '凭证日期'")
    private LocalDate voucherDate;

    /**
     * 一级科目
     */
    @Column(name = "firstSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject", columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 借方金额
     */
    @Column(name = "borrowMoney", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '借方金额'")
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    @Column(name = "loanMoney", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '贷方金额'")
    private Double loanMoney;

    /**
     * 摘要
     */
    @Column(name = "sumary", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String sumary;

    /**
     * 来源
     */
    @Column(name = "source" , nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '来源'")
    private String source;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 制单人
     */
    @Column(name = "ticketer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '制单人'")
    private String ticketer;

    /**
     * 票据数量
     */
    @Column(name = "ticketNum",  columnDefinition = "DECIMAL(10,2)   COMMENT '票据数量'")
    private Double ticketNum;

    /**
     * 附件
     */
    @Column(name = "extraFile",  columnDefinition = "VARCHAR(255)   COMMENT '附件'")
    private String extraFile;

    /**
     * 审核人
     */
    @Column(name = "auditor",  columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 审核状态
     */
    @Column(name = "auditStatus", nullable = false, columnDefinition = "INT(2)  COMMENT '审核状态'")
    private AuditStatus auditStatus;

    /**
     * 过帐状态
     */
    @Column(name = "transferStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '过帐状态'")
    private TransferStatus transferStatus;

    /**
     * 结帐状态
     */
    @Column(name = "checkStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '结帐状态'")
    private CheckStatus checkStatus;


    /**
     * 合计id
     */
    @Column(name = "totalId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合计id'")
    private String totalId;

    /**
     * 凭证唯一id(一个凭证多个科目的uId一样)
     */
    @Column(name = "uId", nullable = false, columnDefinition = "VARCHAR(60)   COMMENT '凭证唯一id(一个凭证多个科目的uId一样)'")
    private String uId;

    /**
     * 一级科目code
     */
    @Column(name = "firstSubjectCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级科目code'")
    private String firstSubjectCode;

    /**
     * 二级科目code
     */
    @Column(name = "secondSubjectCode", columnDefinition = "VARCHAR(255)   COMMENT '二级科目code'")
    private String secondSubjectCode;

    /**
     * 三级科目code
     */
    @Column(name = "thirdSubjectCode",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目code'")
    private String thirdSubjectCode;

//    private Double moneyTotal;

    /**
     * 类别
     */
    @Column(name = "type",  columnDefinition = "VARCHAR(8)   COMMENT '类别'")
    private String type;


    public String getVoucherWord() {
        return voucherWord;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

    public Double getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(Double voucherNum) {
        this.voucherNum = voucherNum;
    }

    public LocalDate getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(LocalDate voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getTicketer() {
        return ticketer;
    }

    public void setTicketer(String ticketer) {
        this.ticketer = ticketer;
    }

    public Double getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Double ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getExtraFile() {
        return extraFile;
    }

    public void setExtraFile(String extraFile) {
        this.extraFile = extraFile;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getFirstSubjectCode() {
        return firstSubjectCode;
    }

    public void setFirstSubjectCode(String firstSubjectCode) {
        this.firstSubjectCode = firstSubjectCode;
    }

    public String getSecondSubjectCode() {
        return secondSubjectCode;
    }

    public void setSecondSubjectCode(String secondSubjectCode) {
        this.secondSubjectCode = secondSubjectCode;
    }

    public String getThirdSubjectCode() {
        return thirdSubjectCode;
    }

    public void setThirdSubjectCode(String thirdSubjectCode) {
        this.thirdSubjectCode = thirdSubjectCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}