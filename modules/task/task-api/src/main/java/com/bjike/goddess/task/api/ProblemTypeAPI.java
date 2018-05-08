package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.ProblemTypeBO;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.to.ProblemTypeTO;

import java.util.List;

/**
 * 问题类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProblemTypeAPI {
    /**
     * 列表
     *
     * @param dto
     * @throws SerException
     */
    default List<ProblemTypeBO> list(ProblemTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void add(ProblemTypeTO to) throws SerException {

    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(ProblemTypeTO to) throws SerException {

    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    ProblemTypeBO findByID(String id) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 开关
     *
     * @param id
     * @throws SerException
     */
    default void enable(String id, Boolean enable) throws SerException {

    }

    /**
     * 获取所有可用的问题类型
     *
     * @return
     * @throws SerException
     */
    List<ProblemTypeBO> types() throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProblemTypeDTO dto) throws SerException;
}
