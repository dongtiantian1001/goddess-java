package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.OptionBO;
import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.bo.ProjectWeekCountBO;
import com.bjike.goddess.budget.bo.ProjectWeekListBO;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目收入周业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectWeekAPI {
    /**
     * 添加
     *
     * @param to 项目收入周信息
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default ProjectWeekBO save(ProjectWeekTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 项目收入周信息
     * @throws SerException
     */
    default void edit(ProjectWeekTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 项目收入周id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 项目收入周分页信息
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default List<ProjectWeekBO> list(ProjectWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 项目收入周id
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default ProjectWeekBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return class ProjectWeekCountBO
     * @throws SerException
     */
    default List<ProjectWeekCountBO> count() throws SerException {
        return null;
    }

    /**
     * 项目收入周列表
     *
     * @param dto 项目收入周列表
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default List<ProjectWeekListBO> listProject(ProjectWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 分项目汇总
     *
     * @param dto1 dto
     * @return class ProjectWeekCountBO
     * @throws SerException
     */
    List<ProjectWeekCountBO> conditionsCount(ProjectWeekDTO dto1) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @return class Long
     * @throws SerException
     */
    default Long countNum(ProjectWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找所有项目
     *
     * @throws SerException
     */
    default List<String> findAllProjects() throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 按条件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ProjectWeekCountBO> collect(ProjectWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 汇总时获取地区
     *
     * @return
     * @throws SerException
     */
    default List<String> findArea() throws SerException {
        return null;
    }

    /**
     * 项目收入周图形化
     *
     * @return
     * @throws SerException
     */
    default OptionBO figureShow() throws SerException {
        return null;
    }
}