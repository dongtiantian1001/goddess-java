package com.bjike.goddess.event.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.enums.Status;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * 事件业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EventBO extends BaseBO {

    /**
     * 事件内容
     */
    private String content;

    /**
     * 事件获取时间
     */
    private String getTime;

    /**
     * 要求处理时间
     */
    private String requestTime;

    /**
     * 处理人
     */
    private String name;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String depart;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 岗位名称
     */
    private String position;

    /**
     * 权限
     */
    private Permissions permissions;

    /**
     * 优先级
     */
    private Integer level;

    /**
     * 提醒间隔时间
     */
    private String remindTime;

    /**
     * 实际处理完成时间
     */
    private String actualTime;

    /**
     * 事件处理状态
     */
    private EventStatus eventStatus;

    /**
     * 颜色
     */
    private String color;

    /**
     * 待办事件对应状态
     */
    private String status;


    /**
     * 2017-12-28新增字段
     *
     * 任务开始时间
     */
    private String startTime;
    /**
     * 2017-12-28新增字段
     * 任务结束时间
     */
    private String endTime;

    /**
     * 是否重复
     *2017-12-30新增字段
     */
    private Status eStatus;

    public Status geteStatus() {
        return eStatus;
    }

    public void seteStatus(Status eStatus) {
        this.eStatus = eStatus;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}