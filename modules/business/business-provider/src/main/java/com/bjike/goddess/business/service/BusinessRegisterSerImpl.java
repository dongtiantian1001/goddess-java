package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.bo.BusinessRegisterListBO;
import com.bjike.goddess.business.bo.RegisterNaTypeCaBO;
import com.bjike.goddess.business.bo.ShareholdersBO;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.business.entity.BusinessTaxChange;
import com.bjike.goddess.business.enums.ChangeDataName;
import com.bjike.goddess.business.enums.GuideAddrStatus;
import com.bjike.goddess.business.excel.BusinessRegisterExcel;
import com.bjike.goddess.business.excel.BusinessRegisterImprot;
import com.bjike.goddess.business.excel.BusinessRegisterImprotTemple;
import com.bjike.goddess.business.excel.SonPermissionObject;
import com.bjike.goddess.business.to.BusinessRegisterExcelTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.business.to.ShareholdersTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 工商注册业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessRegisterSerImpl extends ServiceImpl<BusinessRegister, BusinessRegisterDTO> implements BusinessRegisterSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BusinessAnnualInfoSer businessAnnualInfoSer;
    @Autowired
    private BusinessTaxChangeSer businessTaxChangeSer;

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
            flag = cusPermissionSer.busCusPermission("2");
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeRegister = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddRegister = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("businessregister");
        obj.setDescribesion("工商注册");
        if (flagSeeRegister || flagAddRegister) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnnual = businessAnnualInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("businessannualinfo");
        obj.setDescribesion("工商年检信息");
        if (flagSeeAnnual) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTax = businessTaxChangeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("businesstaxchange");
        obj.setDescribesion("工商税务变更");
        if (flagSeeTax) {
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
        return flag;
    }

    @Override
    public Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        businessRegisterDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessRegisterDTO);
        return counts;
    }

    @Override
    public BusinessRegisterBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusinessRegister businessRegister = super.findById(id);
        BusinessRegisterBO businessRegisterBO = BeanTransform.copyProperties(businessRegister, BusinessRegisterBO.class, "operationPeriod", "shareholders");
        //经营期限转换
        String[] str_operat = businessRegister.getOperationPeriod().split("至");
        businessRegisterBO.setStartOperationPeriod(str_operat[0]);
        if (str_operat.length > 1) {
            businessRegisterBO.setEndOperationPeriod(str_operat[1]);
        } else {
            businessRegisterBO.setEndOperationPeriod(null);
        }
        //股东转换
        String[] str_shareholders = businessRegister.getShareholders().split(";");
        List<ShareholdersBO> shareholdersBOS = new ArrayList<>();
        if (null != str_shareholders && str_shareholders.length > 0) {
            for (String str : str_shareholders) {
                ShareholdersBO shareholdersBO = new ShareholdersBO();
                String[] str_str = str.split(":");
                shareholdersBO.setShareholders(str_str[0]);
                shareholdersBO.setPercentageShares(Double.parseDouble(str_str[1]));
                shareholdersBOS.add(shareholdersBO);
            }
        }
        businessRegisterBO.setShareholdersBOS(shareholdersBOS);
        return businessRegisterBO;
    }

    @Override
    public List<BusinessRegisterListBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        checkSeeIdentity();
        List<BusinessRegister> businessRegisters = super.findByCis(businessRegisterDTO, true);

