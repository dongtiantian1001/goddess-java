package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.BussTypeWeightSetAPI;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.bo.BussTypeWeightSetBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.dto.BussTypeWeightSetDTO;
import com.bjike.goddess.customer.to.BussTypeWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.AreaWeightSetVO;
import com.bjike.goddess.customer.vo.BussTypeWeightSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 业务类型权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("busstypeweightset")
public class BussTypeWeightSetAction {
    @Autowired
    private BussTypeWeightSetAPI bussTypeWeightSetAPI;

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

            Boolean isHasPermission = bussTypeWeightSetAPI.guidePermission(guidePermissionTO);
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
     * 业务类型权重设置列表总条数
     *
     * @param bussTypeWeightSetDTO 业务类型权重设置dto
     * @des 获取所有业务类型权重设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws ActException {
        try {
            Long count = bussTypeWeightSetAPI.countBussType(bussTypeWeightSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个业务类型权重
     *
     * @param id 业务类型权重id
     * @return class BussTypeWeightSetVO
     * @des 获取所有业务类型权重
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result findBussWeightOne(@PathVariable String id) throws ActException {
        try {

            BussTypeWeightSetVO bussTypeWeightSetVO = BeanTransform.copyProperties(
                    bussTypeWeightSetAPI.getOneBussType(id), BussTypeWeightSetVO .class, true);
            return ActResult.initialize(bussTypeWeightSetVO);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务类型权重设置列表
     *
     * @param bussTypeWeightSetDTO 业务类型权重设置dto
     * @return class BussTypeWeightSetVO
     * @des 获取所有业务类型权重设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws ActException {
        try {

            List<BussTypeWeightSetBO> bussTypeWeightSetBOS = bussTypeWeightSetAPI.listBussType(bussTypeWeightSetDTO);
            List<BussTypeWeightSetVO> bussTypeWeightSetVOS = BeanTransform.copyProperties(bussTypeWeightSetBOS, BussTypeWeightSetVO.class);
            return ActResult.initialize(bussTypeWeightSetVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加业务类型权重设置
     *
     * @param bussTypeWeightSetTO 业务类型权重设置数据to
     * @return class BussTypeWeightSetVO
     * @des 添加业务类型权重设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBussWeight(@Validated(ADD.class) BussTypeWeightSetTO bussTypeWeightSetTO, BindingResult bindingResult) throws ActException {
        try {

            BussTypeWeightSetBO bussTypeWeightSetBO = bussTypeWeightSetAPI.addBussType(bussTypeWeightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(bussTypeWeightSetBO, BussTypeWeightSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑业务类型权重设置
     *
     * @param bussTypeWeightSetTO 业务类型权重设置数据bo
     * @return class BussTypeWeightSetVO
     * @des 添加业务类型权重设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editBussWeight(@Validated(EDIT.class) BussTypeWeightSetTO bussTypeWeightSetTO) throws ActException {
        try {

            BussTypeWeightSetBO bussTypeWeightSetBO = bussTypeWeightSetAPI.editBussType(bussTypeWeightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(bussTypeWeightSetBO, BussTypeWeightSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除业务类型权重设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBussWeight(@PathVariable String id) throws ActException {
        try {

            bussTypeWeightSetAPI.deleteBussType(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}