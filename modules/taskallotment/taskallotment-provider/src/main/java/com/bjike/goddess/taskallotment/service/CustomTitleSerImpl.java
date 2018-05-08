package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.CustomTitleBO;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;
import com.bjike.goddess.taskallotment.entity.CustomTitle;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义字段业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class CustomTitleSerImpl extends ServiceImpl<CustomTitle, CustomTitleDTO> implements CustomTitleSer {
    @Override
    public List<CustomTitleBO> findByDTO(CustomTitleDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto), CustomTitleBO.class);
    }
}