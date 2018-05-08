package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.CommunicationFormwork;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.excel.SonPermissionObject;
import com.bjike.goddess.regularization.to.*;
import com.bjike.goddess.regularization.type.GuideAddrStatus;
import com.bjike.goddess.regularization.type.StaffStatus;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 员工转正业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class RegularizationSerImpl extends ServiceImpl<Regularization, RegularizationDTO> implements RegularizationSer {

    @Autowired
    private ManagementScoreSer managementScoreSer;
    @Autowired
    private PerformanceScoreSer performanceScoreSer;
    @Autowired
    private ScoreFormulaSetSer scoreFormulaSetSer;
    @Autowired
    private TimeCriteriaSetSer timeCriteriaSetSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private TransferInfoSer transferInfoSer;
    @Autowired
    private CommunicationFormworkSer communicationFormworkSer;

    /**
     * 检查员工转正查看权限(模块)
     *
     * @throws SerException
     */
    private Boolean checkzzSeePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);

        return flag;
    }

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(层次)
     *
     * @throws SerException
     */
    private void checkArrPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private void checkModWPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private void checkModPPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private void checkModBPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(模块负责人审核)
     *
     * @throws SerException
     */
    private void checkModPepolPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是模块负责人,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 检查权限(总经理岗位)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经理,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理岗位)
     *
     * @throws SerException
     */
    private void checkManagePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是项目经理,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对层次审核权限（层次）
     */
    private Boolean guideArrIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModWIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModPIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 核对模块负责人审核权限(岗位级别)
     *
     * @throws SerException
     */
    private Boolean guideModPepolIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对项目经理审核权限(岗位)
     *
     * @throws SerException
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {
        return true;
    }



    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {

        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuide = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModP = guideModPIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModB = guideModBIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideArr = guideArrIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllTrue = guideAllTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("regularization");
        obj.setDescribesion("员工转正");
        if (flagGuide || flagGuideModP || flagGuideModB || flagGuidePosi || flagGuideArr || flagAllTrue) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidPer = performanceScoreSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("performancescore");
        obj.setDescribesion("工作表现评分");
        if (flagGuidPer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidScore = scoreFormulaSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("scoreformulaset");
        obj.setDescribesion("工作表现计分方式设置");
        if (flagGuidScore) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidTime = timeCriteriaSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("timecriteriaset");
        obj.setDescribesion("时间条件设置");
        if (flagGuidTime) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagTransfer = transferInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("transferinfo");
        obj.setDescribesion("转正人员信息");
        if (flagTransfer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagcommunication = communicationFormworkSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("communicationformwork");
        obj.setDescribesion("各类沟通交流模版");
        if (flagcommunication) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;

    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {

            case ZZLIST:
                flag = guideAllTrueIdentity();
                break;
            case ZZADD:
                flag = guideAllTrueIdentity();
                break;
            case ZZEDIT:
                flag = guideAllTrueIdentity();
                break;
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case MANAGSCORE:
                flag = guideArrIdentity();
                break;
            case DECISIONSCORE:
                flag = guideArrIdentity();
                break;
            case PLANMODUL:
                flag = guideModPIdentity();
                break;
            case BUDGETMODUL:
                flag = guideModBIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case WELFAREASSESS:
                flag = guideModWIdentity();
                break;
            case PLANASSESS:
                flag = guideModPIdentity();
                break;
            case BUDGETASSESS:
                flag = guideModBIdentity();
                break;
            case MODULERESPON:
                flag = guideModPepolIdentity();
                break;
            case PROJECTMANAGE:
                flag = guideManageIdentity();
                break;
            case GENMANAGE:
                flag = guidePosinIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;

        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询员工转正
     *
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    public List<RegularizationBO> list(RegularizationDTO dto) throws SerException {
        List<RegularizationBO> listBO = new ArrayList<>();
        if (checkzzSeePermission()) {
            List<Regularization> list = super.findByPage(dto);
            listBO = BeanTransform.copyProperties(list, RegularizationBO.class);
        } else {
            RegularizationDTO regularizationDTO = new RegularizationDTO();
            String userName = getCurUsername();
            regularizationDTO.getConditions().add(Restrict.eq("name", userName));
            List<Regularization> regularizations = super.findByCis(regularizationDTO);
            listBO = BeanTransform.copyProperties(regularizations, RegularizationBO.class);
        }
        return listBO;
    }

    /**
     * 保存员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RegularizationBO save(RegularizationTO to) throws SerException {
        Regularization entity = BeanTransform.copyProperties(to, Regularization.class, true);
        entity.setCreateTime(LocalDateTime.now());
        LocalDate time = DateUtil.parseDate(to.getHiredate());
        LocalDate date = LocalDate.now();
        int monthDiff = (date.getYear() - time.getYear()) * 12 + date.getMonthValue() - time.getMonthValue();
        entity.setAsProbationLength(monthDiff);
        entity = super.save(entity);
        RegularizationBO bo = BeanTransform.copyProperties(entity, RegularizationBO.class);
        //将转正信息添加到转正人员信息中
        TransferInfoBO transferInfoBO = transferInfoSer.findByEmpNo(to.getEmpNo());
        if(transferInfoBO!=null){
            TransferInfo transferInfo = transferInfoSer.findById(transferInfoBO.getId());
            transferInfo.setModifyTime(LocalDateTime.now());
            transferInfo.setStaffStatus(StaffStatus.POSITIVE);
            transferInfo.setApplyDate(entity.getRegularDate());
            transferInfo.setAsProbationLength(entity.getAsProbationLength());
            transferInfo.setConfirmEvent(entity.getConfirmEvent());
            transferInfo.setConfirmPeople(entity.getConfirmPeople());
            transferInfoSer.update(transferInfo);
        }
        return bo;
    }

    /**
     * 更新员工转正
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(RegularizationTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            Regularization model = super.findById(to.getId());
            if (model != null) {
                updateRegularization(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新员工转正
     *
     * @param to    员工转正to
     * @param model 员工转正
     * @throws SerException
     */
    private void updateRegularization(RegularizationTO to, Regularization model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
        //TODO 将修改的转正信息修改到转正人员信息中 lijuntao 由于不知道怎么处理状态
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        checkPermission();
        Regularization regularization = super.findById(id);
        removeManageScore(id);
        super.remove(id);
        //TODO 删除转正信息时修改到转正人员信息中 lijuntao 由于不知道怎么处理状态
    }

    /**
     * 删除管理层评分
     *
     * @param id 员工转正id
     * @throws SerException
     */
    private void removeManageScore(String id) throws SerException {
        ManagementScoreDTO dto = new ManagementScoreDTO();
        dto.getConditions().add(Restrict.eq("regularizationId", id));
        List<ManagementScore> list = managementScoreSer.findByCis(dto);
        managementScoreSer.remove(list);//删除管理层评分
    }

    /**
     * 管理层评分
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void manageScore(String id, ManagementScoreTO to) throws SerException {
        checkArrPermission();
        String username = getCurUsername();
        ManagementScore model = setManagementScore(id, to, username);
        managementScoreSer.save(model);
        List<ManagementScoreBO> managementScoreBOS = managementScoreSer.findByRegularId(id);
        Integer managementSum = 0;
        Double managementAverage = 0d;
        if (managementScoreBOS != null && managementScoreBOS.size() > 0) {
            managementSum = managementScoreBOS.stream().mapToInt(ManagementScoreBO::getSpecificScore).sum();
            managementAverage = Double.valueOf(managementSum / managementScoreBOS.size());
        }
        Regularization regularization = super.findById(id);
        regularization.setManagementAverage(managementAverage);
        super.update(regularization);
    }

    /**
     * 设置管理层评分
     *
     * @param id
     * @param to
     * @param username
     * @return
     */
    private ManagementScore setManagementScore(String id, ManagementScoreTO to, String username) {
        String opinion = to.getOpinion();//获取管理层意见
        String scoreGrade = to.getScoreGrade();//获取评分等级
        Integer specificScore = to.getSpecificScore();//获取具体评价分数
        ManagementScore model = new ManagementScore();
        model.setManagement(username);
        model.setOpinion(opinion);
        model.setScoreGrade(scoreGrade);
        model.setSpecificScore(specificScore);
        model.setRegularizationId(id);
        return model;
    }

    /**
     * 获取当前用户名
     *
     * @return
     * @throws SerException
     */
    private String getCurUsername() throws SerException {
        UserBO user = userAPI.currentUser();
        return user.getUsername();
    }

    /**
     * 查看管理层评分
     *
     * @param id 员工转正唯一标识
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> checkManageScore(String id) throws SerException {
        checkArrPermission();
        if (StringUtils.isNotBlank(id)) {
            ManagementScoreDTO dto = new ManagementScoreDTO();
            dto.getConditions().add(Restrict.eq("regularizationId", id));
            List<ManagementScore> list = managementScoreSer.findByCis(dto);
            List<ManagementScoreBO> listBO = BeanTransform.copyProperties(list, ManagementScoreBO.class);
            return listBO;
        } else {
            throw new SerException("员工转正id不能为空!");
        }

    }

    /**
     * 决策层评价
     *
     * @param id                    员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank     决策层评分等级
     * @param decisionLevelScore    决策层具体评分
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void decisionLevelEvaluate(String id, String decisionLevelEvaluate, String decisionLevelRank, Integer decisionLevelScore) throws SerException {
        checkArrPermission();
        String curUsername = getCurUsername();
        Regularization model = super.findById(id);
        if (model == null) {
            throw new SerException("查询对象为空,无法进行决策层评价操作操作.");
        }
        model.setDecisionLevelEvaluate(decisionLevelEvaluate);
        model.setDecisionLevelRank(decisionLevelRank);
        model.setDecisionLevelScore(decisionLevelScore);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void planModuleSupply(PlanModuleSupplyTO to) throws SerException {
        checkModPPermission();
        String curUsername = getCurUsername();
        Regularization model = super.findById(to.getId());
        if (model == null) {
            throw new SerException("查询对象为空,无法进行该操作.");
        }
        model.setAfterPost(to.getAfterPost());//转正后岗位
        model.setAfterSkillRank(to.getAfterSkillRank());//转正技能定级
        model.setPlanPositiveComment(to.getPlanPositiveComment());//规划模块转正意见
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 预算模块补充
     *
     * @param id                    员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void budgetModuleSupply(String id, String budgetPositiveComment) throws SerException {
        checkModBPermission();
        String curUsername = getCurUsername();
        Regularization model = super.findById(id);
        if (model == null) {
            throw new SerException("查询对象为空,无法进行预算模块补充操作.");
        }
        model.setBudgetPositiveComment(budgetPositiveComment);//预算模块转正意见
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void zjbApproval(ZjbApprovalTO to) throws SerException {
        checkPonsPermission();
        String curUsername = getCurUsername();
        Regularization model = super.findById(to.getId());
        if (model == null) {
            throw new SerException("查询对象为空,无法进行该操作.");
        }
        model.setModifyTime(LocalDateTime.now());
        model.setPositiveType(to.getPositiveType());//转正类型
        model.setZjbAppraise(to.getZjbAppraise());//总经办评价
        model.setPositiveDate(DateUtil.parseDate(to.getPositiveDate()));
        super.update(model);
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.findStatus();
        if (CollectionUtils.isEmpty(departmentDetailBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DepartmentDetailBO departmentDetailBO : departmentDetailBOS) {
            String details = departmentDetailBO.getDepartment();
            if (StringUtils.isNotBlank(departmentDetailBO.getDepartment())) {
                set.add(details);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS) {
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public RegularizationBO findAddRusult(String empNumer) throws SerException {
        EntryOptionBO entryOptionBO = entryRegisterAPI.getEntryOptionByEmpNum(empNumer);
        RegularizationBO regularizationBO = new RegularizationBO();
        regularizationBO.setName(entryOptionBO.getName());
        regularizationBO.setHiredate(entryOptionBO.getEntryTime());
        regularizationBO.setProfession(entryOptionBO.getProfession());
        regularizationBO.setEducation(entryOptionBO.getEducation());
        regularizationBO.setArea(entryOptionBO.getArea());
        regularizationBO.setProjectGroup(entryOptionBO.getDepartment());
        regularizationBO.setPost(entryOptionBO.getPosition());
        return regularizationBO;
    }

    @Override
    public List<RegularizationBO> findByName() throws SerException {
        RegularizationDTO regularizationDTO = new RegularizationDTO();
        String userName = getCurUsername();
        regularizationDTO.getConditions().add(Restrict.eq("name", userName));
        List<Regularization> regularizations = super.findByCis(regularizationDTO);
        return BeanTransform.copyProperties(regularizations, RegularizationBO.class);
    }

    @Override
    //chenjunhao
    public String time(String empNo) throws SerException {
        RegularizationDTO dto = new RegularizationDTO();
        dto.getConditions().add(Restrict.eq("empNo", empNo));
        List<Regularization> list = super.findByCis(dto);
        if (list != null && !list.isEmpty()) {
            return DateUtil.dateToString(list.get(0).getPositiveDate());
        }
        return null;
    }


    @Override
    //chenjunhao
    public Set<String> allNum() throws SerException {
        List<Regularization> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Regularization r : list) {
            set.add(r.getEmpNo());
        }
        return set;
    }

    @Override
    public String getTime(String userName) throws SerException {
        String time = null;
        RegularizationDTO dto = new RegularizationDTO();
        dto.getConditions().add(Restrict.eq("name", userName));
        List<Regularization> regularizations = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(regularizations)) {
            time = regularizations.get(0).getPositiveDate().toString();
        }
        return time;
    }

    @Override
    public Boolean checkTran(String userName) throws SerException {
        Boolean bool = false;
        RegularizationDTO dto = new RegularizationDTO();
        dto.getConditions().add(Restrict.eq("name", userName));
        List<Regularization> regularizations = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(regularizations)) {
            if(regularizations.get(0).getPassed()){
                bool=true;
            }
        }
        return bool;
    }

    @Override
    public RegularizationBO findByEmpNo(String empNo) throws SerException {
        RegularizationDTO regularizationDTO = new RegularizationDTO();
        regularizationDTO.getConditions().add(Restrict.eq("empNo", empNo));
        Regularization regularization = super.findOne(regularizationDTO);
        return BeanTransform.copyProperties(regularization, RegularizationBO.class);
    }
}