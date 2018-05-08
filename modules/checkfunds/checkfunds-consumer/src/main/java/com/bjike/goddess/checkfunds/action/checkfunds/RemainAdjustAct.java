package com.bjike.goddess.checkfunds.action.checkfunds;

import com.bjike.goddess.checkfunds.api.RemainAdjustAPI;
import com.bjike.goddess.checkfunds.bo.RemainAdjustBO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.RemainAdjustTO;
import com.bjike.goddess.checkfunds.vo.RemainAdjustVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 余额调节
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("remainadjust")
public class RemainAdjustAct {
    @Autowired
    private RemainAdjustAPI remainAdjustAPI;

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

            Boolean isHasPermission = remainAdjustAPI.guidePermission(guidePermissionTO);
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
     * 余额调节列表总条数
     *
     * @param dto 余额调节dto
     * @des 获取所有余额调节总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(RemainAdjustDTO dto) throws ActException {
        try {
            Long count = remainAdjustAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 加资金流水项目
     *
     * @param bankId 银企对账id
     * @param to     余额调节to
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addFund/{bankId}")
    public Result addFund(@PathVariable String bankId, @Validated({RemainAdjustTO.AddFund.class}) RemainAdjustTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustBO> boList = remainAdjustAPI.addFund(to, bankId);
            return ActResult.initialize(BeanTransform.copyProperties(boList, RemainAdjustVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 减资金流水项目
     *
     * @param bankId 银企对账id
     * @param to     余额调节to
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/removeFund/{bankId}")
    public Result removeFund(@PathVariable String bankId, @Validated({RemainAdjustTO.RemoveFund.class}) RemainAdjustTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustBO> boList = remainAdjustAPI.removeFund(to, bankId);
            return ActResult.initialize(BeanTransform.copyProperties(boList, RemainAdjustVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 加银行流水项目
     *
     * @param bankId 银企对账id
     * @param to     余额调节to
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addBank/{bankId}")
    public Result addBank(@PathVariable String bankId, @Validated({RemainAdjustTO.AddBank.class}) RemainAdjustTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustBO> boList = remainAdjustAPI.addBank(to, bankId);
            return ActResult.initialize(BeanTransform.copyProperties(boList, RemainAdjustVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 减银行流水项目
     *
     * @param bankId 银企对账id
     * @param to     余额调节to
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/removeBank/{bankId}")
    public Result removeBank(@PathVariable String bankId, @Validated({RemainAdjustTO.RemoveBank.class}) RemainAdjustTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustBO> boList = remainAdjustAPI.removeBank(to, bankId);
            return ActResult.initialize(BeanTransform.copyProperties(boList, RemainAdjustVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认余额调节
     *
     * @param bankId      银企对账id
     * @param fundBalance 资金流水余额（从余额调节列表中的最后一条记录获取）
     * @param bankBalance 银行流水余额（从余额调节列表中的最后一条记录获取）
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/confirmAdjust/{bankId}")
    public Result confirmAdjust(@PathVariable String bankId, @RequestParam Double fundBalance, @RequestParam Double bankBalance, HttpServletRequest request) throws ActException {
        try {
            remainAdjustAPI.confirmAdjust(bankId, fundBalance, bankBalance);
            request.getSession().removeAttribute("id");
            return new ActResult("确认余额调节成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}