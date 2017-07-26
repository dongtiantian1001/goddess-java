package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.entity.AnnualInfo;
import com.bjike.goddess.annual.to.AnnualInfoTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 年假信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualInfoSer extends Ser<AnnualInfo, AnnualInfoDTO> {

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
     * 获取指定用户的年假信息
     *
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 根据用户名查询年假信息
     *
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> findByUsers(String... username) throws SerException {
        return null;
    }

    /**
     * 生成年假信息数据
     *
     * @throws SerException
     */
    void generate() throws SerException;


    /**
     * 列表
     *
     * @param dto 年假信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<AnnualInfoBO> maps(AnnualInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取年假信息数据
     *
     * @param id 年假信息数据id
     * @return
     * @throws SerException
     */
    default AnnualInfoBO getById(String id) throws SerException {
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
     * 为体验提供保存年假信息数据
     *
     * @param to 保存年假信息传输对象
     * @return
     * @throws SerException
     */
    default AnnualInfoBO save(AnnualInfoTO to) throws SerException {
        return null;
    }
}