package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FailPhoneReasonAPI {
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
     * 根据id查询未成功通话原因
     *
     * @param id 未成功通话原因唯一标识
     * @return class FailPhoneReasonBO
     * @throws SerException
     */
    FailPhoneReasonBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 未成功通话原因dto
     * @throws SerException
     */
    Long count(FailPhoneReasonDTO dto) throws SerException;

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
