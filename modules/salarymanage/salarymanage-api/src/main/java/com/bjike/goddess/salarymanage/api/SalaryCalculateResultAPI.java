package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.ResultAreaBO;
import com.bjike.goddess.salarymanage.bo.SalaryCalculateResultBO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateResultTO;

import java.util.List;
import java.util.Set;

/**
* 薪资测算结果业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryCalculateResultAPI  {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 列表
     */
    List<ResultAreaBO> pageList() throws SerException;

    /**
     * 制定等级份额
     */
    void makeShare(SalaryCalculateResultTO to) throws SerException;

    /**
     * 根据id来查询单个薪资测算结果
     *
     * @param id
     * @throws SerException
     */
    SalaryCalculateResultBO findOne(String id) throws SerException;


}