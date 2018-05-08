package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.api.OilCardReceiveAPI;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.entity.OilCardReceive;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import com.bjike.goddess.oilcardmanage.enums.GuideAddrStatus;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 油卡基础信息业务处理类
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:42]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "oilCardBasicSerImplCache")
@Service
public class OilCardBasicSerImpl extends ServiceImpl<OilCardBasic, OilCardBasicDTO> implements OilCardBasicSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private OilCardReceiveSer oilCardReceiveSer;

    @Autowired
    private OilCardRechargeSer oilCardRechargeSer;



    /**
     * 核对查看权限（层级别）
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
     * 核对添加修改删除审核权限（层级别）
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
     * 导航栏核对查看权限（层级别）
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
     * 导航栏核对添加修改删除审核权限（层级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("oilCardBasic");
        obj.setDescribesion("油卡基础信息");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagReceive = oilCardReceiveSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("oilCardReceiver");
        obj.setDescribesion("油卡领用信息");
        if (flagReceive) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagRecharge = oilCardRechargeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("oilCardRecharge");
        obj.setDescribesion("油卡充值信息");
        if (flagRecharge) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
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
    public OilCardBasicBO saveOilCarBasic(OilCardBasicTO to) throws SerException {
        checkAddIdentity();
        OilCardBasic model = BeanTransform.copyProperties(to, OilCardBasic.class, true);
        model.setCardStatus(OilCardStatus.IDLE);
        model.setBalance(model.getCycleEarlyMoney());
        super.save(model);
        return BeanTransform.copyProperties(model, OilCardBasicBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException {
        checkAddIdentity();
        OilCardBasic model = super.findById(to.getId());
        if (model != null) {
            updateModel(to);
            return BeanTransform.copyProperties(to, OilCardBasicBO.class);
        } else {
            throw new SerException("非法Id,油卡信息对象不能为空!");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void freezeOilCardBasic(String id) throws SerException {
        checkAddIdentity();
        OilCardBasic model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setStatus(Status.CONGEAL);
            } else {
                throw new SerException("该记录无需重复冻结!");
            }
        } else {
            throw new SerException("非法ID,油卡信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        checkAddIdentity();
        OilCardBasic model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复解冻!");
            }
        } else {
            throw new SerException("非法ID,油卡信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException {
        checkSeeIdentity();
        if (dto.getStatus() != null) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        List<OilCardBasic> list = super.findByPage(dto);

        return BeanTransform.copyProperties(list, OilCardBasicBO.class);
    }

    @Override
    public OilCardBasicBO findByCode(String oilCardCode) throws SerException {
        OilCardBasicDTO dto = new OilCardBasicDTO();
        dto.getConditions().add(Restrict.eq("oilCardCode", oilCardCode));
        return BeanTransform.copyProperties(super.findOne(dto), OilCardBasicBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 油卡基本信息
     * @throws SerException 更新油卡异常
     */
    public void updateModel(OilCardBasicTO to) throws SerException {
        OilCardBasic model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空");
        }
    }

    @Override
    public OilCardBasicBO find(String id) throws SerException {
        OilCardBasic oilCardBasic = super.findById(id);
        OilCardBasicBO oilCardBasicBO = BeanTransform.copyProperties(oilCardBasic,OilCardBasicBO.class);
        return oilCardBasicBO;
    }

    @Override
    public void updateOliCardBasic(OilCardBasicBO basicBO) throws SerException {
        OilCardBasic oilCardBasic = super.findById(basicBO.getId());
        oilCardBasic.setBalance(basicBO.getBalance());
        super.update(oilCardBasic);
    }

    @Override
    public List<OilCardBasicBO> findOilCard() throws SerException {
        OilCardBasicDTO dto = new OilCardBasicDTO();
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        List<OilCardBasic> list = super.findByCis(dto);
        List<OilCardBasicBO> boList = BeanTransform.copyProperties(list,OilCardBasicBO.class);
        return boList;
    }

    @Override
    public void deleteOilCardBasic(String id) throws SerException {
        if(id != null) {
            OilCardBasic basic = super.findById(id);
            if(basic != null) {
                OilCardRechargeDTO rechargeDTO = new OilCardRechargeDTO();
                rechargeDTO.getConditions().add(Restrict.eq("oilCardBasic.id",id));
                List<OilCardRecharge> oilCardRecharge = oilCardRechargeSer.findByCis(rechargeDTO);
                if(oilCardRecharge !=null && !oilCardRecharge.isEmpty()){
                    for(OilCardRecharge recharge : oilCardRecharge){
                        oilCardRechargeSer.remove(recharge.getId());
                    }
                }
                OilCardReceiveDTO receiveDTO = new OilCardReceiveDTO();
                receiveDTO.getConditions().add(Restrict.eq("oilCardBasic.id",id));
                List<OilCardReceive> oilCardReceives = oilCardReceiveSer.findByCis(receiveDTO);
                if(oilCardReceives != null && !oilCardReceives.isEmpty()){
                    for(OilCardReceive oilCardReceive : oilCardReceives){
                        oilCardReceiveSer.remove(oilCardReceive.getId());
                    }
                }
                super.remove(id);
            }else{
                throw new SerException("没有此油卡,无法删除");
            }
        }

    }

}
