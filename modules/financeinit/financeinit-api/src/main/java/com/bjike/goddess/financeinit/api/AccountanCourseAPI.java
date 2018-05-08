package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.*;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

import java.util.List;

/**
 * 会计科目业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountanCourseAPI {
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
     * 会计科目列表总条数
     */
    default Long countCourse(AccountanCourseDTO accountanCourseDTO, CategoryName belongCategory) throws SerException {
        return null;
    }

    /**
     * 根据id获取会计科目
     *
     * @return class AccountanCourseBO
     */
    default AccountanCourseBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 会计科目列表
     *
     * @return class AccountanCourseBO
     */
    default List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO, CategoryName belongCategory) throws SerException {
        return null;
    }

    /**
     * 根据会计科目名称查询所属类型
     */
    default CategoryName belongByName(String accountanName) throws SerException {
        return null;
    }

    /**
     * 根据代码获取信息
     *
     * @param code
     * @return
     * @throws SerException
     */
    default CourseDateBO findByCode(String code) throws SerException {
        return null;
    }

    /**
     * 根据一级会计科目id获取二级会计科目
     *
     * @param id 会计科目id
     * @return
     * @throws SerException
     */
    default List<AccountanCourseBO> findSendSubjectByOne(String id) throws SerException {
        return null;
    }

    /**
     * 根据二级会计科目id获取三级会计科目
     *
     * @param id 会计科目id
     * @return
     * @throws SerException
     */
    default List<AccountanCourseBO> findThirdSubjectBySend(String id) throws SerException {
        return null;
    }


    /**
     * 根据一级科目id获取所有对应的二级会计科目名称
     *
     * @param id
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findSendNameByOne(String id) throws SerException {
        return null;
    }

    /**
     * 根据一级科目id获取所有对应的二级会计科目名称
     *
     * @param id
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findThirdNameBySend(String id) throws SerException {
        return null;
    }

    /**
     * 获取所有的会计科目名称和对应的代码
     *
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findNameCode() throws SerException {
        return null;
    }

    /**
     * 获取所有的一级科目名称和对应的代码
     *
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findFirstNameCode() throws SerException {
        return null;
    }

    /**
     * 一级科目添加
     *
     * @param accountanCourseTO 会计科目
     * @return class AccountanCourseBO
     */
    default AccountanCourseBO addOneCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }

    /**
     * 二级科目添加
     *
     * @param accountanCourseTO 会计科目
     * @return class AccountanCourseBO
     */
    default AccountanCourseBO addSendCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }
    /**
     * 三级科目添加
     *
     * @param accountanCourseTO 会计科目
     * @return class AccountanCourseBO
     */
    default AccountanCourseBO addThreeCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param accountanCourseTO 会计科目
     * @return class CompanyBasicInfoBO
     */
    default AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 会计科目id
     */
    default void deleteCourse(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel(CategoryName belongCategory) throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param tos 会计科目
     */
    void importExcel(List<AccountanCourseExport> tos) throws SerException;

    /**
     * 根据一级科目代码获取二级科目名称
     * zhuangkaiqin
     */
    default List<AccountAddDateBO> findSecondName(String code) throws SerException {
        return null;
    }

    /**
     * 根据二级科目代码获取三级科目
     * zhuangkaiqin
     */
    default List<AccountAddDateBO> findThirdName(String secondCode) throws SerException {
        return null;
    }

    /**
     * 根据报销人跟二级科目获取一级科目和三级科目
     *
     * @param name
     * @return
     * @throws SerException
     */
    default SubjectDataBO findSubjects(String name) throws SerException {
        return null;
    }

    /**
     * 借款根据报销人跟二级科目获取一级科目和三级科目
     *
     * @param name
     * @return
     * @throws SerException
     */
    default SubjectDatasBO findSubjects1(String name) throws SerException {
        return null;
    }

    /**
     * 根据一级科目代码获取二级科目
     *
     * @param firstSubjectCode
     * @return
     * @throws SerException
     */
    default List<SecondSubjectDataBO> findSecondSubject(String firstSubjectCode) throws SerException {
        return null;
    }
    /**
     * 获取一级科目为固定资产的所有对应的二级科目
     *
     * @return
     * @throws SerException
     */
    default List<String> findByFixedAssets() throws SerException {
        return null;
    }
    /**
     * 根据科目名称获取代码
     *
     * @return
     * @throws SerException
     */
    default String findByCourseName(String courseName) throws SerException {
        return null;
    }
    /**
     * 获取折旧费用科目
     *
     * @return
     * @throws SerException
     */
    default List<String> findDepreciationAccount() throws SerException {
        return null;
    }
    /**
     * 获取税金科目
     *
     * @return
     * @throws SerException
     */
    default String findtaxSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目名称获取三级科目名称
     *
     * @param firstSubject
     * @return
     * @throws SerException
     */
    default List<String> findByFirstName(String firstSubject) throws SerException {
        return null;
    }
}