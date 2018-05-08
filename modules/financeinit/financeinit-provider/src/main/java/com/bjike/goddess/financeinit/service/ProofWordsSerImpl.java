package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.ProofWordsBO;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.entity.ProofWords;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.ProofWordsTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 凭证字业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class ProofWordsSerImpl extends ServiceImpl<ProofWords, ProofWordsDTO> implements ProofWordsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应财务部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应财务部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countProof(ProofWordsDTO proofWordsDTO) throws SerException {
        proofWordsDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        Long count = super.count(proofWordsDTO);
        return count;
    }

    @Override
    public ProofWordsBO getOneById(String id) throws SerException {
        ProofWords proofWords = super.findById(id);
        return BeanTransform.copyProperties(proofWords, ProofWordsBO.class);
    }

    @Override
    public List<ProofWordsBO> listProof(ProofWordsDTO proofWordsDTO) throws SerException {
        checkSeeIdentity();
        proofWordsDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        List<ProofWords> proofWords = super.findByCis(proofWordsDTO);
        return BeanTransform.copyProperties(proofWords, ProofWordsBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public ProofWordsBO addProof(ProofWordsTO proofWordsTO) throws SerException {
        checkAddIdentity();
        String systemId = getSystemId();
        ProofWordsDTO proofWordsDTO = new ProofWordsDTO();
        proofWordsDTO.getConditions().add(Restrict.eq("systemId", systemId));
        proofWordsDTO.getConditions().add(Restrict.eq("proofCharacter", proofWordsTO.getProofCharacter()));
        List<ProofWords> olds = super.findByCis(proofWordsDTO);
        if (olds != null && olds.size() > 0) {
            throw new SerException("'" + proofWordsTO.getProofCharacter() + "'已存在，不可重复添加");
        }
        ProofWords proofWords = BeanTransform.copyProperties(proofWordsTO, ProofWords.class, true);
        proofWords.setCreateTime(LocalDateTime.now());
        proofWords.setSystemId(systemId);
        super.save(proofWords);
        return BeanTransform.copyProperties(proofWords, ProofWordsBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void delete(String id) throws SerException {
        ProofWordsDTO proofWordsDTO = new ProofWordsDTO();
        proofWordsDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        List<ProofWords> proofWords = super.findByCis(proofWordsDTO);
        if (proofWords == null || proofWords.size() <= 1) {
            throw new SerException("至少存在一条数据，不可删除");
        }
        super.remove(id);
    }

    @Override
    public void update(ProofWordsTO to) throws SerException {
        ProofWords entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("指定实体不存在");
        }
        entity.setProofCharacter(to.getProofCharacter());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 获取公司编号
     *
     * @return
     * @throws SerException
     */
    private String getSystemId() throws SerException {
        String token = RpcTransmit.getUserToken();
        String systemId = userAPI.currentSysNO();
        RpcTransmit.transmitUserToken(token);
        return systemId;
    }
}