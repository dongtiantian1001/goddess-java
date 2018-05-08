package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.bo.UserNameSexBO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.to.*;

import java.util.List;
import java.util.Set;

/**
 * 入职登记业务接口
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:48]
 * @Description: [入职登记业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface EntryRegisterSer extends Ser<EntryRegister, EntryRegisterDTO> {


    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 入职登记列表总条数
     */
    default Long countEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取入职登记
     *
     * @return class EntryRegisterBO
     */
    default EntryRegisterBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取所有入职登记
     *
     * @param entryRegisterDTO 入职登记dto
     * @return class entryRegisterBO
     * @throws SerException
     */
    default List<EntryRegisterBO> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工入职
     *
     * @param entryRegisterTO   员工入职数据to
     * @param familyMemberTO    家庭成员数据to
     * @param studyExperienceTO 学习经历数据to
     * @param workExperienceTO  工作经历数据to
     * @param credentialTO      证书情况数据to
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                                WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return null;
    }

    ;


    /**
     * 编辑员工入职
     *
     * @param entryRegisterTO   员工入职数据to
     * @param familyMemberTO    家庭成员数据to
     * @param studyExperienceTO 学习经历数据to
     * @param workExperienceTO  工作经历数据to
     * @param credentialTO      证书情况数据to
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO editEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                              WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return null;
    }

    ;


    /**
     * 根据id删除入职登记
     *
     * @param id
     * @throws SerException
     */
    default void removeEntryRegister(String id) throws SerException {
        return;
    }

    /**
     * 根据id查找某个员工入职登记
     *
     * @param id 员工入职登记id
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO getEntryRegisterDetail(String id) throws SerException {
        return null;
    }

    /**
     * 根据员工编号获取员工入职信息
     *
     * @param number
     * @return
     */
    default EntryRegisterBO getByNumber(String number) throws SerException {
        return null;
    }

    /**
     * 获取所有的qq号
     * lijuntao
     *
     * @throws SerException
     */
    default List<String> findQQ() throws SerException {
        return null;
    }

    /**
     * 获取所有的毕业学校
     * lijuntao
     *
     * @throws SerException
     */
    default List<String> findSchoolTag() throws SerException {
        return null;
    }

    /**
     * 获取所有的毕业时间
     * lijuntao
     *
     * @throws SerException
     */
    default List<String> findGraduationDate() throws SerException {
        return null;
    }

    /**
     * 获取全部的入职等级信息（不分页）
     * zhuangkaiqin
     */
    default List<EntryRegisterBO> list() throws SerException {
        return null;
    }

    /**
     * 根据姓名获取员工编号
     *
     * @param name
     * @return
     * @throws SerException
     */
    default String findEmpNum(String name) throws SerException {
        return null;
    }

    /**
     * 根据员工编号获取信息
     *
     * @param empNumer
     * @return
     */
    default EntryOptionBO getEntryOptionByEmpNum(String empNumer) throws SerException {

        return null;
    }

    /**
     * 根据姓名获取性别
     *
     * @param username
     * @return
     * @throws SerException
     */
    default String getGender(String username) throws SerException {
        return null;
    }

    /**
     * 根据条件查询不分页
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<EntryRegisterBO> map(EntryRegisterDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据姓名获取信息
     *
     * @param name
     * @return
     * @throws SerException
     */
    default List<EntryRegisterBO> getEntryRegisterByName(String name) throws SerException {
        return null;
    }

    /**
     * 根据姓名获取入职时间
     *
     * @param username
     * @return
     */
    default String getEntryTime(String username) throws SerException {
        return null;
    }

    /**
     * 获取所有的除了离职员工的编号
     *
     * @return
     */
    default List<String> findWorkingEmpNum() throws SerException {

        return null;
    }

    /**
     * 获取所有员工编号和对应的数据
     *
     * @return
     */
    default List<EntryOptionBO> findEmpDate() throws SerException {
        return null;
    }

    /**
     * 根据入职时间获取入职人数
     *
     * @return
     */
    default Integer findNumByEntryDate(String[] date, String area, String dep) throws SerException {
        return null;
    }

    /**
     * 根据入职时间获取入职人数
     *
     * @return
     */
    default Integer findNumByEntryDate(String endDate, String area, String dep) throws SerException {
        return null;
    }

    /**
     * 根据用户名获取对应性别
     *
     * @return
     */
    default List<UserNameSexBO> findSexByUserName(String[] userName) throws SerException {
        return null;
    }
    /**
     * 根据入职时间获取入职人数
     * @return
     */
    default Integer findNumByEntryDate(String dep) throws SerException{
        return null;
    }


    /**
     * chenjunhao
     * 获取所有入职人员的姓名
     *
     * @return
     * @throws SerException
     */
    Set<String> names() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param firmIntroTOS 公司简介
     */
//    void importExcel(List<FirmIntroTO> firmIntroTOS) throws SerException;

    /**
     * 我的团队
     *
     * @return
     * @throws SerException
     */
    default List<EntryRegisterBO> myTeam(EntryRegisterDTO dto) throws SerException {
        return null;
    }
}
