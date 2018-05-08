package com.bjike.goddess.buyticket.vo;

/**
 * 基本信息设置表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BasicInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 购票原因
     */
    private String ticketCause;

    /**
     * 车票类型
     */
    private String ticketType;

    /**
     * 购买方式
     */
    private String buyPattern;

    /**
     * 汇总类型
     */
    private String summaryType;

    /**
     * 汇总周期
     */
    private String summaryCycle;

    /**
     * 数据汇总呈现类型
     */
    private String dataAggregationType;

    /**
     * 查询权限
     */
    private String queryPermission;

    /**
     * 删除权限
     */
    private String deletePermission;

    /**
     * 修改权限
     */
    private String updatePermission;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketCause() {
        return ticketCause;
    }

    public void setTicketCause(String ticketCause) {
        this.ticketCause = ticketCause;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getBuyPattern() {
        return buyPattern;
    }

    public void setBuyPattern(String buyPattern) {
        this.buyPattern = buyPattern;
    }

    public String getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(String summaryType) {
        this.summaryType = summaryType;
    }

    public String getSummaryCycle() {
        return summaryCycle;
    }

    public void setSummaryCycle(String summaryCycle) {
        this.summaryCycle = summaryCycle;
    }

    public String getDataAggregationType() {
        return dataAggregationType;
    }

    public void setDataAggregationType(String dataAggregationType) {
        this.dataAggregationType = dataAggregationType;
    }

    public String getQueryPermission() {
        return queryPermission;
    }

    public void setQueryPermission(String queryPermission) {
        this.queryPermission = queryPermission;
    }

    public String getDeletePermission() {
        return deletePermission;
    }

    public void setDeletePermission(String deletePermission) {
        this.deletePermission = deletePermission;
    }

    public String getUpdatePermission() {
        return updatePermission;
    }

    public void setUpdatePermission(String updatePermission) {
        this.updatePermission = updatePermission;
    }
}