package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.ClosenessFoactorSetAPI;
import com.bjike.goddess.customer.bo.ClosenessFoactorSetBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorSetDTO;
import com.bjike.goddess.customer.to.ClosenessFoactorSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.ClosenessFoactorSetVO;
import com.bjike.goddess.customer.vo.TimelinessFactorSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 亲密度因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("closenessfoactorset")
public class ClosenessFoactorSetAction {
    @Autowired
    private ClosenessFoactorSetAPI closenessFoactorSetAPI;

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

            Boolean isHasPermission = closenessFoactorSetAPI.guidePermission(guidePermissionTO);
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
     * 亲密度因素层设置列表总条数
     *
     * @param closenessFoactorSetDTO 亲密度因素层设置dto
     * @des 获取所有亲密度因素层设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws ActException {
        try {
            Long count = closenessFoactorSetAPI.countCloseness(closenessFoactorSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 亲密度因素层设置列表
     *
     * @param closenessFoactorSetDTO 亲密度因素层设置dto
     * @return class ClosenessFoactorSetVO
     * @des 获取所有亲密度因素层设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws ActException {
        try {

            List<ClosenessFoactorSetBO> timelinessFactorSetBOS = closenessFoactorSetAPI.listCloseness(closenessFoactorSetDTO);
            List<ClosenessFoactorSetVO> timelinessFactorSetVOS = BeanTransform.copyProperties(timelinessFactorSetBOS, ClosenessFoactorSetVO.class);
            return ActResult.initialize(timelinessFactorSetVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个亲密度因素层设置
     *
     * @param id 业务类型权重id
     * @return class ClosenessFoactorSetVO
     * @des 一个亲密度因素层设置
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result findClosenessOne(@PathVariable String id) throws ActException {
        try {

            ClosenessFoactorSetVO closenessFoactorSetVO = BeanTransform.copyProperties(
                    closenessFoactorSetAPI.getOneCloseness(id), ClosenessFoactorSetVO .class, true);
            return ActResult.initialize(closenessFoactorSetVO);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加亲密度因素层设置
     *
     * @param closenessFoactorSetTO 亲密度因素层设置to
     * @return class ClosenessFoactorSetVO
     * @des 添加亲密度因素层设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBussWeight(@Validated(ADD.class) ClosenessFoactorSetTO closenessFoactorSetTO, BindingResult bindingResult) throws ActException {
        try {

            ClosenessFoactorSetBO closenessFoactorSetBO = closenessFoactorSetAPI.addCloseness(closenessFoactorSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(closenessFoactorSetBO, ClosenessFoactorSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑亲密度因素层设置
     *
     * @param closenessFoactorSetTO 亲密度因素层设置数据bo
     * @return class ClosenessFoactorSetVO
     * @des 添加亲密度因素层设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editBussWeight(@Validated(EDIT.class) ClosenessFoactorSetTO closenessFoactorSetTO, BindingResult bindingResult) throws ActException {
        try {

            ClosenessFoactorSetBO closenessFoactorSetBO = closenessFoactorSetAPI.editCloseness(closenessFoactorSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(closenessFoactorSetBO, ClosenessFoactorSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除亲密度因素层设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBussWeight(@PathVariable String id) throws ActException {
        try {

            closenessFoactorSetAPI.deleteCloseness(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}