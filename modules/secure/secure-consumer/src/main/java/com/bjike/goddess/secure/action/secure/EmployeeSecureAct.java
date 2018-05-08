package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.EmployeeSecureAPI;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.NameTO;
import com.bjike.goddess.secure.vo.AddEmployeeVO;
import com.bjike.goddess.secure.vo.EmployeeSecureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 员工社保基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("employeesecure")
public class EmployeeSecureAct {
    @Autowired
    private EmployeeSecureAPI employeeSecureAPI;

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

            Boolean isHasPermission = employeeSecureAPI.guidePermission(guidePermissionTO);
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
     * @param dto     员工社保分页信息
     * @param request 请求对象
     * @return class EmployeeSecureVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(@Validated EmployeeSecureDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<EmployeeSecureBO> list = employeeSecureAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, EmployeeSecureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      员工社保信息
     * @param result
     * @param request 请求对象
     * @return class EmployeeSecureVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EmployeeSecureTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            EmployeeSecureBO employeeSecureBO = employeeSecureAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(employeeSecureBO, EmployeeSecureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      员工社保id
     * @param request 请求对象
     * @return class EmployeeSecureVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/employeesecure/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            EmployeeSecureBO employeeSecureBO = employeeSecureAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(employeeSecureBO, EmployeeSecureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 员工社保id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            employeeSecureAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EmployeeSecureDTO dto) throws ActException {
        try {
            return ActResult.initialize(employeeSecureAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());

        }
    }
    /**
     * 根据姓名获取社保基本信息
     *
     * @param to to
     * @return class EmployeeSecureVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/byName")
    public Result byName(@Validated(NameTO.TestName.class) NameTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<EmployeeSecureBO> bos = employeeSecureAPI.byName(to);
            return ActResult.initialize(BeanTransform.copyProperties(bos, EmployeeSecureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有姓名
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name() throws ActException {
        try {
            Set<String> set = employeeSecureAPI.allName();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}