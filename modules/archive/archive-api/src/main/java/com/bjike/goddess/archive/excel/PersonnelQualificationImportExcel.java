package com.bjike.goddess.archive.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 人员资质业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonnelQualificationImportExcel extends BaseBO {

//    /**
//     * 更新时间
//     */
//    @ExcelHeader(name = "更新时间")
//    private String modifyTime;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String username;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别")
    private String sex;

    /**
     * 身份证号码
     */
    @ExcelHeader(name = "身份证号码")
    private String identityCard;

//    /**
//     * 劳动关系类型id
//     */
//    @ExcelHeader(name = "劳动关系类型id")
//    private String loborId;

    /**
     * 劳动关系类型
     */
    @ExcelHeader(name = "劳动关系类型")
    private String laborName;

//    /**
//     * 社保购买类型
//     */
//    @ExcelHeader(name = "社保购买类型")
//    private String socialId;

    /**
     * 社保购买类型
     */
    @ExcelHeader(name = "社保购买类型")
    private String socialName;

    /**
     * 资质证书1
     */
    @ExcelHeader(name = "资质证书1")
    private String certificateOne;

    /**
     * 查询网站链接1
     */
    @ExcelHeader(name = "查询网站链接1")
    private String websiteOne;

    /**
     * 证件有效期限1
     */
    @ExcelHeader(name = "证件有效期限1")
    private String effectiveOne;

    /**
     * 资质证书2
     */
    @ExcelHeader(name = "资质证书2")
    private String certificateTwo;

    /**
     * 查询网站链接2
     */
    @ExcelHeader(name = "查询网站链接2")
    private String websiteTwo;

    /**
     * 证件有效期限2
     */
    @ExcelHeader(name = "证件有效期限2")
    private String effectiveTwo;

    /**
     * 备注信息
     */
    @ExcelHeader(name = "备注信息")
    private String remark;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getCertificateOne() {
        return certificateOne;
    }

    public void setCertificateOne(String certificateOne) {
        this.certificateOne = certificateOne;
    }

    public String getWebsiteOne() {
        return websiteOne;
    }

    public void setWebsiteOne(String websiteOne) {
        this.websiteOne = websiteOne;
    }

    public String getEffectiveOne() {
        return effectiveOne;
    }

    public void setEffectiveOne(String effectiveOne) {
        this.effectiveOne = effectiveOne;
    }

    public String getCertificateTwo() {
        return certificateTwo;
    }

    public void setCertificateTwo(String certificateTwo) {
        this.certificateTwo = certificateTwo;
    }

    public String getWebsiteTwo() {
        return websiteTwo;
    }

    public void setWebsiteTwo(String websiteTwo) {
        this.websiteTwo = websiteTwo;
    }

    public String getEffectiveTwo() {
        return effectiveTwo;
    }

    public void setEffectiveTwo(String effectiveTwo) {
        this.effectiveTwo = effectiveTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}