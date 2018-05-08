package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.OptionBO;
import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.bo.ProjectWeekCountBO;
import com.bjike.goddess.budget.bo.ProjectWeekListBO;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.service.ProjectWeekSer;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目收入周业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectWeekApiImpl")
public class ProjectWeekApiImpl implements ProjectWeekAPI {
    @Autowired
    private ProjectWeekSer projectWeekSer;

    @Override
    public ProjectWeekBO save(ProjectWeekTO to) throws SerException {
        return projectWeekSer.save(to);
    }

    @Override
    public void edit(ProjectWeekTO to) throws SerException {
        projectWeekSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectWeekSer.delete(id);
    }

    @Override
    public List<ProjectWeekBO> list(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.list(dto);
    }

    @Override
    public ProjectWeekBO findByID(String id) throws SerException {
        return projectWeekSer.findByID(id);
    }

    @Override
    public List<ProjectWeekCountBO> count() throws SerException {
        return projectWeekSer.count();
    }

    @Override
    public List<ProjectWeekListBO> listProject(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.listProject(dto);
    }

    @Override
    public List<ProjectWeekCountBO> conditionsCount(ProjectWeekDTO dto1) throws SerException {
        return projectWeekSer.conditionsCount(dto1);
    }

    @Override
    public Long countNum(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.countNum(dto);
    }

    @Override
    public List<String> findAllProjects() throws SerException {
        return projectWeekSer.findAllProjects();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return projectWeekSer.templateExport();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectWeekSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectWeekSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ProjectWeekCountBO> collect(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.collect(dto);
    }

    @Override
    public List<String> findArea() throws SerException {
        return projectWeekSer.findAreas();
    }

    @Override
    public OptionBO figureShow() throws SerException {
        return projectWeekSer.figureShow();
    }
}