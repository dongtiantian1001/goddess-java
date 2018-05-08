package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAnalisisorBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAnalisisorDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseAnalisisor;
import com.bjike.goddess.lendreimbursement.enums.GuideAddrStatus;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseAnalisisorTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 报销分析人员表业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ReimburseAnalisisorSerImpl extends ServiceImpl<ReimburseAnalisisor, ReimburseAnalisisorDTO> implements ReimburseAnalisisorSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private LendPermissionSer cusPermissionSer;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("reim-Analisisor");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("reim-Analisisor");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        reimburseAnalisisorDTO.getSorts().add("createTime=desc");
        Long count = super.count(reimburseAnalisisorDTO);
        return count;
    }

    @Override
    public List<ReimburseAnalisisorBO> listReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        reimburseAnalisisorDTO.getSorts().add("createTime=desc");
        List<ReimburseAnalisisor> list = super.findByCis(reimburseAnalisisorDTO, true);
        List<ReimburseAnalisisorBO> analisisorBOList = BeanTransform.copyProperties(list, ReimburseAnalisisorBO.class);
        return analisisorBOList;
    }


    @Override
    public ReimburseAnalisisorBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ReimburseAnalisisor reimburseAnalisisor = super.findById(id);

        return BeanTransform.copyProperties(reimburseAnalisisor, ReimburseAnalisisorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseAnalisisorBO addReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(reimburseAnalisisorTO.getEmpNum())) {
            throw new SerException("该用户在系统中不存在，请重新选择");
        }
        //检查一下是否重复添加
        ReimburseAnalisisorDTO reAnalisisorDTO = new ReimburseAnalisisorDTO();
        reAnalisisorDTO.getConditions().add(Restrict.eq("empNum", reimburseAnalisisorTO.getEmpNum()));
        Long countCheck = super.count(reAnalisisorDTO);
        if( countCheck != null && countCheck>0 ){
            throw new SerException("该用户已经添加过，不能重复添加");
        }

        //非重复添加
        ReimburseAnalisisor reimburseAnalisisor = BeanTransform.copyProperties(reimburseAnalisisorTO, ReimburseAnalisisor.class, true);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("employeeNumber", reimburseAnalisisorTO.getEmpNum()));
        RpcTransmit.transmitUserToken(userToken);
        List<UserBO> userBO = userAPI.findByCis(userDTO);
        if (userBO != null && userBO.size() > 0) {
            UserBO temp = userBO.get(0);
            reimburseAnalisisor.setUserName(temp.getUsername());

            //获取岗位和部门
            RpcTransmit.transmitUserToken(userToken);
            List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.findPositionByUser(temp.getId());
            RpcTransmit.transmitUserToken(userToken);
            if (positionDetailBOList != null && positionDetailBOList.size() > 0) {
                reimburseAnalisisor.setDepartment(positionDetailBOList.get(0).getDepartmentName());
                reimburseAnalisisor.setPosition(positionDetailBOList.get(0).getPosition());
            }
        }
        reimburseAnalisisor.setCreateTime(LocalDateTime.now());

        super.save(reimburseAnalisisor);

        return BeanTransform.copyProperties(reimburseAnalisisor, ReimburseAnalisisorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseAnalisisorBO editReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(reimburseAnalisisorTO.getEmpNum())) {
            throw new SerException("该用户在系统中不存在，请重新选择");
        }
        if (StringUtils.isBlank(reimburseAnalisisorTO.getId())) {
            throw new SerException("id不能为空");
        }
        //检查一下是否重复添加
        ReimburseAnalisisorDTO reAnalisisorDTO = new ReimburseAnalisisorDTO();
        reAnalisisorDTO.getConditions().add(Restrict.eq("empNum", reimburseAnalisisorTO.getEmpNum()));
        Long countCheck = super.count(reAnalisisorDTO);
        if( countCheck != null && countCheck>0 ){
            throw new SerException("该用户已经添加过，不能重复添加");
        }

        //非重复添加
        ReimburseAnalisisor temp = super.findById(reimburseAnalisisorTO.getId());
        ReimburseAnalisisor reimburseAnalisisor = BeanTransform.copyProperties(reimburseAnalisisorTO, ReimburseAnalisisor.class, true);

        BeanUtils.copyProperties(reimburseAnalisisor, temp, "id", "createTime");
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("employeeNumber", reimburseAnalisisorTO.getEmpNum()));
        List<UserBO> userBO = userAPI.findByCis(userDTO);
        if (userBO != null && userBO.size() > 0) {
            UserBO user_temp = userBO.get(0);
            temp.setUserName(user_temp.getUsername());

            RpcTransmit.transmitUserToken(userToken);
            List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.findPositionByUser(temp.getId());
            RpcTransmit.transmitUserToken(userToken);
            if (positionDetailBOList != null && positionDetailBOList.size() > 0) {
                reimburseAnalisisor.setDepartment(positionDetailBOList.get(0).getDepartmentName());
                reimburseAnalisisor.setPosition(positionDetailBOList.get(0).getPosition());
            }
            temp.setModifyTime(LocalDateTime.now());
            super.update(temp);
            return BeanTransform.copyProperties(reimburseAnalisisor, ReimburseAnalisisorBO.class);
        } else {
            throw new SerException("请下拉选择用户");
        }


    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteReimburseAnalisisor(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}