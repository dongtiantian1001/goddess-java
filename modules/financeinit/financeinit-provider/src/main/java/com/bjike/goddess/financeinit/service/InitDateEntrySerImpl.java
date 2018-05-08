package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.InitDateEntryBO;
import com.bjike.goddess.financeinit.dto.InitDateEntryDTO;
import com.bjike.goddess.financeinit.entity.InitDateEntry;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.InitDateEntryImport;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.InitDateEntryTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据录入业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class InitDateEntrySerImpl extends ServiceImpl<InitDateEntry, InitDateEntryDTO> implements InitDateEntrySer {

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
    public Long countInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        initDateEntryDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        Long count = super.count(initDateEntryDTO);
        return count;
    }

    @Override
    public InitDateEntryBO getOneById(String id) throws SerException {
        InitDateEntry initDateEntry = super.findById(id);
        return BeanTransform.copyProperties(initDateEntry, InitDateEntryBO.class);
    }

    @Override
    public List<InitDateEntryBO> listInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        checkSeeIdentity();
        initDateEntryDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        initDateEntryDTO.getSorts().add("code=asc");
        List<InitDateEntry> initDateEntries = super.findByCis(initDateEntryDTO, true);
        return BeanTransform.copyProperties(initDateEntries, InitDateEntryBO.class);
    }

    @Override
    public InitDateEntryBO editInit(InitDateEntryTO initDateEntryTO) throws SerException {
        checkAddIdentity();
        InitDateEntry initDateEntry = super.findById(initDateEntryTO.getId());
        LocalDateTime date = initDateEntry.getCreateTime();
        initDateEntry = BeanTransform.copyProperties(initDateEntryTO, InitDateEntry.class, true);
        initDateEntry.setCreateTime(date);
        initDateEntry.setModifyTime(LocalDateTime.now());
        super.update(initDateEntry);
        return BeanTransform.copyProperties(initDateEntry, InitDateEntryBO.class);
    }

    @Override
    public String trialBalance() throws SerException {
        checkAddIdentity();
        List<InitDateEntry> initDateEntryList = new ArrayList<>();
        Double yearBorrowerNum = 0d;//本年借方累计数
        Double yearLenderNum = 0d;//本年贷方累计数
        Double boorrowerBalance = 0d;//借方累计数加借方期初余额
        Double lendBalance = 0d;//待方累计数加借方期初余额
        if (initDateEntryList != null && initDateEntryList.size() > 0) {
            for (InitDateEntry initDateEntry : initDateEntryList) {
                //将一级科目筛出来
                if (initDateEntry.getCode().length() == 4) {
                    yearBorrowerNum += initDateEntry.getYearBorrowerNum();
                    yearLenderNum += initDateEntry.getYearLenderNum();
                    if (initDateEntry.getBalanceDirection() == BalanceDirection.BORROW) {
                        boorrowerBalance += initDateEntry.getBegingBalance() + initDateEntry.getYearBorrowerNum();
                        lendBalance += initDateEntry.getYearLenderNum();
                    } else {
                        boorrowerBalance += initDateEntry.getYearBorrowerNum();
                        lendBalance += initDateEntry.getBegingBalance() + initDateEntry.getYearLenderNum();
                    }
                }
            }
        }
        String str = "本年借方累计数(" + yearBorrowerNum + ")" + (yearBorrowerNum.doubleValue() == yearLenderNum.doubleValue() ? "=" : "≠") + "本年贷方累计数(" + yearLenderNum + ")且本年借方累计数＋期初余额(" + boorrowerBalance + ")" + (boorrowerBalance.doubleValue() == lendBalance.doubleValue() ? "=" : "≠") + "本年贷方累计数＋期初余额(" + lendBalance + ")";
        return str;
    }

    @Override
    public InitDateEntryBO findByName(String name) throws SerException {
        InitDateEntryDTO initDateEntryDTO = new InitDateEntryDTO();
        initDateEntryDTO.getConditions().add(Restrict.eq("accountanName", name));
        initDateEntryDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        InitDateEntry initDateEntry = super.findOne(initDateEntryDTO);
        return BeanTransform.copyProperties(initDateEntry, InitDateEntryBO.class);
    }

    @Override
    public Double findYearProfitLossNumByName(String name) throws SerException {
        Double yearProfitLoss = 0d;
         InitDateEntryDTO initDateEntryDTO = new InitDateEntryDTO();
        initDateEntryDTO.getConditions().add(Restrict.eq("accountanName", name));
        initDateEntryDTO.getConditions().add(Restrict.eq("systemId", getSystemId()));
        List<InitDateEntry> initDateEntrys = super.findByCis(initDateEntryDTO);
        if(initDateEntrys!=null && initDateEntrys.size()>0){
            yearProfitLoss = initDateEntrys.get(0).getYearProfitLossNum();
        }
        return yearProfitLoss;
    }

    public InitDateEntryBO findBySubject(String firstSubject) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        InitDateEntryDTO dto = new InitDateEntryDTO();
        dto.getConditions().add(Restrict.eq("accountanName", firstSubject));
        dto.getConditions().add(Restrict.eq("systemId", getSystemId()));
        List<InitDateEntry> list = super.findByCis(dto);
        List<InitDateEntryBO> bos = BeanTransform.copyProperties(list, InitDateEntryBO.class, false);
        if (null != bos && bos.size() > 0) {
            return bos.get(0);
        }
        return null;
    }

    @Override
    public InitDateEntryBO findBySubject(String firstSubject, String systemId) throws SerException {

        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        InitDateEntryDTO dto = new InitDateEntryDTO();
        dto.getConditions().add(Restrict.eq("accountanName", firstSubject));
        dto.getConditions().add(Restrict.eq("systemId", systemId));
        List<InitDateEntry> list = super.findByCis(dto);
        List<InitDateEntryBO> bos = BeanTransform.copyProperties(list, InitDateEntryBO.class, false);
        if (null != bos && bos.size() > 0) {
            return bos.get(0);
        }
        return null;
    }

    @Override
    public byte[] exportExcel() throws SerException {
//        List<InitDateEntry> list = super.findAll();
        InitDateEntryDTO dto = new InitDateEntryDTO();
        dto.getConditions().add(Restrict.eq("systemId", getSystemId()));
        List<InitDateEntry> list = super.findByCis(dto);
        List<InitDateEntryImport> initDateEntryImports = new ArrayList<>();

        for (InitDateEntry initDateEntry : list) {
            InitDateEntryImport excel = BeanTransform.copyProperties(initDateEntry, InitDateEntryImport.class);
            initDateEntryImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(initDateEntryImports, excel);
        return bytes;
    }

    @Override
    public void importExcel(List<InitDateEntryTO> initDateEntryTOS) throws SerException {
        checkAddIdentity();
        String systemId = getSystemId();
        if (initDateEntryTOS != null && initDateEntryTOS.size() > 0) {
            List<InitDateEntry> initDateEntryList = new ArrayList<>();
            for (InitDateEntryTO str : initDateEntryTOS) {
                InitDateEntryDTO initDateEntryDTO = new InitDateEntryDTO();
                initDateEntryDTO.getConditions().add(Restrict.eq("code", str.getCode()));
                initDateEntryDTO.getConditions().add(Restrict.eq("accountanName", str.getAccountanName()));
                initDateEntryDTO.getConditions().add(Restrict.eq("systemId", systemId));
                InitDateEntry initDateEntry = super.findOne(initDateEntryDTO);
                if (initDateEntry != null) {
                    initDateEntry.setModifyTime(LocalDateTime.now());
                    initDateEntry.setYearBorrowerNum(str.getYearBorrowerNum());
                    initDateEntry.setYearLenderNum(str.getYearLenderNum());
                    initDateEntry.setBegingBalance(str.getBegingBalance());
                    initDateEntry.setYearProfitLossNum(str.getYearProfitLossNum());
                    initDateEntry.setSystemId(systemId);
                    initDateEntryList.add(initDateEntry);
                } else {
                    throw new SerException("代码为:" + str.getCode() + "会计科目为:" + str.getAccountanName() + "匹配不到此条数据,请重新修改");
                }
            }
            super.update(initDateEntryList);
        }
    }


    /**
     * 获取公司编号
     *
     * @return
     * @throws SerException
     */
    private String getSystemId() throws SerException {
        String token = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);
        String systemId = userAPI.currentSysNO();
        RpcTransmit.transmitUserToken(token);
        return systemId;
    }
}