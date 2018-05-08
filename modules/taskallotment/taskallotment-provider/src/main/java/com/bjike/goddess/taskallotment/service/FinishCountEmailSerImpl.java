package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.FinishCountEmail;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.enums.*;
import com.bjike.goddess.taskallotment.to.FinishCountEmailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 完成情况汇总设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class FinishCountEmailSerImpl extends ServiceImpl<FinishCountEmail, FinishCountEmailDTO> implements FinishCountEmailSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private TaskNodeSer taskNodeSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FinishCountEmailBO save(FinishCountEmailTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        FinishCountEmail entity = BeanTransform.copyProperties(to, FinishCountEmail.class, true);
        long mis = DateUtil.mis(entity.getSetTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置时间必须大于当前时间");
        }
        if (null == to.getCountFrequency() && StringUtils.isBlank(to.getStartTime()) && StringUtils.isBlank(to.getEndTime())) {
            throw new SerException("汇总频率和汇总时间区间必须选一个，且只能有一个");
        }
        if (null != to.getCountFrequency() && StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getStartTime())) {
            throw new SerException("汇总频率和汇总时间区间只能有一个");
        }
        entity.setCreater(name);
        String[] countPersonss = to.getCountPersonss();
        String[] areas = to.getAreas();
        String[] departs = to.getDeparts();
        String[] projects = to.getProjects();
        String[] tables = to.getTables();
        String[] forDepartss = to.getForDepartss();
        String[] forPersonss = to.getForPersonss();
        switch (entity.getCountType()) {
            case DEPART:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                break;
            case PERSON:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                entity.setCountPersons(arraysTOString(countPersonss));
                break;
        }
        switch (entity.getForObject()) {
            case DEPART:
                entity.setForDeparts(arraysTOString(forDepartss));
                break;
            case PERSON:
                entity.setForPersons(arraysTOString(forPersonss));
                break;
        }
        int remind = entity.getRemind();
        LocalDateTime lastTime = entity.getLastTime();
        switch (entity.getSpacing()) {
            case MINTUE:
                lastTime = entity.getSetTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime = entity.getSetTime().minusHours(remind);
                break;
            case DAY:
                lastTime = entity.getSetTime().minusDays(remind);
                break;
            case WEEK:
                lastTime = entity.getSetTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime = entity.getSetTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        super.save(entity);
        return BeanTransform.copyProperties(entity, FinishCountEmailBO.class);
    }

    private String arraysTOString(String[] strings) throws SerException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                sb.append(strings[i]);
            } else {
                sb.append(strings[i] + ",");
            }
        }
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(FinishCountEmailTO to) throws SerException {
        FinishCountEmail entity = super.findById(to.getId());
        FinishCountEmail finishCountEmail = BeanTransform.copyProperties(to, FinishCountEmail.class, true);
        BeanUtils.copyProperties(finishCountEmail, entity, "id", "creater", "createTime");
        long mis = DateUtil.mis(entity.getSetTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置时间必须大于当前时间");
        }
        if (null == to.getCountFrequency() && StringUtils.isBlank(to.getStartTime()) && StringUtils.isBlank(to.getEndTime())) {
            throw new SerException("汇总频率和汇总时间区间必须选一个，且只能有一个");
        }
        if (null != to.getCountFrequency() && StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getStartTime())) {
            throw new SerException("汇总频率和汇总时间区间只能有一个");
        }
        String[] countPersonss = to.getCountPersonss();
        String[] areas = to.getAreas();
        String[] departs = to.getDeparts();
        String[] projects = to.getProjects();
        String[] tables = to.getTables();
        String[] forDepartss = to.getForDepartss();
        String[] forPersonss = to.getForPersonss();
        switch (entity.getCountType()) {
            case DEPART:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                break;
            case PERSON:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                entity.setCountPersons(arraysTOString(countPersonss));
                break;
        }
        switch (entity.getForObject()) {
            case DEPART:
                entity.setForDeparts(arraysTOString(forDepartss));
                break;
            case PERSON:
                entity.setForPersons(arraysTOString(forPersonss));
                break;
        }
        int remind = entity.getRemind();
        LocalDateTime lastTime = entity.getLastTime();
        switch (entity.getSpacing()) {
            case MINTUE:
                lastTime = entity.getSetTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime = entity.getSetTime().minusHours(remind);
                break;
            case DAY:
                lastTime = entity.getSetTime().minusDays(remind);
                break;
            case WEEK:
                lastTime = entity.getSetTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime = entity.getSetTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<FinishCountEmailBO> list(FinishCountEmailDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<FinishCountEmail> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, FinishCountEmailBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        FinishCountEmail entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public FinishCountEmailBO findByID(String id) throws SerException {
        FinishCountEmail entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String[] departs = null;
        String[] projects = null;
        String[] tables = null;
        String[] forDepart = null;
        if (null != entity.getForDeparts()) {
            forDepart = entity.getForDeparts().split(",");
        }
        if (null != entity.getDepart()) {
            departs = entity.getDepart().split(",");
        }
        if (null != entity.getProject()) {
            projects = entity.getProject().split(",");
        }
        if (null != entity.getTable()) {
            tables = entity.getTable().split(",");
        }
        List<ProjectBO> projectBOS = new ArrayList<>();
        List<TableBO> tableBOS = new ArrayList<>();
        if (null != projects) {
            for (String project : projects) {
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO.getConditions().add(Restrict.eq("project", project));
                List<Project> projects1 = projectSer.findByCis(projectDTO);
                if (!projects1.isEmpty()) {
                    projectBOS.add(BeanTransform.copyProperties(projects1.get(0), ProjectBO.class));
                }
            }
        }
        if (null != tables) {
            for (String project : tables) {
                TableDTO projectDTO = new TableDTO();
                projectDTO.getConditions().add(Restrict.eq("name", project));
                List<Table> projects1 = tableSer.findByCis(projectDTO);
                if (!projects1.isEmpty()) {
                    tableBOS.add(BeanTransform.copyProperties(projects1.get(0), TableBO.class));
                }
            }
        }
        List<DepartmentDetailBO> detailBOS = new ArrayList<>();
        List<DepartmentDetailBO> forDetailBOS = new ArrayList<>();
        if (null != departs) {
            detailBOS = departmentDetailAPI.departByName(departs);
        }
        if (null != forDepart) {
            forDetailBOS = departmentDetailAPI.departByName(forDepart);
        }
        FinishCountEmailBO bo = BeanTransform.copyProperties(entity, FinishCountEmailBO.class);
        bo.setDeparts(detailBOS);
        bo.setProjects(projectBOS);
        bo.setTables(tableBOS);
        bo.setForDepart(forDetailBOS);
        return bo;
    }

    @Override
    public Long count(FinishCountEmailDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        FinishCountEmailDTO dto = new FinishCountEmailDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<FinishCountEmail> list = super.findByCis(dto);
        for (FinishCountEmail f : list) {
            ForObject forObject = f.getForObject();
            String[] mails = null;
            switch (forObject) {
                case ALL:
                    List<UserBO> users = positionDetailUserAPI.findUserListInOrgan();
                    List<String> names = users.stream().map(userBO -> userBO.getUsername()).collect(Collectors.toList());
                    String[] name = new String[names.size()];
                    name = names.toArray(name);
                    List<String> emails = internalContactsAPI.getEmails(name);
                    mails = new String[emails.size()];
                    mails = emails.toArray(mails);
                    break;
                case DEPART:
                    String[] forDeparts = f.getForDeparts().split(",");   //存的是部门id
                    List<String> list1 = new ArrayList<>();
                    for (String departId : forDeparts) {
                        CommonalityBO commonalityBO = commonalityAPI.findByDepartment(departId);
                        if (null != commonalityBO) {
                            list1.add(commonalityBO.getEmail());
                        }
                    }
                    if (!list1.isEmpty()) {
                        mails = new String[list1.size()];
                        mails = list1.toArray(mails);
                    }
                    break;
                case PERSON:
                    String[] strings = f.getForPersons().split(",");
                    List<String> stringList = internalContactsAPI.getEmails(strings);
                    if (null != stringList && !stringList.isEmpty()) {
                        mails = new String[stringList.size()];
                        mails = stringList.toArray(mails);
                    }
                    break;
            }
            Spacing spacing = f.getSpacing();
            int remind = f.getRemind();
            LocalDateTime lastTime = f.getLastTime();
            LocalDateTime time = null;
            switch (spacing) {
                case MINTUE:
                    time = lastTime.plusMinutes(remind);
                    break;
                case HOUR:
                    time = lastTime.plusHours(remind);
                    break;
                case DAY:
                    time = lastTime.plusDays(remind);
                    break;
                case WEEK:
                    time = lastTime.plusWeeks(remind);
                    break;
                case MONTH:
                    time = lastTime.plusMonths(remind);
                    break;
            }
            long mis = DateUtil.mis(LocalDateTime.now(), time);
            if (mis >= 0) {
                String content = html(finishCaseBOS(f));
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle("完成情况汇总");
                messageTO.setContent(content);
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (null != mails && mails.length > 0) {
                    messageTO.setReceivers(mails);
                }
                messageAPI.send(messageTO);
                f.setLastTime(LocalDateTime.now());
                f.setModifyTime(LocalDateTime.now());
                super.update(f);
            }
        }
    }

    private List<FinishCaseBO> finishCaseBOS(FinishCountEmail f) throws SerException {
        TaskNodeDTO taskNodeDTO = new TaskNodeDTO();
        LocalDate startTime = f.getStartTime();
        LocalDate endTime = f.getEndTime();
        CountType countType = f.getCountType();
        if (CountType.WHOLE.equals(f.getCountType())) {     //整体汇总
            List<String> areas = taskNodeSer.areas();
            List<String> departs = taskNodeSer.departs();
            if (!areas.isEmpty()) {
                String[] area = new String[areas.size()];
                area = areas.toArray(area);
                taskNodeDTO.setArea(area);
            } else {
                taskNodeDTO.setArea(f.getArea().split(","));
            }
            if (!departs.isEmpty()) {
                String[] depart = new String[departs.size()];
                depart = departs.toArray(depart);
                taskNodeDTO.setDepart(depart);
            } else {
                taskNodeDTO.setDepart(f.getDepart().split(","));
            }
        } else {    //其他汇总
            taskNodeDTO.setArea(f.getArea().split(","));
            taskNodeDTO.setDepart(f.getDepart().split(","));
            taskNodeDTO.setTables(f.getTable().split(","));
        }
        if (CountType.PERSON.equals(f.getCountType())) {
            String[] names = f.getCountPersons().split(",");
            taskNodeDTO.setName(names);
        }
        taskNodeDTO.setCountType(countType);
        if (null == startTime && null == endTime) {    //没有汇总时间段就用汇总频率
            CountFrequency countFrequency = f.getCountFrequency();
            switch (countFrequency) {
                case DAY:   //每天
                    startTime = LocalDate.now();
                    endTime = LocalDate.now();
                    break;
                case WEEK:
                    startTime = DateUtil.getStartWeek();
                    endTime = DateUtil.getEndWeek();
                    break;
                case MONTH:
                    startTime = DateUtil.getStartMonth();
                    endTime = DateUtil.getEndMonth();
                    break;
                case SEASON:
                    String s = DateUtil.dateToString(DateUtil.getStartQuart());
                    startTime = DateUtil.parseDate(s.substring(0, s.indexOf(" ")));
                    String e = DateUtil.dateToString(DateUtil.getEndQuart());
                    endTime = DateUtil.parseDate(e.substring(0, e.indexOf(" ")));
                    break;
                case YEAR:
                    startTime = DateUtil.getStartYear();
                    endTime = DateUtil.getEndYear();
                    break;
            }
        }
        taskNodeDTO.setStartTime(DateUtil.dateToString(startTime));
        taskNodeDTO.setEndTime(DateUtil.dateToString(endTime));
        List<FinishCaseBO> finishCaseBOS = taskNodeSer.finishCount(taskNodeDTO);
        return finishCaseBOS;
    }

    private String html(List<FinishCaseBO> finishCaseBOS) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>完成情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>地区</td>");
        sb.append("<td>项目组/部门</td>");
        sb.append("<td>项目表</td>");
        sb.append("<td>姓名</td>");
        sb.append("<td>任务类型</td>");
        sb.append("<td>计划任务量</td>");
        sb.append("<td>完成任务量</td>");
        sb.append("<td>未完成量</td>");
        sb.append("<td>计划总工时（小时）</td>");
        sb.append("<td>已完成工时(小时)</td>");
        sb.append("<td>未完成工时(小时)</td>");
        sb.append("<td>上报未完成工时(小时)</td>");
        sb.append("<td>未上报未完成工时(小时)</td>");
        sb.append("</tr>");
        for (int i = 0; i < finishCaseBOS.size(); i++) {
            int oneSize = num(finishCaseBOS.get(i));
            int twoSize = 0;
            List<CaseSonBO> sons = finishCaseBOS.get(i).getCaseSonS();
            if (i < finishCaseBOS.size() - 2) {
                twoSize = (oneSize - 1) / (sons.size() - 1);
                sb.append("<tr>");
                if (null != finishCaseBOS.get(i).getArea()) {
                    sb.append("<td rowspan='" + oneSize + "'>" + finishCaseBOS.get(i).getArea() + "</td>");
                } else {
                    sb.append("<td rowspan='" + oneSize + "'> </td>");
                }
            } else {
                sb.append("<tr>");
                if (null != finishCaseBOS.get(i).getArea()) {
                    sb.append("<td>" + finishCaseBOS.get(i).getArea() + "</td>");
                } else {
                    sb.append("<td> </td>");
                }
            }
            for (int a = 0; a < sons.size(); a++) {
                if (a != 0) {
                    sb.append("<tr>");
                }
                if (a < sons.size() - 1) {
                    if (null != sons.get(a).getDepart()) {
                        sb.append("<td rowspan='" + twoSize + "'>" + sons.get(a).getDepart() + "</td>");
                    } else {
                        sb.append("<td rowspan='" + twoSize + "'> </td>");
                    }
                } else {
                    if (null != sons.get(a).getDepart()) {
                        sb.append("<td>" + sons.get(a).getDepart() + "</td>");
                    } else {
                        sb.append("<td> </td>");
                    }
                }
                //mm
                List<CaseTableBO> caseTableS = null;
//                List<CaseTableBO> caseTableS = sons.get(a).getCaseTableS();
                int threeSize = twoSize / caseTableS.size();
                for (int b = 0; b < caseTableS.size(); b++) {
                    if (b != 0) {
                        sb.append("<tr>");
                    }
                    if (a < sons.size() - 1) {
                        if (null != caseTableS.get(b).getTableName()) {
                            sb.append("<td rowspan='" + threeSize + "'>" + caseTableS.get(b).getTableName() + "</td>");
                        } else {
                            sb.append("<td rowspan='" + threeSize + "'> </td>");
                        }
                    } else {
                        if (null != caseTableS.get(b).getTableName()) {
                            sb.append("<td>" + caseTableS.get(b).getTableName() + "</td>");
                        } else {
                            sb.append("<td> </td>");
                        }
                    }
                    List<CaseGrandSonBO> grandSonS = caseTableS.get(b).getGrandSonS();
                    int fourSize = threeSize / grandSonS.size();
                    for (int c = 0; c < grandSonS.size(); c++) {
                        if (c != 0) {
                            sb.append("<tr>");
                        }
                        if (a < sons.size() - 1) {
                            if (null != grandSonS.get(c).getName()) {
                                sb.append("<td rowspan='" + fourSize + "'>" + grandSonS.get(c).getName() + "</td>");
                            } else {
                                sb.append("<td rowspan='" + fourSize + "'> </td>");
                            }
                        } else {
                            if (null != grandSonS.get(c).getName()) {
                                sb.append("<td>" + grandSonS.get(c).getName() + "</td>");
                            } else {
                                sb.append("<td> </td>");
                            }
                        }
                        List<CaseLastBO> caseLastS = grandSonS.get(c).getCaseLastS();
                        for (int d = 0; d < caseLastS.size(); d++) {
                            CaseLastBO last = caseLastS.get(d);
                            if (d != 0) {
                                sb.append("<tr>");
                            }
                            if (null != last.getTaskType()) {
                                sb.append("<td>" + last.getTaskType() + "</td>");
                            } else {
                                sb.append("<td> </td>");
                            }
                            sb.append("<td>" + last.getPlanNum() + "</td>");
                            sb.append("<td>" + last.getActualNum() + "</td>");
                            sb.append("<td>" + last.getUnFinishNum() + "</td>");
                            sb.append("<td>" + last.getPlanTime() + "</td>");
                            sb.append("<td>" + last.getActualTime() + "</td>");
                            sb.append("<td>" + last.getUnFinishTime() + "</td>");
                            sb.append("<td>" + last.getReportUnFinish() + "</td>");
                            sb.append("<td>" + last.getUnReportUnFinish() + "</td>");
                            sb.append("</tr>");
                        }
                    }
                }
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    private Integer num(FinishCaseBO finishCaseBO) throws SerException {
        int num = 0;
        List<CaseSonBO> sons = finishCaseBO.getCaseSonS();
        for (CaseSonBO sonBO : sons) {
            //mm
            List<CaseTableBO> caseTableS = null;
//            List<CaseTableBO> caseTableS = sonBO.getCaseTableS();
            for (CaseTableBO table : caseTableS) {
                List<CaseGrandSonBO> grandSonS = table.getGrandSonS();
                for (CaseGrandSonBO grandSonBO : grandSonS) {
                    List<CaseLastBO> caseLastS = grandSonBO.getCaseLastS();
                    num += caseLastS.size();
                }
            }
        }
        return num;
    }
}