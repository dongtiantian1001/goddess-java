package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.BonusBudgetBO;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.entity.BonusBudget;
import com.bjike.goddess.firmreward.excel.SonPermissionObject;
import com.bjike.goddess.firmreward.service.BonusBudgetSer;
import com.bjike.goddess.firmreward.to.BonusBudgetTO;
import com.bjike.goddess.firmreward.to.RewardProgramRatiosTO;
import com.bjike.goddess.firmreward.to.RewardProgramTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖金预算业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bonusBudgetApiImpl")
public class BonusBudgetApiImpl implements BonusBudgetAPI {

    @Autowired
    private BonusBudgetSer bonusBudgetSer;

    /**
     * 根据id查询奖金预算
     *
     * @param id 奖金预算唯一标识
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    public BonusBudgetBO findById(String id) throws SerException {
        BonusBudget model = bonusBudgetSer.findById(id);
        return BeanTransform.copyProperties(model, BonusBudgetBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 奖金预算dto
     * @throws SerException
     */
    @Override
    public Long count(BonusBudgetDTO dto) throws SerException {
        return bonusBudgetSer.count(dto);
    }


    /**
     * 分页查询奖金预算
     *
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    public List<BonusBudgetBO> list(BonusBudgetDTO dto) throws SerException {
        return bonusBudgetSer.list(dto);
    }

    /**
     * 保存奖金预算
     *
     * @param to 奖金预算to
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    public BonusBudgetBO save(BonusBudgetTO to) throws SerException {
        return bonusBudgetSer.save(to);
    }

    /**
     * 根据id删除奖金预算
     *
     * @param id 奖金预算唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        bonusBudgetSer.remove(id);
    }

    /**
     * 更新奖金预算
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    @Override
    public void update(BonusBudgetTO to) throws SerException {
        bonusBudgetSer.update(to);
    }

    /**
     * 添加奖励项目比例
     *
     * @param rewardProgramTO 奖励项目比例to
     * @throws SerException
     */
    @Override
    public void addRewardProgramRatios(RewardProgramTO rewardProgramTO) throws SerException {
        bonusBudgetSer.addRewardProgramRatios(rewardProgramTO);
    }

    /**
     * 更新奖励项目比例
     *
     * @param rewardProgramTO 奖励项目比例to
     * @throws SerException
     */
    @Override
    public void updateRewardProgramRatios(RewardProgramTO rewardProgramTO) throws SerException {
        bonusBudgetSer.updateRewardProgramRatios(rewardProgramTO);
    }

    /**
     * 查看奖励项目比例
     *
     * @param id 奖金预算id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<RewardProgramRatioBO> checkRewardProgramRatios(String id) throws SerException {
        return bonusBudgetSer.checkRewardProgramRatios(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return bonusBudgetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return bonusBudgetSer.guidePermission( guidePermissionTO );
    }

}