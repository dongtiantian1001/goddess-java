package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.SellscheduleBO;
import com.bjike.goddess.staffshares.bo.SellscheduleCollectBO;
import com.bjike.goddess.staffshares.bo.TransactionBO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;

import java.util.List;

/**
 * 出售记录表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SellscheduleAPI {

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
     * 出售
     *
     * @param to
     * @throws SerException
     */
    default void sell(SellscheduleTO to) throws SerException {
    }

    /**
     * 出售记录表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<SellscheduleBO> maps(SellscheduleDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取出售记录表数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default SellscheduleBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param sellscheduleDTO
     * @return
     * @throws SerException
     */
    default Long getTotal(SellscheduleDTO sellscheduleDTO) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return
     * @throws SerException
     */
    default List<SellscheduleCollectBO> collect() throws SerException {
        return null;
    }

    /**
     * 交易汇总表
     *
     * @return
     * @throws SerException
     */
    default List<TransactionBO> transaction() throws SerException {
        return null;
    }
}