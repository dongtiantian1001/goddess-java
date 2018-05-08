package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研需求业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyDemandAPI {


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
     * 保存
     *
     * @param to 调研需求传输对象
     * @return
     * @throws SerException
     */
    default SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研需求传输对象
     * @return
     * @throws SerException
     */
    default SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研需求数据id
     * @return
     * @throws SerException
     */
    default SurveyDemandBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 关闭
     *
     * @param to 关闭需求传输对象
     * @return
     * @throws SerException
     */
    default SurveyDemandBO close(CloseDemandTO to) throws SerException {
        return null;
    }

    /**
     * 根据状态查询调研需求
     *
     * @param status 调研状态
     * @return
     * @throws SerException
     */
    default List<SurveyDemandBO> findByStatus(SurveyStatus status) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研需求数据传输对象
     * @return
     * @throws SerException
     */
    default List<SurveyDemandBO> maps(SurveyDemandDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研需求数据
     *
     * @param id 调研需求数据id
     * @return
     * @throws SerException
     */
    default SurveyDemandBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 获取调研需求id
     */
    default List<String> getDemandId() throws SerException {
        return null;
    }
}