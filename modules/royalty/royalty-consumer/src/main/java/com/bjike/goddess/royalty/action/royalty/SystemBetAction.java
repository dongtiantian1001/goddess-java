package com.bjike.goddess.royalty.action.royalty;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import com.bjike.goddess.royalty.api.SystemBetAPI;
import com.bjike.goddess.royalty.bo.SystemBetABO;
import com.bjike.goddess.royalty.dto.SystemBetADTO;
import com.bjike.goddess.royalty.dto.SystemBetDDTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.ProjectNameTO;
import com.bjike.goddess.royalty.to.SystemBetATO;
import com.bjike.goddess.royalty.vo.SystemBetAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 体系间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("systembet")
public class SystemBetAction {
    @Autowired
    private SystemBetAPI systemBetAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Autowired
    private ModuleAPI moduleAPI;

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

            Boolean isHasPermission = systemBetAPI.guidePermission(guidePermissionTO);
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
     * 体系间对赌表列表总条数
     *
     * @param dto 体系间对赌表dto
     * @des 获取所有体系间对赌表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SystemBetDDTO dto) throws ActException {
        try {
            Long count = systemBetAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个体系间对赌表
     *
     * @param id id
     * @return class SystemBetAVO
     * @des 获取一个体系间对赌表
     * @version v1
     */
    @GetMapping("v1/bet/{id}")
    public Result bet(@PathVariable String id) throws ActException {
        try {
            SystemBetABO systemBetABO = systemBetAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(systemBetABO, SystemBetAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 体系间对赌表列表
     *
     * @param dto 体系间对赌表dto
     * @return class SystemBetAVO
     * @des 获取所有体系间对赌表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SystemBetADTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(systemBetAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加体系间对赌表
     *
     * @param systemBetATO 体系间对赌表to
     * @des 添加体系间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated SystemBetATO systemBetATO, BindingResult bindingResult) throws ActException {
        try {
            systemBetAPI.insert(systemBetATO);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑体系间对赌表
     *
     * @param systemBetATO 体系间对赌表数据to
     * @des 编辑体系间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated SystemBetATO systemBetATO, BindingResult bindingResult) throws ActException {
        try {
            systemBetAPI.edit(systemBetATO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除体系间对赌表
     *
     * @param id 用户id
     * @des 根据用户id删除体系间对赌表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            systemBetAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取项目名称
     *
     * @des 获取项目名称集合
     * @version v1
     */
    @GetMapping("v1/projectName")
    public Result projectName() throws ActException {
        try {
            List<String> projectNameList = systemBetAPI.getProjectName();
            return ActResult.initialize(projectNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取体系
     *
     * @return class HierarchyVO
     * @des 获取体系
     * @version v1
     */
    @GetMapping("v1/hierarchy")
    public Result hierarchy() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.findStatus(), HierarchyVO.class));
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
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<AreaBO> list = new ArrayList<>();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
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
     * 根据项目名称获取体系间对赌表
     *
     * @des 根据项目名称获取体系间对赌表
     * @version v1
     */
    @GetMapping("v1/getSystem")
    public Result getSystem(String projectName) throws ActException {
        try {
            SystemBetABO systemBetABO = systemBetAPI.getSystem(projectName);
            return ActResult.initialize(BeanTransform.copyProperties(systemBetABO, SystemBetAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param to to
     * @return class SystemBetAVO
     * @des 根据项目名称汇总体系间对赌表
     * @version v1
     */
    @GetMapping("v1/systemCollect")
    public Result systemCollect(ProjectNameTO to) throws ActException {
        try {
            List<SystemBetABO> systemBetABO = systemBetAPI.systemCollect(to);
            return ActResult.initialize(BeanTransform.copyProperties(systemBetABO, SystemBetAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}