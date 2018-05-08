package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PublicizeProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.enums.GuideAddrStatus;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import com.bjike.goddess.enterpriseculturemanage.to.PublicizeProgramInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 宣传方案信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "enterpriseculturemanageSerCache")
@Service
public class PublicizeProgramInfoSerImpl extends ServiceImpl<PublicizeProgramInfo, PublicizeProgramInfoDTO> implements PublicizeProgramInfoSer {

    @Autowired
    private EnterpriseCultureInfoSer enterpriseCultureInfoSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ConstructTeamSer constructTeamSer;

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
                flag = guideSeeIdentity();
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
    @Transactional(rollbackFor = SerException.class)
    public PublicizeProgramInfoBO insertModel(PublicizeProgramInfoTO to) throws SerException {

        onOfTeam();
        EnterpriseCultureInfo info = enterpriseCultureInfoSer.findById(to.getInfoId());
        if (info != null) {
            PublicizeProgramInfo model = BeanTransform.copyProperties(to, PublicizeProgramInfo.class);
            if (isExist(model)) {
                throw new SerException("该主题的宣传方案信息已存在!");
            }
            model.setTheme(info.getTheme());
            model.setAuditResult(AuditResult.NOTDEAL);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, PublicizeProgramInfoBO.class);
        } else {
            throw new SerException("非法企业文化信息Id,企业文化信息对象不能为空!");
        }
    }

    //检查当前用户是否为建设小组人员
    public void onOfTeam() throws SerException {

        UserBO userBO = userAPI.currentUser();

        List<ConstructTeam> teamList = constructTeamSer.findAll();
        if (!CollectionUtils.isEmpty(teamList)) {
            List<String> users = new ArrayList<String>();
            for (ConstructTeam team : teamList) {
                users.add(team.getUserNumber());
            }
            if (!users.contains(userBO.getEmployeeNumber())) {
                throw new SerException("只有建设小组可以管理公司文化!");
            }
        } else {
            throw new SerException("请先添加建设小组人员!");
        }
    }

    public Boolean isExist(PublicizeProgramInfo model) throws SerException {
        PublicizeProgramInfoDTO dto = new PublicizeProgramInfoDTO();
        dto.getConditions().add(Restrict.eq("infoId", model.getInfoId()));
        dto.setLimit(1);
        List<PublicizeProgramInfo> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            if (StringUtils.isEmpty(model.getId())) {
                return true;
            } else {
                if (!model.getId().equals(list.get(0).getId())) {
                    return true;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PublicizeProgramInfoBO updateModel(PublicizeProgramInfoTO to) throws SerException {
        checkAddIdentity();
        onOfTeam();
        updateModule(to);
        return BeanTransform.copyProperties(to, PublicizeProgramInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, AuditResult auditResult, String auditSuggestion) throws SerException {
        checkAddIdentity();
        PublicizeProgramInfo model = super.findById(id);
        if (model != null) {
            model.setAuditResult(auditResult);
            model.setAuditSuggestion(auditSuggestion);
            super.update(model);
        } else {
            throw new SerException("非法Id,宣传方案信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<PublicizeProgramInfoBO> pageList(PublicizeProgramInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<PublicizeProgramInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, PublicizeProgramInfoBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 刊物方案信息
     */
    public void updateModule(PublicizeProgramInfoTO to) throws SerException {
        EnterpriseCultureInfo info = enterpriseCultureInfoSer.findById(to.getInfoId());
        if (info != null) {
            PublicizeProgramInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                if (isExist(model)) {
                    throw new SerException("该主题的宣传方案信息已存在!");
                }
                model.setTheme(info.getTheme());
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("非法Id,宣传方案信息对象不能为空!");
            }
        } else {
            throw new SerException("非法企业文化信息Id,企业文化信息对象不能为空!");
        }
    }
}