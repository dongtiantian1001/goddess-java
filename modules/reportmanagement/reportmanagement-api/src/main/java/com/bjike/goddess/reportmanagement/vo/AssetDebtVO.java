package com.bjike.goddess.reportmanagement.vo;

/**
 * 资产负债总表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:37 ]
 * @Description: [ 资产负债总表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetDebtVO {

    /**
     * 资产id
     */
    private String assetId;

    /**
     * 资产
     */
    private String asset;

    /**
     * 资产行次
     */
    private Integer assetNum;

    /**
     * 资产年初数
     */
    private Double beginAsset;

    /**
     * 资产期末数
     */
    private Double endAsset;

    /**
     * 负债和所有者权益(或股东权益)id
     */
    private String debtId;

    /**
     * 负债和所有者权益(或股东权益)
     */
    private String debt;

    /**
     * 负债和所有者权益(或股东权益)行次
     */
    private Integer debtNum;

    /**
     * 负债年初数
     */
    private Double beginDebt;

    /**
     * 负债期末数
     */
    private Double endDebt;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
    }

    public Double getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(Double beginAsset) {
        this.beginAsset = beginAsset;
    }

    public Double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(Double endAsset) {
        this.endAsset = endAsset;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Integer getDebtNum() {
        return debtNum;
    }

    public void setDebtNum(Integer debtNum) {
        this.debtNum = debtNum;
    }

    public Double getBeginDebt() {
        return beginDebt;
    }

    public void setBeginDebt(Double beginDebt) {
        this.beginDebt = beginDebt;
    }

    public Double getEndDebt() {
        return endDebt;
    }

    public void setEndDebt(Double endDebt) {
        this.endDebt = endDebt;
    }
}