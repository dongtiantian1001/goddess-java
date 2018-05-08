package com.bjike.goddess.quartz.action.quartz;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.api.ScheduleJobAPI;
import com.bjike.goddess.quartz.dto.ScheduleJobDTO;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import com.bjike.goddess.quartz.vo.ScheduleJobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务调度
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("schedulejob")
public class ScheduleJobAct {

    @Autowired
    private ScheduleJobAPI scheduleJobAPI;

    /**
     * 分页列表
     *
     * @param dto 分页数据
     * @return class ScheduleJobVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ScheduleJobDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ScheduleJobVO> scheduleJobVOs = BeanTransform.copyProperties(scheduleJobAPI.list(dto), ScheduleJobVO.class, request);
            return ActResult.initialize(scheduleJobVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据量
     *
     * @param dto 分页数据
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ScheduleJobDTO dto) throws ActException {
        try {
            Long count = scheduleJobAPI.count(dto);
            return ActResult.initialize(count);
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
            ScheduleJobVO scheduleJobVO = BeanTransform.copyProperties(scheduleJobAPI.findById(id),ScheduleJobVO.class);
            return ActResult.initialize(scheduleJobVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 添加
     *
     * @param scheduleJobTO 实体数据
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ScheduleJobTO scheduleJobTO, BindingResult result) throws ActException {
        try {
            ScheduleJobVO scheduleJobVO = BeanTransform.copyProperties(scheduleJobAPI.add(scheduleJobTO), ScheduleJobVO.class);
            return ActResult.initialize(scheduleJobVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param scheduleJobTO 实体数据
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ScheduleJobTO scheduleJobTO, BindingResult result) throws ActException {
        try {
            scheduleJobAPI.edit(scheduleJobTO);
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
            scheduleJobAPI.delete(id);
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
            scheduleJobAPI.enable(id, enable);
            return ActResult.initialize("操作成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}