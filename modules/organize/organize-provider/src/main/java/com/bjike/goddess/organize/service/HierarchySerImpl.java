package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.dto.HierarchyDTO;
import com.bjike.goddess.organize.entity.Hierarchy;
import com.bjike.goddess.organize.to.HierarchyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 体系业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:30]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class HierarchySerImpl extends ServiceImpl<Hierarchy, HierarchyDTO> implements HierarchySer {

    @Override
    public List<HierarchyBO> findStatus() throws SerException {
        HierarchyDTO dto = new HierarchyDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Hierarchy> list = super.findByCis(dto);
        List<HierarchyBO> bos = BeanTransform.copyProperties(list, HierarchyBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HierarchyBO save(HierarchyTO to) throws SerException {
        Hierarchy hierarchy = BeanTransform.copyProperties(to, Hierarchy.class);
        hierarchy.setCreateTime(LocalDateTime.now());
        hierarchy.setStatus(Status.THAW);
        super.save(hierarchy);
        return BeanTransform.copyProperties(hierarchy, HierarchyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HierarchyBO update(HierarchyTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        Hierarchy entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, HierarchyBO.class);
    }

    @Override
    public HierarchyBO delete(String id) throws SerException {
        Hierarchy entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return BeanTransform.copyProperties(entity, HierarchyBO.class);
    }

    @Override
    public HierarchyBO congeal(String id) throws SerException {
        Hierarchy entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, HierarchyBO.class);
    }

    @Override
    public HierarchyBO thaw(String id) throws SerException {
        Hierarchy entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, HierarchyBO.class);
    }

    @Override
    public List<HierarchyBO> maps(HierarchyDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), HierarchyBO.class);
    }
}
