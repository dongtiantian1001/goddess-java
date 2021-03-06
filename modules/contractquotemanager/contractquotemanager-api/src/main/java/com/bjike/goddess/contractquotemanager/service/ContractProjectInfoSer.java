package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)业务接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: []
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractProjectInfoSer extends Ser<ContractProjectInfo, ContractProjectInfoDTO> {

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
     * 添加合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @return class ContractProjectInfoBO
     * @throws SerException
     */
    default ContractProjectInfoBO save(ContractProjectInfoTO to) throws SerException {
        return null;
    }

    /**
     * 分页合同项目基本信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ContractProjectInfoBO> list(ContractProjectInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @throws SerException
     */
    default void update(ContractProjectInfoTO to) throws SerException {

    }

    /**
     * 根据id删除合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 查看所有的地区
     *
     * @throws SerException
     */
    List<String> findAllAreas() throws SerException;

    /**
     * 导出excel
     *
     * @param contractProjectInfoDTO
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ContractProjectInfoDTO contractProjectInfoDTO) throws SerException;


    /**
     *  导入
     * @param contractProjectInfoTOS
     */
    void importExcel(List<ContractProjectInfoTO> contractProjectInfoTOS) throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

}