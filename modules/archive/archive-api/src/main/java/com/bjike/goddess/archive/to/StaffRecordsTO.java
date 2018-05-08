package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 员工档案
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecordsTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;


    /**
     * 资质/认证证书
     */
    @NotBlank(message = "资质/认证证书不能为空", groups = {ADD.class, EDIT.class})
    private String litterae;

    /**
     * 是否购买社保
     */
    @NotNull(message = "是否购买社保不能为空", groups = {ADD.class, EDIT.class})
    private Boolean buySecurity;

    /**
     * 社保购买类型
     */
    @NotBlank(message = "社保购买类型不能为空", groups = {ADD.class, EDIT.class})
    private String securityType;

    /**
     * 购买社保所属公司
     */
    @NotBlank(message = "购买社保所属公司不能为空", groups = {ADD.class, EDIT.class})
    private String company;


    /**
     * 学历
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String education;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空", groups = {ADD.class, EDIT.class})
    private String major;

    /**
     * 毕业学校
     */
    @NotBlank(message = "毕业学校不能为空", groups = {ADD.class, EDIT.class})
    private String school;

    /**
     * 毕业时间
     */
    @NotBlank(message = "毕业时间不能为空", groups = {ADD.class, EDIT.class})
    private String graduate;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空", groups = {ADD.class, EDIT.class})
    private String entryTime;

    /**
     * 在职时间(月)
     */
    @NotNull(message = "在职时间(月)不能为空", groups = {ADD.class, EDIT.class})
    private Integer seniority;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空", groups = {ADD.class, EDIT.class})
    private String telephone;

    /**
     * qq号
     */
    @NotBlank(message = "qq号不能为空", groups = {ADD.class, EDIT.class})
    private String qq;

    /**
     * 个人邮箱账号
     */
    @NotBlank(message = "个人邮箱账号不能为空", groups = {ADD.class, EDIT.class})
    private String selfEmail;

    /**
     * 出生日期
     */
    @NotBlank(message = "出生日期不能为空", groups = {ADD.class, EDIT.class})
    private String birth;

    /**
     * 户籍地址
     */
    @NotBlank(message = "户籍地址不能为空", groups = {ADD.class, EDIT.class})
    private String address;

    /**
     * 目前住宿地址
     */
    @NotBlank(message = "目前住宿地址不能为空", groups = {ADD.class, EDIT.class})
    private String nowAddress;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空", groups = {ADD.class, EDIT.class})
    private String identityCard;

    /**
     * 银行卡号码
     */
    @NotBlank(message = "银行卡号码不能为空", groups = {ADD.class, EDIT.class})
    private String bankCard;

    /**
     * 开户行
     */
    @NotBlank(message = "开户行不能为空", groups = {ADD.class, EDIT.class})
    private String bank;

    /**
     * 离职时间
     */
    private String dimissionTime;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String email;

    /**
     * 状态
     */
    private Status status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLitterae() {
        return litterae;
    }

    public void setLitterae(String litterae) {
        this.litterae = litterae;
    }

    public Boolean getBuySecurity() {
        return buySecurity;
    }

    public void setBuySecurity(Boolean buySecurity) {
        this.buySecurity = buySecurity;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSelfEmail() {
        return selfEmail;
    }

    public void setSelfEmail(String selfEmail) {
        this.selfEmail = selfEmail;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}