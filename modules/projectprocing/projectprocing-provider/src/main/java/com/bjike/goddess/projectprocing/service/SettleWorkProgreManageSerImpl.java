package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.projectprocing.api.CommunicationTempleAPI;
import com.bjike.goddess.projectprocing.bo.*;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleProgressManage;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.entity.SettleWorkProgreManage;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.excel.SonPermissionObject;
import com.bjike.goddess.projectprocing.to.CompletionStatusTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算工作进度管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleWorkProgreManageSerImpl extends ServiceImpl<SettleWorkProgreManage, SettleWorkProgreManageDTO> implements SettleWorkProgreManageSer {
    @Autowired
    private NodeHeadersCustomSer nodeHeadersCustomSer;
    @Autowired
    private SettleProgressManageSer settleProgressManageSer;
    @Autowired
    private SettleProgressRecordSer settleProgressRecordSer;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CommunicationTempleSer communicationTempleSer;
    @Autowired
    private HeadersCustomSer headersCustomSer;
    @Autowired
    private NotificationFormulaSer notificationFormulaSer;
    @Autowired
    private OutsourProProgressManageSer outsourProProgressManageSer;
    @Autowired
    private SettlementProcessSer settlementProcessSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
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
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
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
     * 分配责任人权限（部门级别）
     */
    private Boolean guideAllotmentIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 进度模块负责人权限（岗位级别）
     */
    private Boolean guideListIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("10");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSetWorkMana = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("settleWorkprogremanage");
        obj.setDescribesion("结算工作进度管理");
        if (flagSetWorkMana) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagComTemple = communicationTempleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("communicationtemple");
        obj.setDescribesion("各类沟通交流模板");
        if (flagComTemple) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagHeadCust = headersCustomSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("headerscustom");
        obj.setDescribesion("自定义表头设置");
        if (flagHeadCust) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagNodeHeadCust = nodeHeadersCustomSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("nodeheaderscustom");
        obj.setDescribesion("自定义节点表头设置");
        if (flagNodeHeadCust) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagNotiForm = notificationFormulaSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("notificationfFormula");
        obj.setDescribesion("通报机制制定");
        if (flagNotiForm) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutProgress = outsourProProgressManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outsourproprogressmanage");
        obj.setDescribesion("通报机制制定");
        if (flagOutProgress) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSettProcess = settlementProcessSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("settlementprocess");
        obj.setDescribesion("结算流程存储记录");
        if (flagSettProcess) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSettManage = settleProgressManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("settleprogressmanage");
        obj.setDescribesion("结算进度管理");
        if (flagSettManage) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSettRecord = settleProgressRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("settleprogressrecord");
        obj.setDescribesion("结算进度调整记录&结算问题汇总");
        if (flagSettRecord) {
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
            case LIST:
                flag = true;
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
            case ASSIGNEDPERSON:
                flag = guideAllotmentIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        seachCondi(settleWorkProgreManageDTO);
        Long count = super.count(settleWorkProgreManageDTO);
        return count;
    }

    @Override
    public SettleWorkProgreManageBO getOneById(String id) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        return BeanTransform.copyProperties(settleWorkProgreManage, SettleWorkProgreManageBO.class);
    }

    @Override
    public List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
