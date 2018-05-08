package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.ProjectStatus;
import com.bjike.goddess.businessproject.excel.DispatchSheetExcel;
import com.bjike.goddess.businessproject.excel.DispatchSheetExport;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 商务项目派工单信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class DispatchSheetSerImpl extends ServiceImpl<DispatchSheet, DispatchSheetDTO> implements DispatchSheetSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("2",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("2",null);
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
            flag = cusPermissionSer.getCusPermission("1",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 日期格式(年月日)
     */
    private void checkDate(DispatchSheetTO dispatchSheetTO) throws SerException {
        try {
            DateUtil.parseDate(dispatchSheetTO.getSiginTime());
            DateUtil.parseDate(dispatchSheetTO.getStartProjectTime());
            DateUtil.parseDate(dispatchSheetTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
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
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        dispatchSheetDTO.getSorts().add("createTime=desc");

        searchCondition(dispatchSheetDTO);

        Long count = super.count(dispatchSheetDTO);
        return count;
    }

    @Override
    public DispatchSheetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        DispatchSheet dispatchSheet = super.findById(id);
        return BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class);
    }

    @Override
    public List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        checkSeeIdentity();

        searchCondition(dispatchSheetDTO);
        List<DispatchSheet> list = super.findByPage(dispatchSheetDTO);
        List<DispatchSheetBO> dispatchSheetBOList = BeanTransform.copyProperties(list, DispatchSheetBO.class);
        return dispatchSheetBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();
        checkDate(dispatchSheetTO);
        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        dispatchSheet.setCreateTime(LocalDateTime.now());
        super.save(dispatchSheet);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(dispatchSheet, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        checkAddIdentity();

        DispatchSheet temp = super.findById(dispatchSheetTO.getId());

        checkDate(dispatchSheetTO);
        DispatchSheet dispatchSheet = BeanTransform.copyProperties(dispatchSheetTO, DispatchSheet.class, true);
        BeanUtils.copyProperties(dispatchSheet, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        DispatchSheetBO dispatchSheetBO = BeanTransform.copyProperties(temp, DispatchSheetBO.class);

        return dispatchSheetBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDispatchSheet(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }


    public void searchCondition(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getInnerProject())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("innerProject", dispatchSheetDTO.getInnerProject()));
        }/**
         * 合同外部项目编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getOutProjectNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("outProjectNum", dispatchSheetDTO.getOutProjectNum()));
        }/**
         * 对应销售合同编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getSaleContractNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("saleContractNum", dispatchSheetDTO.getSaleContractNum()));
        }
        /**
         * 业务类型
         */
        if (dispatchSheetDTO.getBusinessType() != null) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessType", dispatchSheetDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessSubject())) {
            dispatchSheetDTO.getConditions().add(Restrict.like("businessSubject", dispatchSheetDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getBusinessCooperate())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("businessCooperate", dispatchSheetDTO.getBusinessCooperate()));
        }
        /**
         * 总包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getMajorCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("majorCompany", dispatchSheetDTO.getMajorCompany()));
        }
        /**
         * 分包单位名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getSubCompany())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("subCompany", dispatchSheetDTO.getSubCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getArea())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("area", dispatchSheetDTO.getArea()));
        }
        /**
         * 派工单名称
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchProject())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchProject", dispatchSheetDTO.getDispatchProject()));
        }
        /**
         * 派工单编号
         */
        if (StringUtils.isNotBlank(dispatchSheetDTO.getDispatchNum())) {
            dispatchSheetDTO.getConditions().add(Restrict.eq("dispatchNum", dispatchSheetDTO.getDispatchNum()));
        }

    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<DispatchSheetBO> dispatchSheetBOList = super.findBySql("select area from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> areaList = dispatchSheetBOList.stream().map(DispatchSheetBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> listDispatchName() throws SerException {
        String[] fields = new String[]{"dispatchProject"};
        List<DispatchSheetBO> dispatchSheetBOList = super.findBySql("select dispatchProject from businessproject_dispatchsheet order by area asc ", DispatchSheetBO.class, fields);

        List<String> dispatchProjectList = dispatchSheetBOList.stream().map(DispatchSheetBO::getDispatchProject)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());


        return dispatchProjectList;
    }

    @Override
    public Set<String> allInnerProjects() throws SerException {
        List<DispatchSheet> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (DispatchSheet b : list) {
            set.add(b.getInnerProject());
        }
        return set;
    }

    @Override
    public List<String> allDispatchNum() throws SerException {
        List<DispatchSheet> list = super.findAll();
        List<String> nums = new ArrayList<>();
        for (DispatchSheet b : list) {
            nums.add(b.getDispatchNum());
        }
        return nums;
    }

    @Override
    public List<DispatchSheetBO> getInfoByDispatchNum(String dispatchNum) throws SerException {
        DispatchSheetDTO dto = new DispatchSheetDTO();
        if (StringUtils.isNotBlank(dispatchNum)) {
            dto.getConditions().add(Restrict.eq("dispatchNum", dispatchNum));
        }
        List<DispatchSheet> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DispatchSheetBO.class);
    }

    @Override
    public byte[] exportExcel(DispatchSheetDTO dto) throws SerException {
        String[] innerProjects = dto.getInnerProjects();
        List<DispatchSheetExport> toList = new ArrayList<DispatchSheetExport>();
        if ((innerProjects != null) && (innerProjects.length > 0)) {
            List<DispatchSheet> list = super.findByCis(dto);
            for (String s : innerProjects) {
                if (StringUtils.isNotBlank(s)) {
                    for (DispatchSheet b : list) {
                        if (s.equals(b.getInnerProject())) {
                            DispatchSheetExport excel = new DispatchSheetExport();
                            BeanUtils.copyProperties(b, excel);
                            toList.add(excel);
                        }
                    }
                }
            }
        } else {
            List<DispatchSheet> list = super.findByCis(dto);
            for (DispatchSheet b : list) {
                DispatchSheetExport excel = new DispatchSheetExport();
                BeanUtils.copyProperties(b, excel);
                toList.add(excel);
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<DispatchSheetTO> toList) throws SerException {
        checkAddIdentity();
        for (DispatchSheetTO to : toList) {
            DispatchSheet baseInfoManage = new DispatchSheet();
            BeanUtils.copyProperties(to, baseInfoManage);
            baseInfoManage.setSiginTime(DateUtil.parseDate(to.getSiginTime()));
            baseInfoManage.setStartProjectTime(DateUtil.parseDate(to.getStartProjectTime()));
            baseInfoManage.setEndProjectTime(DateUtil.parseDate(to.getEndProjectTime()));
            String completeProject = baseInfoManage.getCompleteProject();
            String fileCondition = baseInfoManage.getFileCondition();
            String innerProjectNum = baseInfoManage.getInnerProjectNum();
            if ((!"已完工".equals(completeProject)) && (!"未完工".equals(completeProject))) {
                throw new SerException("是否完工只能为未完工或已完工");
            }
            if ((!"未归档".equals(fileCondition)) && (!"已归档".equals(fileCondition))) {
                throw new SerException("合同是否已归档只能为未归档或已归档");
            }
            if (innerProjectNum == null || StringUtils.isBlank(innerProjectNum)) {
                baseInfoManage.setInnerProjectNum("无");
            }
            super.save(baseInfoManage);
        }
    }

    @Override
    public byte[] templateExcel() throws SerException {
        List<DispatchSheetExcel> toList = new ArrayList<DispatchSheetExcel>();
        DispatchSheetExcel baseInfoManageLeadExcel = new DispatchSheetExcel();
        baseInfoManageLeadExcel.setInnerProjectNum("test");
        baseInfoManageLeadExcel.setBusinessType("test");
        baseInfoManageLeadExcel.setBusinessSubject("test");
        baseInfoManageLeadExcel.setOuterProject("test");
        baseInfoManageLeadExcel.setOutProjectNum("test");
        baseInfoManageLeadExcel.setSaleContractNum("test");
        baseInfoManageLeadExcel.setBusinessCooperate("test");
        baseInfoManageLeadExcel.setInnerProject("test");
        baseInfoManageLeadExcel.setArea("test");
        baseInfoManageLeadExcel.setProjectGroup("test");
        baseInfoManageLeadExcel.setSiginTime(LocalDate.now());
        baseInfoManageLeadExcel.setProjectCharge("test");
        baseInfoManageLeadExcel.setDispatchProject("test");
        baseInfoManageLeadExcel.setDispatchNum("test");
        baseInfoManageLeadExcel.setMajorCompany("test");
        baseInfoManageLeadExcel.setSubCompany("test");
        baseInfoManageLeadExcel.setCustomerName("test");
        baseInfoManageLeadExcel.setDispatchText("test");
        baseInfoManageLeadExcel.setStartProjectTime(LocalDate.now());
        baseInfoManageLeadExcel.setEndProjectTime(LocalDate.now());
        baseInfoManageLeadExcel.setMoney(0d);
        baseInfoManageLeadExcel.setCompleteProject("未完工");
        baseInfoManageLeadExcel.setFileCondition("已归档");
        baseInfoManageLeadExcel.setFileCount(0d);
        baseInfoManageLeadExcel.setRemark("test");
        baseInfoManageLeadExcel.setTempContractNum("test");
        baseInfoManageLeadExcel.setMakeContract(MakeContract.HADMAKE);
        baseInfoManageLeadExcel.setTaskNum("test");
        baseInfoManageLeadExcel.setProjectStatus(ProjectStatus.APPROACH);
        baseInfoManageLeadExcel.setContractScale(0d);
        baseInfoManageLeadExcel.setScale(0d);
        baseInfoManageLeadExcel.setMajor("test");

        toList.add(baseInfoManageLeadExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public Set<String> nums() throws SerException {
        List<DispatchSheet> list = super.findAll();
        return list.stream().map(dispatchSheet -> dispatchSheet.getDispatchNum()).collect(Collectors.toSet());
    }

    @Override
    public List<String> areas() throws SerException {
        List<DispatchSheet> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DispatchSheet model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getProjectGroup(String area) throws SerException {
        DispatchSheetDTO dispatchSheetDTO = new DispatchSheetDTO();
        dispatchSheetDTO.getConditions().add(Restrict.eq("area",area));
        List<DispatchSheet> list = super.findByCis(dispatchSheetDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DispatchSheet model : list) {
            String projectGroup = model.getProjectGroup();
            if (StringUtils.isNotBlank(model.getProjectCharge())) {
                set.add(projectGroup);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getInnerName(String area, String projectGroup) throws SerException {
        DispatchSheetDTO dispatchSheetDTO = new DispatchSheetDTO();
        dispatchSheetDTO.getConditions().add(Restrict.eq("area",area));
        dispatchSheetDTO.getConditions().add(Restrict.eq("projectGroup",projectGroup));
        List<DispatchSheet> list = super.findByCis(dispatchSheetDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DispatchSheet model : list) {
            String innerProject = model.getInnerProject();
            if (StringUtils.isNotBlank(model.getInnerProject())) {
                set.add(innerProject);
            }
        }
        return new ArrayList<>(set);
    }
}