package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.dto.AttainmentTypeDTO;
import com.bjike.goddess.attainment.excel.SonPermissionObject;
import com.bjike.goddess.attainment.to.AttainmentTypeTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:49 ]
 * @Description: [ 调研类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttainmentTypeAPI {

    /**
     * 保存
     *
     * @param to 调研类型传输对象
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO save(AttainmentTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研类型传输对象
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO update(AttainmentTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研类型数据id
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 调研类型数据id
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 调研类型数据id
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<AttainmentTypeBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 查询定期或不定期数据
     *
     * @param regular 定期或不定期
     * @return
     * @throws SerException
     */
    default List<AttainmentTypeBO> findRegular(Boolean regular) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<AttainmentTypeBO> maps(AttainmentTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研类型数据
     *
     * @param id 调研类型数据id
     * @return
     * @throws SerException
     */
    default AttainmentTypeBO getById(String id) throws SerException {
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
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
}