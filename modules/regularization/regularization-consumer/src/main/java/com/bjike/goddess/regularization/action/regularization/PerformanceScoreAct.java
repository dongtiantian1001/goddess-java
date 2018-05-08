package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.PerformanceScoreAPI;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;
import com.bjike.goddess.regularization.vo.PerformanceScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作表现评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("performancescore")
public class PerformanceScoreAct {

    @Autowired
    private PerformanceScoreAPI performanceScoreAPI;

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

            Boolean isHasPermission = performanceScoreAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/performancescore/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PerformanceScoreBO bo = performanceScoreAPI.findById(id);
            PerformanceScoreVO vo = BeanTransform.copyProperties(bo, PerformanceScoreVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 工作表现评分dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated PerformanceScoreDTO dto, BindingResult result) throws ActException {
        try {
            Long count = performanceScoreAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询工作表现评分
     *
     * @param dto 工作表现评分dto
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated PerformanceScoreDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<PerformanceScoreBO> boList = performanceScoreAPI.list(dto);
            List<PerformanceScoreVO> voList = BeanTransform.copyProperties(boList, PerformanceScoreVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作表现评分
     *
     * @param to 工作表现评分to
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) PerformanceScoreTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            PerformanceScoreBO bo = performanceScoreAPI.save(to);
            PerformanceScoreVO vo = BeanTransform.copyProperties(bo, PerformanceScoreVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            performanceScoreAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作表现评分
     *
     * @param to 工作表现评分to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) PerformanceScoreTO to, BindingResult result) throws ActException {
        try {
            performanceScoreAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}