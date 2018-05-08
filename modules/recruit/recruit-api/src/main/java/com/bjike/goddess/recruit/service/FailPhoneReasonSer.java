package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FailPhoneReasonSer extends Ser<FailPhoneReason, FailPhoneReasonDTO> {
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
     * 分页查询未成功通话原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException;

    /**
     * 保存未成功通话原因
     *
     * @param failPhoneReasonTO
     * @return
     * @throws SerException
     */
    FailPhoneReasonBO save(FailPhoneReasonTO failPhoneReasonTO) throws SerException;

    /**
     * 根据id删除未成功通话原因
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新未成功通话原因
     *
     * @param failPhoneReasonTO
     * @throws SerException
     */
    void update(FailPhoneReasonTO failPhoneReasonTO) throws SerException;

    /**
     * 查找所有未成功通话原因
     *
     * @return
     * @throws SerException
     */
    Set<String> allReason() throws SerException;
}
