package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.ReportAddressInforAPI;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import com.bjike.goddess.recruit.vo.ReportAddressInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("reportAddressInfor")
public class ReportAddressInforAct {

    @Autowired
    private ReportAddressInforAPI reportAddressInforAPI;

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

            Boolean isHasPermission = reportAddressInforAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询报道地址信息
     *
     * @param id 报道地址信息唯一标识
     * @return class ReportAddressInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/reportAddressInfor/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ReportAddressInforBO bo = reportAddressInforAPI.findById(id);
            ReportAddressInforVO vo = BeanTransform.copyProperties(bo, ReportAddressInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 报道地址信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ReportAddressInforDTO dto, BindingResult result) throws ActException {
        try {
            Long count = reportAddressInforAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 报道地址信息dto
     * @return class ReportAddressInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ReportAddressInforDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ReportAddressInforBO> boList = reportAddressInforAPI.list(dto);
            List<ReportAddressInforVO> voList = BeanTransform.copyProperties(boList, ReportAddressInforVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加报道地址信息
     *
     * @param to 报道地址信息to信息
     * @return class ReportAddressInforVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ReportAddressInforTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ReportAddressInforBO bo = reportAddressInforAPI.save(to);
            ReportAddressInforVO vo = BeanTransform.copyProperties(bo, ReportAddressInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除报道地址信息
     *
     * @param id 报道地址信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            reportAddressInforAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑报道地址信息
     *
     * @param to 报道地址信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ReportAddressInforTO to, BindingResult result) throws ActException {
        try {
            reportAddressInforAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有入职报道地址
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allAddress")
    public Result allAddress() throws ActException {
        try {
            return ActResult.initialize(reportAddressInforAPI.allAddress());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
