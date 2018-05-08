package com.bjike.goddess.staffshares.vo;

/**
 * 公司干股情况汇总表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 公司干股情况汇总表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DividendsConditionsVO {
    /**
     * id
     */
    private String id;

    /**
     * 发行总股数
     */
    private Long totalNum;

    /**
     * 发行总额
     */
    private Double issuance;

    /**
     * 持股人数
     */
    private Long shareholderNum;

    /**
     * 占总发行数量比
     */
    private String ratio;


    /**
     * 持股数/总持股数
     */
    private Long num;

    /**
     * 总股本/持有总额
     */
    private Double totalEquity;

    /**
     * 总收益/总收益额
     */
    private Double totalEarnings;


//    /**
//     * 方案代码
//     */
//    private String code;
//
//    /**
//     * 方案名称
//     */
//    private String name;
//
//    /**
//     * 持股人
//     */
//    private String shareholder;

//    /**
//     * 税后利润
//     */
//    private Double taxProfit;


//    /**
//     * 持股时间/购入时间
//     */
//    private String buyTime;
//
//    /**
//     * 持股状态
//     */
//    private String status;
//
//    /**
//     * 分红状态
//     */
//    private Boolean situation;
//
//    /**
//     * 分红收益
//     */
//    private Double earnings;
//
//
//    /**
//     * 分红时间
//     */
//    private String dividendTime;

//    /**
//     * 本次红利收益时间段
//     */
//    private String time;
//
//    /**
//     * 备注
//     */
//    private String remark;
//
//    /**
//     * 持股人确认情况
//     */
//    private Boolean situation;


    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Double getIssuance() {
        return issuance;
    }

    public void setIssuance(Double issuance) {
        this.issuance = issuance;
    }

    public Long getShareholderNum() {
        return shareholderNum;
    }

    public void setShareholderNum(Long shareholderNum) {
        this.shareholderNum = shareholderNum;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}