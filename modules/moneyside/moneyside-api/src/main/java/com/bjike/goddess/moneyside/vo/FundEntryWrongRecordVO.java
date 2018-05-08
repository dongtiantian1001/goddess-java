package com.bjike.goddess.moneyside.vo;

import com.bjike.goddess.moneyside.enums.PassStatus;

/**
 * 资金进入申请有误记录表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundEntryWrongRecordVO {

    /**
     * id
     */
    private String id;
    /**
     * 投资人
     */
    private String investor;

    /**
     * 资金进入方式
     */
    private String accessToFund;

    /**
     * 资金进入时间
     */
    private String fundEntryTime;

    /**
     * 金额
     */
    private Double money;

    /**
     * 打款账户名
     */
    private String moneyAccountName;

    /**
     * 打款账号
     */
    private String moneyAccount;

    /**
     * 确认人
     */
    private String confirmPeople;

    /**
     * 确认情况
     */
    private String confirmSituation;

    /**
     * 是否通过
     */
    private PassStatus pass;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public String getFundEntryTime() {
        return fundEntryTime;
    }

    public void setFundEntryTime(String fundEntryTime) {
        this.fundEntryTime = fundEntryTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public String getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(String moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public String getConfirmSituation() {
        return confirmSituation;
    }

    public void setConfirmSituation(String confirmSituation) {
        this.confirmSituation = confirmSituation;
    }

    public PassStatus getPass() {
        return pass;
    }

    public void setPass(PassStatus pass) {
        this.pass = pass;
    }
}