package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.*;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.dto.SearchDTO;
import com.bjike.goddess.contacts.to.CollectTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.organize.bo.InternalContactsConditionBO;

import java.util.List;

/**
 * 内部通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InternalContactsAPI {


    /**
     * 保存
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO save(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO update(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO delete(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 查询邮箱不为空数据
     *
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> findEmailNotNull() throws SerException {
        return null;
    }

    /**
     * 根据用户ID查询通讯录
     *
     * @param user_id 用户ID
     * @return
     * @throws SerException
     */
    default InternalContactsBO findByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 内部通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 通讯录信息确认邮件
     *
     * @throws SerException
     */
    void sendEmail() throws SerException;

    /**
     * 根据id获取内部通讯录数据
     *
     * @param id 内部通讯录数据id
     * @return
     * @throws SerException
     */
    default InternalContactsBO getById(String id) throws SerException {
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
     */
    InternalContactsBO importExcel(List<InternalContactsTO> tocs) throws SerException;

    /**
     * 定时器检测并删除离职员工通讯录
     */
    void checkDimissionInfo() throws SerException;

    /**
     * 获得入职人员的姓名
     */
    List<NameAndIdBO> getUserName() throws SerException;

    default byte[] templateExport() throws SerException {
        return null;
    }

    /**
     * 根据用户名字获取邮箱
     */
    default List<String> getEmails(String[] names) throws SerException {
        return null;
    }

    /**
     * 根据单个名字获取邮箱
     */
    default String getEmail(String name) throws SerException {
        return null;
    }

    /**
     * 移动端获取列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<MobileInternalContactsBO> mobileList(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据姓名获取个人信息
     *
     * @param name
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> getInfoByName(String name) throws SerException {
        return null;
    }

    /**
     * 移动端获取根据姓名获取所有电话号码
     *
     * @return class PhoneNumberBO
     * @throws SerException
     */
    default List<PhoneNumberBO> mobileGetTel() throws SerException {
        return null;
    }

    /**
     * 移动端总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getMobileTotal(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id 获取移动端数据
     *
     * @param id
     * @return
     */
    default MobileInternalContactsBO findByMobileID(String id) throws SerException {
        return null;
    }

    default void test(List<InternalContactsTO> tocs) throws SerException {
        return;
    }

    /**
     * 根据姓名获取地区,员工编号,职位,部门
     *
     * @param name
     * @return
     * @throws SerException
     */
    default InternalContactsConditionBO getByName(String name) throws SerException {
        return null;
    }

    /**
     * 通讯录信息管理日汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<ContactsCollectBO> dayCollect(CollectTO to) throws SerException;

    /**
     * 通讯录信息管理周汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<ContactsCollectBO> weekCollect(CollectTO to) throws SerException;

    /**
     * 通讯录信息管理月汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<ContactsCollectBO> monthCollect(CollectTO to) throws SerException;

    /**
     * 通讯录信息管理累计汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<ContactsCollectBO> totalCollect(CollectTO to) throws SerException;

    /**
     * 员工信息管理日汇总图表
     *
     * @param to
     * @return
     * @throws SerException
     */
    OptionBO dayCollectFigure(CollectTO to) throws SerException;

    /**
     * 员工信息管理周汇总图表
     *
     * @param to
     * @return
     * @throws SerException
     */
    OptionBO weekCollectFigure(CollectTO to) throws SerException;

    /**
     * 员工信息管理月汇总图表
     *
     * @param to
     * @return
     * @throws SerException
     */
    OptionBO monthCollectFigure(CollectTO to) throws SerException;

    /**
     * 员工信息管理累计汇总图表
     *
     * @param to
     * @return
     * @throws SerException
     */
    OptionBO totalCollectFigure(CollectTO to) throws SerException;

    /**
     * 检测邮箱是否通过
     *
     * @throws SerException
     */
    default void checkEmail() throws SerException {
    }

    /**
     * 查询部门下所有人的信息
     *
     * @return class MobileContactsBO
     * @throws SerException
     */
    default List<MobileContactsBO> mobileInfoByDepartment() throws SerException {
        return null;
    }

    /**
     * 全局搜索
     *
     * @return class MobileSearchBO
     * @throws SerException
     */
    default List<MobileSearchBO> mobileSearch(SearchDTO dto) throws SerException {
        return null;
    }

}