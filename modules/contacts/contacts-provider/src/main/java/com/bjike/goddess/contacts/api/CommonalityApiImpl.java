package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.service.CommonalitySer;
import com.bjike.goddess.contacts.to.CommonalityTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共邮箱管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commonalityApiImpl")
public class CommonalityApiImpl implements CommonalityAPI {

    @Autowired
    private CommonalitySer commonalitySer;

    @Override
    public CommonalityBO save(CommonalityTO to) throws SerException {
        return commonalitySer.save(to);
    }

    @Override
    public CommonalityBO update(CommonalityTO to) throws SerException {
        return commonalitySer.update(to);
    }

    @Override
    public CommonalityBO delete(CommonalityTO to) throws SerException {
        return commonalitySer.delete(to);
    }

    @Override
    public CommonalityBO congeal(CommonalityTO to) throws SerException {
        return commonalitySer.congeal(to);
    }

    @Override
    public CommonalityBO thaw(CommonalityTO to) throws SerException {
        return commonalitySer.thaw(to);
    }

    @Override
    public List<CommonalityBO> findThaw() throws SerException {
        return commonalitySer.findThaw();
    }

    @Override
    public List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        return commonalitySer.maps(dto);
    }

    @Override
    public List<CommonalityBO> findAll() throws SerException {
        return commonalitySer.findAlls();
    }

    @Override
    public CommonalityBO findByDepartment(String department) throws SerException {
        return commonalitySer.findByDepartment(department);
    }

    @Override
    public CommonalityBO getById(String id) throws SerException {
        return commonalitySer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return commonalitySer.getTotal();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return commonalitySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return commonalitySer.guidePermission(guidePermissionTO);
    }

    @Override
    public void importExcel(List<CommonalityTO> tocs) throws SerException {
        commonalitySer.importExcel(tocs);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return commonalitySer.templateExport();
    }

    @Override
    public List<String> getEmails() throws SerException {
        return commonalitySer.getEmails();
    }

    @Override
    public List<String> findDepartment() throws SerException {
        return commonalitySer.findDepartment();
    }
}