package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffactivity.bo.ActivityApplyInforBO;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityApplyInforDTO;
import com.bjike.goddess.staffactivity.entity.ActivityApplyInfor;
import com.bjike.goddess.staffactivity.to.ActivityApplyInforTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.SonPermissionObject;

import java.util.List;

/**
 * 活动申请信息业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityApplyInforSer extends Ser<ActivityApplyInfor, ActivityApplyInforDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 分页查询活动申请信息
     *
     * @param dto 活动申请信息dto
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    List<ActivityApplyInforBO> list(ActivityApplyInforDTO dto) throws SerException;

    /**
     * 保存活动申请信息
     *
     * @param to 活动申请信息to
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    ActivityApplyInforBO save(ActivityApplyInforTO to) throws SerException;

    /**
     * 根据id删除活动申请信息
     *
     * @param id 活动申请信息唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动申请信息
     *
     * @param to 活动申请信息to
     * @throws SerException
     */
    void update(ActivityApplyInforTO to) throws SerException;

    /**
     * 参与该活动
     *
     * @param id 活动申请信息唯一标识
     * @param area 地区
     * @throws SerException
     */
    void joinActivity(String id, String area) throws SerException;

    /**
     * 退出该活动
     *
     * @param id 活动申请信息唯一标识
     * @param abandonReason 放弃参与活动原因
     * @throws SerException
     */
    void exitActivity(String id, String abandonReason) throws SerException;

    /**
     * 查看活动人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    List<ActivityStaffListBO> checkStaffList(String id) throws SerException;

    /**
     * 查看参与了活动并且没有退出活动的人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    List<ActivityStaffListBO> checkAttendList(String id) throws SerException;

}