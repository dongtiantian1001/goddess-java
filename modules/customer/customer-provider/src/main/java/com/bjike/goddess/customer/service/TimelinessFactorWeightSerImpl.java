package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.TimelinessFactorWeightBO;
import com.bjike.goddess.customer.dto.TimelinessFactorWeightDTO;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 时效性因素层权重业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class TimelinessFactorWeightSerImpl extends ServiceImpl<TimelinessFactorWeight, TimelinessFactorWeightDTO> implements TimelinessFactorWeightSer {
    @Override
    public TimelinessFactorWeightBO findByName(String timelinessName) throws SerException {
        TimelinessFactorWeightDTO timelinessFactorWeightDTO = new TimelinessFactorWeightDTO();
        timelinessFactorWeightDTO.getConditions().add(Restrict.eq("timelinessName",timelinessName));
        TimelinessFactorWeight timelinessFactorWeight = super.findOne(timelinessFactorWeightDTO);
        return BeanTransform.copyProperties(timelinessFactorWeight,TimelinessFactorWeightBO.class);
    }
}