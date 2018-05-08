package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.FinanceAttendanceBO;
import com.bjike.goddess.attendance.bo.FinanceCountBO;
import com.bjike.goddess.attendance.bo.FinanceCountFieldBO;
import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.entity.FinanceAttendance;
import com.bjike.goddess.attendance.entity.PageUtils;
import com.bjike.goddess.attendance.to.FinanceAttendanceTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 财务出勤表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinanceAttendanceSer extends Ser<FinanceAttendance, FinanceAttendanceDTO> {
    Boolean sonPermission() throws SerException;
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 财务出勤列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceAttendanceBO> list(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 申请反馈审批
     *
     * @param to
     * @throws SerException
     */
    void apply(FinanceAttendanceTO to) throws SerException;

    /**
     * 审核列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceAttendanceBO> aduitList(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 审核列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long aduitListNum(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    FinanceAttendanceBO one(String id) throws SerException;

    /**
     * 审核
     *
     * @param to
     * @throws SerException
     */
    void audit(FinanceAttendanceTO to) throws SerException;

    /**
     * 汇总详细字段信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceCountFieldBO> fields(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 财务出勤表汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceCountBO> financeCount(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 获取某人当天的请假时长
     *
     * @param name
     * @param date
     * @return
     * @throws SerException
     */
    Double vacateDay(String name, String date) throws SerException;


    /**
     * 导出数据
     *
     * @param dto
     * @throws SerException
     */
    default ByteArrayOutputStream excelExport(FinanceAttendanceDTO dto) throws SerException {
        return null;
    }

    /**
     * 新方式
     * @param pageNum
     * @param pageSize
     * @return
     * @throws SerException
     */
    default PageUtils findAll(String pageNum, String pageSize, String name)throws SerException {
        return  null;
    }

    void save(FinanceAttendanceDTO dto) throws SerException;
    void delete(String[] ids) throws SerException;
    void Update(FinanceAttendanceDTO dto) throws SerException;


}