package com.bjike.goddess.supplier.vo;

/**
 * 企业资质表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 03:58 ]
 * @Description: [ 企业资质表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EnterpriseQualificaVO {

    /**
     * id
     */
    private String id;
    /**
     * 供应商信息登记id
     */
    private String supplierInfoRegiId;
    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 证书编号
     */
    private String certificateNum;

    /**
     * 有效期限
     */
    private String validTerm;

    /**
     * 颁发单位
     */
    private String issuedUnit;

    /**
     * 资质等级
     */
    private String qualifiLevel;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierInfoRegiId() {
        return supplierInfoRegiId;
    }

    public void setSupplierInfoRegiId(String supplierInfoRegiId) {
        this.supplierInfoRegiId = supplierInfoRegiId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getValidTerm() {
        return validTerm;
    }

    public void setValidTerm(String validTerm) {
        this.validTerm = validTerm;
    }

    public String getIssuedUnit() {
        return issuedUnit;
    }

    public void setIssuedUnit(String issuedUnit) {
        this.issuedUnit = issuedUnit;
    }

    public String getQualifiLevel() {
        return qualifiLevel;
    }

    public void setQualifiLevel(String qualifiLevel) {
        this.qualifiLevel = qualifiLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}