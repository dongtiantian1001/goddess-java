package com.bjike.goddess.rentutilitiespay.vo;

import java.time.LocalDate;

/**
* 模板表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class StencilVO {
    /**
     * id
     */
    private String  id;
    /**
     * 通报对象
     */
    private String  notificationPeople;

    /**
     * 通报对象邮箱
     */
    private String  notificationObject;

    /**
     * 通报对象时间
     */
    private String notificationDate;

    /**
     * 通报内容
     */
    private String  content;

    /**
     * 通报模板
     */
    private String  stencil;

    /**
     * 例子
     */
    private String  example;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotificationPeople() {
        return notificationPeople;
    }

    public void setNotificationPeople(String notificationPeople) {
        this.notificationPeople = notificationPeople;
    }

    public String getNotificationObject() {
        return notificationObject;
    }

    public void setNotificationObject(String notificationObject) {
        this.notificationObject = notificationObject;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStencil() {
        return stencil;
    }

    public void setStencil(String stencil) {
        this.stencil = stencil;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}