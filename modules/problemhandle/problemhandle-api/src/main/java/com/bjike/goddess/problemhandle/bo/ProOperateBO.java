package com.bjike.goddess.problemhandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 问题权限配置业务传输对象list返回对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置业务传输对象list返回对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProOperateBO extends BaseBO {


    /**
     * 操作对象
     */
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}