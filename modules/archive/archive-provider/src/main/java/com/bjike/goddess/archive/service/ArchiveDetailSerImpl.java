package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveDetailBO;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.entity.ArchiveDetail;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.api.LevelShowAPI;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.bo.PositionUserDetailBO;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 档案明细业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ArchiveDetailSerImpl extends ServiceImpl<ArchiveDetail, ArchiveDetailDTO> implements ArchiveDetailSer {
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private LevelShowAPI levelShowAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;

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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

    private ArchiveDetailBO transformBO(ArchiveDetail entity) throws SerException {
        ArchiveDetailBO bo = BeanTransform.copyProperties(entity, ArchiveDetailBO.class);
        bo.setPosition("");
        bo.setProject("");
        bo.setArea("");
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null != user) {
            PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getUsername());
            if (null != detailBO) {
                List<PositionUserDetailBO> positionUserDetailBOSList = detailBO.getDetailS();
                if (null != positionUserDetailBOSList) {
                    for (PositionUserDetailBO p : positionUserDetailBOSList) {
                        if (WorkStatus.MAIN.equals(p.getWorkStatus())) {
                            for (String id : p.getPositionId().split(",")) {
                                PositionDetailBO position = positionDetailAPI.findBOById(id);
                                bo.setPosition(bo.getPosition() + "," + position.getPosition());
                                bo.setProject(bo.getProject() + "," + position.getDepartmentName());
                                bo.setArea(position.getArea());
                            }
                        }
                    }
                }
            }
            bo.setSerialNumber(user.getEmployeeNumber());
        }
        return bo;
    }

    private List<ArchiveDetailBO> transformBOList(List<ArchiveDetail> list) throws SerException {
        List<ArchiveDetailBO> bos = new ArrayList<>(list.size());
        for (ArchiveDetail entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveDetailBO save(ArchiveDetailTO to) throws SerException {
        ArchiveDetail entity = BeanTransform.copyProperties(to, ArchiveDetail.class);
//        if (moduleAPI.isCheck("managementpromotion")) {
//            LevelShow levelShow = levelShowAPI.findByName(to.getUsername());
//            if (null != levelShow) {
//                entity.setManage(levelShow.getCurrentLevel());
//            }
//        }
        if (moduleAPI.isCheck("bonus")) {
            String reward = disciplineRecordAPI.getRewardBallot(to.getUsername());
            String push = disciplineRecordAPI.getPushBallot(to.getUsername());
            entity.setReward(reward);
            entity.setPush(push);
        }
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArchiveDetailBO update(ArchiveDetailTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ArchiveDetail entity = super.findById(to.getId());
                BeanUtils.copyProperties(to, entity);
//                if (moduleAPI.isCheck("managementpromotion")) {
//                    LevelShow levelShow = levelShowAPI.findByName(to.getUsername());
//                    if (null != levelShow) {
//                        entity.setManage(levelShow.getCurrentLevel());
//                    }
//                }
//                if (moduleAPI.isCheck("bonus")) {
//                    String reward = disciplineRecordAPI.getRewardBallot(to.getUsername());
//                    String push = disciplineRecordAPI.getPushBallot(to.getUsername());
//                    entity.setReward(reward);
//                    entity.setPush(push);
//                }
                entity.setModifyTime(LocalDateTime.now());
                entity.setRemark(to.getRemark());
                entity.setStorage(to.getStorage());
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ArchiveDetailBO delete(String id) throws SerException {
        ArchiveDetail entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArchiveDetailBO findByUsername(String username) throws SerException {
        ArchiveDetailDTO dto = new ArchiveDetailDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, username));
        ArchiveDetail entity = super.findOne(dto);
        return this.transformBO(entity);
    }

    @Override
    public List<ArchiveDetailBO> maps(ArchiveDetailDTO dto) throws SerException {
//        dto = findData(dto);
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ArchiveDetailBO getById(String id) throws SerException {
        ArchiveDetail entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        ArchiveDetailDTO dto = new ArchiveDetailDTO();
        return super.count(dto);
    }

    @Override
    public String findManage(String name) throws SerException {
        if (moduleAPI.isCheck("managementpromotion")) {
            LevelShow levelShow = levelShowAPI.findByName(name);
            if (null != levelShow) {
                return levelShow.getCurrentLevel();
            }
        }
        return null;
    }

    @Override
    public String[] findPushAndReward(String name) throws SerException {
        if (moduleAPI.isCheck("bonus")) {
            String reward = disciplineRecordAPI.getRewardBallot(name);
            String push = disciplineRecordAPI.getPushBallot(name);
            String[] strings = new String[]{push, reward};
        }
        return null;
    }

    /**
     * 是否有权限查看所有人的信息(岗位级别)
     */
    private Boolean guideSeePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.guideSeePositionIdentity();
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据岗位查看所有信息或个人信息
     */
    private ArchiveDetailDTO findData(ArchiveDetailDTO dto) throws SerException {
        if (!guideSeePositionIdentity()) {
            dto = new ArchiveDetailDTO();
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            dto.getConditions().add(Restrict.eq("username", userBO.getUsername()));
        }
        return dto;
    }
}