//       checkPermission();
        List<SettleWorkProgreManageBO> settleWorkProgreManageBOList = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean bool = guideListIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if(bool){
            seachCondi(settleWorkProgreManageDTO);
            List<SettleWorkProgreManage> settleWorkProgreManageList = super.findByCis(settleWorkProgreManageDTO, true);
            settleWorkProgreManageBOList = BeanTransform.copyProperties(settleWorkProgreManageList, SettleWorkProgreManageBO.class);
        }else{
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            List<SettleWorkProgreManage> settleWorkProgreManageList = super.findByCis(settleWorkProgreManageDTO, true);
            if(settleWorkProgreManageList!=null && settleWorkProgreManageList.size()>0){
                for (SettleWorkProgreManage settleWorkProgreManage : settleWorkProgreManageList){
                    String name = userBO.getUsername();
                    RpcTransmit.transmitUserToken(userToken);
                    if(name.equals(settleWorkProgreManage.getAllocationPeople()) || name.equals(settleWorkProgreManage.getResponsible())){
                        settleWorkProgreManageBOList.add(BeanTransform.copyProperties(settleWorkProgreManage,SettleWorkProgreManageBO.class));
                    }
                }
            }
        }
        return settleWorkProgreManageBOList;
    }

    private void seachCondi(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        if (StringUtils.isNotBlank(settleWorkProgreManageDTO.getContractNo())) {
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("contractNo", settleWorkProgreManageDTO.getContractNo()));
        }
        if (StringUtils.isNotBlank(settleWorkProgreManageDTO.getDispatchingName())) {
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("dispatchingName", settleWorkProgreManageDTO.getDispatchingName()));
        }
        if (StringUtils.isNotBlank(settleWorkProgreManageDTO.getResponsible())) {
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("responsible", settleWorkProgreManageDTO.getResponsible()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
       checkPermission();
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        SettleWorkProgreManage settleWorkProgreManage = BeanTransform.copyProperties(settleWorkProgreManageTO, SettleWorkProgreManage.class, true);
        settleWorkProgreManage.setCreateTime(LocalDateTime.now());
        settleWorkProgreManage.setAllocationPeople(userBO.getUsername());
        RpcTransmit.transmitUserToken(userToken);
        super.save(settleWorkProgreManage);
        return BeanTransform.copyProperties(settleWorkProgreManage, SettleWorkProgreManageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void redistribution(String id, String responsible) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        settleWorkProgreManage.setResponsible(responsible);
        super.update(settleWorkProgreManage);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSettleWork(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    @Override
    public List<SettleWorkProgreManageBO> listWorkByOutUnit(String outUnit) throws SerException {
        List<NodeHeadersCustomBO> nodeHeadersCustomBOList = nodeHeadersCustomSer.getNodeByOutUnit(outUnit);
        List<SettleWorkProgreManage> settleWorkProgreManageList = new ArrayList<>();
        if (nodeHeadersCustomBOList != null && nodeHeadersCustomBOList.size() > 0) {
            for (NodeHeadersCustomBO nodeHeadersCustomBO : nodeHeadersCustomBOList) {
                String[] str = new String[]{nodeHeadersCustomBO.getNodeOneHeader(), nodeHeadersCustomBO.getNodeTwoHeader(), nodeHeadersCustomBO.getNodeThreeHeader(), nodeHeadersCustomBO.getNodeFourHeader()};
                SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
                settleWorkProgreManageDTO.getConditions().add(Restrict.in("node", str));
                List<SettleWorkProgreManage> settleWorkProgreManageList1 = super.findByCis(settleWorkProgreManageDTO);
                if (settleWorkProgreManageList1 != null && settleWorkProgreManageList1.size() > 0) {
                    settleWorkProgreManageList.addAll(settleWorkProgreManageList1);
                }
            }
        }
        List<SettleWorkProgreManageBO> settleWorkProgreManageBOList = BeanTransform.copyProperties(settleWorkProgreManageList, SettleWorkProgreManageBO.class);
        SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
        int limit = settleWorkProgreManageDTO.getLimit();
        int start = limit * settleWorkProgreManageDTO.getPage();
        return settleWorkProgreManageBOList.stream().skip(start).limit(limit).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void fullFinishStatus(CompletionStatusTO completionStatusTO) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(completionStatusTO.getId());
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if(!settleWorkProgreManage.getResponsible().equals(userBO.getUsername())){
            throw new SerException("您不是责任人,没有权限进行填写完成任务");
        }
        SettleProgressManageBO settleProgressManageBO = settleProgressManageSer.findByContractNo(settleWorkProgreManage.getContractNo());

        if (!completionStatusTO.getComplete()) {
            checkUpdateZD(settleWorkProgreManage, completionStatusTO.getDateTime());//修改节点数据
            //添加至结算进度调整记录&结算问题汇总表中
            SettleProgressRecord settleProgressRecord = new SettleProgressRecord();
            String moneyModuleName = positionDetailUserAPI.moneyModulePerson();
            List<PositionDetailUserBO> positionDetailUserBOS = positionUserDetailAPI.findManager();
            settleProgressRecord.setOutUnit(settleProgressManageBO.getOutUnit());
            settleProgressRecord.setDispatchingName(settleProgressManageBO.getDispatName());
            settleProgressRecord.setInternalName(settleProgressManageBO.getInternalProName());
            settleProgressRecord.setModifier(userBO.getUsername());
            settleProgressRecord.setUpdateDate(LocalDateTime.now());
            settleProgressRecord.setUpdateContent("修改了" + settleWorkProgreManage.getNode() + "节点时间改为" + completionStatusTO.getDateTime());
            settleProgressRecord.setProblemDescription(completionStatusTO.getProblemDescription());
            settleProgressRecord.setProblemType(completionStatusTO.getProblemType());
            settleProgressRecord.setAssistPeoper(completionStatusTO.getAssistPeoper());
            settleProgressRecord.setAssistContent(completionStatusTO.getAssistContent());
            settleProgressRecord.setMoneyModule(moneyModuleName);
            if (positionDetailUserBOS != null && positionDetailUserBOS.size() > 0) {
                settleProgressRecord.setMoneyModule(positionDetailUserBOS.get(0).getName());
            }
            settleProgressRecordSer.save(settleProgressRecord);

            settleWorkProgreManage.setDelayDate(completionStatusTO.getDateTime());//修改本表数据

            //更新结算进度表中的更新时间
            SettleProgressManage settleProgressManage = settleProgressManageSer.findById(settleProgressManageBO.getId());
            settleProgressManage.setChangeDate(true);
            settleProgressManage.setUpdateDate(settleProgressRecord.getUpdateDate());
            settleProgressManageSer.update(settleProgressManage);
        }
        settleWorkProgreManage.setComplete(completionStatusTO.getComplete());
        settleWorkProgreManage.setModifyTime(LocalDateTime.now());
        super.update(settleWorkProgreManage);
    }

    /**
     * 检测修改的是哪个节点然后进行后面节点数据的推算
     *
     * @param settleWorkProgreManage
     * @param dateTime
     * @throws SerException
     */
    private void checkUpdateZD(SettleWorkProgreManage settleWorkProgreManage, String dateTime) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = nodeHeadersCustomSer.findById(settleWorkProgreManage.getNodeId());//本条节点内容
        NodeHeadersCustomBO nodeHeadersCustomBO = nodeHeadersCustomSer.getByFatherId(nodeHeadersCustom.getFatherId());//本条数据的对应父数据(节点表头名)
        if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeOneHeader())) {
            nodeHeadersCustom.setNodeOneContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeTwoContent(nodeHeadersCustom.getNodeOneContent().plusDays(nodeHeadersCustomBO.getNodeTwoInterDate()));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeTwoHeader())) {
            nodeHeadersCustom.setNodeTwoContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeThreeHeader())) {
            nodeHeadersCustom.setNodeThreeContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeFourHeader())) {
            nodeHeadersCustom.setNodeFourContent(DateUtil.parseDate(dateTime));
        }
        nodeHeadersCustom.setModifyTime(LocalDateTime.now());
        nodeHeadersCustomSer.update(nodeHeadersCustom);
    }

    @Override
    public PersonalTasksSummBO personalSummDay(String summDate) throws SerException {
//        checkPermission();
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        return personalTotalMether(startDate,endDate);
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }


    @Override
    public PersonalTasksSummBO personalSummWeek(Integer year,Integer month,Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        return personalTotalMether(startDate,endDate);
    }

    @Override
    public PersonalTasksSummBO personalSummMonth(Integer year,Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));

        return personalTotalMether(startDate,endDate);
    }

    public PersonalTasksSummBO personalTotalMether(String startDate,String endDate) throws SerException{
        List<SettleWorkProgreManage> settleWorkProgreManageList = super.findAll();
        List<String> headers = new ArrayList<>();
        headers.add("负责人");
        headers.add("类型");

        List<String> node_list = settleWorkProgreManageList.stream().map(SettleWorkProgreManage::getNode).distinct().collect(Collectors.toList());//所有的节点
        List<String> respPerson_list = settleWorkProgreManageList.stream().map(SettleWorkProgreManage::getResponsible).distinct().collect(Collectors.toList());//所有的负责人
        headers.addAll(node_list);

        List<PeoperDataBO> peoperDataBOS = new ArrayList<>();

        if(respPerson_list!=null && respPerson_list.size()>0){
            for (String respPerson : respPerson_list){

                //类型对应数据
                List<TypeDataBO> dataBOList = new ArrayList<>();
                //计划
                TypeDataBO typeDataBO1 = new TypeDataBO();
                List<Integer> num1_list = new ArrayList<>();
                for (String node : node_list){
                    String sql = "SELECT count(*) FROM projectprocing_settleworkprogremanage WHERE responsible = '"+respPerson+"' AND node = '"+node+"' AND delayDate BETWEEN '"+startDate+"' AND '"+endDate+"'";
                    List<Object> objectList = super.findBySql(sql);
                    num1_list.add(Integer.parseInt(String.valueOf(objectList.get(0))));
                }
                typeDataBO1.setType("计划");
                typeDataBO1.setNumber(num1_list);
                dataBOList.add(typeDataBO1);

                //遗留
                TypeDataBO typeDataBO2 = new TypeDataBO();
                List<Integer> num2_list = new ArrayList<>();
                for (String node : node_list){
                    String sql = "SELECT count(*) FROM projectprocing_settleworkprogremanage WHERE responsible = '"+respPerson+"' AND node = '"+node+"' AND delayDate BETWEEN '"+startDate+"' AND '"+endDate+"' AND is_complete = 0 ";
                    List<Object> objectList = super.findBySql(sql);
                    num2_list.add(Integer.parseInt(String.valueOf(objectList.get(0))));
                }
                typeDataBO2.setType("遗留");
                typeDataBO2.setNumber(num2_list);
                dataBOList.add(typeDataBO2);

                //实际
                TypeDataBO typeDataBO3 = new TypeDataBO();
                List<Integer> num3_list = new ArrayList<>();
                for (String node : node_list){
                    String sql = "SELECT count(*) FROM projectprocing_settleworkprogremanage WHERE responsible = '"+respPerson+"' AND node = '"+node+"' AND delayDate BETWEEN '"+startDate+"' AND '"+endDate+"' AND is_complete = 1 ";
                    List<Object> objectList = super.findBySql(sql);
                    num3_list.add(Integer.parseInt(String.valueOf(objectList.get(0))));
                }
                typeDataBO3.setType("实际");
                typeDataBO3.setNumber(num3_list);
                dataBOList.add(typeDataBO3);

                //责任人对应数据
                PeoperDataBO peoperDataBO = new PeoperDataBO();
                peoperDataBO.setRespPerson(respPerson);
                peoperDataBO.setTypeDataBOS(dataBOList);
                peoperDataBOS.add(peoperDataBO);
            }
        }
        PersonalTasksSummBO personalTasksSummBO = new PersonalTasksSummBO();
        personalTasksSummBO.setHeaderData(headers);
        personalTasksSummBO.setPeoperDataBOList(peoperDataBOS);
        return personalTasksSummBO;
    }
}