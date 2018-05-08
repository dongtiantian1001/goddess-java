package com.bjike.goddess.rentcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentcar.bo.DriverRecruitBO;
import com.bjike.goddess.rentcar.dto.DriverRecruitDTO;
import com.bjike.goddess.rentcar.service.DriverRecruitSer;
import com.bjike.goddess.rentcar.to.DriverRecruitTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 司机招聘信息业务接口实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:28 ]
 * @Description: [ 司机招聘信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("driverRecruitApiImpl1")
public class DriverRecruitApiImpl implements DriverRecruitAPI {

    @Autowired
    private DriverRecruitSer driverRecruitSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return driverRecruitSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return driverRecruitSer.guidePermission(guidePermissionTO);
    }

    @Override
    public DriverRecruitBO save(DriverRecruitTO to) throws SerException {
        return driverRecruitSer.insertModel(to);
    }

    @Override
    public DriverRecruitBO edit(DriverRecruitTO to) throws SerException {
        return driverRecruitSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        driverRecruitSer.remove(id);
    }

    @Override
    public Long count(DriverRecruitDTO dto) throws SerException {
        return driverRecruitSer.count(dto);
    }

    @Override
    public DriverRecruitBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(driverRecruitSer.findById(id),DriverRecruitBO.class);
    }

    @Override
    public List<DriverRecruitBO> pageList(DriverRecruitDTO dto) throws SerException {
        return driverRecruitSer.pageList(dto);
    }

    @Override
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        driverRecruitSer.audit(id,suggest,audit);
    }
}