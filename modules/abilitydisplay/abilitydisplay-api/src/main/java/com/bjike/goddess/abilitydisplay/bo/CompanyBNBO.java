package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.abilitydisplay.entity.ComProject;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公司业务业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:15 ]
 * @Description: [ 公司业务业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyBNBO extends BaseBO {

    /**
     * 公司核心业务介绍
     */
    private String introduction;

    /**
     * 公司业务类型
     */
    private String type;

    /**
     * 公司参与项目
     */
    private ComProjectBO projectBO;


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ComProjectBO getProjectBO() {
        return projectBO;
    }

    public void setProjectBO(ComProjectBO projectBO) {
        this.projectBO = projectBO;
    }

    @Override
    public String toString() {
        return "CompanyBNBO{" +
                "introduction='" + introduction + '\'' +
                ", type='" + type + '\'' +
                ", projectBO=" + projectBO +
                '}';
    }
}