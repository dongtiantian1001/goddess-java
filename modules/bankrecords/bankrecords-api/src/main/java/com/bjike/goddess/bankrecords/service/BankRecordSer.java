package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.bo.*;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.io.InputStream;
import java.util.List;

/**
 * 银行流水业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankRecordSer extends Ser<BankRecord, BankRecordDTO> {

    /**
     * 检查导入的Excel标题
     *
     * @version v1
     */
    List<ExcelTitleBO> check(List<InputStream> streamList) throws SerException;

    /**
     * 导入银行流水
     *
     * @param to 银行流水
     * @return 银行流水
     */
    void upload(BankRecordTO to) throws SerException;

    /**
     * 汇总
     *
     * @param
     * @return class
     * @version v1
     */
    Long count(BankRecordDTO dto) throws SerException;

    /**
     * 列表
     *
     * @param dto 分页条件或查询条件
     * @return 列表结果集
     */
    List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException;

    /**
     * 删除银行流水记录
     *
     * @param id 银行流水id
     */
    void delete(String id) throws SerException;

    /**
     * 根据id查询银行流水
     *
     * @param id 银行流水id
     * @return 银行流水信息
     */
    BankRecordBO find(String id) throws SerException;

    /**
     * 汇总
     *
     * @param year        年份
     * @param month       月份
     * @param accountIds 账户名称ID
     * @return 汇总结果集
     */
    List<BankRecordCollectBO> collect(Integer year, Integer month, String[] accountIds) throws SerException;

    /**
     * 分析
     *
     * @param year        年份
     * @param month       月份
     * @param accountIds 账户名称ID
     * @return 分析结果
     */
    List<BankRecordAnalyzeBO> analyze(Integer year, Integer month, String[] accountIds) throws SerException;

    /**
     * 对比分析
     * @param year 年份
     * @param month 月份
     * @return
     */
    BankRecordCompareBO compare(Integer year, Integer month) throws SerException;

    /**
     * 根据账号查询
     * @param year
     * @param month
     * @param number
     * @return
     * @throws SerException
     */
    List<BankRecordBO> findByCondition(Integer year, Integer month, String number) throws SerException;

    List<BankRecordCollectBO> collectByCondition(Integer year, Integer month, String number) throws SerException;

    Boolean guidePermission(GuidePermissionTO to) throws SerException;

    /**
     * 查询所有银行的余额
     * @param startTime 年份
     * @param endTime 月份
     * @return 余额
     */
    Double balanceByMonth(String startTime,String endTime) throws SerException;

    Boolean sonPermission() throws SerException;

    byte[] collectExcel(Integer year, Integer month, String[] accountIds) throws SerException;

    byte[] analyzeExcel(Integer year, Integer month, String[] accountIds) throws SerException;
}