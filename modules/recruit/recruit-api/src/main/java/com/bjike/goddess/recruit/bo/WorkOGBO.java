package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * 工作对赌业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkOGBO extends BaseBO {

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String quarters;

    /**
     * 模块
     */
    private String modular;

    /**
     * 评分对象
     */
    private String scoringOB;

    /**
     * 评分者
     */
    private String raters;

    /**
     * 项目流程
     */
    private String projectPRO;

    /**
     * 节点内容
     */
    private String nodeCON;

    /**
     * 时间
     */
    private String time;

    /**
     * 对赌总比例
     */
    private String proportionOG;

    /**
     * 各标签对赌比例
     */
    private String labelPRO;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 工作指标
     */
    private String workIndex;

    /**
     * 各标签评分
     */
    private Integer labelSCO;

    /**
     * 得分
     */
    private String score;

    private List<AlertIndexBO> alertIndexBOS;

    /**
     * 状态
     */
    private String state;

    /**
     * 接受时间
     */
    private String acceptTime;

    /**
     * 评分内容
     */
    private String scoreContent;

    /**
     * 对赌内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<AlertIndexBO> getAlertIndexBOS() {
        return alertIndexBOS;
    }

    public void setAlertIndexBOS(List<AlertIndexBO> alertIndexBOS) {
        this.alertIndexBOS = alertIndexBOS;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getScoringOB() {
        return scoringOB;
    }

    public void setScoringOB(String scoringOB) {
        this.scoringOB = scoringOB;
    }

    public String getRaters() {
        return raters;
    }

    public void setRaters(String raters) {
        this.raters = raters;
    }

    public String getProjectPRO() {
        return projectPRO;
    }

    public void setProjectPRO(String projectPRO) {
        this.projectPRO = projectPRO;
    }

    public String getNodeCON() {
        return nodeCON;
    }

    public void setNodeCON(String nodeCON) {
        this.nodeCON = nodeCON;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProportionOG() {
        return proportionOG;
    }

    public void setProportionOG(String proportionOG) {
        this.proportionOG = proportionOG;
    }

    public String getLabelPRO() {
        return labelPRO;
    }

    public void setLabelPRO(String labelPRO) {
        this.labelPRO = labelPRO;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getWorkIndex() {
        return workIndex;
    }

    public void setWorkIndex(String workIndex) {
        this.workIndex = workIndex;
    }

    public Integer getLabelSCO() {
        return labelSCO;
    }

    public void setLabelSCO(Integer labelSCO) {
        this.labelSCO = labelSCO;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "WorkOGBO{" +
                "department='" + department + '\'' +
                ", quarters='" + quarters + '\'' +
                ", modular='" + modular + '\'' +
                ", scoringOB='" + scoringOB + '\'' +
                ", raters='" + raters + '\'' +
                ", projectPRO='" + projectPRO + '\'' +
                ", nodeCON='" + nodeCON + '\'' +
                ", time='" + time + '\'' +
                ", proportionOG='" + proportionOG + '\'' +
                ", labelPRO='" + labelPRO + '\'' +
                ", indexType='" + indexType + '\'' +
                ", workIndex='" + workIndex + '\'' +
                ", labelSCO=" + labelSCO +
                ", score='" + score + '\'' +
                ", alertIndexBOS=" + alertIndexBOS +
                '}';
    }
}