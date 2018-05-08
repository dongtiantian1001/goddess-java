//package com.bjike.goddess.marketdevelopment.api;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanChoiceBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanCollectBO;
//import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
//import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
//import com.bjike.goddess.marketdevelopment.service.YearPlanSer;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 年计划业务接口实现
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 05:57 ]
// * @Description: [ 年计划业务接口实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Service("yearPlanApiImpl")
//public class YearPlanApiImpl implements YearPlanAPI {
//
//    @Autowired
//    private YearPlanSer yearPlanSer;
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return yearPlanSer.guidePermission(guidePermissionTO);
//    }
//
//    @Override
//    public YearPlanBO save(YearPlanTO to) throws SerException {
//        return yearPlanSer.save(to);
//    }
//
//    @Override
//    public YearPlanBO update(YearPlanTO to) throws SerException {
//        return yearPlanSer.update(to);
//    }
//
//    @Override
//    public YearPlanBO delete(YearPlanTO to) throws SerException {
//        return yearPlanSer.delete(to);
//    }
//
//    @Override
//    public List<YearPlanBO> findThisYear() throws SerException {
//        return yearPlanSer.findThisYear();
//    }
//
//    @Override
//    public List<YearPlanBO> findByYear(Integer year) throws SerException {
//        return yearPlanSer.findByYear(year);
//    }
//
//    @Override
//    public YearPlanBO getById(String id) throws SerException {
//        return yearPlanSer.getById(id);
//    }
//
//    @Override
//    public List<YearPlanBO> maps(YearPlanDTO dto) throws SerException {
//        return yearPlanSer.maps(dto);
//    }
//
//    @Override
//    public Integer getTotal() throws SerException {
//        return yearPlanSer.getTotal();
//    }
//
//    @Override
//    public List<YearPlanChoiceBO> getChoice() throws SerException {
//        return yearPlanSer.getChoice();
//    }
//
//    @Override
//    public byte[] exportExcel(CollectTO to) throws SerException {
//        return yearPlanSer.exportExcel(to);
//    }
//
//    @Override
//    public List<YearPlanBO> findByType(String type) throws SerException {
//        return yearPlanSer.findByType(type);
//    }
//
//    @Override
//    public List<SonPermissionObject> sonPermission() throws SerException {
//        return yearPlanSer.sonPermission();
//    }
//
//    @Override
//    public List<YearPlanCollectBO> collect() throws SerException {
//        return yearPlanSer.collect();
//    }
//}