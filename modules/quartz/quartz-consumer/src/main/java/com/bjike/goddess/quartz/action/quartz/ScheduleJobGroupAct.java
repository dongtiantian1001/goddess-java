package com.bjike.goddess.quartz.action.quartz;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.api.ScheduleJobGroupAPI;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduleJobGroup;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import com.bjike.goddess.quartz.vo.ScheduleJobGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务调度组
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("schedulejob/group")
public class ScheduleJobGroupAct {


    @Autowired
    private ScheduleJobGroupAPI scheduleJobGroupAPI;

    /**
     * 分页列表
     *
     * @param dto
     * @return class ScheduleJobGroupVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ScheduleJobGroupDTO dto) throws ActException {
        try {

            List<ScheduleJobGroupVO> jobGroupVOs = BeanTransform.copyProperties(scheduleJobGroupAPI.list(dto), ScheduleJobGroupVO.class);
            return ActResult.initialize(jobGroupVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 列表
     *
     * @param dto
     * @return class ScheduleJobGroupVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all(ScheduleJobGroupDTO dto) throws ActException {
        try {
            List<ScheduleJobGroupVO> jobGroupVOs = BeanTransform.copyProperties(scheduleJobGroupAPI.all(dto), ScheduleJobGroupVO.class);
            return ActResult.initialize(jobGroupVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据量
     *
     * @param dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ScheduleJobGroupDTO dto) throws ActException {
        try {
            ScheduleJobGroupVO jobGroupVO = BeanTransform.copyProperties(scheduleJobGroupAPI.count(dto), ScheduleJobGroupVO.class);
            return ActResult.initialize(jobGroupVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @version v1
     */
    @GetMapping("v1/{id}")
    public Result count(@PathVariable String id) throws ActException {
        try {
            ScheduleJobGroupVO scheduleJobVO = BeanTransform.copyProperties(scheduleJobGroupAPI.findById(id),ScheduleJobGroupVO.class);
            return ActResult.initialize(scheduleJobVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param jobGroupTO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ScheduleJobGroupTO jobGroupTO, BindingResult result) throws ActException {
        try {
            ScheduleJobGroupVO jobGroupVO = BeanTransform.copyProperties(scheduleJobGroupAPI.add(null, jobGroupTO), ScheduleJobGroupVO.class);
            return ActResult.initialize(jobGroupVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param jobGroupTO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ScheduleJobGroupTO jobGroupTO, BindingResult result) throws ActException {
        try {
            scheduleJobGroupAPI.edit(jobGroupTO);
            return ActResult.initialize("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            scheduleJobGroupAPI.delete(id);
            return ActResult.initialize("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启关闭
     *
     * @param id
     * @param enable 开启
     * @version v1
     */
    @PutMapping("v1/enable/{id}/{enable}")
    public Result enable(@PathVariable String id, @PathVariable boolean enable) throws ActException {
        try {
            scheduleJobGroupAPI.enable(id, enable);
            return ActResult.initialize("操作成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}