package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.PhoneReimburseDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimPhoneSelectStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimPhoneShowStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import com.bjike.goddess.lendreimbursement.to.*;
import com.bjike.goddess.lendreimbursement.vo.PhoneReimAuditprocingVO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseRecordVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 手机端报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 手机端报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("phoneReimburse")
public class PhoneReimburseAction extends BaseFileAction {

    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ReimburseAuditLogAPI reimburseAuditLogAPI;

    @Autowired
    private FileAPI fileAPI;



    /**
     * 添加报销凭证图片
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-发票图片
            String path = "/reimburseRecord/" + id + "/ticket";
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取一个报销的凭证图片
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listCard/{id}")
    public Result listCard(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/reimburseRecord/" + id + "/ticket";
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加申请报销
     *
     * @param phoneApplyReimburseTO 申请报销基本信息数据to
     * @return class ReimburseRecordVO
     * @des 添加申请报销
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addReimburseRecord(@Validated(PhoneApplyReimburseTO.TestAddAndEdit.class) PhoneApplyReimburseTO phoneApplyReimburseTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseRecordTO reimburseRecordTO = new ReimburseRecordTO();
            reimburseRecordTO.setOccureDate(phoneApplyReimburseTO.getOccureDate());
            reimburseRecordTO.setReimer(phoneApplyReimburseTO.getReimer());
            reimburseRecordTO.setCharger(phoneApplyReimburseTO.getCharger());
            reimburseRecordTO.setArea(phoneApplyReimburseTO.getArea());
            reimburseRecordTO.setProject(phoneApplyReimburseTO.getProject());
            reimburseRecordTO.setProjectGroup(phoneApplyReimburseTO.getProjectGroup());
            reimburseRecordTO.setFirstSubject(phoneApplyReimburseTO.getFirstSubject());
            reimburseRecordTO.setSecondSubject(phoneApplyReimburseTO.getSecondSubject());
            reimburseRecordTO.setThirdSubject(phoneApplyReimburseTO.getThirdSubject());
            reimburseRecordTO.setPlainInfo(phoneApplyReimburseTO.getPlainInfo());
            reimburseRecordTO.setDayTask(phoneApplyReimburseTO.getDayTask());
            reimburseRecordTO.setReimMoney(phoneApplyReimburseTO.getReimMoney());
            reimburseRecordTO.setTicketQuantity(phoneApplyReimburseTO.getTicketQuantity());
            reimburseRecordTO.setAttender(phoneApplyReimburseTO.getAttender());
            reimburseRecordTO.setTicketCondition(phoneApplyReimburseTO.getTicketCondition());
            reimburseRecordTO.setNoTicketRemark(phoneApplyReimburseTO.getNoTicketRemark());
            reimburseRecordTO.setAddContent(phoneApplyReimburseTO.getAddContent());
            reimburseRecordTO.setSummary(phoneApplyReimburseTO.getAddContent());
            reimburseRecordTO.setReimerRemark(phoneApplyReimburseTO.getAddContent());
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.addReimburseRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负责人审核
     *
     * @param phoneApplyLendChargerTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 审核等待审核报销
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/auditRecord")
    public Result auditRecord(@Validated(PhoneApplyLendChargerTO.TESTChargeAudit.class) PhoneApplyLendChargerTO phoneApplyLendChargerTO) throws ActException {
        try {
            ReimburseRecordTO reimburseRecordTO = new ReimburseRecordTO();
            reimburseRecordTO.setChargerAuditStatus(phoneApplyLendChargerTO.getChargerOpinion());
            if (phoneApplyLendChargerTO.getChargerPass().equals("是")) {
                reimburseRecordTO.setReimStatus(ReimStatus.CHARGEPASS);
            } else if (phoneApplyLendChargerTO.getChargerPass().equals("否")) {
                reimburseRecordTO.setReimStatus(ReimStatus.CHARGENOTPASS);
            }
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.auditRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 寄件
     *
     * @param phoneReimburseSendTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待审核的寄件
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/send")
    public Result sendRecord(@Validated(PhoneReimburseSendTO.TestAddAndEdit.class) PhoneReimburseSendTO phoneReimburseSendTO,BindingResult bindingResult) throws ActException {
        try {
            ReimburseRecordTO reimburseRecordTO = new ReimburseRecordTO();
            reimburseRecordTO.setId(phoneReimburseSendTO.getId());
            reimburseRecordTO.setTicketCondition(phoneReimburseSendTO.getTicketCondition());
            reimburseRecordTO.setSendRecevier(phoneReimburseSendTO.getSendRecevier());
            reimburseRecordTO.setSendDate(phoneReimburseSendTO.getSendDate());
            reimburseRecordTO.setReceiveArea(phoneReimburseSendTO.getReceiveArea());
            reimburseRecordTO.setReceiveAddr(phoneReimburseSendTO.getReceiveAddr());
            reimburseRecordTO.setSendCondition( phoneReimburseSendTO.getSendCondition() );
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.sendRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 负责人审核
     *
     * @param phoneReimburseChargeTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 审核等待审核报销
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/charge/auditRecord")
    public Result auditRecord(@Validated(PhoneReimburseChargeTO.TestChargeAudit.class) PhoneReimburseChargeTO phoneReimburseChargeTO) throws ActException {
        try {
            String usertoken = RpcContext.getContext().getAttachment("userToken");
            ReimburseRecordBO reimburseRecordBO1 = new ReimburseRecordBO();
            ReimburseRecordTO to = new ReimburseRecordTO();
            to.setId(phoneReimburseChargeTO.getId());
            ReimburseRecordBO reim = reimburseRecordAPI.getOneById(phoneReimburseChargeTO.getId());
            RpcContext.getContext().setAttachment("userToken",usertoken);
            if (reim.getReimStatus().equals(ReimStatus.NONE)) {
                //负责人审核
                to.setChargerAuditStatus(phoneReimburseChargeTO.getChargerAuditStatus());
                to.setTicketCondition(reim.getTicketCondition());
                to.setAuditAdvice(phoneReimburseChargeTO.getAuditAdvice());

                String pass = phoneReimburseChargeTO.getChargerAuditStatus();
                if ("通过".equals(pass)) {
                    to.setTicketCondition("是");
                } else if ("不通过".equals(pass)) {
                    to.setTicketCondition("否");
                }
                reimburseRecordBO1 = reimburseRecordAPI.auditRecord(to);
                RpcContext.getContext().setAttachment("userToken",usertoken);
            } else if (reim.getReimStatus().equals(ReimStatus.CONGEL)) {
                //负责人冻结确认
                String pass = phoneReimburseChargeTO.getChargerAuditStatus();
                if ("通过".equals(pass)) {
                    to.setSureCongel("否");
                } else if ("不通过".equals(pass)) {
                    to.setSureCongel("是");
                }
                to.setAuditAdvice(phoneReimburseChargeTO.getAuditAdvice());
                reimburseRecordBO1 = reimburseRecordAPI.congelAuditRecord(to);
            }
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 分析已经审核(运营部分析)
     *
     * @param phoneReimburseAnalisisTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 审分析报销, 包括申请冻结
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/analisys")
    public Result analisys(@Validated(PhoneReimburseAnalisisTO.TestAnalysis.class) PhoneReimburseAnalisisTO phoneReimburseAnalisisTO, BindingResult bindingResult) throws ActException {
        try {
            String usertoken = RpcContext.getContext().getAttachment("userToken");
            ReimburseRecordBO reimburseRecordBO1 = new ReimburseRecordBO();
            ReimburseRecordTO to = new ReimburseRecordTO();
            to.setId(phoneReimburseAnalisisTO.getId());
            ReimburseRecordBO reim = reimburseRecordAPI.getOneById(phoneReimburseAnalisisTO.getId());
            RpcContext.getContext().setAttachment("userToken",usertoken);
            if (reim.getReimStatus().equals(ReimStatus.CHARGEPASS)) {
                if ("已分析".equals(phoneReimburseAnalisisTO.getChargerAuditStatus())) {
                    to.setChargerAuditStatus("通过");
                    to.setAuditAdvice(phoneReimburseAnalisisTO.getAuditAdvice());
                    reimburseRecordBO1 = reimburseRecordAPI.analisysRecord(to);
                    RpcContext.getContext().setAttachment("userToken",usertoken);
                } else if ("申请冻结".equals(phoneReimburseAnalisisTO.getChargerAuditStatus())) {
                    to.setAuditAdvice(phoneReimburseAnalisisTO.getAuditAdvice());
                    reimburseRecordBO1 = reimburseRecordAPI.congelAnalisysRecord(to);

                }
            } else {
                throw new ActException("不可以分析");
            }
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 核对有误-帐务核对(收到单据)
     *
     * @param phoneReimburseCheckTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 帐务核对确认收到单据
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/acount/recieveTicket")
    public Result recieveTicket(@Validated(PhoneReimburseCheckTO.TestAddAndEdit.class) PhoneReimburseCheckTO phoneReimburseCheckTO) throws ActException {
        try {
            // 帐务核对有误的情况
            String userToken = RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
            UserBO userBO = userAPI.currentUser();
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
            ReimburseRecordTO to = new ReimburseRecordTO();
            to.setId(phoneReimburseCheckTO.getId());
            to.setAccountCheckPassOr("否");
            to.setReceiveTicketer(userBO.getUsername());
            to.setReceiveTicketCon(phoneReimburseCheckTO.getReceiveTicketCon());
            to.setReceiveTicketTime(LocalDate.now().toString());
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.recieveTicketCondition(to);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 预计付款-帐务核对通过
     *
     * @param phoneReimbursePayTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待付款预计付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/phone/prepay")
    public Result prepay(@Validated(PhoneReimbursePayTO.TestPrePay.class) PhoneReimbursePayTO phoneReimbursePayTO) throws ActException {
        try {
            String userToken = RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
            if (StringUtils.isBlank(phoneReimbursePayTO.getId())) {
                throw new ActException("id不能为空");
            }
            ReimburseRecordBO reimburseRecordBO1 = new ReimburseRecordBO();
            ReimburseRecordBO temp = reimburseRecordAPI.getOneById(phoneReimbursePayTO.getId());
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
            if (phoneReimbursePayTO.getPayOrPre()) {
                //说明是预计付款
                if (!phoneReimbursePayTO.getReimMoney().equals(temp.getReimMoney())) {
                    throw new SerException("预计付款金额与报销金额相等，预计付款失败");
                }
                ReimburseRecordTO reimburseRecordTO = new ReimburseRecordTO();
                reimburseRecordTO.setReimNumbers(new String[]{temp.getReimNumber()});
                reimburseRecordTO.setBudgetPayTime(phoneReimbursePayTO.getBudgetPayTime());
                reimburseRecordTO.setPayPlan(phoneReimbursePayTO.getReceiveTicketCon());
                reimburseRecordBO1 = reimburseRecordAPI.prePay(reimburseRecordTO);
            } else if (!phoneReimbursePayTO.getPayOrPre()) {
                //说明是付款
                throw new SerException("请选择预计付款");
            }
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 去付款-帐务核对通过
     *
     * @param phoneReimbursePayTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待付款的付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/phone/pay")
    public Result pay(@Validated(PhoneReimbursePayTO.TestPay.class) PhoneReimbursePayTO phoneReimbursePayTO) throws ActException {
        try {
            if (StringUtils.isBlank(phoneReimbursePayTO.getId())) {
                throw new ActException("id不能为空");
            }
            ReimburseRecordBO reimburseRecordBO1 = new ReimburseRecordBO();
            if (phoneReimbursePayTO.getPayOrPre()) {
                //说明是预计付款
                throw new SerException("请选择付款,而不是预计付款");
            } else if (!phoneReimbursePayTO.getPayOrPre()) {
                //说明是付款
                reimburseRecordBO1 = reimburseRecordAPI.phoneWaitPay(phoneReimbursePayTO);
            }
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查看审核过程原因
     *
     * @param id 申请报销基本信息数据id
     * @return class PhoneReimAuditprocingVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/reimAudit/reason/{id}")
    public Result findReason(@PathVariable String id) throws ActException {
        try {
            //负责人审核
            //财务分析
            //财务分析申请冻结
            //负责人确认冻结
            List<PhoneReimAuditprocingVO> list = new ArrayList<>();
            PhoneReimAuditprocingVO vo = new PhoneReimAuditprocingVO();
            ReimburseRecordBO reimburseRecordBO = reimburseRecordAPI.getOneById(id);
            //负责人审核
            if (StringUtils.isNotBlank(reimburseRecordBO.getChargerAuditTime()) && StringUtils.isBlank(reimburseRecordBO.getChargerCongelTime())) {
                //说明只是负责人审核，还没有冻结操作
                vo.setAuditTime(reimburseRecordBO.getChargerAuditTime());
                vo.setProcing("负责人:" + reimburseRecordBO.getCharger() + " <br>是否通过：" + reimburseRecordBO.getChargerAuditStatus()
                        + " <br>审核意见：" + (StringUtils.isBlank(reimburseRecordBO.getAuditAdvice())?"":reimburseRecordBO.getAuditAdvice()));

                list.add(vo);
            } else if (StringUtils.isNotBlank(reimburseRecordBO.getChargerAuditTime()) && StringUtils.isNotBlank(reimburseRecordBO.getChargerCongelTime())) {
                //说明负责人既有审核，又有冻结操作
                vo = new PhoneReimAuditprocingVO();
                vo.setAuditTime(reimburseRecordBO.getChargerCongelTime());
                vo.setProcing("负责人:" + reimburseRecordBO.getCharger() + " <br>是否通过：审核通过"
                        + " <br>审核意见：" + reimburseRecordBO.getChargeCongleAdvice());
                list.add(vo);

            }else if( StringUtils.isBlank(reimburseRecordBO.getChargerAuditTime()) && StringUtils.isBlank(reimburseRecordBO.getChargerCongelTime()) ){
                //说明负责人既没有审核，也没有冻结操作
                vo.setAuditTime(reimburseRecordBO.getChargerAuditTime());
                vo.setProcing("负责人:" + reimburseRecordBO.getCharger()
                        + " <br>是否通过：" + (StringUtils.isBlank(reimburseRecordBO.getChargerAuditStatus())?"未处理":reimburseRecordBO.getChargerAuditStatus())
                        + " <br>审核意见：" + (StringUtils.isBlank(reimburseRecordBO.getAuditAdvice())?"":reimburseRecordBO.getAuditAdvice()));
                list.add(vo);
            }

            //财务分析分析或申请冻结
            List<ReimburseAuditLogBO> logs = reimburseAuditLogAPI.listReimburseAuditLogByRid(reimburseRecordBO.getId());
            if (logs != null && logs.size() > 0) {
                for (ReimburseAuditLogBO str : logs) {
                    vo = new PhoneReimAuditprocingVO();
                    vo.setAuditTime(str.getAuditTime());
                    vo.setProcing("分析人：" + str.getUserName() + " <br>分析人职位：" + (StringUtils.isBlank(str.getPosition())?"":str.getPosition())
                            + " <br>是否分析通过：" + (StringUtils.isBlank(str.getAuditStatus())?"":str.getAuditStatus())
                            + " <br>审核分析意见：" + (StringUtils.isBlank(str.getContent())?"":str.getContent()));

                    list.add(vo);
                }
            }

            //负责人确认冻结
            if (StringUtils.isNotBlank(reimburseRecordBO.getChargerAuditTime()) && StringUtils.isNotBlank(reimburseRecordBO.getChargerCongelTime())) {
                vo = new PhoneReimAuditprocingVO();
                vo.setAuditTime(reimburseRecordBO.getChargerAuditTime());
                vo.setProcing("负责人:" + reimburseRecordBO.getCharger()
                        + " <br>是否确认冻结："+("不通过".equals(reimburseRecordBO.getChargerAuditStatus())?"确认冻结":"取消冻结")
                        + " <br>审核意见：" + reimburseRecordBO.getAuditAdvice());
                list.add(vo);
            }

            //帐务核对
            if (StringUtils.isNotBlank(reimburseRecordBO.getReceiveTicketTime())) {
                vo = new PhoneReimAuditprocingVO();
                vo.setAuditTime(reimburseRecordBO.getReceiveTicketTime());
                vo.setProcing("帐务审核人:" + reimburseRecordBO.getReceiveTicketer() + " <br>是否帐务核对通过：" + reimburseRecordBO.getAccountCheckPassOr()
                        + " <br>帐务核对意见：" + reimburseRecordBO.getReceiveTicketCon());

                list.add(vo);
            }
            //预计付款
            if (StringUtils.isNotBlank(reimburseRecordBO.getBudgetPayTime())) {
                vo = new PhoneReimAuditprocingVO();
                vo.setAuditTime(reimburseRecordBO.getBudgetPayTime());
                vo.setProcing("预计付款说明：" + reimburseRecordBO.getPayPlan());

                list.add(vo);
            }
            //付款
            if (StringUtils.isNotBlank(reimburseRecordBO.getPayTime())) {
                vo = new PhoneReimAuditprocingVO();
                vo.setAuditTime(reimburseRecordBO.getPayTime());
                vo.setProcing("付款说明：" + reimburseRecordBO.getReceiveTicketCon());

                list.add(vo);
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 报销所有列表
     *
     * @param phoneReimburseDTO 申请借款条件
     * @return class ReimburseRecordVO
     * @des 获取所有列表
     * @version v1
     */
    @GetMapping("v1/listAll")
    public Result listAll(@Validated(PhoneReimburseDTO.TESTSELECT.class) PhoneReimburseDTO phoneReimburseDTO , BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listAll(phoneReimburseDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 详情(手机端权限个别按钮出现)
     *
     * @param id 报销id
     * @param reimPhoneSelectStatus 状态
     * @return class ReimburseRecordVO
     * @des 手机端的详情
     * @version v1
     */
    @GetMapping("v1/info/reim/{id}/{reimPhoneSelectStatus}")
    public Result findListApplyLend(@PathVariable String id, @PathVariable ReimPhoneSelectStatus reimPhoneSelectStatus, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            String userToken = RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
            ReimburseRecordVO recordVO = BeanTransform.copyProperties(
                    reimburseRecordAPI.getOneById(id), ReimburseRecordVO.class, true);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
            ReimPhoneShowStatus showRight = reimburseRecordAPI.phoneShowRight(reimPhoneSelectStatus, id);
            recordVO.setReimPhoneShowStatus(showRight);

            return ActResult.initialize(recordVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    //图片列表
//    String path = "/reimburseRecord/" + id + "/ticket";
//    FileInfo fileInfo = new FileInfo();
//            fileInfo.setPath(path);
//    Object storageToken = request.getAttribute("storageToken");
//            fileInfo.setStorageToken(storageToken.toString());
//    List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
//
//
//    /**
//     * 文件下载
//     *
//     * @param path 文件路径
//     * @version v1
//     */
//    @GetMapping("v1/downloadFile")
//    public Result download(@RequestParam String path, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ActException {
//        try {
//            //该文件的路径
//            FileInfo fileInfo = new FileInfo();
//            Object storageToken = request.getAttribute("storageToken");
//            fileInfo.setStorageToken(storageToken.toString());
//            fileInfo.setPath(path);
//            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
//            byte[] buffer = fileAPI.download(fileInfo);
//            writeOutFile(response, buffer, filename);
//            return new ActResult("download success");
//        } catch (Exception e) {
//            throw new ActException(e.getMessage());
//        }
//
//    }








}