package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.TimelinessFactorSetAPI;
import com.bjike.goddess.customer.bo.TimelinessFactorSetBO;
import com.bjike.goddess.customer.dto.TimelinessFactorSetDTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.TimelinessFactorSetTO;
import com.bjike.goddess.customer.vo.FunPowerWeightFactorVO;
import com.bjike.goddess.customer.vo.TimelinessFactorSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 时效性因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:49 ]
 * @Description: [ 时效性因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("timelinessfactorset")
public class TimelinessFactorSetAction {
    @Autowired
    private TimelinessFactorSetAPI timelinessFactorSetAPI;

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

            Boolean isHasPermission = timelinessFactorSetAPI.guidePermission(guidePermissionTO);
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
     * 时效性因素层设置列表总条数
     *
     * @param timelinessFactorSetDTO 时效性因素层设置dto
     * @des 获取所有时效性因素层设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TimelinessFactorSetDTO timelinessFactorSetDTO) throws ActException {
        try {
            Long count = timelinessFactorSetAPI.countTimeliness(timelinessFactorSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个时效性因素层设置
     *
     * @param id  时效性因素层设置id
     * @return class TimelinessFactorSetVO
     * @des 一个时效性因素层设置
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result findCustomerWeiOne(@PathVariable String id) throws ActException {
        try {

            TimelinessFactorSetVO timelinessFactorSetVO = BeanTransform.copyProperties(
                    timelinessFactorSetAPI.getOneTimeliness(id), TimelinessFactorSetVO .class, true);
            return ActResult.initialize(timelinessFactorSetVO);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 时效性因素层设置列表
     *
     * @param timelinessFactorSetDTO 时效性因素层设置dto
     * @return class TimelinessFactorSetVO
     * @des 获取所有时效性因素层设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(TimelinessFactorSetDTO timelinessFactorSetDTO) throws ActException {
        try {

            List<TimelinessFactorSetBO> timelinessFactorSetBOS = timelinessFactorSetAPI.listTimeliness(timelinessFactorSetDTO);
            List<TimelinessFactorSetVO> timelinessFactorSetVOS = BeanTransform.copyProperties(timelinessFactorSetBOS, TimelinessFactorSetVO.class);
            return ActResult.initialize(timelinessFactorSetVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加时效性因素层设置
     *
     * @param timelinessFactorSetTO 时效性因素层设置to
     * @return class TimelinessFactorSetVO
     * @des 添加时效性因素层设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBussWeight(@Validated(ADD.class) TimelinessFactorSetTO timelinessFactorSetTO, BindingResult bindingResult) throws ActException {
        try {

            TimelinessFactorSetBO timelinessFactorSetBO = timelinessFactorSetAPI.addTimeliness(timelinessFactorSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(timelinessFactorSetBO, TimelinessFactorSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑时效性因素层设置
     *
     * @param timelinessFactorSetTO 时效性因素层设置数据bo
     * @return class TimelinessFactorSetVO
     * @des 添加时效性因素层设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editBussWeight(@Validated(EDIT.class) TimelinessFactorSetTO timelinessFactorSetTO, BindingResult bindingResult) throws ActException {
        try {

            TimelinessFactorSetBO timelinessFactorSetBO = timelinessFactorSetAPI.editTimeliness(timelinessFactorSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(timelinessFactorSetBO, TimelinessFactorSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除时效性因素层设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBussWeight(@PathVariable String id) throws ActException {
        try {

            timelinessFactorSetAPI.deleteTimeliness(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}