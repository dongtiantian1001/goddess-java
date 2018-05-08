package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.excel.BusinessCourseImportExcel;
import com.bjike.goddess.marketdevelopment.service.BusinessCourseSer;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务方向科目业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessCourseApiImpl")
public class BusinessCourseApiImpl implements BusinessCourseAPI {

    @Autowired
    private BusinessCourseSer businessCourseSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessCourseSer.guidePermission(guidePermissionTO);
    }

    @Override
    public BusinessCourseBO save(BusinessCourseTO to) throws SerException {
        return businessCourseSer.save(to);
    }

    @Override
    public BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        return businessCourseSer.update(to);
    }

    @Override
    public BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
        return businessCourseSer.congeal(to);
    }

    @Override
    public BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
        return businessCourseSer.thaw(to);
    }

    @Override
    public BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
        return businessCourseSer.delete(to);
    }

    @Override
    public List<BusinessCourseBO> findByType(String type_id) throws SerException {
        return businessCourseSer.findByType(type_id);
    }

    @Override
    public List<BusinessCourseBO> findThaw() throws SerException {
        return businessCourseSer.findThaw();
    }

    @Override
    public BusinessCourseBO getById(String id) throws SerException {
        return businessCourseSer.getById(id);
    }

    @Override
    public List<BusinessCourseBO> maps(BusinessCourseDTO dto) throws SerException {
        return businessCourseSer.maps(dto);
    }

    @Override
    public Integer getTotal(BusinessCourseDTO dto) throws SerException {
        return Integer.valueOf(businessCourseSer.count(dto).toString());
    }

    @Override
    public List<String> getProjectName() throws SerException {
        return businessCourseSer.getProjectName();
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return businessCourseSer.templateExcel();
    }

    @Override
    public void upload(List<BusinessCourseImportExcel> tos) throws SerException {
        businessCourseSer.upload(tos);
    }

    @Override
    public byte[] exportExcel(BusinessCourseDTO dto) throws SerException {
        return businessCourseSer.exportExcel(dto);
    }

    @Override
    public List<String> findBusinessType() throws SerException {
        return businessCourseSer.findBusinessType();
    }

    @Override
    public List<String> findSubject(String businessType) throws SerException {
        return businessCourseSer.findSubject(businessType);
    }

    @Override
    public List<String> findAllSubject() throws SerException {
        return businessCourseSer.findAllSubject();
    }
}