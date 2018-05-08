package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.to.CommonalityTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;

import java.util.List;

/**
 * 公共邮箱管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommonalityAPI {

    /**
     * 保存
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO save(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO update(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO delete(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO congeal(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO thaw(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return
     * @throws SerException
     */
    default List<CommonalityBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表查询
     *
     * @param dto 公共邮箱管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        return null;
    }

    default List<CommonalityBO> findAll() throws SerException {
        return null;
    }

    /**
     * 根据部门ID查询公共邮箱
     *
     * @param department 部门ID
     * @return
     * @throws SerException
     */
    default CommonalityBO findByDepartment(String department) throws SerException {
        return null;
    }


    /**
     * 根据id获取公共邮箱数据
     *
     * @param id 公共邮箱数据id
     * @return
     * @throws SerException
     */
    default CommonalityBO getById(String id) throws SerException {
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
     * @param tocs 公共邮箱管理
     * @return class CommonalityTO
     */
    default void importExcel(List<CommonalityTO> tocs) throws SerException {

    }

    default byte[] templateExport() throws SerException {
        return null;
    }

    /**
     * 获取未冻结的公邮邮件List<String>
     *
     * @return
     * @throws SerException
     */
    default List<String> getEmails() throws SerException {
        return null;
    }

    /**
     * 获取部门项目组
     *
     * @return
     * @throws SerException
     */
    default List<String> findDepartment() throws SerException {
        return null;
    }
}