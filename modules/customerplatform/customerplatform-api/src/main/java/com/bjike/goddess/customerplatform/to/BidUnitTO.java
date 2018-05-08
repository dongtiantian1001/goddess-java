package com.bjike.goddess.customerplatform.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 中标单位
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:20 ]
 * @Description: [ 中标单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidUnitTO extends BaseTO {

    /**
     * 企业名称
     */
    @NotBlank(message = "企业名称不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 公司成立时间
     */
    private String createCompanyTime;

    /**
     * 公司类型
     */
    @NotBlank(message = "公司类型不能为空", groups = {ADD.class, EDIT.class})
    private String companyType;

    /**
     * 主要产品和服务
     */
    @NotBlank(message = "主要产品和服务不能为空", groups = {ADD.class, EDIT.class})
    private String products;

    /**
     * 注册资金
     */
    private Double money;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空", groups = {ADD.class, EDIT.class})
    private String provinces;
    /**
     * 市
     */
    @NotBlank(message = "市不能为空", groups = {ADD.class, EDIT.class})
    private String city;
    /**
     * 区
     */
    @NotBlank(message = "区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 企业邮箱
     */
    @NotBlank(message = "企业邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String companyEmail;

    /**
     * 企业网站
     */
    private String website;

    /**
     * 企业电话
     */
    @NotBlank(message = "企业电话不能为空", groups = {ADD.class, EDIT.class})
    private String companyPhone;

    /**
     * 联系人名字
     */
    @NotBlank(message = "联系人名字不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 联系人性别
     */
    @NotBlank(message = "联系人性别不能为空", groups = {ADD.class, EDIT.class})
    private String sex;

    /**
     * 联系人职位
     */
    @NotBlank(message = "联系人职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 联系人电话
     */
    @NotBlank(message = "联系人电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 联系人微信
     */
    private String wechat;

//    /**
//     * 业务类型
//     */
//    private String businessType;

    /**
     * 需求类型
     */
    private String projectType;

    /**
     * 需求说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreateCompanyTime() {
        return createCompanyTime;
    }

    public void setCreateCompanyTime(String createCompanyTime) {
        this.createCompanyTime = createCompanyTime;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}