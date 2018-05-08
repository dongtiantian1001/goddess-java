package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommerceMemberBO;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.service.CommerceMemberSer;
import com.bjike.goddess.contacts.to.CommerceMemberTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务会员卡业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commerceMemberApiImpl")
public class CommerceMemberApiImpl implements CommerceMemberAPI {

    @Autowired
    private CommerceMemberSer commerceMemberSer;

    @Override
    public CommerceMemberBO save(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.save(to);
    }

    @Override
    public CommerceMemberBO update(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.update(to);
    }

    @Override
    public CommerceMemberBO delete(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.delete(to);
    }

    @Override
    public List<CommerceMemberBO> maps(CommerceMemberDTO dto) throws SerException {
        return commerceMemberSer.maps(dto);
    }

    @Override
    public CommerceMemberBO getById(String id) throws SerException {
        return commerceMemberSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return commerceMemberSer.getTotal();
    }
    
    @Override
    public Boolean sonPermission() throws SerException {
        return commerceMemberSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return commerceMemberSer.guidePermission(guidePermissionTO);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return commerceMemberSer.templateExport();
    }
}