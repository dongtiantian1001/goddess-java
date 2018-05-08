package com.bjike.goddess.bidding.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

/**
 * 投标答疑问题记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.896 ]
 * @Description: [ 投标答疑问题记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAnswerQuestionsTO extends BaseTO {
    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空",groups = {ADD.class, EDIT.class})
    private String biddingNumber;
    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 答疑准备人
     */
    @NotBlank(message = "答疑准备人不能为空",groups = {ADD.class, EDIT.class})
    private String peopleAnswerQuestions;

    /**
     * 答疑时间
     */
    @NotBlank(message = "答疑时间不能为空",groups = {ADD.class, EDIT.class})
    private String officeHour;

    /**
     * 解答单位
     */
    @NotBlank(message = "解答单位不能为空",groups = {ADD.class, EDIT.class})
    private String answersUnit;

    /**
     * 问题
     */
    @NotBlank(message = "问题不能为空",groups = {ADD.class, EDIT.class})
    private String problem;

    /**
     * 回复
     */
    @NotBlank(message = "回复不能为空",groups = {ADD.class, EDIT.class})
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

    public String getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(String officeHour) {
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