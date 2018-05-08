package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.VisitScheduleBO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;

/**
 * 拜访日程表业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VisitScheduleAPI {
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
     *
     * @param visitScheduleDTO 拜访日程列表
     * @return
     * @throws SerException
     */
    default VisitScheduleBO findVisit(VisitScheduleDTO visitScheduleDTO) throws SerException {
        return null;
    }
}