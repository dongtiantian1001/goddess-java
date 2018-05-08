package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.receivable.entity.Contractor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 回款明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private String area;

    /**
     * 项目内部名称
     */
    @NotBlank(message = "项目内部名称不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private String innerName;

    /**
     * 派工单编号
     */
    private String taskNum;

    /**
     * 派工合同号
     */
    private String contractNum;
    /**
     * 外包合同号
     */
    private String outsourcingNum;
    /**
     * 派工单价
     */
    @NotNull(message = "派工单价不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double taskPrice;

    /**
     * 合同规模数
     */
    @NotNull(message = "合同规模数不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double pactNum;

    /**
     * 合同规模金额(派工单价*合同规模数)
     */
    private Double pactMoney;

    /**
     * 已派工量
     */
    @NotNull(message = "已派工量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double pactSize;

    /**
     * 中兴派工金额(派工单价*已派工量)
     */
    private Double taskMoney;

    /**
     * 已完工量
     */
    @NotNull(message = "已完工量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double finishNum;

    /**
     * 已完工金额(派工单价*已完工量)
     */
    private Double finishMoney;

    /**
     * 未完工量
     */
    @NotNull(message = "未完工量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double unfinishNum;

    /**
     * 未完工金额(派工单价*未完工量)
     */
    private Double unfinishMoney;

    /**
     * 已交维数量
     */
    @NotNull(message = "已交维数量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double payTax;

    /**
     * 未交维数量
     */
    @NotNull(message = "未交维数量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double undeal;

    /**
     * 违约金
     */
    @NotNull(message = "违约金不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double penalty;

    /**
     * 实际结算数量
     */
    @NotNull(message = "实际结算数量不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @NotNull(message = "实际数量金额不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Double realCountMoney;

    /**
     * 完工时间
     */
    @NotBlank(message = "完工时间不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private String finishTime;

    /**
     * 验收交维时间
     */
    @NotBlank(message = "验收交维时间不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private String checkTime;

    /**
     * 签字审批时间
     */
    private String auditTime;

    /**
     * 结算审批时间
     */
    private String countTime;

    /**
     * 发票审核时间
     */
    private String billTime;

    /**
     * 预计支付时间
     */
    private String planTime;

    /**
     * 管理费(实际数量金额*承包商比例)
     */
    private Double managementFee;

    /**
     * 到账时间
     */
    private String accountTime;

    /**
     * 结算进度:A
     */
    private String progressA;

    /**
     * 结算进度:B
     */
    private String progressB;

    /**
     * 结算进度:C
     */
    private String progressC;

    /**
     * 结算进度:D
     */
    private String progressD;

    /**
     * 到账金额(实际数量金额*管理费)
     */
    private Double accountMoney;

    /**
     * 税金(到账金额*6.79%)
     */
    private Double taxes;

    /**
     * 税后金额(到账金额-税金)
     */
    private Double afterTax;

    /**
     * 剩余结算量(已派工量-实际结算数量)
     */
    private Double moreNum;

    /**
     * 剩余结算金额(派工单价*剩余结算量)
     */
    private Double moreMoney;

    /**
     * 承包商
     */
    @NotBlank(message = "承包商不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private String contractorId;

    /**
     * 是否已支付
     */
    @NotNull(message = "是否已支付不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Boolean pay;

    /**
     * 是否框架内
     */
    @NotNull(message = "是否框架内不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Boolean frame;

    /**
     * 是否有单次合同
     */
    @NotNull(message = "是否有单次合同不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Boolean pact;

    /**
     * 是否已走结算流程
     */
    @NotNull(message = "是否已走结算流程不能为空",groups = {ReceivableSubsidiaryTO.TestAdd.class,ReceivableSubsidiaryTO.TestEdit.class})
    private Boolean flow;

    private String contractorName;

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOutsourcingNum() {
        return outsourcingNum;
    }

    public void setOutsourcingNum(String outsourcingNum) {
        this.outsourcingNum = outsourcingNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Double getPactNum() {
        return pactNum;
    }

    public void setPactNum(Double pactNum) {
        this.pactNum = pactNum;
    }

    public Double getPactMoney() {
        return pactMoney;
    }

    public void setPactMoney(Double pactMoney) {
        this.pactMoney = pactMoney;
    }

    public Double getPactSize() {
        return pactSize;
    }

    public void setPactSize(Double pactSize) {
        this.pactSize = pactSize;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Double finishNum) {
        this.finishNum = finishNum;
    }

    public Double getFinishMoney() {
        return finishMoney;
    }

    public void setFinishMoney(Double finishMoney) {
        this.finishMoney = finishMoney;
    }

    public Double getUnfinishNum() {
        return unfinishNum;
    }

    public void setUnfinishNum(Double unfinishNum) {
        this.unfinishNum = unfinishNum;
    }

    public Double getUnfinishMoney() {
        return unfinishMoney;
    }

    public void setUnfinishMoney(Double unfinishMoney) {
        this.unfinishMoney = unfinishMoney;
    }

    public Double getPayTax() {
        return payTax;
    }

    public void setPayTax(Double payTax) {
        this.payTax = payTax;
    }

    public Double getUndeal() {
        return undeal;
    }

    public void setUndeal(Double undeal) {
        this.undeal = undeal;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Double getRealCountNum() {
        return realCountNum;
    }

    public void setRealCountNum(Double realCountNum) {
        this.realCountNum = realCountNum;
    }

    public Double getRealCountMoney() {
        return realCountMoney;
    }

    public void setRealCountMoney(Double realCountMoney) {
        this.realCountMoney = realCountMoney;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public String getProgressA() {
        return progressA;
    }

    public void setProgressA(String progressA) {
        this.progressA = progressA;
    }

    public String getProgressB() {
        return progressB;
    }

    public void setProgressB(String progressB) {
        this.progressB = progressB;
    }

    public String getProgressC() {
        return progressC;
    }

    public void setProgressC(String progressC) {
        this.progressC = progressC;
    }

    public String getProgressD() {
        return progressD;
    }

    public void setProgressD(String progressD) {
        this.progressD = progressD;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public Double getMoreNum() {
        return moreNum;
    }

    public void setMoreNum(Double moreNum) {
        this.moreNum = moreNum;
    }

    public Double getMoreMoney() {
        return moreMoney;
    }

    public void setMoreMoney(Double moreMoney) {
        this.moreMoney = moreMoney;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Boolean getFrame() {
        return frame;
    }

    public void setFrame(Boolean frame) {
        this.frame = frame;
    }

    public Boolean getPact() {
        return pact;
    }

    public void setPact(Boolean pact) {
        this.pact = pact;
    }

    public Boolean getFlow() {
        return flow;
    }

    public void setFlow(Boolean flow) {
        this.flow = flow;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }
    /*public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }*/
}