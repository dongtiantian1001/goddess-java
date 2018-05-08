package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.CusPermissionOperateBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.entity.CusPermissionOperate;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;
import com.bjike.goddess.oilcardmanage.service.OilCardReceiveSer;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.organize.bo.AreaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 油卡领用对外发布实现类
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:34]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("oilCardReceiveApiImpl")
public class OilCardReceiveApiImpl implements OilCardReceiveAPI {

    @Autowired
    private OilCardReceiveSer oilCardReceiveSer;

    @Override
    public OilCardReceiveBO saveOilCardReceive(OilCardReceiveTO to) throws SerException {
        return oilCardReceiveSer.insertModel(to);
    }

    @Override
    public OilCardReceiveBO updateOilCardReceive(OilCardReceiveTO to) throws SerException {
        return oilCardReceiveSer.updateModel(to);
    }

    @Override
    public void auditOilCardReceive(String id, String auditSuggestion, OilCardReceiveResult auditResult) throws SerException {
        oilCardReceiveSer.audit(id, auditSuggestion, auditResult);
    }

    @Override
    public void deleteOilCardReceive(String id) throws SerException {
        oilCardReceiveSer.remove(id);
    }

    @Override
    public void returnOilCardReceive(String id) throws SerException {
        oilCardReceiveSer.returnOilCardReceive(id);
    }

    @Override
    public List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException {
        return oilCardReceiveSer.pageList(dto);
    }

    @Override
    public OilCardReceiveBO findOne(String id) throws SerException {
        return oilCardReceiveSer.findOne(id);
    }

    @Override
    public Long count(OilCardReceiveDTO dto) throws SerException {
        return oilCardReceiveSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return oilCardReceiveSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return oilCardReceiveSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<String> findOilCard() throws SerException {
        return oilCardReceiveSer.findOilCard();
    }

    @Override
    public List<AreaBO> findArea() throws SerException {
        return oilCardReceiveSer.findArea();
    }

    @Override
    public List<String> findOperate() throws SerException {
        return oilCardReceiveSer.findOperate();
    }

}
