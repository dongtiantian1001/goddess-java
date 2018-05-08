package com.bjike.goddess.checkfunds.action.checkfunds;

import com.bjike.goddess.bankrecords.bo.BankRecordPageListBO;
import com.bjike.goddess.checkfunds.api.BankReconciliationAPI;
import com.bjike.goddess.checkfunds.bo.BankReconciliationBO;
import com.bjike.goddess.checkfunds.bo.CreditorDifferBO;
import com.bjike.goddess.checkfunds.bo.DebtorDifferBO;
import com.bjike.goddess.checkfunds.bo.FundDetailBO;
import com.bjike.goddess.checkfunds.dto.BankReconciliationDTO;
import com.bjike.goddess.checkfunds.to.BankReconciliationTO;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.vo.*;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 银企对账（核对）
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bankreconciliation")
public class BankReconciliationAct {
    @Autowired
    private BankReconciliationAPI bankReconciliationAPI;
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
    public Result setButtonPermission() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = bankReconciliationAPI.sonPermission();
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

            Boolean isHasPermission = bankReconciliationAPI.guidePermission(guidePermissionTO);
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
     * 银企对账（核对）列表总条数
     *
     * @param dto 银企对账（核对）dto
     * @des 获取所有银企对账（核对）总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(BankReconciliationDTO dto) throws ActException {
        try {
            Long count = bankReconciliationAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个银企对账（核对）
     *
     * @param id id
     * @return class BankReconciliationVO
     * @des 获取一个银企对账（核对）
     * @version v1
     */
    @GetMapping("v1/bankreconciliation/{id}")
    public Result bankreconciliation(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BankReconciliationBO bo = bankReconciliationAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BankReconciliationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 银企对账（核对）列表
     *
     * @param dto 银企对账（核对）信息dto
     * @return class BankReconciliationVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BankReconciliationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BankReconciliationVO> VOS = BeanTransform.copyProperties
                    (bankReconciliationAPI.list(dto), BankReconciliationVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 经办
     *
     * @param to 银企对账（核对）信息数据to
     * @return class BankReconciliationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/handle")
    public Result handle(@Validated({ADD.class}) BankReconciliationTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        String id = (String) request.getSession().getAttribute("id");
        try {
            BankReconciliationBO bo = bankReconciliationAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BankReconciliationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 余额调节
     *
     * @param id 银企对账id
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/adjust/{id}")
    public Result adjust(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustVO> VOS = BeanTransform.copyProperties
                    (bankReconciliationAPI.adjust(id), RemainAdjustVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 提交
     *
     * @param id 银企对账id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/commit/{id}")
    public Result commit(@PathVariable String id) throws ActException {
        try {
            bankReconciliationAPI.commit(id);
            return new ActResult("提交成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审批通过
     *
     * @param id 银企对账id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/aduitPass/{id}")
    public Result aduitPass(@PathVariable String id) throws ActException {
        try {
            bankReconciliationAPI.aduitPass(id);
            return new ActResult("审批通过");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审批不通过
     *
     * @param id 银企对账id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/aduitNotPass/{id}")
    public Result aduitNotPass(@PathVariable String id) throws ActException {
        try {
            bankReconciliationAPI.aduitNotPass(id);
            return new ActResult("审批不通过");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有用户名
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allNames")
    public Result allNames() throws ActException {
        try {
            return ActResult.initialize(bankReconciliationAPI.allNames());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找资金流水明细
     *
     * @param id 银行对账id
     * @return class FundDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/fundDetail/{id}")
    public Result fundDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<FundDetailBO> list = bankReconciliationAPI.fundDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, FundDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找银行流水明细
     *
     * @param id 银行对账id
     * @return class BankDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/bankDetail/{id}")
    public Result bankDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<BankRecordPageListBO> list = bankReconciliationAPI.bankDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, BankDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借方差异
     *
     * @param id 银行对账id
     * @return class DebtorDifferVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/debtorDiffer/{id}")
    public Result debtorDiffer(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<DebtorDifferBO> list = bankReconciliationAPI.debtorDiffer(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, DebtorDifferVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 贷方差异
     *
     * @param id 银行对账id
     * @return class CreditorDifferVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/creditorDiffer/{id}")
    public Result creditorDiffer(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<CreditorDifferBO> list = bankReconciliationAPI.creditorDiffer(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, CreditorDifferVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}