package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.WeekPlanBO;
import com.bjike.goddess.marketdevelopment.dto.WeekPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekPlan;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 周计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class WeekPlanSerImpl extends ServiceImpl<WeekPlan, WeekPlanDTO> implements WeekPlanSer {

    @Autowired
    private MonthPlanSer monthPlanSer;

    private WeekPlanBO transformBO(WeekPlan entity) {
        WeekPlanBO bo = BeanTransform.copyProperties(entity, WeekPlanBO.class);
        bo.setCourse(entity.getMonth().getYear().getCourse());
        bo.setMonth_id(entity.getMonth().getId());
        bo.setMonthTotal(entity.getMonth().getTotal());
        bo.setYear(entity.getMonth().getYear().getYear());
        bo.setType(entity.getMonth().getMonth().getValueString());
        bo.setCycle(entity.getStartCycle().toString() + "至" + entity.getEndCycle().toString());
        return bo;
    }

    private List<WeekPlanBO> transformBOList(List<WeekPlan> list) {
        List<WeekPlanBO> bos = new ArrayList<>(list.size());
        for (WeekPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO save(WeekPlanTO to) throws SerException {
        WeekPlan entity = BeanTransform.copyProperties(to, WeekPlan.class, true);
        entity.setMonth(monthPlanSer.findById(to.getMonth_id()));
        entity.setTotal(entity.getActivity() + entity.getVisit() + entity.getContact() + entity.getKnow() + entity.getInquire());
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO update(WeekPlanTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                WeekPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeekPlanBO delete(WeekPlanTO to) throws SerException {
        WeekPlan entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<WeekPlanBO> findByMonth(String month_id) throws SerException {
        WeekPlanDTO dto = new WeekPlanDTO();
        dto.getConditions().add(Restrict.eq("month.id", month_id));
        List<WeekPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<WeekPlanBO> findByDate(String startDate, String endDate) throws SerException {
        LocalDate start = LocalDate.parse(startDate), end = LocalDate.parse(endDate);
        WeekPlanDTO dto = new WeekPlanDTO();
        dto.getConditions().add(Restrict.gt_eq("startCycle", startDate));
        dto.getConditions().add(Restrict.lt_eq("startCycle", endDate));
        dto.getConditions().add(Restrict.gt_eq("endCycle", startDate));
        dto.getConditions().add(Restrict.lt_eq("endCycle", endDate));
        List<WeekPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public WeekPlanBO getById(String id) throws SerException {
        try {
            return this.transformBO(super.findById(id));
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public List<WeekPlanBO> maps(WeekPlanDTO dto) throws SerException {
        dto.getSorts().add("startCycle=desc");
        return this.transformBOList(super.findByPage(dto));
    }
}