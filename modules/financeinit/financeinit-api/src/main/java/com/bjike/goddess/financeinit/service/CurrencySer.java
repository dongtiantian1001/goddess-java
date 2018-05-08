package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

import java.util.List;

/**
 * 设置币别业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CurrencySer extends Ser<Currency, CurrencyDTO> {
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
     * 设置币别列表总条数
     */
    default Long countCurren(CurrencyDTO currencyDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取设置币别
     *
     * @return class CurrencyBO
     */
    default CurrencyBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 设置币别列表
     *
     * @return class CurrencyBO
     */
    default List<CurrencyBO> listCurren(CurrencyDTO currencyDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param currencyTO 设置币别
     * @return class CurrencyBO
     */
    default CurrencyBO addCurren(CurrencyTO currencyTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param currencyTO 设置币别
     * @return class CurrencyBO
     */
    default CurrencyBO editCurren(CurrencyTO currencyTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 设置币别id
     */
    default void deleteCurren(String id) throws SerException {
        return;
    }

}