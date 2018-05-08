package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmeasure.bo.ProjectBasicInfoBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectBasicInfo;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;

import java.util.List;

/**
 * 项目基本信息业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectBasicInfoSer extends Ser<ProjectBasicInfo, ProjectBasicInfoDTO> {

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
     * 分页查询项目基本信息
     *
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    List<ProjectBasicInfoBO> list(ProjectBasicInfoDTO dto) throws SerException;

    /**
     * 保存项目基本信息
     *
     * @param to 项目基本信息to
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    ProjectBasicInfoBO save(ProjectBasicInfoTO to) throws SerException;

    /**
     * 根据id删除项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新项目基本信息
     *
     * @param to 项目基本信息to
     * @throws SerException
     */
    void update(ProjectBasicInfoTO to) throws SerException;

    /**
     * 查询所有的项目名称
     *
     * @return
     * @throws SerException
     */
    List<String> findAllProjectNames() throws SerException;

    /**
     * 查询所有的地址
     *
     * @return list
     */
    default List<String> findAllAreas() throws SerException{
        return null;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return ProjectBasicInfo对象
     */
    default ProjectBasicInfoBO getOne(String id) throws SerException{
        return null;
    }

}