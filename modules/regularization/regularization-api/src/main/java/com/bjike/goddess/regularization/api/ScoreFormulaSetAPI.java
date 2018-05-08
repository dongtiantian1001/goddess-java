package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.ScoreFormulaSetBO;
import com.bjike.goddess.regularization.dto.ScoreFormulaSetDTO;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.ScoreFormulaSetTO;

import java.util.List;

/**
 * 工作表现计分方式设置业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScoreFormulaSetAPI {



    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 根据id查询工作表现计分方式
     *
     * @param id 工作表现计分方式唯一标识
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    ScoreFormulaSetBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 工作表现计分方式dto
     * @throws SerException
     */
    Long count(ScoreFormulaSetDTO dto) throws SerException;

    /**
     * 分页查询工作表现计分方式
     *
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    List<ScoreFormulaSetBO> list(ScoreFormulaSetDTO dto) throws SerException;

    /**
     * 保存工作表现计分方式
     *
     * @param to 工作表现计分方式to
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    ScoreFormulaSetBO save(ScoreFormulaSetTO to) throws SerException;

    /**
     * 根据id删除工作表现计分方式
     *
     * @param id 工作表现计分方式唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新工作表现计分方式
     *
     * @param to 工作表现计分方式to
     * @throws SerException
     */
    void update(ScoreFormulaSetTO to) throws SerException;

}