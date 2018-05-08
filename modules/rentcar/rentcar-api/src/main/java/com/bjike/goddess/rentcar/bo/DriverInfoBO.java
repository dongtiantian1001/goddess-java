package com.bjike.goddess.rentcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.rentcar.enums.AgreementStatus;

import java.time.LocalDateTime;

/**
 * 租车协议管理业务传输对象
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:47 ]
 * @Description: [ 租车协议管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverInfoBO extends BaseBO {

//    /**
//     * 账务模块审核意见
//     */
//    private String suggest;
//
//    /**
//     * 审核结果
//     */
//    private Boolean audit;

    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 租车单价
     */
    private Double rentCarUtilCost;

    /**
     * 结算方式
     */
    private String payWay;

    /**
     * 司机名称
     */
    private String driver;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 地址
     */
    private String address;

    /**
     * 车辆型号
     */
    private String carModel;

    /**
     * 车牌号码
     */
    private String carNum;

    /**
     * 发动机号
     */
    private String engineNum;

    /**
     * 汽车购买时间
     */
    private String buyDate;

    /**
     * 汽车使用时长
     */
    private Double useTime;

    /**
     * 汽车排量
     */
    private Double emissions;

    /**
     * 本车耗油
     */
    private Double carFuel;

    /**
     * 是否确定签订协议
     */
    private Boolean enSureAgreement;

    /**
     * 是否签订租车协议
     */
    private Boolean agreement;

    /**
     * 是否提供协议附件
     */
    private Boolean attachment;

    /**
     * 签订日期
     */
    private String signDate;

    /**
     * 合同开始日期
     */
    private String startDate;

    /**
     * 合同终止日期
     */
    private String endDate;

    /**
     * 是否提供行驶证照片
     */
    private Boolean travel;

    /**
     * 行驶证所有者
     */
    private String travelName;

    /**
     * 是否提供驾驶证照片
     */
    private Boolean driverLicense;

    /**
     * 驾驶证所有者
     */
    private String licenseName;

    /**
     * 是否提供本车辆保险
     */
    private Boolean carInsurance;

    /**
     * 开户人
     */
    private String cardUser;

    /**
     * 开户账号
     */
    private String cardNum;

    /**
     * 开户行
     */
    private String cardBank;

    /**
     * 协议状态
     */
    private AgreementStatus agreementStatus;

    /**
     * 是否解约
     */
    private Boolean breakAgreement;

    /**
     * 备注
     */
    private String remark;

    /**
     * 解除时间
     */
    private String liftTime;


    /**
     * 是否为自己添加的数据
     */
    private Boolean ifAdd;

    public Boolean getIfAdd() {
        return ifAdd;
    }

    public void setIfAdd(Boolean ifAdd) {
        this.ifAdd = ifAdd;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getUseTime() {
        return useTime;
    }

    public void setUseTime(Double useTime) {
        this.useTime = useTime;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }

    public Double getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(Double carFuel) {
        this.carFuel = carFuel;
    }

    public Boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public String getSignDate() {
        return signDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getTravel() {
        return travel;
    }

    public void setTravel(Boolean travel) {
        this.travel = travel;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public Boolean getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(Boolean driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public Boolean getCarInsurance() {
        return carInsurance;
    }

    public void setCarInsurance(Boolean carInsurance) {
        this.carInsurance = carInsurance;
    }

    public String getCardUser() {
        return cardUser;
    }

    public void setCardUser(String cardUser) {
        this.cardUser = cardUser;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public Boolean getBreakAgreement() {
        return breakAgreement;
    }

    public void setBreakAgreement(Boolean breakAgreement) {
        this.breakAgreement = breakAgreement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getRentCarUtilCost() {
        return rentCarUtilCost;
    }

    public void setRentCarUtilCost(Double rentCarUtilCost) {
        this.rentCarUtilCost = rentCarUtilCost;
    }

    public Boolean getEnSureAgreement() {
        return enSureAgreement;
    }

    public void setEnSureAgreement(Boolean enSureAgreement) {
        this.enSureAgreement = enSureAgreement;
    }

    public AgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(AgreementStatus agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getLiftTime() {
        return liftTime;
    }

    public void setLiftTime(String liftTime) {
        this.liftTime = liftTime;
    }
}