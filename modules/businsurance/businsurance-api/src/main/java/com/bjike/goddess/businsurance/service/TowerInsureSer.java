package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.excel.SonPermissionObject;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businsurance.entity.TowerInsure;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;

import java.util.List;

/**
 * 塔工意外险信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TowerInsureSer extends Ser<TowerInsure, TowerInsureDTO> {

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
     * 塔工意外险信息列表总条数
     *
     */
    default Long countTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        return null;
    }
    /**
     * 塔工意外险信息列表
     * @return class TowerInsureBO
     */
    default List<TowerInsureBO> listTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {return null;}
    /**
     *  添加
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO addTowerInsure(TowerInsureTO towerInsureTO) throws SerException { return null;}

    /**
     *  编辑
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editTowerInsure(TowerInsureTO towerInsureTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteTowerInsure(String id ) throws SerException {return;};

    /**
     *  编辑转账授权信息
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editAccount(TowerInsureTO towerInsureTO) throws SerException { return null;}

    /**
     *  编辑要约内容
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editContext(TowerInsureTO towerInsureTO) throws SerException { return null;}
    /**
     *  编辑投保人基本信息
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO applicant(TowerInsureTO towerInsureTO) throws SerException { return null;}
    /**
     *  编辑被投保人基本信息
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editInsurePerson(TowerInsureTO towerInsureTO) throws SerException { return null;}
    /**
     *  编辑受益人基本信息
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editBenefit(TowerInsureTO towerInsureTO) throws SerException { return null;}
    /**
     *  编辑销售机构信息
     * @param towerInsureTO 塔工意外险信息信息
     * @return class TowerInsureBO
     */
    default TowerInsureBO editSaleOrgan(TowerInsureTO towerInsureTO) throws SerException { return null;}
    /**
     * 查看明细
     * @return class TowerInsureBO
     */
    default TowerInsureBO getTowerInsure(String id ) throws SerException {return null;}



}