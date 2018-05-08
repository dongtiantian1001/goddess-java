package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.SecondSubjectDataBO;
import com.bjike.goddess.financeinit.bo.SubjectDataBO;
import com.bjike.goddess.financeinit.bo.SubjectDatasBO;
import com.bjike.goddess.lendreimbursement.bo.*;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendSelectDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.*;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneShowStatus;
import com.bjike.goddess.lendreimbursement.to.*;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;

import java.util.List;

/**
 * 申请借款业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplyLendAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 申请借款列表总条数
     */
    default Long countApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取申请借款
     *
     * @return class ApplyLendBO
     */
    default ApplyLendBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 申请借款列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO addApplyLend(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editApplyLend(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteApplyLend(String id) throws SerException {
        return;
    }

    ;

    /**
     * 申请借款审核详情
     *
     * @return class ApplyLendBO
     */
    default ApplyLendBO getApplyLendDetail(String id) throws SerException {
        return null;
    }


    /**
     * 申请借款审核人员审核情况
     *
     * @return class LendAuditDetailBO
     */
    default List<LendAuditDetailBO> getLendAuditDetailByApplyLendId(String applyLendId) throws SerException {
        return null;
    }


    /**
     * 等待审核列表总条数
     */
    default Long countWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 等待审核列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 编辑等待审核列表
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 负责人审核等待审核列表
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editChargerWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 总经办审核等待审核
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editManageWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 运营商审核等待审核
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editOperateWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 运营商冻结
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editOperateCongel(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 负责人确认冻结
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editChargeSureCongel(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 负责人取消冻结
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editChargeConcelCongel(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }


    /**
     * 申请单有误列表总条数
     */
    default Long countApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 申请单有误列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 申请单有误编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editApplyError(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 申请单有误删除
     *
     * @param id id
     */
    default void deleteApplyError(String id) throws SerException {
        return;
    }

    ;

    /**
     * 申请单有误审核详情
     *
     * @return class ApplyLendBO
     */
    default ApplyLendBO getApplyApplyError(String id) throws SerException {
        return null;
    }

    /**
     * 申请单有误审核详情副本
     *
     * @return class ApplyLendBO
     */
    default ApplyLendBO getApplyApplyErrorCopy(String id) throws SerException {
        return null;
    }

    /**
     * 已审核/分析记录列表列表总条数
     */
    default Long countHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 已审核/分析记录列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 等待付款总条数
     */
    default Long countWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 等待付款列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }


    /**
     * 等待付款的付款编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editPayMoney(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 确认收款总条数
     */
    default Long countSureRecieve(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 确认收款列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listSureRecieveMoney(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 确认收款编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editSureRecieveMoney(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 借款记录总条数
     */
    default Long countBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 借款记录列表
     *
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 借款记录还款编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editReturnBorrowRecord(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }


    /**
     * 借款记录寄件编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editBorrowRecordSend(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 手机版还款
     *
     * @param phoneLendReturnSendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editPhoneReturn(PhoneLendReturnSendTO phoneLendReturnSendTO) throws SerException {
        return null;
    }

    /**
     * 手机版寄件
     *
     * @param phoneLendSendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editPhoneSend(PhoneLendSendTO phoneLendSendTO) throws SerException {
        return null;
    }

    /**
     * 借款记录生成记账凭证
     *
     * @param id 申请借款信息id
     * @return class AccountVoucherBO
     */
    default List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        return null;
    }

    /**
     * 还款记录总条数
     */
    default Long countReturn(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 还款记录
     *
     * @param applyLendDTO applyLendDTO
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listReturnMoneyRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }


    /**
     * 还款记录生成记账凭证
     *
     * @param id 申请借款信息id
     * @return class AccountVoucherBO
     */
    default List<AccountVoucherBO> listAccountVoucherByReturnMoney(String id) throws SerException {
        return null;
    }

    /**
     * 还款记录还款核对
     *
     * @param applyLendTO applyLendTO
     * @return class ApplyLendBO
     */
    default ApplyLendBO checkReturnMoney(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 手机版还款记录还款核对
     *
     * @param phoneLendReturnCheckTO applyLendTO
     * @return class ApplyLendBO
     */
    default ApplyLendBO phoneCheckReturn(PhoneLendReturnCheckTO phoneLendReturnCheckTO) throws SerException {
        return null;
    }

    /**
     * 网页版还款记录不通过编辑
     * 当不通过才去编辑，否则不可以编辑
     *
     * @param applyLendTO 申请借款信息
     * @return class ApplyLendBO
     */
    default ApplyLendBO editErrorReturn(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 帐务核对总条数
     */
    default Long countBusCheck(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }


    /**
     * 帐务核对记录
     *
     * @param applyLendDTO applyLendDTO
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listBusinessCheck(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 帐务核对收到单据确认
     *
     * @param applyLendTO applyLendTO
     * @return class ApplyLendBO
     */
    default ApplyLendBO checkTicket(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 已收票总条数
     */
    default Long countRecTicket(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 已收票记录
     *
     * @param applyLendDTO applyLendDTO
     * @return class ApplyLendBO
     */
    default List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 汇总借款人
     *
     * @param applyLendDTO applyLendDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectLender(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 汇总地区
     *
     * @param applyLendDTO applyLendDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectArea(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 汇总项目组
     *
     * @param applyLendDTO applyLendDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectProjectGroup(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 汇总项目名称
     *
     * @param applyLendDTO applyLendDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectProjectName(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }


    /**
     * 获取所有用户
     *
     * @return
     */
    default List<String> getAllUser() throws SerException {
        return null;
    }


    /**
     * 获取所有借款人
     *
     * @return
     */
    default List<String> listLender() throws SerException {
        return null;
    }

    /**
     * 获取所有地区
     *
     * @return
     */
    default List<String> listArea() throws SerException {
        return null;
    }

    /**
     * 手机端获取所有地区
     *
     * @return
     */
    default List<String> listPhoneArea() throws SerException {
        return null;
    }

    /**
     * 获取所有项目组
     *
     * @return
     */
    default List<String> listProjectGroup() throws SerException {
        return null;
    }

    /**
     * 手机端获取所有项目组
     *
     * @return
     */
    default List<String> listPhoneProjectGroup(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        return null;
    }

    /**
     * 获取所有项目名
     *
     * @return
     */
    default List<String> listProjectName() throws SerException {
        return null;
    }


    /**
     * 手机端获取所有项目名
     *
     * @return
     */
    default List<String> listPhoneProjectName(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        return null;
    }


    /**
     * 申请借款导出excel
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 待付款记录导出excel
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    byte[] waitingPayExcel(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 借款记录导出excel
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    byte[] borrowExcel(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 还款记录导出excel
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    byte[] returnExcel(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 已收票导出excel
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    byte[] receiveExcel(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 获取所有账户来源
     * chenjunhao
     *
     * @return
     */
    default List<String> listAccountCom() throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 等待付款导出cjh
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    List<ExportExcelTO> waitPayExport(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * chenjunhao
     * 等待付款列表
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    List<ApplyLendBO> listWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * chenjunhao
     * 付款
     *
     * @param applyLendTO
     * @return
     * @throws SerException
     */
    ApplyLendBO editPayMoneyCJH(ApplyLendTO applyLendTO) throws SerException;

    /**
     * chenjunhao
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    Long countWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException;

    /**
     * 判断手机端页面某些地方显示问题的权限
     *
     * @param lendPhoneShowStatus
     * @param lendId              借款id
     * @return
     * @throws SerException
     */
    Boolean phoneShowRight(LendPhoneShowStatus lendPhoneShowStatus, String lendId) throws SerException;

    /**
     * 手机版所有列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ApplyLendBO> listAll(PhoneApplyLendDTO dto) throws SerException {
        return null;
    }

    ;


    /**
     * 汇总个人周月年借款数据图
     *
     * @param reimburseShapeDTO
     * @return
     * @throws SerException
     */
    default ReimShapeAllVO collectSelfShape(ReimburseShapeDTO reimburseShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总任意两月的借款变化趋势图
     *
     * @param reimburseTrendShapeDTO
     * @return
     * @throws SerException
     */
    default ReimShapeMixVO collectSelfTrend(ReimburseTrendShapeDTO reimburseTrendShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司项目组时间段内的借款特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectGroupBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司项目时间段内的借款特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectProjectBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司地区时间段内的借款特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectAreaBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司地区时间段内的借款详情特定指标统计图
     *
     * @param reimburseShapeDetailDTO
     * @return
     * @throws SerException
     */
    default ReimShapeVO collectAreaDetailBar(LendShapeDetailDTO reimburseShapeDetailDTO) throws SerException {
        return null;
    }


    /**
     * 汇总个人借款和报销混合年和月和周的（申报报销/已报销/申请借款/已还款的借款）四种数据
     *
     * @param lendMixReimShapeDTO
     * @return
     * @throws SerException
     */
    default LendMixReimShapeVO collectMixMonAndWeek(LendMixReimSelfShapeDTO lendMixReimShapeDTO) throws SerException {
        return null;
    }

    /**
     * 汇总公司借款和报销混合年和月和周的（申报报销/已报销/申请借款/已还款的借款）四种数据
     *
     * @param lendMixCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default LendMixReimShapeVO collectMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        return null;
    }

    /**
     * 汇总详细公司借款和报销混合年和月和周的（申报报销/已报销/申请借款/已还款的借款）四种数据
     *
     * @param lendMixCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimShapeVO collectDetailMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        return null;
    }

    /**
     * 报销根据报销人跟二级科目获取一级科目和三级科目
     *
     * @param name
     * @return
     * @throws SerException
     */
    default SubjectDataBO findSubjects(String name) throws SerException {
        return null;
    }

    /**
     * 借款根据报销人跟二级科目获取一级科目和三级科目
     *
     * @param name
     * @return
     * @throws SerException
     */
    default SubjectDatasBO findSubjects1(String name) throws SerException {
        return null;
    }

    /**
     * 根据一级科目代码获取二级科目
     *
     * @param firstSubjectCode
     * @return
     * @throws SerException
     */
    default List<SecondSubjectDataBO> findSecondSubject(String firstSubjectCode) throws SerException {
        return null;
    }

    /**
     * 借款数据分析图
     *
     * @return
     * @throws SerException
     */
    default OptionBO analysisDiagram() throws SerException {
        return null;
    }

    /**
     * 分析
     *
     * @param applyLendTO
     * @return
     * @throws SerException
     */
    default ApplyLendBO analyse(ApplyLendTO applyLendTO) throws SerException {
        return null;
    }

    /**
     * 账户核对导出
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    default byte[] businessCheckOut(ApplyLendDTO applyLendDTO) throws SerException {
        return null;
    }

    /**
     * 获取所有人名
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllName() throws SerException {
        return null;
    }

    /**
     * 获取所有地区
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllArea() throws SerException {
        return null;
    }

    /**
     * 获取全部的项目组
     *
     * @return
     * @throws SerException
     */
    default List<String> findDepartment() throws SerException {
        return null;
    }

    /**
     * 获取全部的项目名称
     *
     * @return
     * @throws SerException
     */
    default List<String> findProject() throws SerException {
        return null;
    }

    /**
     * 提醒功能
     *
     * @throws SerException
     */
    default void sendEmail() throws SerException {
        return;
    }

    /**
     * 部门提醒功能
     *
     * @throws SerException
     */
    default void departmentEmail() throws SerException {
        return;
    }

    /**
     * 财务部提醒功能
     *
     * @throws SerException
     */
    default void finanEmail() throws SerException {
        return;
    }
}