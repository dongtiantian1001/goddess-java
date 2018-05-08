package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.ProjectBasicInfoAPI;
import com.bjike.goddess.projectmeasure.api.ProjectMeasureSummaryAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import com.bjike.goddess.projectmeasure.vo.ProjectMeasureSummaryVO;
import com.bjike.goddess.projectmeasure.vo.ProjectMeasureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目测算邮件发送
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasuresummary")
public class ProjectMeasureSummaryAct {

    @Autowired
    private ProjectMeasureSummaryAPI projectMeasureSummaryAPI;

    @Autowired
    private ProjectBasicInfoAPI projectBasicInfoAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = projectMeasureSummaryAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/projectmeasuresummary/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            ProjectMeasureSummaryBO bo = projectMeasureSummaryAPI.getOne(id);
            ProjectMeasureSummaryVO vo = BeanTransform.copyProperties(bo, ProjectMeasureSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目测算邮件发送dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated ProjectMeasureSummaryDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectMeasureSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目测算邮件发送
     *
     * @param dto 项目测算邮箱发送传输对象
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectMeasureSummaryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMeasureSummaryBO> boList = projectMeasureSummaryAPI.list(dto);
            List<ProjectMeasureSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ProjectMeasureSummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectMeasureSummaryBO bo = projectMeasureSummaryAPI.save(to);
            ProjectMeasureSummaryVO vo = BeanTransform.copyProperties(bo, ProjectMeasureSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ProjectMeasureSummaryTO to, BindingResult result) throws ActException {
        try {
            projectMeasureSummaryAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.thaw(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.congeal(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目测算汇总
     *
     * @param projectMeasureSummaryTO 汇总地区参数
     * @return class ProjectMeasureVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize")
    public Result summarize(@Validated({ProjectMeasureSummaryTO.TestCollect.class}) ProjectMeasureSummaryTO projectMeasureSummaryTO , BindingResult bindingResult) throws ActException {
        try {
            if( projectMeasureSummaryTO.getAreas() == null || projectMeasureSummaryTO.getAreas().length<=0 ){
                throw new ActException("地区数组不能为空");
            }
            String[] areas = projectMeasureSummaryTO.getAreas();
            List<ProjectMeasureBO> boList = projectMeasureSummaryAPI.summarize(areas);
            List<ProjectMeasureVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureVO.class );
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有的地址
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAreas")
    public Result findAreas() throws ActException {
        try {
            List<String> areas = projectBasicInfoAPI.findAllAreas();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 项目测算邮箱汇总发送检查
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail() throws ActException {
        try {
            projectMeasureSummaryAPI.checkSendEmail();
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}