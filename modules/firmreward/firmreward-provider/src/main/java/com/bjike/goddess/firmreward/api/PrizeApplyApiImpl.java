package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.*;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.entity.PrizeApply;
import com.bjike.goddess.firmreward.service.PrizeApplySer;
import com.bjike.goddess.firmreward.to.ApplyDetailTO;
import com.bjike.goddess.firmreward.to.PrizeApplyTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖品申请业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("prizeApplyApiImpl")
public class PrizeApplyApiImpl implements PrizeApplyAPI {

    @Autowired
    private PrizeApplySer prizeApplySer;

    /**
     * 根据id查询奖品申请
     *
     * @param id 奖品申请唯一标识
     * @return class PrizeApplyBO
     * @throws SerException
     */
    @Override
    public PrizeApplyBO findById(String id) throws SerException {
        PrizeApply model = prizeApplySer.findById(id);
        return BeanTransform.copyProperties(model, PrizeApplyBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 奖品申请dto
     * @throws SerException
     */
    @Override
    public Long count(PrizeApplyDTO dto) throws SerException {
        return prizeApplySer.count(dto);
    }

    /**
     * 分页查询奖品申请
     *
     * @return class PrizeApplyBO
     * @throws SerException
     */
    @Override
    public List<PrizeApplyBO> list(PrizeApplyDTO dto) throws SerException {
        return prizeApplySer.list(dto);
    }

    /**
     * 保存奖品申请
     *
     * @param to 奖品申请to
     * @return class PrizeApplyBO
     * @throws SerException
     */
    @Override
    public PrizeApplyBO save(PrizeApplyTO to) throws SerException {
        return prizeApplySer.save(to);
    }

    /**
     * 根据id删除奖品申请
     *
     * @param id 讲评申请唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        prizeApplySer.remove(id);
    }

    /**
     * 更新奖品申请
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    public void update(PrizeApplyTO to) throws SerException {
        prizeApplySer.update(to);
    }

    /**
     * 添加奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    public void addPrizeDetails(ApplyDetailTO to) throws SerException {
        prizeApplySer.addPrizeDetails(to);
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品申请to
     * @throws SerException
     */
    @Override
    public void updatePrizeDetails(ApplyDetailTO to) throws SerException {
        prizeApplySer.updatePrizeDetails(to);
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
        return prizeApplySer.checkPrizeDetails(applyId);
    }

    /**
     * 员工奖励汇总
     * @return class StaffRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<StaffRewardCollectBO> staffRewardCollect() throws SerException {
        return prizeApplySer.staffRewardCollect();
    }

    /**
     * 项目组奖励汇总
     *
     * @return class ProjectGroupRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<ProjectGroupRewardCollectBO> projectGroupRewardCollect() throws SerException {
        return prizeApplySer.projectGroupRewardCollect();
    }

    /**
     * 地区奖励汇总
     *
     * @return class AreaRewardCollectBO
     * @throws SerException
     */
    @Override
    public List<AreaRewardCollectBO> areaRewardCollect() throws SerException {
        return prizeApplySer.areaRewardCollect();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return prizeApplySer.sonPermission();
    }


    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return prizeApplySer.guidePermission(guidePermissionTO);
    }
}