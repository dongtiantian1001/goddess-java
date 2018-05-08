package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.service.RemoveEmployeeSer;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 减员名单业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("removeEmployeeApiImpl")
public class RemoveEmployeeApiImpl implements RemoveEmployeeAPI {
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;

    @Override
    public RemoveEmployeeBO save(RemoveEmployeeTO to) throws SerException {
        return removeEmployeeSer.save(to);
    }

    @Override
    public RemoveEmployeeBO edit(RemoveEmployeeTO to) throws SerException {
        return removeEmployeeSer.edit(to);
    }

    @Override
    public List<RemoveEmployeeBO> find(RemoveEmployeeDTO dto) throws SerException {
        return removeEmployeeSer.find(dto);
    }

    @Override
    public RemoveEmployeeBO findByID(String id) throws SerException {
        return removeEmployeeSer.findByID(id);
    }

    @Override
    public RemoveEmployeeBO delete(String id) throws SerException {
        return removeEmployeeSer.delete(id);
    }

    @Override
    public List<RemoveEmployeeBO> findALL() throws SerException {
        return removeEmployeeSer.findALL();
    }

    @Override
    public RemoveEmployeeBO findByNameAndId(RemoveEmployeeTO to) throws SerException {
        return removeEmployeeSer.findByNameAndId(to);
    }

    @Override
    public void confirmRemove(String id) throws SerException {
        removeEmployeeSer.confirmRemove(id);
    }

    @Override
    public Long count(RemoveEmployeeDTO dto) throws SerException {
        return removeEmployeeSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return removeEmployeeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return removeEmployeeSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Set<String> allName() throws SerException {
        return removeEmployeeSer.allName();
    }
}