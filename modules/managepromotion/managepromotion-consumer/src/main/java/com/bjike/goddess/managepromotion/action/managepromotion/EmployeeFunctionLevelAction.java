package com.bjike.goddess.managepromotion.action.managepromotion;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.vo.StaffRecordsVO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.EmployeeFunctionLevelAPI;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.vo.EmployeeFunctionLevelVO;
import com.bjike.goddess.managepromotion.vo.OverviewSkillLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工职能定级
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("employeefunctionlevel")
public class EmployeeFunctionLevelAction {
    @Autowired
    private EmployeeFunctionLevelAPI employeeFunctionLevelAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;

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

            Boolean isHasPermission = employeeFunctionLevelAPI.guidePermission(guidePermissionTO);
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
     * 员工职能定级列表总条数
     *
     * @param employeeFunctionLevelDTO 员工职能定级记录dto
     * @des 获取所有员工职能定级
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws ActException {
        try {
            Long count = employeeFunctionLevelAPI.countEmployeeFunctionLevel(employeeFunctionLevelDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个员工职能定级
     *
     * @param id
     * @return class EmployeeFunctionLevelVO
     * @des 获取一个员工职能定级
     * @version v1
     */
    @GetMapping("v1/emp/{id}")
    public Result emp(@PathVariable String id) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(employeeFunctionLevelBO, EmployeeFunctionLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 员工职能定级列表
     *
     * @param employeeFunctionLevelDTO 员工职能定级记录dto
     * @return class EmployeeFunctionLevelVO
     * @des 获取所有员工职能定级
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmployeeFunctionLevelDTO employeeFunctionLevelDTO, HttpServletRequest request) throws ActException {
        try {
            List<EmployeeFunctionLevelVO> employeeFunctionLevelVOS = BeanTransform.copyProperties(
                    employeeFunctionLevelAPI.findListEmployeeFunctionLevel(employeeFunctionLevelDTO), EmployeeFunctionLevelVO.class, request);
            return ActResult.initialize(employeeFunctionLevelVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级to
     * @return class EmployeeFunctionLevelVO
     * @des 添加员工职能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.insertSkillGrading(employeeFunctionLevelTO);
            return ActResult.initialize(employeeFunctionLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级数据to
     * @return class EmployeeFunctionLevelVO
     * @des 添加员工职能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.editEmployeeFunctionLevel(employeeFunctionLevelTO);
            return ActResult.initialize(employeeFunctionLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工职能定级
     *
     * @param id 用户id
     * @des 根据用户id删除员工职能定级
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            employeeFunctionLevelAPI.removeEmployeeFunctionLevel(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能等级情况概览
     *
     * @param employeeFunctionLevelTO 技能等级情况概览to
     * @return class OverviewSkillLevelVO
     * @des 技能等级情况概览
     * @version v1
     */
    @PostMapping("v1/skill")
    public Result skill(@Validated EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = employeeFunctionLevelAPI.skill(employeeFunctionLevelTO);
            return ActResult.initialize(BeanTransform.copyProperties(overviewSkillLevelBO, OverviewSkillLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名查询入职时间
     *
     * @return class StaffRecordsVO
     * @version v1
     */
    @GetMapping("v1/entryTime")
    public Result entryTime(String username, HttpServletRequest request) throws ActException {
        try {
            StaffRecordsBO bo = null;
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("archive")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
                bo = staffRecordsAPI.findByName(username);

            }
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffRecordsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}