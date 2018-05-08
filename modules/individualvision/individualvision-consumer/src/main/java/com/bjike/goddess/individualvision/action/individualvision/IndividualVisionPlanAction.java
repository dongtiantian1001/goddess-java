package com.bjike.goddess.individualvision.action.individualvision;

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
import com.bjike.goddess.individualvision.api.IndividualVisionPlanAPI;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.excel.SonPermissionObject;
import com.bjike.goddess.individualvision.to.GuidePermissionTO;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;
import com.bjike.goddess.individualvision.vo.IndividualVisionPlanVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 个人愿景计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("individualvisionplan")
public class IndividualVisionPlanAction {
    @Autowired
    private IndividualVisionPlanAPI individualVisionPlanAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = individualVisionPlanAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = individualVisionPlanAPI.guidePermission(guidePermissionTO);
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
     * 个人愿景计划列表总条数
     *
     * @param individualVisionPlanDTO 个人愿景计划dto
     * @des 获取所有个人愿景计划
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IndividualVisionPlanDTO individualVisionPlanDTO) throws ActException {
        try {
            Long count = individualVisionPlanAPI.countIndividualVisionPlan(individualVisionPlanDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个个人愿景计划
     *
     * @param id
     * @return class IndividualVisionPlanVO
     * @des 获取一个个人愿景计划
     * @version v1
     */
    @GetMapping("v1/plan/{id}")
    public Result plan(@PathVariable String id) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(individualVisionPlanBO, IndividualVisionPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人愿景计划列表
     *
     * @param individualVisionPlanDTO 个人愿景计划dto
     * @return class IndividualVisionPlanVO
     * @des 获取所有个人愿景计划
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IndividualVisionPlanDTO individualVisionPlanDTO, HttpServletRequest request) throws ActException {
        try {
            List<IndividualVisionPlanVO> individualVisionPlanVOS = BeanTransform.copyProperties
                    (individualVisionPlanAPI.findListIndividualVisionPlan(individualVisionPlanDTO), IndividualVisionPlanVO.class, request);
            return ActResult.initialize(individualVisionPlanVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @return class IndividualVisionPlanVO
     * @des 添加个人愿景计划
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) IndividualVisionPlanTO individualVisionPlanTO, BindingResult bindingResult) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.insertIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(individualVisionPlanBO, IndividualVisionPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @return class IndividualVisionPlanVO
     * @des 编辑个人愿景计划
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IndividualVisionPlanTO individualVisionPlanTO, BindingResult bindingResult) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.editIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(individualVisionPlanBO, IndividualVisionPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除个人愿景计划
     *
     * @param id 用户id
     * @des 根据用户id删除个人愿景计划记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            individualVisionPlanAPI.removeIndividualVisionPlan(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param individualVisionPlanTO 个人愿景计划数据bo
     * @return class IndividualVisionPlanVO
     * @des 审核个人愿景计划
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(@Validated(IndividualVisionPlanTO.TestAudit.class) IndividualVisionPlanTO individualVisionPlanTO, BindingResult bindingResult) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.auditIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(individualVisionPlanBO, IndividualVisionPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
                boList = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
                boList = departmentDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = new ArrayList<>();
            String userToken=request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
                userBOS = positionDetailUserAPI.findUserListInOrgan();
            }
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
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