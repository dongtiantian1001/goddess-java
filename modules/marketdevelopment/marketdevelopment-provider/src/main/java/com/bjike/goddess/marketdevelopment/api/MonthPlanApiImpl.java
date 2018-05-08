//package com.bjike.goddess.marketdevelopment.api;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.marketdevelopment.bo.MonthPlanBO;
//import com.bjike.goddess.marketdevelopment.bo.MonthPlanChoiceBO;
//import com.bjike.goddess.marketdevelopment.dto.MonthPlanDTO;
//import com.bjike.goddess.marketdevelopment.service.MonthPlanSer;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 月计划业务接口实现
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 06:41 ]
// * @Description: [ 月计划业务接口实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Service("monthPlanApiImpl")
//public class MonthPlanApiImpl implements MonthPlanAPI {
//
//    @Autowired
//    private MonthPlanSer monthPlanSer;
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return monthPlanSer.guidePermission(guidePermissionTO);
//    }
//
//    @Override
//    public MonthPlanBO save(MonthPlanTO to) throws SerException {
//        return monthPlanSer.save(to);
//    }
//
//    @Override
//    public MonthPlanBO update(MonthPlanTO to) throws SerException {
//        return monthPlanSer.update(to);
//    }
//
//    @Override
//    public MonthPlanBO delete(MonthPlanTO to) throws SerException {
//        return monthPlanSer.delete(to);
//    }
//
//    @Override
//    public List<MonthPlanBO> findByYearID(String year_id) throws SerException {
//        return monthPlanSer.findByYearID(year_id);
//    }
//
//    @Override
//    public List<MonthPlanBO> findByYear(Integer year) throws SerException {
//        return monthPlanSer.findByYear(year);
//    }
//
//    @Override
//    public MonthPlanBO getById(String id) throws SerException {
//        return monthPlanSer.getById(id);
//    }
//
//    @Override
//    public List<MonthPlanBO> maps(MonthPlanDTO dto) throws SerException {
//        return monthPlanSer.maps(dto).stream()
//                .sorted(Comparator.comparing(MonthPlanBO::getYearNumber).reversed()
//                        .thenComparing(MonthPlanBO::getMonth))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Integer getTotal() throws SerException {
//        return monthPlanSer.findAll().size();
//    }
//
//    @Override
//    public List<MonthPlanChoiceBO> getChoice() throws SerException {
//        return monthPlanSer.getChoice();
//    }
//
//    @Override
//    public List<MonthPlanBO> findByType(String type) throws SerException {
//        return monthPlanSer.findByType(type);
//    }
//
//    @Override
//    public byte[] exportExcel(CollectTO to) throws SerException {
//        return monthPlanSer.exportExcel(to);
//    }
//}