package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.AnalyseType;

/**
 * 现金流量分析
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyseTO extends BaseTO {

    /**
     * 序号
     */
    private Integer num;

    /**
     * 项目
     */
    private String project;

    /**
     * 金额
     */
    private String money;

    /**
     * 比率
     */
    private String rate;

    /**
     * 分析类型
     */
    private AnalyseType analyseType;

    /**
     * 分析
     */
    private String analyse;

    /**
     * 管理建议
     */
    private String advice;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public AnalyseType getAnalyseType() {
        return analyseType;
    }

    public void setAnalyseType(AnalyseType analyseType) {
        this.analyseType = analyseType;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}