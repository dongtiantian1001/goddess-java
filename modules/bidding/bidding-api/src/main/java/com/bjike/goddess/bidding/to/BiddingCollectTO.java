package com.bjike.goddess.bidding.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 招投标流程进度管理汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 17:00]
 * @Description: [招投标流程进度管理汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BiddingCollectTO extends BaseTO {
    /**
     * 时间
     */
    private String time;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 第几周
     */
    private Integer week;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
