package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.to.BuyscheduleTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买入记录表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class BuyscheduleSerImpl extends ServiceImpl<Buyschedule, BuyscheduleDTO> implements BuyscheduleSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
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
    public List<BuyscheduleBO> maps(BuyscheduleDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();
        List<BuyscheduleBO> buyscheduleBOList = new ArrayList<>(0);
        List<Buyschedule> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            Boolean tar = authority(userBO);
            if (tar) {
                buyscheduleBOList = BeanTransform.copyProperties(list, BuyscheduleBO.class, false);
            } else {
                List<Buyschedule> list1 = list.stream().filter(str -> str.getShareholder().equals(userBO.getUsername())).collect(Collectors.toList());
                buyscheduleBOList = BeanTransform.copyProperties(list1, BuyscheduleBO.class, false);
            }
        }
        return buyscheduleBOList;
    }

    @Override
    public BuyscheduleBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Buyschedule buyschedule = super.findById(id);
        return BeanTransform.copyProperties(buyschedule, BuyscheduleBO.class);
    }

    @Override
    public Long getTotal(BuyscheduleDTO buyscheduleDTO) throws SerException {
        searchCondition(buyscheduleDTO);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Long count = 0l;
        Boolean tar = authority(userBO);
        if (tar) {
            count = super.count(buyscheduleDTO);
        } else {
            buyscheduleDTO.getConditions().add(Restrict.eq("shareholder", userBO.getUsername()));
            count = super.count(buyscheduleDTO);
        }
        return count;
    }

    @Override
    public void sell(BuyscheduleTO buyscheduleTO) throws SerException {
//        if (StringUtils.isBlank(buyscheduleTO.getId())) {
//            throw new SerException("ｉｄ不能为空");
//        }
//
//        Buyschedule entity = super.findById(buyscheduleTO.getId());
//        if (null == entity) {
//            throw new SerException("出售数据不能为空");
//        }
//
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//
//        Sellschedule sellschedule = new Sellschedule();
//        sellschedule.setSellName(userBO.getUsername());
//        sellschedule.setCode(entity.getCode());
//        sellschedule.setName(entity.getName());
//        sellschedule.setSellNum(buyscheduleTO.gets);
//        sellschedule.setSellPrice;
//        sellschedule.setTotalSellPrice;
//        sellschedule.setSellTime;
//        sellschedule.setNumber;
//        sellschedule.setBuyName;
//        sellschedule.setPurchaseNum;
//        sellschedule.setBuyTime;
    }

    @Override
    public List<BuyscheduleCollectBO> collect() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();

        Boolean tar = authority(userBO);
        String file[] = new String[]{"shareholder", "purchaseNum", "totalBuyPrice", "totalPrice"};
        String sql = getSql(tar, userBO);
        List<Buyschedule> list = super.findBySql(sql, Buyschedule.class, file);
        List<BuyscheduleCollectBO> buyscheduleCollectBOs = BeanTransform.copyProperties(list, BuyscheduleCollectBO.class);
        return buyscheduleCollectBOs;
    }

    //判断是否有权限
    private Boolean authority(UserBO userBO) throws SerException {
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        Boolean tar = false;
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) || "规划模块".equals(positionDetailBO.getDepartmentName()) || "协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName())) {
                    //拥有全部权限
                    tar = true;
                }
            }
        }
        if ("admin".equals(userBO.getUsername())) {
            tar = true;
        }
        return tar;
    }

    private String getSql(Boolean tar, UserBO userBO) throws SerException {
        String file[] = new String[]{};
        StringBuilder sql = new StringBuilder("select shareholder, ");
        sql.append(" sum(purchaseNum) as purchaseNum, ");
        sql.append(" sum(totalBuyPrice) as totalBuyPrice, ");
        sql.append(" sum(totalPrice) as totalPrice ");
        sql.append(" from staffshares_buyschedule ");
        if (!tar) {
            sql.append(" where shareholder = '" + userBO.getUsername() + "' ");
        }
        sql.append(" group by shareholder; ");
        return sql.toString();
    }

    public void searchCondition(BuyscheduleDTO buyscheduleDTO) throws SerException {
        /**
         * 持股人
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getShareholder())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("shareholder", buyscheduleDTO.getShareholder()));
        }
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getCode())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("code", buyscheduleDTO.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(buyscheduleDTO.getName())) {
            buyscheduleDTO.getConditions().add(Restrict.eq("name", buyscheduleDTO.getName()));
        }
        /**
         * 购入股数
         */
        if (null != buyscheduleDTO.getPurchaseNum()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("purchaseNum", buyscheduleDTO.getPurchaseNum()));
        }
        /**
         * 买入价格
         */
        if (null != buyscheduleDTO.getBuyPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("buyPrice", buyscheduleDTO.getBuyPrice()));
        }
        /**
         * 发行价格
         */
        if (null != buyscheduleDTO.getPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("price", buyscheduleDTO.getPrice()));
        }
        /**
         * 买入金额
         */
        if (null != buyscheduleDTO.getTotalBuyPrice()) {
            buyscheduleDTO.getConditions().add(Restrict.eq("totalBuyPrice", buyscheduleDTO.getTotalBuyPrice()));
        }
    }
}