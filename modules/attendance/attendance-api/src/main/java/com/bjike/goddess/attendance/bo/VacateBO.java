package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.attendance.enums.VacateType;

import java.io.Serializable;
import java.util.List;

/**
 * 请假管理业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateBO implements Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 请假时间
     */
    private String date;
    /**
     * uuid
     */
    private String uuid;
//    /**
//     * 员工编号
//     */
//    private String employeeNumber;

    /**
     * 请假人
     */
    private String name;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String depart;

    /**
     * 职位
     */
    private String position;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 请假时长
     */
    private Double time;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 填单时间是否符合提前规范
     */
    private Boolean advance;

    /**
     * 是否符合时长
     */
    private Boolean conform;

    /**
     * 主送人
     */
    private String main;

    /**
     * 抄送人
     */
    private String carbon;

    /**
     * 审核意见
     */
    private String advice;

    /**
     * 请假类型
     */
    private VacateType vacateType;

    /**
     * 审核状态
     */
    private AduitStatus aduitStatus;
    /**
     * 工作交接内容
     */
    private String handoff;

    /**
     * 子表
     */
    private List<VacateAuditBO> sons;
    /**
     * 照片下载地址
     */
    private List<String> photos;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHandoff() {
        return handoff;
    }

    public void setHandoff(String handoff) {
        this.handoff = handoff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<VacateAuditBO> getSons() {
        return sons;
    }

    public void setSons(List<VacateAuditBO> sons) {
        this.sons = sons;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getAdvance() {
        return advance;
    }

    public void setAdvance(Boolean advance) {
        this.advance = advance;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}