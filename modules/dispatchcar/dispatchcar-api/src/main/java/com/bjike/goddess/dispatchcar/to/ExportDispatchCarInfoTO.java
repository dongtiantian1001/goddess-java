package com.bjike.goddess.dispatchcar.to;

import java.io.Serializable;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-26 15:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportDispatchCarInfoTO implements Serializable{
    /**
     * 地区
     */
    private String area;

    /**
     * 导出开始时间
     */
    private String startTime;

    /**
     * 导出结束时间
     */
    private String endTime;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
}
