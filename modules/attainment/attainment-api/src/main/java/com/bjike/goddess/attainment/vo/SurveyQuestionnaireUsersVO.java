package com.bjike.goddess.attainment.vo;

import java.util.List;

/**
 * 问卷调查历史记录表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireUsersVO {

    /**
     * id
     */
    private String id;

    /**
     * 调研实施id
     */
    private String actualizeId;

    /**
     * 问卷
     */
    private String questionnaireName;


    /**
     * 用户
     */
    private String user;

    /**
     * 问题选项
     */
    private List<SurveyQuestionnaireOptionUserVO> surveyQuestionnaireOptionUserVOList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActualizeId() {
        return actualizeId;
    }

    public void setActualizeId(String actualizeId) {
        this.actualizeId = actualizeId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<SurveyQuestionnaireOptionUserVO> getSurveyQuestionnaireOptionUserVOList() {
        return surveyQuestionnaireOptionUserVOList;
    }

    public void setSurveyQuestionnaireOptionUserVOList(List<SurveyQuestionnaireOptionUserVO> surveyQuestionnaireOptionUserVOList) {
        this.surveyQuestionnaireOptionUserVOList = surveyQuestionnaireOptionUserVOList;
    }
}