package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 技能等级晋升管理汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 18:41]
 * @Description: [ 技能等级晋升管理汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillLevelCollectTO extends BaseTO {
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

    /**
     * 季度
     */
    private Integer quart;

    public Integer getQuart() {
        return quart;
    }

    public void setQuart(Integer quart) {
        this.quart = quart;
    }

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
