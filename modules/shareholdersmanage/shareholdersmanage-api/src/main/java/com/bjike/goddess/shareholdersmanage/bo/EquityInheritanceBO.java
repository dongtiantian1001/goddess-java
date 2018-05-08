package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 股权继承业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityInheritanceBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 继承人
     */
    private String heir;

    /**
     * 被继承人
     */
    private String beHeir;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 继承日期
     */
    private String heirDate;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getBeHeir() {
        return beHeir;
    }

    public void setBeHeir(String beHeir) {
        this.beHeir = beHeir;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getHeirDate() {
        return heirDate;
    }

    public void setHeirDate(String heirDate) {
        this.heirDate = heirDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}