//        List<BusinessRegisterBO> businessRegisterBOS = new ArrayList<>();
//        if (null != businessRegisterBOS && businessRegisterBOS.size() > 0) {
//            for (BusinessRegister businessRegister : businessRegisters){
//                BusinessRegisterBO businessRegisterBO = BeanTransform.copyProperties(businessRegister, BusinessRegisterBO.class, "operationPeriod","shareholders");
//                //经营期限转换
//                String[] str_operat = businessRegister.getOperationPeriod().split("-");
//                businessRegisterBO.setStartOperationPeriod(str_operat[0]);
//                if (str_operat.length > 1) {
//                    businessRegisterBO.setEndOperationPeriod(str_operat[1]);
//                } else {
//                    businessRegisterBO.setEndOperationPeriod(null);
//                }
//                //股东:占股比例转换
//                String[] str_shareholders = businessRegister.getShareholders().split(";");
//                List<ShareholdersBO> shareholdersBOS = new ArrayList<>();
//                if (null != str_shareholders && str_shareholders.length > 0) {
//                    for (String str : str_shareholders) {
//                        ShareholdersBO shareholdersBO = new ShareholdersBO();
//                        String[] str_str = str.split(":");
//                        shareholdersBO.setShareholders(str_str[0]);
//                        shareholdersBO.setPercentageShares(Double.parseDouble(str_str[1]));
//                        shareholdersBOS.add(shareholdersBO);
//                    }
//                }
//                businessRegisterBO.setShareholdersBOS(shareholdersBOS);
//            }
//        }
        return BeanTransform.copyProperties(businessRegisters, BusinessRegisterListBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        checkAddIdentity();
        StringBuffer shareholders = new StringBuffer();
        //转换股东:占股比例
        List<ShareholdersTO> shareholdersTOList = businessRegisterTO.getShareholdersTOList();
        Double sum = 0d;
        if (null != shareholdersTOList && shareholdersTOList.size() > 0) {
            for (ShareholdersTO shareholdersTO : shareholdersTOList) {
                sum += shareholdersTO.getPercentageShares();
                shareholders.append(shareholdersTO.getShareholders() + ":" + shareholdersTO.getPercentageShares());
                shareholders.append(";");
            }
        }
        if (sum != 1) {
            throw new SerException("股东占股之和必须为1");
        }

        String share = "";
        if (null != shareholders && shareholders.length() > 0) {
            share = shareholders.substring(0, shareholders.lastIndexOf(";"));
        }
        BusinessRegister businessRegister = BeanTransform.copyProperties(businessRegisterTO, BusinessRegister.class, true,"startOperationPeriod", "endOperationPeriod", "shareholdersTOList");
        businessRegister.setCreateTime(LocalDateTime.now());
        //转换经营期限
        if (StringUtils.isNotBlank(businessRegisterTO.getEndOperationPeriod())) {
            businessRegister.setOperationPeriod(businessRegisterTO.getStartOperationPeriod() + "至" + businessRegisterTO.getEndOperationPeriod());
        } else {
            businessRegister.setOperationPeriod(businessRegisterTO.getStartOperationPeriod());
        }
        businessRegister.setShareholders(share);
        super.save(businessRegister);
        return BeanTransform.copyProperties(businessRegister, BusinessRegisterBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(businessRegisterTO.getId())) {
            throw new SerException("id不能为空");
        }
        //如果本条数据被变更了,就不能被编辑了
        BusinessTaxChangeDTO businessTaxChangeDTO = new BusinessTaxChangeDTO();
        businessTaxChangeDTO.getConditions().add(Restrict.eq("businessRegisterId", businessRegisterTO.getId()));
        List<BusinessTaxChange> businessTaxChangeList = businessTaxChangeSer.findByCis(businessTaxChangeDTO);
        if (null != businessTaxChangeList && businessTaxChangeList.size() < 0) {
            throw new SerException("该数据已被变更了,不能被编辑");
        }

        StringBuffer shareholders = new StringBuffer();
        //转换股东:占股比例
        List<ShareholdersTO> shareholdersTOList = businessRegisterTO.getShareholdersTOList();
        Double sum = 0d;
        if (null != shareholdersTOList && shareholdersTOList.size() > 0) {
            for (ShareholdersTO shareholdersTO : shareholdersTOList) {
                sum += shareholdersTO.getPercentageShares();
                shareholders.append(shareholdersTO.getShareholders() + ":" + shareholdersTO.getPercentageShares());
                shareholders.append(";");
            }
        }
        if (sum != 1) {
            throw new SerException("股东占股之和必须为1");
        }

        String share = "";
        if (null != shareholders && shareholders.length() > 0) {
            share = shareholders.substring(0, shareholders.lastIndexOf(";"));
        }

        BusinessRegister businessRegister = super.findById(businessRegisterTO.getId());
        LocalDateTime dateTime = businessRegister.getCreateTime();
        businessRegister = BeanTransform.copyProperties(businessRegisterTO, BusinessRegister.class, true,"startOperationPeriod", "endOperationPeriod", "shareholdersTOList");
        businessRegister.setModifyTime(LocalDateTime.now());
        businessRegister.setCreateTime(dateTime);

        //转换经营期限
        if (StringUtils.isNotBlank(businessRegisterTO.getEndOperationPeriod())) {
            businessRegister.setOperationPeriod(businessRegisterTO.getStartOperationPeriod() + "至" + businessRegisterTO.getEndOperationPeriod());
        } else {
            businessRegister.setOperationPeriod(businessRegisterTO.getStartOperationPeriod());
        }
        businessRegister.setShareholders(share);

        super.update(businessRegister);
        return BeanTransform.copyProperties(businessRegisterTO, BusinessRegisterBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessRegister(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusinessTaxChangeDTO businessTaxChangeDTO = new BusinessTaxChangeDTO();
        businessTaxChangeDTO.getConditions().add(Restrict.eq("businessRegisterId", id));
        List<BusinessTaxChange> businessTaxChangeList = businessTaxChangeSer.findByCis(businessTaxChangeDTO);
        businessTaxChangeSer.remove(businessTaxChangeList);
        super.remove(id);
    }

    @Override
    public List<RegisterNaTypeCaBO> findRegiNaTyCa() throws SerException {
        List<BusinessRegister> businessRegisters = super.findAll();
        return BeanTransform.copyProperties(businessRegisters, RegisterNaTypeCaBO.class);
    }

    @Override
    public List<String> findAddress() throws SerException {
        List<BusinessRegister> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusinessRegister model : list) {
            String address = model.getAddress();
            if (StringUtils.isNotBlank(model.getAddress())) {
                set.add(address);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkAddIdentity();
        List<BusinessRegister> list = super.findAll();

        List<BusinessRegisterImprot> businessRegisterImprots = new ArrayList<>();
        list.stream().forEach(str -> {
            BusinessRegisterImprot excel = BeanTransform.copyProperties(str, BusinessRegisterImprot.class, "representativeLegal");
            excel.setRepresentativeLegal(str.getRepresentativeLegal()?"是":"否");
            businessRegisterImprots.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessRegisterImprots, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<BusinessRegisterExcelTO> businessRegisterExcelTOS) throws SerException {
       checkAddIdentity();
        List<BusinessRegister> businessRegisters = BeanTransform.copyProperties(businessRegisterExcelTOS, BusinessRegister.class, true);
        for (BusinessRegister str : businessRegisters){
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());

            //股东转换
            String[] str_shareholders = str.getShareholders().split(";");
            Double sum = 0d;
            if (null != str_shareholders && str_shareholders.length > 0) {
                for (String shareholder : str_shareholders) {
                    String[] str_str = shareholder.split(":");
                    if(str_str.length==2){
                        try {
                            sum += Double.parseDouble(str_str[1]);
                        }catch (RuntimeException e){
                            throw new SerException("股东:占股比例 格式不正确,正确格式为(李四:0.5;王五:0.5)");
                        }
                    }else{
                        throw new SerException("股东:占股比例 格式不正确,正确格式为(李四:0.5;王五:0.5)");
                    }
                }
            }
            if(sum!=1){
                throw new SerException("所有股东的占股比例之和必须为1");
            }

        }
        super.save(businessRegisters);
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<BusinessRegisterImprotTemple> businessRegisterImprotTemples = new ArrayList<>();
        BusinessRegisterImprotTemple excel = new BusinessRegisterImprotTemple();
        excel.setRegisterCompanyName("北京艾佳天城");
        excel.setRegisterNum("3655665");
        excel.setSetUpDate("2001-12-12");
        excel.setOperationPeriod("这里必须填写正确的格式 例如:2016-01-01至2017-12-12 或者 2017-12-12至长期 或者 长期");
        excel.setRegisterType("通讯");
        excel.setRegisterCapital("2000wa");
        excel.setOperationScope("北京,广州");
        excel.setPermittedBusiness("北京,上海");
        excel.setLegalPerson("李四");
        excel.setShareholders("这里必须填写正确的格式,如果有多个就用分号隔开(;),占股比例相加必须为1 例如:李四:0.5;王五:0.5");
        excel.setAddress("广州市天河区");
        excel.setStatus("开业");
        excel.setIssuingDate("2017-12-12");
        excel.setRegistrationAuthor("行政机构");
        excel.setOrganizationNemName("六六");
        excel.setPosition("咨询助理");
        excel.setPositionWay("招聘");
        excel.setRepresentativeLegal("是/否");
        excel.setUrl("http:baidu.com");
        excel.setCursorAdapter("成功学");
        excel.setRemark("重要重要");
        businessRegisterImprotTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessRegisterImprotTemples, exce);
        return bytes;
    }

    @Override
    public BusinessRegisterListBO findOneById(String id) throws SerException {
        BusinessRegister businessRegister = super.findById(id);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterListBO.class);
    }
}