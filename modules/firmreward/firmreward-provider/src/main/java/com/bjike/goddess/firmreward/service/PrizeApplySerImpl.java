package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.firmreward.api.AwardDetailAPI;
import com.bjike.goddess.firmreward.bo.*;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.dto.PrizeDetailDTO;
import com.bjike.goddess.firmreward.entity.PrizeApply;
import com.bjike.goddess.firmreward.entity.PrizeDetail;
import com.bjike.goddess.firmreward.enums.GuideAddrStatus;
import com.bjike.goddess.firmreward.to.ApplyDetailTO;
import com.bjike.goddess.firmreward.to.DetailTO;
import com.bjike.goddess.firmreward.to.PrizeApplyTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 奖品申请业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class PrizeApplySerImpl extends ServiceImpl<PrizeApply, PrizeApplyDTO> implements PrizeApplySer {

    @Autowired
    private PrizeDetailSer prizeDetailSer;
    @Autowired
    private AwardDetailAPI awardDetailAPI;
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
            flag = cusPermissionSer.busCusPermission("2");
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
        return flag;
    }

    /**
     * 分页查询奖品申请
     *
     * @return class PrizeApplyBO
     * @throws SerException
     */
    @Override
    public List<PrizeApplyBO> list(PrizeApplyDTO dto) throws SerException {
        checkSeeIdentity();
        List<PrizeApply> list = super.findByPage(dto);
        List<PrizeApplyBO> listBO = BeanTransform.copyProperties(list, PrizeApplyBO.class);
        return listBO;
    }

    /**
     * 保存奖品申请
     *
     * @param to 奖品申请to
     * @return class PrizeApplyBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public PrizeApplyBO save(PrizeApplyTO to) throws SerException {
        PrizeApply entity = BeanTransform.copyProperties(to, PrizeApply.class, true);
        entity = super.save(entity);
        PrizeApplyBO bo = BeanTransform.copyProperties(entity, PrizeApplyBO.class);
        return bo;
    }

    /**
     * 根据id删除奖品申请
     *
     * @param id 讲评申请唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        List<PrizeDetail> list = getPrizeDetailsByApplyId(id);
        prizeDetailSer.remove(list);//先删除子类对象
        super.remove(id);//再删除父类对象
    }

    /**
     * 根据奖励申请id查找奖品明细
     *
     * @param id 奖励申请id
     * @return class PrizeDetail
     * @throws SerException
     */
    private List<PrizeDetail> getPrizeDetailsByApplyId(String id) throws SerException {
        PrizeDetailDTO dto = new PrizeDetailDTO();
        dto.getConditions().add(Restrict.eq("prizeApplyId", id));
        return prizeDetailSer.findByCis(dto);
    }

    /**
     * 更新奖品申请
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(PrizeApplyTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            PrizeApply model = super.findById(to.getId());
            if (model != null) {
                updatePrizeApply(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖品申请
     *
     * @param to    奖品申请to
     * @param model 奖品申请
     */
    private void updatePrizeApply(PrizeApplyTO to, PrizeApply model) throws SerException {
        LocalDateTime date = model.getCreateTime();
        model = BeanTransform.copyProperties(to, PrizeApply.class, true);
        model.setCreateTime(date);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    public void addPrizeDetails(ApplyDetailTO to) throws SerException {
        String prizeApplyId = to.getId();//奖品申请id
        List<DetailTO> detailTOS = to.getDetailTOS();
        if (detailTOS != null && detailTOS.size() > 0 && StringUtils.isNotEmpty(prizeApplyId)) {
            List<PrizeDetail> list = new ArrayList<>(0);
            for (DetailTO detailTO : detailTOS) {
                PrizeDetail model = new PrizeDetail();
                model.setPrizeDetail(detailTO.getPrizeDetails());//奖品明细
                model.setPrizeBuyWay(detailTO.getPrizeBuyWays());//奖品购置途径
                model.setPrizeIssueForm(detailTO.getPrizeIssueForms());//奖品发放形式
                model.setAwardTime(DateUtil.parseDate(detailTO.getAwardTimes()));//颁奖时间
                model.setPrizeApplyId(prizeApplyId);
                list.add(model);
            }
            prizeDetailSer.save(list);
        }
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    public void updatePrizeDetails(ApplyDetailTO to) throws SerException {
        String prizeApplyId = to.getId();//奖品申请id
        List<PrizeDetail> list = getPrizeDetailsByApplyId(prizeApplyId);
        prizeDetailSer.remove(list);
        addPrizeDetails(to);
    }

    /**
     * 查看奖品明细
     *
     * @param applyId 奖品申请id
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public List<PrizeDetailBO> checkPrizeDetails(String applyId) throws SerException {
        List<PrizeDetail> list = getPrizeDetailsByApplyId(applyId);
        return BeanTransform.copyProperties(list, PrizeDetailBO.class);
    }

    /**
     * 员工奖励汇总
     *
     * @return class StaffRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<StaffRewardCollectBO> staffRewardCollect() throws SerException {
        // TODO: 17-4-14
        return null;
    }

    /**
     * 项目组奖励汇总
     *
     * @return class ProjectGroupRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<ProjectGroupRewardCollectBO> projectGroupRewardCollect() throws SerException {
        // TODO: 17-4-14  
        return null;
    }

    /**
     * 地区奖励汇总
     *
     * @return class AreaRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<AreaRewardCollectBO> areaRewardCollect() throws SerException {
        // TODO: 17-4-14
        return null;
    }
}