package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
* 薪资管理
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryBasicTO extends BaseTO {


    public interface TestAdd{

    }
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空!", groups = {ADD.class, EDIT.class})
    private String  area;

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空!", groups = {ADD.class, EDIT.class})
    private String  system;

    /**
     * 部门/项目组
     */
    @NotBlank(message = "部门/项目组不能为空!", groups = {ADD.class, EDIT.class})
    private String  department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空!", groups = {ADD.class, EDIT.class})
    private String  position;

    /**
     * 基本工资
     */
    @NotBlank(message = "基本工资不能为空!", groups = {ADD.class, EDIT.class})
    private String  basePay;



    public String getArea () {
        return area;
    }
    public void setArea (String area ) {
        this.area = area ;
    }
    public String getSystem () {
        return system;
    }
    public void setSystem (String system ) {
        this.system = system ;
    }
    public String getDepartment () {
        return department;
    }
    public void setDepartment (String department ) {
        this.department = department ;
    }
    public String getPosition () {
        return position;
    }
    public void setPosition (String position ) {
        this.position = position ;
    }
    public String getBasePay () {
        return basePay;
    }
    public void setBasePay (String basePay ) {
        this.basePay = basePay ;
    }
}