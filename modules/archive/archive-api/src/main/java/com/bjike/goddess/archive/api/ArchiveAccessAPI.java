package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 档案调阅业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArchiveAccessAPI {

    /**
     * 保存
     *
     * @param to 档案调阅传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 档案调阅传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 档案调阅数据对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 审核
     *
     * @param to 档案调阅审核传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO audit(AccessAuditTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 档案调阅数据传输对象
     * @return
     * @throws SerException
     */
    default List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(ArchiveAccessDTO dto) throws SerException {
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

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(ArchiveAccessDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
     * @throws SerException
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }


    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void  upload(List<ArchiveAccessTO> tos) throws SerException {
        return;
    }

    /**
     * 导出时获取的姓名
     *
     * @return
     * @throws SerException
     */
    default List<String> findUserName() throws SerException {
        return null;
    }
}