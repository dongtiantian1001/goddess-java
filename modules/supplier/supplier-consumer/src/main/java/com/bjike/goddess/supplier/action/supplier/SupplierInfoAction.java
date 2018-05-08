package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.supplier.api.QualificationLevelSetAPI;
import com.bjike.goddess.supplier.api.SupplierInfoAPI;
import com.bjike.goddess.supplier.api.SupplierTypeSetAPI;
import com.bjike.goddess.supplier.bo.OptionBO;
import com.bjike.goddess.supplier.bo.SummationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.bo.SupplierInfoRegistraDataBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.excel.SupplierInfoExcel;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SiginManageDeleteFileTO;
import com.bjike.goddess.supplier.to.SupplierInfoRegistraDataTO;
import com.bjike.goddess.supplier.to.SupplierInfoTO;
import com.bjike.goddess.supplier.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商信息管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplierinfo")
public class SupplierInfoAction extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private SupplierInfoAPI supplierInfoAPI;
    @Autowired
    private SupplierTypeSetAPI supplierTypeSetAPI;
    @Autowired
    private QualificationLevelSetAPI qualificationLevelSetAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("propermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = supplierInfoAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = supplierInfoAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询供应商信息管理
     *
     * @param id 供应商信息管理唯一标识
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/supplierinfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.getOneById(id);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 供应商信息管理dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated SupplierInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = supplierInfoAPI.countSupplierInfo(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 供应商信息管理dto
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated SupplierInfoDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<SupplierInfoBO> boList = supplierInfoAPI.listSupplierInfo(dto);
            List<SupplierInfoVO> voList = BeanTransform.copyProperties(boList, SupplierInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加供应商信息管理
     *
     * @param to 供应商信息管理to信息
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) SupplierInfoTO to, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.addSupplierInfo(to);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除供应商信息管理
     *
     * @param id 供应商信息管理唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            supplierInfoAPI.deleteSupplierInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑供应商信息管理
     *
     * @param to 供应商信息管理to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) SupplierInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.editSupplierInfo(to);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出Excel
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "供应商信息管理.xlsx";
            super.writeOutFile(response, supplierInfoAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SupplierInfoExcel> tos = ExcelUtil.excelToClazz(is, SupplierInfoExcel.class, excel);
            List<SupplierInfoTO> tocs = new ArrayList<>();
            for (SupplierInfoExcel str : tos) {
                SupplierInfoTO supplierInfoTO = BeanTransform.copyProperties(str, SupplierInfoTO.class,
                        "infoCollectDate", "newSigningDate", "newCutoffDate", "settlementCompleteDate",
                        "uploadBusinLicense", "uploadQualifition", "deterCooper", "payComplete", "infoPerfecting");
                supplierInfoTO.setInfoCollectDate(str.getInfoCollectDate().toString());
                supplierInfoTO.setNewSigningDate(str.getNewSigningDate().toString());
                supplierInfoTO.setNewCutoffDate(str.getNewCutoffDate().toString());
                supplierInfoTO.setSettlementCompleteDate(str.getSettlementCompleteDate().toString());
                supplierInfoTO.setUploadBusinLicense(stringToBool(str.getUploadBusinLicense(),"是否上传营业执照附件"));
                supplierInfoTO.setUploadQualifition(stringToBool(str.getUploadQualifition(),"是否上传资质附件"));
                supplierInfoTO.setDeterCooper(stringToBool(str.getDeterCooper(),"是否确定合作"));
                supplierInfoTO.setPayComplete(stringToBool(str.getPayComplete(),"是否付款完成"));
                supplierInfoTO.setInfoPerfecting(stringToBool(str.getInfoPerfecting(),"供应商信息是否完善"));
                tocs.add(supplierInfoTO);
            }
            //注意序列化
            supplierInfoAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private Boolean stringToBool(String type, String fileName) throws ActException {
        Boolean bool = null;
        if (type != null) {
            switch (type) {
                case "是":
                    bool = true;
                    break;
                case "否":
                    bool = false;
                    break;
                default:
                    throw new ActException(fileName + "格式输入错误,正确格式为(是/否)");

            }
        }
        return bool;
    }

    /**
     * excel模板下载
     *
     * @des 供应商信息管理模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "供应商信息管理模板.xlsx";
            super.writeOutFile(response, supplierInfoAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
    /**
     * 供应商信息详情获取数据
     *
     * @param id 供应商信息管理id
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/linkSupplierData")
    public Result linkSupplierData(@RequestParam String id, HttpServletRequest request) throws ActException {
        try {
            SupplierInfoRegistraDataBO bo = supplierInfoAPI.linkSupplierData(id);
            SupplierInfoRegistraDataVO vo = BeanTransform.copyProperties(bo, SupplierInfoRegistraDataVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商信息详情编辑
     *
     * @param supplierInfoRegistraDataTO 供应商信息管理to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addSupplierDetail")
    public Result addSupplierDetail(@Validated(value = {ADD.class}) SupplierInfoRegistraDataTO supplierInfoRegistraDataTO, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            supplierInfoAPI.addSupplierDetail(supplierInfoRegistraDataTO);
            return new  ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有的资质等级设置
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllLevel")
    public Result findAllLevel() throws ActException {
        try {
            List<String> types = qualificationLevelSetAPI.findAllLevel();
            return ActResult.initialize(types);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有的供应商类型
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllType")
    public Result findAllType() throws ActException {
        try {
            List<String> types = supplierTypeSetAPI.findAllType();
            return ActResult.initialize(types);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理日汇总
     *
     * @param date 日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaDay(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商管理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaWeek(year, month, week);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaMonth(year, month);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/quarter")
    public Result summarizeQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaQuarter(year, quarter);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商管理年度汇总
     *
     * @param year  年份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/year")
    public Result summarizeYear(Integer year,  HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaYear(year);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = supplierInfoAPI.summaTotal(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商管理图形展示日汇总
     *
     * @param date 日期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowDay(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);

            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理图形展示周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商管理图形展示月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowMonth(year, month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商管理图形展示季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/quarter")
    public Result figureShowQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowQuarter(year, quarter);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 供应商管理图形展示年度汇总
     *
     * @param year  年份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/year")
    public Result figureShowQuarter(Integer year, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 供应商管理图形展示累计汇总
     * @return class OptionVO
     * @param date 截止日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = supplierInfoAPI.figureShowTotal(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}