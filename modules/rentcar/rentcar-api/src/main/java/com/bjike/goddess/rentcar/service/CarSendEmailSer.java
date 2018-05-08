package com.bjike.goddess.rentcar.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.rentcar.bo.CarSendEmailBO;
import com.bjike.goddess.rentcar.dto.CarSendEmailDTO;
import com.bjike.goddess.rentcar.entity.CarSendEmail;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;

import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CarSendEmailSer extends Ser<CarSendEmail, CarSendEmailDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 发送邮箱方法
     * @throws SerException
     */
    void sendEmail() throws SerException;

    /**
     * 发送邮件提醒
     */
    void sendEmailRemind() throws SerException;

    /**
     * 获取项目组信息
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findDepartMent() throws SerException{
        return null;
    }

    /**
     * 根据部门id来查询该部门所有岗位
     * @param id
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findPosition(String id) throws SerException{
        return null;
    }

    /**
     * 添加项目经理和商务人员
     * @param to
     * @return
     * @throws SerException
     */
    default CarSendEmailBO add(CarSendEmailTO to) throws SerException{
        return null;
    }

    /**
     * 查询发送对象的部门和岗位人员
     * @return
     * @throws SerException
     */
    default List<CarSendEmailBO> list() throws SerException{
        return null;
    }

    /**
     * 修改商务人员和项目经理
     * @param to
     * @return
     * @throws SerException
     */
    default CarSendEmailBO edit(CarSendEmailTO to) throws SerException{
        return null;
    }


    /**
     * 总条数
     * @param dto
     * @throws SerException
     */
    Long count(CarSendEmailDTO dto) throws SerException;


    /**
     * 根据id来查询单条发送对象的数据
     * @param id
     * @throws SerException
     */
    CarSendEmailBO findOne(String id) throws SerException;


    /**
     * 删除
     */
    void delete(String id) throws SerException;
}