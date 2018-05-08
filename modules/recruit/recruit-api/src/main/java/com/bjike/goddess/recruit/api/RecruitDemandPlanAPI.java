package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.*;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.to.ChannelTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandPlanTO;

import java.util.List;

/**
 * 招聘需求与计划业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecruitDemandPlanAPI {
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
     * 招聘需求与计划总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(RecruitDemandPlanDTO dto) throws SerException;

    /**
     * 根据id获取招聘需求与计划
     *
     * @param id
     * @return
     * @throws SerException
     */
    RecruitDemandPlanBO getId(String id) throws SerException;

    /**
     * 分页查询招聘计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitDemandPlanBO> list(RecruitDemandPlanDTO dto) throws SerException;

    /**
     * 保存招聘计划
     *
     * @param to
     * @return
     * @throws SerException
     */
    RecruitDemandPlanBO save(RecruitDemandPlanTO to) throws SerException;

    /**
     * 根据id删除招聘计划
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘计划
     *
     * @param to
     * @throws SerException
     */
    RecruitDemandPlanBO update(RecruitDemandPlanTO to) throws SerException;

    /**
     * 制定招聘计划
     *
     * @param to
     * @return
     * @throws SerException
     */
    RecruitDemandPlanBO makePlan(RecruitDemandPlanTO to) throws SerException;
    /**
     * 招聘管理日汇总
     *
     * @param time
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> dayCollect(String time) throws SerException;

    /**
     * 招聘管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException;

    /**
     * 招聘管理月汇总
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> monthCollect(Integer year, Integer month) throws SerException;

    /**
     * 招聘管理季度汇总
     *
     * @param year
     * @param quarter
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException;

    /**
     * 招聘管理年汇总
     *
     * @param year
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> yearCollect(Integer year) throws SerException;

    /**
     * 招聘管理累计汇总
     *
     * @param time
     * @return
     * @throws SerException
     */
    List<RecruitProgressBO> totalCollect(String time) throws SerException;
    /**
     * 招聘详情日汇总
     *
     * @param time
     * @return
     * @throws SerException
     */
    List<RecruitDetailsBO> dayRecruit(String time) throws SerException;

    /**
     * 招聘详情周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    List<RecruitDetailsBO> weekRecruit(Integer year, Integer month, Integer week) throws SerException;

    /**
     * 招聘详情月汇总
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    List<RecruitDetailsBO> monthRecruit(Integer year, Integer month) throws SerException;

    /**
     * 渠道汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    OptionBO channelCollect(ChannelTO to) throws SerException;
    /**
     * 面试率图表
     *
     * @return
     * @throws SerException
     */
    OptionPieBO faceFigure(ChannelTO to) throws SerException;

    /**
     * 入职率图表
     *
     * @return
     * @throws SerException
     */
    OptionPieBO entryFigure(ChannelTO to) throws SerException;
    /**
     * 获取所有岗位
     *
     * @return
     * @throws SerException
     */
    List<String> getPosition() throws SerException;

}