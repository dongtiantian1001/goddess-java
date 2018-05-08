package com.bjike.goddess.managefee.action.managefee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managefee.api.OutFeeAPI;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.entity.OutFee;
import com.bjike.goddess.managefee.excel.OutFeeAreaExportDetail;
import com.bjike.goddess.managefee.excel.OutFeeImport;
import com.bjike.goddess.managefee.excel.SonPermissionObject;
import com.bjike.goddess.managefee.to.*;
import com.bjike.goddess.managefee.vo.OutFeeVO;
import com.bjike.goddess.managefee.vo.OutFeeVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 外包费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outfee")
public class OutFeeAction extends BaseFileAction {

    @Autowired
    private OutFeeAPI outFeeAPI;
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
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
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

            List<SonPermissionObject> hasPermissionList = outFeeAPI.sonPermission();
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

            Boolean isHasPermission = outFeeAPI.guidePermission(guidePermissionTO);
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
     *  列表总条数
     *
     * @param outFeeDTO  外包费信息dto
     * @des 获取所有外包费信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OutFeeDTO outFeeDTO) throws ActException {
        try {
            Long count = outFeeAPI.countOutFee(outFeeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个外包费
     *
     * @param id 外包费信息id
     * @des 根据id获取所有外包费信息
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            OutFeeVO outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.getOneById( id ), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 外包费列表
     *
     * @param outFeeDTO 外包费信息dto
     * @param request 前端过滤参数
     * @des 获取所有外包费信息
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListOutFee(OutFeeDTO outFeeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.listOutFee(outFeeDTO), OutFeeVO.class, request);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加外包费
     *
     * @param outFeeTO 外包费基本信息数据to
     * @des 添加外包费
     * @return  class OutFeeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addOutFee(@Validated(OutFeeTO.TestAdd.class) OutFeeTO outFeeTO, BindingResult bindingResult) throws ActException {
        try {
            OutFeeBO outFeeBO1 = outFeeAPI.addOutFee(outFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(outFeeBO1,OutFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑外包费
     *
     * @param outFeeTO 外包费基本信息数据bo
     * @des 编辑外包费
     * @return  class OutFeeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editOutFee(@Validated(OutFeeTO.TestAdd.class) OutFeeTO outFeeTO , BindingResult bindingResult) throws ActException {
        try {
            OutFeeBO outFeeBO1 = outFeeAPI.editOutFee(outFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(outFeeBO1,OutFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除外包费信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteOutFee(@PathVariable String id) throws ActException {
        try {
            outFeeAPI.deleteOutFee(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 根据地区汇总合计
     *
     * @param collectAreaTO 外包费信息dto
     * @des 根据地区汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctArea")
    public Result collectCom(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectArea(collectAreaTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据项目组汇总合计
     *
     * @param collectGroupTO 外包费信息dto
     * @des 根据项目组汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctGroup")
    public Result ctGroup(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectGroupTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectGroup(collectGroupTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总合计
     *
     * @param collectProjectTO 外包费信息dto
     * @des 根据项目汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result collectPro(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectProject(collectProjectTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据类别汇总合计
     *
     * @param collectCategoryTO 外包费信息dto
     * @des 根据类别汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctType")
    public Result ctType(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectType(collectCategoryTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区汇总详情
     *
     * @param collectAreaTO 地区汇总to
     * @return class OutFeeVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/collectArea/detail")
    public Result collectAreaDetial(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectAreaDetial(collectAreaTO), OutFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目组汇总详情
     *
     * @param collectGroupTO 管理费信息to
     * @return class OutFeeVO
     * @des 根据项目组汇总
     * @version v1
     */
    @GetMapping("v1/collectGroup/detail")
    public Result ctGroupDetial(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectGroupTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectGroupDetail(collectGroupTO), OutFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总详情
     *
     * @param collectProjectTO 管理费信息to
     * @return class OutFeeVO
     * @des 根据项目汇总
     * @version v1
     */
    @GetMapping("v1/collectProject/detail")
    public Result collectProDetial(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectProjectDetail(collectProjectTO), OutFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据类别汇总详情
     *
     * @param collectCategoryTO 管理费信息to
     * @return class OutFeeVO
     * @des 根据类别汇总
     * @version v1
     */
    @GetMapping("v1/collectType/detail")
    public Result ctTypeDetial(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , BindingResult bindingResult) throws ActException {
        try {
            List<OutFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectTypeDetail(collectCategoryTO), OutFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 获取所有年份
     *
     * @version v1
     */
    @GetMapping("v1/listYear")
    public Result yearList( ) throws ActException {
        try {
            List<String> list = outFeeAPI.yearList( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result areaList() throws ActException {
        try {
            List<String> list = outFeeAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目组
     *
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result groupList() throws ActException {
        try {
            List<String> list = outFeeAPI.groupList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目
     *
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result projectList() throws ActException {
        try {
            List<String> list = outFeeAPI.projectList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有汇总类别
     *
     * @version v1
     */
    @GetMapping("v1/listType")
    public Result typeList() throws ActException {
        try {
            List<String> list = outFeeAPI.typeList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出地区excel
     *
     * @param collectAreaTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/area/export")
    public Result areaExportReport(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "地区汇总.xlsx";
            super.writeOutFile(response, outFeeAPI.areaExportReport(collectAreaTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出项目名称excel
     *
     * @param collectProjectTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/project/export")
    public Result projectExportReport(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO ,  HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目名称汇总.xlsx";
            super.writeOutFile(response, outFeeAPI.projectExportReport(collectProjectTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出项目组excel
     *
     * @param collectProjectTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/group/export")
    public Result groupExportReport(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectProjectTO , HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目组汇总.xlsx";
            super.writeOutFile(response, outFeeAPI.groupExportReport(collectProjectTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出类别excel
     *
     * @param collectCategoryTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/type/export")
    public Result typeExportReport(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , HttpServletResponse response) throws ActException {
        try {
            String fileName = "类别汇总.xlsx";
            super.writeOutFile(response, outFeeAPI.typeExportReport(collectCategoryTO), fileName);
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
            List<OutFeeImport> tos = ExcelUtil.excelToClazz(is, OutFeeImport.class, excel);
            List<OutFeeTO> tocs = new ArrayList<>();
            for (OutFeeImport str : tos) {
                OutFeeTO outFeeTO = BeanTransform.copyProperties(str, OutFeeTO.class);
                if(StringUtils.isNotBlank(str.getMonth()) ){
                    if(  Integer.parseInt( str.getMonth().trim())>12  ||  Integer.parseInt( str.getMonth().trim())<=0 ){
                        throw new ActException("导入失败，月份必须在1-12月份");
                    }
                    str.setMonth( String.valueOf(Integer.parseInt( str.getMonth().trim())) );

                }
                tocs.add(outFeeTO);
            }
            //注意序列化
            outFeeAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * excel导入模板下载
     *
     * @des 下载模板外包费
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "外包费导入模板.xlsx";
            super.writeOutFile(response, outFeeAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }




}