package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.QQGroupBO;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.QQGroupTO;

import java.util.List;

/**
 * QQ群管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QQGroupAPI {

    /**
     * 保存
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO save(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO update(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO delete(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 关闭QQ群
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO close(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto QQ群管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<QQGroupBO> maps(QQGroupDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取QQ群数据
     *
     * @param id QQ群数据id
     * @return
     * @throws SerException
     */
    default QQGroupBO getById(String id) throws SerException {
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
     * 导入
     *
     * @param qqGroupTO QQ群管理
     * @return class QQGroupBO
     */
    default QQGroupBO importExcel(List<QQGroupTO> qqGroupTO) throws SerException {
        return null;
    }

    default byte[] templateExport() throws SerException {
        return null;
    }
}