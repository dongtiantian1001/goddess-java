package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfare.entity.ThankStatement;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.ThankStatementTO;

import java.util.List;

/**
 * 感谢语业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ThankStatementSer extends Ser<ThankStatement, ThankStatementDTO> {
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
     * 新增感谢语
     *
     * @param to 感谢语
     * @return 感谢语
     */
    ThankStatementBO insertModel(ThankStatementTO to) throws SerException;

    /**
     * 编辑感谢语
     *
     * @param to 感谢语
     * @return 感谢语
     */
    ThankStatementBO updateModel(ThankStatementTO to) throws SerException;

    /**
     * 分页查询感谢语
     *
     * @param dto 分页条件
     * @return
     * @throws SerException
     */
    List<ThankStatementBO> pageList(ThankStatementDTO dto) throws SerException;

    /**
     * 冻结感谢语
     *
     * @param id 感谢语id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻感谢语
     *
     * @param id 感谢语id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 查询当前用户及共享的非冻结感谢语
     *
     * @return 感谢语结果集
     */
    List<ThankStatementBO> findAllStatement() throws SerException;

    /**
     * 列表总条数
     * @param dto
     * @throws SerException
     */
    Long count(ThankStatementDTO dto) throws SerException;


    /**
     * 根据id来查询单个数据
     * @param id
     * @throws SerException
     */
    ThankStatementBO findOne(String id) throws SerException;
}