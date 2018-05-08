package com.bjike.goddess.staffentry.vo;

import com.bjike.goddess.organize.enums.StaffStatus;

import java.util.List;

/**
 * 入职登记表现层对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 11:11]
 * @Description: [入职登记表现层对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryRegisterVO {

    /**
     * 入职登记
     */
    private String id;

    /**
     * 员工编号
     */
    private String empNumber;
    /**
     * 姓名
     */
    private String username;

    /**
     * 性别0男1女
     */
    private Integer gender;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 民族
     */
    private String nation;

    /**
     * 婚姻状况
     */
    private String marriage;

    /**
     * 政治面貌
     */
    private String politicsStatus;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 身高
     */
    private String stature;

    /**
     * 专业
     */
    private String profession;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业学校
     */
    private String schoolTag;

    /**
     * 毕业时间
     */
    private String graduationDate;

    /**
     * 健康状况
     */
    private String healthStatus;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 个人邮箱账号
     */
    private String email;

    /**
     * 紧急情况联系人
     */
    private String emergencyContact;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 户口地址
     */
    private String registeredAddress;

    /**
     * 目前住宿地址
     */
    private String location;

    /**
     * 兴趣爱好
     */
    private String hobbies;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 地区
     */
    private String area;
    /**
     * 部门/项目组
     */
    private String department;
    /**
     * 职位 
     */
    private String position;
    /**
     * 入职日期 
     */
    private String inductionDate;
    /**
     * 状态 
     */
    private String staffStatus;
    /**
     * 家庭成员集合
     */
    private List<FamilyMemberVO> familyMemberVOList;

    /**
     * 学习经历集合
     */
    private List<StudyExperienceVO> studyExperienceVOList;

    /**
     * 工作经历集合
     */
    private List<WorkExperienceVO> workExperienceVOList;

    /**
     * 证书集合
     */
    private List<CredentialVO> credentialVOList;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolTag() {
        return schoolTag;
    }

    public void setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInductionDate() {
        return inductionDate;
    }

    public void setInductionDate(String inductionDate) {
        this.inductionDate = inductionDate;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public List<FamilyMemberVO> getFamilyMemberVOList() {
        return familyMemberVOList;
    }

    public void setFamilyMemberVOList(List<FamilyMemberVO> familyMemberVOList) {
        this.familyMemberVOList = familyMemberVOList;
    }

    public List<StudyExperienceVO> getStudyExperienceVOList() {
        return studyExperienceVOList;
    }

    public void setStudyExperienceVOList(List<StudyExperienceVO> studyExperienceVOList) {
        this.studyExperienceVOList = studyExperienceVOList;
    }

    public List<WorkExperienceVO> getWorkExperienceVOList() {
        return workExperienceVOList;
    }

    public void setWorkExperienceVOList(List<WorkExperienceVO> workExperienceVOList) {
        this.workExperienceVOList = workExperienceVOList;
    }

    public List<CredentialVO> getCredentialVOList() {
        return credentialVOList;
    }

    public void setCredentialVOList(List<CredentialVO> credentialVOList) {
        this.credentialVOList = credentialVOList;
    }
}
