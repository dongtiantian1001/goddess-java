package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 个人简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualResumeTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 部门/项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "部门/项目组不能为空")
    private String department;

    /**
     * 姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工编号不能为空")
    private String employeeId;

    /**
     * 岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String post;

    /**
     * 工作邮箱
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "工作邮箱不能为空")
    private String eMsil;

    /**
     * 入职日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "入职日期不能为空")
    private String entryDate;

    /**
     * 在职时间(月)
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "在职时间(月)不能为空")
    private String tenancyDuration;

    /**
     * 转正时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "转正时间不能为空")
    private String positiveTime;

    /**
     * 是否购买社保
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否购买社保不能为空")
    private Boolean whetherSocialSecurity;

    /**
     * 社保购买时间
     */
    private String buySocialSecurityTime;

    /**
     * 技能等级
     */
    private String skillRank;

    /**
     * 管理等级
     */
    private String manageGrade;

    /**
     * 项目提成
     */
    private String itemCommission;

    /**
     * 管理提成
     */
    private String manageCommission;

    /**
     * 奖励分数
     */
    private Integer awardScore;

    /**
     * 处罚分数
     */
    private Integer penaltyScore;

    /**
     * 经验值
     */
    private String empiricalValue;

    /**
     * 补助额度
     */
    private String subsidyAmount;

    /**
     * 年假天数
     */
    private Double annualLeave;

    /**
     * 个人愿景
     */
    private String individualVision;

    /**
     * 照片
     */
    private String picture;

    /**
     * 兴趣爱好
     */
    private String hobbies;

    /**
     * 个人邮箱
     */
    private String personalEmail;

    /**
     * 出生年月
     */
    private String dateOfBirth;

    /**
     * QQ号
     */
    private String qqNumber;

    /**
     * 微信号
     */
    private String wechatId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系人手机号
     */
    private String emergencyContactPhone;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String specialty;

    /**
     * 专业学校
     */
    private String academy;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 现居住地
     */
    private String nowResidence;

    /**
     * 户口地址
     */
    private String registeredAddress;

    /**
     * 工作年限
     */
    private String workExperience;

    /**
     * 员工奖励
     */
    private List<StaffRewardTO> staffRewardTOS;


    /**
     *
     * 员工荣誉
     */
    private List<StaffHonorTO> staffHonorTOS;


    /**
     *
     * 教育经历
     */
    private List<EducateExperienceTO> educateExperienceTOS;


    /**
     *
     * 工作经历
     */
    private List<WorkExperienceTO> workExperienceTOS;

    /**
     *
     * 证书情况
     */
    private List<CredentialSituationTO> credentialSituationTOS;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEMsil() {
        return eMsil;
    }

    public void setEMsil(String eMsil) {
        this.eMsil = eMsil;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getTenancyDuration() {
        return tenancyDuration;
    }

    public void setTenancyDuration(String tenancyDuration) {
        this.tenancyDuration = tenancyDuration;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
    }

    public Boolean getWhetherSocialSecurity() {
        return whetherSocialSecurity;
    }

    public void setWhetherSocialSecurity(Boolean whetherSocialSecurity) {
        this.whetherSocialSecurity = whetherSocialSecurity;
    }

    public String getBuySocialSecurityTime() {
        return buySocialSecurityTime;
    }

    public void setBuySocialSecurityTime(String buySocialSecurityTime) {
        this.buySocialSecurityTime = buySocialSecurityTime;
    }

    public String getSkillRank() {
        return skillRank;
    }

    public void setSkillRank(String skillRank) {
        this.skillRank = skillRank;
    }

    public String getManageGrade() {
        return manageGrade;
    }

    public void setManageGrade(String manageGrade) {
        this.manageGrade = manageGrade;
    }

    public String getItemCommission() {
        return itemCommission;
    }

    public void setItemCommission(String itemCommission) {
        this.itemCommission = itemCommission;
    }

    public String getManageCommission() {
        return manageCommission;
    }

    public void setManageCommission(String manageCommission) {
        this.manageCommission = manageCommission;
    }

    public Integer getAwardScore() {
        return awardScore;
    }

    public void setAwardScore(Integer awardScore) {
        this.awardScore = awardScore;
    }

    public Integer getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(Integer penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public String getEmpiricalValue() {
        return empiricalValue;
    }

    public void setEmpiricalValue(String empiricalValue) {
        this.empiricalValue = empiricalValue;
    }

    public String getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(String subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public Double getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Double annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getIndividualVision() {
        return individualVision;
    }

    public void setIndividualVision(String individualVision) {
        this.individualVision = individualVision;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getNowResidence() {
        return nowResidence;
    }

    public void setNowResidence(String nowResidence) {
        this.nowResidence = nowResidence;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String geteMsil() {
        return eMsil;
    }

    public void seteMsil(String eMsil) {
        this.eMsil = eMsil;
    }

    public List<StaffRewardTO> getStaffRewardTOS() {
        return staffRewardTOS;
    }

    public void setStaffRewardTOS(List<StaffRewardTO> staffRewardTOS) {
        this.staffRewardTOS = staffRewardTOS;
    }

    public List<StaffHonorTO> getStaffHonorTOS() {
        return staffHonorTOS;
    }

    public void setStaffHonorTOS(List<StaffHonorTO> staffHonorTOS) {
        this.staffHonorTOS = staffHonorTOS;
    }

    public List<EducateExperienceTO> getEducateExperienceTOS() {
        return educateExperienceTOS;
    }

    public void setEducateExperienceTOS(List<EducateExperienceTO> educateExperienceTOS) {
        this.educateExperienceTOS = educateExperienceTOS;
    }

    public List<WorkExperienceTO> getWorkExperienceTOS() {
        return workExperienceTOS;
    }

    public void setWorkExperienceTOS(List<WorkExperienceTO> workExperienceTOS) {
        this.workExperienceTOS = workExperienceTOS;
    }

    public List<CredentialSituationTO> getCredentialSituationTOS() {
        return credentialSituationTOS;
    }

    public void setCredentialSituationTOS(List<CredentialSituationTO> credentialSituationTOS) {
        this.credentialSituationTOS = credentialSituationTOS;
    }
}