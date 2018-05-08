package com.bjike.goddess.organize.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.bo.EmailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.bo.PositionUserDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.*;
import com.bjike.goddess.organize.enums.IntervalType;
import com.bjike.goddess.organize.to.EmailTO;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ike on 17-9-7.
 */
@Service
public class EmailSerImpl extends ServiceImpl<Email, EmailDTO> implements EmailSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private PositionDetailSer positionDetailSer;
    @Autowired
    private PositionDetailUserSer positionDetailUserSer;
    @Autowired
    private PositionUserDetailSer positionUserDetailSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void add(EmailTO to) throws SerException {
        Email email = BeanTransform.copyProperties(to, Email.class, true);
        LocalDateTime sendTime = email.getSetTime();
        Long mis = sendTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (mis <= 0) {
            throw new SerException("发送时间必须大于当前时间");
        }
        String[] departs = to.getDeparts();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < departs.length; i++) {
            if (i == departs.length - 1) {
                sb.append(departs[i]);
            } else {
                sb.append(departs[i] + ",");
            }
        }
        email.setDepart(sb.toString());
        String[] sendObjects = to.getSendObjects();
        if (null != sendObjects) {
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < sendObjects.length; i++) {
                if (i == sendObjects.length - 1) {
                    sb1.append(sendObjects[i]);
                } else {
                    sb1.append(sendObjects[i] + ",");
                }
            }
            email.setSendObject(sb1.toString());
        }
        IntervalType intervalType = email.getIt();
        int interval = email.getItTime();
        LocalDateTime lastTime = null;
        switch (intervalType) {
            case MINUTE:
                lastTime = email.getSetTime().minusMinutes(interval);
                break;
            case HOUR:
                lastTime = email.getSetTime().minusHours(interval);
                break;
            case DAY:
                lastTime = email.getSetTime().minusDays(interval);
                break;
            case WEEK:
                lastTime = email.getSetTime().minusWeeks(interval);
                break;
            case MONTH:
                lastTime = email.getSetTime().minusMonths(interval);
                break;
        }
        email.setLastTime(lastTime);
        super.save(email);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(EmailTO to) throws SerException {
        Email entity = super.findById(to.getId());
        Email email = BeanTransform.copyProperties(to, Email.class, true);
        BeanUtils.copyProperties(email, entity, "id", "createTime");
        LocalDateTime time = DateUtil.parseDateTime(to.getSetTime());
        entity.setSetTime(time);
        Long mis = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (mis <= 0) {
            throw new SerException("发送时间必须大于当前时间");
        }
        String[] departs = to.getDeparts();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < departs.length; i++) {
            if (i == departs.length - 1) {
                sb.append(departs[i]);
            } else {
                sb.append(departs[i] + ",");
            }
        }
        entity.setDepart(sb.toString());
        String[] sendObjects = to.getSendObjects();
        if (null != sendObjects) {
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < sendObjects.length; i++) {
                if (i == sendObjects.length - 1) {
                    sb1.append(sendObjects[i]);
                } else {
                    sb1.append(sendObjects[i] + ",");
                }
            }
            entity.setSendObject(sb1.toString());
        }
        IntervalType intervalType = email.getIt();
        int interval = email.getItTime();
        LocalDateTime lastTime = null;
        switch (intervalType) {
            case MINUTE:
                lastTime = email.getSetTime().minusMinutes(interval);
                break;
            case HOUR:
                lastTime = email.getSetTime().minusHours(interval);
                break;
            case DAY:
                lastTime = email.getSetTime().minusDays(interval);
                break;
            case WEEK:
                lastTime = email.getSetTime().minusWeeks(interval);
                break;
            case MONTH:
                lastTime = email.getSetTime().minusMonths(interval);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<EmailBO> list(EmailDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Email> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, EmailBO.class);
    }

    @Override
    public EmailBO one(String id) throws SerException {
        Email email = super.findById(id);
        if (null == email) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(email, EmailBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void send() throws SerException {
        List<Email> list = super.findAll();
        for (Email email : list) {
            String sendObject = email.getSendObject();
            List<String> stringList = new ArrayList<>();
            if (null != sendObject) {
                String[] strings = sendObject.split(",");
                stringList = Arrays.asList(strings);
            }
            if (email.getSendAll()) {
                String[] departs = email.getDepart().split(",");
                String[] names = names(departs);
                if (null != names) {
                    List<String> emails = new ArrayList<>(0);
                    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                    StringBuilder sb = new StringBuilder();
                    try {
                        for (int i = 0; i < names.length; i++) {
                            if (i == names.length - 1) {
//                byte[] b=names[i].getBytes("iso-8859-1");
                                sb.append(names[i]);
                            } else {
//                byte[] b=names[i].getBytes("iso-8859-1");
                                sb.append(names[i]);
                            }
                        }
                        HttpPost httpPost = new HttpPost("https://contacts.issp.bjike.com:8080/internalcontacts/v1/getEmail?names=" + sb.toString() + "");//线上
//        HttpPost httpPost = new HttpPost("http://localhost:51310/internalcontacts/v1/getEmail?names="+sb.toString()+"");//线下测试
                        httpPost.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));
                        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("names", sb.toString()));

                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps);
                        entity.setContentEncoding("utf-8");
                        httpPost.setEntity(entity);
//            httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//        httpclient.execute(httpPost);
//        httpclient.getConnectionManager().shutdown();

                        ActResultOrgan resultOrgan = new ActResultOrgan();
                        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
                        resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
                        emails = (List<String>) (resultOrgan.getData());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stringList.addAll(emails);
                }
            }
            LocalDateTime lastTime = email.getLastTime();
            LocalDateTime now = LocalDateTime.now();
            IntervalType intervalType = email.getIt();
            int interval = email.getItTime();
            LocalDateTime time = null;
            switch (intervalType) {
                case MINUTE:
                    time = lastTime.plusMinutes(interval);
                    break;
                case HOUR:
                    time = lastTime.plusHours(interval);
                    break;
                case DAY:
                    time = lastTime.plusDays(interval);
                    break;
                case WEEK:
                    time = lastTime.plusWeeks(interval);
                    break;
                case MONTH:
                    time = lastTime.plusMonths(interval);
                    break;
            }
            Long mis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();      //当前时间和发送时间作比较
            if (mis >= 0) {
                email.setLastTime(now);
                email.setModifyTime(LocalDateTime.now());
                super.update(email);
                if (!stringList.isEmpty()) {
                    String[] mails = new String[stringList.size()];
                    mails = stringList.toArray(mails);
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle("组织（人员）构成");
                    messageTO.setContent(html(email));
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);
                    //定时发送必须写
                    messageTO.setSenderId("SYSTEM");
                    messageTO.setSenderName("SYSTEM");
                    messageTO.setReceivers(mails);
                    messageAPI.send(messageTO);
                }
            }
        }
    }

    private String html(Email email) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>组织结构:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        sb.append("<tr>");
        sb.append("<td>员工编号</td>");
        sb.append("<td>姓名</td>");
        sb.append("<td>体系编号</td>");
        sb.append("<td>体系</td>");
        sb.append("<td>所属地区</td>");
        sb.append("<td>部门编号</td>");
        sb.append("<td>项目组/部门</td>");
        sb.append("<td>轮岗层级</td>");
        sb.append("<td>岗位层级</td>");
        sb.append("<td>模块</td>");
        sb.append("<td>岗位名称</td>");
        sb.append("<td>岗位编号</td>");
        sb.append("<td>担任状态</td>");
        sb.append("<td>是否为代理岗位</td>");
        sb.append("<td>代理类型</td>");
        sb.append("<td>人员状态</td>");
        sb.append("</tr>");
        String[] departs = email.getDepart().split(",");
        Set<String> positions = positions(departs);
        List<PositionDetailUserBO> boList = boList(positions);
        for (PositionDetailUserBO bo : boList) {
            List<PositionUserDetailBO> detailS = bo.getDetailS();
            int size = detailS.size();
            sb.append("<tr>");
            sb.append("<td rowspan='" + size + "'>" + bo.getNumber() + "</td>");
            if (null != bo.getName()) {
                sb.append("<td rowspan='" + size + "'>" + bo.getName() + "</td>");
            } else {
                sb.append("<td rowspan='" + size + "'> </td>");
            }
            for (int i = 0; i < detailS.size(); i++) {
                if (i == 0) {
                    sb.append("<td>" + detailS.get(i).getHierarchyNumber() + "</td>");
                    sb.append("<td>" + detailS.get(i).getHierarchy() + "</td>");
                    sb.append("<td>" + detailS.get(i).getArea() + "</td>");
                    sb.append("<td>" + detailS.get(i).getDepartNumber() + "</td>");
                    sb.append("<td>" + detailS.get(i).getDepartment() + "</td>");
                    sb.append("<td>" + detailS.get(i).getRotationLevel() + "</td>");
                    sb.append("<td>" + detailS.get(i).getArrangement() + "</td>");
                    if (null == detailS.get(i).getModule()) {
                        sb.append("<td> </td>");
                    } else {
                        sb.append("<td>" + detailS.get(i).getModule() + "</td>");
                    }
                    sb.append("<td>" + detailS.get(i).getPosition() + "</td>");
                    sb.append("<td>" + detailS.get(i).getPositionNumber() + "</td>");
                    if (null != detailS.get(i).getWorkStatus()) {
                        sb.append("<td>" + detailS.get(i).getWorkStatus().toString() + "</td>");
                    } else {
                        sb.append("<td> </td>");
                    }
                    if (null != detailS.get(i).getAgent()) {
                        if (detailS.get(i).getAgent()) {
                            sb.append("<td>是</td>");
                        } else {
                            sb.append("<td>否</td>");
                        }
                    } else {
                        sb.append("<td> </td>");
                    }
                    if (null == detailS.get(i).getAgentType()) {
                        sb.append("<td> </td>");
                    } else {
                        sb.append("<td>" + detailS.get(i).getAgentType().toString() + "</td>");
                    }
                    if (null != bo.getStaffStatus()) {
                        sb.append("<td rowspan='" + size + "'>" + bo.getStaffStatus().toString() + "</td>");
                    }else {
                        sb.append("<td rowspan='" + size + "'> </td>");
                    }
                    sb.append("</tr>");
                } else {
                    sb.append("<tr>");
                    sb.append("<td>" + detailS.get(i).getHierarchyNumber() + "</td>");
                    sb.append("<td>" + detailS.get(i).getHierarchy() + "</td>");
                    sb.append("<td>" + detailS.get(i).getArea() + "</td>");
                    sb.append("<td>" + detailS.get(i).getDepartNumber() + "</td>");
                    sb.append("<td>" + detailS.get(i).getDepartment() + "</td>");
                    sb.append("<td>" + detailS.get(i).getRotationLevel() + "</td>");
                    sb.append("<td>" + detailS.get(i).getArrangement() + "</td>");
                    if (null == detailS.get(i).getModule()) {
                        sb.append("<td> </td>");
                    } else {
                        sb.append("<td>" + detailS.get(i).getModule() + "</td>");
                    }
                    sb.append("<td>" + detailS.get(i).getPosition() + "</td>");
                    sb.append("<td>" + detailS.get(i).getPositionNumber() + "</td>");
                    if (null != detailS.get(i).getWorkStatus()) {
                        sb.append("<td>" + detailS.get(i).getWorkStatus().toString() + "</td>");
                    } else {
                        sb.append("<td> </td>");
                    }
                    if (null != detailS.get(i).getAgent()) {
                        if (detailS.get(i).getAgent()) {
                            sb.append("<td>是</td>");
                        } else {
                            sb.append("<td>否</td>");
                        }
                    } else {
                        sb.append("<td> </td>");
                    }
                    if (null == detailS.get(i).getAgentType()) {
                        sb.append("<td> </td>");
                    } else {
                        sb.append("<td>" + detailS.get(i).getAgentType().toString() + "</td>");
                    }
                    sb.append("</tr>");
                }
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    private Set<String> positions(String[] departs) throws SerException {
        List<String> departIds = new ArrayList<>();
        for (String depart : departs) {
            DepartmentDetailDTO dto = new DepartmentDetailDTO();
            dto.getConditions().add(Restrict.eq("department", depart));
            DepartmentDetail detail = departmentDetailSer.findOne(dto);
            if (null != detail) {
                departIds.add(detail.getId());
            }
        }
        PositionDetailDTO positionDetailDTO = new PositionDetailDTO();
        positionDetailDTO.getConditions().add(Restrict.in("department.id", departIds));
        List<PositionDetail> positionDetails = positionDetailSer.findByCis(positionDetailDTO);
        Set<String> positionIds = positionDetails.stream().map(positionDetail -> positionDetail.getId()).collect(Collectors.toSet());
        return positionIds;
    }

    private String[] names(String[] departs) throws SerException {
        Set<String> positions = positions(departs);
        PositionUserDetailDTO dto = new PositionUserDetailDTO();
        dto.getConditions().add(Restrict.in("positionId", positions));
        List<PositionUserDetail> list = positionUserDetailSer.findByCis(dto);
        Set<String> userIds = list.stream().map(positionUserDetail -> positionUserDetail.getUserId()).collect(Collectors.toSet());
        List<String> names = new ArrayList<>();
        for (String s : userIds) {
            String name = userAPI.findNameById(s);
            if (null != name) {
                names.add(name);
            }
        }
        if (!names.isEmpty()) {
            String[] strings = new String[names.size()];
            strings = names.toArray(strings);
            return strings;
        }
        return null;
    }

    private List<PositionDetailUserBO> boList(Set<String> positions) throws SerException {
        List<PositionDetailUser> list = positionDetailUserSer.findAll();
        List<PositionDetailUserBO> boList = new ArrayList<>();
        for (PositionDetailUser p : list) {
            PositionDetailUserBO bo = positionDetailUserSer.bo(p, positions);
            if (null != bo) {
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public Long count(EmailDTO dto) throws SerException {
        return super.count(dto);
    }
}
