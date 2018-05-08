package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingLayBO;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingLayTO;

import java.util.List;

/**
 * 会议层面业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingLayAPI {

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
     * 新增会议层面
     *
     * @param to 会议层面
     * @return 会议层面
     */
    MeetingLayBO add(MeetingLayTO to) throws SerException;

    /**
     * 编辑会议层面
     *
     * @param to 会议层面
     * @return 会议层面
     */
    MeetingLayBO edit(MeetingLayTO to) throws SerException;

    /**
     * 删除会议层面
     *
     * @param id 会议层面ID
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingLayBO> pageList(MeetingLayDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(MeetingLayDTO dto) throws SerException;

    /**
     * 根据id查询会议层面
     *
     * @param id 会议层面id
     * @return 会议层面
     */
    MeetingLayBO findById(String id) throws SerException;

    /**
     * 查询所有岗位
     */
    String[] findPosition() throws SerException;

}