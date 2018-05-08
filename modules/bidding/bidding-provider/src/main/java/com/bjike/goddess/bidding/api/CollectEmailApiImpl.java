package com.bjike.goddess.bidding.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.bidding.bo.CollectEmailBO;
import com.bjike.goddess.bidding.dto.CollectEmailDTO;
import com.bjike.goddess.bidding.service.CollectEmailSer;
import com.bjike.goddess.bidding.to.CollectEmailTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招投标信息邮件发送定制接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 招投标信息邮件发送定制接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("biddingEmailApiImpl")
public class CollectEmailApiImpl implements CollectEmailAPI {

    @Autowired
    private CollectEmailSer collectEmailSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return collectEmailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return collectEmailSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.counts(collectEmailDTO);
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        return collectEmailSer.getOne(id);
    }
    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.listCollectEmail( collectEmailDTO );
    }

    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.addCollectEmail( collectEmailTO);
    }

    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.editCollectEmail(collectEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        collectEmailSer.deleteCollectEmail(id);
    }
    @Override
    public void congealCollectEmail(String id) throws SerException {
        collectEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        collectEmailSer.thawCollectEmail(id);
    }
    @Override
    public void checkSendEmail() throws SerException {
         collectEmailSer.checkSendEmail();
    }
}