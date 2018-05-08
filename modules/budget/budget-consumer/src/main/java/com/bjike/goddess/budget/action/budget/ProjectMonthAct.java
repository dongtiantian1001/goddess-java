package com.bjike.goddess.budget.action.budget;

import com.bjike.goddess.budget.api.ProjectMonthAPI;
import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.vo.ProjectMonthCountVO;
import com.bjike.goddess.budget.vo.ProjectMonthVO;
import com.bjike.goddess.budget.vo.ProjectWeekListVO;
import com.bjike.goddess.budget.vo.ProjectWeekVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目收入月
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmonth")
public class ProjectMonthAct {
    @Autowired
    private ProjectMonthAPI projectMonthAPI;

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

            Boolean isHasPermission = projectMonthAPI.guidePermission(guidePermissionTO);
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
     * 查找
     *
     * @param dto     项目收入月分页信息
     * @param request 请求对象
     * @return class ProjectMonthVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectMonthDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMonthBO> list = projectMonthAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMonthVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目收入周分页列表
     *
     * @param dto     项目收入周分页信息
     * @param request 请求对象
     * @return class ProjectWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listProjectMonth")
    public Result listProjectMonth(ProjectMonthDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectWeekListBO> list = projectMonthAPI.listProjectMonth(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectWeekListVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param request 请求对象
     * @return class ProjectMonthCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HttpServletRequest request) throws ActException {
        try {
            List<ProjectMonthCountBO> list = projectMonthAPI.count();
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMonthCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 按项目汇总
     *
     * @param dto 项目收入月dto
     * @return class ProjectMonthCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/conditionsCount")
    public Result conditionsCount(ProjectMonthDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMonthCountBO> list = projectMonthAPI.conditionsCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMonthCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询该月明细
     *
     * @param id      项目收入月id
     * @param request 请求对象
     * @return class ProjectWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<ProjectWeekBO> list = projectMonthAPI.findDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(ProjectMonthDTO dto) throws ActException {
        try {
            Long num = projectMonthAPI.countNum(dto);
            return ActResult.initialize(num);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有项目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllProjects")
    public Result findAllProjects() throws ActException {
        try {
            List<String> list = projectMonthAPI.findAllProjects();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 按条件汇总
     *
     * @return class ProjectMonthCountVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(ProjectMonthDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMonthCountBO> list = projectMonthAPI.collect(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMonthCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目收入月图形化
     *
     * @return class OptionBO
     * @version v1
     */
    @GetMapping("v1/figureShow")
    public Result figureShow() throws ActException {
        try {
            OptionBO bo = projectMonthAPI.figureShow();
//            return ActResult.initialize(BeanTransform.copyProperties(bo, OptionVO.class));
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}