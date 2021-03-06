package com.bjike.goddess.managefee.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 管理费业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManageFeeAreaExport extends BaseBO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;


    /**
     * 目标管理费用
     */
    @ExcelHeader(name = "目标管理费用",notNull = true)
    private Double targetFee;

    /**
     * 实际管理费用
     */
    @ExcelHeader(name = "实际管理费用",notNull = true)
    private Double actualFee;

    /**
     * 比率
     */
    @ExcelHeader(name = "比率",notNull = true)
    private String ratePersent;

    /**
     * 差额
     */
    @ExcelHeader(name = "差额",notNull = true)
    private Double balance;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getTargetFee() {
        return targetFee;
    }

    public void setTargetFee(Double targetFee) {
        this.targetFee = targetFee;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
    }

    public String getRatePersent() {
        return ratePersent;
    }

    public void setRatePersent(String ratePersent) {
        this.ratePersent = ratePersent;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}