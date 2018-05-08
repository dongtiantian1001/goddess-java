package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;

import java.util.List;

/**
 * 投资形式业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:28 ]
 * @Description: [ 投资形式业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvestFormAPI {
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
     * 投资形式列表总条数
     */
    default Long countInvestForm(InvestFormDTO investFormDTO) throws SerException {
        return null;
    }

    /**
     * 一个投资形式
     *
     * @return class InvestFormBO
     */
    default InvestFormBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 投资形式
     *
     * @param investFormDTO 投资形式dto
     * @return class InvestFormBO
     * @throws SerException
     */
    default List<InvestFormBO> findListInvestForm(InvestFormDTO investFormDTO) throws SerException {
        return null;
    }

    /**
     * 添加投资形式
     *
     * @param investFormTO 投资形式数据to
     * @return class InvestFormBO
     * @throws SerException
     */
    default InvestFormBO insertInvestForm(InvestFormTO investFormTO) throws SerException {
        return null;
    }

    /**
     * 编辑投资形式
     *
     * @param investFormTO 投资形式数据to
     * @return class InvestFormBO
     * @throws SerException
     */
    default InvestFormBO editInvestForm(InvestFormTO investFormTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除投资形式
     *
     * @param id
     * @throws SerException
     */
    default void removeInvestForm(String id) throws SerException {

    }

}