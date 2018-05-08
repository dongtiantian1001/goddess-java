package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;

import java.util.List;

/**
 * Created by haikuang on 17-8-17.
 */
public interface StaffBirthDayAPI {

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
     * 根据月份来查询员工生日信息
     * @param dto
     * @throws SerException
     */
    List<StaffBirthDayBO> findBirthDay(StaffBirthDayDTO dto) throws SerException;


    /**
     * 列表总条数
     */
    Long count(StaffBirthDayDTO dto) throws SerException;
}
