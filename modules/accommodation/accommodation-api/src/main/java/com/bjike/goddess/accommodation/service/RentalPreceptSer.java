package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.entity.RentalPrecept;
import com.bjike.goddess.accommodation.excel.SonPermissionObject;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房方案接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RentalPreceptSer extends Ser<RentalPrecept, RentalPreceptDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 租房方案列表总条数
     */
    default Long countRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        return null;
    }

    /**
     * 一个租房方案
     *
     * @return class RentalPreceptBO
     */
    default RentalPreceptBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 租房方案列表
     *
     * @param rentalPreceptDTO 租房方案dto
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default List<RentalPreceptBO> findListRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        return null;
    }

    /**
     * 添加租房方案
     *
     * @param preceptTO 租房方案数据集合
     * @throws SerException
     */
    default RentalPreceptBO insertPecept(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 编辑租房方案
     *
     * @param preceptTO 租房方案信息数据to
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO editPecept(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }


    /**
     * 根据id删除租房方案
     *
     * @param id
     * @throws SerException
     */
    default void removePecept(String id) throws SerException {
        return;
    }


    /**
     * 商务发展部
     *
     * @param preceptTO
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO businessAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 运营财务部
     *
     * @param preceptTO
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO financeAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 综合资源部
     *
     * @param preceptTO
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO resourceAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 项目经理审核
     *
     * @param preceptTO
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO manageAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 总经办审核
     *
     * @param preceptTO
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO generalAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }

    /**
     * 获取租房编号
     *
     * @return class String
     * @throws SerException
     */
    default List<String> getNum() throws SerException {
        return null;
    }

    /**
     * 根据编号获取租房方案
     *
     * @return class RentalPreceptBO
     * @throws SerException
     */
    default RentalPreceptBO getRent(String rentNum) throws SerException {
        return null;
    }

    /**
     * 根据所有用户
     *
     * @return class UserBO
     * @throws SerException
     */
    default List<UserBO> getUser() throws SerException {
        return null;
    }


}