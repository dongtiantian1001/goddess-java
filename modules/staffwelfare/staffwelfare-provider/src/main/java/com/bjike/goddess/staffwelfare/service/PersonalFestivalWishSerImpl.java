package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfare.bo.PersonalFestivalWishBO;
import com.bjike.goddess.staffwelfare.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfare.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfare.entity.PersonalFestival;
import com.bjike.goddess.staffwelfare.entity.PersonalFestivalWish;
import com.bjike.goddess.staffwelfare.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 节日祝福语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class PersonalFestivalWishSerImpl extends ServiceImpl<PersonalFestivalWish, PersonalFestivalWishDTO> implements PersonalFestivalWishSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PersonalFestivalSer personalFestivalSer;

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
    @Transactional(rollbackFor = SerException.class)
    public List<PersonalFestivalWishBO> pageList(PersonalFestivalWishDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        UserBO userBO = userAPI.currentUser();
        if(userBO == null){
            throw new SerException("用户未登录或登录已失效!");
        }
        //查询当前用户收到的祝福语
        dto.getConditions().add(Restrict.eq("receiveUserId",userBO.getId()));
        PersonalFestivalDTO personalFestivalDTO = new PersonalFestivalDTO();
        if(StringUtils.isNotBlank(dto.getFestivalName())) {
            personalFestivalDTO.getConditions().add(Restrict.eq("festivalName", dto.getFestivalName()));
        }
        List<PersonalFestival> personalFestivals = personalFestivalSer.findByCis(personalFestivalDTO);
        List<PersonalFestivalWishBO> boList = new ArrayList<>();
        if(personalFestivals != null && personalFestivals.size() > 0){
            for(PersonalFestival personalFestival : personalFestivals){
                PersonalFestivalWishDTO wishDTO = new PersonalFestivalWishDTO();
                wishDTO.getConditions().add(Restrict.eq("festivalId",personalFestival.getId()));
                List<PersonalFestivalWish> wishList = super.findByCis(wishDTO);
                if(wishList != null && wishList.size() > 0) {
                    for(PersonalFestivalWish personalFestivalWish : wishList) {
                        PersonalFestivalWishBO wishBO = new PersonalFestivalWishBO();
                        wishBO.setFestivalName(personalFestival.getFestivalName());
                        wishBO.setFestivalTime(personalFestival.getFestivalDate().toString());
                        wishBO.setSendUser(personalFestivalWish.getSendUser());
                        wishBO.setWishStatement(personalFestivalWish.getWishStatement());
                        wishBO.setThankStatement(personalFestival.getThankStatement());
                        wishBO.setRemark(personalFestival.getRemark());
                        boList.add(wishBO);
                    }
                }
            }
        }
//        List<PersonalFestivalWish> list = super.findByPage(dto);
//        List<PersonalFestivalWishBO> boList = new ArrayList<PersonalFestivalWishBO>();
//        if (list != null && !list.isEmpty()) {
//            boList = BeanTransform.copyProperties(list, PersonalFestivalWishBO.class);
//            for (PersonalFestivalWishBO bo : boList) {
//                PersonalFestival personalFestival = personalFestivalSer.findById(bo.getFestivalId());
//                if(personalFestival!=null){
//                    PersonalFestivalBO personalFestivalBO = BeanTransform.copyProperties(personalFestival,PersonalFestivalBO.class);
//                    bo.setFestivalName(personalFestivalBO.getFestivalName());
//                    bo.setFestivalTime(personalFestivalBO.getFestivalDate());
//                    bo.setThankStatement(personalFestivalBO.getThankStatement());
//                }
//            }
//
//        }
        return boList;
    }


}