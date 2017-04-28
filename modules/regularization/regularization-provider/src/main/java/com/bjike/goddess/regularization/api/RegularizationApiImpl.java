package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.service.RegularizationSer;
import com.bjike.goddess.regularization.to.RegularizationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工转正业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("regularizationApiImpl")
public class RegularizationApiImpl implements RegularizationAPI {

    @Autowired
    private RegularizationSer regularizationSer;

    /**
     * 根据id查询员工转正
     *
     * @param id 员工转正id
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public RegularizationBO findById(String id) throws SerException {
        Regularization model = regularizationSer.findById(id);
        return BeanTransform.copyProperties(model, RegularizationBO.class);
    }

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public List<RegularizationBO> list(RegularizationDTO dto) throws SerException {
        return regularizationSer.list(dto);
    }

    /**
     * 保存员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public RegularizationBO save(RegularizationTO to) throws SerException {
        return regularizationSer.save(to);
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        regularizationSer.remove(id);
    }

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void update(RegularizationTO to) throws SerException {
        regularizationSer.update(to);
    }

    /**
     * 管理层评分
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void manageScore(RegularizationTO to) throws SerException {
        regularizationSer.manageScore(to);
    }

    /**
     * 查看管理层评分
     *
     * @param to 员工转正to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> checkManageScore(RegularizationTO to) throws SerException {
        return regularizationSer.checkManageScore(to);
    }

    /**
     * 决策层评价
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void decisionLevelEvaluate(RegularizationTO to) throws SerException {
        regularizationSer.decisionLevelEvaluate(to);
    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void planModuleSupply(RegularizationTO to) throws SerException {
        regularizationSer.planModuleSupply(to);
    }

    /**
     * 预算模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void budgetModuleSupply(RegularizationTO to) throws SerException {
        regularizationSer.budgetModuleSupply(to);
    }

    /**
     * 总经办审批
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    public void zjbApproval(RegularizationTO to) throws SerException {
        regularizationSer.zjbApproval(to);
    }
}