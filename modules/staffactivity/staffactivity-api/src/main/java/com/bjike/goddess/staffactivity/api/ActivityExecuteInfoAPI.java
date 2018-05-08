package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 活动执行信息业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityExecuteInfoAPI {
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
     * 根据id查询活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    ActivityExecuteInfoBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 活动执行信息dto
     * @throws SerException
     */
    Long count(ActivityExecuteInfoDTO dto) throws SerException;

    /**
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    List<ActivityExecuteInfoBO> list(ActivityExecuteInfoDTO dto) throws SerException;

    /**
     * 保存活动执行信息
     *
     * @param to 活动执行信息to
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    ActivityExecuteInfoBO save(ActivityExecuteInfoTO to) throws SerException;

    /**
     * 根据id删除活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动执行信息
     *
     * @param to 活动执行信息to
     * @throws SerException
     */
    void update(ActivityExecuteInfoTO to) throws SerException;

    /**
     * 定时查看
     *
     * @throws SerException
     */
    void send() throws SerException;

    /**
     * 查找所有活动方案
     *
     * @return
     * @throws SerException
     */
    Set<String> allActivityScheme() throws SerException;
}