package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.CustomerCollectUnit;
import com.bjike.goddess.customer.enums.CustomerSendUnit;

import java.util.List;
import java.util.Map;

/**
 * 客户邮件发送定制表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.880 ]
 * @Description: [ 客户邮件发送定制表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusEmailVO {

    /**
     * id
     */
    private String id;
    /**
     * 行业
     */
    private String work;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    private CustomerSendUnit customerSendUnit;

    /**
     * 汇总间隔
     */
    private CustomerCollectUnit customerCollectUnit;

    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 发送对象数组
     */
    private List<String> sendObjectList;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 创建人
     */
    private String createPersion;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    /**
     * 个数
     */
    private Integer counts;

    /**
     * 数据库枚举转换
     */
    private int enumConvert;

    /**
     * 行业地区汇总集合
     */
    private Object areaMap;

    /**
     * 行业客户级别汇总集合
     */
    private Object levelMap;

    /**
     * 行业客户类别汇总集合
     */
    private Object cusTypeMap;

    /**
     * 行业客户状态汇总集合
     */
    private Object cusStatusMap;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSendNum() {
        return sendNum;
    }

    public void setSendNum(Double sendNum) {
        this.sendNum = sendNum;
    }

    public String getSendNumAndUnit() {
        return sendNumAndUnit;
    }

    public void setSendNumAndUnit(String sendNumAndUnit) {
        this.sendNumAndUnit = sendNumAndUnit;
    }

    public CustomerSendUnit getCustomerSendUnit() {
        return customerSendUnit;
    }

    public void setCustomerSendUnit(CustomerSendUnit customerSendUnit) {
        this.customerSendUnit = customerSendUnit;
    }

    public CustomerCollectUnit getCustomerCollectUnit() {
        return customerCollectUnit;
    }

    public void setCustomerCollectUnit(CustomerCollectUnit customerCollectUnit) {
        this.customerCollectUnit = customerCollectUnit;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public List<String> getSendObjectList() {
        return sendObjectList;
    }

    public void setSendObjectList(List<String> sendObjectList) {
        this.sendObjectList = sendObjectList;
    }

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public Object getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(Object areaMap) {
        this.areaMap = areaMap;
    }

    public Object getLevelMap() {
        return levelMap;
    }

    public void setLevelMap(Object levelMap) {
        this.levelMap = levelMap;
    }

    public Object getCusTypeMap() {
        return cusTypeMap;
    }

    public void setCusTypeMap(Object cusTypeMap) {
        this.cusTypeMap = cusTypeMap;
    }

    public Object getCusStatusMap() {
        return cusStatusMap;
    }

    public void setCusStatusMap(Object cusStatusMap) {
        this.cusStatusMap = cusStatusMap;
    }
}