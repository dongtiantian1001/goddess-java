package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 投标答疑问题记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.883 ]
 * @Description: [ 投标答疑问题记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_biddinganswerquestions")
public class BiddingAnswerQuestions extends BaseEntity {
    /**
     * 编号
     */
    @Column(name = "biddingNumber", columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String biddingNumber;
    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 答疑准备人
     */
    @Column(name = "peopleAnswerQuestions", columnDefinition = "VARCHAR(255)   COMMENT '答疑准备人'")
    private String peopleAnswerQuestions;

    /**
     * 答疑时间
     */
    @Column(name = "officeHour", columnDefinition = "DATE   COMMENT '答疑时间'")
    private LocalDate officeHour;

    /**
     * 解答单位
     */
    @Column(name = "answersUnit", columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String answersUnit;

    /**
     * 问题
     */
    @Column(name = "problem", columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String problem;

    /**
     * 回复
     */
    @Column(name = "reply", columnDefinition = "VARCHAR(255)   COMMENT '回复'")
    private String reply;

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPeopleAnswerQuestions() {
        return peopleAnswerQuestions;
    }

    public void setPeopleAnswerQuestions(String peopleAnswerQuestions) {
        this.peopleAnswerQuestions = peopleAnswerQuestions;
    }

    public LocalDate getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(LocalDate officeHour) {
        this.officeHour = officeHour;
    }

    public String getAnswersUnit() {
        return answersUnit;
    }

    public void setAnswersUnit(String answersUnit) {
        this.answersUnit = answersUnit;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}