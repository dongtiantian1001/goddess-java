package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;

import java.util.List;

/**
 * 时间条件设置业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimeCriteriaSetAPI {

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
     * 根据id查询时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    TimeCriteriaSetBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 时间条件设置dto
     * @throws SerException
     */
    Long count(TimeCriteriaSetDTO dto) throws SerException;

    /**
     * 分页查询时间条件设置
     *
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    List<TimeCriteriaSetBO> list(TimeCriteriaSetDTO dto) throws SerException;

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException;

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @throws SerException
     */
    void update(TimeCriteriaSetTO to) throws SerException;

}