package com.bjike.goddess.moneyside.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.entity.IncomeDistribution;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
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
 * 收益比例分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class IncomeDistributionSerImpl extends ServiceImpl<IncomeDistribution, IncomeDistributionDTO> implements IncomeDistributionSer {
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
        return flag;
    }

    @Override
    public Long countIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        Long count = super.count(incomeDistributionDTO);
        return count;
    }

    @Override
    public IncomeDistributionBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        IncomeDistribution incomeDistribution = super.findById(id);
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Override
    public List<IncomeDistributionBO> findListIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        List<IncomeDistribution> incomeDistributions = super.findByPage(incomeDistributionDTO);
        List<IncomeDistributionBO> incomeDistributionBOS = BeanTransform.copyProperties(incomeDistributions,IncomeDistributionBO.class);
        return incomeDistributionBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeDistributionBO insertIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        IncomeDistribution incomeDistribution = BeanTransform.copyProperties(incomeDistributionTO,IncomeDistribution.class,true);
        incomeDistribution.setCreateTime(LocalDateTime.now());
        super.save(incomeDistribution);
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeDistributionBO editIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        if (StringUtils.isBlank(incomeDistributionTO.getId())) {
            throw new SerException("id不能为空");
        }
        IncomeDistribution incomeDistribution = super.findById(incomeDistributionTO.getId());
        BeanTransform.copyProperties(incomeDistributionTO,incomeDistribution,true);
        incomeDistribution.setModifyTime(LocalDateTime.now());
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeIncomeDistribution(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}