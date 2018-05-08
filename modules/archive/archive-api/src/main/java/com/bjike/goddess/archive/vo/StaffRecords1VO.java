package com.bjike.goddess.archive.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 员工档案表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecords1VO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 员工编号
     */
    private String serialNumber;

    /**
     * 项目组
     */
    private String project;

    /**
     * 职位
     */
    private String position;

    /**
     * 资质/认证证书
     */
    private String litterae;

    /**
     * 是否购买社保
     */
    private Boolean buySecurity;

    /**
     * 社保购买类型
     */
    private String securityType;

    /**
     * 购买社保所属公司
     */
    private String company;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String major;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 毕业时间
     */
    private String graduate;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 在职时间(月)
     */
    private Integer seniority;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 户籍地址
     */
    private String address;

    /**
     * 身份证号码
     */
    private String identityCard;

    /**
     * 银行卡号码
     */
    private String bankCard;

    /**
     * 离职时间
     */
    private String dimissionTime;
    /**
     * 开户行
     */
    private String bank;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Status status;

    /**
     * qq
     * @return
     */
    private String qq;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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
}