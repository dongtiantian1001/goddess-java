package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.LandTaxReportBO;
import com.bjike.goddess.foreigntax.dto.LandTaxReportDTO;
import com.bjike.goddess.foreigntax.entity.LandTaxReport;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.LandTaxReportTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 国地税报表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class LandTaxReportSerImpl extends ServiceImpl<LandTaxReport, LandTaxReportDTO> implements LandTaxReportSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long count(LandTaxReportDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public LandTaxReportBO getOne(String id) throws SerException {
        LandTaxReport landTaxReport = super.findById(id);
        LandTaxReportBO bo = BeanTransform.copyProperties(landTaxReport, LandTaxReportBO.class);
        return bo;
    }

    @Override
    public List<LandTaxReportBO> list(LandTaxReportDTO dto) throws SerException {
        checkSeeIdentity();
        List<LandTaxReport> landTaxReports = super.findByCis(dto);
        List<LandTaxReportBO> landTaxReportBOS = BeanTransform.copyProperties(landTaxReports, LandTaxReportBO.class);
        return landTaxReportBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LandTaxReportBO insert(LandTaxReportTO to) throws SerException {
        checkAddIdentity();
        LandTaxReport landTaxReport = BeanTransform.copyProperties(to, LandTaxReport.class, true);
        landTaxReport.setCreateTime(LocalDateTime.now());
        super.save(landTaxReport);
        LandTaxReportBO bo = BeanTransform.copyProperties(landTaxReport, LandTaxReportBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LandTaxReportBO edit(LandTaxReportTO to) throws SerException {
        checkAddIdentity();
        LandTaxReport landTaxReport = super.findById(to.getId());
        LocalDateTime createTime = landTaxReport.getCreateTime();
        landTaxReport = BeanTransform.copyProperties(to, LandTaxReport.class, true);
        landTaxReport.setCreateTime(createTime);
        landTaxReport.setModifyTime(LocalDateTime.now());
        super.update(landTaxReport);
        LandTaxReportBO bo = BeanTransform.copyProperties(landTaxReport, LandTaxReportBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

}