package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 调动经手人日汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:53 ]
 * @Description: [ 调动经手人日汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_transferhandlerdaysum")
public class TransferHandlerDaySum extends BaseEntity {

    /**
     * 汇总日期
     */
    @Column(name = "sumDate", nullable = false, columnDefinition = "DATE COMMENT '汇总日期'")
    private LocalDate sumDate;

    /**
     * 经手人
     */
    @Column(name = "handler", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '经手人'")
    private String handler;

    /**
     * 物资类型
     */
    @Column(name = "deviceType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资类型'")
    private String deviceType;

    /**
     * 物资名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资名称'")
    private String materialName;

    /**
     * 型号
     */
    @Column(name = "model", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '型号'")
    private String model;

    /**
     * 调入地区总数量
     */
    @Column(name = "transferredAreaTotalQty", nullable = false, columnDefinition = "INT(11) COMMENT '调入地区总数量'")
    private Integer transferredAreaTotalQty;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT '0' COMMENT '状态'")
    private Status status;


    public LocalDate getSumDate() {
        return sumDate;
    }

    public void setSumDate(LocalDate sumDate) {
        this.sumDate = sumDate;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getTransferredAreaTotalQty() {
        return transferredAreaTotalQty;
    }

    public void setTransferredAreaTotalQty(Integer transferredAreaTotalQty) {
        this.transferredAreaTotalQty = transferredAreaTotalQty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}