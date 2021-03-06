package com.bjike.goddess.staffshares.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 出售记录表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SellscheduleTO extends BaseTO {

    /**
     * 出售人
     */
    private String sellName;

    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 出售股数
     */
    @NotNull(message = "出售股数不能为空", groups = {EDIT.class})
    private Long sellNum;

    /**
     * 出售价格
     */
    private Double sellPrice;

    /**
     * 出售金额
     */
    @NotNull(message = "出售金额不能为空", groups = {EDIT.class})
    private Double totalSellPrice;

    /**
     * 出售时间
     */
    private String sellTime;

    /**
     * 剩余出售量
     */
    private Long number;

    /**
     * 购买人
     */
    private String buyName;

    /**
     * 购买股数
     */
    private Long purchaseNum;

    /**
     * 购买时间
     */
    private String buyTime;


    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSellNum() {
        return sellNum;
    }

    public void setSellNum(Long sellNum) {
        this.sellNum = sellNum;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(Double totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
}