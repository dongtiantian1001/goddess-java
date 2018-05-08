package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.service.FirstPhoneRecordSer;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("firstPhoneRecordApiImpl")
public class FirstPhoneRecordApiImpl implements FirstPhoneRecordAPI {

    @Autowired
    private FirstPhoneRecordSer firstPhoneRecordSer;

    /**
     * 根据id查询第一次电访记录
     *
     * @param id 第一次电访记录唯一标识
     * @return class FirstPhoneRecordBO
     * @throws SerException
     */
    @Override
    public FirstPhoneRecordBO findById(String id) throws SerException {
        FirstPhoneRecord model = firstPhoneRecordSer.findById(id);
        return BeanTransform.copyProperties(model, FirstPhoneRecordBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 第一次电访记录dto
     * @throws SerException
     */
    @Override
    public Long count(FirstPhoneRecordDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getPosition())){
            dto.getConditions().add(Restrict.like("position",dto.getPosition()));
        }
        if(StringUtils.isNotBlank(dto.getName())){
            dto.getConditions().add(Restrict.like("name",dto.getName()));
        }
        if(StringUtils.isNotBlank(dto.getStartDate()) && StringUtils.isNotBlank(dto.getEndDate())){
            dto.getConditions().add(Restrict.between("date",new String[]{dto.getStartDate(),dto.getEndDate()}));
        }
        return firstPhoneRecordSer.count(dto);
    }

    /**
     * 分页查询第一次电访记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException {
        return firstPhoneRecordSer.list(dto);
    }

    /**
     * 保存第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @return
     * @throws SerException
     */
    @Override
    @Transactional
    public FirstPhoneRecordBO save(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {
        return firstPhoneRecordSer.save(firstPhoneRecordTO);
    }

    /**
     * 根据id删除第一次电访记录
     *
     * @param id 第一次电访记录id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        firstPhoneRecordSer.remove(id);
    }

    /**
     * 更新第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @throws SerException
     */
    @Override
    public void update(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException {
        firstPhoneRecordSer.update(firstPhoneRecordTO);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return firstPhoneRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return firstPhoneRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Set<String> allFirstName() throws SerException {
        return firstPhoneRecordSer.allFirstName();
    }

    @Override
    public FirstPhoneRecordBO importExcel(List<FirstPhoneRecordTO> firstPhoneRecordTOS) throws SerException {
        return firstPhoneRecordSer.importExcel(firstPhoneRecordTOS);
    }

    @Override
    public byte[] exportExcel(FirstPhoneRecordDTO dto) throws SerException {
        return firstPhoneRecordSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return firstPhoneRecordSer.templateExport();
    }


}
