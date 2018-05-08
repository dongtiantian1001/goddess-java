package com.bjike.goddess.intromanage.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.intromanage.type.DemandType;

import java.util.List;

/**
 * 公司简介表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroVO {

    /**
     * id
     */
    private String id;

    /**
     * 公司id
     */
    private String firmId;

    /**
     * 公司名称
     */
    private String firmName;

    /**
     * 公司性质
     */
    private String firmNature;

    /**
     * 注册资金
     */
    private String registerMoney;

    /**
     * 注册时间
     */
    private String registerDate;

    /**
     * 公司精神
     */
    private String firmSpirit;

    /**
     * 服务意识
     */
    private String serviceAwareness;

    /**
     * 公司宗旨
     */
    private String firmTenet;

    /**
     * 人才观
     */
    private String talentView;

    /**
     * 经营观
     */
    private String operationView;

    /**
     * 质量观
     */
    private String qualityView;

    /**
     * 组织结构
     */
    private String organization;

    /**
     * 管理模式
     */
    private String manageModel;

    /**
     * 服务团队介绍
     */
    private String serviceTeamIntro;

    /**
     * 员工数量
     */
    private String staffNo;

    /**
     * 囊括区域
     */
    private String includeArea;

    /**
     * 一体化解决方案
     */
    private String solvingScheme;

    /**
     * 需求类型
     */
    private DemandType demandType;
    /**
     * 更新（制作）时间
     */
    private String updateDate;

    /**
     * 战略定位
     */
    private String positioning;

    /**
     * 状态
     */
    private Status status;

    /**
     * 荣誉与资质
     */
    private List<HonorAndQualityVO> honorAndQualityVOS;
    /**
     * 主业介绍
     */
    private List<MainBusinessIntroVO> mainBusinessIntroVOS;
    /**
     * 成功案例
     */
    private List<SuccessStoriesVO> successStoriesVOS;

    /**
     * 客户及合作伙伴
     */
    private List<CustomerAndPartnerVO> customerAndPartnerVOS;

    /**
     * 通讯途径
     */
    private List<CommunicationPathVO> communicationPathVOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmNature() {
        return firmNature;
    }

    public void setFirmNature(String firmNature) {
        this.firmNature = firmNature;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getFirmSpirit() {
        return firmSpirit;
    }

    public void setFirmSpirit(String firmSpirit) {
        this.firmSpirit = firmSpirit;
    }

    public String getServiceAwareness() {
        return serviceAwareness;
    }

    public void setServiceAwareness(String serviceAwareness) {
        this.serviceAwareness = serviceAwareness;
    }

    public String getFirmTenet() {
        return firmTenet;
    }

    public void setFirmTenet(String firmTenet) {
        this.firmTenet = firmTenet;
    }

    public String getTalentView() {
        return talentView;
    }

    public void setTalentView(String talentView) {
        this.talentView = talentView;
    }

    public String getOperationView() {
        return operationView;
    }

    public void setOperationView(String operationView) {
        this.operationView = operationView;
    }

    public String getQualityView() {
        return qualityView;
    }

    public void setQualityView(String qualityView) {
        this.qualityView = qualityView;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getManageModel() {
        return manageModel;
    }

    public void setManageModel(String manageModel) {
        this.manageModel = manageModel;
    }

    public String getServiceTeamIntro() {
        return serviceTeamIntro;
    }

    public void setServiceTeamIntro(String serviceTeamIntro) {
        this.serviceTeamIntro = serviceTeamIntro;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getIncludeArea() {
        return includeArea;
    }

    public void setIncludeArea(String includeArea) {
        this.includeArea = includeArea;
    }

    public String getSolvingScheme() {
        return solvingScheme;
    }

    public void setSolvingScheme(String solvingScheme) {
        this.solvingScheme = solvingScheme;
    }

    public DemandType getDemandType() {
        return demandType;
    }

    public void setDemandType(DemandType demandType) {
        this.demandType = demandType;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPositioning() {
        return positioning;
    }

    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<HonorAndQualityVO> getHonorAndQualityVOS() {
        return honorAndQualityVOS;
    }

    public void setHonorAndQualityVOS(List<HonorAndQualityVO> honorAndQualityVOS) {
        this.honorAndQualityVOS = honorAndQualityVOS;
    }

    public List<MainBusinessIntroVO> getMainBusinessIntroVOS() {
        return mainBusinessIntroVOS;
    }

    public void setMainBusinessIntroVOS(List<MainBusinessIntroVO> mainBusinessIntroVOS) {
        this.mainBusinessIntroVOS = mainBusinessIntroVOS;
    }

    public List<SuccessStoriesVO> getSuccessStoriesVOS() {
        return successStoriesVOS;
    }

    public void setSuccessStoriesVOS(List<SuccessStoriesVO> successStoriesVOS) {
        this.successStoriesVOS = successStoriesVOS;
    }

    public List<CustomerAndPartnerVO> getCustomerAndPartnerVOS() {
        return customerAndPartnerVOS;
    }

    public void setCustomerAndPartnerVOS(List<CustomerAndPartnerVO> customerAndPartnerVOS) {
        this.customerAndPartnerVOS = customerAndPartnerVOS;
    }

    public List<CommunicationPathVO> getCommunicationPathVOS() {
        return communicationPathVOS;
    }

    public void setCommunicationPathVOS(List<CommunicationPathVO> communicationPathVOS) {
        this.communicationPathVOS = communicationPathVOS;
    }
}