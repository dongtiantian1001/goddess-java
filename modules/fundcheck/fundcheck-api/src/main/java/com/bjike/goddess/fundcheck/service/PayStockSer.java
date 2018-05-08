package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.bo.PayStockBO;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.entity.PayStock;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import com.bjike.goddess.fundcheck.to.PayStockCollectTO;
import com.bjike.goddess.fundcheck.to.PayStockTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付给股东业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayStockSer extends Ser<PayStock, PayStockDTO> {
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
     * 支付给股东列表总条数
     */
    default Long count(PayStockDTO payStockDTO) throws SerException {
        return null;
    }

    /**
     * 一个支付给股东
     *
     * @return class PayStockBO
     */
    default PayStockBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 支付给股东
     *
     * @param payStockDTO 支付给股东dto
     * @return class PayStockBO
     * @throws SerException
     */
    default List<PayStockBO> findListBack(PayStockDTO payStockDTO) throws SerException {
        return null;
    }

    /**
     * 添加支付给股东
     *
     * @param payStockTO 支付给股东数据to
     * @return class PayStockBO
     * @throws SerException
     */
    default PayStockBO insert(PayStockTO payStockTO) throws SerException {
        return null;
    }

    /**
     * 编辑支付给股东
     *
     * @param payStockTO 支付给股东数据to
     * @return class PayStockBO
     * @throws SerException
     */
    default PayStockBO edit(PayStockTO payStockTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除支付给股东
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to
     * @throws SerException
     */
    default LinkedHashMap<String, String> collect(PayStockCollectTO to) throws SerException {
        return null;
    }
    /**
     * 查询所有一级科目
     *
     * @return String
     * @throws SerException
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目查询二级科目
     *
     * @param firstSub 一级科目
     * @return String
     * @throws SerException
     */
    default List<String> listSubByFirst(String firstSub) throws SerException {
        return null;
    }

    /**
     * 根据一级二级查询三级科目
     *
     * @param firstSub  一级科目
     * @param secondSub 二级科目
     * @return String
     * @throws SerException
     */
    default List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return null;
    }
    /**
     * 导入
     *
     * @param payStockTOS 支付给股东
     * @return class PayStockBO
     */
    default PayStockBO importExcel(List<PayStockTO> payStockTOS) throws SerException {
        return null;
    }
    /**
     * 导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;
}