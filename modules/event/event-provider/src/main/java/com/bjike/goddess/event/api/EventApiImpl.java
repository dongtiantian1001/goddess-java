package com.bjike.goddess.event.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.event.bo.*;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.entity.Event;
import com.bjike.goddess.event.service.EventSer;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.event.to.GuidePermissionTO;
import com.bjike.goddess.event.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事件业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("eventApiImpl")
public class EventApiImpl implements EventAPI {
    @Autowired
    private EventSer eventSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return eventSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return eventSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<FatherBO> list(FatherDTO dto) throws SerException {
        return eventSer.list(dto);
    }

    @Override
    public EventBO save(EventTO to) throws SerException {
        return eventSer.save(to);
    }

    @Override
    public void delete(String id) throws SerException {
        eventSer.remove(id);
    }

    @Override
    public String findId(String eventId, String name) throws SerException {
        EventDTO dto = new EventDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("eventId", eventId));
        Event event = eventSer.findOne(dto);
        if (null != event) {
            return event.getId();
        }
        return null;
    }

    @Override
    public void edit(EventTO to) throws SerException {
        eventSer.edit(to);
    }

    @Override
    public EventBO findByID(String id) throws SerException {
        return eventSer.findByID(id);
    }

    @Override
    public void quarzt() throws SerException {
        eventSer.quarzt();
    }

    @Override
    public List<ContentBO> findByMonth(EventDTO dto) throws SerException {
        return eventSer.findByMonth(dto);
    }

    @Override
    public List<FatherBO> allList(FatherDTO dto) throws SerException {
        return eventSer.allList(dto);
    }

    @Override
    public Long allCount(FatherDTO dto) throws SerException {
        return eventSer.allCount(dto);
    }

    @Override
    public Long count(FatherDTO dto) throws SerException {
        return eventSer.count(dto);
    }

    @Override
    public List<AreaCountBO> zongCount(EventDTO dto) throws SerException {
        return eventSer.zongCount(dto);
    }

    @Override
    public List<AreaCountBO> haveDealCount(EventDTO dto) throws SerException {
        return eventSer.haveDealCount(dto);
    }

    @Override
    public List<AreaCountBO> noDealCount(EventDTO dto) throws SerException {
        return eventSer.noDealCount(dto);
    }

    @Override
    public List<AreaCountBO> passNoDealCount(EventDTO dto) throws SerException {
        return eventSer.passNoDealCount(dto);
    }

    @Override
    public List<ClassifyCountBO> classifyCount(EventDTO dto) throws SerException {
        return eventSer.classifyCount(dto);
    }

    @Override
    public List<AppListDataBO> findAppList(String type) throws SerException {
        return eventSer.findAppList(type);
    }

    @Override
    public FatherBO findFatherById(String id) throws SerException {
        return eventSer.findFatherById(id);
    }

    @Override
    public Long currentUserEvenCount() throws SerException {
        return eventSer.currentUserEvenCount();
    }

    @Override
    public List<FatherBO> findByPlanType(EventDTO dto) throws SerException {
        return eventSer.findByPlanType(dto);
    }

    @Override
    public EventBO saveEvTo(EventTO to) throws SerException {
        return eventSer.saveEvTo(to);
    }

    @Override
    public EventBO update(EventTO to) throws SerException {
        return eventSer.update(to);
    }

    @Override
    public List<FatherBO> findByName(EventDTO dto, String name) throws SerException {
        return eventSer.findByName(dto,name);
    }

    @Override
    public List<Object> findAllName(EventDTO dto) throws SerException {
        return eventSer.findAllName(dto);
    }
}