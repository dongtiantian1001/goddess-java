package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.royalty.bo.SystemBetABO;
import com.bjike.goddess.royalty.dto.SystemBetADTO;
import com.bjike.goddess.royalty.dto.SystemBetDDTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.ProjectNameTO;
import com.bjike.goddess.royalty.to.SystemBetATO;

import java.util.List;

/**
 * 体系间对赌表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SystemBetAPI {
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
     * 体系间对赌表列表总条数
     */
    default Long count(SystemBetDDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个体系间对赌表
     *
     * @return class SystemBetABO
     */
    default SystemBetABO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 体系间对赌表
     *
     * @param dto 体系间对赌表数据dto
     * @return class SystemBetABO
     * @throws SerException
     */
    default List<SystemBetABO> list(SystemBetADTO dto) throws SerException {
        return null;
    }

    /**
     * 添加体系间对赌表
     *
     * @param systemBetATO 体系间对赌表数据to
     * @throws SerException
     */
    default void insert(SystemBetATO systemBetATO) throws SerException {
    }


    /**
     * 编辑体系间对赌表
     *
     * @param systemBetATO 体系间对赌表数据to
     * @throws SerException
     */
    default void edit(SystemBetATO systemBetATO) throws SerException {
    }

    /**
     * 根据id删除体系间对赌表
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 获取所有项目名称
     *
     * @throws SerException
     */
    default List<String> getProjectName() throws SerException {
        return null;
    }

    /**
     * 获取所有体系
     *
     * @throws SerException
     */
    default List<String> system() throws SerException {
        return null;
    }
    /**
     * 根据项目名称获取体系间对赌表
     *
     * @throws SerException
     */
    default SystemBetABO getSystem(String projectName) throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @param to to
     * @return class SystemBetABO
     * @throws SerException
     */
    default List<SystemBetABO> systemCollect(ProjectNameTO to) throws SerException {
        return null;
    }

}