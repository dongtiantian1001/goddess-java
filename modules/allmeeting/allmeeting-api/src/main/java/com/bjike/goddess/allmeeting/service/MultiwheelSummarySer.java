package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MultiwheelSummaryBO;
import com.bjike.goddess.allmeeting.bo.OrganizeForSummaryBO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.entity.MultiwheelSummary;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;

import java.util.List;

/**
* 简洁交流讨论纪要业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-01 10:44 ]
* @Description:	[ 简洁交流讨论纪要业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MultiwheelSummarySer extends Ser<MultiwheelSummary, MultiwheelSummaryDTO> {
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

    MultiwheelSummaryBO updateModel(MultiwheelSummaryTO to) throws SerException;

    void freeze(String id) throws SerException;

    List<MultiwheelSummaryBO> pageList(MultiwheelSummaryDTO dto) throws SerException;

    OrganizeForSummaryBO organize(String id) throws SerException;

    void unfreeze(String id) throws SerException;
}