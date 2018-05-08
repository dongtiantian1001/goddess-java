package com.bjike.goddess.devicerepair.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.entity.DeviceRepair;
import com.bjike.goddess.devicerepair.excel.SonPermissionObject;
import com.bjike.goddess.devicerepair.service.DeviceRepairSer;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.GuidePermissionTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.MaterialState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 设备维修业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [ 设备维修业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("deviceRepairApiImpl")
public class DeviceRepairApiImpl implements DeviceRepairAPI {

    @Autowired
    private DeviceRepairSer deviceRepairSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return deviceRepairSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return deviceRepairSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询设备维修
     *
     * @param id 设备维修唯一标识
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    public DeviceRepairBO findById(String id) throws SerException {
        DeviceRepair model = deviceRepairSer.findById(id);
        return BeanTransform.copyProperties(model, DeviceRepairBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 设备维修dto
     * @throws SerException
     */
    @Override
    public Long count(DeviceRepairDTO dto) throws SerException {
        return deviceRepairSer.count(dto);
    }

    /**
     * 分页查询设备维修
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    public List<DeviceRepairBO> list(DeviceRepairDTO dto) throws SerException {
        return deviceRepairSer.list(dto);
    }

    /**
     * 保存设备维修
     *
     * @param to 设备维修to
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    public DeviceRepairBO save(DeviceRepairTO to) throws SerException {
        return deviceRepairSer.save(to);
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        deviceRepairSer.remove(id);
    }

    /**
     * 更新设备维修
     *
     * @param to 设备维修to
     * @throws SerException
     */
    @Override
    public void update(DeviceRepairTO to) throws SerException {
        deviceRepairSer.update(to);
    }

    /**
     * 福利模块审核
     *
     * @param to 福利模块审核to
     * @throws SerException
     */
    @Override
    public void welfareAudit(WelfareAuditTO to) throws SerException {
        deviceRepairSer.welfareAudit(to);
    }

    /**
     * 项目经理审核状态
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws SerException
     */
    @Override
    public void pmAudit(String id, AuditState pmAuditState) throws SerException {
        deviceRepairSer.pmAudit(id, pmAuditState);
    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws SerException
     */
    @Override
    public void deviceScrap(String id, String deviceIssue) throws SerException {
        deviceRepairSer.deviceScrap(id, deviceIssue);
    }

    /**
     * 取回设备
     *
     * @param to
     * @throws SerException
     */
    @Override
    public void fetchDevice(FetchDeviceTO to) throws SerException {
        deviceRepairSer.fetchDevice(to);
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        return deviceRepairSer.findAddAllDetails();
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        return deviceRepairSer.findallMonUser();
    }

    @Override
    public List<MaterialState> findDeviceStatus(String[] intervalTime) throws SerException {
        return deviceRepairSer.findDeviceStatus(intervalTime);
    }

    @Override
    public List<String> findAreaByStatus(String[] intervalTime, MaterialState materialState) throws SerException {
        return deviceRepairSer.findAreaByStatus(intervalTime,materialState);
    }

    @Override
    public List<String> findProjectByStaAnAr(String[] intervalTime, MaterialState materialState, String area) throws SerException {
        return deviceRepairSer.findProjectByStaAnAr(intervalTime,materialState,area);
    }

    @Override
    public List<DeviceRepairBO> findByStaAnAr(String[] intervalTime, MaterialState materialState, String area, String projectGroup) throws SerException {
        return deviceRepairSer.findByStaAnAr(intervalTime,materialState,area,projectGroup);
    }

    @Override
    public List<Boolean> findBool(String[] intervalTime) throws SerException {
        return deviceRepairSer.findBool(intervalTime);
    }

    @Override
    public List<String> findAreaByBool(String[] intervalTime, Boolean whetherWarranty) throws SerException {
        return deviceRepairSer.findAreaByBool(intervalTime,whetherWarranty);
    }

    @Override
    public List<String> findProjByBoArea(String[] intervalTime, String area, Boolean whetherWarranty) throws SerException {
        return deviceRepairSer.findProjByBoArea(intervalTime,area,whetherWarranty);
    }

    @Override
    public List<DeviceRepairBO> findByBoAnArDep(String[] intervalTime, String area, Boolean whetherWarranty, String projectGroup) throws SerException {
        return deviceRepairSer.findByBoAnArDep(intervalTime,area,whetherWarranty,projectGroup);
    }
}